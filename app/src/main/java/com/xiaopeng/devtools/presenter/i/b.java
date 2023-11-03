package com.xiaopeng.devtools.presenter.i;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.text.format.Formatter;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.system.b.c;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.lib.utils.j;
import com.xpeng.test.WifiTest;

/* compiled from: UserSettingsPresenter.java */
/* loaded from: classes12.dex */
public class b implements a {
    private com.xiaopeng.devtools.view.usersettings.a Aj;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.presenter.i.b.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NetworkInfo networkInfo;
            if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction()) && (networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo")) != null) {
                if (NetworkInfo.State.CONNECTED.equals(networkInfo.getState())) {
                    WifiInfo wifiInfo = b.this.Ak.getWifiInfo();
                    if (wifiInfo == null) {
                        if (b.this.Aj != null) {
                            b.this.Aj.dy("UNKNOWN");
                        }
                    } else if (b.this.Aj != null) {
                        b.this.Aj.dy(Formatter.formatIpAddress(wifiInfo.getIpAddress()));
                    }
                } else if (b.this.Aj != null) {
                    b.this.Aj.dy("UNKNOWN");
                }
            }
        }
    };
    private com.xiaopeng.devtools.model.i.a Ai = com.xiaopeng.devtools.model.i.b.hN();
    private WifiTest Ak = new WifiTest(MyApplication.getContext());

    public b() {
    }

    public b(com.xiaopeng.devtools.view.usersettings.a aVar) {
        this.Aj = aVar;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        MyApplication.getContext().registerReceiver(this.mReceiver, intentFilter);
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public boolean hL() {
        return this.Ai.hL();
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public void F(final boolean z) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.i.b.2
            @Override // java.lang.Runnable
            public void run() {
                b.this.Ai.F(z);
                if (b.this.Aj != null) {
                    b.this.Aj.oa();
                }
            }
        });
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public boolean hM() {
        return this.Ai.hM();
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public void setDebugStatus(final boolean z) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.i.-$$Lambda$b$WU4gi-Myjy2mcChocjeyK2iLYC4
            @Override // java.lang.Runnable
            public final void run() {
                b.this.P(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(boolean z) {
        this.Ai.setDebugStatus(z);
        c.sleep(5000L);
        if (this.Aj != null) {
            this.Aj.oa();
        }
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public void N(final boolean z) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.i.-$$Lambda$b$jmuwrEpYzbmBNFt7-FjvHhubGzI
            @Override // java.lang.Runnable
            public final void run() {
                b.this.O(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(boolean z) {
        r.cE(z ? 5050 : -1);
        r.cK("adbd");
        c.sleep(1000L);
        r.cI("adbd");
        if (this.Aj != null) {
            this.Aj.oa();
        }
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public boolean kG() {
        return r.lk() == 5050;
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public String kH() {
        return Formatter.formatIpAddress(this.Ak.getIPAddress());
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public void G(boolean z) {
        this.Ai.G(z);
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public void H(boolean z) {
        this.Ai.H(z);
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public void bH(String str) {
        this.Ai.bH(str);
    }

    @Override // com.xiaopeng.devtools.presenter.i.a
    public void onDestroy() {
        MyApplication.getContext().unregisterReceiver(this.mReceiver);
    }
}
