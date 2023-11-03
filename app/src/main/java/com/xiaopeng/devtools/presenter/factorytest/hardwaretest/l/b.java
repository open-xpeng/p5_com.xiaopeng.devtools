package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.l;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.xiaopeng.lib.utils.c;

/* compiled from: WlanPresenter.java */
/* loaded from: classes12.dex */
public class b implements a {
    private WifiManager.ActionListener xx = new WifiManager.ActionListener() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.l.b.1
        public void onSuccess() {
            c.f("WlanPresenter", "mConnectListener onSuccess");
            com.xiaopeng.devtools.system.b.c.sleep(3000L);
        }

        public void onFailure(int i) {
            c.f("WlanPresenter", "mConnectListener onFailure reason = " + i);
        }
    };
    private WifiManager.ActionListener xy = new WifiManager.ActionListener() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.l.b.2
        public void onSuccess() {
            c.f("WlanPresenter", "mForgetListener onSuccess");
            b.this.closeWifi();
        }

        public void onFailure(int i) {
            c.f("WlanPresenter", "mForgetListener onFailure reason = " + i);
        }
    };
    private com.xiaopeng.devtools.model.c.a.j.a xw = new com.xiaopeng.devtools.model.c.a.j.b();

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.l.a
    public void onInit() {
        this.xw.onInit();
    }

    public void closeWifi() {
        this.xw.closeWifi();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.l.a
    public String getWlanLocalAddr() {
        return this.xw.getWlanLocalAddr();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.l.a
    public WifiInfo getWifiInfo() {
        return this.xw.getWifiInfo();
    }
}
