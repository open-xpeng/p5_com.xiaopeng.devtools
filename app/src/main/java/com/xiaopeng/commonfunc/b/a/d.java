package com.xiaopeng.commonfunc.b.a;

import android.car.hardware.imu.CarImuManager;

/* compiled from: ImuModel.java */
/* loaded from: classes11.dex */
public class d extends b<CarImuManager> {
    public d(String str) {
        super(str);
    }

    @Override // com.xiaopeng.commonfunc.b.a.b
    protected String getServiceName() {
        return "xp_imu";
    }
}
