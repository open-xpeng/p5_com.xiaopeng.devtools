package com.xiaopeng.xui.drawable.progress;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes13.dex */
public class XCircularProgressBgDrawable extends Drawable {
    private static final float STROKE_WIDTH_DEFAULT = 0.0f;
    private Drawable mIndicatorPause;
    private Drawable mIndicatorPlay;
    private Drawable mIndicatorStart;
    private Drawable mIndicatorStop;
    private float mInset;
    private int mProgressBgColor;
    private float mStrokeWidth;
    private Rect mTmpIndicatorBounds = new Rect();
    private Paint mPaint = new Paint();
    private Rect mOutBounds = getBounds();
    private RectF mInnerBounds = new RectF(getBounds());
    private boolean mShowIndicator = true;
    private boolean mPlayingStop = false;
    private boolean mPlayingPause = false;
    private boolean mPaused = false;
    private boolean mStartDownload = false;

    public XCircularProgressBgDrawable() {
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setColor(this.mProgressBgColor);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        updateAttr(resources, attributeSet, theme);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet) throws IOException, XmlPullParserException {
        super.inflate(resources, xmlPullParser, attributeSet);
        updateAttr(resources, attributeSet, null);
    }

    private void updateAttr(Resources resources, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes;
        if (theme != null) {
            obtainAttributes = theme.obtainStyledAttributes(attributeSet, R.styleable.XCircularProgressBgDrawable, 0, 0);
        } else {
            obtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.XCircularProgressBgDrawable);
        }
        this.mProgressBgColor = obtainAttributes.getColor(R.styleable.XCircularProgressBgDrawable_progress_backgroundColor, resources.getColor(R.color.x_circular_progress_circle_bg_color, theme));
        this.mPaint.setColor(this.mProgressBgColor);
        this.mStrokeWidth = obtainAttributes.getDimension(R.styleable.XCircularProgressBgDrawable_progress_background_strokeWidth, 0.0f);
        this.mInset = obtainAttributes.getDimensionPixelSize(R.styleable.XCircularProgressBgDrawable_progress_background_inset, 0);
        this.mIndicatorPlay = obtainAttributes.getDrawable(R.styleable.XCircularProgressBgDrawable_progress_bg_indicator_play);
        this.mIndicatorPause = obtainAttributes.getDrawable(R.styleable.XCircularProgressBgDrawable_progress_bg_indicator_pause);
        this.mIndicatorStop = obtainAttributes.getDrawable(R.styleable.XCircularProgressBgDrawable_progress_bg_indicator_stop);
        this.mIndicatorStart = obtainAttributes.getDrawable(R.styleable.XCircularProgressBgDrawable_progress_bg_indicator_start);
        obtainAttributes.recycle();
    }

    public void setStrokeWidth(float f) {
        this.mStrokeWidth = f;
    }

    public void setInset(float f) {
        this.mInset = f;
    }

    public void setIndicatorPlay(Drawable drawable) {
        this.mIndicatorPlay = drawable;
    }

    public void setIndicatorStop(Drawable drawable) {
        this.mIndicatorStop = drawable;
    }

    public void setIndicatorPause(Drawable drawable) {
        this.mIndicatorPause = drawable;
    }

    public void setIndicatorStart(Drawable drawable) {
        this.mIndicatorStart = drawable;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        int save = canvas.save();
        canvas.drawArc(this.mInnerBounds, 0.0f, 360.0f, false, this.mPaint);
        canvas.restoreToCount(save);
        if (this.mShowIndicator) {
            if (this.mPlayingStop) {
                if (this.mIndicatorStop != null) {
                    this.mIndicatorStop.draw(canvas);
                }
            } else if (this.mPlayingPause) {
                if (this.mIndicatorPause != null) {
                    this.mIndicatorPause.draw(canvas);
                }
            } else if (this.mPaused) {
                if (this.mIndicatorPlay != null) {
                    this.mIndicatorPlay.draw(canvas);
                }
            } else if (this.mStartDownload && this.mIndicatorStart != null) {
                this.mIndicatorStart.draw(canvas);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(@NonNull Rect rect) {
        super.onBoundsChange(rect);
        applyIndicatorBounds(this.mIndicatorPlay, this.mTmpIndicatorBounds);
        applyIndicatorBounds(this.mIndicatorPause, this.mTmpIndicatorBounds);
        applyIndicatorBounds(this.mIndicatorStop, this.mTmpIndicatorBounds);
        applyIndicatorBounds(this.mIndicatorStart, this.mTmpIndicatorBounds);
        if (this.mStrokeWidth == 0.0f) {
            this.mStrokeWidth = (this.mOutBounds.width() * 1.0f) / 10.0f;
        }
        float f = this.mStrokeWidth / 2.0f;
        this.mInnerBounds.set(rect);
        this.mInnerBounds.inset(this.mInset + f, this.mInset + f);
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (this.mIndicatorPause != null) {
            this.mIndicatorPause.setState(iArr);
        }
        if (this.mIndicatorPlay != null) {
            this.mIndicatorPlay.setState(iArr);
        }
        if (this.mIndicatorStart != null) {
            this.mIndicatorStart.setState(iArr);
        }
        if (this.mIndicatorStop != null) {
            this.mIndicatorStop.setState(iArr);
        }
        int length = iArr.length;
        boolean z5 = false;
        int i = 0;
        boolean z6 = false;
        while (true) {
            if (i >= length) {
                z = false;
                z2 = false;
                z3 = false;
                break;
            }
            int i2 = iArr[i];
            if (i2 == R.attr.progress_state_playing_stop) {
                z2 = false;
                z3 = false;
                z = true;
                break;
            } else if (i2 == R.attr.progress_state_playing_pause) {
                z = false;
                z3 = false;
                z2 = true;
                break;
            } else if (i2 == R.attr.progress_state_paused) {
                z = false;
                z2 = false;
                z3 = true;
                break;
            } else {
                if (i2 == R.attr.progress_state_start_download) {
                    z6 = true;
                }
                i++;
            }
        }
        if (z != this.mPlayingStop) {
            this.mPlayingStop = z;
            z4 = true;
        } else {
            z4 = false;
        }
        if (z2 != this.mPlayingPause) {
            this.mPlayingPause = z2;
            z4 = true;
        }
        if (z3 != this.mPaused) {
            this.mPaused = z3;
            z4 = true;
        }
        if (z6 != this.mStartDownload) {
            this.mStartDownload = z6;
            z4 = true;
        }
        if (z4) {
            if (this.mPlayingStop || this.mPlayingPause || this.mPaused || this.mStartDownload) {
                z5 = true;
            }
            this.mShowIndicator = z5;
            return true;
        }
        return super.onStateChange(iArr);
    }

    private void applyIndicatorBounds(Drawable drawable, Rect rect) {
        if (drawable == null || rect == null) {
            return;
        }
        Rect bounds = getBounds();
        float width = bounds.width();
        float height = bounds.height();
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth >= width || intrinsicHeight >= height) {
            rect.set(bounds);
            drawable.setBounds(rect);
            return;
        }
        int round = Math.round(bounds.left + ((width - intrinsicWidth) / 2.0f));
        int round2 = Math.round(bounds.top + ((height - intrinsicHeight) / 2.0f));
        rect.set(round, round2, Math.round(intrinsicWidth) + round, Math.round(intrinsicHeight) + round2);
        drawable.setBounds(rect);
    }
}