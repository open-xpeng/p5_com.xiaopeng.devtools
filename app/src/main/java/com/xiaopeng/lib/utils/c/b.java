package com.xiaopeng.lib.utils.c;

import android.os.Build;
import android.os.SystemProperties;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

/* compiled from: BuildInfoUtils.java */
/* loaded from: classes12.dex */
public class b {
    private static String Xe = null;

    public static String getSystemVersion() {
        String pg;
        int indexOf;
        int i;
        int indexOf2;
        String string = getString("ro.product.firmware");
        if ("unknown".equals(string) && (indexOf = (pg = pg()).indexOf("_")) > 1 && (indexOf2 = pg.indexOf("_", (i = indexOf + 1))) > indexOf) {
            return pg.substring(i, indexOf2);
        }
        return string;
    }

    public static String pg() {
        if (Xe != null) {
            return Xe;
        }
        Xe = getString("ro.xiaopeng.software");
        if ("unknown".equals(Xe)) {
            Xe = getString("ro.build.display.id");
        }
        return Xe;
    }

    public static String ph() {
        return getString("ro.xiaopeng.hardware");
    }

    public static int pi() {
        String ph = ph();
        if (!"unknown".equals(ph) && ph.length() >= 5) {
            try {
                return Integer.parseInt(ph.substring(3, ph.length()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static String getHardwareId() {
        return getString("persist.sys.mcu.hardwareId");
    }

    public static String pj() {
        int indexOf;
        String pg = pg();
        if (TextUtils.isEmpty(pg) || (indexOf = pg.indexOf("_")) <= 1) {
            return "4";
        }
        return pg.substring(indexOf - 1, indexOf);
    }

    public static boolean pk() {
        return "4".equals(pj());
    }

    public static boolean pl() {
        return "eng".equals(Build.TYPE);
    }

    public static boolean pm() {
        return "userdebug".equals(Build.TYPE);
    }

    public static boolean pn() {
        return pl() || pm();
    }

    public static boolean po() {
        return "user".equals(Build.TYPE);
    }

    private static String getString(String str) {
        return SystemProperties.get(str, "unknown");
    }

    @VisibleForTesting
    static boolean isDeviceApproved() {
        String string = getString("ro.product.manufacturer");
        return "XiaoPeng".equals(string) || "XPENG".equals(string);
    }
}
