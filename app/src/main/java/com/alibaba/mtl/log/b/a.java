package com.alibaba.mtl.log.b;

import android.text.TextUtils;
import com.alibaba.mtl.log.d.i;
import java.util.List;

/* compiled from: CoreStatics.java */
/* loaded from: classes11.dex */
public class a {
    private static volatile long e;
    private static long f;
    private static long g;
    private static long h;
    private static long i;
    private static int u;
    private static long j = 0;
    private static long k = 0;
    private static long ck = 0;
    private static long cl = 0;
    private static int v = 0;
    private static int w = 0;
    private static long cm = 0;
    private static long cn = 0;
    private static long co = 0;
    private static long cp = 0;
    private static long cq = 0;
    private static long cr = 0;
    private static long t = 0;
    private static long cs = 0;
    private static long ct = 0;

    /* renamed from: w  reason: collision with other field name */
    private static long f5w = 0;
    private static long x = 0;
    private static long y = 0;
    private static StringBuilder cu = new StringBuilder();

    public static synchronized void u(String str) {
        synchronized (a.class) {
            if (e(str)) {
                return;
            }
            if ("65501".equalsIgnoreCase(str)) {
                y++;
            } else if ("65133".equalsIgnoreCase(str)) {
                f5w++;
            } else if ("65502".equalsIgnoreCase(str)) {
                x++;
            } else if ("65503".equalsIgnoreCase(str)) {
                ct++;
            }
            e++;
        }
    }

    public static synchronized void x(String str) {
        synchronized (a.class) {
            if (e(str)) {
                return;
            }
            f++;
        }
    }

    public static synchronized void a(List<com.alibaba.mtl.log.model.a> list, int i2) {
        synchronized (a.class) {
            if (list == null) {
                return;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                com.alibaba.mtl.log.model.a aVar = list.get(i4);
                if (aVar != null) {
                    if (!"6005".equalsIgnoreCase(aVar.X)) {
                        i3++;
                    }
                    cu.append(aVar.ec);
                    if (i4 != list.size() - 1) {
                        cu.append(",");
                    }
                }
            }
            i.a("CoreStatics", "[uploadInc]:", Long.valueOf(g), "count:", Integer.valueOf(i2));
            g += i2;
            i.a("CoreStatics", "[uploadInc]:", Long.valueOf(g));
            if (i3 != i2) {
                i.a("CoreStatics", "Mutil Process Upload Error");
            }
        }
    }

    public static synchronized void d(int i2) {
        synchronized (a.class) {
            u += i2;
        }
    }

    public static synchronized void am() {
        synchronized (a.class) {
            h++;
        }
    }

    public static synchronized void an() {
        synchronized (a.class) {
            i++;
        }
    }

    public static synchronized void ao() {
        synchronized (a.class) {
            cm++;
        }
    }

    public static synchronized void ap() {
        synchronized (a.class) {
            cn++;
        }
    }

    public static synchronized void aq() {
        synchronized (a.class) {
            co++;
        }
    }

    public static synchronized void ar() {
        synchronized (a.class) {
            cp++;
        }
    }

    public static synchronized void as() {
        synchronized (a.class) {
            cq++;
        }
    }

    public static synchronized void at() {
        synchronized (a.class) {
            cr++;
        }
    }

    public static synchronized void au() {
        synchronized (a.class) {
            t++;
        }
    }

    public static synchronized void av() {
        synchronized (a.class) {
            cs++;
        }
    }

    @Deprecated
    public static synchronized void c(boolean z) {
        synchronized (a.class) {
        }
    }

    public static synchronized void aw() {
        synchronized (a.class) {
            w++;
            if (e == 0 && g == 0) {
                return;
            }
            if (com.alibaba.mtl.log.a.bG || w >= 6) {
                c(true);
            }
        }
    }

    private static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "6005".equalsIgnoreCase(str.trim());
    }
}
