package com.alibaba.mtl.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.l;
import com.alibaba.mtl.log.d.s;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: UTDC.java */
/* loaded from: classes11.dex */
public class a {
    private static boolean bI;
    public static String bJ;
    public static final AtomicInteger bK;
    public static boolean bL;
    public static com.alibaba.mtl.log.e.b bM;
    public static boolean bN;
    private static Context mContext;
    private static boolean a = false;
    public static boolean bG = false;
    public static int s = com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.r;
    public static int t = 0;
    public static long b = -1;
    public static boolean bH = false;

    static {
        bI = t <= s;
        bJ = String.valueOf(System.currentTimeMillis());
        bK = new AtomicInteger(0);
        bL = true;
        bM = null;
        bN = true;
    }

    public static synchronized void a(Context context) {
        synchronized (a.class) {
            if (context == null) {
                i.a("UTDC", "UTDC init failed ,context:" + context);
                return;
            }
            if (!a) {
                a = true;
                mContext = context.getApplicationContext();
                com.alibaba.mtl.log.f.a.ba().start();
            }
        }
    }

    public static void a(com.alibaba.mtl.log.e.b bVar) {
        bM = bVar;
        if (bM != null) {
            com.alibaba.mtl.log.d.b.A(bM.getAppkey());
        }
    }

    public static void setChannel(String str) {
        com.alibaba.mtl.log.d.b.z(str);
    }

    public static void aa() {
        i.a("UTDC", "[onBackground]");
        bG = true;
        com.alibaba.mtl.log.b.a.aw();
    }

    public static void ab() {
        i.a("UTDC", "[onForeground]");
        bG = false;
        com.alibaba.mtl.log.f.a.ba().start();
    }

    public static void c(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        if (mContext == null) {
            i.a("UTDC", "please call UTDC.init(context) before commit log,and this log will be discarded");
        } else if (bM == null) {
            i.a("UTDC", "please call UTDC.setRequestAuthentication(auth) before commit log,and this log will be discarded");
        } else {
            d(str, str2, str3, str4, str5, map);
        }
    }

    private static void d(final String str, final String str2, final String str3, final String str4, final String str5, Map<String, String> map) {
        final HashMap hashMap = new HashMap(map);
        s.aW().b(new Runnable() { // from class: com.alibaba.mtl.log.a.1
            @Override // java.lang.Runnable
            public void run() {
                i.a("UTDC", "[commit] page:", str, "eventId:", str2, "arg1:", str3, "arg2:", str4, "arg3:", str5, "args:", hashMap);
                try {
                    com.alibaba.mtl.log.b.a.u(str2);
                    com.alibaba.mtl.log.c.c.aA().a(new com.alibaba.mtl.log.model.a(str, str2, str3, str4, str5, hashMap));
                } catch (Throwable th) {
                }
            }
        });
    }

    public static Context getContext() {
        return mContext;
    }

    public static com.alibaba.mtl.log.e.b ac() {
        if (bM == null || TextUtils.isEmpty(bM.getAppkey())) {
            if (i.aR()) {
                throw new RuntimeException("please Set <meta-data android:value=\"YOU KEY\" android:name=\"com.alibaba.apmplus.app_key\"></meta-data> in app AndroidManifest.xml ");
            }
            Log.w("UTDC", "please Set <meta-data android:value=\"YOU KEY\" android:name=\"com.alibaba.apmplus.app_key\"></meta-data> in app AndroidManifest.xml ");
        }
        return bM;
    }

    public static String b() {
        try {
            return l.j(getContext())[0];
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static String c() {
        try {
            String[] j = l.j(getContext());
            if (j[0].equals("2G/3G")) {
                return j[1];
            }
            return "Unknown";
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static String d() {
        return "";
    }

    public static String e() {
        return "";
    }

    public static void ad() {
        com.alibaba.mtl.log.f.a.ba().start();
    }
}
