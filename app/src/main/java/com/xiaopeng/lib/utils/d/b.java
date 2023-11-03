package com.xiaopeng.lib.utils.d;

import android.content.Context;
import android.util.DisplayMetrics;

/* compiled from: UIUtils.java */
/* loaded from: classes12.dex */
public class b {
    private static int height;
    private static int width;

    public static void init(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    public static int a(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
