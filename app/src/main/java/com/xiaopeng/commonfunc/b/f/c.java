package com.xiaopeng.commonfunc.b.f;

import android.content.Context;
import android.pso.PsoCrypto;
import android.pso.XpPso;
import com.xiaopeng.a.a;

/* compiled from: PsoModel.java */
/* loaded from: classes11.dex */
public class c {
    private final PsoCrypto ov;

    public c(Context context) {
        this.ov = new XpPso(context);
        this.ov.set_carType(a.b.getInt("PSO_CAR_TYPE"));
    }

    public boolean aT(String str) {
        boolean z;
        com.xiaopeng.lib.utils.c.g("PsoModel", "verifyCertKey");
        try {
            z = this.ov.CertKeyVerify(str);
        } catch (Exception e) {
            com.xiaopeng.lib.utils.c.i("PsoModel", "CertKeyVerify Failed...Descrition:" + e);
            z = false;
        }
        com.xiaopeng.lib.utils.c.i("PsoModel", "verifyCertKey:" + z);
        return z;
    }
}
