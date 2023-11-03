package com.xiaopeng.xui.view.a;

import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;

/* compiled from: XViewDelegatePart.java */
/* loaded from: classes13.dex */
public abstract class b {
    public abstract void onAttachedToWindow();

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onDetachedFromWindow();

    public static b b(@NonNull TextView textView, AttributeSet attributeSet, int i, int i2) {
        return a.a(textView, attributeSet, i, i2);
    }
}
