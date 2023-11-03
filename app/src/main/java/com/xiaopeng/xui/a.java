package com.xiaopeng.xui;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.xiaopeng.xui.drawable.shimmer.XShimmer;

/* compiled from: Xui.java */
/* loaded from: classes13.dex */
public class a {
    private static Application ZF;
    private static boolean ZG;
    private static boolean ZH;
    private static boolean ZI;

    public static void a(Application application) {
        ZF = application;
        Log.i("xpui", "0.7.10_HEAD_bb7632f_2021/07/23 03:21:41");
        XShimmer.msGlobalEnable = false;
    }

    public static Context getContext() {
        if (ZF == null) {
            throw new RuntimeException("Xui must be call Xui#init()!");
        }
        return ZF;
    }

    public static boolean qn() {
        return ZG;
    }

    public static boolean qo() {
        return ZH;
    }

    public static boolean qp() {
        return ZI;
    }
}
