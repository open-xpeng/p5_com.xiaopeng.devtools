package com.xiaopeng.commonfunc.b.c.a;

import android.content.Context;
import com.xiaopeng.lib.utils.c;

/* compiled from: BluetoothModel.java */
/* loaded from: classes11.dex */
public class a {
    private final com.xiaopeng.commonfunc.b.g.a nW;

    public a(Context context, com.xiaopeng.commonfunc.b.g.a.a aVar) {
        this.nW = new com.xiaopeng.commonfunc.b.g.a(context);
        this.nW.a(aVar);
        this.nW.registerReceiver();
    }

    public void dL() {
        c.g("BluetoothModel", "powerOnBluetooth");
        this.nW.enable();
    }

    public void dM() {
        c.g("BluetoothModel", "powerOffBluetooth");
        this.nW.disable();
    }

    public int dN() {
        int state = this.nW.getState();
        c.g("BluetoothModel", "getBtPowerStatus = " + state);
        return state;
    }

    public void onDestroy() {
        this.nW.unregisterReceiver();
        this.nW.releaseCallback();
        this.nW.closeProfileProxy();
    }
}
