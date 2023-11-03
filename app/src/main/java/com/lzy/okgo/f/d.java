package com.lzy.okgo.f;

import android.util.Log;

/* compiled from: OkLogger.java */
/* loaded from: classes11.dex */
public class d {
    private static boolean mK = true;
    private static String tag = "OkGo";

    public static void v(String str, String str2) {
        if (mK) {
            Log.v(str, str2);
        }
    }

    public static void f(Throwable th) {
        if (!mK || th == null) {
            return;
        }
        th.printStackTrace();
    }
}
