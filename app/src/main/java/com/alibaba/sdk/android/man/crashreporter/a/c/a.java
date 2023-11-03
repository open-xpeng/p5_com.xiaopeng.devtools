package com.alibaba.sdk.android.man.crashreporter.a.c;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes11.dex */
public class a {
    private static final int s = -1;
    private static final int t = 0;
    private static final int u = 1;
    private static int v = -1;

    public static String b(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "null";
        }
        String str = "{";
        try {
            for (String str2 : map.keySet()) {
                String str3 = map.get(str2);
                if (str3 != null) {
                    str = str + "\"" + ((Object) str2) + "\":\"" + str3 + "\",";
                }
            }
            String substring = str.substring(0, str.length() - 1);
            try {
                return substring + "}";
            } catch (Exception e) {
                str = substring;
                e = e;
                com.alibaba.sdk.android.man.crashreporter.b.a.d("simpleMapToJsonStr error.", e);
                return str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public static String m45a(Context context) {
        if (context != null) {
            try {
                int myPid = Process.myPid();
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager != null) {
                    for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                        if (runningAppProcessInfo.pid == myPid) {
                            return runningAppProcessInfo.processName;
                        }
                    }
                    return null;
                }
                return null;
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("getCurProcessName error.", e);
                return null;
            }
        }
        return null;
    }

    public static double a(Context context) {
        String[] split;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            for (String str : readLine.split("\\s+")) {
                com.alibaba.sdk.android.man.crashreporter.b.a.c(readLine, str + "\t");
            }
            bufferedReader.close();
            return Integer.valueOf(split[1]).intValue() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (IOException e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getTotalMemory error.", e);
            return -1.0d;
        }
    }

    public static double b(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        try {
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            return (memoryInfo.availMem / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getAvailMemory error.", e);
            return -1.0d;
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public static boolean m46a(Context context) {
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                    if (runningAppProcessInfo.importance == 400) {
                        com.alibaba.sdk.android.man.crashreporter.b.a.b("app is background :", runningAppProcessInfo.processName);
                        return true;
                    }
                    com.alibaba.sdk.android.man.crashreporter.b.a.b("app is foreground:", runningAppProcessInfo.processName);
                    return false;
                }
            }
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("isBackgroundRunning  error.", e);
        }
        return false;
    }

    /* renamed from: b  reason: collision with other method in class */
    public static String m47b(Context context) {
        try {
            return ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses().get(0).processName;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getRunningActivityName error.", e);
            return "";
        }
    }

    public static String c(Context context) {
        try {
            if (com.alibaba.sdk.android.man.crashreporter.e.a.d(context) && com.alibaba.sdk.android.man.crashreporter.e.a.m53e(context)) {
                return com.alibaba.sdk.android.man.crashreporter.e.a.c(context);
            }
            return "127.0.0.1";
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getWifiIpAddress error.", e);
            return "127.0.0.1";
        }
    }

    public static boolean b() {
        if (v == 1) {
            return true;
        }
        if (v == 0) {
            return false;
        }
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < strArr.length; i++) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    v = 1;
                    return true;
                }
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("isRootSystem error.", e);
            }
        }
        v = 0;
        return false;
    }

    public static String getExternalStorageState() {
        try {
            return Environment.getExternalStorageState();
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("hasSDCard error.", e);
            return "unknown";
        }
    }

    public static long[] a(boolean z) {
        File externalStorageDirectory;
        long[] jArr = {-1, -1, -1};
        try {
            if (z) {
                externalStorageDirectory = Environment.getDataDirectory();
            } else {
                externalStorageDirectory = Environment.getExternalStorageDirectory();
            }
            if (externalStorageDirectory != null) {
                StatFs statFs = new StatFs(externalStorageDirectory.getPath());
                int blockSize = statFs.getBlockSize();
                int blockCount = statFs.getBlockCount();
                int freeBlocks = statFs.getFreeBlocks();
                int availableBlocks = statFs.getAvailableBlocks();
                jArr[0] = blockCount * blockSize;
                jArr[1] = freeBlocks * blockSize;
                jArr[2] = blockSize * availableBlocks;
            }
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getStorageSize error.", e);
        }
        return jArr;
    }

    /* renamed from: b  reason: collision with other method in class */
    public static boolean m48b(Context context) {
        try {
            if ((context.getApplicationInfo().flags & 262144) != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("isInstallOnSDCard error.", e);
            return false;
        }
    }
}
