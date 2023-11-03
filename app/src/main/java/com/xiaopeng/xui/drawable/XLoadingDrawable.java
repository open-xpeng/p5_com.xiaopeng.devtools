package com.xiaopeng.xui.drawable;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.c.a;
import com.xiaopeng.xui.d.f;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes13.dex */
public class XLoadingDrawable extends Drawable {
    private static final int ALPHA_MAX = 255;
    private static final int ALPHA_MIN = 92;
    private static final int COUNT_LARGE = 5;
    private static final int COUNT_MEDIUM = 5;
    private static final int COUNT_SMALL = 3;
    private static final int COUNT_XLARGE = 7;
    private static final float DEFAULT_DEGREE = 25.0f;
    private static final long DEFAULT_DURATION = 1000;
    private static final float DEFAULT_MASK_FILTER = 2.0f;
    private static final double DEFAULT_RADIANS = Math.toRadians(25.0d);
    private static final float DEFAULT_RECT_RADIUS = 1.0f;
    private static final float MAX_HEIGHT_LARGE = 34.0f;
    private static final float MAX_HEIGHT_MEDIUM = 22.0f;
    private static final float MAX_HEIGHT_SMALL = 16.0f;
    private static final float MAX_HEIGHT_XLARGE = 50.0f;
    private static final float MIN_HEIGHT_LARGE = 12.0f;
    private static final float MIN_HEIGHT_MEDIUM = 8.0f;
    private static final float MIN_HEIGHT_SMALL = 4.0f;
    private static final float MIN_HEIGHT_XLARGE = 10.0f;
    private static final String TAG = "xpui-XLoadingDrawable";
    public static final int TYPE_LARGE = 2;
    public static final int TYPE_MEDIUM = 1;
    public static final int TYPE_SMALL = 0;
    public static final int TYPE_XLARGE = 3;
    private static final float WIDTH_LARGE = 6.0f;
    private static final float WIDTH_MEDIUM = 4.0f;
    private static final float WIDTH_SMALL = 4.0f;
    private static final float WIDTH_XLARGE = 6.0f;
    private static final float X_SPACING_LARGE = 8.0f;
    private static final float X_SPACING_MEDIUM = 5.0f;
    private static final float X_SPACING_SMALL = 5.0f;
    private static final float X_SPACING_XLARGE = 19.0f;
    private boolean isAmStarted;
    private int[] mAlphas;
    private ValueAnimator[] mAnimations;
    private float mCenterX;
    private float mCenterY;
    private int mColor;
    private int mCount;
    private float mDelayFactor;
    private final MaskFilter mMaskFilter;
    private float[] mRectHeights;
    private float[] mRectTopXs;
    private float[] mRectTopYs;
    private float mRectWidth;
    private float mSpacingX;
    private float maxHeight;
    private float minHeight;
    private int mColorId = R.color.x_theme_text_01;
    private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private long mDuration = DEFAULT_DURATION;
    private boolean isDebug = false;
    private final Paint mPaint = new Paint();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes13.dex */
    public @interface Type {
    }

    public XLoadingDrawable() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mMaskFilter = new BlurMaskFilter(DEFAULT_MASK_FILTER, BlurMaskFilter.Blur.SOLID);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.XLoadingDrawable);
        this.mColorId = obtainAttributes.getResourceId(R.styleable.XLoadingDrawable_loading_color, R.color.x_theme_text_01);
        this.mColor = resources.getColor(this.mColorId, theme);
        setType(obtainAttributes.getInt(R.styleable.XLoadingDrawable_loading_type, 3));
        obtainAttributes.recycle();
        setBlurEffect(resources);
    }

    public void setType(int i) {
        cancelAnimations();
        switch (i) {
            case 0:
                this.mCount = 3;
                this.mRectWidth = 4.0f;
                this.mSpacingX = 5.0f;
                this.maxHeight = MAX_HEIGHT_SMALL;
                this.minHeight = 4.0f;
                break;
            case 1:
                this.mCount = 5;
                this.mRectWidth = 4.0f;
                this.mSpacingX = 5.0f;
                this.maxHeight = MAX_HEIGHT_MEDIUM;
                this.minHeight = 8.0f;
                break;
            case 2:
                this.mCount = 5;
                this.mRectWidth = 6.0f;
                this.mSpacingX = 8.0f;
                this.maxHeight = MAX_HEIGHT_LARGE;
                this.minHeight = MIN_HEIGHT_LARGE;
                break;
            default:
                this.mCount = 7;
                this.mRectWidth = 6.0f;
                this.mSpacingX = X_SPACING_XLARGE;
                this.maxHeight = 50.0f;
                this.minHeight = MIN_HEIGHT_XLARGE;
                break;
        }
        this.mDelayFactor = 0.5f / (this.mCount - 1);
        invalidateSelf();
    }

    public void setDebug(boolean z) {
        this.isDebug = z;
    }

    public void setDuration(long j) {
        cancelAnimations();
        this.mDuration = j;
        invalidateSelf();
    }

    public float getDelayFactor() {
        return this.mDelayFactor;
    }

    private void startAnimations() {
        makeAnimations();
        int i = 0;
        while (i < this.mCount) {
            int i2 = i + 1;
            this.mAnimations[i].setCurrentFraction(1.0f - (this.mDelayFactor * i2));
            this.mAnimations[i].start();
            i = i2;
        }
        this.isAmStarted = true;
    }

    private void makeAnimations() {
        final int i = this.mCount;
        this.mAnimations = new ValueAnimator[i];
        this.mRectHeights = new float[i];
        this.mRectTopYs = new float[i];
        this.mAlphas = new int[i];
        for (final int i2 = 0; i2 < i; i2++) {
            this.mAnimations[i2] = ValueAnimator.ofFloat(0.0f, 1.0f, 0.0f);
            this.mAnimations[i2].setRepeatCount(-1);
            this.mAnimations[i2].setRepeatMode(2);
            this.mAnimations[i2].setDuration(this.mDuration);
            this.mAnimations[i2].setInterpolator(this.mInterpolator);
            this.mAnimations[i2].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.xui.drawable.-$$Lambda$XLoadingDrawable$DpLV3pscnQdWTddtu4oa-Sye8L4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    XLoadingDrawable.lambda$makeAnimations$0(XLoadingDrawable.this, i2, i, valueAnimator);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$makeAnimations$0(XLoadingDrawable xLoadingDrawable, int i, int i2, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        xLoadingDrawable.mAlphas[i] = ((int) (163.0f * floatValue)) + 92;
        xLoadingDrawable.mRectHeights[i] = xLoadingDrawable.minHeight + ((xLoadingDrawable.maxHeight - xLoadingDrawable.minHeight) * floatValue);
        int i3 = i2 - 1;
        xLoadingDrawable.mRectTopYs[i] = (xLoadingDrawable.mCenterY - (xLoadingDrawable.mRectHeights[i] * 0.5f)) + (((i3 * 0.5f) - i) * ((float) Math.tan(DEFAULT_RADIANS)) * (xLoadingDrawable.mSpacingX + xLoadingDrawable.mRectWidth));
        if (i == i3) {
            xLoadingDrawable.invalidateSelf();
        }
    }

    public void cancelAnimations() {
        ValueAnimator[] valueAnimatorArr;
        if (this.mAnimations != null) {
            for (ValueAnimator valueAnimator : this.mAnimations) {
                valueAnimator.removeAllUpdateListeners();
                valueAnimator.cancel();
            }
        }
        this.isAmStarted = false;
        this.mAnimations = null;
        this.mCenterX = 0.0f;
    }

    public void onConfigurationChanged(Context context, Configuration configuration) {
        this.mColor = context.getResources().getColor(this.mColorId, context.getTheme());
        setBlurEffect(context.getResources());
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        if (!z) {
            cancelAnimations();
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        initParams();
        if (!this.isAmStarted && isVisible()) {
            startAnimations();
            return;
        }
        if (this.isDebug) {
            this.mPaint.setColor(-65536);
            canvas.drawLine(0.0f, this.mCenterY, getIntrinsicWidth(), this.mCenterY, this.mPaint);
            canvas.drawLine(this.mCenterX, 0.0f, this.mCenterX, getIntrinsicHeight(), this.mPaint);
        }
        canvas.rotate(DEFAULT_DEGREE, this.mCenterX, this.mCenterY);
        for (int i = 0; i < this.mCount; i++) {
            this.mPaint.setColor(this.mColor);
            this.mPaint.setAlpha(this.mAlphas[i]);
            canvas.drawRoundRect(this.mRectTopXs[i], this.mRectTopYs[i], this.mRectTopXs[i] + this.mRectWidth, this.mRectTopYs[i] + this.mRectHeights[i], 1.0f, 1.0f, this.mPaint);
        }
    }

    private void setBlurEffect(Resources resources) {
        if (this.mPaint == null || this.mMaskFilter == null) {
            return;
        }
        if (a.a(resources.getConfiguration())) {
            this.mPaint.setMaskFilter(this.mMaskFilter);
        } else {
            this.mPaint.setMaskFilter(null);
        }
    }

    private void initParams() {
        if (this.mCount == 0) {
            f.i(TAG, "You must setType or config loading_type first");
        }
        if (this.mCenterX == 0.0f) {
            this.mCenterX = getIntrinsicWidth() * 0.5f;
            this.mCenterY = getIntrinsicHeight() * 0.5f;
            this.mRectTopXs = new float[this.mCount];
            float f = this.mCenterX - (((this.mCount * this.mRectWidth) * 0.5f) + ((this.mCount >> 1) * this.mSpacingX));
            for (int i = 0; i < this.mCount; i++) {
                this.mRectTopXs[i] = (i * (this.mSpacingX + this.mRectWidth)) + f;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return getBounds().right - getBounds().left;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return getBounds().bottom - getBounds().top;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }
}
