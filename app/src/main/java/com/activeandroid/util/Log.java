package com.activeandroid.util;

/* loaded from: classes11.dex */
public final class Log {
    private static String sTag = "ActiveAndroid";
    private static boolean sEnabled = false;

    private Log() {
    }

    public static boolean isEnabled() {
        return sEnabled;
    }

    public static void setEnabled(boolean z) {
        sEnabled = z;
    }

    public static boolean isLoggingEnabled() {
        return sEnabled;
    }

    public static int v(String str) {
        if (sEnabled) {
            return android.util.Log.v(sTag, str);
        }
        return 0;
    }

    public static int v(String str, String str2) {
        if (sEnabled) {
            return android.util.Log.v(str, str2);
        }
        return 0;
    }

    public static int v(String str, Throwable th) {
        if (sEnabled) {
            return android.util.Log.v(sTag, str, th);
        }
        return 0;
    }

    public static int v(String str, String str2, Throwable th) {
        if (sEnabled) {
            return android.util.Log.v(str, str2, th);
        }
        return 0;
    }

    public static int d(String str) {
        if (sEnabled) {
            return android.util.Log.d(sTag, str);
        }
        return 0;
    }

    public static int d(String str, String str2) {
        if (sEnabled) {
            return android.util.Log.d(str, str2);
        }
        return 0;
    }

    public static int d(String str, Throwable th) {
        if (sEnabled) {
            return android.util.Log.d(sTag, str, th);
        }
        return 0;
    }

    public static int d(String str, String str2, Throwable th) {
        if (sEnabled) {
            return android.util.Log.d(str, str2, th);
        }
        return 0;
    }

    public static int i(String str) {
        if (sEnabled) {
            return android.util.Log.i(sTag, str);
        }
        return 0;
    }

    public static int i(String str, String str2) {
        if (sEnabled) {
            return android.util.Log.i(str, str2);
        }
        return 0;
    }

    public static int i(String str, Throwable th) {
        if (sEnabled) {
            return android.util.Log.i(sTag, str, th);
        }
        return 0;
    }

    public static int i(String str, String str2, Throwable th) {
        if (sEnabled) {
            return android.util.Log.i(str, str2, th);
        }
        return 0;
    }

    public static int w(String str) {
        if (sEnabled) {
            return android.util.Log.w(sTag, str);
        }
        return 0;
    }

    public static int w(String str, String str2) {
        if (sEnabled) {
            return android.util.Log.w(str, str2);
        }
        return 0;
    }

    public static int w(String str, Throwable th) {
        if (sEnabled) {
            return android.util.Log.w(sTag, str, th);
        }
        return 0;
    }

    public static int w(String str, String str2, Throwable th) {
        if (sEnabled) {
            return android.util.Log.w(str, str2, th);
        }
        return 0;
    }

    public static int e(String str) {
        if (sEnabled) {
            return android.util.Log.e(sTag, str);
        }
        return 0;
    }

    public static int e(String str, String str2) {
        if (sEnabled) {
            return android.util.Log.e(str, str2);
        }
        return 0;
    }

    public static int e(String str, Throwable th) {
        if (sEnabled) {
            return android.util.Log.e(sTag, str, th);
        }
        return 0;
    }

    public static int e(String str, String str2, Throwable th) {
        if (sEnabled) {
            return android.util.Log.e(str, str2, th);
        }
        return 0;
    }

    public static int t(String str, Object... objArr) {
        if (sEnabled) {
            return android.util.Log.v("test", String.format(str, objArr));
        }
        return 0;
    }
}
