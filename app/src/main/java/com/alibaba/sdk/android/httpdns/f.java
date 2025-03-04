package com.alibaba.sdk.android.httpdns;

import com.alibaba.sdk.android.httpdns.probe.IPProbeItem;
import java.util.List;

/* loaded from: classes11.dex */
class f {

    /* renamed from: a  reason: collision with other field name */
    static String f30a;

    /* renamed from: b  reason: collision with other field name */
    static String[] f32b = {"203.107.1.1"};
    static final String[] c = {"203.107.1.97", "203.107.1.100", "httpdns-sc.aliyuncs.com"};
    static final String[] d = new String[0];
    static String b = "80";
    static String PROTOCOL = "http://";
    static int a = 15000;

    /* renamed from: a  reason: collision with other field name */
    static List<IPProbeItem> f31a = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void a(List<IPProbeItem> list) {
        synchronized (f.class) {
            f31a = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized boolean a(String[] strArr) {
        boolean z;
        synchronized (f.class) {
            if (strArr != null) {
                if (strArr.length != 0) {
                    f32b = strArr;
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void c(String str) {
        synchronized (f.class) {
            f30a = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void setHTTPSRequestEnabled(boolean z) {
        String str;
        synchronized (f.class) {
            try {
                if (z) {
                    PROTOCOL = "https://";
                    str = "443";
                } else {
                    PROTOCOL = "http://";
                    str = "80";
                }
                b = str;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void setTimeoutInterval(int i) {
        synchronized (f.class) {
            if (i > 0) {
                a = i;
            }
        }
    }
}
