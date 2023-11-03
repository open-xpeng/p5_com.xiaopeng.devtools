package com.alibaba.mtl.log.d;

import android.app.ActivityManager;
import android.content.Context;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;

/* compiled from: AppInfoUtil.java */
/* loaded from: classes11.dex */
public class b {
    private static String cT = "";
    private static String g;

    public static String k() {
        if (com.alibaba.mtl.log.a.getContext() == null) {
            return "";
        }
        try {
            String string = com.alibaba.mtl.log.a.getContext().getSharedPreferences("UTCommon", 0).getString("_lun", "");
            if (TextUtils.isEmpty(string)) {
                return "";
            }
            return new String(c.decode(string.getBytes(), 2), "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String aE() {
        if (com.alibaba.mtl.log.a.getContext() == null) {
            return "";
        }
        try {
            String string = com.alibaba.mtl.log.a.getContext().getSharedPreferences("UTCommon", 0).getString("_luid", "");
            if (TextUtils.isEmpty(string)) {
                return "";
            }
            return new String(c.decode(string.getBytes(), 2), "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String aF() {
        return cT;
    }

    public static void z(String str) {
        i.a("AppInfoUtil", "[setChannle]", str);
        if (!TextUtils.isEmpty(str)) {
            int indexOf = str.indexOf("@");
            if (indexOf == -1) {
                cT = str;
            } else {
                cT = str.substring(0, indexOf);
            }
        }
    }

    public static String aG() {
        return "";
    }

    public static String aH() {
        return "";
    }

    public static String getAppkey() {
        return g;
    }

    public static void A(String str) {
        i.a("AppInfoUtil", "set Appkey:", str);
        g = str;
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            String packageName = context.getPackageName();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(packageName)) {
                    if (runningAppProcessInfo.importance == 400) {
                        return false;
                    }
                    if (powerManager.isScreenOn()) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }
}
