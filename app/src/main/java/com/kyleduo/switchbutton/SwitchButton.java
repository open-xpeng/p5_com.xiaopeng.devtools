package com.kyleduo.switchbutton;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;

/* loaded from: classes11.dex */
public class SwitchButton extends CompoundButton {
    private static int[] ks = {16842912, 16842910, 16842919};
    private static int[] kt = {-16842912, 16842910, 16842919};
    private float kA;
    private long kB;
    private boolean kC;
    private int kD;
    private PointF kE;
    private int kF;
    private int kG;
    private int kH;
    private int kI;
    private int kJ;
    private Drawable kK;
    private Drawable kL;
    private RectF kM;
    private RectF kN;
    private RectF kO;
    private RectF kP;
    private RectF kQ;
    private boolean kR;
    private boolean kS;
    private boolean kT;
    private ObjectAnimator kU;
    private float kV;
    private RectF kW;
    private float kX;
    private float kY;
    private float kZ;
    private Drawable ku;
    private ColorStateList kv;
    private ColorStateList kw;
    private float kx;
    private float ky;
    private RectF kz;
    private int la;
    private Paint lb;
    private float lc;
    private float ld;
    private float le;
    private boolean lf;
    private CompoundButton.OnCheckedChangeListener lg;
    private Layout mOffLayout;
    private Layout mOnLayout;
    private Paint mPaint;
    private CharSequence mTextOff;
    private CharSequence mTextOn;
    private TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    private int mTouchSlop;

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.kT = false;
        this.lf = true;
        init(attributeSet);
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.kT = false;
        this.lf = true;
        init(attributeSet);
    }

    public SwitchButton(Context context) {
        super(context);
        this.kT = false;
        this.lf = true;
        init(null);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        int i;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        String str;
        String str2;
        boolean z;
        int i2;
        Drawable drawable;
        float f6;
        float f7;
        float f8;
        ColorStateList colorStateList;
        Drawable drawable2;
        ColorStateList colorStateList2;
        float f9;
        boolean z2;
        float f10;
        float f11;
        TypedArray obtainStyledAttributes2;
        float f12;
        boolean z3;
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.la = ViewConfiguration.getPressedStateDuration() + ViewConfiguration.getTapTimeout();
        this.mPaint = new Paint(1);
        this.lb = new Paint(1);
        this.lb.setStyle(Paint.Style.STROKE);
        this.lb.setStrokeWidth(getResources().getDisplayMetrics().density);
        this.mTextPaint = getPaint();
        this.kM = new RectF();
        this.kN = new RectF();
        this.kO = new RectF();
        this.kE = new PointF();
        this.kz = new RectF();
        this.kP = new RectF();
        this.kQ = new RectF();
        this.kU = ObjectAnimator.ofFloat(this, "process", 0.0f, 0.0f).setDuration(250L);
        this.kU.setInterpolator(new AccelerateDecelerateInterpolator());
        this.kW = new RectF();
        float f13 = getResources().getDisplayMetrics().density;
        float f14 = f13 * 2.0f;
        float f15 = f13 * 20.0f;
        float f16 = f15 / 2.0f;
        if (attributeSet != null) {
            obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SwitchButton);
        } else {
            obtainStyledAttributes = null;
        }
        if (obtainStyledAttributes != null) {
            drawable = obtainStyledAttributes.getDrawable(R.styleable.SwitchButton_kswThumbDrawable);
            colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.SwitchButton_kswThumbColor);
            float dimension = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswThumbMargin, f14);
            float dimension2 = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswThumbMarginLeft, dimension);
            f8 = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswThumbMarginRight, dimension);
            float dimension3 = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswThumbMarginTop, dimension);
            float dimension4 = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswThumbMarginBottom, dimension);
            f3 = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswThumbWidth, f15);
            float dimension5 = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswThumbHeight, f15);
            float dimension6 = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswThumbRadius, Math.min(f3, dimension5) / 2.0f);
            float dimension7 = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswBackRadius, dimension6 + f14);
            drawable2 = obtainStyledAttributes.getDrawable(R.styleable.SwitchButton_kswBackDrawable);
            colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.SwitchButton_kswBackColor);
            float f17 = obtainStyledAttributes.getFloat(R.styleable.SwitchButton_kswBackMeasureRatio, 1.8f);
            int integer = obtainStyledAttributes.getInteger(R.styleable.SwitchButton_kswAnimationDuration, 250);
            boolean z4 = obtainStyledAttributes.getBoolean(R.styleable.SwitchButton_kswFadeBack, true);
            int color = obtainStyledAttributes.getColor(R.styleable.SwitchButton_kswTintColor, 0);
            String string = obtainStyledAttributes.getString(R.styleable.SwitchButton_kswTextOn);
            String string2 = obtainStyledAttributes.getString(R.styleable.SwitchButton_kswTextOff);
            f14 = obtainStyledAttributes.getDimension(R.styleable.SwitchButton_kswTextMarginH, Math.max(f14, dimension7 / 2.0f));
            boolean z5 = obtainStyledAttributes.getBoolean(R.styleable.SwitchButton_kswAutoAdjustTextPosition, true);
            obtainStyledAttributes.recycle();
            f5 = dimension7;
            f9 = dimension2;
            f6 = dimension4;
            f2 = dimension5;
            f4 = dimension6;
            f = f17;
            i = integer;
            z2 = z4;
            i2 = color;
            str2 = string2;
            f7 = dimension3;
            z = z5;
            str = string;
        } else {
            i = 250;
            f = 1.8f;
            f2 = f15;
            f3 = f2;
            f4 = f16;
            f5 = f4;
            str = null;
            str2 = null;
            z = true;
            i2 = 0;
            drawable = null;
            f6 = 0.0f;
            f7 = 0.0f;
            f8 = 0.0f;
            colorStateList = null;
            drawable2 = null;
            colorStateList2 = null;
            f9 = 0.0f;
            z2 = true;
        }
        if (attributeSet != null) {
            f10 = f6;
            f11 = f7;
            obtainStyledAttributes2 = getContext().obtainStyledAttributes(attributeSet, new int[]{16842970, 16842981});
        } else {
            f10 = f6;
            f11 = f7;
            obtainStyledAttributes2 = null;
        }
        if (obtainStyledAttributes2 != null) {
            f12 = f8;
            boolean z6 = obtainStyledAttributes2.getBoolean(0, true);
            boolean z7 = obtainStyledAttributes2.getBoolean(1, z6);
            setFocusable(z6);
            setClickable(z7);
            obtainStyledAttributes2.recycle();
        } else {
            f12 = f8;
        }
        this.mTextOn = str;
        this.mTextOff = str2;
        this.le = f14;
        this.lf = z;
        this.mThumbDrawable = drawable;
        this.kw = colorStateList2;
        this.kR = this.mThumbDrawable != null;
        this.kD = i2;
        if (this.kD == 0) {
            TypedValue typedValue = new TypedValue();
            z3 = true;
            if (getContext().getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true)) {
                this.kD = typedValue.data;
            } else {
                this.kD = 3309506;
            }
        } else {
            z3 = true;
        }
        if (!this.kR && this.kw == null) {
            this.kw = a.bA(this.kD);
            this.kF = this.kw.getDefaultColor();
        }
        if (this.kR) {
            f3 = Math.max(f3, this.mThumbDrawable.getMinimumWidth());
            f2 = Math.max(f2, this.mThumbDrawable.getMinimumHeight());
        }
        this.kE.set(f3, f2);
        this.ku = drawable2;
        this.kv = colorStateList;
        if (this.ku == null) {
            z3 = false;
        }
        this.kS = z3;
        if (!this.kS && this.kv == null) {
            this.kv = a.bB(this.kD);
            this.kG = this.kv.getDefaultColor();
            this.kH = this.kv.getColorForState(ks, this.kG);
        }
        this.kz.set(f9, f11, f12, f10);
        if (this.kz.width() >= 0.0f) {
            f = Math.max(f, 1.0f);
        }
        this.kA = f;
        this.kx = f4;
        this.ky = f5;
        this.kB = i;
        this.kC = z2;
        this.kU.setDuration(this.kB);
        if (isChecked()) {
            setProcess(1.0f);
        }
    }

    private Layout makeLayout(CharSequence charSequence) {
        return new StaticLayout(charSequence, this.mTextPaint, (int) Math.ceil(Layout.getDesiredWidth(charSequence, this.mTextPaint)), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mOnLayout == null && this.mTextOn != null) {
            this.mOnLayout = makeLayout(this.mTextOn);
        }
        if (this.mOffLayout == null && this.mTextOff != null) {
            this.mOffLayout = makeLayout(this.mTextOff);
        }
        setMeasuredDimension(bC(i), bD(i2));
    }

    private int bC(int i) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int d = d(this.kE.x * this.kA);
        if (this.kS) {
            d = Math.max(d, this.ku.getMinimumWidth());
        }
        float width = this.mOnLayout != null ? this.mOnLayout.getWidth() : 0.0f;
        float width2 = this.mOffLayout != null ? this.mOffLayout.getWidth() : 0.0f;
        if (width != 0.0f || width2 != 0.0f) {
            this.lc = Math.max(width, width2) + (this.le * 2.0f);
            float f = d;
            float f2 = f - this.kE.x;
            if (f2 < this.lc) {
                d = (int) (f + (this.lc - f2));
            }
        } else {
            this.lc = 0.0f;
        }
        int max = Math.max(d, d(d + this.kz.left + this.kz.right));
        int max2 = Math.max(Math.max(max, getPaddingLeft() + max + getPaddingRight()), getSuggestedMinimumWidth());
        if (mode == 1073741824) {
            return Math.max(max2, size);
        }
        if (mode == Integer.MIN_VALUE) {
            return Math.min(max2, size);
        }
        return max2;
    }

    private int bD(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int d = d(Math.max(this.kE.y, this.kE.y + this.kz.top + this.kz.right));
        float height = this.mOnLayout != null ? this.mOnLayout.getHeight() : 0.0f;
        float height2 = this.mOffLayout != null ? this.mOffLayout.getHeight() : 0.0f;
        if (height != 0.0f || height2 != 0.0f) {
            this.ld = Math.max(height, height2);
            d = d(Math.max(d, this.ld));
        } else {
            this.ld = 0.0f;
        }
        int max = Math.max(d, getSuggestedMinimumHeight());
        int max2 = Math.max(max, getPaddingTop() + max + getPaddingBottom());
        if (mode == 1073741824) {
            return Math.max(max2, size);
        }
        if (mode == Integer.MIN_VALUE) {
            return Math.min(max2, size);
        }
        return max2;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            setup();
        }
    }

    private int d(double d) {
        return (int) Math.ceil(d);
    }

    private void setup() {
        float paddingTop = getPaddingTop() + Math.max(0.0f, this.kz.top);
        float paddingLeft = getPaddingLeft() + Math.max(0.0f, this.kz.left);
        if (this.mOnLayout != null && this.mOffLayout != null && this.kz.top + this.kz.bottom > 0.0f) {
            paddingTop += (((((getMeasuredHeight() - getPaddingBottom()) - getPaddingTop()) - this.kE.y) - this.kz.top) - this.kz.bottom) / 2.0f;
        }
        if (this.kR) {
            this.kE.x = Math.max(this.kE.x, this.mThumbDrawable.getMinimumWidth());
            this.kE.y = Math.max(this.kE.y, this.mThumbDrawable.getMinimumHeight());
        }
        this.kM.set(paddingLeft, paddingTop, this.kE.x + paddingLeft, this.kE.y + paddingTop);
        float f = this.kM.left - this.kz.left;
        float min = Math.min(0.0f, ((Math.max(this.kE.x * this.kA, this.kE.x + this.lc) - this.kM.width()) - this.lc) / 2.0f);
        float min2 = Math.min(0.0f, (((this.kM.height() + this.kz.top) + this.kz.bottom) - this.ld) / 2.0f);
        this.kN.set(f + min, (this.kM.top - this.kz.top) + min2, (((f + this.kz.left) + Math.max(this.kE.x * this.kA, this.kE.x + this.lc)) + this.kz.right) - min, (this.kM.bottom + this.kz.bottom) - min2);
        this.kO.set(this.kM.left, 0.0f, (this.kN.right - this.kz.right) - this.kM.width(), 0.0f);
        this.ky = Math.min(Math.min(this.kN.width(), this.kN.height()) / 2.0f, this.ky);
        if (this.ku != null) {
            this.ku.setBounds((int) this.kN.left, (int) this.kN.top, d(this.kN.right), d(this.kN.bottom));
        }
        if (this.mOnLayout != null) {
            float width = this.kN.left + ((((this.kN.width() - this.kM.width()) - this.kz.right) - this.mOnLayout.getWidth()) / 2.0f) + (this.kz.left < 0.0f ? this.kz.left * (-0.5f) : 0.0f);
            if (!this.kS && this.lf) {
                width += this.ky / 4.0f;
            }
            float height = this.kN.top + ((this.kN.height() - this.mOnLayout.getHeight()) / 2.0f);
            this.kP.set(width, height, this.mOnLayout.getWidth() + width, this.mOnLayout.getHeight() + height);
        }
        if (this.mOffLayout != null) {
            float width2 = ((this.kN.right - ((((this.kN.width() - this.kM.width()) - this.kz.left) - this.mOffLayout.getWidth()) / 2.0f)) - this.mOffLayout.getWidth()) + (this.kz.right < 0.0f ? this.kz.right * 0.5f : 0.0f);
            if (!this.kS && this.lf) {
                width2 -= this.ky / 4.0f;
            }
            float height2 = this.kN.top + ((this.kN.height() - this.mOffLayout.getHeight()) / 2.0f);
            this.kQ.set(width2, height2, this.mOffLayout.getWidth() + width2, this.mOffLayout.getHeight() + height2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x012e  */
    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onDraw(android.graphics.Canvas r15) {
        /*
            Method dump skipped, instructions count: 498
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kyleduo.switchbutton.SwitchButton.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (!this.kR && this.kw != null) {
            this.kF = this.kw.getColorForState(getDrawableState(), this.kF);
        } else {
            setDrawableState(this.mThumbDrawable);
        }
        int[] iArr = isChecked() ? kt : ks;
        ColorStateList textColors = getTextColors();
        if (textColors != null) {
            int defaultColor = textColors.getDefaultColor();
            this.kI = textColors.getColorForState(ks, defaultColor);
            this.kJ = textColors.getColorForState(kt, defaultColor);
        }
        if (!this.kS && this.kv != null) {
            this.kG = this.kv.getColorForState(getDrawableState(), this.kG);
            this.kH = this.kv.getColorForState(iArr, this.kG);
            return;
        }
        if ((this.ku instanceof StateListDrawable) && this.kC) {
            this.ku.setState(iArr);
            this.kL = this.ku.getCurrent().mutate();
        } else {
            this.kL = null;
        }
        setDrawableState(this.ku);
        if (this.ku != null) {
            this.kK = this.ku.getCurrent().mutate();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && isClickable() && isFocusable()) {
            int action = motionEvent.getAction();
            float x = motionEvent.getX() - this.kX;
            float y = motionEvent.getY() - this.kY;
            switch (action) {
                case 0:
                    cD();
                    this.kX = motionEvent.getX();
                    this.kY = motionEvent.getY();
                    this.kZ = this.kX;
                    setPressed(true);
                    break;
                case 1:
                case 3:
                    setPressed(false);
                    boolean statusBasedOnPos = getStatusBasedOnPos();
                    float eventTime = (float) (motionEvent.getEventTime() - motionEvent.getDownTime());
                    if (x < this.mTouchSlop && y < this.mTouchSlop && eventTime < this.la) {
                        performClick();
                        break;
                    } else if (statusBasedOnPos != isChecked()) {
                        playSoundEffect(0);
                        setChecked(statusBasedOnPos);
                        break;
                    } else {
                        v(statusBasedOnPos);
                        break;
                    }
                    break;
                case 2:
                    float x2 = motionEvent.getX();
                    setProcess(getProcess() + ((x2 - this.kZ) / this.kO.width()));
                    this.kZ = x2;
                    break;
            }
            return true;
        }
        return false;
    }

    private boolean getStatusBasedOnPos() {
        return getProcess() > 0.5f;
    }

    public final float getProcess() {
        return this.kV;
    }

    public final void setProcess(float f) {
        if (f <= 1.0f) {
            if (f < 0.0f) {
                f = 0.0f;
            }
        } else {
            f = 1.0f;
        }
        this.kV = f;
        invalidate();
    }

    @Override // android.widget.CompoundButton, android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    protected void v(boolean z) {
        if (this.kU == null) {
            return;
        }
        if (this.kU.isRunning()) {
            this.kU.cancel();
        }
        this.kU.setDuration(this.kB);
        if (z) {
            this.kU.setFloatValues(this.kV, 1.0f);
        } else {
            this.kU.setFloatValues(this.kV, 0.0f);
        }
        this.kU.start();
    }

    private void cD() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (isChecked() != z) {
            v(z);
        }
        super.setChecked(z);
    }

    public void setCheckedNoEvent(boolean z) {
        if (this.lg == null) {
            setChecked(z);
            return;
        }
        super.setOnCheckedChangeListener(null);
        setChecked(z);
        setOnCheckedChangeListener(this.lg);
    }

    public void setCheckedImmediatelyNoEvent(boolean z) {
        if (this.lg == null) {
            setCheckedImmediately(z);
            return;
        }
        super.setOnCheckedChangeListener(null);
        setCheckedImmediately(z);
        setOnCheckedChangeListener(this.lg);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        super.setOnCheckedChangeListener(onCheckedChangeListener);
        this.lg = onCheckedChangeListener;
    }

    public void setCheckedImmediately(boolean z) {
        super.setChecked(z);
        if (this.kU != null && this.kU.isRunning()) {
            this.kU.cancel();
        }
        setProcess(z ? 1.0f : 0.0f);
        invalidate();
    }

    private void setDrawableState(Drawable drawable) {
        if (drawable != null) {
            drawable.setState(getDrawableState());
            invalidate();
        }
    }

    public void setDrawDebugRect(boolean z) {
        this.kT = z;
        invalidate();
    }

    public long getAnimationDuration() {
        return this.kB;
    }

    public void setAnimationDuration(long j) {
        this.kB = j;
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    public void setThumbDrawable(Drawable drawable) {
        this.mThumbDrawable = drawable;
        this.kR = this.mThumbDrawable != null;
        setup();
        refreshDrawableState();
        requestLayout();
        invalidate();
    }

    public void setThumbDrawableRes(int i) {
        setThumbDrawable(ContextCompat.getDrawable(getContext(), i));
    }

    public Drawable getBackDrawable() {
        return this.ku;
    }

    public void setBackDrawable(Drawable drawable) {
        this.ku = drawable;
        this.kS = this.ku != null;
        setup();
        refreshDrawableState();
        requestLayout();
        invalidate();
    }

    public void setBackDrawableRes(int i) {
        setBackDrawable(ContextCompat.getDrawable(getContext(), i));
    }

    public ColorStateList getBackColor() {
        return this.kv;
    }

    public void setBackColor(ColorStateList colorStateList) {
        this.kv = colorStateList;
        if (this.kv != null) {
            setBackDrawable(null);
        }
        invalidate();
    }

    public void setBackColorRes(int i) {
        setBackColor(ContextCompat.getColorStateList(getContext(), i));
    }

    public ColorStateList getThumbColor() {
        return this.kw;
    }

    public void setThumbColor(ColorStateList colorStateList) {
        this.kw = colorStateList;
        if (this.kw != null) {
            setThumbDrawable(null);
        }
    }

    public void setThumbColorRes(int i) {
        setThumbColor(ContextCompat.getColorStateList(getContext(), i));
    }

    public float getBackMeasureRatio() {
        return this.kA;
    }

    public void setBackMeasureRatio(float f) {
        this.kA = f;
        requestLayout();
    }

    public RectF getThumbMargin() {
        return this.kz;
    }

    public void setThumbMargin(RectF rectF) {
        if (rectF == null) {
            a(0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            a(rectF.left, rectF.top, rectF.right, rectF.bottom);
        }
    }

    public void a(float f, float f2, float f3, float f4) {
        this.kz.set(f, f2, f3, f4);
        requestLayout();
    }

    public void a(float f, float f2) {
        this.kE.set(f, f2);
        setup();
        requestLayout();
    }

    public float getThumbWidth() {
        return this.kE.x;
    }

    public float getThumbHeight() {
        return this.kE.y;
    }

    public void setThumbSize(PointF pointF) {
        if (pointF == null) {
            float f = getResources().getDisplayMetrics().density * 20.0f;
            a(f, f);
            return;
        }
        a(pointF.x, pointF.y);
    }

    public PointF getThumbSizeF() {
        return this.kE;
    }

    public float getThumbRadius() {
        return this.kx;
    }

    public void setThumbRadius(float f) {
        this.kx = f;
        if (!this.kR) {
            invalidate();
        }
    }

    public PointF getBackSizeF() {
        return new PointF(this.kN.width(), this.kN.height());
    }

    public float getBackRadius() {
        return this.ky;
    }

    public void setBackRadius(float f) {
        this.ky = f;
        if (!this.kS) {
            invalidate();
        }
    }

    public void setFadeBack(boolean z) {
        this.kC = z;
    }

    public int getTintColor() {
        return this.kD;
    }

    public void setTintColor(int i) {
        this.kD = i;
        this.kw = a.bA(this.kD);
        this.kv = a.bB(this.kD);
        this.kS = false;
        this.kR = false;
        refreshDrawableState();
        invalidate();
    }

    public void a(CharSequence charSequence, CharSequence charSequence2) {
        this.mTextOn = charSequence;
        this.mTextOff = charSequence2;
        this.mOnLayout = null;
        this.mOffLayout = null;
        requestLayout();
        invalidate();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.lh = this.mTextOn;
        savedState.li = this.mTextOff;
        return savedState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        a(savedState.lh, savedState.li);
        super.onRestoreInstanceState(savedState.getSuperState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.kyleduo.switchbutton.SwitchButton.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: aA */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: bE */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        CharSequence lh;
        CharSequence li;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.lh = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.li = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.lh, parcel, i);
            TextUtils.writeToParcel(this.li, parcel, i);
        }
    }
}
