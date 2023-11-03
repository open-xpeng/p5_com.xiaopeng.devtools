package com.xiaopeng.xui.widget.toggle;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.vui.commons.VuiElementType;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.drawable.XLoadingDrawable;
import com.xiaopeng.xui.widget.XRelativeLayout;

/* loaded from: classes13.dex */
public class XToggleLayout extends XRelativeLayout implements Checkable {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private boolean alc;
    private boolean ald;
    private boolean ale;
    private XLoadingDrawable alf;
    private a alg;
    private float alh;
    private Drawable ali;
    private int alj;
    private boolean mChecked;
    private boolean mEnabled;

    /* loaded from: classes13.dex */
    public interface a {
        void a(XToggleLayout xToggleLayout, boolean z);

        boolean a(XToggleLayout xToggleLayout);
    }

    public XToggleLayout(Context context) {
        this(context, null);
    }

    public XToggleLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.style.XToggleLayout_Fill);
    }

    public XToggleLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XToggleLayout_Fill);
    }

    public XToggleLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mEnabled = true;
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        setClickable(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XToggleLayout, i, i2);
        this.mChecked = obtainStyledAttributes.getBoolean(R.styleable.XToggleLayout_android_checked, false);
        this.mEnabled = obtainStyledAttributes.getBoolean(R.styleable.XToggleLayout_android_enabled, true);
        super.setEnabled(this.mEnabled);
        this.alh = obtainStyledAttributes.getFloat(R.styleable.XToggleLayout_android_disabledAlpha, 0.5f);
        setLoading(obtainStyledAttributes.getBoolean(R.styleable.XToggleLayout_loading, false));
        this.alj = obtainStyledAttributes.getResourceId(R.styleable.XToggleLayout_android_background, 0);
        obtainStyledAttributes.recycle();
        if (this.abr != null && this.abr.qP() != null) {
            this.abr.qP().setCallback(new ThemeViewModel.OnCallback() { // from class: com.xiaopeng.xui.widget.toggle.-$$Lambda$eP8l9DEpzq_0sU46p0hHiuy4fuw
                @Override // com.xiaopeng.libtheme.ThemeViewModel.OnCallback
                public final void onThemeChanged() {
                    XToggleLayout.this.ri();
                }
            });
        }
        a(VuiElementType.SWITCH);
    }

    public void setLoading(boolean z) {
        if (this.ale != z) {
            this.ale = z;
            if (this.ale) {
                setEnabled(false);
                if (this.alf == null) {
                    this.alf = new XLoadingDrawable();
                    this.alf.onConfigurationChanged(getContext(), null);
                    this.alf.setCallback(this);
                    this.alf.setType(1);
                    int measuredWidth = getMeasuredWidth();
                    int measuredHeight = getMeasuredHeight();
                    if (measuredWidth != 0 && measuredHeight != 0) {
                        this.alf.setBounds(0, 0, measuredWidth, measuredHeight);
                    }
                }
                invalidate();
                return;
            }
            if (this.alf != null) {
                this.alf.cancelAnimations();
            }
            setEnabled(true);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.alf != null) {
            this.alf.setBounds(0, 0, i, i2);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        c(getBackground());
        sh();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
        c(drawable);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.ali != null) {
            this.ali.setAlpha(isEnabled() ? 255 : (int) (255.0f * this.alh));
        }
    }

    private void sh() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof Checkable) {
                ((Checkable) childAt).setChecked(this.mChecked);
                childAt.setEnabled(this.mEnabled);
            }
        }
    }

    private void si() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof Checkable) {
                ((Checkable) childAt).setChecked(this.mChecked);
            }
        }
    }

    private void sj() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof Checkable) {
                childAt.setEnabled(this.mEnabled);
            }
        }
    }

    @Override // android.view.View
    protected boolean verifyDrawable(@NonNull Drawable drawable) {
        return drawable == this.alf || super.verifyDrawable(drawable);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.ale) {
            if (this.alf != null) {
                this.alf.draw(canvas);
                return;
            }
            return;
        }
        super.dispatchDraw(canvas);
    }

    public void setOnCheckedChangeListener(@Nullable a aVar) {
        this.alg = aVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    private void c(Drawable drawable) {
        if (drawable instanceof LayerDrawable) {
            this.ali = ((LayerDrawable) drawable).findDrawableByLayerId(16908311);
        } else {
            this.ali = null;
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.mEnabled != z) {
            this.mEnabled = z;
            sj();
        }
    }

    public void setChecked(boolean z) {
        if (this.ald) {
            throw new IllegalStateException("Cannot change check state in onInterceptClickCheck");
        }
        if (this.mChecked != z) {
            this.mChecked = z;
            si();
            refreshDrawableState();
            if (this.alc) {
                return;
            }
            this.alc = true;
            if (this.alg != null) {
                this.alg.a(this, this.mChecked);
            }
            this.alc = false;
            l(this);
        }
    }

    @Override // android.view.View
    public boolean performClick() {
        boolean z;
        this.ald = true;
        if (this.alg != null) {
            z = this.alg.a(this);
        } else {
            z = false;
        }
        this.ald = false;
        if (!z) {
            toggle();
        }
        boolean performClick = super.performClick();
        if (!performClick) {
            playSoundEffect(0);
        }
        return performClick;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.mChecked);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XRelativeLayout, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.alf != null) {
            this.alf.onConfigurationChanged(getContext(), configuration);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XRelativeLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.alf != null) {
            this.alf.onConfigurationChanged(getContext(), getResources().getConfiguration());
            this.alf.cancelAnimations();
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XRelativeLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.alf != null) {
            this.alf.cancelAnimations();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ri() {
        setBackground(getContext().getDrawable(this.alj));
        refreshDrawableState();
    }
}
