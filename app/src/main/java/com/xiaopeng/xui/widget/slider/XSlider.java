package com.xiaopeng.xui.widget.slider;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.XViewGroup;
import java.text.DecimalFormat;

/* loaded from: classes13.dex */
public class XSlider extends XViewGroup implements com.xiaopeng.vui.commons.b {
    private Paint ajQ;
    @ColorInt
    int ajX;
    private float akA;
    private String akB;
    private Paint akC;
    private int akD;
    private float akE;
    private LinearGradient akF;
    private LinearGradient akG;
    private int akH;
    private int akI;
    private int akJ;
    private BitmapDrawable akK;
    private boolean akL;
    private float akM;
    private boolean akN;
    private final Paint aka;
    private final Paint akb;
    private int akc;
    @ColorInt
    int akd;
    @ColorInt
    int ake;
    @ColorInt
    int akf;
    @ColorInt
    int akg;
    com.xiaopeng.xui.widget.slider.a akh;
    @ColorInt
    private int aki;
    private float akj;
    private float akk;
    private float akl;
    private float akm;
    private Paint akn;
    private Paint ako;
    private int akp;
    private String akq;
    private int akr;
    private int aks;
    private float akt;
    private float aku;
    private DecimalFormat akv;
    private float akw;
    private b akx;
    private a aky;
    private float akz;
    private int endColor;
    private int endIndex;
    private boolean isNight;
    private float mScaledTouchSlop;
    private int mStep;
    private int startIndex;
    private int upperLimit;

    /* loaded from: classes13.dex */
    public interface a {
        void a(XSlider xSlider, float f, String str, boolean z);
    }

    /* loaded from: classes13.dex */
    public interface b {
        void a(XSlider xSlider);

        void a(XSlider xSlider, float f, String str);

        void b(XSlider xSlider);
    }

    public XSlider(Context context) {
        super(context);
        this.aka = new Paint(1);
        this.akb = new Paint(1);
        this.akc = 92;
        this.ajX = -15945223;
        this.akd = 1543503872;
        this.ake = 1560281087;
        this.akf = -12871169;
        this.akg = -1;
        this.aki = 0;
        this.akj = 100.0f;
        this.akk = 644.0f;
        this.akl = 40.0f;
        this.akm = 16.0f;
        this.akp = 40;
        this.akr = -1;
        this.upperLimit = Integer.MIN_VALUE;
        this.akt = 16.0f;
        this.akA = 1.0f;
        this.endColor = 1555808977;
        this.akH = 1555808977;
        this.akI = -12871169;
        this.akJ = -12860929;
        this.mStep = 1;
        this.akL = false;
    }

    public XSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.aka = new Paint(1);
        this.akb = new Paint(1);
        this.akc = 92;
        this.ajX = -15945223;
        this.akd = 1543503872;
        this.ake = 1560281087;
        this.akf = -12871169;
        this.akg = -1;
        this.aki = 0;
        this.akj = 100.0f;
        this.akk = 644.0f;
        this.akl = 40.0f;
        this.akm = 16.0f;
        this.akp = 40;
        this.akr = -1;
        this.upperLimit = Integer.MIN_VALUE;
        this.akt = 16.0f;
        this.akA = 1.0f;
        this.endColor = 1555808977;
        this.akH = 1555808977;
        this.akI = -12871169;
        this.akJ = -12860929;
        this.mStep = 1;
        this.akL = false;
        c(context, attributeSet);
        rY();
        if (!isInEditMode()) {
            this.isNight = com.xiaopeng.xui.c.a.aB(getContext());
        }
    }

    public XSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.aka = new Paint(1);
        this.akb = new Paint(1);
        this.akc = 92;
        this.ajX = -15945223;
        this.akd = 1543503872;
        this.ake = 1560281087;
        this.akf = -12871169;
        this.akg = -1;
        this.aki = 0;
        this.akj = 100.0f;
        this.akk = 644.0f;
        this.akl = 40.0f;
        this.akm = 16.0f;
        this.akp = 40;
        this.akr = -1;
        this.upperLimit = Integer.MIN_VALUE;
        this.akt = 16.0f;
        this.akA = 1.0f;
        this.endColor = 1555808977;
        this.akH = 1555808977;
        this.akI = -12871169;
        this.akJ = -12860929;
        this.mStep = 1;
        this.akL = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XViewGroup, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!isInEditMode()) {
            this.isNight = com.xiaopeng.xui.c.a.aB(getContext());
        }
        postDelayed(new Runnable() { // from class: com.xiaopeng.xui.widget.slider.XSlider.1
            @Override // java.lang.Runnable
            public void run() {
                XSlider.this.invalidate();
            }
        }, 1000L);
    }

    private void rY() {
        if (!this.akL) {
            this.akh = new com.xiaopeng.xui.widget.slider.a(getContext());
        }
        this.akn = new Paint(1);
        this.akn.setStyle(Paint.Style.FILL);
        this.akn.setColor(this.akd);
        this.ajQ = new Paint(1);
        this.ajQ.setStyle(Paint.Style.FILL);
        this.ajQ.setStrokeCap(Paint.Cap.ROUND);
        this.ajQ.setColor(this.ajX);
        this.ajQ.setStrokeWidth(16.0f);
        this.ako = new Paint(1);
        this.ako.setStyle(Paint.Style.FILL);
        this.ako.setColor(-1);
        this.akC = new Paint(1);
        this.akC.setStyle(Paint.Style.FILL);
        this.akC.setStrokeCap(Paint.Cap.ROUND);
        this.akC.setStrokeWidth(12.0f);
        this.akC.setColor(this.akg);
        setEnabled(true);
        this.akK = (BitmapDrawable) ContextCompat.getDrawable(getContext(), R.drawable.x_slider_slideblock_night);
    }

    private void c(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XSlider);
            this.akq = obtainStyledAttributes.getString(R.styleable.XSlider_slider_unit);
            this.startIndex = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_start_index, 0);
            this.mStep = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_step, 1);
            this.akr = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_init_index, -1);
            this.endIndex = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_end_index, 100);
            this.upperLimit = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_upper_limit, Integer.MIN_VALUE);
            this.aks = obtainStyledAttributes.getInteger(R.styleable.XSlider_slider_index_decimal, 0);
            this.akB = obtainStyledAttributes.getString(R.styleable.XSlider_slider_unit_prefix);
            this.akd = obtainStyledAttributes.getColor(R.styleable.XSlider_slider_bg_color, this.akd);
            this.ajX = obtainStyledAttributes.getColor(R.styleable.XSlider_slider_bg_line_color, this.ajX);
            this.aki = obtainStyledAttributes.getColor(R.styleable.XSlider_slider_background, 0);
            this.akA = obtainStyledAttributes.getFloat(R.styleable.XSlider_slider_accuracy, 0.0f);
            this.akL = obtainStyledAttributes.getBoolean(R.styleable.XSlider_slider_hide_pop, false);
            if (this.akr == -1) {
                this.akr = this.startIndex > this.endIndex ? this.endIndex : this.startIndex;
            }
            this.akw = this.akr - this.startIndex;
            if (this.endIndex == this.startIndex) {
                throw new RuntimeException("startIndex = endIndex!!! please check the xml");
            }
            this.akv = this.aks == 0 ? null : this.aks == 1 ? new DecimalFormat("0.0") : new DecimalFormat("0.00");
            if (this.akA == 0.0f) {
                this.akA = this.aks == 0 ? 1.0f : this.aks == 1 ? 0.1f : 0.01f;
            }
            obtainStyledAttributes.recycle();
        }
        setMinimumWidth(80);
        setBackground(new ColorDrawable(this.aki));
        this.mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredWidth;
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            measuredWidth = (int) this.akk;
        } else {
            measuredWidth = getMeasuredWidth();
        }
        setMeasuredDimension(measuredWidth, (int) this.akj);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isNight) {
            this.akn.setColor(this.akd);
            canvas.drawRoundRect(0.0f, (getHeightExIndicator() / 2.0f) - this.akt, getWidthExIndicator(), (getHeightExIndicator() / 2.0f) + this.akt, this.akm, this.akm, this.akn);
            return;
        }
        if (isEnabled()) {
            this.akb.setShader(this.akF);
            canvas.drawRoundRect(0.0f, (getHeightExIndicator() / 2.0f) - this.akt, getWidthExIndicator(), (getHeightExIndicator() / 2.0f) + this.akt, this.akm, this.akm, this.akb);
        } else {
            this.akn.setColor(this.ake);
            canvas.drawRoundRect(0.0f, (getHeightExIndicator() / 2.0f) - this.akt, getWidthExIndicator(), (getHeightExIndicator() / 2.0f) + this.akt, this.akm, this.akm, this.akn);
        }
        if (isEnabled()) {
            this.aka.setShader(this.akG);
            canvas.drawRoundRect(0.0f, (getHeightExIndicator() / 2.0f) - this.akt, sb() + 9.0f + 7.0f, (getHeightExIndicator() / 2.0f) + this.akt, this.akm, this.akm, this.aka);
            return;
        }
        this.akn.setColor(this.akf);
        canvas.drawRoundRect(0.0f, (getHeightExIndicator() / 2.0f) - this.akt, sb() + 9.0f + 7.0f, (getHeightExIndicator() / 2.0f) + this.akt, this.akm, this.akm, this.akn);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setPadding(0, 0, 0, 0);
        this.akD = i - 32;
        this.akE = this.akD / 29.0f;
        this.aku = (Math.abs((this.akr - this.startIndex) / (this.endIndex - this.startIndex)) * this.akD) + 16.0f;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 >= 30) {
                break;
            }
            Context context = getContext();
            if (this.aku <= (this.akE * i5) + 16.0f) {
                z = false;
            }
            addView(new SlideLineView(context, z));
            i5++;
        }
        this.akF = new LinearGradient(0.0f, (getHeightExIndicator() / 2.0f) - this.akt, 0.0f, (getHeightExIndicator() / 2.0f) + this.akt, new int[]{this.akH, this.endColor}, (float[]) null, Shader.TileMode.REPEAT);
        this.akG = new LinearGradient(16.0f, 0.0f, this.akD, 0.0f, new int[]{this.akJ, this.akI}, (float[]) null, Shader.TileMode.CLAMP);
        if (!this.akL) {
            this.akh.a(sb(), getPopString(), this.isNight, getWidth());
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float width = (getWidth() - 36) / 29.0f;
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            float f = 18.0f + (i5 * width);
            getChildAt(i5).layout((int) (f - 10.0f), (((int) getHeightExIndicator()) / 2) - 20, (int) (f + 10.0f), (((int) getHeightExIndicator()) / 2) + 20);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            switch (motionEvent.getAction()) {
                case 0:
                    if (rZ()) {
                        this.akM = motionEvent.getX();
                        break;
                    } else {
                        this.akN = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        if (this.akx != null) {
                            this.akx.a(this);
                        }
                        this.aku = motionEvent.getX();
                        c(true, false);
                        invalidateAll();
                        break;
                    }
                case 1:
                    if (this.akN) {
                        this.akN = false;
                    } else if (this.akx != null) {
                        this.akx.a(this);
                    }
                    this.aku = motionEvent.getX();
                    sa();
                    c(true, true);
                    getParent().requestDisallowInterceptTouchEvent(false);
                    if (this.akx != null) {
                        this.akx.b(this);
                    }
                    invalidateAll();
                    break;
                case 2:
                    if (this.akN) {
                        this.aku = motionEvent.getX();
                        c(true, false);
                        invalidateAll();
                        break;
                    } else if (Math.abs(motionEvent.getX() - this.akM) > this.mScaledTouchSlop) {
                        this.akN = true;
                        if (this.akx != null) {
                            this.akx.a(this);
                        }
                        this.aku = motionEvent.getX();
                        getParent().requestDisallowInterceptTouchEvent(true);
                        c(true, false);
                        invalidateAll();
                        break;
                    }
                    break;
                case 3:
                    if (this.akN) {
                        this.akN = false;
                    }
                    invalidateAll();
                    break;
            }
            return true;
        }
        return true;
    }

    private void invalidateAll() {
        invalidate();
        if (!this.akL) {
            this.akh.a(sb(), getPopString(), this.isNight, getWidth());
        }
    }

    private boolean rZ() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    private void sa() {
        float f;
        if (this.mStep == 1) {
            return;
        }
        this.aku = (((int) (((this.aku - 16.0f) / f) + 0.5d)) * (this.akD / (this.endIndex - this.startIndex))) + 16.0f;
    }

    private void c(boolean z, boolean z2) {
        float sb = sb();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            SlideLineView slideLineView = (SlideLineView) getChildAt(i);
            if (slideLineView.getX() + (slideLineView.getWidth() / 2) <= sb()) {
                if (!slideLineView.rX()) {
                    slideLineView.bs(true);
                }
            } else {
                slideLineView.bs(false);
            }
        }
        if (z) {
            float f = sb - 16.0f;
            this.akw = (f / this.akD) * (this.endIndex - this.startIndex);
            if (this.akx != null) {
                if (z2 || this.akw + this.startIndex >= this.akz + this.akA || this.akw + this.startIndex <= this.akz - this.akA || this.akz == 0.0f) {
                    this.akx.a(this, this.akw + this.startIndex, this.akq);
                    this.akw = (f / this.akD) * (this.endIndex - this.startIndex);
                    this.akz = ((int) this.akw) + this.startIndex;
                    l(this);
                }
            }
        }
    }

    public float getIndicatorValue() {
        return (this.akw + this.startIndex) * this.mStep;
    }

    public void setAccuracy(float f) {
        this.akA = f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        float sb = sb();
        if (sb == 0.0f) {
            return;
        }
        float heightExIndicator = getHeightExIndicator() / 2.0f;
        if (!this.akL) {
            this.akh.a(canvas, this.isNight, isEnabled());
        }
        if (!isEnabled()) {
            return;
        }
        if (this.isNight) {
            canvas.drawBitmap(this.akK.getBitmap(), sb - (this.akK.getBitmap().getWidth() / 2), heightExIndicator - (this.akK.getBitmap().getHeight() / 2), this.ajQ);
        } else {
            canvas.drawCircle(sb, heightExIndicator, 9.0f, this.ako);
        }
    }

    public float getHeightExIndicator() {
        return getHeight() + 40;
    }

    private float getWidthExIndicator() {
        return getWidth() + 0;
    }

    public float getIndicatorLocationX() {
        return this.aku;
    }

    private float sb() {
        if (this.aku < 16.0f) {
            return 16.0f;
        }
        float width = (getWidth() - 16) - sc();
        if (this.aku > width) {
            return width;
        }
        return this.aku;
    }

    private float sc() {
        if (this.upperLimit != Integer.MIN_VALUE && this.startIndex < this.endIndex && this.startIndex <= this.upperLimit && this.upperLimit <= this.endIndex) {
            return ((this.endIndex - this.upperLimit) * this.akD) / (this.endIndex - this.startIndex);
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XViewGroup, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    protected String getPopString() {
        if (this.akq == null) {
            this.akq = "";
        }
        if (this.akB == null) {
            this.akB = "";
        }
        if (this.akv == null) {
            if (this.mStep == 1) {
                return this.akB + (this.startIndex + ((int) this.akw)) + this.akq;
            }
            return this.akB + ((this.startIndex + ((int) (this.akw + 0.5d))) * this.mStep) + this.akq;
        }
        return this.akB + this.akv.format((this.startIndex + this.akw) * this.mStep) + this.akq;
    }

    public void setSliderProgressListener(b bVar) {
        this.akx = bVar;
    }

    public void setProgressChangeListener(a aVar) {
        this.aky = aVar;
    }

    public void setCurrentIndex(int i) {
        g(i, false);
    }

    public void g(final int i, final boolean z) {
        post(new Runnable() { // from class: com.xiaopeng.xui.widget.slider.-$$Lambda$XSlider$W29qCPpC1hxxwbKTT5zvQhNsiW0
            @Override // java.lang.Runnable
            public final void run() {
                XSlider.this.h(i, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(int i, boolean z) {
        this.aku = (((i - this.startIndex) / (this.endIndex - this.startIndex)) * this.akD) + 16.0f;
        this.akw = i - this.startIndex;
        invalidate();
        c(false, false);
        if (!this.akL) {
            this.akh.a(sb(), getPopString(), this.isNight, getWidth());
        }
        if (z && this.aky != null) {
            this.aky.a(this, this.akw + this.startIndex, this.akq, true);
        }
        if (qZ() != null && ((Float) qZ()).floatValue() == getIndicatorValue()) {
            return;
        }
        l(this);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (!z) {
            this.akN = false;
        }
        super.setEnabled(z);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(z);
        }
        setAlphaByEnable(z);
        invalidate();
    }

    public void setStartIndex(int i) {
        if (i == this.endIndex) {
            throw new RuntimeException("startIndex = endIndex!!!");
        }
        this.startIndex = i;
        post(new Runnable() { // from class: com.xiaopeng.xui.widget.slider.-$$Lambda$XSlider$5ogfebRIm1M1SR_hncOKyPTQmD0
            @Override // java.lang.Runnable
            public final void run() {
                XSlider.this.se();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void se() {
        if (!this.akL) {
            this.akh.a(sb(), getPopString(), this.isNight, getWidth());
        }
        invalidate();
    }

    public void setEndIndex(int i) {
        if (this.startIndex == i) {
            throw new RuntimeException("startIndex = endIndex!!!");
        }
        this.endIndex = i;
        post(new Runnable() { // from class: com.xiaopeng.xui.widget.slider.-$$Lambda$XSlider$cINLbNCBalJdPAOnK6_7eFGQE-s
            @Override // java.lang.Runnable
            public final void run() {
                XSlider.this.sd();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void sd() {
        invalidate();
    }

    public void setInitIndex(int i) {
        if (i > this.endIndex) {
            this.akr = this.endIndex;
        } else if (i < this.startIndex) {
            this.akr = this.startIndex;
        } else {
            this.akr = i;
            this.akw = i - this.startIndex;
            invalidate();
        }
    }

    private void setAlphaByEnable(boolean z) {
        this.akd = D(this.akd, z ? this.akc : this.akp);
        this.ake = D(this.ake, z ? this.akc : this.akp);
        this.akf = D(this.akf, z ? 255 : this.akp);
    }

    private int D(@ColorInt int i, int i2) {
        return (i & 16777215) | (i2 << 24);
    }
}
