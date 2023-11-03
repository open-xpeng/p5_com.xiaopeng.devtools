package com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.xiaopeng.devtools.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class CircularProgressView extends View {
    private RectF II;
    private boolean IJ;
    private boolean IK;
    private float IL;
    private float IM;
    private float IO;
    private float IP;
    private int IQ;
    private int IR;
    private int IS;
    private int IT;
    private float IU;
    private float IV;
    private ValueAnimator IW;
    private ValueAnimator IX;
    private AnimatorSet IY;
    private float IZ;
    private int color;
    private List<a> listeners;
    private Paint paint;
    private int size;
    private int thickness;

    public CircularProgressView(Context context) {
        super(context);
        this.size = 0;
        a((AttributeSet) null, 0);
    }

    public CircularProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.size = 0;
        a(attributeSet, 0);
    }

    public CircularProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.size = 0;
        a(attributeSet, i);
    }

    protected void a(AttributeSet attributeSet, int i) {
        this.listeners = new ArrayList();
        b(attributeSet, i);
        this.paint = new Paint(1);
        mH();
        this.II = new RectF();
    }

    private void b(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CircularProgressView, i, 0);
        Resources resources = getResources();
        this.IL = obtainStyledAttributes.getFloat(8, resources.getInteger(R.integer.xp_default_progress));
        this.IM = obtainStyledAttributes.getFloat(7, resources.getInteger(R.integer.xp_default_max_progress));
        this.thickness = obtainStyledAttributes.getDimensionPixelSize(10, resources.getDimensionPixelSize(R.dimen.xp_default_thickness));
        this.IJ = obtainStyledAttributes.getBoolean(6, resources.getBoolean(R.bool.xp_default_is_indeterminate));
        this.IK = obtainStyledAttributes.getBoolean(0, resources.getBoolean(R.bool.xp_default_anim_autostart));
        this.IZ = obtainStyledAttributes.getFloat(9, resources.getInteger(R.integer.xp_default_start_angle));
        this.IU = this.IZ;
        int identifier = getContext().getResources().getIdentifier("colorAccent", "attr", getContext().getPackageName());
        if (obtainStyledAttributes.hasValue(5)) {
            this.color = obtainStyledAttributes.getColor(5, resources.getColor(R.color.xp_default_color));
        } else if (identifier != 0) {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(identifier, typedValue, true);
            this.color = typedValue.data;
        }
        this.color = resources.getColor(R.color.xp_default_color);
        this.IQ = obtainStyledAttributes.getInteger(1, resources.getInteger(R.integer.xp_default_anim_duration));
        this.IR = obtainStyledAttributes.getInteger(3, resources.getInteger(R.integer.xp_default_anim_swoop_duration));
        this.IS = obtainStyledAttributes.getInteger(4, resources.getInteger(R.integer.xp_default_anim_sync_duration));
        this.IT = obtainStyledAttributes.getInteger(2, resources.getInteger(R.integer.xp_default_anim_steps));
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int measuredWidth = getMeasuredWidth() - paddingLeft;
        int measuredHeight = getMeasuredHeight() - paddingTop;
        if (measuredWidth >= measuredHeight) {
            measuredWidth = measuredHeight;
        }
        this.size = measuredWidth;
        setMeasuredDimension(this.size + paddingLeft, this.size + paddingTop);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i >= i2) {
            i = i2;
        }
        this.size = i;
        mG();
    }

    private void mG() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.II.set(this.thickness + paddingLeft, this.thickness + paddingTop, (this.size - paddingLeft) - this.thickness, (this.size - paddingTop) - this.thickness);
    }

    private void mH() {
        this.paint.setColor(this.color);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.thickness);
        this.paint.setStrokeCap(Paint.Cap.BUTT);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = ((isInEditMode() ? this.IL : this.IV) / this.IM) * 360.0f;
        if (!this.IJ) {
            canvas.drawArc(this.II, this.IU, f, false, this.paint);
        } else {
            canvas.drawArc(this.II, this.IU + this.IP, this.IO, false, this.paint);
        }
    }

    public void setIndeterminate(boolean z) {
        boolean z2 = this.IJ;
        boolean z3 = this.IJ == z;
        this.IJ = z;
        if (z3) {
            mI();
        }
        if (z2 != z) {
            for (a aVar : this.listeners) {
                aVar.at(z);
            }
        }
    }

    public int getThickness() {
        return this.thickness;
    }

    public void setThickness(int i) {
        this.thickness = i;
        mH();
        mG();
        invalidate();
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
        mH();
        invalidate();
    }

    public float getMaxProgress() {
        return this.IM;
    }

    public void setMaxProgress(float f) {
        this.IM = f;
        invalidate();
    }

    public float getProgress() {
        return this.IL;
    }

    public void setProgress(final float f) {
        this.IL = f;
        if (!this.IJ) {
            if (this.IX != null && this.IX.isRunning()) {
                this.IX.cancel();
            }
            this.IX = ValueAnimator.ofFloat(this.IV, f);
            this.IX.setDuration(this.IS);
            this.IX.setInterpolator(new LinearInterpolator());
            this.IX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.CircularProgressView.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    CircularProgressView.this.IV = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    CircularProgressView.this.invalidate();
                }
            });
            this.IX.addListener(new AnimatorListenerAdapter() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.CircularProgressView.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    for (a aVar : CircularProgressView.this.listeners) {
                        aVar.e(f);
                    }
                }
            });
            this.IX.start();
        }
        invalidate();
        for (a aVar : this.listeners) {
            aVar.d(f);
        }
    }

    public void startAnimation() {
        mI();
    }

    public void mI() {
        if (this.IW != null && this.IW.isRunning()) {
            this.IW.cancel();
        }
        if (this.IX != null && this.IX.isRunning()) {
            this.IX.cancel();
        }
        if (this.IY != null && this.IY.isRunning()) {
            this.IY.cancel();
        }
        int i = 0;
        if (!this.IJ) {
            this.IU = this.IZ;
            this.IW = ValueAnimator.ofFloat(this.IU, this.IU + 360.0f);
            this.IW.setDuration(this.IR);
            this.IW.setInterpolator(new DecelerateInterpolator(2.0f));
            this.IW.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.CircularProgressView.3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    CircularProgressView.this.IU = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    CircularProgressView.this.invalidate();
                }
            });
            this.IW.start();
            this.IV = 0.0f;
            this.IX = ValueAnimator.ofFloat(this.IV, this.IL);
            this.IX.setDuration(this.IS);
            this.IX.setInterpolator(new LinearInterpolator());
            this.IX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.CircularProgressView.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    CircularProgressView.this.IV = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    CircularProgressView.this.invalidate();
                }
            });
            this.IX.start();
            return;
        }
        this.IO = 15.0f;
        this.IY = new AnimatorSet();
        AnimatorSet animatorSet = null;
        while (i < this.IT) {
            AnimatorSet c = c(i);
            AnimatorSet.Builder play = this.IY.play(c);
            if (animatorSet != null) {
                play.after(animatorSet);
            }
            i++;
            animatorSet = c;
        }
        this.IY.addListener(new AnimatorListenerAdapter() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.CircularProgressView.5
            boolean Jc = false;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                this.Jc = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!this.Jc) {
                    CircularProgressView.this.mI();
                }
            }
        });
        this.IY.start();
        for (a aVar : this.listeners) {
            aVar.mJ();
        }
    }

    public void stopAnimation() {
        if (this.IW != null) {
            this.IW.cancel();
            this.IW = null;
        }
        if (this.IX != null) {
            this.IX.cancel();
            this.IX = null;
        }
        if (this.IY != null) {
            this.IY.cancel();
            this.IY = null;
        }
    }

    private AnimatorSet c(float f) {
        final float f2 = ((360.0f * (this.IT - 1)) / this.IT) + 15.0f;
        final float f3 = (-90.0f) + ((f2 - 15.0f) * f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(15.0f, f2);
        ofFloat.setDuration((this.IQ / this.IT) / 2);
        ofFloat.setInterpolator(new DecelerateInterpolator(1.0f));
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.CircularProgressView.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircularProgressView.this.IO = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircularProgressView.this.invalidate();
            }
        });
        float f4 = (0.5f + f) * 720.0f;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat((f * 720.0f) / this.IT, f4 / this.IT);
        ofFloat2.setDuration((this.IQ / this.IT) / 2);
        ofFloat2.setInterpolator(new LinearInterpolator());
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.CircularProgressView.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircularProgressView.this.IP = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            }
        });
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(f3, (f3 + f2) - 15.0f);
        ofFloat3.setDuration((this.IQ / this.IT) / 2);
        ofFloat3.setInterpolator(new DecelerateInterpolator(1.0f));
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.CircularProgressView.8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircularProgressView.this.IU = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircularProgressView.this.IO = (f2 - CircularProgressView.this.IU) + f3;
                CircularProgressView.this.invalidate();
            }
        });
        ValueAnimator ofFloat4 = ValueAnimator.ofFloat(f4 / this.IT, ((f + 1.0f) * 720.0f) / this.IT);
        ofFloat4.setDuration((this.IQ / this.IT) / 2);
        ofFloat4.setInterpolator(new LinearInterpolator());
        ofFloat4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.CircularProgressView.9
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircularProgressView.this.IP = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.play(ofFloat3).with(ofFloat4).after(ofFloat2);
        return animatorSet;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.IK) {
            startAnimation();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        int visibility = getVisibility();
        super.setVisibility(i);
        if (i != visibility) {
            if (i == 0) {
                mI();
            } else if (i == 8 || i == 4) {
                stopAnimation();
            }
        }
    }
}
