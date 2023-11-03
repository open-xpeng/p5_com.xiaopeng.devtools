package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.vui.commons.VuiUpdateType;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XFrameLayout extends FrameLayout implements a {
    protected com.xiaopeng.xui.view.a abr;

    public XFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public XFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i, i2);
        c(this, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.abr != null) {
            this.abr.onConfigurationChanged(configuration);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.abr != null) {
            this.abr.onAttachedToWindow();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.abr != null) {
            this.abr.onDetachedFromWindow();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        e(this, i);
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        super.setSelected(z);
        e(this, z);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        a(this, VuiUpdateType.UPDATE_VIEW);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        a(this, VuiUpdateType.UPDATE_VIEW);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        qX();
    }
}