package com.alibaba.mtl.log.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.alibaba.mtl.log.model.LogField;
import com.ut.device.UTDevice;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: DeviceUtil.java */
/* loaded from: classes11.dex */
public class d {
    private static Map<String, String> cV = null;

    public static synchronized Map<String, String> a(Context context) {
        synchronized (d.class) {
            if (cV != null) {
                cV.put(LogField.CHANNEL.toString(), b.aF());
                cV.put(LogField.APPKEY.toString(), b.getAppkey());
                a(cV, context);
                return cV;
            } else if (context == null) {
                return null;
            } else {
                cV = new HashMap();
                try {
                    String l = m.l(context);
                    String m = m.m(context);
                    if (TextUtils.isEmpty(l) || TextUtils.isEmpty(m)) {
                        l = "";
                        m = "";
                    }
                    cV.put(LogField.IMEI.toString(), l);
                    cV.put(LogField.IMSI.toString(), m);
                    cV.put(LogField.BRAND.toString(), Build.BRAND);
                    cV.put(LogField.DEVICE_MODEL.toString(), Build.MODEL);
                    cV.put(LogField.RESOLUTION.toString(), c(context));
                    cV.put(LogField.CHANNEL.toString(), b.aF());
                    cV.put(LogField.APPKEY.toString(), b.getAppkey());
                    cV.put(LogField.APPVERSION.toString(), d(context));
                    cV.put(LogField.LANGUAGE.toString(), b(context));
                    cV.put(LogField.OS.toString(), aJ());
                    cV.put(LogField.OSVERSION.toString(), aI());
                    cV.put(LogField.SDKVERSION.toString(), "2.6.4.10_for_bc");
                    cV.put(LogField.SDKTYPE.toString(), "mini");
                    try {
                        cV.put(LogField.UTDID.toString(), UTDevice.getUtdid(context));
                    } catch (Throwable th) {
                        Log.e("DeviceUtil", "utdid4all jar doesn't exist, please copy the libs folder.");
                        th.printStackTrace();
                    }
                    try {
                        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                        String str = "";
                        if (telephonyManager != null && telephonyManager.getSimState() == 5) {
                            str = telephonyManager.getNetworkOperatorName();
                        }
                        if (TextUtils.isEmpty(str)) {
                            str = "Unknown";
                        }
                        cV.put(LogField.CARRIER.toString(), str);
                    } catch (Exception e) {
                    }
                    a(cV, context);
                    return cV;
                } catch (Exception e2) {
                    return null;
                }
            }
        }
    }

    private static String aI() {
        String str = Build.VERSION.RELEASE;
        if (aK()) {
            String property = System.getProperty("ro.yunos.version");
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
            str = aO();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return str;
    }

    private static String aJ() {
        if (!aK() || aL()) {
            return "a";
        }
        return "y";
    }

    private static void a(Map<String, String> map, Context context) {
        try {
            String[] j = l.j(context);
            map.put(LogField.ACCESS.toString(), j[0]);
            if (j[0].equals("2G/3G")) {
                map.put(LogField.ACCESS_SUBTYPE.toString(), j[1]);
            } else {
                map.put(LogField.ACCESS_SUBTYPE.toString(), "Unknown");
            }
        } catch (Exception e) {
            map.put(LogField.ACCESS.toString(), "Unknown");
            map.put(LogField.ACCESS_SUBTYPE.toString(), "Unknown");
        }
    }

    private static String b(Context context) {
        try {
            return Locale.getDefault().getLanguage();
        } catch (Throwable th) {
            return "Unknown";
        }
    }

    private static String c(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            if (i > i2) {
                int i3 = i ^ i2;
                i2 ^= i3;
                i = i3 ^ i2;
            }
            return i2 + "*" + i;
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static String d(Context context) {
        String f = com.alibaba.mtl.log.b.aj().f();
        if (!TextUtils.isEmpty(f)) {
            return f;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo == null) {
                return "Unknown";
            }
            cV.put(LogField.APPVERSION.toString(), packageInfo.versionName);
            return packageInfo.versionName;
        } catch (Throwable th) {
            return "Unknown";
        }
    }

    public static boolean aK() {
        try {
            if ((System.getProperty("java.vm.name") == null || !System.getProperty("java.vm.name").toLowerCase().contains("lemur")) && System.getProperty("ro.yunos.version") == null && TextUtils.isEmpty(r.get("ro.yunos.build.version"))) {
                return aL();
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean aL() {
        if (!TextUtils.isEmpty(r.get("ro.yunos.product.chip")) || !TextUtils.isEmpty(r.get("ro.yunos.hardware"))) {
            return true;
        }
        return false;
    }

    public static String aM() {
        String str = r.get("ro.aliyun.clouduuid", "false");
        if ("false".equals(str)) {
            str = r.get("ro.sys.aliyun.clouduuid", "false");
        }
        if (TextUtils.isEmpty(str)) {
            return aN();
        }
        return str;
    }

    private static String aN() {
        try {
            return (String) Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    private static String aO() {
        try {
            Field declaredField = Build.class.getDeclaredField("YUNOS_BUILD_VERSION");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                return (String) declaredField.get(new String());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
