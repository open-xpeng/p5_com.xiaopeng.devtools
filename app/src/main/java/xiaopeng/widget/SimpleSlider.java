package xiaopeng.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

/* loaded from: classes13.dex */
public class SimpleSlider extends View {
    private float alh;
    private d atA;
    private a atB;
    private boolean atq;
    private boolean atr;
    private float ats;
    private Drawable att;
    private int atu;
    private int atv;
    private boolean atw;
    private float atx;
    private boolean aty;
    private int atz;
    private boolean mEnabled;
    private int mMax;
    private int mMin;
    private int mProgress;
    private Drawable mTickMark;
    protected int mTouchMode;

    /* loaded from: classes13.dex */
    public interface a {
        void b(SimpleSlider simpleSlider);

        void b(SimpleSlider simpleSlider, int i, boolean z);

        void c(SimpleSlider simpleSlider);
    }

    /* loaded from: classes13.dex */
    public interface d {
        boolean a(SimpleSlider simpleSlider, MotionEvent motionEvent);
    }

    public SimpleSlider(Context context) {
        this(context, null);
    }

    public SimpleSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.style.SimpleSlider);
    }

    public SimpleSlider(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.SimpleSlider);
    }

    public SimpleSlider(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mProgress = 0;
        this.mMin = 0;
        this.mMax = 100;
        this.atq = false;
        this.atr = false;
        this.ats = 1.0f;
        this.atu = -1;
        this.atv = -1;
        this.atw = false;
        this.atx = 0.0f;
        this.aty = false;
        this.mTouchMode = 0;
        this.mEnabled = true;
        init(context, attributeSet, i, i2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setEnabled(this.mEnabled);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SimpleSlider, i, i2);
        this.atu = obtainStyledAttributes.getResourceId(R.styleable.SimpleSlider_android_progressDrawable, this.atu);
        setProgressDrawable(obtainStyledAttributes.getDrawable(R.styleable.SimpleSlider_android_progressDrawable));
        this.atv = obtainStyledAttributes.getResourceId(R.styleable.SimpleSlider_android_tickMark, this.atv);
        setTickMark(obtainStyledAttributes.getDrawable(R.styleable.SimpleSlider_android_tickMark));
        this.atw = obtainStyledAttributes.getBoolean(R.styleable.SimpleSlider_ss_tickMarkAll, this.atw);
        this.atx = obtainStyledAttributes.getDimension(R.styleable.SimpleSlider_ss_tickMark_padding, this.atx);
        this.aty = obtainStyledAttributes.getBoolean(R.styleable.SimpleSlider_ss_tickMark_ProgressEnd, this.aty);
        this.ats = obtainStyledAttributes.getFloat(R.styleable.SimpleSlider_ss_slideScale, this.ats);
        this.mEnabled = obtainStyledAttributes.getBoolean(R.styleable.SimpleSlider_android_enabled, this.mEnabled);
        this.alh = obtainStyledAttributes.getFloat(R.styleable.SimpleSlider_android_disabledAlpha, 0.36f);
        setMin(obtainStyledAttributes.getInt(R.styleable.SimpleSlider_android_min, this.mMin));
        setMax(obtainStyledAttributes.getInt(R.styleable.SimpleSlider_android_max, this.mMax));
        setProgress(obtainStyledAttributes.getInt(R.styleable.SimpleSlider_android_progress, this.mProgress));
        this.mTouchMode = obtainStyledAttributes.getInt(R.styleable.SimpleSlider_ss_touchMode, this.mTouchMode);
        this.atA = fE(this.mTouchMode);
        obtainStyledAttributes.recycle();
        this.atz = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    protected d fE(int i) {
        if (i == 1) {
            return new b();
        }
        return new c();
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        if (this.att != null) {
            this.att.setHotspot(f, f2);
        }
    }

    int getScaledTouchSlop() {
        return this.atz;
    }

    public void setTouchEventHandler(d dVar) {
        this.atA = dVar;
    }

    public void vh() {
        if (this.atu != -1) {
            setProgressDrawable(getContext().getDrawable(this.atu));
        }
        if (this.atv != -1) {
            setTickMark(getContext().getDrawable(this.atv));
        }
        int range = getRange();
        setVisualProgress(range > 0 ? (getProgress() - getMin()) / range : 0.0f);
    }

    public int getMin() {
        return this.mMin;
    }

    public void setMin(int i) {
        if (this.atq && i > this.mMax) {
            i = this.mMax;
        }
        this.atr = true;
        if (this.atq && i != this.mMin) {
            this.mMin = i;
            postInvalidate();
            if (this.mProgress < i) {
                this.mProgress = i;
            }
            setProgress(this.mProgress);
            return;
        }
        this.mMin = i;
    }

    public int getMax() {
        return this.mMax;
    }

    public void setMax(int i) {
        if (this.atr && i < this.mMin) {
            i = this.mMin;
        }
        this.atq = true;
        if (this.atr && i != this.mMax) {
            this.mMax = i;
            postInvalidate();
            if (this.mProgress > i) {
                this.mProgress = i;
            }
            setProgress(this.mProgress);
            return;
        }
        this.mMax = i;
    }

    public int getRange() {
        return this.mMax - this.mMin;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public void setOnSlideChangeListener(a aVar) {
        this.atB = aVar;
    }

    public void setProgress(int i) {
        k(i, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(int i, boolean z) {
        int fF = fF(i);
        this.mProgress = fF;
        int i2 = this.mMax - this.mMin;
        setVisualProgress(i2 > 0 ? (fF - this.mMin) / i2 : 0.0f);
        if (this.atB != null) {
            this.atB.b(this, fF, z);
        }
    }

    public float getSlideScale() {
        return this.ats;
    }

    public void setSlideScale(float f) {
        this.ats = f;
    }

    public void setProgressDrawable(Drawable drawable) {
        if (this.att != drawable) {
            if (this.att != null) {
                this.att.setCallback(null);
                unscheduleDrawable(this.att);
            }
            this.att = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                drawable.setLayoutDirection(getLayoutDirection());
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                G(getWidth(), getHeight());
                vi();
            }
        }
    }

    public void setTickMark(Drawable drawable) {
        if (this.mTickMark != null) {
            this.mTickMark.setCallback(null);
        }
        this.mTickMark = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            drawable.setLayoutDirection(getLayoutDirection());
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
        }
        invalidate();
    }

    public Drawable getTickMark() {
        return this.mTickMark;
    }

    private void G(int i, int i2) {
        int paddingRight = i - (getPaddingRight() + getPaddingLeft());
        int paddingTop = i2 - (getPaddingTop() + getPaddingBottom());
        if (this.att != null) {
            this.att.setBounds(0, 0, paddingRight, paddingTop);
        }
    }

    private void vi() {
        boolean z;
        int[] drawableState = getDrawableState();
        Drawable drawable = this.att;
        if (drawable != null && drawable.isStateful()) {
            z = drawable.setState(drawableState);
        } else {
            z = false;
        }
        if (z) {
            invalidate();
        }
    }

    protected void setVisualProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        Drawable drawable = this.att;
        if ((drawable instanceof LayerDrawable) && (drawable = ((LayerDrawable) drawable).findDrawableByLayerId(16908301)) == null) {
            drawable = this.att;
        }
        if (drawable != null) {
            drawable.setLevel((int) (f * 10000.0f));
        } else {
            invalidate();
        }
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        if (this.att != null) {
            this.att.setAlpha(isEnabled() ? 255 : (int) (this.alh * 255.0f));
        }
        if (this.mTickMark != null) {
            this.mTickMark.setAlpha(isEnabled() ? 255 : (int) (255.0f * this.alh));
        }
        super.drawableStateChanged();
    }

    @Override // android.view.View
    protected boolean verifyDrawable(@NonNull Drawable drawable) {
        return drawable == this.att || super.verifyDrawable(drawable);
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 3) {
                switch (actionMasked) {
                    case 0:
                        setPressed(true);
                        break;
                }
                return super.dispatchTouchEvent(motionEvent);
            }
            setPressed(false);
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        if (this.atA != null) {
            return this.atA.a(this, motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void vj() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        G(i, i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.att != null) {
            this.att.draw(canvas);
        }
        drawTickMarks(canvas);
    }

    protected void drawTickMarks(Canvas canvas) {
        Drawable tickMark = getTickMark();
        if (tickMark != null) {
            int max = getMax() - getMin();
            if (max > 1) {
                int intrinsicWidth = tickMark.getIntrinsicWidth();
                int intrinsicHeight = tickMark.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                tickMark.setBounds(-i, -i2, i, i2);
                float width = (((getWidth() - getPaddingLeft()) - getPaddingRight()) - (this.atx * 2.0f)) / max;
                int save = canvas.save();
                canvas.translate(getPaddingLeft(), getHeight() >> 1);
                canvas.translate(this.atx + width, 0.0f);
                for (int i3 = 1; i3 < max && (this.atw || ((!this.aty || i3 <= this.mProgress) && (this.aty || i3 < this.mProgress))); i3++) {
                    tickMark.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    protected void onStartTrackingTouch() {
        if (this.atB != null) {
            this.atB.b(this);
        }
    }

    protected void onStopTrackingTouch() {
        if (this.atB != null) {
            this.atB.c(this);
        }
    }

    private int fF(int i) {
        return Math.min(this.mMax, Math.max(i, this.mMin));
    }

    public boolean isInScrollingContainer() {
        for (ViewParent parent = getParent(); parent != null && (parent instanceof ViewGroup); parent = parent.getParent()) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: classes13.dex */
    public static class c implements d {
        private float akM;
        private boolean akN;
        private SimpleSlider atC;
        private int atD;

        @Override // xiaopeng.widget.SimpleSlider.d
        public boolean a(SimpleSlider simpleSlider, MotionEvent motionEvent) {
            this.atC = simpleSlider;
            return g(motionEvent);
        }

        private boolean g(MotionEvent motionEvent) {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    this.akM = motionEvent.getX();
                    break;
                case 1:
                case 3:
                    if (this.akN) {
                        this.atC.onStopTrackingTouch();
                        this.akN = false;
                        break;
                    }
                    break;
                case 2:
                    if (this.akN) {
                        f(motionEvent);
                        break;
                    } else if (Math.abs(motionEvent.getX() - this.akM) > this.atC.getScaledTouchSlop()) {
                        this.atD = this.atC.getProgress();
                        this.akN = true;
                        this.atC.onStartTrackingTouch();
                        f(motionEvent);
                        this.atC.vj();
                        break;
                    }
                    break;
            }
            return true;
        }

        private void f(MotionEvent motionEvent) {
            this.atC.k(Math.round(this.atD + ((Math.round(motionEvent.getX() - this.akM) / ((this.atC.getWidth() - this.atC.getPaddingLeft()) - this.atC.getPaddingRight())) * this.atC.getSlideScale() * (this.atC.getMax() - this.atC.getMin())) + this.atC.getMin()), true);
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements d {
        private float akM;
        private boolean akN;
        private SimpleSlider atC;

        @Override // xiaopeng.widget.SimpleSlider.d
        public boolean a(SimpleSlider simpleSlider, MotionEvent motionEvent) {
            this.atC = simpleSlider;
            return e(motionEvent);
        }

        private boolean e(MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    if (this.atC.isInScrollingContainer()) {
                        this.akM = motionEvent.getX();
                        return true;
                    }
                    onStartTrackingTouch();
                    f(motionEvent);
                    this.atC.vj();
                    return true;
                case 1:
                    if (this.akN) {
                        f(motionEvent);
                        onStopTrackingTouch();
                        return true;
                    }
                    onStartTrackingTouch();
                    f(motionEvent);
                    onStopTrackingTouch();
                    return true;
                case 2:
                    if (this.akN) {
                        f(motionEvent);
                        return true;
                    } else if (Math.abs(motionEvent.getX() - this.akM) > this.atC.getScaledTouchSlop()) {
                        onStartTrackingTouch();
                        f(motionEvent);
                        this.atC.vj();
                        return true;
                    } else {
                        return true;
                    }
                case 3:
                    if (this.akN) {
                        onStopTrackingTouch();
                        return true;
                    }
                    return true;
                default:
                    return true;
            }
        }

        private void onStartTrackingTouch() {
            this.akN = true;
            this.atC.onStartTrackingTouch();
        }

        private void onStopTrackingTouch() {
            this.akN = false;
            this.atC.onStopTrackingTouch();
        }

        private void f(MotionEvent motionEvent) {
            this.atC.k(Math.round(0.0f + ((Math.round(motionEvent.getX()) / ((this.atC.getWidth() - this.atC.getPaddingLeft()) - this.atC.getPaddingRight())) * (this.atC.getMax() - this.atC.getMin())) + this.atC.getMin()), true);
        }
    }
}
