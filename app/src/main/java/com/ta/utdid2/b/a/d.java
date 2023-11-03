package com.ta.utdid2.b.a;

import java.lang.reflect.Method;

/* compiled from: DebugUtils.java */
/* loaded from: classes11.dex */
public class d {
    private static Class<?> a;

    /* renamed from: a  reason: collision with other field name */
    private static Method f124a;
    private static Method b;
    public static boolean e;

    public static int getInt(String str, int i) {
        a();
        try {
            return ((Integer) b.invoke(a, str, Integer.valueOf(i))).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return i;
        }
    }

    static {
        e = getInt("alidebug", 0) == 1;
        a = null;
        f124a = null;
        b = null;
    }

    private static void a() {
        try {
            if (a == null) {
                a = Class.forName("android.os.SystemProperties");
                f124a = a.getDeclaredMethod("get", String.class);
                b = a.getDeclaredMethod("getInt", String.class, Integer.TYPE);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
