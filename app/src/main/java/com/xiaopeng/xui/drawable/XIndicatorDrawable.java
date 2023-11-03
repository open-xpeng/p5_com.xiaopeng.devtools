package com.xiaopeng.xui.drawable;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.c.a;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes13.dex */
public final class XIndicatorDrawable extends Drawable {
    private static final int INDICATOR_TYPE_LINE = 1;
    private static final int INDICATOR_TYPE_RECT = 0;
    private static final String TAG = XIndicatorDrawable.class.getSimpleName();
    private float mAnimIndicatorEnd;
    private float mAnimIndicatorStart;
    private BlurMaskFilter mBlurMaskFilter;
    private ColorStateList mColorStateList;
    private int mDefaultColor;
    @ColorRes
    private int mIndicatorColorRes;
    private int mIndicatorCount;
    private float mIndicatorEnd;
    private float mIndicatorEndAnimSpeed;
    private float mIndicatorHeight;
    private float mIndicatorPaddingBottom;
    private float mIndicatorPercent;
    private float mIndicatorRadius;
    private final RectF mIndicatorRect;
    private float mIndicatorStart;
    private float mIndicatorStartAnimSpeed;
    private final ValueAnimator mValueAnimator;
    private int mIndicatorType = 0;
    private int mCurrentSelection = -1;
    private boolean mEnable = true;
    private final Paint mPaint = new Paint(1);

    public XIndicatorDrawable() {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mIndicatorRect = new RectF();
        this.mValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mValueAnimator.setDuration(500L);
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.xui.drawable.-$$Lambda$XIndicatorDrawable$RF3IRyape-IxJ1hs8nfgdCdiWyU
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                XIndicatorDrawable.lambda$new$0(XIndicatorDrawable.this, valueAnimator);
            }
        });
        this.mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mValueAnimator.addListener(new Animator.AnimatorListener() { // from class: com.xiaopeng.xui.drawable.XIndicatorDrawable.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                XIndicatorDrawable.this.mAnimIndicatorStart = XIndicatorDrawable.this.mIndicatorStart;
                XIndicatorDrawable.this.mAnimIndicatorEnd = XIndicatorDrawable.this.mIndicatorEnd;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    public static /* synthetic */ void lambda$new$0(XIndicatorDrawable xIndicatorDrawable, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        float f = xIndicatorDrawable.mIndicatorStartAnimSpeed * floatValue;
        if (f > 1.0f) {
            f = 1.0f;
        }
        xIndicatorDrawable.mIndicatorStart += (xIndicatorDrawable.mAnimIndicatorStart - xIndicatorDrawable.mIndicatorStart) * f;
        float f2 = floatValue * xIndicatorDrawable.mIndicatorEndAnimSpeed;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        xIndicatorDrawable.mIndicatorEnd += (xIndicatorDrawable.mAnimIndicatorEnd - xIndicatorDrawable.mIndicatorEnd) * f2;
        xIndicatorDrawable.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.mIndicatorType == 1) {
            this.mIndicatorRect.bottom = rect.height() - this.mIndicatorPaddingBottom;
            this.mIndicatorRect.top = this.mIndicatorRect.bottom - this.mIndicatorHeight;
        } else {
            this.mIndicatorRect.top = 0.0f;
            this.mIndicatorRect.bottom = rect.height();
        }
        this.mIndicatorRadius = this.mIndicatorRect.height() / 2.0f;
        setSelection(this.mIndicatorCount, this.mCurrentSelection, false);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (isVisible() && this.mCurrentSelection != -1) {
            this.mIndicatorRect.left = this.mIndicatorStart;
            this.mIndicatorRect.right = this.mIndicatorEnd;
            if (this.mIndicatorType == 1) {
                this.mPaint.setMaskFilter(this.mBlurMaskFilter);
            } else {
                this.mPaint.setMaskFilter(null);
            }
            canvas.drawRoundRect(this.mIndicatorRect, this.mIndicatorRadius, this.mIndicatorRadius, this.mPaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        super.onStateChange(iArr);
        setPaintColor();
        return true;
    }

    private void setPaintColor() {
        if (this.mPaint != null && this.mColorStateList != null) {
            if (this.mEnable) {
                this.mPaint.setColor(this.mColorStateList.getColorForState(getState(), this.mDefaultColor));
            } else {
                this.mPaint.setColor(this.mColorStateList.getColorForState(StateSet.WILD_CARD, this.mDefaultColor));
            }
        }
    }

    public void setEnable(boolean z) {
        if (z != this.mEnable) {
            this.mEnable = z;
            setPaintColor();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet) throws IOException, XmlPullParserException {
        inflateAttrs(resources, attributeSet, null);
        super.inflate(resources, xmlPullParser, attributeSet);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        inflateAttrs(resources, attributeSet, theme);
        super.inflate(resources, xmlPullParser, attributeSet, theme);
    }

    public void setSelection(int i, int i2, boolean z) {
        this.mIndicatorCount = i;
        this.mCurrentSelection = i2;
        Rect bounds = getBounds();
        if (this.mIndicatorCount > this.mCurrentSelection && bounds.width() > 0) {
            if (this.mValueAnimator.isRunning()) {
                this.mValueAnimator.cancel();
            }
            float width = bounds.width();
            if (this.mIndicatorCount != 0) {
                width = bounds.width() / this.mIndicatorCount;
            }
            this.mAnimIndicatorStart = this.mCurrentSelection * width;
            this.mAnimIndicatorEnd = this.mAnimIndicatorStart + width;
            if (this.mIndicatorType == 1) {
                float f = (width * (1.0f - this.mIndicatorPercent)) / 2.0f;
                this.mAnimIndicatorStart += f;
                this.mAnimIndicatorEnd -= f;
            }
            if (this.mIndicatorStart == this.mAnimIndicatorStart && this.mIndicatorEnd == this.mAnimIndicatorEnd) {
                return;
            }
            if (z) {
                startAnimation();
                return;
            }
            this.mIndicatorStart = this.mAnimIndicatorStart;
            this.mIndicatorEnd = this.mAnimIndicatorEnd;
            invalidateSelf();
        }
    }

    private void startAnimation() {
        if (this.mAnimIndicatorStart <= this.mIndicatorStart && this.mAnimIndicatorEnd <= this.mIndicatorEnd) {
            this.mIndicatorStartAnimSpeed = 2.0f;
            this.mIndicatorEndAnimSpeed = 1.0f;
        } else if (this.mAnimIndicatorStart >= this.mIndicatorStart && this.mAnimIndicatorEnd >= this.mIndicatorEnd) {
            this.mIndicatorStartAnimSpeed = 1.0f;
            this.mIndicatorEndAnimSpeed = 2.0f;
        } else {
            this.mIndicatorStartAnimSpeed = 1.0f;
            this.mIndicatorEndAnimSpeed = 1.0f;
        }
        this.mValueAnimator.start();
    }

    public void inflateAttrs(Resources resources, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes;
        if (resources != null && attributeSet != null) {
            if (theme != null) {
                obtainAttributes = theme.obtainStyledAttributes(attributeSet, R.styleable.XSegmented, 0, 0);
            } else {
                obtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.XSegmented);
            }
            this.mIndicatorColorRes = obtainAttributes.getResourceId(R.styleable.XSegmented_segment_indicator_color, R.color.x_segment_indicator_color);
            this.mIndicatorType = obtainAttributes.getInt(R.styleable.XSegmented_segment_indicator_type, 0);
            onConfigurationChanged(resources, theme);
            if (this.mIndicatorType == 1) {
                this.mIndicatorPercent = obtainAttributes.getFloat(R.styleable.XSegmented_segment_line_width_percent, 1.0f);
                this.mIndicatorHeight = dp(resources, 4);
                this.mIndicatorPaddingBottom = obtainAttributes.getDimension(R.styleable.XSegmented_segment_line_padding_bottom, dp(resources, 6));
                if (this.mIndicatorPercent > 1.0f) {
                    this.mIndicatorPercent = 1.0f;
                }
            }
            obtainAttributes.recycle();
        }
    }

    public void onConfigurationChanged(Resources resources, Resources.Theme theme) {
        this.mColorStateList = resources.getColorStateList(this.mIndicatorColorRes, theme);
        this.mDefaultColor = resources.getColor(R.color.x_segment_indicator_color, theme);
        setPaintColor();
        if (a.a(resources.getConfiguration()) && this.mIndicatorType == 1) {
            this.mBlurMaskFilter = new BlurMaskFilter(dp(resources, 4), BlurMaskFilter.Blur.SOLID);
        } else {
            this.mBlurMaskFilter = null;
        }
        invalidateSelf();
    }

    private float dp(Resources resources, int i) {
        return TypedValue.applyDimension(1, i, resources.getDisplayMetrics());
    }
}
