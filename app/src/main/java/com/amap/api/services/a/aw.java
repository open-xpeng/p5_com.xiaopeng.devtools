package com.amap.api.services.a;

/* compiled from: Utils.java */
/* loaded from: classes11.dex */
public class aw {
    public static int q(String str, String str2) {
        try {
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            int min = Math.min(split.length, split2.length);
            int i = 0;
            for (int i2 = 0; i2 < min; i2++) {
                i = split[i2].length() - split2[i2].length();
                if (i != 0 || (i = split[i2].compareTo(split2[i2])) != 0) {
                    break;
                }
            }
            return i != 0 ? i : split.length - split2.length;
        } catch (Exception e) {
            o.a(e, "Utils", "compareVersion");
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Throwable th, String str, String str2) {
        o.a(th, str, str2);
    }
}
