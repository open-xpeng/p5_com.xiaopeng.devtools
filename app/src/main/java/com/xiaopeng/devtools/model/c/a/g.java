package com.xiaopeng.devtools.model.c.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.utils.m;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.utils.j;
import java.io.File;
import java.lang.ref.WeakReference;

/* compiled from: MobileNetworkModel.java */
/* loaded from: classes12.dex */
public class g implements e {
    private Context mContext;
    private TelephonyManager mTelephonyManager;
    private b sA;
    private a sB;
    private int sC;
    private ConnectivityManager sw;
    private WifiManager sx;
    private boolean sy;
    private WeakReference<Handler> sz;

    public g(Handler handler) {
        this.sC = 0;
        this.sz = new WeakReference<>(handler);
        this.mContext = MyApplication.getContext();
        this.sw = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        this.sx = (WifiManager) this.mContext.getSystemService("wifi");
    }

    public g() {
        this.sC = 0;
        this.mContext = MyApplication.getContext();
        this.sw = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        this.sx = (WifiManager) this.mContext.getSystemService("wifi");
        this.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
    }

    @Override // com.xiaopeng.devtools.model.c.a.e
    public void onInit() {
        this.sy = this.sx.isWifiEnabled();
        this.sx.setWifiEnabled(false);
        if (this.sz == null && this.sA == null) {
            this.sA = new b();
            this.sA.start();
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.e
    public void fT() {
        this.sx.setWifiEnabled(this.sy);
        if (this.sA != null) {
            this.sA.kill();
        }
    }

    /* compiled from: MobileNetworkModel.java */
    /* loaded from: classes12.dex */
    private final class b extends Thread {
        private b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            g.this.sB = new a();
            g.this.mTelephonyManager.listen(g.this.sB, 256);
            Looper.loop();
        }

        public void kill() {
            g.this.sA.interrupt();
            if (g.this.sB != null && g.this.mTelephonyManager != null) {
                g.this.mTelephonyManager.listen(g.this.sB, 0);
                g.this.sB = null;
            }
            g.this.sA = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MobileNetworkModel.java */
    /* loaded from: classes12.dex */
    public final class a extends PhoneStateListener {
        private a() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            String[] split = signalStrength.toString().split(" ");
            g.this.sC = Integer.parseInt(split[9]);
            com.xiaopeng.lib.utils.c.f("MobileNetworkModel", "onSignalStrengthsChanged lteSignalStrength = " + g.this.sC);
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.e
    public void fU() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.model.c.a.-$$Lambda$g$kWvddmHx-pBzCMFIOAK2jmbtIXk
            @Override // java.lang.Runnable
            public final void run() {
                g.this.fX();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void fX() {
        com.xiaopeng.lib.utils.c.f("MobileNetworkModel", "getConnectInfo");
        ((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).get("http://www.baidu.com").execute(new Callback() { // from class: com.xiaopeng.devtools.model.c.a.g.1
            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onSuccess(IResponse iResponse) {
                if (iResponse.code() == 200) {
                    g.this.bw(iResponse.body());
                } else {
                    g.this.bw("");
                }
            }

            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onFailure(IResponse iResponse) {
                g.this.bw("");
            }
        });
    }

    @Override // com.xiaopeng.devtools.model.c.a.e
    public void p(String str, int i) {
        j.execute(new com.xiaopeng.devtools.system.b.b(str, i));
    }

    @Override // com.xiaopeng.devtools.model.c.a.e
    public void bu(String str) {
        m.R("ping", str);
    }

    @Override // com.xiaopeng.devtools.model.c.a.e
    public boolean bv(String str) {
        return m.Q("ping", str) != null;
    }

    @Override // com.xiaopeng.devtools.model.c.a.e
    public boolean fV() {
        if (new File("/dev/ttyUSB0").exists()) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bw(String str) {
        if (this.sz.get() != null) {
            Message obtainMessage = this.sz.get().obtainMessage();
            obtainMessage.what = 200001;
            obtainMessage.obj = str;
            this.sz.get().sendMessage(obtainMessage);
        }
    }
}
