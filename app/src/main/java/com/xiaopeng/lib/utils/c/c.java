package com.xiaopeng.lib.utils.c;

import android.os.SystemProperties;
import android.util.Log;

/* compiled from: DeviceInfoUtils.java */
/* loaded from: classes12.dex */
public class c {
    public static String pp() {
        return SystemProperties.get("ro.product.model", "");
    }

    public static boolean pq() {
        boolean z = true;
        try {
            z = true ^ getVersionInCountryCode().contains("ZH");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("netChannel", "isInternationVersion :\t" + z);
        return z;
    }

    public static String getVersionInCountryCode() {
        String str = SystemProperties.get("ro.xiaopeng.software", "");
        if (!"".equals(str) && str != null) {
            return str.substring(7, 9);
        }
        return str;
    }
}
