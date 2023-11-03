package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XCompoundButton extends CompoundButton implements a {
    protected com.xiaopeng.xui.view.a abr;
    private boolean ada;

    public XCompoundButton(Context context) {
        this(context, null);
    }

    public XCompoundButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XCompoundButton(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XCompoundButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i, i2);
        c(this, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.abr != null) {
            this.abr.onConfigurationChanged(configuration);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.abr != null) {
            this.abr.onAttachedToWindow();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onDetachedFromWindow() {
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

    @Override // android.widget.CompoundButton, android.widget.Checkable
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

    @Override // android.widget.CompoundButton, android.view.View
    public boolean performClick() {
        setFromUser(true);
        boolean performClick = super.performClick();
        setFromUser(false);
        return performClick;
    }

    private void setFromUser(boolean z) {
        this.ada = z;
    }
}
