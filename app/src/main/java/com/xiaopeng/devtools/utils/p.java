package com.xiaopeng.devtools.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/* compiled from: ScreenUtils.java */
/* loaded from: classes12.dex */
public class p {
    private static int width = -1;
    private static int height = -1;

    private static void init(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    public static int ae(Context context) {
        if (height < 0) {
            init(context);
        }
        return height;
    }
}
