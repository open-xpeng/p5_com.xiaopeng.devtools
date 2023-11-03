package com.xiaopeng.commonfunc.b.a;

import android.car.hardware.icm.CarIcmManager;

/* compiled from: IcmModel.java */
/* loaded from: classes11.dex */
public class c extends b<CarIcmManager> {
    public c(String str) {
        super(str);
    }

    @Override // com.xiaopeng.commonfunc.b.a.b
    protected String getServiceName() {
        return "xp_icm";
    }

    public void sendLogCompressRequest(String str) {
        String str2 = this.TAG;
        com.xiaopeng.lib.utils.c.f(str2, "sendLogCompressRequest :" + str);
        try {
            CarIcmManager dE = dE();
            if (dE != null) {
                dE.sendLogCompressRequest(str);
            } else {
                com.xiaopeng.lib.utils.c.i(this.TAG, "sendLogCompressRequest error:mCarIcmManager is null ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
