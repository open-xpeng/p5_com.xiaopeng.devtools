package com.alibaba.sdk.android.man.util;

import android.util.Log;

/* loaded from: classes11.dex */
public class MANLog {
    private static boolean isPrintLog = false;

    private MANLog() {
    }

    public static boolean isPrintLog() {
        return isPrintLog;
    }

    public static void enableLog() {
        isPrintLog = true;
    }

    public static void Logd(String str, String str2) {
        if (isPrintLog && str != null && str2 != null) {
            Log.d(str, str2);
        }
    }

    public static void Logd(String str, String str2, long j) {
        if (isPrintLog && str != null && str2 != null) {
            Log.i(str, Thread.currentThread().getId() + " - " + str2 + ((System.nanoTime() - j) / 1000000));
        }
    }

    public static void Loge(String str, String str2) {
        if (isPrintLog && str != null && str2 != null) {
            Log.e(str, str2);
        }
    }

    public static void Logi(String str, String str2) {
        if (isPrintLog && str != null && str2 != null) {
            Log.i(str, str2);
        }
    }

    public static void Logv(String str, String str2) {
        if (isPrintLog && str != null && str2 != null) {
            Log.v(str, str2);
        }
    }

    public static void Logw(String str, String str2) {
        if (isPrintLog && str != null && str2 != null) {
            Log.w(str, str2);
        }
    }

    public static void Logf(String str, String str2) {
        if (str != null && str2 != null) {
            Log.d(str, str2);
        }
    }
}
