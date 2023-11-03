package com.xiaopeng.xui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.xiaopeng.vui.commons.VuiElementType;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.view.a;

@Deprecated
/* loaded from: classes13.dex */
public class XTabLayout extends XLinearLayout implements com.xiaopeng.vui.commons.b {
    private float agA;
    private int agB;
    private int agC;
    private int agD;
    private float agE;
    private int agF;
    private int agG;
    private float agH;
    private boolean agI;
    private Paint agJ;
    private Paint agK;
    private Paint agL;
    private float agM;
    private float agN;
    private int agO;
    private int agP;
    private int agQ;
    private int agR;
    private int agS;
    private int agT;
    private int agU;
    private int agV;
    private int agW;
    private int agX;
    private int agY;
    private int agZ;
    private final int agt;
    private int agu;
    private boolean agv;
    private float agw;
    private ColorStateList agx;
    private float agy;
    private float agz;
    private float aha;
    private int ahb;
    private boolean ahc;
    private int ahd;
    private boolean ahe;
    private CharSequence[] ahf;
    private int[] ahg;
    private CharSequence[] ahh;
    @Nullable
    private b ahi;
    private View.OnClickListener ahj;
    private boolean ahk;
    private boolean ahl;
    private int mIndicatorColor;
    private float mIndicatorHeight;
    private Paint mPaint;
    private int mStyle;
    private ValueAnimator mValueAnimator;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public interface a {
        void onEnd();

        void onStart();
    }

    /* loaded from: classes13.dex */
    public interface b {
        boolean b(XTabLayout xTabLayout, int i, boolean z, boolean z2);

        void c(XTabLayout xTabLayout, int i, boolean z, boolean z2);

        void d(XTabLayout xTabLayout, int i, boolean z, boolean z2);
    }

    public XTabLayout(Context context) {
        this(context, null);
    }

    public XTabLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XTabLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XTabLayout(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2 == 0 ? R.style.XTabLayoutAppearance : i2);
        this.agt = 0;
        this.mPaint = new Paint(1);
        this.agJ = new Paint(1);
        this.agK = new Paint(1);
        this.agL = new Paint(1);
        this.agO = 0;
        this.agP = 0;
        this.agQ = 0;
        this.agR = 0;
        this.agS = 0;
        this.agT = 0;
        this.agU = 0;
        this.agV = 0;
        this.agW = 0;
        this.agX = 0;
        this.agY = 0;
        this.agZ = 0;
        this.aha = 0.6f;
        this.ahb = -1;
        this.mStyle = 2;
        this.ahj = new View.OnClickListener() { // from class: com.xiaopeng.xui.widget.XTabLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (XTabLayout.this.mValueAnimator == null || !XTabLayout.this.mValueAnimator.isRunning()) {
                    XTabLayout.this.b(XTabLayout.this.indexOfChild(view), true, true);
                }
            }
        };
        this.ahk = true;
        if (Build.VERSION.SDK_INT <= 26) {
            setLayerType(1, null);
        }
        getContext().getTheme();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.XTabLayout, i == 0 ? R.style.XTabLayoutAppearance : i, R.style.XTabLayoutAppearance);
        this.ahf = obtainStyledAttributes.getTextArray(R.styleable.XTabLayout_titles);
        this.agw = obtainStyledAttributes.getDimension(R.styleable.XTabLayout_titleTextSize, 35.0f);
        final com.xiaopeng.xui.view.b.b a2 = com.xiaopeng.xui.view.b.b.a(obtainStyledAttributes, R.styleable.XTabLayout_titleTextSize);
        if (a2 != null && this.abr != null) {
            this.abr.a(new a.InterfaceC0085a() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XTabLayout$4Cp2U8F9Ia0QSq7u967xh5maB-g
                @Override // com.xiaopeng.xui.view.a.InterfaceC0085a
                public final void onFontScaleChanged() {
                    XTabLayout.this.a(a2);
                }
            });
        }
        this.agx = obtainStyledAttributes.getColorStateList(R.styleable.XTabLayout_titleTextColorStateList);
        int integer = obtainStyledAttributes.getInteger(R.styleable.XTabLayout_selectTab, 0);
        this.agy = obtainStyledAttributes.getDimension(R.styleable.XTabLayout_indicatorWidth, 0.0f);
        this.agz = obtainStyledAttributes.getFraction(R.styleable.XTabLayout_indicatorWidthPercent, 1, 1, 0.7f);
        this.agM = obtainStyledAttributes.getDimension(R.styleable.XTabLayout_indicatorMaxHeight, eN(4));
        this.agN = obtainStyledAttributes.getDimension(R.styleable.XTabLayout_indicatorMinHeight, eN(2));
        this.mIndicatorHeight = this.agM;
        this.agA = obtainStyledAttributes.getDimension(R.styleable.XTabLayout_indicatorMarginBottom, eN(6));
        this.mIndicatorColor = obtainStyledAttributes.getResourceId(R.styleable.XTabLayout_indicatorColor, R.color.x_theme_primary_normal);
        this.agB = obtainStyledAttributes.getColor(R.styleable.XTabLayout_indicatorColorFrom, -1);
        this.agC = obtainStyledAttributes.getColor(R.styleable.XTabLayout_indicatorColorTo, -1);
        this.agD = obtainStyledAttributes.getColor(R.styleable.XTabLayout_indicatorShadowColor, -15880455);
        this.agE = obtainStyledAttributes.getDimension(R.styleable.XTabLayout_indicatorShadowRadius, eN(4));
        this.agF = obtainStyledAttributes.getResourceId(R.styleable.XTabLayout_indicatorColor2, R.color.x_table_indicator_color);
        this.agG = obtainStyledAttributes.getColor(R.styleable.XTabLayout_indicatorShadowColor2, -15880455);
        this.agH = obtainStyledAttributes.getDimension(R.styleable.XTabLayout_indicatorShadowRadius2, eN(4));
        this.agv = obtainStyledAttributes.getBoolean(R.styleable.XTabLayout_indicatorAnimatorEnable, true);
        this.ahd = obtainStyledAttributes.getResourceId(R.styleable.XTabLayout_titleTextColorStateList, -1);
        this.ahc = obtainStyledAttributes.getBoolean(R.styleable.XTabLayout_tabsBarStyle, false);
        this.ahe = obtainStyledAttributes.getBoolean(R.styleable.XTabLayout_tabCustomBackground, false);
        this.agu = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XTabLayout_tabPaddingNight, eN(40));
        this.agI = obtainStyledAttributes.getBoolean(R.styleable.XTabLayout_indicatorDayNightDiff, false);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.XTabLayout_tabLayoutIcons, 0);
        if (resourceId != 0) {
            TypedArray obtainTypedArray = getResources().obtainTypedArray(resourceId);
            this.ahg = new int[obtainTypedArray.length()];
            for (int i3 = 0; i3 < obtainTypedArray.length(); i3++) {
                this.ahg[i3] = obtainTypedArray.getResourceId(i3, 0);
            }
            obtainTypedArray.recycle();
        }
        this.ahh = obtainStyledAttributes.getTextArray(R.styleable.XTabLayout_tabLayoutVuiLabels);
        setGravity(17);
        obtainStyledAttributes.recycle();
        setWillNotDraw(false);
        init();
        b(integer, false, false);
        setStyle(isNight() ? 2 : 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(com.xiaopeng.xui.view.b.b bVar) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof TextView) {
                bVar.a((TextView) childAt);
            }
        }
    }

    @Nullable
    private View getSelectView() {
        return getChildAt(this.ahb);
    }

    public void setStyle(int i) {
        this.ahl = isNight();
        if (!this.agI) {
            i = 1;
        }
        this.mStyle = i;
        if (this.ahc) {
            this.mStyle = 2;
        }
        if (this.mStyle == 2) {
            setPadding(this.agu, 0, this.agu, 0);
        } else {
            setPadding(0, 0, 0, 0);
        }
        if (this.ahd > 0) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (childAt instanceof TextView) {
                    ((TextView) childAt).setTextColor(getResources().getColorStateList(this.ahd, getContext().getTheme()));
                } else if ((childAt instanceof ImageView) && this.ahg != null && this.ahg.length > i2) {
                    ((ImageView) childAt).setImageResource(this.ahg[i2]);
                }
            }
        }
        this.mPaint.setColor(getContext().getColor(this.mIndicatorColor));
        this.agK.setColor(getContext().getColor(this.agF));
        a(false, (a) null);
    }

    private void init() {
        this.mPaint.setStrokeWidth(0.0f);
        this.mPaint.setColor(getContext().getColor(this.mIndicatorColor));
        this.agJ.setStrokeWidth(0.0f);
        this.agJ.setColor(this.agD);
        if (Build.VERSION.SDK_INT <= 26) {
            setLayerType(1, this.agJ);
        }
        this.agK.setStrokeWidth(0.0f);
        this.agK.setColor(getContext().getColor(this.agF));
        this.agL.setStrokeWidth(0.0f);
        this.agL.setColor(this.agG);
        if (Build.VERSION.SDK_INT <= 26) {
            setLayerType(1, this.agL);
        }
        if (this.ahf != null && this.ahf.length > 0) {
            for (CharSequence charSequence : this.ahf) {
                m(charSequence);
            }
        } else if (this.ahg != null && this.ahg.length > 0) {
            for (int i = 0; i < this.ahg.length; i++) {
                String str = "";
                if (this.ahh != null && this.ahh.length > i) {
                    str = this.ahh[i].toString();
                }
                j(this.ahg[i], str);
            }
        }
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            getChildAt(i2).setOnClickListener(this.ahj);
        }
        setOnHierarchyChangeListener(new AnonymousClass2());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.xui.widget.XTabLayout$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    public class AnonymousClass2 implements ViewGroup.OnHierarchyChangeListener {
        AnonymousClass2() {
        }

        private void bj(final boolean z) {
            XTabLayout.this.post(new Runnable() { // from class: com.xiaopeng.xui.widget.XTabLayout.2.1
                @Override // java.lang.Runnable
                public void run() {
                    XTabLayout.this.a(true, new a() { // from class: com.xiaopeng.xui.widget.XTabLayout.2.1.1
                        @Override // com.xiaopeng.xui.widget.XTabLayout.a
                        public void onStart() {
                            if (XTabLayout.this.ahi != null) {
                                XTabLayout.this.ahi.c(XTabLayout.this, XTabLayout.this.ahb, z, false);
                            }
                        }

                        @Override // com.xiaopeng.xui.widget.XTabLayout.a
                        public void onEnd() {
                            if (XTabLayout.this.ahi != null) {
                                XTabLayout.this.ahi.d(XTabLayout.this, XTabLayout.this.ahb, z, false);
                            }
                        }
                    });
                }
            });
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            view2.setOnClickListener(XTabLayout.this.ahj);
            if (XTabLayout.this.ahb < 0) {
                XTabLayout.this.ahb = XTabLayout.this.indexOfChild(view2);
                view2.setSelected(true);
            }
            Object tag = view2.getTag();
            bj(tag == null ? false : ((Boolean) tag).booleanValue());
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            view2.setOnClickListener(null);
            Object tag = view2.getTag();
            bj(tag == null ? false : ((Boolean) tag).booleanValue());
        }
    }

    public void setIndicatorAnimatorEnable(boolean z) {
        this.agv = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        switch (this.mStyle) {
            case 1:
                a(canvas);
                break;
            case 2:
                b(canvas);
                break;
        }
        super.dispatchDraw(canvas);
    }

    public int b(CharSequence charSequence, int i) {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.x_tab_layout_title_view, (ViewGroup) this, false);
        textView.setText(charSequence);
        textView.setTextColor(this.agx);
        textView.setTextSize(0, this.agw);
        textView.setTag(Boolean.valueOf(i == this.ahb));
        if (i <= this.ahb) {
            this.ahb++;
        }
        textView.setSoundEffectsEnabled(isSoundEffectsEnabled());
        addView(textView, i);
        return i;
    }

    public int a(int i, int i2, String str) {
        XImageView xImageView = new XImageView(getContext());
        xImageView.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0f));
        xImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        xImageView.setImageResource(i);
        xImageView.a(VuiElementType.TEXTVIEW);
        xImageView.fk(str);
        xImageView.setTag(Boolean.valueOf(i2 == this.ahb));
        if (i2 <= this.ahb) {
            this.ahb++;
        }
        xImageView.setSoundEffectsEnabled(isSoundEffectsEnabled());
        addView(xImageView, i2);
        return i2;
    }

    public void setAllTabClickable(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setClickable(z);
        }
    }

    public int m(CharSequence charSequence) {
        return b(charSequence, getChildCount());
    }

    public int j(int i, String str) {
        return a(i, getChildCount(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i, boolean z, final boolean z2) {
        final boolean z3;
        if (i >= getTabCount() || i < 0 || i == this.ahb) {
            return;
        }
        if (this.ahi != null && this.ahi.b(this, i, true, z2)) {
            return;
        }
        final View childAt = getChildAt(i);
        View selectView = getSelectView();
        if (childAt != selectView) {
            if (childAt != null) {
                childAt.setSelected(true);
            }
            if (selectView != null) {
                selectView.setSelected(false);
            }
            this.ahb = i;
            z3 = true;
        } else {
            z3 = false;
        }
        a(z, new a() { // from class: com.xiaopeng.xui.widget.XTabLayout.3
            @Override // com.xiaopeng.xui.widget.XTabLayout.a
            public void onStart() {
                if (z3 && XTabLayout.this.ahi != null) {
                    if (childAt == null) {
                        XTabLayout.this.ahi.c(XTabLayout.this, -1, true, z2);
                    } else {
                        XTabLayout.this.ahi.c(XTabLayout.this, i, true, z2);
                    }
                }
            }

            @Override // com.xiaopeng.xui.widget.XTabLayout.a
            public void onEnd() {
                if (z3 && XTabLayout.this.ahi != null) {
                    if (childAt == null) {
                        XTabLayout.this.ahi.d(XTabLayout.this, -1, true, z2);
                    } else {
                        XTabLayout.this.ahi.d(XTabLayout.this, i, true, z2);
                    }
                }
                XTabLayout.this.l((View) XTabLayout.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XLinearLayout, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (com.xiaopeng.xui.c.a.isThemeChanged(configuration)) {
            setStyle(isNight() ? 2 : 1);
        }
    }

    private int eK(int i) {
        if (i < 0 || getWidth() <= 0) {
            return 0;
        }
        if (this.mStyle != 2) {
            return 0 + (i * (getWidth() / getTabCount()));
        }
        int width = (getWidth() - (this.agu * 2)) / getTabCount();
        return this.agu + (i * width) + eM(width);
    }

    private int eL(int i) {
        if (i < 0 || getWidth() <= 0) {
            return 0;
        }
        if (this.mStyle == 2) {
            int width = (getWidth() - (this.agu * 2)) / getTabCount();
            return this.agu + (((i + 1) * width) - eM(width));
        }
        return ((i + 1) * (getWidth() / getTabCount())) + 0;
    }

    private int eM(int i) {
        float f;
        if (this.agy != 0.0f) {
            f = (i - this.agy) / 2.0f;
        } else {
            f = i * ((1.0f - this.agz) / 2.0f);
        }
        return (int) f;
    }

    private void getIndicatorPosition() {
        int selectedTabIndex = getSelectedTabIndex();
        if (selectedTabIndex < 0) {
            this.agS = 0;
            this.agU = 0;
            this.agT = 0;
            this.agV = 0;
            return;
        }
        this.agS = eK(selectedTabIndex);
        this.agU = eL(selectedTabIndex);
        this.agT = this.agS;
        this.agV = this.agU;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, @Nullable final a aVar) {
        if (getTabCount() <= 0) {
            return;
        }
        boolean z2 = z && this.agv;
        getIndicatorPosition();
        if (z2) {
            if (this.mValueAnimator == null) {
                this.mValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
                this.mValueAnimator.setDuration(300L);
                this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.xui.widget.XTabLayout.4
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        float min = Math.min(floatValue, XTabLayout.this.aha) / XTabLayout.this.aha;
                        if (floatValue < XTabLayout.this.aha) {
                            XTabLayout.this.mIndicatorHeight = (int) (XTabLayout.this.agM - ((floatValue / XTabLayout.this.aha) * (XTabLayout.this.agM - XTabLayout.this.agN)));
                        } else {
                            XTabLayout.this.mIndicatorHeight = (int) (XTabLayout.this.agM + ((((floatValue - XTabLayout.this.aha) / (1.0f - XTabLayout.this.aha)) - 1.0f) * (XTabLayout.this.agM - XTabLayout.this.agN)));
                        }
                        if (XTabLayout.this.agS > XTabLayout.this.agO) {
                            XTabLayout.this.agW = (int) (XTabLayout.this.agO + (Math.pow(floatValue, 1.048d) * (XTabLayout.this.agS - XTabLayout.this.agO)));
                            XTabLayout.this.agY = (int) (XTabLayout.this.agP + ((XTabLayout.this.agU - XTabLayout.this.agP) * min));
                        } else {
                            XTabLayout.this.agW = (int) (XTabLayout.this.agO + ((XTabLayout.this.agS - XTabLayout.this.agO) * min));
                            XTabLayout.this.agY = (int) (XTabLayout.this.agP + (Math.pow(floatValue, 1.048d) * (XTabLayout.this.agU - XTabLayout.this.agP)));
                        }
                        if (XTabLayout.this.agT > XTabLayout.this.agQ) {
                            XTabLayout.this.agX = (int) (XTabLayout.this.agQ + (Math.pow(floatValue, 4.648d) * (XTabLayout.this.agT - XTabLayout.this.agQ)));
                            XTabLayout.this.agZ = (int) (XTabLayout.this.agR + (min * (XTabLayout.this.agV - XTabLayout.this.agR)));
                        } else {
                            XTabLayout.this.agX = (int) (XTabLayout.this.agQ + (min * (XTabLayout.this.agT - XTabLayout.this.agQ)));
                            XTabLayout.this.agZ = (int) (XTabLayout.this.agR + (Math.pow(floatValue, 4.648d) * (XTabLayout.this.agV - XTabLayout.this.agR)));
                        }
                        XTabLayout.this.invalidate();
                    }
                });
                this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.xiaopeng.xui.widget.XTabLayout.5
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        XTabLayout.this.rF();
                        XTabLayout.this.invalidate();
                    }
                });
                this.mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            }
            this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.xiaopeng.xui.widget.XTabLayout.6
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    if (aVar != null) {
                        aVar.onStart();
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    XTabLayout.this.mValueAnimator.removeListener(this);
                    if (aVar != null) {
                        aVar.onEnd();
                    }
                }
            });
            this.mValueAnimator.start();
            return;
        }
        rF();
        if (aVar != null) {
            aVar.onStart();
            aVar.onEnd();
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rF() {
        this.agO = this.agS;
        this.agP = this.agU;
        this.agQ = this.agT;
        this.agR = this.agV;
        this.agW = this.agO;
        this.agY = this.agP;
        this.agX = this.agO;
        this.agZ = this.agR;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setEnabled(z);
        }
        if (Build.VERSION.SDK_INT > 26) {
            int i2 = z ? 255 : 92;
            this.mPaint.setAlpha(i2);
            this.agJ.setAlpha(i2);
            this.agK.setAlpha(i2);
            this.agL.setAlpha(i2);
            invalidate();
        }
    }

    public boolean isEnabled(int i) {
        int childCount = getChildCount();
        View childAt = getChildAt(i);
        if (i < childCount && childAt != null) {
            return childAt.isEnabled();
        }
        return false;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new Runnable() { // from class: com.xiaopeng.xui.widget.XTabLayout.7
            @Override // java.lang.Runnable
            public void run() {
                XTabLayout.this.a(false, (a) null);
            }
        });
    }

    public int getSelectedTabIndex() {
        return this.ahb;
    }

    public int getTabCount() {
        return getChildCount();
    }

    private void a(Canvas canvas) {
        if (!isNight()) {
            this.mPaint.setMaskFilter(null);
        }
        this.mPaint.setAlpha(isEnabled(this.ahb) ? 255 : 92);
        float height = (getHeight() * 1.0f) / 2.0f;
        float height2 = getHeight() >> 1;
        if (this.agW < this.agY) {
            canvas.drawRoundRect(this.agW, height2 - height, this.agY, height2 + height, height, height, this.mPaint);
        } else {
            canvas.drawRoundRect(this.agY, height2 - height, this.agW, height2 + height, height, height, this.mPaint);
        }
    }

    private void b(Canvas canvas) {
        if (isNight()) {
            this.mPaint.setMaskFilter(new BlurMaskFilter(this.agE, BlurMaskFilter.Blur.SOLID));
            this.agK.setMaskFilter(new BlurMaskFilter(this.agH, BlurMaskFilter.Blur.SOLID));
        } else {
            this.mPaint.setMaskFilter(null);
            this.agK.setMaskFilter(null);
        }
        this.mPaint.setAlpha(isEnabled(this.ahb) ? 255 : 92);
        this.agK.setAlpha(isEnabled(this.ahb) ? 255 : 92);
        float f = this.mIndicatorHeight / 2.0f;
        float height = getHeight() - this.agA;
        if (this.agX < this.agZ) {
            canvas.drawRoundRect(this.agX, height - Math.max(this.mIndicatorHeight, 1.0f), this.agZ, height, f, f, this.agK);
        } else {
            canvas.drawRoundRect(this.agZ, height - Math.max(this.mIndicatorHeight, 1.0f), this.agX, height, f, f, this.agK);
        }
    }

    protected int eN(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public void setOnTabChangeListener(@Nullable b bVar) {
        this.ahi = bVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XLinearLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.ahk && this.ahl != isNight()) {
            this.ahk = false;
            setStyle(isNight() ? 2 : 1);
        }
    }

    private boolean isNight() {
        if (isInEditMode()) {
            return false;
        }
        return com.xiaopeng.xui.c.a.aB(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XLinearLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.ahk = true;
        this.ahl = isNight();
    }

    @Override // android.view.View
    public void setSoundEffectsEnabled(boolean z) {
        super.setSoundEffectsEnabled(z);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                childAt.setSoundEffectsEnabled(z);
            }
        }
    }
}
