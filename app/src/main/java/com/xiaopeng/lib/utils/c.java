package com.xiaopeng.lib.utils;

import android.app.ActivityThread;
import android.util.Log;

/* compiled from: LogUtils.java */
/* loaded from: classes12.dex */
public class c {
    public static int Wl = 2;
    public static String Wm = getAppName();
    public static b Wn = new a();
    public static boolean Wo = true;
    public static boolean Wp = false;

    /* compiled from: LogUtils.java */
    /* loaded from: classes12.dex */
    public interface b {
        void a(int i, String str, String str2, String str3);
    }

    public static boolean oM() {
        return Wo;
    }

    public static boolean dQ(int i) {
        return Wl <= i && oM();
    }

    public static void e(Object obj, String str) {
        if (dQ(2)) {
            a(2, obj, str, (Throwable) null, Wp);
        }
    }

    public static void d(String str) {
        if (dQ(3)) {
            a(3, (Object) null, str, (Throwable) null, Wp);
        }
    }

    public static void f(Object obj, String str) {
        if (dQ(3)) {
            a(3, obj, str, (Throwable) null, Wp);
        }
    }

    public static void d(Object obj, String str, Object... objArr) {
        if (dQ(3)) {
            a(3, obj, String.format(str, objArr), (Throwable) null, Wp);
        }
    }

    public static void a(Object obj, String str, Throwable th) {
        if (dQ(3)) {
            a(3, obj, str, th, Wp);
        }
    }

    public static void a(Object obj, Throwable th) {
        if (dQ(3)) {
            a(3, obj, "Exception occurs at", th, Wp);
        }
    }

    public static void g(Object obj, String str) {
        if (dQ(4)) {
            a(4, obj, str, (Throwable) null, Wp);
        }
    }

    public static void i(Object obj, String str, Object... objArr) {
        if (dQ(4)) {
            a(4, obj, String.format(str, objArr), (Throwable) null, Wp);
        }
    }

    public static void w(String str) {
        if (dQ(5)) {
            a(5, (Object) null, str, (Throwable) null, Wp);
        }
    }

    public static void h(Object obj, String str) {
        if (dQ(5)) {
            a(5, obj, str, (Throwable) null, Wp);
        }
    }

    public static void b(Object obj, String str, Throwable th) {
        if (dQ(5)) {
            a(5, obj, str, th, Wp);
        }
    }

    public static void e(String str) {
        if (dQ(6)) {
            a(6, (Object) null, str, (Throwable) null, Wp);
        }
    }

    public static void i(Object obj, String str) {
        if (dQ(6)) {
            a(6, obj, str, (Throwable) null, Wp);
        }
    }

    public static void a(Object obj, String str, Object... objArr) {
        if (dQ(6)) {
            a(6, obj, String.format(str, objArr), (Throwable) null, Wp);
        }
    }

    public static void c(Object obj, String str, Throwable th) {
        if (dQ(6)) {
            a(6, obj, str, th, Wp);
        }
    }

    public static void b(Object obj, Throwable th) {
        if (dQ(6)) {
            a(6, obj, "Exception occurs at", th, Wp);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(int r4, java.lang.Object r5, java.lang.String r6, java.lang.Throwable r7, boolean r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L23
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            java.lang.StackTraceElement[] r1 = r1.getStackTrace()
            if (r1 == 0) goto L17
            int r2 = r1.length
            r3 = 4
            if (r2 <= r3) goto L17
            r1 = r1[r3]
            goto L18
        L17:
            r1 = r0
        L18:
            if (r1 == 0) goto L23
            java.lang.String r0 = r1.getFileName()
            int r1 = r1.getLineNumber()
            goto L24
        L23:
            r1 = 0
        L24:
            if (r8 != 0) goto L28
            if (r7 == 0) goto L2c
        L28:
            java.lang.String r6 = a(r0, r1, r6, r7, r8)
        L2c:
            java.lang.String r5 = o(r5)
            if (r5 != 0) goto L3c
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 == 0) goto L3b
            java.lang.String r0 = com.xiaopeng.lib.utils.c.Wm
            goto L3d
        L3b:
            goto L3d
        L3c:
            r0 = r5
        L3d:
            b(r4, r0, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.lib.utils.c.a(int, java.lang.Object, java.lang.String, java.lang.Throwable, boolean):void");
    }

    private static void b(int i, String str, String str2) {
        Wn.a(i, str2, str, null);
    }

    private static String a(String str, int i, String str2, Throwable th, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        if (z) {
            sb.append(" (T:");
            sb.append(Thread.currentThread().getId());
            sb.append(")");
            if (Wm != null) {
                sb.append("(C:");
                sb.append(Wm);
                sb.append(")");
            }
            sb.append("at (");
            if (str == null) {
                str = "";
            }
            sb.append(str);
            sb.append(":");
            sb.append(i);
            sb.append(")");
        }
        if (th != null) {
            sb.append('\n');
            sb.append(Log.getStackTraceString(th));
        }
        return sb.toString();
    }

    private static String o(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Class) {
            return ((Class) obj).getSimpleName();
        }
        return obj.getClass().getSimpleName();
    }

    /* compiled from: LogUtils.java */
    /* loaded from: classes12.dex */
    public static class a implements b {
        @Override // com.xiaopeng.lib.utils.c.b
        public void a(int i, String str, String str2, String str3) {
            switch (i) {
                case 2:
                    Log.v(str2, str);
                    return;
                case 3:
                    Log.d(str2, str);
                    String str4 = "DEBUG: " + str;
                    return;
                case 4:
                    Log.i(str2, str);
                    String str5 = "INFO: " + str;
                    return;
                case 5:
                    Log.w(str2, str);
                    String str6 = "WARN: " + str;
                    return;
                case 6:
                    Log.e(str2, str);
                    String str7 = "ERROR: " + str;
                    return;
                default:
                    return;
            }
        }
    }

    private static String getAppName() {
        String[] split = ActivityThread.currentApplication().getPackageName().split("\\.");
        return split[split.length - 1];
    }
}
