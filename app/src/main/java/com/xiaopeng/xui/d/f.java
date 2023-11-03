package com.xiaopeng.xui.d;

import android.util.Log;
import androidx.annotation.RestrictTo;

/* compiled from: XLogUtils.java */
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes13.dex */
public class f {
    private static String Wm = "xpui";
    private static b abp = new a();
    private static boolean Wo = true;
    private static boolean Wp = false;
    private static int Wl = 4;

    /* compiled from: XLogUtils.java */
    /* loaded from: classes13.dex */
    public interface b {
        void a(int i, String str, String str2, String str3);
    }

    public static boolean oM() {
        return Wo;
    }

    public static boolean dQ(int i) {
        return Wl <= i && oM();
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

    public static void g(Object obj, String str) {
        if (dQ(4)) {
            a(4, obj, str, (Throwable) null, Wp);
        }
    }

    public static void h(Object obj, String str) {
        if (dQ(5)) {
            a(5, obj, str, (Throwable) null, Wp);
        }
    }

    public static void i(Object obj, String str) {
        if (dQ(6)) {
            a(6, obj, str, (Throwable) null, Wp);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(int r4, java.lang.Object r5, java.lang.String r6, java.lang.Throwable r7, boolean r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L21
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            java.lang.StackTraceElement[] r1 = r1.getStackTrace()
            int r2 = r1.length
            r3 = 4
            if (r2 <= r3) goto L15
            r1 = r1[r3]
            goto L16
        L15:
            r1 = r0
        L16:
            if (r1 == 0) goto L21
            java.lang.String r0 = r1.getFileName()
            int r1 = r1.getLineNumber()
            goto L22
        L21:
            r1 = 0
        L22:
            if (r8 != 0) goto L26
            if (r7 == 0) goto L2a
        L26:
            java.lang.String r6 = a(r0, r1, r6, r7, r8)
        L2a:
            java.lang.String r5 = o(r5)
            if (r5 != 0) goto L3a
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 == 0) goto L39
            java.lang.String r0 = com.xiaopeng.xui.d.f.Wm
            goto L3b
        L39:
            goto L3b
        L3a:
            r0 = r5
        L3b:
            b(r4, r0, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.xui.d.f.a(int, java.lang.Object, java.lang.String, java.lang.Throwable, boolean):void");
    }

    private static void b(int i, String str, String str2) {
        abp.a(i, str2, str, null);
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
        return (obj instanceof Class ? (Class) obj : obj.getClass()).getSimpleName();
    }

    /* compiled from: XLogUtils.java */
    /* loaded from: classes13.dex */
    static class a implements b {
        a() {
        }

        @Override // com.xiaopeng.xui.d.f.b
        public void a(int i, String str, String str2, String str3) {
            switch (i) {
                case 2:
                    Log.v(str2, str);
                    return;
                case 3:
                    Log.d(str2, str);
                    return;
                case 4:
                    Log.i(str2, str);
                    return;
                case 5:
                    Log.w(str2, str);
                    return;
                case 6:
                    Log.e(str2, str);
                    return;
                default:
                    return;
            }
        }
    }
}
