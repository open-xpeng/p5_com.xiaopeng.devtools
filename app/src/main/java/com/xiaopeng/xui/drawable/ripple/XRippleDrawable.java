package com.xiaopeng.xui.drawable.ripple;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes13.dex */
public class XRippleDrawable extends Drawable {
    private static final String TAG = XRippleDrawable.class.getSimpleName();
    private boolean mIsTouchDown;
    private float mDownX = -1.0f;
    private float mDownY = -1.0f;
    private int[] stateSpecPress = new int[2];
    private XRipple mRipple = new XRipple();

    public XRippleDrawable() {
        this.stateSpecPress[0] = 16842919;
        this.stateSpecPress[1] = 16842910;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        if (iArr != null && this.mRipple != null) {
            if (StateSet.stateSetMatches(this.stateSpecPress, iArr) && !this.mIsTouchDown && this.mDownX > 0.0f && this.mDownY > 0.0f) {
                this.mIsTouchDown = true;
                this.mRipple.pressDown(this.mDownX, this.mDownY);
            } else if (this.mIsTouchDown) {
                this.mIsTouchDown = false;
                this.mRipple.pressUp();
            }
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        initRipple(rect);
    }

    private void initRipple(Rect rect) {
        this.mRipple.setCallback(getCallback());
        this.mRipple.setRippleRect(new RectF(rect.left, rect.top, rect.right, rect.bottom));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mRipple != null) {
            this.mRipple.drawRipple(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
        this.mDownX = f;
        this.mDownY = f2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public XRipple getXRipple() {
        return this.mRipple;
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

    private void inflateAttrs(Resources resources, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes;
        if (resources != null && attributeSet != null) {
            if (theme != null) {
                obtainAttributes = theme.obtainStyledAttributes(attributeSet, R.styleable.XRippleDrawable, 0, 0);
            } else {
                obtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.XRippleDrawable);
            }
            int color = obtainAttributes.getColor(R.styleable.XRippleDrawable_rippleBackgroundColor, 0);
            int color2 = obtainAttributes.getColor(R.styleable.XRippleDrawable_rippleColor, resources.getColor(R.color.x_ripple_default_color, theme));
            boolean z = obtainAttributes.getBoolean(R.styleable.XRippleDrawable_rippleSupportScale, false);
            int i = obtainAttributes.getInt(R.styleable.XRippleDrawable_rippleDuration, resources.getInteger(R.integer.x_ripple_default_anim));
            this.mRipple.setRippleRadius(obtainAttributes.getDimensionPixelSize(R.styleable.XRippleDrawable_rippleRadius, 0));
            this.mRipple.setRippleColor(color2);
            this.mRipple.setRippleBackgroundColor(color);
            this.mRipple.setSupportScale(z);
            long j = i;
            this.mRipple.setPressDuration(j);
            this.mRipple.setUpDuration(j);
            obtainAttributes.recycle();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.mRipple != null && visible) {
            this.mRipple.setVisible(z);
        }
        return visible;
    }
}
