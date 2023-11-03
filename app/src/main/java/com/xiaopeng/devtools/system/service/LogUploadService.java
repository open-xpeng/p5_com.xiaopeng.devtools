package com.xiaopeng.devtools.system.service;

import android.app.IntentService;
import android.car.Car;
import com.google.gson.Gson;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.utils.j;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.IRemoteStorage;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.StorageException;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.framework.netchannelmodule.http.xmart.bizapi.BizConstants;
import com.xiaopeng.lib.utils.a;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.c.b;
import com.xiaopeng.lib.utils.i;
import com.xiaopeng.logictree.d;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class LogUploadService extends IntentService {
    private boolean Bk;

    public LogUploadService() {
        this("LogUploadService");
    }

    public LogUploadService(String str) {
        super(str);
        this.Bk = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x016e  */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.io.IOException] */
    @Override // android.app.IntentService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onHandleIntent(android.content.Intent r12) {
        /*
            Method dump skipped, instructions count: 385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.system.service.LogUploadService.onHandleIntent(android.content.Intent):void");
    }

    private void D(String str, String str2) {
        IRemoteStorage iRemoteStorage = (IRemoteStorage) Module.get(NetworkChannelsEntry.class).get(IRemoteStorage.class);
        long currentTimeMillis = System.currentTimeMillis();
        String str3 = "xmart-cdu-service-log/" + b.getSystemVersion() + File.separatorChar + a.n(currentTimeMillis) + File.separatorChar + i.lp() + File.separatorChar + str2 + ".zip";
        HashMap hashMap = new HashMap();
        hashMap.put("callbackUrl", "https://v-callback.xiaopeng.com/oss/vehicle/oss/callback/feedback");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("app_id", "xmart:appid:002");
        hashMap2.put("device", i.getHardwareId());
        hashMap2.put("timestamp", String.valueOf(currentTimeMillis));
        hashMap2.put("sid", i.oS());
        hashMap2.put("type", String.valueOf(1));
        hashMap2.put("address", "http://xp-log.oss-cn-hangzhou.aliyuncs.com/" + str3);
        hashMap2.put("timer", String.valueOf(currentTimeMillis));
        try {
            hashMap2.put("vmodel", Car.getHardwareCarType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        hashMap2.put("sign", j.a(MyApplication.getContext(), hashMap2, currentTimeMillis));
        c.g("LogUploadService", new Gson().toJson(hashMap2));
        hashMap.put("callbackBody", new Gson().toJson(hashMap2));
        hashMap.put("callbackBodyType", BizConstants.CONTENT_TYPE_JSON);
        try {
            iRemoteStorage.initWithContext(MyApplication.qx);
            iRemoteStorage.uploadWithPathAndCallback("xp-log", str3, str, new Callback() { // from class: com.xiaopeng.devtools.system.service.LogUploadService.1
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback
                public void onStart(String str4, String str5) {
                    c.g("LogUploadService", "uploadLog onStart");
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback
                public void onSuccess(String str4, String str5) {
                    c.g("LogUploadService", "uploadLog onSuccess");
                    LogUploadService.this.S(true);
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback
                public void onFailure(String str4, String str5, StorageException storageException) {
                    c.g("LogUploadService", "uploadLog onFailure");
                    LogUploadService.this.S(false);
                }
            }, hashMap);
        } catch (Exception e2) {
            e2.printStackTrace();
            S(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S(boolean z) {
        c.g("LogUploadService", "removeUploadFolder");
        com.xiaopeng.lib.utils.b.bn("sdcard/aftersales/");
        if (z) {
            d.aS(this.Bk);
        } else {
            d.aR(this.Bk);
        }
    }
}
