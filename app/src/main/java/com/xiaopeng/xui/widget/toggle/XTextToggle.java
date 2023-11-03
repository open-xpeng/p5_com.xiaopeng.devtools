package com.xiaopeng.xui.widget.toggle;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.view.a;
import com.xiaopeng.xui.view.b.b;

@Deprecated
/* loaded from: classes13.dex */
public class XTextToggle extends XToggleLayout {
    private CheckedTextView akZ;
    private int ala;
    private int alb;
    private CharSequence mTextOff;
    private CharSequence mTextOn;

    public XTextToggle(Context context) {
        this(context, null);
    }

    public XTextToggle(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.style.XToggleLayout_Fill_TextToggle);
    }

    public XTextToggle(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XToggleLayout_Fill_TextToggle);
    }

    public XTextToggle(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.XTextToggle, i, i2);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XTextToggle_android_textSize, 15);
        final b a = b.a(obtainStyledAttributes, R.styleable.XTextToggle_android_textSize);
        if (a != null && this.abr != null) {
            this.abr.a(new a.InterfaceC0085a() { // from class: com.xiaopeng.xui.widget.toggle.-$$Lambda$XTextToggle$u5RLTmPAwFkkmYvqtai-hkRRxzU
                @Override // com.xiaopeng.xui.view.a.InterfaceC0085a
                public final void onFontScaleChanged() {
                    XTextToggle.this.a(a);
                }
            });
        }
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.XTextToggle_android_textColor);
        this.ala = obtainStyledAttributes.getResourceId(R.styleable.XTextToggle_android_textColor, 0);
        this.akZ = new CheckedTextView(context);
        this.akZ.setTextColor(colorStateList);
        this.akZ.setTextSize(0, dimensionPixelSize);
        this.akZ.setGravity(16);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(this.akZ, layoutParams);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.XTextToggle_android_drawableStart);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XTextToggle_android_drawablePadding, 0);
        if (drawable != null) {
            this.akZ.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
            this.akZ.setCompoundDrawablePadding(dimensionPixelSize2);
        }
        this.alb = obtainStyledAttributes.getResourceId(R.styleable.XTextToggle_android_drawableStart, 0);
        this.mTextOn = obtainStyledAttributes.getText(R.styleable.XTextToggle_android_textOn);
        this.mTextOff = obtainStyledAttributes.getText(R.styleable.XTextToggle_android_textOff);
        sg();
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(b bVar) {
        bVar.a(this.akZ);
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public void setTextOn(CharSequence charSequence) {
        this.mTextOn = charSequence;
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public void setTextOff(CharSequence charSequence) {
        this.mTextOff = charSequence;
    }

    @Override // com.xiaopeng.xui.widget.toggle.XToggleLayout, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        sg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.toggle.XToggleLayout
    public void ri() {
        if (this.ala != 0) {
            this.akZ.setTextColor(getContext().getColorStateList(this.ala));
        }
        if (this.alb != 0) {
            this.akZ.setCompoundDrawablesRelativeWithIntrinsicBounds(getContext().getDrawable(this.alb), (Drawable) null, (Drawable) null, (Drawable) null);
        }
        super.ri();
    }

    private void sg() {
        boolean isChecked = isChecked();
        if (isChecked && this.mTextOn != null) {
            setText(this.mTextOn);
        } else if (!isChecked && this.mTextOff != null) {
            setText(this.mTextOff);
        }
    }

    public void setText(CharSequence charSequence) {
        if (this.akZ != null) {
            this.akZ.setText(charSequence);
        }
    }
}
