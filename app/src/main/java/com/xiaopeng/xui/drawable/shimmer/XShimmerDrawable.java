package com.xiaopeng.xui.drawable.shimmer;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.drawable.shimmer.XShimmer;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@Deprecated
/* loaded from: classes13.dex */
public class XShimmerDrawable extends Drawable {
    private XShimmer.Builder mBuilder;
    @Nullable
    private XShimmer mShimmer;
    @Nullable
    private ValueAnimator mValueAnimator;
    private final ValueAnimator.AnimatorUpdateListener mUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.xui.drawable.shimmer.XShimmerDrawable.1
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            XShimmerDrawable.this.invalidateSelf();
        }
    };
    private final Paint mShimmerPaint = new Paint();
    private final Rect mDrawRect = new Rect();
    private final Matrix mShaderMatrix = new Matrix();

    public XShimmerDrawable() {
        this.mShimmerPaint.setAntiAlias(true);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        setShimmer(parseShimmer(resources, attributeSet, theme));
        super.inflate(resources, xmlPullParser, attributeSet, theme);
    }

    public XShimmer parseShimmer(Resources resources, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes;
        if (attributeSet == null) {
            return new XShimmer.AlphaHighlightBuilder().build();
        }
        if (resources != null && attributeSet != null) {
            if (theme != null) {
                obtainAttributes = theme.obtainStyledAttributes(attributeSet, R.styleable.XShimmerDrawable, 0, 0);
            } else {
                obtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.XShimmerDrawable);
            }
            try {
                XShimmer.Builder colorHighlightBuilder = (obtainAttributes.hasValue(R.styleable.XShimmerDrawable_shimmer_colored) && obtainAttributes.getBoolean(R.styleable.XShimmerDrawable_shimmer_colored, false)) ? new XShimmer.ColorHighlightBuilder() : new XShimmer.AlphaHighlightBuilder();
                saveShimmerBuilder(colorHighlightBuilder);
                return colorHighlightBuilder.consumeAttributes(obtainAttributes).build();
            } finally {
                obtainAttributes.recycle();
            }
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        float offset;
        float offset2;
        if (this.mShimmer == null || this.mShimmerPaint.getShader() == null) {
            return;
        }
        float tan = (float) Math.tan(Math.toRadians(this.mShimmer.tilt));
        float height = this.mDrawRect.height() + (this.mDrawRect.width() * tan);
        float width = this.mDrawRect.width() + (tan * this.mDrawRect.height());
        float f = 0.0f;
        float animatedFraction = this.mValueAnimator != null ? this.mValueAnimator.getAnimatedFraction() : 0.0f;
        switch (this.mShimmer.direction) {
            case 1:
                offset = offset(-height, height, animatedFraction);
                f = offset;
                offset2 = 0.0f;
                break;
            case 2:
                offset2 = offset(width, -width, animatedFraction);
                break;
            case 3:
                offset = offset(height, -height, animatedFraction);
                f = offset;
                offset2 = 0.0f;
                break;
            default:
                offset2 = offset(-width, width, animatedFraction);
                break;
        }
        this.mShaderMatrix.reset();
        this.mShaderMatrix.setRotate(this.mShimmer.tilt, this.mDrawRect.width() / 2.0f, this.mDrawRect.height() / 2.0f);
        this.mShaderMatrix.postTranslate(offset2, f);
        this.mShimmerPaint.getShader().setLocalMatrix(this.mShaderMatrix);
        canvas.drawRect(this.mDrawRect, this.mShimmerPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mBuilder.setHighlightAlpha(i / 255.0f).build();
        setShimmer(this.mBuilder.build());
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return (this.mShimmer == null || !(this.mShimmer.clipToChildren || this.mShimmer.alphaShimmer)) ? -1 : -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mDrawRect.set(rect);
        updateShader();
        maybeStartShimmer();
    }

    public void setColorHighlight(@ColorInt int i, int i2) {
        this.mBuilder.setHighlightColor(i).setBaseColor(i2).build();
        setShimmer(this.mBuilder.build());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        if (!z) {
            stopShimmer();
        } else {
            maybeStartShimmer();
        }
        return super.setVisible(z, z2);
    }

    public void setShimmer(@Nullable XShimmer xShimmer) {
        this.mShimmer = xShimmer;
        if (this.mShimmer != null) {
            updateShader();
            updateValueAnimator();
            invalidateSelf();
        }
    }

    private void saveShimmerBuilder(XShimmer.Builder builder) {
        this.mBuilder = builder;
    }

    public void maybeStartShimmer() {
        if (this.mValueAnimator != null && !this.mValueAnimator.isStarted() && this.mShimmer != null && this.mShimmer.autoStart && getCallback() != null) {
            this.mValueAnimator.start();
        }
    }

    public void startShimmer() {
        if (this.mValueAnimator != null && !isShimmerStarted() && getCallback() != null) {
            this.mValueAnimator.start();
        }
    }

    public void stopShimmer() {
        if (this.mValueAnimator != null && isShimmerStarted()) {
            this.mValueAnimator.cancel();
        }
    }

    public boolean isShimmerStarted() {
        return this.mValueAnimator != null && this.mValueAnimator.isStarted();
    }

    private void updateValueAnimator() {
        boolean z;
        if (this.mShimmer == null) {
            return;
        }
        if (this.mValueAnimator != null) {
            z = this.mValueAnimator.isStarted();
            this.mValueAnimator.cancel();
            this.mValueAnimator.removeAllUpdateListeners();
        } else {
            z = false;
        }
        this.mValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f + ((float) (this.mShimmer.repeatDelay / this.mShimmer.animationDuration)));
        this.mValueAnimator.setRepeatMode(this.mShimmer.repeatMode);
        this.mValueAnimator.setRepeatCount(this.mShimmer.repeatCount);
        this.mValueAnimator.setDuration(this.mShimmer.animationDuration + this.mShimmer.repeatDelay);
        this.mValueAnimator.addUpdateListener(this.mUpdateListener);
        if (z) {
            this.mValueAnimator.start();
        }
    }

    private void updateShader() {
        Shader radialGradient;
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (width == 0 || height == 0 || this.mShimmer == null) {
            return;
        }
        int width2 = this.mShimmer.width(width);
        int height2 = this.mShimmer.height(height);
        boolean z = true;
        if (this.mShimmer.shape != 1) {
            if (this.mShimmer.direction != 1 && this.mShimmer.direction != 3) {
                z = false;
            }
            if (z) {
                width2 = 0;
            }
            if (!z) {
                height2 = 0;
            }
            radialGradient = new LinearGradient(0.0f, 0.0f, width2, height2, this.mShimmer.colors, this.mShimmer.positions, Shader.TileMode.CLAMP);
        } else {
            radialGradient = new RadialGradient(width2 / 2.0f, height2 / 2.0f, (float) (Math.max(width2, height2) / Math.sqrt(2.0d)), this.mShimmer.colors, this.mShimmer.positions, Shader.TileMode.CLAMP);
        }
        this.mShimmerPaint.setShader(radialGradient);
    }

    private float offset(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }
}
