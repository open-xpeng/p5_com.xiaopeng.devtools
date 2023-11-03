package com.xiaopeng.xui.view;

import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import com.xiaopeng.libtheme.ThemeViewModel;

/* compiled from: XViewDelegate.java */
/* loaded from: classes13.dex */
public abstract class a {

    /* compiled from: XViewDelegate.java */
    /* renamed from: com.xiaopeng.xui.view.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public interface InterfaceC0085a {
        void onFontScaleChanged();
    }

    public abstract void a(InterfaceC0085a interfaceC0085a);

    public abstract void onAttachedToWindow();

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onDetachedFromWindow();

    public abstract void onWindowFocusChanged(boolean z);

    public abstract ThemeViewModel qP();

    public static a b(View view, AttributeSet attributeSet) {
        return a(view, attributeSet, 0, 0);
    }

    public static a a(View view, AttributeSet attributeSet, int i) {
        return a(view, attributeSet, i, 0);
    }

    public static a a(View view, AttributeSet attributeSet, int i, int i2) {
        return new b(view, attributeSet, i, i2, null);
    }

    public static a a(View view, AttributeSet attributeSet, int i, int i2, Object obj) {
        return new b(view, attributeSet, i, i2, obj);
    }
}
