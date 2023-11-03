package com.xiaopeng.xui.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes13.dex */
public class XRadioDrawable extends Drawable {
    private float DIMEN_18;
    private int mBgColor;
    private int mBgDisableColor;
    private int mBgEnableNormalColor;
    private int mBgEnablePressedColor;
    private float mCenterX;
    private float mCenterY;
    private int mCircleColor;
    private int mCircleDisableColor;
    private int mCircleEnableColor;
    private float mHeight;
    private int mInnerColor;
    private int mInnerDisableColor;
    private int mInnerEnableColor;
    private float mInnerRadius;
    private float mLeft;
    private Paint mPaint;
    private float mTop;
    private float mWidth;
    private float DIMEN_16 = 16.0f;
    private float DIMEN_16_5 = 16.5f;
    private float DIMEN_3 = 3.0f;
    private float DIMEN_8 = 8.0f;
    private final int[] stateSpecPress = {16842919};
    private final int[] stateSpecEnable = {16842910};
    private final int[] stateSpecCheck = {16842912};

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
        inflateAttrs(resources, theme);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet) {
        inflateAttrs(resources, null);
    }

    private void inflateAttrs(Resources resources, Resources.Theme theme) {
        this.mPaint = new Paint(1);
        if (resources != null) {
            this.mBgEnableNormalColor = resources.getColor(R.color.x_theme_primary_neutral_normal, theme);
            this.mBgEnablePressedColor = resources.getColor(R.color.x_theme_primary_neutral_pressed, theme);
            this.mBgDisableColor = resources.getColor(R.color.x_theme_primary_neutral_disable, theme);
            this.mCircleEnableColor = resources.getColor(R.color.x_theme_primary_normal, theme);
            this.mCircleDisableColor = resources.getColor(R.color.x_radio_disable_color, theme);
            this.mInnerEnableColor = resources.getColor(R.color.x_radio_inner_color, theme);
            this.mInnerDisableColor = resources.getColor(R.color.x_radio_inner_disable_color, theme);
            this.DIMEN_18 = TypedValue.applyDimension(1, 18.0f, resources.getDisplayMetrics());
            this.DIMEN_16 = TypedValue.applyDimension(1, 16.0f, resources.getDisplayMetrics());
            this.DIMEN_16_5 = TypedValue.applyDimension(1, 16.5f, resources.getDisplayMetrics());
            this.DIMEN_3 = TypedValue.applyDimension(1, 3.0f, resources.getDisplayMetrics());
            this.DIMEN_8 = TypedValue.applyDimension(1, 8.0f, resources.getDisplayMetrics());
        }
    }

    private void changeParams() {
        if (StateSet.stateSetMatches(this.stateSpecEnable, getState())) {
            if (StateSet.stateSetMatches(this.stateSpecPress, getState())) {
                this.mBgColor = this.mBgEnablePressedColor;
            } else {
                this.mBgColor = this.mBgEnableNormalColor;
            }
            this.mCircleColor = this.mCircleEnableColor;
            this.mInnerColor = this.mInnerEnableColor;
        } else {
            this.mBgColor = this.mBgDisableColor;
            this.mCircleColor = this.mCircleDisableColor;
            this.mInnerColor = this.mInnerDisableColor;
        }
        updateInnerShape();
    }

    private void updateInnerShape() {
        if (!StateSet.stateSetMatches(this.stateSpecCheck, getState())) {
            this.mLeft = this.mCenterX - this.DIMEN_16_5;
            this.mWidth = this.DIMEN_16_5 * 2.0f;
            this.mTop = this.mCenterY;
            this.mHeight = 0.0f;
            this.mInnerRadius = 0.0f;
            return;
        }
        this.mLeft = this.mCenterX - this.DIMEN_8;
        this.mWidth = this.DIMEN_16;
        this.mTop = this.mCenterY - this.DIMEN_8;
        this.mHeight = this.DIMEN_16;
        this.mInnerRadius = this.DIMEN_8;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mBgColor);
        canvas.drawCircle(this.mCenterX, this.mCenterY, this.DIMEN_18, this.mPaint);
        this.mPaint.setColor(this.mCircleColor);
        if (!StateSet.stateSetMatches(this.stateSpecCheck, getState())) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.DIMEN_3);
            canvas.drawCircle(this.mCenterX, this.mCenterY, this.DIMEN_16_5, this.mPaint);
        } else {
            canvas.drawCircle(this.mCenterX, this.mCenterY, this.DIMEN_18, this.mPaint);
        }
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mInnerColor);
        canvas.drawRoundRect(this.mLeft, this.mTop, this.mLeft + this.mWidth, this.mTop + this.mHeight, this.mInnerRadius, this.mInnerRadius, this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        changeParams();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mCenterX = rect.width() / 2.0f;
        this.mCenterY = rect.height() / 2.0f;
        changeParams();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }
}