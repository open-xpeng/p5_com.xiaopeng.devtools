package com.amap.api.services.core;

/* compiled from: ServiceSettings.java */
/* loaded from: classes11.dex */
public class a {
    private static a gI;
    private String a = "zh-CN";
    private int b = 1;
    private int d = 20000;
    private int e = 20000;

    public int bF() {
        return this.d;
    }

    public int bG() {
        return this.e;
    }

    private a() {
    }

    public static a bH() {
        if (gI == null) {
            gI = new a();
        }
        return gI;
    }

    public String getLanguage() {
        return this.a;
    }

    public int getProtocol() {
        return this.b;
    }
}
