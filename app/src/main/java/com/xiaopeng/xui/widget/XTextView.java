package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XTextView extends AppCompatTextView implements a {
    protected com.xiaopeng.xui.view.a abr;

    public XTextView(Context context) {
        super(context);
    }

    public XTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.abr = com.xiaopeng.xui.view.a.b(this, attributeSet);
        c(this, attributeSet);
    }

    public XTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i);
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

    @Override // android.widget.TextView, android.view.View
    public void setSelected(boolean z) {
        super.setSelected(z);
        e(this, z);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        qX();
    }
}
