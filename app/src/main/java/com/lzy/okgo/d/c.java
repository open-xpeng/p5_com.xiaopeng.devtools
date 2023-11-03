package com.lzy.okgo.d;

/* compiled from: ColumnEntity.java */
/* loaded from: classes11.dex */
public class c {
    public String lR;
    public String lS;
    public String[] lT;
    public boolean lU;
    public boolean lV;
    public boolean lW;

    public c(String... strArr) {
        this.lT = strArr;
    }

    public c(String str, String str2) {
        this(str, str2, false, false, false);
    }

    public c(String str, String str2, boolean z, boolean z2) {
        this(str, str2, z, z2, false);
    }

    public c(String str, String str2, boolean z, boolean z2, boolean z3) {
        this.lR = str;
        this.lS = str2;
        this.lU = z;
        this.lV = z2;
        this.lW = z3;
    }
}
