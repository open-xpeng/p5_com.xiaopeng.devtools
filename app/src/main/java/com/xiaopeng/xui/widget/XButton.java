package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xui.d.b;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XButton extends AppCompatButton implements a {
    protected com.xiaopeng.xui.view.a abr;
    private Rect acK;

    public XButton(Context context) {
        super(context);
    }

    public XButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.abr = com.xiaopeng.xui.view.a.b(this, attributeSet);
        init(attributeSet);
    }

    public XButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        c(this, attributeSet);
        this.acK = b.a(this, attributeSet);
        if (this.abr != null && this.abr.qP() != null) {
            this.abr.qP().setCallback(new ThemeViewModel.OnCallback() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XButton$TIa5cM5ov-Rnf0o4YAFE2XlT8NU
                @Override // com.xiaopeng.libtheme.ThemeViewModel.OnCallback
                public final void onThemeChanged() {
                    XButton.this.er();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void er() {
        post(new Runnable() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XButton$Rz8hmxuE3X2MnrG1-5PILLIUr10
            @Override // java.lang.Runnable
            public final void run() {
                XButton.this.rf();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void rf() {
        if (this.acK != null) {
            fl("XButton change theme reset backgroundPadding");
            this.acK = b.b(this, this.acK.left, this.acK.top, this.acK.right, this.acK.bottom);
        }
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

    protected void finalize() throws Throwable {
        super.finalize();
        qX();
    }
}
