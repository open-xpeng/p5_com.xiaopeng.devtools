package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;
import java.net.SocketTimeoutException;

/* loaded from: classes11.dex */
public class t {

    /* renamed from: g  reason: collision with other field name */
    private static boolean f51g = false;

    /* renamed from: e  reason: collision with other field name */
    private static boolean f50e = false;
    private static SharedPreferences a = null;
    private static int f = 0;
    private static int g = 0;
    private static long e = 0;

    /* renamed from: a  reason: collision with other field name */
    private static a f49a = a.ENABLE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public enum a {
        ENABLE,
        PRE_DISABLE,
        DISABLE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized String a(o oVar) {
        synchronized (t.class) {
            if (oVar != o.QUERY_HOST && oVar != o.SNIFF_HOST) {
                return (oVar == o.QUERY_SCHEDULE_CENTER || oVar == o.SNIFF_SCHEDULE_CENTER) ? null : null;
            }
            if (f49a != a.ENABLE && f49a != a.PRE_DISABLE) {
                if (oVar == o.QUERY_HOST) {
                    return null;
                }
                return f.f32b[f];
            }
            return f.f32b[f];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void a(String str, String str2, long j) {
        synchronized (t.class) {
            b(str, str2, j);
            reportHttpDnsSuccess(str, 1);
            if (f49a != a.ENABLE && str2 != null && str2.equals(f.f32b[f])) {
                StringBuilder sb = new StringBuilder();
                sb.append(f49a == a.DISABLE ? "Disable " : "Pre_disable ");
                sb.append("mode finished. Enter enable mode.");
                i.f(sb.toString());
                f49a = a.ENABLE;
                c(false);
                s.a().e();
                g = f;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void a(String str, String str2, Throwable th) {
        synchronized (t.class) {
            a(str2, th);
            if (a(th) && str2 != null && str2.equals(f.f32b[f])) {
                f();
                if (g == f) {
                    s.a().b(false);
                    p.a().c();
                }
                if (f49a == a.ENABLE) {
                    f49a = a.PRE_DISABLE;
                    i.f("enter pre_disable mode");
                } else if (f49a == a.PRE_DISABLE) {
                    f49a = a.DISABLE;
                    i.f("enter disable mode");
                    c(true);
                    h(str);
                    s.a().g(str);
                }
            }
        }
    }

    private static void a(String str, Throwable th) {
        com.alibaba.sdk.android.httpdns.d.a a2 = com.alibaba.sdk.android.httpdns.d.a.a();
        if (a2 != null) {
            int a3 = com.alibaba.sdk.android.httpdns.d.b.a(th);
            a2.b(str, String.valueOf(a3), com.alibaba.sdk.android.httpdns.d.b.m34a(th), com.alibaba.sdk.android.httpdns.d.b.b());
        }
    }

    private static boolean a(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            return true;
        }
        if (th instanceof h) {
            h hVar = (h) th;
            if (hVar.getErrorCode() == 403 && hVar.getMessage().equals("ServiceLevelDeny")) {
                return true;
            }
        }
        return false;
    }

    static void b(int i) {
        if (a == null || i < 0 || i >= f.f32b.length) {
            return;
        }
        f = i;
        SharedPreferences.Editor edit = a.edit();
        edit.putInt("activiate_ip_index", i);
        edit.putLong("activiated_ip_index_modified_time", System.currentTimeMillis());
        edit.commit();
    }

    private static void b(String str, String str2, long j) {
        com.alibaba.sdk.android.httpdns.d.a a2 = com.alibaba.sdk.android.httpdns.d.a.a();
        if (a2 != null) {
            a2.b(str2, j, com.alibaba.sdk.android.httpdns.d.b.b());
        }
    }

    static synchronized void c(boolean z) {
        synchronized (t.class) {
            if (f51g != z) {
                f51g = z;
                if (a != null) {
                    SharedPreferences.Editor edit = a.edit();
                    edit.putBoolean("status", f51g);
                    edit.putLong("disable_modified_time", System.currentTimeMillis());
                    edit.commit();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized boolean e() {
        boolean z;
        synchronized (t.class) {
            z = f51g;
        }
        return z;
    }

    private static void f() {
        f = f == f.f32b.length + (-1) ? 0 : f + 1;
        b(f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void g() {
        synchronized (t.class) {
            b(0);
            g = f;
            s.a().b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void h() {
        synchronized (t.class) {
            s.a().b(true);
        }
    }

    private static void h(String str) {
        com.alibaba.sdk.android.httpdns.d.a a2 = com.alibaba.sdk.android.httpdns.d.a.a();
        if (a2 != null) {
            String f2 = p.a().f();
            int i = f;
            if (i == 0) {
                i = f.f32b.length;
            }
            int i2 = i - 1;
            int length = i2 == 0 ? f.f32b.length - 1 : i2 - 1;
            if (i2 < 0 || i2 >= f.f32b.length || length < 0 || length >= f.f32b.length) {
                return;
            }
            String str2 = f.f32b[i2];
            a2.b(str, f2, f.f32b[length] + "," + str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void init(Context context) {
        synchronized (t.class) {
            if (!f50e) {
                synchronized (t.class) {
                    if (!f50e) {
                        if (context != null) {
                            a = context.getSharedPreferences("httpdns_config_cache", 0);
                        }
                        f51g = a.getBoolean("status", false);
                        f = a.getInt("activiate_ip_index", 0);
                        g = f;
                        e = a.getLong("disable_modified_time", 0L);
                        if (System.currentTimeMillis() - e >= 86400000) {
                            c(false);
                        }
                        f49a = f51g ? a.DISABLE : a.ENABLE;
                        f50e = true;
                    }
                }
            }
        }
    }

    public static void reportHttpDnsSuccess(String str, int i) {
        com.alibaba.sdk.android.httpdns.d.a a2 = com.alibaba.sdk.android.httpdns.d.a.a();
        if (a2 != null) {
            a2.a(str, i, com.alibaba.sdk.android.httpdns.d.b.b(), com.alibaba.sdk.android.httpdns.b.b.m19a() ? 1 : 0);
        }
    }
}
