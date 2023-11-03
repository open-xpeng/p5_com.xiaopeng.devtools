package com.xiaopeng.xui.widget.slider;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.ColorInt;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.view.XView;

/* loaded from: classes13.dex */
class SlideLineView extends XView {
    private final int ajM;
    private final int ajN;
    private final int ajO;
    private boolean ajP;
    private Paint ajQ;
    @ColorInt
    int ajR;
    @ColorInt
    int ajS;
    private float ajT;
    private float ajU;
    private float ajV;
    @ColorInt
    int ajW;
    @ColorInt
    int ajX;
    private Paint ajY;
    private ValueAnimator animator;
    private boolean isNight;
    private float progress;

    public SlideLineView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.style.XSliderLine);
    }

    public SlideLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.ajM = 22;
        this.ajN = 40;
        this.ajO = 4;
        this.ajR = 671088640;
        this.ajS = -1;
        this.ajT = 1.55f;
        this.ajU = 5.0f;
        this.ajV = this.ajU / this.ajT;
        this.ajW = 671088640;
        this.ajX = -15301639;
        this.isNight = com.xiaopeng.xui.c.a.aB(getContext());
        this.progress = 1.0f;
        c(context, attributeSet);
    }

    public SlideLineView(Context context, boolean z) {
        this(context, (AttributeSet) null);
        this.ajP = z;
    }

    private void c(Context context, AttributeSet attributeSet) {
        setLayerType(1, null);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlideLineView);
            this.ajW = obtainStyledAttributes.getColor(R.styleable.SlideLineView_slider_line_un_select, this.ajW);
            this.ajX = obtainStyledAttributes.getColor(R.styleable.SlideLineView_slider_line_select, this.ajX);
            obtainStyledAttributes.recycle();
        }
        this.ajQ = new Paint(1);
        this.ajQ.setStyle(Paint.Style.FILL);
        this.ajQ.setStrokeCap(Paint.Cap.ROUND);
        this.ajQ.setStrokeWidth(4.0f);
        this.ajQ.setColor(this.ajX);
        this.ajY = new Paint(1);
        this.ajY.setStyle(Paint.Style.FILL);
        this.ajY.setStrokeCap(Paint.Cap.ROUND);
        this.ajY.setStrokeWidth(4.0f);
        this.ajY.setColor(4);
        this.animator = ValueAnimator.ofFloat(0.0f, 2.0f, 1.0f);
        this.animator.setDuration(800L);
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.xui.widget.slider.SlideLineView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SlideLineView.this.progress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SlideLineView.this.invalidate();
            }
        });
        this.animator.setInterpolator(new DecelerateInterpolator());
        this.animator.addListener(new Animator.AnimatorListener() { // from class: com.xiaopeng.xui.widget.slider.SlideLineView.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SlideLineView.this.ajQ.setStrokeWidth(4.0f);
                SlideLineView.this.ajY.setMaskFilter(null);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }
        });
        setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.view.XView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.isNight = com.xiaopeng.xui.c.a.aB(getContext());
        postDelayed(new Runnable() { // from class: com.xiaopeng.xui.widget.slider.SlideLineView.3
            @Override // java.lang.Runnable
            public void run() {
                SlideLineView.this.invalidate();
            }
        }, 500L);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.ajP) {
            if (this.isNight) {
                this.ajQ.setColor(this.ajX);
                this.ajY.setColor(this.ajX);
                canvas.drawLine((getWidth() / 2) - (this.ajV * this.progress), (getHeight() / 2) + (this.ajU * this.progress), (getWidth() / 2) + (this.ajV * this.progress), (getHeight() / 2) - (this.ajU * this.progress), this.ajQ);
                canvas.drawLine((getWidth() / 2) - (this.ajV * this.progress), (getHeight() / 2) + (this.ajU * this.progress), (getWidth() / 2) + (this.ajV * this.progress), (getHeight() / 2) - (this.ajU * this.progress), this.ajY);
                return;
            }
            this.ajQ.setColor(this.ajS);
            this.ajY.setColor(this.ajS);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.progress * 2.0f, this.ajQ);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, 2.0f * this.progress, this.ajY);
        } else if (this.isNight) {
            this.ajQ.setColor(this.ajW);
            canvas.drawLine((getWidth() / 2) - this.ajV, (getHeight() / 2) + this.ajU, (getWidth() / 2) + this.ajV, (getHeight() / 2) - this.ajU, this.ajQ);
        } else {
            this.ajQ.setColor(this.ajR);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, 2.0f, this.ajQ);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(22, 40);
    }

    public void bs(boolean z) {
        this.ajP = z;
        this.ajQ.setStrokeWidth(z ? 2.0f : 4.0f);
        if (z) {
            if (this.isNight) {
                this.ajY.setColor(this.ajX);
            } else {
                this.ajY.setColor(this.ajS);
            }
            this.ajY.setMaskFilter(new BlurMaskFilter(4.0f, BlurMaskFilter.Blur.NORMAL));
        }
        if (!z) {
            this.animator.cancel();
        } else {
            this.animator.start();
        }
        invalidate();
    }

    public boolean rX() {
        return this.ajP;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        setAlphaByEnable(z);
        invalidate();
    }

    private void setAlphaByEnable(boolean z) {
        this.ajW = z ? 671088640 : 503316480;
        this.ajX = z ? this.ajX | (-1291845632) : this.ajX & 1291845631;
    }
}
