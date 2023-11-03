package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.xiaopeng.xui.view.a;

/* loaded from: classes13.dex */
public class XProgressBar extends ProgressBar {
    protected a abr;

    public XProgressBar(Context context) {
        super(context);
        a(null, 0, 0);
    }

    public XProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0, 0);
    }

    public XProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i, 0);
    }

    private void a(AttributeSet attributeSet, int i, int i2) {
        this.abr = a.a(this, attributeSet, i, i2);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.abr != null) {
            this.abr.onConfigurationChanged(configuration);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.abr != null) {
            this.abr.onAttachedToWindow();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.abr != null) {
            this.abr.onDetachedFromWindow();
        }
    }
}
