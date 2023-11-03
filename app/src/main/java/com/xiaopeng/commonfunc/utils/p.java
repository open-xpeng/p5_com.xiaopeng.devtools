package com.xiaopeng.commonfunc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: TimeUtil.java */
/* loaded from: classes11.dex */
public class p {
    public static String eI() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static String a(long j, String str) {
        return new SimpleDateFormat((str == null || str.isEmpty()) ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd HH:mm:ss").format(new Date(j * 1000));
    }

    public static long z(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
