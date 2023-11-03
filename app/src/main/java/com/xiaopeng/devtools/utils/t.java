package com.xiaopeng.devtools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: TimeUtil.java */
/* loaded from: classes12.dex */
public class t {
    public static String eI() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static String cL(String str) {
        return new SimpleDateFormat(str).format(new Date());
    }

    public static String cM(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(new Date());
    }

    public static double lE() {
        return (System.nanoTime() / 1000) / 1000000.0d;
    }
}
