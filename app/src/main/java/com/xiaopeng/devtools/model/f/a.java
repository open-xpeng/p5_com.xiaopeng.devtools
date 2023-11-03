package com.xiaopeng.devtools.model.f;

import java.text.DecimalFormat;

/* compiled from: FmPerformanceModel.java */
/* loaded from: classes12.dex */
public class a {
    private static volatile a tU;
    private String tW = "N/A";
    private String mFmState = "N/A";
    private String mFmStrength = "N/A";
    private DecimalFormat tV = new DecimalFormat("0.0");

    private a() {
    }

    public static a hg() {
        if (tU == null) {
            synchronized (a.class) {
                if (tU == null) {
                    tU = new a();
                }
            }
        }
        return tU;
    }

    public String getFrequency() {
        return this.tW;
    }

    public String hh() {
        return this.mFmState;
    }

    public String hi() {
        return this.mFmStrength;
    }
}
