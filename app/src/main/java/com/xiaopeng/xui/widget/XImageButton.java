package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageButton;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xui.d.b;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XImageButton extends AppCompatImageButton implements a {
    protected com.xiaopeng.xui.view.a abr;
    Rect acK;

    public XImageButton(Context context) {
        super(context);
    }

    public XImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.abr = com.xiaopeng.xui.view.a.b(this, attributeSet);
        init(attributeSet);
    }

    public XImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        c(this, attributeSet);
        this.acK = b.a(this, attributeSet);
        if (this.abr != null && this.abr.qP() != null) {
            this.abr.qP().setCallback(new ThemeViewModel.OnCallback() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XImageButton$5yMzYzmupQyo9sQB9vQcszjVbN0
                @Override // com.xiaopeng.libtheme.ThemeViewModel.OnCallback
                public final void onThemeChanged() {
                    XImageButton.this.er();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void er() {
        post(new Runnable() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XImageButton$8R_TSSzQHa1Od4yWm7U6TDoH_dM
            @Override // java.lang.Runnable
            public final void run() {
                XImageButton.this.rf();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void rf() {
        if (this.acK != null) {
            fl("XImageButton change theme reset backgroundPadding");
            this.acK = b.b(this, this.acK.left, this.acK.top, this.acK.right, this.acK.bottom);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView
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
