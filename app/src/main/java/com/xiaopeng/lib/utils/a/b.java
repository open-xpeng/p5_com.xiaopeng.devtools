package com.xiaopeng.lib.utils.a;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

/* compiled from: EnvConfig.java */
/* loaded from: classes12.dex */
public final class b {
    private static Properties Xb = new Properties();

    static {
        init();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Properties pa() {
        Properties properties = new Properties();
        properties.putAll(Xb);
        return properties;
    }

    public static boolean dL(String str) {
        return Xb.containsKey(str);
    }

    public static boolean pb() {
        return Xb.size() > 0 && dL("main_host");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeKey(String str) {
        if (!TextUtils.isEmpty(str)) {
            Xb.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setString(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        Xb.put(str, str2);
    }

    public static String Y(String str, String str2) {
        return q("main_host", str, str2);
    }

    public static String q(String str, String str2, String str3) {
        if (com.xiaopeng.lib.utils.c.b.pn()) {
            String property = Xb.getProperty(str);
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
        }
        if (com.xiaopeng.lib.utils.c.b.pk()) {
            return str2;
        }
        return str3;
    }

    private static long dM(String str) {
        try {
            String str2 = "yyyyMMdd HH:mm:ss";
            if (str.indexOf(":") < 0) {
                if (str.indexOf(" ") < 0) {
                    if (str.length() <= 8) {
                        str2 = "yyyyMMdd";
                    } else {
                        str2 = "yyyyMMddHHmmss";
                    }
                } else {
                    str2 = "yyyyMMdd HHmmss";
                }
            }
            return new SimpleDateFormat(str2).parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    private static void init() {
        FileInputStream fileInputStream;
        Throwable th;
        BufferedInputStream bufferedInputStream;
        Exception e;
        File file;
        if (!com.xiaopeng.lib.utils.c.b.pn()) {
            return;
        }
        try {
            try {
                file = new File("/sdcard/pre_env.ini");
            } catch (Exception e2) {
                fileInputStream = null;
                e = e2;
                bufferedInputStream = null;
            } catch (Throwable th2) {
                fileInputStream = null;
                th = th2;
                bufferedInputStream = null;
            }
            if (!file.exists()) {
                com.xiaopeng.lib.utils.b.closeQuietly(null);
                com.xiaopeng.lib.utils.b.closeQuietly(null);
                return;
            }
            fileInputStream = new FileInputStream(file);
            try {
                bufferedInputStream = new BufferedInputStream(fileInputStream);
            } catch (Exception e3) {
                bufferedInputStream = null;
                e = e3;
            } catch (Throwable th3) {
                bufferedInputStream = null;
                th = th3;
                com.xiaopeng.lib.utils.b.closeQuietly(bufferedInputStream);
                com.xiaopeng.lib.utils.b.closeQuietly(fileInputStream);
                throw th;
            }
            try {
                Xb.load(bufferedInputStream);
                Log.w("EnvConfig", "<<<< warning, load file: pre_env.ini !!!");
                String property = Xb.getProperty("expired_time", null);
                if (!TextUtils.isEmpty(property)) {
                    long dM = dM(property);
                    if (dM > 0 && System.currentTimeMillis() >= dM) {
                        Log.w("EnvConfig", "<<<< file pre_env.ini is expired!");
                        Xb.clear();
                    }
                }
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
                com.xiaopeng.lib.utils.b.closeQuietly(bufferedInputStream);
                com.xiaopeng.lib.utils.b.closeQuietly(fileInputStream);
            }
            com.xiaopeng.lib.utils.b.closeQuietly(bufferedInputStream);
            com.xiaopeng.lib.utils.b.closeQuietly(fileInputStream);
        } catch (Throwable th4) {
            th = th4;
        }
    }
}
