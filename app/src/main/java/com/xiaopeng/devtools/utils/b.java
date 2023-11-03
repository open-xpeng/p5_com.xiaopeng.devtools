package com.xiaopeng.devtools.utils;

import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.devtools.MyApplication;

/* compiled from: AfterSalesHelper.java */
/* loaded from: classes12.dex */
public class b {
    public static void recordRepairModeAction(String str, String str2) {
        ((AfterSalesManager) MyApplication.getContext().getSystemService("xiaopeng_aftersales")).recordRepairModeAction(str.replace("\r\n", "").replace("\n", "").replace("\r", "").replace("@", "").replace("#", ""), str2);
    }
}
