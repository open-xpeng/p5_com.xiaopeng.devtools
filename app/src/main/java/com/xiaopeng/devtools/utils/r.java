package com.xiaopeng.devtools.utils;

import com.xiaopeng.a.a;

/* compiled from: SystemPropertyUtil.java */
/* loaded from: classes12.dex */
public class r {
    public static boolean lj() {
        return a.e.getString("PROPERTY_VALUE_CHN_REGION").equalsIgnoreCase(a.d.get("LOCALE_REGION"));
    }

    public static int getLogLevel() {
        return a.d.getInt("LOG_LEVEL", 1);
    }

    public static void cD(int i) {
        a.d.set("LOG_LEVEL", String.valueOf(i));
    }

    public static void cE(int i) {
        a.d.set("TCP_PORT", String.valueOf(i));
    }

    public static int lk() {
        return a.d.getInt("TCP_PORT", -1);
    }

    public static void U(boolean z) {
        a.d.set("DEBUG_MODE", String.valueOf(z));
    }

    public static boolean hL() {
        return a.d.getBoolean("UDISK_READONLY");
    }

    public static void F(boolean z) {
        a.d.set("UDISK_READONLY", String.valueOf(z));
    }

    public static void V(boolean z) {
        a.d.set("BTSNOOP_ENABLE", String.valueOf(z));
    }

    public static boolean ll() {
        return a.d.getBoolean("BTSNOOP_ENABLE");
    }

    public static void cG(String str) {
        a.d.set("BTSNOOP_PATH", str);
    }

    public static String lm() {
        return a.d.get("LTE_APN");
    }

    public static void cH(String str) {
        a.d.set("LTE_APN", str);
    }

    public static boolean ln() {
        return a.d.getBoolean("NCM_CLUSTER");
    }

    public static String eH() {
        return a.d.get("VIN", "");
    }

    public static String lo() {
        return a.d.get("UDISK_PATH", "");
    }

    public static String getHardwareId() {
        return a.d.get("HARDWARE_ID", "");
    }

    public static int lp() {
        return a.d.getInt("VID", -1);
    }

    public static String getIccid() {
        return a.d.get("ICCID", "");
    }

    public static String hJ() {
        return a.d.get("HARDWARE_VERSION");
    }

    public static String lq() {
        return a.d.get("SOFTWARE_VERSION");
    }

    public static String getMcuVersion() {
        return a.d.get("MCU_VERSION");
    }

    public static String lr() {
        return a.d.get("TBOX_VERSION");
    }

    public static String ls() {
        return a.d.get("TMCU_VERSION");
    }

    public static void cI(String str) {
        a.d.set("CONTROL_START", str);
    }

    public static void cJ(String str) {
        a.d.set("CONTROL_RESTART", str);
    }

    public static void cK(String str) {
        a.d.set("CONTROL_STOP", str);
    }

    public static void W(boolean z) {
        a.d.set("GRAB_LOG", z ? a.e.getString("PROPERTY_VALUE_ON") : a.e.getString("PROPERTY_VALUE_OFF"));
    }

    public static boolean lt() {
        return a.e.getString("PROPERTY_VALUE_ON").equalsIgnoreCase(a.d.get("GRAB_LOG"));
    }

    public static void H(boolean z) {
        a.d.set("SHOW_DIALOG", String.valueOf(z));
    }

    public static boolean lu() {
        return a.d.getBoolean("SHOW_DIALOG");
    }

    public static boolean lv() {
        return !a.e.getString("PROPERTY_VALUE_USER").equalsIgnoreCase(a.d.get("BUILD_TYPE"));
    }

    public static boolean lw() {
        return a.e.getString("PROPERTY_VALUE_RAMDUMP_ON").equalsIgnoreCase(a.d.get("RAMDUMP"));
    }

    public static void cF(int i) {
        a.d.set("CFCINDEX", String.valueOf(i));
    }

    public static int lx() {
        return a.d.getInt("CFCINDEX", 0);
    }

    public static boolean ly() {
        return a.d.getBoolean("NAVI_LOG");
    }

    public static void X(boolean z) {
        a.d.set("NAVI_LOG", String.valueOf(z));
    }

    public static String getPartNumber() {
        return a.d.get("PARTNUMBER");
    }

    public static boolean lz() {
        String str = "UNKNOWN";
        String[] split = a.d.get("SOFTWARE_VERSION").split("_");
        if (split.length > 2) {
            str = split[split.length - 1];
        }
        return "DEV".equals(str) || "FC".equals(str);
    }

    public static void Y(boolean z) {
        a.d.set("FACTORY_MODE", String.valueOf(z));
    }

    public static boolean isFactoryMode() {
        return a.d.getBoolean("FACTORY_MODE");
    }

    public static boolean lA() {
        return a.d.getBoolean("XPU");
    }

    public static boolean lB() {
        if (a.d.get("TCPDUMP_TBOX", "").equals("running") || a.d.get("TCPDUMP_WLAN", "").equals("running") || a.d.get("TCPDUMP_ETH", "").equals("running")) {
            return true;
        }
        return false;
    }

    public static boolean lC() {
        if (a.d.get("TCPDUMP_ICM", "").equals("running")) {
            return true;
        }
        return false;
    }

    public static int lD() {
        return a.d.getInt("PROP_CODE_VERSION", 1);
    }
}
