package com.xiaopeng.xui.a;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;

/* compiled from: XLightPaint.java */
/* loaded from: classes13.dex */
public class b {
    private static float aba = 200.0f;
    private LightingColorFilter aaU;
    @ColorInt
    private int aaV = -1;
    private int aaW = 255;
    private int aaX = 0;
    @FloatRange(from = 0.0d, to = 1.0d)
    private float aaY = 1.0f;
    private int aaZ = 0;
    private BlurMaskFilter mBlurMaskFilter;
    private Paint mPaint;

    public b(Paint paint) {
        this.mPaint = paint;
    }

    public void es(int i) {
        this.aaZ = i;
    }

    public void setLightRadius(float f) {
        aba = f;
    }

    public void bZ(@IntRange(from = 0, to = 255) int i) {
        this.aaX = i;
    }

    public void setLightColor(@ColorInt int i) {
        this.aaV = i;
    }

    public void f(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.aaY = f;
    }

    public void apply() {
        float f = aba * this.aaY;
        if (this.aaX > 0) {
            int i = (int) (this.aaY * this.aaX);
            this.aaU = new LightingColorFilter(-1, Color.argb(this.aaW, i, i, i));
            this.mPaint.setColorFilter(this.aaU);
        }
        if (this.aaZ == 0) {
            if (f > 0.0f) {
                this.mBlurMaskFilter = new BlurMaskFilter(f, BlurMaskFilter.Blur.SOLID);
                this.mPaint.setMaskFilter(this.mBlurMaskFilter);
                return;
            }
            this.mPaint.setMaskFilter(null);
        } else if (this.aaZ == 1) {
            if (this.aaV == -1) {
                throw new IllegalArgumentException("Please set light color.");
            }
            this.mPaint.setShadowLayer(f, 0.0f, 0.0f, this.aaV);
        }
    }
}
