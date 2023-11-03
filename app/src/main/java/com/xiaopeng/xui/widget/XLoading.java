package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.a.a;
import com.xiaopeng.xui.drawable.XLoadingDrawable;
import com.xiaopeng.xui.view.XView;

/* loaded from: classes13.dex */
public class XLoading extends XView {
    private a adw;
    private XLoadingDrawable adx;
    private boolean isDebug;

    public XLoading(Context context) {
        this(context, null);
    }

    public XLoading(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.style.XLoading_XLarge);
    }

    public XLoading(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XLoading_XLarge);
    }

    public XLoading(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.isDebug = false;
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        if (Build.VERSION.SDK_INT <= 26) {
            setLayerType(1, null);
        }
        this.adx = new XLoadingDrawable();
        this.adx.setCallback(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XLoading, i, i2);
        this.adx.setType(obtainStyledAttributes.getInt(R.styleable.XLoading_loading_type, 3));
        obtainStyledAttributes.recycle();
        if (this.isDebug) {
            this.adw = new a("XLoading");
        }
    }

    @Override // android.view.View
    protected boolean verifyDrawable(@NonNull Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.adx;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (getLayoutParams().width == -2 && getLayoutParams().height == -2) {
            setMeasuredDimension(216, 216);
        } else if (getLayoutParams().width == -2) {
            setMeasuredDimension(216, size2);
        } else if (getLayoutParams().height == -2) {
            setMeasuredDimension(size, 216);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.adx.setBounds(i, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.view.XView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.adx.onConfigurationChanged(getContext(), configuration);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.isDebug && this.adw != null) {
            this.adw.qK();
        }
        super.draw(canvas);
        if (this.isDebug && this.adw != null) {
            this.adw.qL();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.adx.draw(canvas);
    }

    public void setType(int i) {
        this.adx.setType(i);
    }

    public void setDuration(long j) {
        this.adx.setDuration(j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.view.XView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.adx.onConfigurationChanged(getContext(), getResources().getConfiguration());
        this.adx.cancelAnimations();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.view.XView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.adx.cancelAnimations();
    }

    public void setDebug(boolean z) {
        this.isDebug = z;
        this.adx.setDebug(z);
    }

    public float getDelayFactor() {
        return this.adx.getDelayFactor();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.adx.setVisible(i == 0, true);
    }
}
