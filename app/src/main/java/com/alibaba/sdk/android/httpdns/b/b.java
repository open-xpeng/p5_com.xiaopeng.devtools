package com.alibaba.sdk.android.httpdns.b;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class b {
    private static f a;

    /* renamed from: a  reason: collision with other field name */
    private static com.alibaba.sdk.android.httpdns.c.a f22a;

    /* renamed from: a  reason: collision with other field name */
    private static boolean f23a = false;

    public static List<e> a() {
        ArrayList arrayList = new ArrayList();
        if (f23a) {
            arrayList.addAll(a.a());
            return arrayList;
        }
        return arrayList;
    }

    public static void a(Context context) {
        if (context != null) {
            f22a.m24b(context);
        }
    }

    public static void a(Context context, com.alibaba.sdk.android.httpdns.c.a aVar) {
        a = new a(context);
        f22a = aVar;
        if (f22a == null) {
            f22a = com.alibaba.sdk.android.httpdns.c.a.a();
        }
    }

    public static void a(e eVar) {
        if (eVar == null) {
            return;
        }
        a.a(eVar);
    }

    public static void a(boolean z) {
        f23a = z;
    }

    /* renamed from: a  reason: collision with other method in class */
    public static boolean m19a() {
        return f23a;
    }

    public static void b(e eVar) {
        if (eVar == null) {
            return;
        }
        a.b(eVar);
    }

    public static String g() {
        return f22a.g();
    }

    public static void init(Context context) {
        a(context, null);
    }
}
