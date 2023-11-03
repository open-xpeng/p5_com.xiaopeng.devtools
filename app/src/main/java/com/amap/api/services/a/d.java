package com.amap.api.services.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: AppInfo.java */
/* loaded from: classes11.dex */
public class d {
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static String e = null;

    public static String a(Context context) {
        try {
            return g(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return d;
        }
    }

    public static String b(Context context) {
        try {
        } catch (Throwable th) {
            o.a(th, "AppInfo", "getApplicationName");
        }
        if (!"".equals(a)) {
            return a;
        }
        PackageManager packageManager = context.getPackageManager();
        a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        return a;
    }

    public static String c(Context context) {
        try {
        } catch (Throwable th) {
            o.a(th, "AppInfo", "getPackageName");
        }
        if (b != null && !"".equals(b)) {
            return b;
        }
        b = context.getApplicationContext().getPackageName();
        return b;
    }

    public static String d(Context context) {
        try {
        } catch (Throwable th) {
            o.a(th, "AppInfo", "getApplicationVersion");
        }
        if (!"".equals(c)) {
            return c;
        }
        c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        return c == null ? "" : c;
    }

    public static String e(Context context) {
        try {
            if (e != null && !"".equals(e)) {
                return e;
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] digest = MessageDigest.getInstance("SHA1").digest(packageInfo.signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                String upperCase = Integer.toHexString(255 & b2).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(upperCase);
                stringBuffer.append(":");
            }
            stringBuffer.append(packageInfo.packageName);
            e = stringBuffer.toString();
            return e;
        } catch (Throwable th) {
            o.a(th, "AppInfo", "getSHA1AndPackage");
            return e;
        }
    }

    public static String f(Context context) {
        try {
            return g(context);
        } catch (Throwable th) {
            o.a(th, "AppInfo", "getKey");
            return d;
        }
    }

    private static String g(Context context) throws PackageManager.NameNotFoundException {
        if (d == null || d.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return d;
            }
            d = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            if (d == null) {
                d = "";
            }
        }
        return d;
    }
}
