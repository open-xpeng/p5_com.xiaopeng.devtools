package com.xiaopeng.xui.drawable.shimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import com.xiaopeng.xpui.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
/* loaded from: classes13.dex */
public class XShimmer {
    private static final int COMPONENT_COUNT = 4;
    public static boolean msGlobalEnable;
    long repeatDelay;
    final float[] positions = new float[4];
    final int[] colors = new int[4];
    int direction = 0;
    @ColorInt
    int highlightColor = 1728053247;
    @ColorInt
    int baseColor = 16777215;
    int shape = 0;
    int fixedWidth = 0;
    int fixedHeight = 0;
    float widthRatio = 1.0f;
    float heightRatio = 1.0f;
    float intensity = 0.0f;
    float dropoff = 0.5f;
    float tilt = 0.0f;
    boolean clipToChildren = true;
    boolean autoStart = true;
    boolean alphaShimmer = true;
    int repeatCount = -1;
    int repeatMode = 1;
    long animationDuration = 1000;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes13.dex */
    public @interface Direction {
        public static final int BOTTOM_TO_TOP = 3;
        public static final int LEFT_TO_RIGHT = 0;
        public static final int RIGHT_TO_LEFT = 2;
        public static final int TOP_TO_BOTTOM = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes13.dex */
    public @interface Shape {
        public static final int LINEAR = 0;
        public static final int RADIAL = 1;
    }

    XShimmer() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int width(int i) {
        return this.fixedWidth > 0 ? this.fixedWidth : Math.round(this.widthRatio * i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int height(int i) {
        return this.fixedHeight > 0 ? this.fixedHeight : Math.round(this.heightRatio * i);
    }

    void updateColors() {
        if (this.shape != 1) {
            this.colors[0] = this.baseColor;
            this.colors[1] = this.highlightColor;
            this.colors[2] = this.highlightColor;
            this.colors[3] = this.baseColor;
            return;
        }
        this.colors[0] = this.highlightColor;
        this.colors[1] = this.highlightColor;
        this.colors[2] = this.baseColor;
        this.colors[3] = this.baseColor;
    }

    void updatePositions() {
        if (this.shape != 1) {
            this.positions[0] = Math.max(((1.0f - this.intensity) - this.dropoff) / 2.0f, 0.0f);
            this.positions[1] = Math.max((1.0f - this.intensity) / 2.0f, 0.0f);
            this.positions[2] = Math.min((this.intensity + 1.0f) / 2.0f, 1.0f);
            this.positions[3] = Math.min(((this.intensity + 1.0f) + this.dropoff) / 2.0f, 1.0f);
            return;
        }
        this.positions[0] = 0.0f;
        this.positions[1] = Math.min(this.intensity, 1.0f);
        this.positions[2] = Math.min(this.intensity + this.dropoff, 1.0f);
        this.positions[3] = 1.0f;
    }

    /* loaded from: classes13.dex */
    public static abstract class Builder<T extends Builder<T>> {
        final XShimmer mShimmer = new XShimmer();

        protected abstract T getThis();

        public T consumeAttributes(Context context, AttributeSet attributeSet) {
            return consumeAttributes(context.obtainStyledAttributes(attributeSet, R.styleable.XShimmerDrawable, 0, 0));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public T consumeAttributes(TypedArray typedArray) {
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_clip_to_children)) {
                setClipToChildren(typedArray.getBoolean(R.styleable.XShimmerDrawable_shimmer_clip_to_children, this.mShimmer.clipToChildren));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_auto_start)) {
                setAutoStart(typedArray.getBoolean(R.styleable.XShimmerDrawable_shimmer_auto_start, this.mShimmer.autoStart));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_base_alpha)) {
                setBaseAlpha(typedArray.getFloat(R.styleable.XShimmerDrawable_shimmer_base_alpha, 0.3f));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_highlight_alpha)) {
                setHighlightAlpha(typedArray.getFloat(R.styleable.XShimmerDrawable_shimmer_highlight_alpha, 1.0f));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_duration)) {
                setDuration(typedArray.getInt(R.styleable.XShimmerDrawable_shimmer_duration, (int) this.mShimmer.animationDuration));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_repeat_count)) {
                setRepeatCount(typedArray.getInt(R.styleable.XShimmerDrawable_shimmer_repeat_count, this.mShimmer.repeatCount));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_repeat_delay)) {
                setRepeatDelay(typedArray.getInt(R.styleable.XShimmerDrawable_shimmer_repeat_delay, (int) this.mShimmer.repeatDelay));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_repeat_mode)) {
                setRepeatMode(typedArray.getInt(R.styleable.XShimmerDrawable_shimmer_repeat_mode, this.mShimmer.repeatMode));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_direction)) {
                setDirection(typedArray.getInt(R.styleable.XShimmerDrawable_shimmer_direction, this.mShimmer.direction));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_shape)) {
                setShape(typedArray.getInt(R.styleable.XShimmerDrawable_shimmer_shape, this.mShimmer.shape));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_dropoff)) {
                setDropoff(typedArray.getFloat(R.styleable.XShimmerDrawable_shimmer_dropoff, this.mShimmer.dropoff));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_fixed_width)) {
                setFixedWidth(typedArray.getDimensionPixelSize(R.styleable.XShimmerDrawable_shimmer_fixed_width, this.mShimmer.fixedWidth));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_fixed_height)) {
                setFixedHeight(typedArray.getDimensionPixelSize(R.styleable.XShimmerDrawable_shimmer_fixed_height, this.mShimmer.fixedHeight));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_intensity)) {
                setIntensity(typedArray.getFloat(R.styleable.XShimmerDrawable_shimmer_intensity, this.mShimmer.intensity));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_width_ratio)) {
                setWidthRatio(typedArray.getFloat(R.styleable.XShimmerDrawable_shimmer_width_ratio, this.mShimmer.widthRatio));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_height_ratio)) {
                setHeightRatio(typedArray.getFloat(R.styleable.XShimmerDrawable_shimmer_height_ratio, this.mShimmer.heightRatio));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_tilt)) {
                setTilt(typedArray.getFloat(R.styleable.XShimmerDrawable_shimmer_tilt, this.mShimmer.tilt));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_global_configuration_enable) && typedArray.getBoolean(R.styleable.XShimmerDrawable_shimmer_global_configuration_enable, false)) {
                setAutoStart(XShimmer.msGlobalEnable);
            }
            return getThis();
        }

        public T copyFrom(XShimmer xShimmer) {
            setDirection(xShimmer.direction);
            setShape(xShimmer.shape);
            setFixedWidth(xShimmer.fixedWidth);
            setFixedHeight(xShimmer.fixedHeight);
            setWidthRatio(xShimmer.widthRatio);
            setHeightRatio(xShimmer.heightRatio);
            setIntensity(xShimmer.intensity);
            setDropoff(xShimmer.dropoff);
            setTilt(xShimmer.tilt);
            setClipToChildren(xShimmer.clipToChildren);
            setAutoStart(xShimmer.autoStart);
            setRepeatCount(xShimmer.repeatCount);
            setRepeatMode(xShimmer.repeatMode);
            setRepeatDelay(xShimmer.repeatDelay);
            setDuration(xShimmer.animationDuration);
            this.mShimmer.baseColor = xShimmer.baseColor;
            this.mShimmer.highlightColor = xShimmer.highlightColor;
            return getThis();
        }

        public T setDirection(int i) {
            this.mShimmer.direction = i;
            return getThis();
        }

        public T setShape(int i) {
            this.mShimmer.shape = i;
            return getThis();
        }

        public T setFixedWidth(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Given invalid width: " + i);
            }
            this.mShimmer.fixedWidth = i;
            return getThis();
        }

        public T setFixedHeight(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Given invalid height: " + i);
            }
            this.mShimmer.fixedHeight = i;
            return getThis();
        }

        public T setWidthRatio(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Given invalid width ratio: " + f);
            }
            this.mShimmer.widthRatio = f;
            return getThis();
        }

        public T setHeightRatio(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Given invalid height ratio: " + f);
            }
            this.mShimmer.heightRatio = f;
            return getThis();
        }

        public T setIntensity(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Given invalid intensity value: " + f);
            }
            this.mShimmer.intensity = f;
            return getThis();
        }

        public T setDropoff(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Given invalid dropoff value: " + f);
            }
            this.mShimmer.dropoff = f;
            return getThis();
        }

        public T setTilt(float f) {
            this.mShimmer.tilt = f;
            return getThis();
        }

        public T setBaseAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f) {
            this.mShimmer.baseColor = (((int) (clamp(0.0f, 1.0f, f) * 255.0f)) << 24) | (this.mShimmer.baseColor & 16777215);
            return getThis();
        }

        public T setHighlightAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f) {
            this.mShimmer.highlightColor = (((int) (clamp(0.0f, 1.0f, f) * 255.0f)) << 24) | (this.mShimmer.highlightColor & 16777215);
            return getThis();
        }

        public T setHighlightColor(@ColorInt int i) {
            return getThis();
        }

        public T setBaseColor(@ColorInt int i) {
            return getThis();
        }

        public T setClipToChildren(boolean z) {
            this.mShimmer.clipToChildren = z;
            return getThis();
        }

        public T setAutoStart(boolean z) {
            this.mShimmer.autoStart = z;
            return getThis();
        }

        public T setRepeatCount(int i) {
            this.mShimmer.repeatCount = i;
            return getThis();
        }

        public T setRepeatMode(int i) {
            this.mShimmer.repeatMode = i;
            return getThis();
        }

        public T setRepeatDelay(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("Given a negative repeat delay: " + j);
            }
            this.mShimmer.repeatDelay = j;
            return getThis();
        }

        public T setDuration(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("Given a negative duration: " + j);
            }
            this.mShimmer.animationDuration = j;
            return getThis();
        }

        public XShimmer build() {
            this.mShimmer.updateColors();
            this.mShimmer.updatePositions();
            return this.mShimmer;
        }

        private static float clamp(float f, float f2, float f3) {
            return Math.min(f2, Math.max(f, f3));
        }
    }

    /* loaded from: classes13.dex */
    public static class AlphaHighlightBuilder extends Builder<AlphaHighlightBuilder> {
        public AlphaHighlightBuilder() {
            this.mShimmer.alphaShimmer = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.xiaopeng.xui.drawable.shimmer.XShimmer.Builder
        public AlphaHighlightBuilder getThis() {
            return this;
        }
    }

    /* loaded from: classes13.dex */
    public static class ColorHighlightBuilder extends Builder<ColorHighlightBuilder> {
        public ColorHighlightBuilder() {
            this.mShimmer.alphaShimmer = false;
        }

        @Override // com.xiaopeng.xui.drawable.shimmer.XShimmer.Builder
        public ColorHighlightBuilder setHighlightColor(@ColorInt int i) {
            this.mShimmer.highlightColor = i;
            return getThis();
        }

        @Override // com.xiaopeng.xui.drawable.shimmer.XShimmer.Builder
        public ColorHighlightBuilder setBaseColor(@ColorInt int i) {
            this.mShimmer.baseColor = (i & 16777215) | (this.mShimmer.baseColor & (-16777216));
            return getThis();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.xiaopeng.xui.drawable.shimmer.XShimmer.Builder
        public ColorHighlightBuilder consumeAttributes(TypedArray typedArray) {
            super.consumeAttributes(typedArray);
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_base_color)) {
                setBaseColor(typedArray.getColor(R.styleable.XShimmerDrawable_shimmer_base_color, this.mShimmer.baseColor));
            }
            if (typedArray.hasValue(R.styleable.XShimmerDrawable_shimmer_highlight_color)) {
                setHighlightColor(typedArray.getColor(R.styleable.XShimmerDrawable_shimmer_highlight_color, this.mShimmer.highlightColor));
            }
            return getThis();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaopeng.xui.drawable.shimmer.XShimmer.Builder
        public ColorHighlightBuilder getThis() {
            return this;
        }
    }
}
