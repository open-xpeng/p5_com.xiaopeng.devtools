package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.media.MediaPlayer2;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.annotation.CallSuper;
import androidx.appcompat.widget.AppCompatEditText;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.xpui.R;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: classes13.dex */
public class XNumberPicker extends XLinearLayout {
    private static final g adz = new g();
    private static final char[] aeo = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641, 1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785, 2406, 2407, 2408, 2409, 2410, 2411, 2412, 2413, 2414, 2415, 2534, 2535, 2536, 2537, 2538, 2539, 2540, 2541, 2542, 2543, 3302, 3303, 3304, 3305, 3306, 3307, 3308, 3309, 3310, 3311};
    private int adA;
    private final int adB;
    private final boolean adC;
    private final int adD;
    private int adE;
    private String[] adF;
    private int adG;
    private int adH;
    private e adI;
    private d adJ;
    private c adK;
    private long adL;
    private final SparseArray<String> adM;
    private final int[] adN;
    private final Paint adO;
    private int adP;
    private int adQ;
    private int adR;
    private int adS;
    private final Scroller adT;
    private final Scroller adU;
    private int adV;
    private b adW;
    private a adX;
    private float adY;
    private long adZ;
    private boolean ady;
    private float aea;
    private int aeb;
    private boolean aec;
    private final int aed;
    private final Drawable aee;
    private final Drawable aef;
    private final int aeg;
    private boolean aeh;
    private boolean aei;
    private int aej;
    private int aek;
    private final f ael;
    private int aem;
    private boolean aen;
    private final int mMaxHeight;
    private int mMaxWidth;
    private int mMaximumFlingVelocity;
    private final int mMinHeight;
    private final int mMinWidth;
    private int mMinimumFlingVelocity;
    private int mScrollState;
    private ColorStateList mTextColors;
    private final int mTextSize;
    private int mTouchSlop;
    private int mValue;
    private VelocityTracker mVelocityTracker;
    private int mWidth;

    /* loaded from: classes13.dex */
    public interface c {
        String format(int i);
    }

    /* loaded from: classes13.dex */
    public interface d {
        void a(XNumberPicker xNumberPicker, int i);
    }

    /* loaded from: classes13.dex */
    public interface e {
        void onValueChange(XNumberPicker xNumberPicker, int i, int i2);
    }

    /* loaded from: classes13.dex */
    private static class g implements c {
        Formatter mFmt;
        char mZeroDigit;
        final StringBuilder mBuilder = new StringBuilder();
        final Object[] mArgs = new Object[1];

        g() {
            a(Locale.getDefault());
        }

        private void a(Locale locale) {
            this.mFmt = c(locale);
            this.mZeroDigit = b(locale);
        }

        @Override // com.xiaopeng.xui.widget.XNumberPicker.c
        public String format(int i) {
            Locale locale = Locale.getDefault();
            if (this.mZeroDigit != b(locale)) {
                a(locale);
            }
            this.mArgs[0] = Integer.valueOf(i);
            this.mBuilder.delete(0, this.mBuilder.length());
            this.mFmt.format("%02d", this.mArgs);
            return this.mFmt.toString();
        }

        private static char b(Locale locale) {
            return '0';
        }

        private Formatter c(Locale locale) {
            return new Formatter(this.mBuilder, locale);
        }
    }

    public static c getTwoDigitFormatter() {
        return adz;
    }

    public XNumberPicker(Context context) {
        this(context, null);
    }

    public XNumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.style.XNumberPicker);
    }

    public XNumberPicker(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XNumberPicker);
    }

    public XNumberPicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        boolean z;
        this.ady = true;
        this.adL = 300L;
        this.adM = new SparseArray<>();
        this.adN = new int[5];
        this.adR = Integer.MIN_VALUE;
        this.mScrollState = 0;
        this.aem = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XNumberPicker, i, i2);
        this.aen = obtainStyledAttributes.getBoolean(R.styleable.XNumberPicker_np_hideWheelUntilFocused, false);
        this.aeb = obtainStyledAttributes.getInt(R.styleable.XNumberPicker_np_textLayout, 1);
        this.aed = obtainStyledAttributes.getColor(R.styleable.XNumberPicker_np_solidColor, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.XNumberPicker_np_selectionDivider);
        if (drawable != null) {
            drawable.setCallback(this);
            drawable.setLayoutDirection(getLayoutDirection());
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
        }
        this.aee = drawable;
        this.aef = obtainStyledAttributes.getDrawable(R.styleable.XNumberPicker_np_symbol);
        this.aeg = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XNumberPicker_np_selectionDividerHeight, (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.adB = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XNumberPicker_np_selectionDividersDistance, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
        this.adA = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XNumberPicker_np_text_layout_margin, 0);
        this.mMinHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XNumberPicker_np_internalMinHeight, -1);
        this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XNumberPicker_np_internalMaxHeight, -1);
        if (this.mMinHeight == -1 || this.mMaxHeight == -1 || this.mMinHeight <= this.mMaxHeight) {
            this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XNumberPicker_np_internalMinWidth, -1);
            this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XNumberPicker_np_internalMaxWidth, -1);
            if (this.mMinWidth == -1 || this.mMaxWidth == -1 || this.mMinWidth <= this.mMaxWidth) {
                if (this.mMaxWidth == -1) {
                    z = true;
                } else {
                    z = false;
                }
                this.adC = z;
                this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XNumberPicker_android_textSize, 20);
                this.adD = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XNumberPicker_np_selectedTextSize, 20);
                this.mTextColors = obtainStyledAttributes.getColorStateList(R.styleable.XNumberPicker_android_textColor);
                if (this.mTextColors == null) {
                    this.mTextColors = ColorStateList.valueOf(context.getColor(17170444));
                }
                obtainStyledAttributes.recycle();
                this.ael = new f();
                setWillNotDraw(false);
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
                this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
                this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity() / 8;
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                switch (this.aeb) {
                    case 0:
                        paint.setTextAlign(Paint.Align.LEFT);
                        break;
                    case 1:
                        paint.setTextAlign(Paint.Align.CENTER);
                        break;
                    case 2:
                        paint.setTextAlign(Paint.Align.RIGHT);
                        break;
                }
                paint.setTextSize(Math.max(this.mTextSize, this.adD));
                paint.setColor(this.mTextColors.getDefaultColor());
                paint.setTypeface(Typeface.create(getResources().getString(R.string.x_font_typeface_medium), 0));
                this.adO = paint;
                this.adT = new Scroller(getContext(), null, true);
                this.adU = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
                if (getImportantForAccessibility() == 0) {
                    setImportantForAccessibility(1);
                }
                if (getFocusable() == 16) {
                    setFocusable(1);
                    setFocusableInTouchMode(true);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("minWidth > maxWidth");
        }
        throw new IllegalArgumentException("minHeight > maxHeight");
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            rq();
            rr();
            this.aej = (((getHeight() - this.adB) / 2) - this.aeg) + 5;
            this.aek = this.aej + (this.aeg * 2) + this.adB;
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(makeMeasureSpec(i, this.mMaxWidth), makeMeasureSpec(i2, this.mMaxHeight));
        int e2 = e(this.mMinWidth, getMeasuredWidth(), i);
        this.mWidth = e2;
        setMeasuredDimension(e2, e(this.mMinHeight, getMeasuredHeight(), i2));
    }

    private boolean a(Scroller scroller) {
        scroller.forceFinished(true);
        int finalY = scroller.getFinalY() - scroller.getCurrY();
        int i = this.adR - ((this.adS + finalY) % this.adP);
        if (i == 0) {
            return false;
        }
        if (Math.abs(i) > this.adP / 2) {
            if (i > 0) {
                i -= this.adP;
            } else {
                i += this.adP;
            }
        }
        scrollBy(0, finalY + i);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && motionEvent.getActionMasked() == 0) {
            rv();
            float y = motionEvent.getY();
            this.adY = y;
            this.aea = y;
            this.adZ = motionEvent.getEventTime();
            this.aeh = false;
            this.aei = false;
            if (this.adY < this.aej) {
                if (this.mScrollState == 0) {
                    this.ael.buttonPressDelayed(2);
                }
            } else if (this.adY > this.aek && this.mScrollState == 0) {
                this.ael.buttonPressDelayed(1);
            }
            getParent().requestDisallowInterceptTouchEvent(true);
            if (!this.adT.isFinished()) {
                this.adT.forceFinished(true);
                this.adU.forceFinished(true);
                eD(0);
            } else if (!this.adU.isFinished()) {
                this.adT.forceFinished(true);
                this.adU.forceFinished(true);
            } else if (this.adY < this.aej) {
                a(false, ViewConfiguration.getLongPressTimeout());
            } else if (this.adY > this.aek) {
                a(true, ViewConfiguration.getLongPressTimeout());
            } else {
                this.aei = true;
                rt();
            }
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            switch (motionEvent.getActionMasked()) {
                case 1:
                    ru();
                    rs();
                    this.ael.cancel();
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
                    int yVelocity = (int) velocityTracker.getYVelocity();
                    if (Math.abs(yVelocity) > this.mMinimumFlingVelocity) {
                        fling(yVelocity);
                        eD(2);
                    } else {
                        int y = (int) motionEvent.getY();
                        int abs = (int) Math.abs(y - this.adY);
                        long eventTime = motionEvent.getEventTime() - this.adZ;
                        if (abs <= this.mTouchSlop && eventTime < ViewConfiguration.getTapTimeout()) {
                            if (this.aei) {
                                this.aei = false;
                                performClick();
                            } else {
                                int i = (y / this.adP) - 2;
                                if (i > 0) {
                                    bh(true);
                                    this.ael.buttonTapped(1);
                                } else if (i < 0) {
                                    bh(false);
                                    this.ael.buttonTapped(2);
                                }
                            }
                        } else {
                            rw();
                        }
                        eD(0);
                    }
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                case 2:
                    if (!this.aeh) {
                        float y2 = motionEvent.getY();
                        if (this.mScrollState != 1) {
                            if (((int) Math.abs(y2 - this.adY)) > this.mTouchSlop) {
                                rv();
                                eD(1);
                            }
                        } else {
                            scrollBy(0, (int) (y2 - this.aea));
                            invalidate();
                        }
                        this.aea = y2;
                        break;
                    }
                    break;
            }
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            rv();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XLinearLayout, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (ThemeManager.isThemeChanged(configuration)) {
            this.mTextColors = getContext().getResources().getColorStateList(R.color.x_number_picker_text_color, null);
            postInvalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 23 || keyCode == 66) {
            rv();
        } else {
            switch (keyCode) {
                case 19:
                case 20:
                    switch (keyEvent.getAction()) {
                        case 0:
                            if (this.aec || (keyCode != 20 ? getValue() > getMinValue() : getValue() < getMaxValue())) {
                                requestFocus();
                                this.aem = keyCode;
                                rv();
                                if (this.adT.isFinished()) {
                                    bh(keyCode == 20);
                                }
                                return true;
                            }
                        case 1:
                            if (this.aem == keyCode) {
                                this.aem = -1;
                                return true;
                            }
                            break;
                    }
                    break;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            rv();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller scroller = this.adT;
        if (scroller.isFinished()) {
            scroller = this.adU;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (this.adV == 0) {
            this.adV = scroller.getStartY();
        }
        scrollBy(0, currY - this.adV);
        this.adV = currY;
        if (scroller.isFinished()) {
            b(scroller);
        } else {
            invalidate();
        }
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        int[] iArr = this.adN;
        int i3 = this.adS;
        if (!this.aec && i2 > 0 && iArr[2] <= this.adG) {
            this.adS = this.adR;
        } else if (!this.aec && i2 < 0 && iArr[2] >= this.adH) {
            this.adS = this.adR;
        } else {
            this.adS += i2;
            while (this.adS - this.adR > this.adE) {
                this.adS -= (this.adE * 2) + 1;
                f(iArr);
                setValueInternal(iArr[2], true);
                if (!this.aec && iArr[2] <= this.adG) {
                    this.adS = this.adR;
                }
            }
            while (this.adS - this.adR < (-this.adE)) {
                this.adS += (this.adE * 2) + 1;
                e(iArr);
                setValueInternal(iArr[2], true);
                if (!this.aec && iArr[2] >= this.adH) {
                    this.adS = this.adR;
                }
            }
            if (i3 != this.adS) {
                onScrollChanged(0, this.adS, 0, i3);
            }
        }
    }

    @Override // android.view.View
    protected int computeVerticalScrollOffset() {
        return this.adS;
    }

    @Override // android.view.View
    protected int computeVerticalScrollRange() {
        return ((this.adH - this.adG) + 1) * this.adP;
    }

    @Override // android.view.View
    protected int computeVerticalScrollExtent() {
        return getHeight();
    }

    @Override // android.view.View
    public int getSolidColor() {
        return this.aed;
    }

    public void setOnValueChangedListener(e eVar) {
        this.adI = eVar;
    }

    public void setOnScrollListener(d dVar) {
        this.adJ = dVar;
    }

    public void setFormatter(c cVar) {
        if (cVar == this.adK) {
            return;
        }
        this.adK = cVar;
        rp();
    }

    public void setValue(int i) {
        setValueInternal(i, false);
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    @Override // android.view.View
    public boolean performLongClick() {
        return super.performLongClick();
    }

    private void rn() {
        int i;
        if (!this.adC) {
            return;
        }
        int i2 = 0;
        if (this.adF == null) {
            float f2 = 0.0f;
            for (int i3 = 0; i3 <= 9; i3++) {
                float measureText = this.adO.measureText(eH(i3));
                if (measureText > f2) {
                    f2 = measureText;
                }
            }
            for (int i4 = this.adH; i4 > 0; i4 /= 10) {
                i2++;
            }
            i = (int) (i2 * f2);
        } else {
            int length = this.adF.length;
            String[] strArr = this.adF;
            int length2 = strArr.length;
            int i5 = 0;
            while (i2 < length2) {
                float measureText2 = this.adO.measureText(strArr[i2]);
                if (measureText2 > i5) {
                    i5 = (int) measureText2;
                }
                i2++;
            }
            i = i5;
        }
        if (this.mMaxWidth != i) {
            if (i > this.mMinWidth) {
                this.mMaxWidth = i;
            } else {
                this.mMaxWidth = this.mMinWidth;
            }
            invalidate();
        }
    }

    public boolean getWrapSelectorWheel() {
        return this.aec;
    }

    public void setWrapSelectorWheel(boolean z) {
        this.ady = z;
        ro();
    }

    private void ro() {
        boolean z = true;
        if (!((this.adH - this.adG) + 1 >= this.adN.length) || !this.ady) {
            z = false;
        }
        this.aec = z;
    }

    public void setOnLongPressUpdateInterval(long j) {
        this.adL = j;
    }

    public int getValue() {
        return this.mValue;
    }

    public int getMinValue() {
        return this.adG;
    }

    public void setMinValue(int i) {
        if (this.adG == i) {
            return;
        }
        if (i < 0) {
            throw new IllegalArgumentException("minValue must be >= 0");
        }
        this.adG = i;
        if (this.adG > this.mValue) {
            this.mValue = this.adG;
        }
        ro();
        invalidate();
    }

    public int getMaxValue() {
        return this.adH;
    }

    public void setMaxValue(int i) {
        if (this.adH == i) {
            return;
        }
        if (i < 0) {
            throw new IllegalArgumentException("maxValue must be >= 0");
        }
        this.adH = i;
        if (this.adH < this.mValue) {
            this.mValue = this.adH;
        }
        ro();
        invalidate();
    }

    public String[] getDisplayedValues() {
        return this.adF;
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.adF == strArr) {
            return;
        }
        this.adF = strArr;
        rp();
        rn();
    }

    public CharSequence getDisplayedValueForCurrentSelection() {
        return this.adM.get(getValue());
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        return 0.9f;
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        return 0.9f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XLinearLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        rv();
    }

    @Override // android.view.ViewGroup, android.view.View
    @CallSuper
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.aee;
        if (drawable != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidateDrawable(drawable);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @CallSuper
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.aee != null) {
            this.aee.jumpToCurrentState();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        float f2;
        float f3;
        boolean z = !this.aen || hasFocus();
        int[] iArr = this.adN;
        float f4 = this.adS;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            String str = this.adM.get(iArr[i2]);
            if (z) {
                if (i2 == 2) {
                    this.adO.setColor(this.mTextColors.getColorForState(SELECTED_STATE_SET, -16777216));
                    this.adO.setTextSize(this.adD);
                    int i3 = this.aeb;
                    if (i3 == 0) {
                        f3 = this.adA;
                    } else if (i3 != 2) {
                        f3 = (getRight() - getLeft()) / 2.0f;
                    } else {
                        f3 = (getRight() - getLeft()) - this.adA;
                    }
                    canvas.drawText(str, f3, f4, this.adO);
                } else {
                    this.adO.setColor(this.mTextColors.getDefaultColor());
                    this.adO.setTextSize(this.mTextSize);
                    int i4 = this.aeb;
                    if (i4 == 0) {
                        f2 = this.adA;
                    } else if (i4 != 2) {
                        f2 = (getRight() - getLeft()) / 2.0f;
                    } else {
                        f2 = (getRight() - getLeft()) - this.adA;
                    }
                    canvas.drawText(str, f2, f4, this.adO);
                }
            }
            if (i2 == 1) {
                i = this.adQ;
            } else {
                i = this.adP;
            }
            f4 += i;
        }
        if (z && this.aee != null) {
            int i5 = this.aej;
            int i6 = this.aeg + i5;
            this.aee.setBounds(0, i5, this.mWidth, i6);
            this.aee.draw(canvas);
            int i7 = this.aek;
            int i8 = i7 - this.aeg;
            this.aee.setBounds(0, i8, this.mWidth, i7);
            this.aee.draw(canvas);
            if (this.aef != null) {
                int i9 = ((2 * i6) + i8) / 3;
                int i10 = (i6 + (i8 * 2)) / 3;
                int i11 = this.aeb;
                if (i11 == 0) {
                    this.aef.setBounds(this.mWidth - 6, i9, this.mWidth, i9 + 6);
                    this.aef.draw(canvas);
                    this.aef.setBounds(this.mWidth - 6, i10 - 6, this.mWidth, i10);
                    this.aef.draw(canvas);
                } else if (i11 == 2) {
                    this.aef.setBounds(35, i9, 41, i9 + 6);
                    this.aef.draw(canvas);
                    this.aef.setBounds(35, i10 - 6, 41, i10);
                    this.aef.draw(canvas);
                }
            }
        }
    }

    private int makeMeasureSpec(int i, int i2) {
        if (i2 == -1) {
            return i;
        }
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        if (mode != Integer.MIN_VALUE) {
            if (mode != 0) {
                if (mode == 1073741824) {
                    return i;
                }
                throw new IllegalArgumentException("Unknown measure mode: " + mode);
            }
            return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(size, i2), 1073741824);
    }

    private int e(int i, int i2, int i3) {
        if (i != -1) {
            return resolveSizeAndState(Math.max(i, i2), i3, 0);
        }
        return i2;
    }

    private void rp() {
        this.adM.clear();
        int[] iArr = this.adN;
        int value = getValue();
        for (int i = 0; i < this.adN.length; i++) {
            int i2 = (i - 2) + value;
            if (this.aec) {
                i2 = eE(i2);
            }
            iArr[i] = i2;
            eF(iArr[i]);
        }
    }

    private void setValueInternal(int i, boolean z) {
        int min;
        if (this.mValue == i) {
            return;
        }
        if (this.aec) {
            min = eE(i);
        } else {
            min = Math.min(Math.max(i, this.adG), this.adH);
        }
        int i2 = this.mValue;
        this.mValue = min;
        if (z) {
            com.xiaopeng.xui.b.f.qN().play(2);
            y(i2, min);
        }
        rp();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bh(boolean z) {
        if (!a(this.adT)) {
            a(this.adU);
        }
        this.adV = 0;
        if (z) {
            this.adT.startScroll(0, 0, 0, -this.adP, 300);
        } else {
            this.adT.startScroll(0, 0, 0, this.adP, 300);
        }
        invalidate();
    }

    private void rq() {
        rp();
        int[] iArr = this.adN;
        this.adE = (int) ((((getBottom() - getTop()) - (((iArr.length - 1) * this.mTextSize) + this.adD)) / iArr.length) + 0.5f);
        this.adP = this.mTextSize + this.adE;
        this.adQ = this.adD + this.adE;
        this.adR = this.adE + (this.mTextSize / 2);
        this.adS = this.adR;
    }

    private void rr() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.mTextSize) / 2);
    }

    private void b(Scroller scroller) {
        if (scroller == this.adT) {
            rw();
            eD(0);
        }
    }

    private void eD(int i) {
        if (this.mScrollState == i) {
            return;
        }
        this.mScrollState = i;
        if (this.adJ != null) {
            this.adJ.a(this, i);
        }
    }

    private void fling(int i) {
        this.adV = 0;
        if (i > 0) {
            this.adT.fling(0, 0, 0, i, 0, 0, 0, Integer.MAX_VALUE);
        } else {
            this.adT.fling(0, Integer.MAX_VALUE, 0, i, 0, 0, 0, Integer.MAX_VALUE);
        }
        invalidate();
    }

    private int eE(int i) {
        if (i > this.adH) {
            return (this.adG + ((i - this.adH) % (this.adH - this.adG))) - 1;
        }
        if (i < this.adG) {
            return (this.adH - ((this.adG - i) % (this.adH - this.adG))) + 1;
        }
        return i;
    }

    private void e(int[] iArr) {
        if (iArr.length - 1 >= 0) {
            System.arraycopy(iArr, 1, iArr, 0, iArr.length - 1);
        }
        int i = iArr[iArr.length - 2] + 1;
        if (this.aec && i > this.adH) {
            i = this.adG;
        }
        iArr[iArr.length - 1] = i;
        eF(i);
    }

    private void f(int[] iArr) {
        if (iArr.length - 1 >= 0) {
            System.arraycopy(iArr, 0, iArr, 1, iArr.length - 1);
        }
        int i = iArr[1] - 1;
        if (this.aec && i < this.adG) {
            i = this.adH;
        }
        iArr[0] = i;
        eF(i);
    }

    private void eF(int i) {
        String str;
        SparseArray<String> sparseArray = this.adM;
        if (sparseArray.get(i) != null) {
            return;
        }
        if (i < this.adG || i > this.adH) {
            str = "";
        } else if (this.adF != null) {
            str = this.adF[i - this.adG];
        } else {
            str = eG(i);
        }
        sparseArray.put(i, str);
    }

    private String eG(int i) {
        return this.adK != null ? this.adK.format(i) : eH(i);
    }

    private void y(int i, int i2) {
        if (this.adI != null) {
            this.adI.onValueChange(this, i, this.mValue);
        }
    }

    private void a(boolean z, long j) {
        if (this.adW == null) {
            this.adW = new b();
        } else {
            removeCallbacks(this.adW);
        }
        this.adW.bi(z);
        postDelayed(this.adW, j);
    }

    private void rs() {
        if (this.adW != null) {
            removeCallbacks(this.adW);
        }
    }

    private void rt() {
        if (this.adX == null) {
            this.adX = new a();
        } else {
            removeCallbacks(this.adX);
        }
        postDelayed(this.adX, ViewConfiguration.getLongPressTimeout());
    }

    private void ru() {
        if (this.adX != null) {
            removeCallbacks(this.adX);
        }
    }

    private void rv() {
        if (this.adW != null) {
            removeCallbacks(this.adW);
        }
        if (this.adX != null) {
            removeCallbacks(this.adX);
        }
        this.ael.cancel();
    }

    private boolean rw() {
        int i = this.adR - this.adS;
        if (i == 0) {
            return false;
        }
        this.adV = 0;
        if (Math.abs(i) > this.adP / 2) {
            i += i > 0 ? -this.adP : this.adP;
        }
        this.adU.startScroll(0, 0, 0, i, MediaPlayer2.MEDIA_INFO_BAD_INTERLEAVING);
        invalidate();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class f implements Runnable {
        private final int aer = 1;
        private final int aes = 2;
        private int aet;
        private int mMode;

        f() {
        }

        public void cancel() {
            this.mMode = 0;
            this.aet = 0;
            XNumberPicker.this.removeCallbacks(this);
        }

        public void buttonPressDelayed(int i) {
            cancel();
            this.mMode = 1;
            this.aet = i;
            XNumberPicker.this.postDelayed(this, ViewConfiguration.getTapTimeout());
        }

        public void buttonTapped(int i) {
            cancel();
            this.mMode = 2;
            this.aet = i;
            XNumberPicker.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mMode == 1) {
                switch (this.aet) {
                    case 1:
                        XNumberPicker.this.invalidate(0, XNumberPicker.this.aek, XNumberPicker.this.getRight(), XNumberPicker.this.getBottom());
                        return;
                    case 2:
                        XNumberPicker.this.invalidate(0, 0, XNumberPicker.this.getRight(), XNumberPicker.this.aej);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements Runnable {
        private boolean aeq;

        b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void bi(boolean z) {
            this.aeq = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            XNumberPicker.this.bh(this.aeq);
            XNumberPicker.this.postDelayed(this, XNumberPicker.this.adL);
        }
    }

    /* loaded from: classes13.dex */
    public static class CustomEditText extends AppCompatEditText {
        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // android.widget.TextView
        public void onEditorAction(int i) {
            super.onEditorAction(i);
            if (i == 6) {
                clearFocus();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            XNumberPicker.this.performLongClick();
        }
    }

    private static String eH(int i) {
        return String.format(Locale.getDefault(), "%d", Integer.valueOf(i));
    }
}
