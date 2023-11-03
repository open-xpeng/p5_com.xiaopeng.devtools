package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.ToggleButton;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XToggleButton extends ToggleButton implements a {
    protected com.xiaopeng.xui.view.a abr;

    public XToggleButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i, i2);
        c(this, attributeSet);
    }

    public XToggleButton(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XButton_CompoundButton_ToggleButton_Fill);
    }

    public XToggleButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.style.XButton_CompoundButton_ToggleButton_Fill, R.style.XButton_CompoundButton_ToggleButton_Fill);
    }

    public XToggleButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.abr != null) {
            this.abr.onConfigurationChanged(configuration);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.abr != null) {
            this.abr.onAttachedToWindow();
        }
    }

    @Override // android.view.View
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

    @Override // android.widget.TextView, android.view.View
    public void setSelected(boolean z) {
        super.setSelected(z);
        e(this, z);
    }

    @Override // android.widget.ToggleButton, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        boolean isChecked = isChecked();
        super.setChecked(z);
        if (isChecked != z) {
            l(this);
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        qX();
    }
}
