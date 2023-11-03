package com.alibaba.mtl.log.d;

import android.os.Process;
import android.util.Log;

/* compiled from: Logger.java */
/* loaded from: classes11.dex */
public class i {
    private static String dc = "UTAnalytics:";
    private static boolean dd = false;
    private static boolean de = false;

    public static boolean aQ() {
        return dd;
    }

    public static boolean aR() {
        return de;
    }

    public static void d(boolean z) {
        de = z;
    }

    public static void a(String str, Object... objArr) {
        if (de) {
            String str2 = dc + str;
            StringBuilder sb = new StringBuilder();
            sb.append("pid:");
            sb.append(Process.myPid());
            sb.append(" ");
            if (objArr != null) {
                for (int i = 0; i < objArr.length; i++) {
                    if (objArr[i] != null) {
                        String obj = objArr[i].toString();
                        if (obj.endsWith(":") || obj.endsWith(": ")) {
                            sb.append(obj);
                        } else {
                            sb.append(obj);
                            sb.append(",");
                        }
                    }
                }
            }
            Log.d(str2, sb.toString());
        }
    }

    public static void a(String str, Object obj, Throwable th) {
        if (aR() || aQ()) {
            Log.w(str + dc, obj + "", th);
        }
    }

    public static void a(String str, Object obj) {
        if (aR() || aQ()) {
            Log.w(str + dc, obj + "");
        }
    }

    public static void a(String str, String... strArr) {
        if (de) {
            String str2 = dc + str;
            StringBuilder sb = new StringBuilder();
            sb.append("pid:");
            sb.append(Process.myPid());
            sb.append(" ");
            if (strArr != null) {
                for (int i = 0; i < strArr.length; i++) {
                    if (strArr[i] != null) {
                        String str3 = strArr[i];
                        if (str3.endsWith(":") || str3.endsWith(": ")) {
                            sb.append(str3);
                        } else {
                            sb.append(str3);
                            sb.append(",");
                        }
                    }
                }
            }
            Log.i(str2, sb.toString());
        }
    }
}
