package com.alibaba.mtl.appmonitor.f;

/* compiled from: StringUtils.java */
/* loaded from: classes11.dex */
public class b {
    public static boolean c(String str) {
        return !d(str);
    }

    public static boolean d(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
