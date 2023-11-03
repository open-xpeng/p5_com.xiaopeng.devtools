package com.xiaopeng.devtools.model.c.a.i;

import com.xiaopeng.a.a;
import com.xiaopeng.devtools.utils.g;

/* compiled from: TemperatureModel.java */
/* loaded from: classes12.dex */
public class a {
    public int gP() {
        return g.cu(a.c.ec("CPU_INTERNAL_THERM"));
    }

    public int gQ() {
        return g.cu(a.c.ec("SOC_THERM"));
    }

    public int gR() {
        return g.cu(a.c.ec("UFS_THERM"));
    }

    public int gS() {
        return g.cu(a.c.ec("DDR_THERM"));
    }

    public int gT() {
        return g.cu(a.c.ec("AMP_THERM"));
    }

    public int gU() {
        return g.cu(a.c.ec("PMIC_THERM"));
    }
}
