package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.annotation.DrawableRes;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.drawable.progress.XCircularProgressBgDrawable;
import com.xiaopeng.xui.drawable.progress.XCircularProgressIndeterminateDrawable;
import com.xiaopeng.xui.drawable.progress.XCircularProgressPgDrawable;
import com.xiaopeng.xui.view.a;

/* loaded from: classes13.dex */
public class XCircularProgressBar extends ProgressBar {
    private static final int[] acM = {R.attr.progress_state_playing_stop};
    private static final int[] acN = {R.attr.progress_state_playing_pause};
    private static final int[] acO = {R.attr.progress_state_paused};
    private static final int[] acP = {R.attr.progress_state_start_download};
    private a abr;
    private boolean acQ;
    @DrawableRes
    private int acR;
    @DrawableRes
    private int acS;
    @DrawableRes
    private int acT;
    @DrawableRes
    private int acU;
    @DrawableRes
    private int acV;
    @DrawableRes
    private int acW;
    private XCircularProgressPgDrawable acX;
    private XCircularProgressBgDrawable acY;
    private XCircularProgressIndeterminateDrawable acZ;
    private Drawable mIndicatorPause;
    private Drawable mIndicatorPlay;
    private Drawable mIndicatorStart;
    private Drawable mIndicatorStop;
    private int mIndicatorType;
    private float mInset;
    private float mLightRadius;
    private float mStrokeWidth;

    public XCircularProgressBar(Context context) {
        this(context, null);
    }

    public XCircularProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.style.XProgressBar_Circular_Medium);
    }

    public XCircularProgressBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XProgressBar_Circular_Medium);
    }

    public XCircularProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.acQ = false;
        a(attributeSet, i, i2);
    }

    public void setIndicatorType(int i) {
        if (this.mIndicatorType == 3) {
            super.setProgress(getMin());
        }
        if (i == 3) {
            super.setProgress(getMax());
        }
        this.mIndicatorType = i;
        refreshDrawableState();
    }

    public int getIndicatorType() {
        return this.mIndicatorType;
    }

    public void setEnableIndicator(boolean z) {
        this.acQ = z;
        refreshDrawableState();
    }

    private void a(AttributeSet attributeSet, int i, int i2) {
        if (attributeSet == null) {
            return;
        }
        this.abr = a.b(this, attributeSet);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.XCircularProgressBar, i, i2);
        this.acQ = obtainStyledAttributes.getBoolean(R.styleable.XCircularProgressBar_progress_enableIndicator, false);
        this.mIndicatorType = obtainStyledAttributes.getInt(R.styleable.XCircularProgressBar_progress_indicatorType, 0);
        this.mInset = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XCircularProgressBar_progress_inset, 0);
        this.mStrokeWidth = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.XCircularProgressBar_progress_strokeWidth, 0);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.XCircularProgressBar_android_enabled, true);
        this.mLightRadius = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.XCircularProgressBar_progress_light_radius, 0);
        this.mIndicatorPlay = obtainStyledAttributes.getDrawable(R.styleable.XCircularProgressBar_progress_indicator_play);
        this.mIndicatorPause = obtainStyledAttributes.getDrawable(R.styleable.XCircularProgressBar_progress_indicator_pause);
        this.mIndicatorStop = obtainStyledAttributes.getDrawable(R.styleable.XCircularProgressBar_progress_indicator_stop);
        this.mIndicatorStart = obtainStyledAttributes.getDrawable(R.styleable.XCircularProgressBar_progress_indicator_start);
        this.acR = obtainStyledAttributes.getResourceId(R.styleable.XCircularProgressBar_progress_indicator_play, 0);
        this.acT = obtainStyledAttributes.getResourceId(R.styleable.XCircularProgressBar_progress_indicator_pause, 0);
        this.acS = obtainStyledAttributes.getResourceId(R.styleable.XCircularProgressBar_progress_indicator_stop, 0);
        this.acU = obtainStyledAttributes.getResourceId(R.styleable.XCircularProgressBar_progress_indicator_start, 0);
        this.acV = obtainStyledAttributes.getResourceId(R.styleable.XCircularProgressBar_android_indeterminateDrawable, 0);
        this.acW = obtainStyledAttributes.getResourceId(R.styleable.XCircularProgressBar_android_progressDrawable, 0);
        obtainStyledAttributes.recycle();
        setEnabled(z);
        if (this.mIndicatorType == 3) {
            super.setProgress(getMax());
        }
        if (this.abr != null && this.abr.qP() != null) {
            this.abr.qP().setCallback(new ThemeViewModel.OnCallback() { // from class: com.xiaopeng.xui.widget.-$$Lambda$qjPnpsY2uI5Qjnz_Xur7lRYMrRw
                @Override // com.xiaopeng.libtheme.ThemeViewModel.OnCallback
                public final void onThemeChanged() {
                    XCircularProgressBar.this.ri();
                }
            });
        }
        rg();
        rh();
    }

    private void a(Drawable drawable) {
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908301);
            if (findDrawableByLayerId instanceof XCircularProgressPgDrawable) {
                this.acX = (XCircularProgressPgDrawable) findDrawableByLayerId;
            }
            Drawable findDrawableByLayerId2 = layerDrawable.findDrawableByLayerId(16908288);
            if (findDrawableByLayerId2 instanceof XCircularProgressBgDrawable) {
                this.acY = (XCircularProgressBgDrawable) findDrawableByLayerId2;
            }
        }
    }

    private void b(Drawable drawable) {
        if (drawable instanceof RotateDrawable) {
            Drawable drawable2 = ((RotateDrawable) drawable).getDrawable();
            if (drawable2 instanceof XCircularProgressIndeterminateDrawable) {
                this.acZ = (XCircularProgressIndeterminateDrawable) drawable2;
            }
        }
    }

    private void rg() {
        if (this.acX != null) {
            this.acX.setInset(this.mInset);
            this.acX.setStrokeWidth(this.mStrokeWidth);
            this.acX.setLightRadius(this.mLightRadius);
        }
        if (this.acY != null) {
            this.acY.setInset(this.mInset);
            this.acY.setStrokeWidth(this.mStrokeWidth);
            this.acY.setIndicatorPlay(this.mIndicatorPlay);
            this.acY.setIndicatorPause(this.mIndicatorPause);
            this.acY.setIndicatorStop(this.mIndicatorStop);
            this.acY.setIndicatorStart(this.mIndicatorStart);
        }
    }

    private void rh() {
        if (this.acZ != null) {
            this.acZ.setInset(this.mInset);
            this.acZ.setStrokeWidth(this.mStrokeWidth);
            this.acZ.setLightRadius(this.mLightRadius);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ri() {
        if (this.acU != 0) {
            this.mIndicatorStart = getDrawable(this.acU);
        }
        if (this.acT != 0) {
            this.mIndicatorPause = getDrawable(this.acT);
        }
        if (this.acS != 0) {
            this.mIndicatorStop = getDrawable(this.acS);
        }
        if (this.acR != 0) {
            this.mIndicatorPlay = getDrawable(this.acR);
        }
        if (this.acV != 0) {
            setIndeterminateDrawable(getDrawable(this.acV));
        }
        if (this.acW != 0) {
            setProgressDrawable(getDrawable(this.acW));
        }
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        if (this.mIndicatorType == 3) {
            i = getMax();
        }
        super.setProgress(i);
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable) {
        a(drawable);
        rg();
        super.setProgressDrawable(drawable);
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable) {
        b(drawable);
        rh();
        super.setIndeterminateDrawable(drawable);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.abr.onConfigurationChanged(configuration);
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.abr.onAttachedToWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.abr.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z) {
        super.setIndeterminate(z);
        if (!z) {
            refreshDrawableState();
        }
    }

    @Override // android.view.View
    protected int[] onCreateDrawableState(int i) {
        if (isIndeterminate() || !this.acQ) {
            return super.onCreateDrawableState(i);
        }
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mIndicatorType != 0) {
            if (this.mIndicatorType == 1) {
                mergeDrawableStates(onCreateDrawableState, acM);
            } else if (this.mIndicatorType == 2) {
                mergeDrawableStates(onCreateDrawableState, acN);
            } else if (this.mIndicatorType == 3) {
                mergeDrawableStates(onCreateDrawableState, acP);
            }
        } else {
            mergeDrawableStates(onCreateDrawableState, acO);
        }
        return onCreateDrawableState;
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (isEnabled()) {
            setAlpha(1.0f);
        } else {
            setAlpha(0.16f);
        }
    }

    public static Drawable getDrawable(@DrawableRes int i) {
        return com.xiaopeng.xui.a.getContext().getResources().getDrawable(i, null);
    }
}
