package com.xiaopeng.a;

import android.util.Log;

/* compiled from: XmlUtil.java */
/* loaded from: classes11.dex */
public class c {
    public static String TAG = "XmlData";

    public static void t(String str, String str2, String str3) {
        Log.d(TAG, d(str, str2, str3, 1));
    }

    public static void u(String str, String str2, String str3) {
        Log.e(TAG, d(str, str2, str3, 4));
    }

    public static void v(String str, String str2, String str3) {
        Log.i(TAG, d(str, str2, str3, 2));
    }

    private static String d(String str, String str2, String str3, int i) {
        str = (str == null || str.equals("")) ? " " : " ";
        str2 = (str2 == null || str2.equals("")) ? " " : " ";
        str3 = (str3 == null || str3.equals("")) ? " " : " ";
        return "[" + str + "$" + str2 + "]" + str3;
    }

    public static void d(Exception exc) {
        StackTraceElement[] stackTrace = exc.getStackTrace();
        String str = TAG;
        Log.w(str, "WARNNING: " + exc.toString());
        for (int i = 0; i < stackTrace.length; i++) {
            String str2 = TAG;
            Log.w(str2, "WARNNING:     " + stackTrace[i]);
        }
    }
}
