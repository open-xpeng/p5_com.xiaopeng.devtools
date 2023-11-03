package com.xiaopeng.xui.widget.dialogview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.XFrameLayout;

/* loaded from: classes13.dex */
class XDialogMessage extends XFrameLayout {
    private ImageView PB;
    private TextView aiu;

    public XDialogMessage(Context context) {
        this(context, null);
    }

    public XDialogMessage(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XDialogMessage(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XDialogMessage(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        LayoutInflater.from(context).inflate(R.layout.x_dialog_message, this);
        nN();
    }

    private void nN() {
        this.aiu = (TextView) findViewById(R.id.x_dialog_message);
        this.PB = (ImageView) findViewById(R.id.x_dialog_icon);
    }

    public void eP(@StringRes int i) {
        this.aiu.setText(i);
        rO();
    }

    public void setMessage(@Nullable CharSequence charSequence) {
        this.aiu.setText(charSequence);
        rO();
    }

    private void rO() {
        if (this.aiu.getWidth() > 0) {
            rP();
        } else {
            this.aiu.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.xiaopeng.xui.widget.dialogview.XDialogMessage.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    XDialogMessage.this.aiu.getViewTreeObserver().removeOnPreDrawListener(this);
                    XDialogMessage.this.rP();
                    return true;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rP() {
        if (this.aiu.getLineCount() < 2) {
            this.aiu.setGravity(1);
        } else {
            this.aiu.setGravity(8388611);
        }
        this.aiu.setVisibility(0);
    }
}
