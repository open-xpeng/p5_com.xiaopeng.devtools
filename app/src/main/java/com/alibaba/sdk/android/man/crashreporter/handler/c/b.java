package com.alibaba.sdk.android.man.crashreporter.handler.c;

import com.alibaba.sdk.android.man.crashreporter.e.h;

/* loaded from: classes11.dex */
public class b {
    public static void a(String str, int i, int i2) {
        String str2 = "";
        if (i == 0) {
            str2 = "CRASH_HANDLE";
        } else if (i == 1) {
            str2 = "NATIVE_CRASH_HANDLE";
        } else if (i == 2) {
            str2 = "ANR_HANDLE";
        }
        Class<?> cls = null;
        try {
            try {
                cls = Class.forName("com.taobao.statistis.TBS$Ext");
            } catch (ClassNotFoundException e) {
            }
            if (cls == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("com.taobao.stdatistis.TBS.Ext is null");
                return;
            }
            h.a((Class) cls, "commitEvent", new Object[]{"", Integer.valueOf(i2), str, str2}, new Class[0]);
            com.alibaba.sdk.android.man.crashreporter.b.a.e("commitEvent call succ");
        } catch (Exception e2) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("watchDog error.", e2);
        }
    }
}
