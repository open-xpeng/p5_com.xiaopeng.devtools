package com.amap.api.services.a;

/* compiled from: DexDownloadItem.java */
/* loaded from: classes11.dex */
public class al {
    String a;
    String b;
    String c;
    String d;
    String e;
    int f;
    int g;
    private String h;
    private String i;

    public al(String str, String str2, String str3) {
        this.h = str;
        this.i = str2;
        try {
            String[] split = str.split("/");
            int length = split.length;
            if (length <= 1) {
                return;
            }
            this.a = split[length - 1];
            String[] split2 = this.a.split("_");
            this.b = split2[0];
            this.c = split2[2];
            this.d = split2[1];
            this.f = Integer.parseInt(split2[3]);
            this.g = Integer.parseInt(split2[4].split("\\.")[0]);
        } catch (Throwable th) {
            aw.a(th, "DexDownloadItem", "DexDownloadItem");
        }
    }

    public String a() {
        return this.h;
    }

    public String b() {
        return this.i;
    }

    public String c() {
        return this.c;
    }
}
