package com.xiaopeng.xui.c;

import android.content.Context;
import android.content.res.Configuration;
import android.view.Window;
import com.xiaopeng.libtheme.ThemeManager;

/* compiled from: XThemeManager.java */
/* loaded from: classes13.dex */
public class a {
    public static void setWindowBackgroundResource(Configuration configuration, Window window, int i) {
        ThemeManager.setWindowBackgroundResource(configuration, window, i);
    }

    public static boolean isThemeChanged(Configuration configuration) {
        return ThemeManager.isThemeChanged(configuration);
    }

    public static boolean aB(Context context) {
        return isNightMode(context);
    }

    public static boolean a(Configuration configuration) {
        return configuration != null && (configuration.uiMode & 48) == 32;
    }

    public static boolean isNightMode(Context context) {
        return ThemeManager.isNightMode(context);
    }
}
