package com.xiaopeng.lib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemProperties;

/* compiled from: NetUtils.java */
/* loaded from: classes12.dex */
public class e {
    public static boolean ar(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
            return true;
        }
        return false;
    }

    public static int as(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context != null && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null) {
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                        return 1;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 2;
                    case 13:
                        return 3;
                }
            } else if (activeNetworkInfo.getType() == 1) {
                return 10;
            }
        }
        return 0;
    }

    public static boolean at(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static int oN() {
        return SystemProperties.getInt("persist.sys.xp.4g.st", 3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003d, code lost:
        if ("unknown".equals(r0) != false) goto L21;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v8, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean oO() {
        /*
            java.lang.String r0 = "on"
            java.lang.String r1 = "persist.sys.ril.gobinet"
            java.lang.String r1 = android.os.SystemProperties.get(r1)
            boolean r0 = r0.equals(r1)
            r1 = 1
            if (r0 != 0) goto L11
            return r1
        L11:
            java.io.File r0 = new java.io.File
            java.lang.String r2 = "/sys/class/net/xpusb0/operstate"
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 != 0) goto L1f
            return r1
        L1f:
            r2 = 0
            r3 = 0
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d
            java.io.FileReader r5 = new java.io.FileReader     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d
            java.lang.String r0 = r4.readLine()     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L48
            java.lang.String r3 = "up"
            boolean r3 = r3.equals(r0)     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L48
            if (r3 != 0) goto L3f
            java.lang.String r3 = "unknown"
            boolean r0 = r3.equals(r0)     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L48
            if (r0 == 0) goto L41
        L3f:
        L40:
            r2 = r1
        L41:
            com.xiaopeng.lib.utils.b.closeQuietly(r4)
            goto L54
        L45:
            r0 = move-exception
            r3 = r4
            goto L56
        L48:
            r0 = move-exception
            r3 = r4
            goto L4e
        L4b:
            r0 = move-exception
            goto L56
        L4d:
            r0 = move-exception
        L4e:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L4b
            com.xiaopeng.lib.utils.b.closeQuietly(r3)
        L54:
            return r2
        L56:
            com.xiaopeng.lib.utils.b.closeQuietly(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.lib.utils.e.oO():boolean");
    }
}
