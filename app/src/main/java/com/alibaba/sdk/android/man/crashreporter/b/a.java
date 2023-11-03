package com.alibaba.sdk.android.man.crashreporter.b;

import android.util.Log;

/* loaded from: classes11.dex */
public class a {
    public static final String TAG = "MotuCrashReporter";

    public static void e(String str) {
        b(TAG, str);
    }

    public static void b(String str, String str2) {
        Log.d(str, str2);
    }

    public static void a(String str, Throwable th) {
        a(TAG, str, th);
    }

    public static void a(String str, String str2, Throwable th) {
        Log.d(str, str2, th);
    }

    public static void f(String str) {
        c(TAG, str);
    }

    public static void c(String str, String str2) {
        Log.i(str, str2);
    }

    public static void b(String str, Throwable th) {
        b(TAG, str, th);
    }

    public static void b(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    public static void g(String str) {
        d(TAG, str);
    }

    public static void d(String str, String str2) {
        Log.w(str, str2);
    }

    public static void c(String str, Throwable th) {
        c(TAG, str, th);
    }

    public static void c(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    public static void h(String str) {
        e(TAG, str);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }

    public static void d(String str, Throwable th) {
        d(TAG, str, th);
    }

    public static void d(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }
}
