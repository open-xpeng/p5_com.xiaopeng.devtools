package com.xiaopeng.lib.utils;

import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;

/* compiled from: SystemPropertyUtil.java */
/* loaded from: classes12.dex */
public class i {
    private static final String Ww = "persist.sys.mqtt.comm_url" + com.xiaopeng.lib.utils.c.b.pj();
    private static final String Wx = "persist.sys.mqtt.url" + com.xiaopeng.lib.utils.c.b.pj();
    private static final String SYSTEM_PROPERTY_MQTT_USER = "persist.sys.mqtt.user" + com.xiaopeng.lib.utils.c.b.pj();
    private static final String SYSTEM_PROPERTY_MQTT_PASS = "persist.sys.mqtt.pass" + com.xiaopeng.lib.utils.c.b.pj();
    private static final String SYSTEM_PROPERTY_MQTT_ID = "persist.sys.mqtt.id" + com.xiaopeng.lib.utils.c.b.pj();

    public static long oR() {
        return SystemProperties.getLong("persist.sys.account_uid", -1L);
    }

    public static int lp() {
        return SystemProperties.getInt("persist.sys.vehicle_id", -1);
    }

    public static String getIccid() {
        if (Build.VERSION.SDK_INT >= 26) {
            return SystemProperties.get("sys.xiaopeng.iccid", "");
        }
        return SystemProperties.get("ril.audio.iccid", "");
    }

    public static String eH() {
        String str = SystemProperties.get("persist.sys.xiaopeng.vin", "");
        if (TextUtils.isEmpty(str)) {
            return SystemProperties.get("sys.xiaopeng.vin", "");
        }
        return str;
    }

    public static String getHardwareId() {
        if (Build.VERSION.SDK_INT >= 26) {
            return SystemProperties.get("persist.sys.mcu.hardwareId");
        }
        return SystemProperties.get("persist.sys.hardware_id");
    }

    public static String oS() {
        return SystemProperties.get("ro.xiaopeng.software");
    }

    public static String oT() {
        return SystemProperties.get("sys.xiaopeng.dbc_ver", "");
    }
}
