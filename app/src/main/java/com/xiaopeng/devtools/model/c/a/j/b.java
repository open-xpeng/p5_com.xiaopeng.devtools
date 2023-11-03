package com.xiaopeng.devtools.model.c.a.j;

import android.content.Context;
import android.net.wifi.WifiInfo;
import com.xiaopeng.devtools.MyApplication;
import com.xpeng.test.WifiTest;

/* compiled from: WlanModel.java */
/* loaded from: classes12.dex */
public class b implements a {
    private Context mContext = MyApplication.getContext();
    private WifiTest tF = new WifiTest(this.mContext);

    @Override // com.xiaopeng.devtools.model.c.a.j.a
    public void onInit() {
        this.tF.onInit();
    }

    @Override // com.xiaopeng.devtools.model.c.a.j.a
    public void closeWifi() {
        this.tF.closeWifi();
    }

    @Override // com.xiaopeng.devtools.model.c.a.j.a
    public String getWlanLocalAddr() {
        return this.tF.getWlanLocalAddr();
    }

    @Override // com.xiaopeng.devtools.model.c.a.j.a
    public WifiInfo getWifiInfo() {
        return this.tF.getWifiInfo();
    }
}
