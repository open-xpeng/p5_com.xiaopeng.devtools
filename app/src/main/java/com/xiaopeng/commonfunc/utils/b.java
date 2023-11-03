package com.xiaopeng.commonfunc.utils;

import android.content.Context;
import com.xiaopeng.aftersales.AfterSalesManager;

/* compiled from: AfterSalesHelper.java */
/* loaded from: classes11.dex */
public class b {
    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static AfterSalesManager eo() {
        return (AfterSalesManager) sContext.getSystemService("xiaopeng_aftersales");
    }
}
