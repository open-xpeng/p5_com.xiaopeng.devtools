package com.xiaopeng.devtools.system.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.can.CanDataFeature;
import com.xiaopeng.devtools.bean.can.CanDataNode;
import com.xiaopeng.devtools.model.can.CanCollectModel;
import com.xiaopeng.devtools.system.c.b;
import com.xiaopeng.devtools.utils.d;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.t;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class CanDataCollectService extends Service {
    private b AA;
    private b AB;
    private FileWriter AC;
    private ExecutorService AD;
    private ExecutorService AE;
    private double AF;
    private String AG;
    private String AH;
    private byte[] AN;
    private byte[] AO;
    private byte[] AP;
    private byte[] AQ;
    private LinkedBlockingQueue<CanDataNode> Az;
    private File mFile;
    private int mLength;
    private String mPath;
    private boolean rt = false;
    private int AI = -1;
    private int AJ = 0;
    private int AK = 0;
    private int AL = 0;
    private int AM = 0;
    private boolean AR = false;
    private final BroadcastReceiver AS = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.system.service.CanDataCollectService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            c.f("CanDataCollectService", "onReceive action-->" + action);
            if (action.equals("android.intent.action.MEDIA_EJECT") || action.equals("android.intent.action.MEDIA_REMOVED")) {
                if (CanCollectModel.fo().fp() == 10002) {
                    CanCollectModel.fo().setErrorMsg(CanDataCollectService.this.getString(R.string.candata_errormsg, new Object[]{t.cL("yyyy-MM-dd HH:mm:ss"), CanDataCollectService.this.getString(R.string.tips_udisk_unpluged)}));
                    CanDataCollectService.this.kM();
                }
            } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                CanCollectModel.fo().setErrorMsg(CanDataCollectService.this.getString(R.string.candata_errormsg, new Object[]{t.cL("yyyy-MM-dd HH:mm:ss"), CanDataCollectService.this.getString(R.string.tips_car_power_off)}));
                CanDataCollectService.this.kM();
            }
        }
    };
    private Runnable AT = new Runnable() { // from class: com.xiaopeng.devtools.system.service.CanDataCollectService.2
        @Override // java.lang.Runnable
        public void run() {
            while (CanDataCollectService.this.rt) {
                while (!CanDataCollectService.this.Az.isEmpty()) {
                    CanDataNode canDataNode = (CanDataNode) CanDataCollectService.this.Az.poll();
                    while (!canDataNode.isParsed()) {
                        if (MyApplication.eZ()) {
                            c.f("CanDataCollectService", "canDataNode is not parsed, wait...");
                        }
                        try {
                            Thread.sleep(50L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (MyApplication.eZ()) {
                        c.f("CanDataCollectService", "start to lockData");
                    }
                    canDataNode.lockData();
                    if (MyApplication.eZ()) {
                        c.f("CanDataCollectService", "save data queue : " + canDataNode.getQueque());
                    }
                    if (canDataNode.isDataNormal() && canDataNode.getParsingString() != null) {
                        CanDataCollectService.this.cw(canDataNode.getParsingString().length());
                        if (canDataNode.isWithTriggerEvent()) {
                            if (canDataNode.getTriggerFlag() == 1 && CanDataCollectService.this.AI == 0 && canDataNode.getTriggerEvent() != null) {
                                CanDataCollectService.this.cn(canDataNode.getTriggerEvent());
                            }
                            CanDataCollectService.this.AI = canDataNode.getTriggerFlag();
                        }
                        CanDataCollectService.this.cn(canDataNode.getParsingString());
                    }
                    if (MyApplication.eZ()) {
                        c.f("CanDataCollectService", "start to unlockData");
                    }
                    canDataNode.unlockData();
                }
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            if (MyApplication.eZ()) {
                c.f("CanDataCollectService", "mCanDataQueue finish");
            }
            if (CanCollectModel.fo().fp() == 10002) {
                CanDataCollectService.this.cn(CanDataCollectService.this.getString(R.string.tail_candata_file));
            }
            CanDataCollectService.this.kL();
            if (!CanDataCollectService.this.rt) {
                CanDataCollectService.this.Q(false);
            }
        }
    };
    private Thread AU = new Thread(new Runnable() { // from class: com.xiaopeng.devtools.system.service.CanDataCollectService.3
        @Override // java.lang.Runnable
        public void run() {
            CanDataCollectService.this.kJ();
            while (CanDataCollectService.this.rt) {
                try {
                    if (CanDataCollectService.this.AN != null) {
                        CanDataCollectService.this.AA.y(CanDataCollectService.this.AN);
                    }
                    CanDataCollectService.this.AN = CanDataCollectService.this.AO != null ? (byte[]) CanDataCollectService.this.AO.clone() : CanDataCollectService.this.AA.cB(1);
                    CanDataCollectService.this.AO = null;
                    if (CanDataCollectService.this.AN[0] == 120) {
                        CanDataCollectService.this.AO = CanDataCollectService.this.AA.cB(1);
                        switch (CanDataCollectService.this.AO[0]) {
                            case 112:
                            case 113:
                            case 114:
                                CanDataCollectService.this.AP = CanDataCollectService.this.AA.cB(2);
                                CanDataCollectService.this.mLength = ((CanDataCollectService.this.AP[0] & 255) * 256) + (CanDataCollectService.this.AP[1] & 255);
                                CanDataCollectService.this.AA.y(CanDataCollectService.this.AP);
                                CanDataCollectService.this.AQ = CanDataCollectService.this.AA.cB(CanDataCollectService.this.mLength);
                                if (MyApplication.eZ()) {
                                    c.f("CanDataCollectService", "mReadBuffTimes:" + CanDataCollectService.this.AJ);
                                }
                                CanDataCollectService.m(CanDataCollectService.this);
                                CanDataNode canDataNode = new CanDataNode(CanDataCollectService.this.AQ, CanDataCollectService.this.mLength, CanDataCollectService.this.AO, CanDataCollectService.o(CanDataCollectService.this));
                                CanDataCollectService.this.a(canDataNode);
                                CanDataCollectService.this.Az.put(canDataNode);
                                CanDataCollectService.this.AO = null;
                                continue;
                            default:
                                continue;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    CanDataCollectService.this.AA.disconnect();
                    com.xiaopeng.devtools.system.b.c.sleep(2000L);
                }
            }
        }
    });

    static /* synthetic */ int m(CanDataCollectService canDataCollectService) {
        int i = canDataCollectService.AJ;
        canDataCollectService.AJ = i + 1;
        return i;
    }

    static /* synthetic */ int o(CanDataCollectService canDataCollectService) {
        int i = canDataCollectService.AM;
        canDataCollectService.AM = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cn(String str) {
        switch (CanCollectModel.fo().fp()) {
            case 10001:
                try {
                    if (this.AB != null) {
                        this.AB.x(str.getBytes());
                        return;
                    }
                    return;
                } catch (IOException e) {
                    c.i("CanDataCollectService", e.toString());
                    CanCollectModel.fo().setErrorMsg(getString(R.string.candata_errormsg_to_pc, new Object[]{t.cL("yyyy-MM-dd HH:mm:ss"), e.getLocalizedMessage()}));
                    kM();
                    return;
                }
            case 10002:
                try {
                    if (this.AC != null) {
                        this.AC.write(str);
                        return;
                    }
                    return;
                } catch (IOException e2) {
                    c.i("CanDataCollectService", e2.toString());
                    CanCollectModel.fo().setErrorMsg(getString(R.string.candata_errormsg, new Object[]{t.cL("yyyy-MM-dd HH:mm:ss"), e2.getLocalizedMessage()}));
                    kM();
                    return;
                }
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final CanDataNode canDataNode) {
        this.AD.execute(new Runnable() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$CanDataCollectService$rob15q1Tu8DlOgZo8Hv4fjyzw0k
            @Override // java.lang.Runnable
            public final void run() {
                CanDataCollectService.this.c(canDataNode);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.xiaopeng.devtools.system.c.b] */
    /* JADX WARN: Type inference failed for: r0v6 */
    public /* synthetic */ void c(CanDataNode canDataNode) {
        boolean z = 1;
        z = 1;
        try {
            try {
                canDataNode.lockData();
                canDataNode.setDataNormal(b(canDataNode));
            } catch (Exception e) {
                e.printStackTrace();
                canDataNode.setDataNormal(false);
            }
        } finally {
            canDataNode.setParsed(z);
            this.AA.y(canDataNode.getData());
            this.AA.y(canDataNode.getType());
            canDataNode.unlockData();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v2 */
    private boolean b(CanDataNode canDataNode) {
        byte[] bArr;
        int i;
        long j;
        String str;
        byte[] data = canDataNode.getData();
        int dataLength = canDataNode.getDataLength();
        ?? r5 = 0;
        byte b = canDataNode.getType()[0];
        int queque = canDataNode.getQueque();
        if (MyApplication.eZ()) {
            StringBuilder sb = new StringBuilder();
            sb.append("mParseTime:");
            int i2 = this.AK;
            this.AK = i2 + 1;
            sb.append(i2);
            sb.append(", queue:");
            sb.append(queque);
            c.f("CanDataCollectService", sb.toString());
        }
        CanDataFeature canDataFeature = new CanDataFeature();
        ?? r10 = 1;
        switch (b) {
            case 112:
                canDataFeature.setMinLength(4);
                canDataFeature.setMinEachCanLength(3);
                canDataFeature.setStartpoint(1);
                canDataFeature.setChannelbitoffset(3);
                canDataFeature.setCanidbit((byte) 7);
                canDataFeature.setWithValidbit(true);
                canDataFeature.setWithTimestamp(false);
                canDataFeature.setWithTriggerEvent(false);
                canDataFeature.setDLC(false);
                break;
            case 113:
                canDataFeature.setMinLength(10);
                canDataFeature.setMinEachCanLength(5);
                canDataFeature.setStartpoint(5);
                canDataFeature.setChannelbitoffset(4);
                canDataFeature.setCanidbit((byte) 7);
                canDataFeature.setWithValidbit(false);
                canDataFeature.setWithTimestamp(true);
                canDataFeature.setWithTriggerEvent(true);
                canDataFeature.setDLC(true);
                break;
            case 114:
                canDataFeature.setMinLength(12);
                canDataFeature.setMinEachCanLength(5);
                canDataFeature.setStartpoint(7);
                canDataFeature.setChannelbitoffset(4);
                canDataFeature.setCanidbit((byte) 7);
                canDataFeature.setWithValidbit(false);
                canDataFeature.setWithTimestamp(true);
                canDataFeature.setWithTriggerEvent(true);
                canDataFeature.setDLC(true);
                break;
            default:
                c.f("CanDataCollectService", "parseData bad type:" + d.a(new byte[]{b}, false));
                return false;
        }
        if (a(data, dataLength, canDataFeature)) {
            String str2 = "";
            long j2 = 0;
            if (!canDataFeature.isWithTimestamp()) {
                str2 = a(t.lE() - this.AF, "%.6f");
            } else {
                j2 = d.C(d.c(data, 1, 4));
            }
            canDataNode.setWithTriggerEvent(canDataFeature.isWithTriggerEvent());
            canDataNode.setTriggerFlag(d.b(data[0]));
            if (canDataFeature.isWithTriggerEvent()) {
                canDataNode.setTriggerEvent(a(j2 / 10000.0d, "%.4f") + "   TriggerEvent: TriggerBlock[TCP Connect]");
            }
            StringBuilder sb2 = new StringBuilder();
            byte[] bArr2 = new byte[2];
            int startpoint = canDataFeature.getStartpoint();
            while (startpoint < dataLength) {
                int channelbitoffset = data[startpoint] >> canDataFeature.getChannelbitoffset();
                int i3 = data[startpoint + 2];
                if (canDataFeature.isDLC()) {
                    i3 = d.cC(i3);
                }
                if (CanCollectModel.fo().bU(channelbitoffset)) {
                    bArr2[r5] = (byte) (data[startpoint] & canDataFeature.getCanidbit());
                    bArr2[r10] = data[startpoint + 1];
                    String substring = d.a(bArr2, (boolean) r5).substring(r10);
                    int i4 = startpoint + 3;
                    i = dataLength;
                    String a = d.a(d.c(data, i4, i3), (boolean) r10);
                    String cy = cy(channelbitoffset);
                    String str3 = str2;
                    String cx = cx(i3);
                    if (canDataFeature.isWithTimestamp()) {
                        int i5 = i4 + i3;
                        bArr2[0] = data[i5];
                        bArr2[1] = data[i5 + 1];
                        bArr = data;
                        j = 4666723172467343360L;
                        str = a((d.B(bArr2) + j2) / 10000.0d, "%.4f");
                    } else {
                        bArr = data;
                        j = 4666723172467343360L;
                        str = str3;
                    }
                    sb2.append(str);
                    sb2.append("   CANFD  ");
                    sb2.append(channelbitoffset + 1);
                    sb2.append(" Rx   ");
                    sb2.append(substring);
                    sb2.append("    1 1 ");
                    sb2.append(cx);
                    sb2.append(a);
                    sb2.append(cy);
                    str2 = str;
                } else {
                    bArr = data;
                    i = dataLength;
                    j = 4666723172467343360L;
                }
                startpoint = startpoint + canDataFeature.getMinEachCanLength() + i3;
                dataLength = i;
                data = bArr;
                r5 = 0;
                r10 = 1;
            }
            if (sb2.length() > 0) {
                canDataNode.setParsingString(sb2.toString());
                return true;
            }
            c.g("CanDataCollectService", "parse String is null");
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cw(int i) {
        if (CanCollectModel.fo().fp() == 10002) {
            if (MyApplication.eZ()) {
                c.f("CanDataCollectService", "checkCanFileSize:" + this.mFile.length());
            }
            if (Long.valueOf(this.mFile.length() + i).longValue() > CanCollectModel.rp.longValue()) {
                if (CanCollectModel.fo().fp() == 10002) {
                    cn(getString(R.string.tail_candata_file));
                }
                kL();
                if (Long.valueOf(g.cA(this.AH)).longValue() < CanCollectModel.rq.longValue()) {
                    c.f("CanDataCollectService", "Udisk size not enough, need to remove old canFile");
                    File[] a = g.a(g.K(this.AH, ".asc"));
                    if (a != null) {
                        for (File file : a) {
                            file.delete();
                            if (Long.valueOf(g.cA(this.AH)).longValue() >= CanCollectModel.rq.longValue()) {
                                break;
                            }
                        }
                    }
                }
                kJ();
            }
        }
    }

    private String cx(int i) {
        StringBuilder sb = new StringBuilder();
        if (i == 12) {
            sb.append("9");
        } else if (i == 16) {
            sb.append("a");
        } else if (i == 20) {
            sb.append("b");
        } else if (i == 24) {
            sb.append("c");
        } else if (i == 32) {
            sb.append("d");
        } else if (i == 48) {
            sb.append("e");
        } else if (i == 64) {
            sb.append("f");
        } else {
            sb.append(i);
        }
        sb.append(" ");
        if (i / 10 >= 1) {
            sb.append(i);
            sb.append(" ");
        } else {
            sb.append(" ");
            sb.append(i);
            sb.append(" ");
        }
        return sb.toString();
    }

    private String cy(int i) {
        if (i == CanCollectModel.CanData.ADCAN.ordinal() || i == CanCollectModel.CanData.ICAN.ordinal()) {
            return "   213015  355   327000 80162294 50140850 50050850 2007030e 20070002\r\n";
        }
        return "   213015  355   220040     34a8 50140850 50050850 2007030e 20070002\r\n";
    }

    private String a(double d, String str) {
        StringBuilder sb = new StringBuilder(String.format(str, Double.valueOf(d)));
        if (d / 1000.0d < 1.0d) {
            if (d / 100.0d >= 1.0d) {
                sb.insert(0, " ");
            } else if (d / 10.0d >= 1.0d) {
                sb.insert(0, " ");
                sb.insert(0, " ");
            } else {
                sb.insert(0, " ");
                sb.insert(0, " ");
                sb.insert(0, " ");
            }
        }
        return sb.toString();
    }

    private boolean a(byte[] bArr, int i, CanDataFeature canDataFeature) {
        boolean z = false;
        if (i < canDataFeature.getMinLength() || (canDataFeature.isWithValidbit() && bArr[0] == 0)) {
            c.i("CanDataCollectService", "checkCandataNormal dataLength: " + i + ", feature.isWithValidbit(): " + canDataFeature.isWithValidbit() + ", data[0]: " + ((int) bArr[0]));
        } else {
            int startpoint = canDataFeature.getStartpoint();
            while (true) {
                if (startpoint >= i) {
                    break;
                }
                int channelbitoffset = bArr[startpoint] >> canDataFeature.getChannelbitoffset();
                int i2 = bArr[startpoint + 2];
                if (canDataFeature.isDLC()) {
                    i2 = d.cC(i2);
                }
                if (channelbitoffset >= CanCollectModel.CanData.values().length) {
                    c.i("CanDataCollectService", "checkCandataNormal canChannel:" + channelbitoffset);
                    break;
                } else if ((i2 < 0 || i2 > 8) && i2 != 12 && i2 != 16 && i2 != 20 && i2 != 24 && i2 != 32 && i2 != 48 && i2 != 64) {
                    c.i("CanDataCollectService", "checkCandataNormal canDataLength:" + i2);
                    break;
                } else {
                    startpoint += canDataFeature.getMinEachCanLength() + i2;
                }
            }
            if (startpoint == i) {
                z = true;
            } else if (startpoint > i) {
                c.i("CanDataCollectService", "checkCandataNormal i > dataLength i:" + startpoint + ", dataLength:" + i);
            }
        }
        if (MyApplication.eZ()) {
            c.f("CanDataCollectService", "checkCandataNormal res = " + z);
        }
        return z;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        c.f("CanDataCollectService", "onCreate");
        this.Az = new LinkedBlockingQueue<>();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addDataScheme("file");
        registerReceiver(this.AS, intentFilter);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        c.f("CanDataCollectService", "onStartCommand");
        Q(true);
        this.AH = intent.getExtras().getString("storagePath");
        this.AD = Executors.newFixedThreadPool(10);
        this.AE = Executors.newSingleThreadExecutor();
        this.AA = new b("192.168.225.1", 3002);
        if (CanCollectModel.fo().fp() == 10001) {
            this.AB = new b(this.AH, 10402);
        }
        this.rt = true;
        this.AE.submit(this.AT);
        j.execute(this.AU);
        return 2;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        c.f("CanDataCollectService", "onBind");
        return null;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        c.f("CanDataCollectService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        c.f("CanDataCollectService", "onDestroy mIsWriteUdiskError : " + this.AR);
        super.onDestroy();
        unregisterReceiver(this.AS);
        this.rt = false;
        this.AI = -1;
        this.AU.interrupt();
        j.b(0, new Runnable() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$CanDataCollectService$QuAv3T-KcmHmDbaogmPGsG6NMzA
            @Override // java.lang.Runnable
            public final void run() {
                CanDataCollectService.this.kP();
            }
        }, 2000L);
        if (this.AR || CanCollectModel.fo().fs()) {
            CanCollectModel.fo().D(false);
            this.Az.clear();
            if (this.AD != null) {
                this.AD.shutdownNow();
            }
            if (this.AE != null) {
                this.AE.shutdownNow();
            }
            Q(false);
            return;
        }
        j.b(0, new Runnable() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$CanDataCollectService$sV59SG8kg_qW31E4JUR90UyR1zU
            @Override // java.lang.Runnable
            public final void run() {
                CanDataCollectService.this.kN();
            }
        }, 10000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void kP() {
        this.AA.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void kN() {
        c.f("CanDataCollectService", "shuting down ExcutorService");
        this.AD.execute(new Runnable() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$CanDataCollectService$dExF2425wZ21sR0HfAZ_AFrmPU0
            @Override // java.lang.Runnable
            public final void run() {
                CanDataCollectService.this.kO();
            }
        });
        if (this.AD != null) {
            this.AD.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void kO() {
        if (this.AE != null) {
            this.AE.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void kJ() {
        if (CanCollectModel.fo().fp() == 10002) {
            this.mPath = this.AH + "can-canfd-" + t.cL("yyyy-MM-dd HH.mm.ss") + ".asc";
            this.AF = t.lE();
            this.AG = t.cM("EEE MMM dd HH:mm:ss.SSS aa yyyy");
            c.f("CanDataCollectService", "mTimestamp:" + this.AF + ", mTime:" + this.AG);
            kK();
            cn(getString(R.string.header_candata_file, new Object[]{this.AG, this.AG}));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q(boolean z) {
        c.f("CanDataCollectService", "changeCollectStatus : " + z);
        CanCollectModel.fo().C(z);
        if (!z && CanCollectModel.fo().fp() == 10001 && this.AB != null) {
            this.AB.disconnect();
        }
        EventBus.getDefault().post(4);
    }

    private void kK() {
        this.mFile = new File(this.mPath);
        c.i("CanDataCollectService", "createFileWriter [%s]", this.mPath);
        this.AC = g.f(this.mPath, true);
        if (this.AC == null) {
            c.i("CanDataCollectService", "get FileWriter fail");
            CanCollectModel.fo().setErrorMsg(getString(R.string.candata_errormsg, new Object[]{t.cL("yyyy-MM-dd HH:mm:ss"), getString(R.string.tips_get_filewriter_fail)}));
            kM();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void kL() {
        c.g("CanDataCollectService", "destroyFileWriter");
        g.a(this.AC);
        this.AC = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void kM() {
        c.f("CanDataCollectService", "finishService");
        kL();
        this.AR = true;
        stopSelf();
    }
}
