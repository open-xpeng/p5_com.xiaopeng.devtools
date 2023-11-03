package com.alibaba.sdk.android.man.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.regex.Pattern;

/* loaded from: classes11.dex */
public class ToolKit {
    private static final String TAG = "MAN_ToolKit";
    private static final String validIp = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    private static Pattern patternIp = Pattern.compile(validIp);
    private static final String validHostnameRegex = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
    private static Pattern patternHost = Pattern.compile(validHostnameRegex);

    public static Object checkNotNull(Object obj, Object obj2) {
        if (obj == null) {
            throw new IllegalArgumentException(String.valueOf(obj2));
        }
        return obj;
    }

    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    public static long convertStr2Long(String str) {
        try {
            return Long.valueOf(str).longValue();
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    public static String getMetaDataAppKey(Context context) {
        String str;
        if (context == null) {
            return "";
        }
        String str2 = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                Object obj = applicationInfo.metaData.get(MANConfig.MAN_APPKEY_META_DATA_KEY);
                if (!(obj instanceof String) && obj != null) {
                    str = obj.toString();
                    str2 = str;
                    MANLog.Logi(TAG, "appKey : " + str2);
                }
                str = (String) obj;
                str2 = str;
                MANLog.Logi(TAG, "appKey : " + str2);
            }
            if (str2 == null) {
                return "";
            }
            return str2;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String getMetaDataAppSecret(Context context) {
        String str;
        if (context == null) {
            return "";
        }
        String str2 = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                Object obj = applicationInfo.metaData.get(MANConfig.MAN_APPSECRET_META_DATA_KEY);
                if (!(obj instanceof String) && obj != null) {
                    str = obj.toString();
                    str2 = str;
                    MANLog.Logi(TAG, "appSecret : " + str2);
                }
                str = (String) obj;
                str2 = str;
                MANLog.Logi(TAG, "appSecret : " + str2);
            }
            if (str2 == null) {
                return "";
            }
            return str2;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String getMetaDataChannel(Context context) {
        String str;
        if (context == null) {
            return "";
        }
        String str2 = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                Object obj = applicationInfo.metaData.get(MANConfig.MAN_CHANNEL_META_DATA_KEY);
                if (!(obj instanceof String) && obj != null) {
                    str = obj.toString();
                    str2 = str;
                    MANLog.Logi(TAG, "channel : " + str2);
                }
                str = (String) obj;
                str2 = str;
                MANLog.Logi(TAG, "channel : " + str2);
            }
            if (str2 == null) {
                return "";
            }
            return str2;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String getMetaDataAppVersion(Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            str = "Unknown";
        }
        if (isNullOrEmpty(str)) {
            return "-";
        }
        return str;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isHost(String str) {
        if (str != null && !str.isEmpty()) {
            return patternHost.matcher(str).matches();
        }
        return false;
    }

    public static boolean isIp(String str) {
        if (str != null && !str.isEmpty()) {
            return patternIp.matcher(str).matches();
        }
        return false;
    }
}
