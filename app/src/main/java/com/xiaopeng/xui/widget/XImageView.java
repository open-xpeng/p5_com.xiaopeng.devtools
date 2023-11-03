package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XImageView extends AppCompatImageView implements a {
    protected com.xiaopeng.xui.view.a abr;

    public XImageView(Context context) {
        super(context);
    }

    public XImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.abr = com.xiaopeng.xui.view.a.b(this, attributeSet);
        c(this, attributeSet);
    }

    public XImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i);
        c(this, attributeSet);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.abr != null && this.abr.qP() != null) {
            this.abr.qP().setImageResource(i);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.abr != null) {
            this.abr.onConfigurationChanged(configuration);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.abr != null) {
            this.abr.onAttachedToWindow();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.abr != null) {
            this.abr.onDetachedFromWindow();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        e(this, i);
    }

    @Override // android.widget.ImageView, android.view.View
    public void setSelected(boolean z) {
        super.setSelected(z);
        e(this, z);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        qX();
    }
}
