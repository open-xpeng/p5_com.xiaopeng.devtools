package com.xiaopeng.devtools.system.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiClient;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.a.b;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.lib.utils.c;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class PcCommandService extends Service {
    private a Bm;
    private com.xiaopeng.devtools.a.a Bn;
    private boolean Bo = false;
    private ConnectivityManager.OnStartTetheringCallback Bp = new ConnectivityManager.OnStartTetheringCallback() { // from class: com.xiaopeng.devtools.system.service.PcCommandService.1
        public void onTetheringStarted() {
            super.onTetheringStarted();
            c.g("PcCommandService", "onTetheringStarted");
            if (!PcCommandService.this.Bo && PcCommandService.this.Bm == null) {
                PcCommandService.this.Bm = new a(MyApplication.getContext());
                PcCommandService.this.Bm.start();
            }
        }

        public void onTetheringFailed() {
            super.onTetheringFailed();
            c.g("PcCommandService", "onTetheringStarted");
            PcCommandService.this.stopSelf();
        }
    };
    private WifiManager.SoftApCallback Bq = new WifiManager.SoftApCallback() { // from class: com.xiaopeng.devtools.system.service.PcCommandService.2
        public void onStateChanged(int i, int i2) {
            c.g("PcCommandService", "onStateChanged state : " + i + ", failureReason : " + i2);
        }

        public void onNumClientsChanged(int i) {
        }

        public void onClientsUpdated(List<WifiClient> list) {
            c.f("PcCommandService", "onClientsUpdated");
            if (PcCommandService.this.Bm == null || PcCommandService.this.ve == null || TextUtils.isEmpty(PcCommandService.this.ve.hT())) {
                return;
            }
            boolean z = true;
            Iterator<WifiClient> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WifiClient next = it.next();
                c.g("PcCommandService", "client : " + next.mIpAddr);
                if (next.mIpAddr.equalsIgnoreCase(PcCommandService.this.ve.hT())) {
                    z = false;
                    break;
                }
            }
            if (!z) {
                return;
            }
            PcCommandService.this.Bm.kU();
        }
    };
    private WifiManager sx;
    private b ve;

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.sx = (WifiManager) getSystemService("wifi");
        this.sx.registerSoftApCallback(this.Bq, null);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String eH;
        String str;
        c.f("PcCommandService", "onStartCommand");
        if (TextUtils.isEmpty(r.eH())) {
            str = "XPENGTOOLS";
        } else {
            str = "XPENGTOOLS" + eH.substring(eH.length() - 6);
        }
        E(str, "td$9knu!rg3d");
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        c.g("PcCommandService", "PcCommandService destroy");
        this.Bo = true;
        this.sx.unregisterSoftApCallback(this.Bq);
        if (this.ve != null) {
            this.ve.hQ();
        }
        if (this.Bm != null) {
            this.Bm.kill();
        }
        EventBus.getDefault().post(6);
        kT();
    }

    /* loaded from: classes12.dex */
    private class a extends Thread {
        ServerSocket Bs;
        boolean Bt = false;
        boolean Bu = true;
        private BufferedReader Bv;
        private final Context mContext;
        Socket uY;

        public a(Context context) {
            this.mContext = context;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (PcCommandService.this.Bo) {
                c.g("PcCommandService", "SERVICE IS STOP");
                return;
            }
            c.f("PcCommandService", "ConnectionThread start");
            try {
                this.Bs = new ServerSocket();
                this.Bs.setReuseAddress(true);
                this.Bs.bind(new InetSocketAddress(10400));
            } catch (IOException e) {
                e.printStackTrace();
            }
            int i = 0;
            while (this.Bu) {
                try {
                    if (PcCommandService.this.ve == null) {
                        PcCommandService.this.ve = new b();
                    }
                    PcCommandService.this.ve.hR();
                    PcCommandService.this.ve.bJ("");
                    EventBus.getDefault().post(5);
                    c.f("PcCommandService", "listening to accept");
                    this.uY = this.Bs.accept();
                    this.Bt = true;
                } catch (IOException e2) {
                    this.Bt = false;
                    e2.printStackTrace();
                }
                if (this.uY == null) {
                    c.f("PcCommandService", "connection accept fail");
                    this.Bt = false;
                }
                if (this.Bt) {
                    c.f("PcCommandService", "connect to PC Tools socket");
                    if (PcCommandService.this.ve != null) {
                        PcCommandService.this.ve.a(this.uY);
                    } else {
                        PcCommandService.this.ve = new b(this.uY);
                    }
                    if (PcCommandService.this.Bn == null) {
                        PcCommandService.this.Bn = new com.xiaopeng.devtools.a.a();
                    }
                    PcCommandService.this.Bn.a(this.mContext, PcCommandService.this.ve);
                    PcCommandService.this.ve.write("XPENG TOOLS CONNECTION COMPLETED\r\n");
                    try {
                        this.Bv = new BufferedReader(new InputStreamReader(this.uY.getInputStream()));
                    } catch (IOException e3) {
                        kU();
                        e3.printStackTrace();
                    }
                    while (this.Bt) {
                        try {
                            String readLine = this.Bv.readLine();
                            if (readLine != null) {
                                c.f("PcCommandService", "incoming = " + readLine);
                                PcCommandService.this.Bn.a(readLine, PcCommandService.this.ve);
                            } else {
                                i++;
                                c.f("PcCommandService", "receive garbage value");
                                if (i > 10) {
                                    c.i("PcCommandService", "Stop PC Tools socket (Garbage data count=" + i + ")");
                                    try {
                                        kU();
                                        i = 0;
                                    } catch (IOException e4) {
                                        e = e4;
                                        i = 0;
                                        kU();
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } catch (IOException e5) {
                            e = e5;
                        }
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void kU() {
            c.g("PcCommandService", "releaseConnection");
            this.Bt = false;
            if (this.uY != null) {
                try {
                    this.uY.shutdownInput();
                    this.uY.shutdownOutput();
                    this.uY.close();
                    this.uY = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (this.Bv != null) {
                    this.Bv.close();
                    this.Bv = null;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (PcCommandService.this.ve != null) {
                PcCommandService.this.ve.close();
            }
            if (PcCommandService.this.Bn != null) {
                PcCommandService.this.Bn.hP();
            }
        }

        public void kill() {
            this.Bu = false;
            PcCommandService.this.Bm.interrupt();
            kU();
            if (this.Bs != null) {
                try {
                    this.Bs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            PcCommandService.this.Bm = null;
            c.f("PcCommandService", "Kill ConnectionThread done");
        }
    }

    private void E(String str, String str2) {
        c.f("PcCommandService", "startTether...");
        WifiConfiguration wifiApConfiguration = this.sx.getWifiApConfiguration();
        wifiApConfiguration.SSID = str;
        wifiApConfiguration.preSharedKey = str2;
        boolean wifiApConfiguration2 = this.sx.setWifiApConfiguration(wifiApConfiguration);
        StringBuilder sb = new StringBuilder();
        sb.append("startTether() setWifiApConfiguration...");
        sb.append(wifiApConfiguration2 ? EcuUpdateResult.RESULT_SUCCESS : "failure");
        c.g("PcCommandService", sb.toString());
        if (wifiApConfiguration2) {
            ((ConnectivityManager) MyApplication.getContext().getSystemService("connectivity")).startTethering(0, true, this.Bp, new Handler(Looper.getMainLooper()));
        }
    }

    private void kT() {
        ((ConnectivityManager) MyApplication.getContext().getSystemService("connectivity")).stopTethering(0);
    }
}
