package com.xiaopeng.datalog.stat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xiaopeng.datalog.b.a;
import com.xiaopeng.datalog.bean.LogEvent;
import com.xiaopeng.datalog.d;
import com.xiaopeng.lib.bughunter.IDataUploadInterface;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.c.b;
import com.xiaopeng.lib.utils.i;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.lib.utils.k;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes11.dex */
public class DataCollectorHelper {
    public static final String BUCKET_AND_ENDPOINT;
    public static final String BUCKET_NAME;
    private static final int CHECK_CONNECTION_DELAY = 10000;
    private static final boolean DEBUG = false;
    private static final int DUMP_CACHE_DELAY = 60000;
    private static final String LOG_PATH = "/data/Log/log0/";
    private static final int MAX_NUM_PER_ZIP_DATA_LOCAL = 20;
    private static final int MESSAGE_CHECK_CONNECTION = 3;
    private static final int MESSAGE_DUMP_CAN = 1;
    private static final int MESSAGE_DUMP_CDU = 2;
    private static final String SEPARATOR = "#";
    private static final String TAG = "DataCollectorHelper";
    private static volatile DataCollectorHelper mInstance;
    private Context mContext;
    private IDataUploadInterface remoteService;
    private final List<String> mCanDataCache = new CopyOnWriteArrayList();
    private final List<Map<String, Object>> mCduDataCache = new CopyOnWriteArrayList();
    private Gson mGson = new Gson();
    private Handler mHandler = new Handler(j.dR(0), new Handler.Callback() { // from class: com.xiaopeng.datalog.stat.DataCollectorHelper.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    c.e(DataCollectorHelper.TAG, "handleMessage MESSAGE_DUMP_CAN");
                    DataCollectorHelper.this.dumpCan2File();
                    return true;
                case 2:
                    c.f(DataCollectorHelper.TAG, "handleMessage MESSAGE_DUMP_CDU");
                    DataCollectorHelper.this.dumpCdu2File();
                    return true;
                case 3:
                    c.f(DataCollectorHelper.TAG, "handleMessage MESSAGE_CHECK_CONNECTION");
                    if (DataCollectorHelper.this.remoteService == null) {
                        DataCollectorHelper.this.bindService();
                        return true;
                    }
                    return true;
                default:
                    return true;
            }
        }
    });
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.xiaopeng.datalog.stat.DataCollectorHelper.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            c.f(DataCollectorHelper.TAG, "onServiceConnected");
            DataCollectorHelper.this.remoteService = IDataUploadInterface.Stub.asInterface(iBinder);
            IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: com.xiaopeng.datalog.stat.DataCollectorHelper.2.1
                @Override // android.os.IBinder.DeathRecipient
                public void binderDied() {
                    if (DataCollectorHelper.this.remoteService != null) {
                        DataCollectorHelper.this.remoteService.asBinder().unlinkToDeath(this, 0);
                        DataCollectorHelper.this.remoteService = null;
                        DataCollectorHelper.this.bindService();
                    }
                }
            };
            try {
                if (DataCollectorHelper.this.remoteService != null) {
                    DataCollectorHelper.this.remoteService.asBinder().linkToDeath(deathRecipient, 0);
                }
            } catch (RemoteException e) {
                c.b(DataCollectorHelper.TAG, "RemoteException occurs when reLink to Service, exception:", e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            c.f(DataCollectorHelper.TAG, "onServiceDisconnected");
            DataCollectorHelper.this.remoteService = null;
            DataCollectorHelper.this.bindService();
        }
    };

    static {
        BUCKET_NAME = b.pk() ? "xp-log-local" : "xp-log";
        BUCKET_AND_ENDPOINT = "http://" + BUCKET_NAME + ".oss-cn-hangzhou.aliyuncs.com/";
    }

    private DataCollectorHelper() {
    }

    public static DataCollectorHelper getInstance() {
        if (mInstance == null) {
            synchronized (DataCollectorHelper.class) {
                if (mInstance == null) {
                    mInstance = new DataCollectorHelper();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        bindService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindService() {
        Intent intent = new Intent("com.xiaopeng.service.DATA_SERVICE");
        intent.setPackage("com.xiaopeng.data.uploader");
        this.mContext.startServiceAsUser(intent, UserHandle.CURRENT);
        this.mContext.bindServiceAsUser(intent, this.mConnection, 1, UserHandle.CURRENT);
        if (!this.mHandler.hasMessages(3)) {
            this.mHandler.sendEmptyMessageDelayed(3, 10000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isConnected() {
        return this.remoteService != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uploadCdu(java.lang.String r4) {
        /*
            r3 = this;
            com.xiaopeng.lib.bughunter.IDataUploadInterface r0 = r3.remoteService
            if (r0 == 0) goto L1f
            com.xiaopeng.lib.bughunter.IDataUploadInterface r0 = r3.remoteService     // Catch: android.os.RemoteException -> L17
            r0.uploadLog(r4)     // Catch: android.os.RemoteException -> L17
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r0 = r3.mCduDataCache     // Catch: android.os.RemoteException -> L17
            int r0 = r0.size()     // Catch: android.os.RemoteException -> L17
            if (r0 <= 0) goto L15
            r3.dumpCdu2File()     // Catch: android.os.RemoteException -> L17
        L15:
            r0 = 1
            goto L20
        L17:
            r0 = move-exception
            java.lang.String r1 = "DataCollectorHelper"
            java.lang.String r2 = "uploadCdu error!"
            com.xiaopeng.lib.utils.c.b(r1, r2, r0)
        L1f:
            r0 = 0
        L20:
            if (r0 != 0) goto L36
            com.google.gson.Gson r0 = r3.mGson
            com.xiaopeng.datalog.stat.DataCollectorHelper$3 r1 = new com.xiaopeng.datalog.stat.DataCollectorHelper$3
            r1.<init>()
            java.lang.reflect.Type r1 = r1.getType()
            java.lang.Object r4 = r0.fromJson(r4, r1)
            java.util.Map r4 = (java.util.Map) r4
            r3.uploadCduLogInternal(r4)
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.datalog.stat.DataCollectorHelper.uploadCdu(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uploadLogOrigin(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            com.xiaopeng.lib.bughunter.IDataUploadInterface r0 = r2.remoteService
            if (r0 == 0) goto L1b
            com.xiaopeng.lib.bughunter.IDataUploadInterface r0 = r2.remoteService     // Catch: android.os.RemoteException -> L17
            r0.uploadLogOrigin(r3, r4)     // Catch: android.os.RemoteException -> L17
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r0 = r2.mCduDataCache     // Catch: android.os.RemoteException -> L17
            int r0 = r0.size()     // Catch: android.os.RemoteException -> L17
            if (r0 <= 0) goto L15
            r2.dumpCdu2File()     // Catch: android.os.RemoteException -> L17
        L15:
            r0 = 1
            goto L1c
        L17:
            r0 = move-exception
            r0.printStackTrace()
        L1b:
            r0 = 0
        L1c:
            if (r0 != 0) goto L56
            java.lang.Class<com.xiaopeng.datalog.a> r0 = com.xiaopeng.datalog.a.class
            com.xiaopeng.lib.framework.module.IModuleEntry r0 = com.xiaopeng.lib.framework.module.Module.get(r0)
            java.lang.Class<com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog> r1 = com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog.class
            java.lang.Object r0 = r0.get(r1)
            com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog r0 = (com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog) r0
            com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEventBuilder r0 = r0.buildStat()
            com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEventBuilder r3 = r0.setEventName(r3)
            java.lang.String r0 = "data"
            com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEventBuilder r3 = r3.setProperty(r0, r4)
            com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent r3 = r3.build()
            java.lang.String r3 = r3.toJson()
            com.google.gson.Gson r4 = r2.mGson
            com.xiaopeng.datalog.stat.DataCollectorHelper$4 r0 = new com.xiaopeng.datalog.stat.DataCollectorHelper$4
            r0.<init>()
            java.lang.reflect.Type r0 = r0.getType()
            java.lang.Object r3 = r4.fromJson(r3, r0)
            java.util.Map r3 = (java.util.Map) r3
            r2.uploadCduLogInternal(r3)
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.datalog.stat.DataCollectorHelper.uploadLogOrigin(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uploadCan(java.lang.String r5) {
        /*
            r4 = this;
            com.xiaopeng.lib.bughunter.IDataUploadInterface r0 = r4.remoteService
            r1 = 1
            if (r0 == 0) goto L1e
            com.xiaopeng.lib.bughunter.IDataUploadInterface r0 = r4.remoteService     // Catch: android.os.RemoteException -> L1a
            r0.uploadCan(r5)     // Catch: android.os.RemoteException -> L1a
            java.util.List<java.lang.String> r0 = r4.mCanDataCache     // Catch: android.os.RemoteException -> L1a
            int r0 = r0.size()     // Catch: android.os.RemoteException -> L1a
            if (r0 <= 0) goto L16
            r4.dumpCan2File()     // Catch: android.os.RemoteException -> L1a
        L16:
            r0 = r1
            goto L1f
        L1a:
            r0 = move-exception
            r0.printStackTrace()
        L1e:
            r0 = 0
        L1f:
            if (r0 != 0) goto L44
            java.util.List<java.lang.String> r0 = r4.mCanDataCache
            r0.add(r5)
            java.util.List<java.lang.String> r5 = r4.mCanDataCache
            int r5 = r5.size()
            r0 = 20
            if (r5 < r0) goto L34
            r4.dumpCan2File()
            goto L44
        L34:
            android.os.Handler r5 = r4.mHandler
            boolean r5 = r5.hasMessages(r1)
            if (r5 != 0) goto L44
            android.os.Handler r5 = r4.mHandler
            r2 = 60000(0xea60, double:2.9644E-319)
            r5.sendEmptyMessageDelayed(r1, r2)
        L44:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.datalog.stat.DataCollectorHelper.uploadCan(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dumpCan2File() {
        if (this.mCanDataCache.isEmpty()) {
            c.f(TAG, "mCanDataCache.isEmpty(), return!");
            return;
        }
        a.eW().a("candata", k.dK(buildCanDataZipJson()), this.mContext);
        this.mHandler.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dumpCdu2File() {
        if (this.mCduDataCache.isEmpty()) {
            c.f(TAG, "mCduDataCache.isEmpty(), return!");
        }
        a.eW().a("cdudata", k.dK(buildCduDataZipJson()), this.mContext);
        this.mHandler.removeMessages(2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uploadLogImmediately(java.lang.String r2, java.lang.String r3) {
        /*
            r1 = this;
            com.xiaopeng.lib.bughunter.IDataUploadInterface r0 = r1.remoteService
            if (r0 == 0) goto L14
            com.xiaopeng.lib.bughunter.IDataUploadInterface r0 = r1.remoteService     // Catch: android.os.RemoteException -> Lc
            r0.uploadLogImmediately(r2, r3)     // Catch: android.os.RemoteException -> Lc
            r2 = 1
            goto L15
        Lc:
            r2 = move-exception
            java.lang.String r3 = "DataCollectorHelper"
            java.lang.String r0 = "uploadLogImmediately error!"
            com.xiaopeng.lib.utils.c.b(r3, r0, r2)
        L14:
            r2 = 0
        L15:
            if (r2 != 0) goto L1e
            java.lang.String r2 = "DataCollectorHelper"
            java.lang.String r3 = "uploadLogImmediately fail and ignore!"
            com.xiaopeng.lib.utils.c.h(r2, r3)
        L1e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.datalog.stat.DataCollectorHelper.uploadLogImmediately(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void uploadCduWithFiles(final d dVar, final List<String> list) {
        final String[] generateUploadInfos = generateUploadInfos();
        j.d(new Runnable() { // from class: com.xiaopeng.datalog.stat.DataCollectorHelper.5
            @Override // java.lang.Runnable
            public void run() {
                DataCollectorHelper.this.zipAndEncrypt(generateUploadInfos[1], list);
                dVar.put("address", generateUploadInfos[0]);
                String json = dVar.toJson();
                ArrayList arrayList = new ArrayList();
                arrayList.add(generateUploadInfos[1].replace(".zip", "_en.zip"));
                DataCollectorHelper.this.uploadCdu(json);
                DataCollectorHelper.this.uploadFiles(arrayList);
            }
        });
    }

    private String[] generateUploadInfos() {
        long currentTimeMillis = System.currentTimeMillis();
        String str = BUCKET_NAME + "/log/" + b.getSystemVersion() + "/" + com.xiaopeng.lib.utils.a.n(currentTimeMillis) + "/" + i.lp();
        return new String[]{generateRemoteUrl(currentTimeMillis, str), generateFilePath(currentTimeMillis, str)};
    }

    private String generateRemoteUrl(long j, String str) {
        return BUCKET_AND_ENDPOINT + (str.substring(str.indexOf("/") + 1) + "/" + j + "_en.zip");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zipAndEncrypt(String str, List<String> list) {
        File file;
        try {
            file = k.b(str, list);
        } catch (IOException e) {
            e.printStackTrace();
            file = null;
        }
        deleteLocalFile(file, com.xiaopeng.lib.utils.b.a.a(file, new File(str.replace(".zip", "_en.zip")), "@!chxpzi#0109$+/"));
    }

    @NonNull
    private String generateFilePath(long j, String str) {
        String str2 = "/sdcard/Log/upload-zip/" + str;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2 + "/" + j + ".zip";
    }

    private void deleteLocalFile(File file, boolean z) {
        if (z) {
            try {
                file.delete();
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    private String generateAllFilePathString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                sb.append(list.get(i));
                sb.append(SEPARATOR);
            } else {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void uploadFiles(List<String> list) {
        if (this.remoteService != null) {
            try {
                this.remoteService.uploadFiles(list);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    private String buildCanDataZipJson() {
        LogEvent create = LogEvent.create(LogEvent.RefType.CAN);
        try {
            create.setV(Integer.valueOf(i.oT()).intValue());
        } catch (Exception e) {
            c.b(TAG, "parse dbcVersion error!", e);
            create.setV(0);
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("data", this.mGson.toJson(this.mCanDataCache));
        this.mCanDataCache.clear();
        ArrayList arrayList = new ArrayList();
        arrayList.add(jsonObject);
        create.setMsg(arrayList);
        String json = this.mGson.toJson(create);
        arrayList.clear();
        return json;
    }

    private void uploadCduLogInternal(Map<String, Object> map) {
        this.mCduDataCache.add(map);
        if (this.mCduDataCache.size() >= 20) {
            dumpCdu2File();
        } else if (!this.mHandler.hasMessages(2)) {
            this.mHandler.sendEmptyMessageDelayed(2, 60000L);
        }
    }

    private String buildCduDataZipJson() {
        LogEvent create = LogEvent.create(LogEvent.RefType.CDU);
        try {
            create.setV(Integer.valueOf(i.oT()).intValue());
        } catch (Exception e) {
            c.b(TAG, "parse dbcVersion error!", e);
            create.setV(0);
        }
        ArrayList arrayList = new ArrayList(this.mCduDataCache);
        this.mCduDataCache.clear();
        create.setMsg(arrayList);
        return this.mGson.toJson(create);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String uploadRecentSystemLog() {
        final String str = generateUploadInfos()[0];
        j.d(new Runnable() { // from class: com.xiaopeng.datalog.stat.DataCollectorHelper.6
            @Override // java.lang.Runnable
            public void run() {
                if (DataCollectorHelper.this.isConnected()) {
                    DataCollectorHelper.this.internalUploadRecentSystemLog(str);
                } else {
                    j.c(new Runnable() { // from class: com.xiaopeng.datalog.stat.DataCollectorHelper.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DataCollectorHelper.this.internalUploadRecentSystemLog(str);
                        }
                    }, 1000L);
                }
            }
        });
        return str;
    }

    void internalUploadRecentSystemLog(String str) {
        if (this.remoteService != null) {
            try {
                c.g(TAG, "internalUploadRecentSystemLog " + str);
                this.remoteService.uploadSystemLog(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
