package com.xiaopeng.commonfunc.utils;

import android.content.Context;
import android.content.Intent;

/* compiled from: ActivityUtil.java */
/* loaded from: classes11.dex */
public class a {
    public static void e(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.setFlags(335544320);
        context.startActivity(intent);
    }
}
