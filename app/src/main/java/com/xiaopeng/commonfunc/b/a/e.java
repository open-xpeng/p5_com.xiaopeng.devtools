package com.xiaopeng.commonfunc.b.a;

import android.car.hardware.tbox.CarTboxManager;

/* compiled from: TboxModel.java */
/* loaded from: classes11.dex */
public class e extends b<CarTboxManager> {
    public e(String str) {
        super(str);
    }

    @Override // com.xiaopeng.commonfunc.b.a.b
    protected String getServiceName() {
        return "xp_tbox";
    }

    public void setRoutingForTbox(int i) {
        String str = this.TAG;
        com.xiaopeng.lib.utils.c.f(str, "setRoutingForTbox cmd:" + i);
        try {
            CarTboxManager dE = dE();
            if (dE != null) {
                dE.setRoutingForTbox(i);
            } else {
                com.xiaopeng.lib.utils.c.i(this.TAG, "setRoutingForTbox carTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTboxModemCapture(int i) {
        String str = this.TAG;
        com.xiaopeng.lib.utils.c.f(str, "setTboxModemCapture cmd:" + i);
        try {
            CarTboxManager dE = dE();
            if (dE != null) {
                dE.setTboxModemCapture(i);
            } else {
                com.xiaopeng.lib.utils.c.i(this.TAG, "setTboxModemCapture carTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCopyTboxLogRequest(int i) {
        String str = this.TAG;
        com.xiaopeng.lib.utils.c.f(str, "sendCopyTboxLogRequest type:" + i);
        try {
            CarTboxManager dE = dE();
            if (dE != null) {
                dE.sendCopyTboxLogRequest(i);
            } else {
                com.xiaopeng.lib.utils.c.i(this.TAG, "sendCopyTboxLogRequest carTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
