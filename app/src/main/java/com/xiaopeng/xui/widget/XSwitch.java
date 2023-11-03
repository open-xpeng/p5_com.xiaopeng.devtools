package com.xiaopeng.xui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.v4.media.MediaPlayer2;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.b.f;

/* loaded from: classes13.dex */
public class XSwitch extends XCompoundButton implements ValueAnimator.AnimatorUpdateListener {
    private static final String TAG = XSwitch.class.getSimpleName();
    private final float SHADOW_RADIUS;
    private Paint aeB;
    private Paint aeC;
    private float aeD;
    private Xfermode aeE;
    private final float aeF;
    private float aeG;
    private float aeH;
    private float aeI;
    private final float aeJ;
    private float aeK;
    private float aeL;
    private final float aeM;
    private float aeN;
    private float aeO;
    private float aeP;
    private float aeQ;
    private float aeR;
    private final float aeS;
    private float aeT;
    private float aeU;
    private float aeV;
    private float aeW;
    private float aeX;
    private final float aeY;
    private float aeZ;
    private int afA;
    private int afB;
    private int afC;
    private int afD;
    private int afE;
    private int afF;
    private int afG;
    private int afH;
    private int afI;
    private Shader afJ;
    private final float afK;
    private final float afL;
    private float afM;
    private float afN;
    private float afO;
    private float afP;
    private int afQ;
    private float afR;
    private float afS;
    private Shader afT;
    private int afU;
    private int afV;
    private int afW;
    private int afX;
    private Shader afY;
    private int afZ;
    private float afa;
    private float afb;
    private final float afc;
    private final float afd;
    private final float afe;
    private final float aff;
    private int afg;
    private int afh;
    private int afi;
    private int afj;
    private int afk;
    private int afl;
    private int afm;
    private int afn;
    private int afo;
    private int afp;
    private int afq;
    private int afr;
    private int afs;
    private int aft;
    private int afu;
    private int afv;
    private Shader afw;
    private int afx;
    private int afy;
    private int afz;
    private int aga;
    private int agb;
    private int agc;
    private int agd;
    private int age;
    private int agf;
    private float agg;
    private float agh;
    private float agi;
    private float agj;
    private float agk;
    private int agl;
    private int agm;
    private a agn;
    private boolean ago;
    private boolean agp;
    private boolean agq;
    private float agr;
    private ArgbEvaluator mArgbEvaluator;
    private BlurMaskFilter mBlurMaskFilter;
    private float mTranslateX;
    private ValueAnimator mValueAnimator;

    /* loaded from: classes13.dex */
    public interface a {
        boolean f(View view, boolean z);
    }

    public XSwitch(Context context) {
        this(context, null);
    }

    public XSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.aeF = eJ(2);
        this.SHADOW_RADIUS = eJ(6);
        this.aeG = getResources().getDimension(R.dimen.x_switch_padding_top);
        this.aeH = getResources().getDimension(R.dimen.x_switch_padding_start);
        this.aeI = this.aeG + eJ(50);
        this.aeJ = eJ(25);
        this.aeK = this.aeH + eJ(90);
        this.aeL = this.aeG + this.aeF;
        this.aeM = eJ(21);
        this.aeN = this.aeL + this.aeF;
        this.aeO = this.aeH + eJ(4);
        this.aeP = this.aeN + (this.aeM * 2.0f);
        this.aeQ = this.aeP + this.aeF;
        this.aeR = this.aeO + (this.aeM * 2.0f);
        this.aeS = eJ(19);
        this.aeT = this.aeO + this.aeF;
        this.aeU = this.aeN + this.aeF;
        this.aeV = this.aeU + (this.aeS * 2.0f);
        this.aeW = this.aeT + (this.aeS * 2.0f);
        this.aeX = ((this.aeI - this.aeG) / 2.0f) + this.aeG;
        this.aeY = eJ(40);
        this.aeZ = this.aeT + eJ(10);
        this.afa = this.aeZ + eJ(46);
        this.afb = this.aeU + eJ(8);
        this.afc = eJ(18);
        this.afd = eJ(6);
        this.afe = eJ(22);
        this.aff = eJ(3);
        this.afK = eJ(5);
        this.afL = this.SHADOW_RADIUS;
        this.ago = true;
        this.agp = true;
        init();
    }

    public XSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.aeF = eJ(2);
        this.SHADOW_RADIUS = eJ(6);
        this.aeG = getResources().getDimension(R.dimen.x_switch_padding_top);
        this.aeH = getResources().getDimension(R.dimen.x_switch_padding_start);
        this.aeI = this.aeG + eJ(50);
        this.aeJ = eJ(25);
        this.aeK = this.aeH + eJ(90);
        this.aeL = this.aeG + this.aeF;
        this.aeM = eJ(21);
        this.aeN = this.aeL + this.aeF;
        this.aeO = this.aeH + eJ(4);
        this.aeP = this.aeN + (this.aeM * 2.0f);
        this.aeQ = this.aeP + this.aeF;
        this.aeR = this.aeO + (this.aeM * 2.0f);
        this.aeS = eJ(19);
        this.aeT = this.aeO + this.aeF;
        this.aeU = this.aeN + this.aeF;
        this.aeV = this.aeU + (this.aeS * 2.0f);
        this.aeW = this.aeT + (this.aeS * 2.0f);
        this.aeX = ((this.aeI - this.aeG) / 2.0f) + this.aeG;
        this.aeY = eJ(40);
        this.aeZ = this.aeT + eJ(10);
        this.afa = this.aeZ + eJ(46);
        this.afb = this.aeU + eJ(8);
        this.afc = eJ(18);
        this.afd = eJ(6);
        this.afe = eJ(22);
        this.aff = eJ(3);
        this.afK = eJ(5);
        this.afL = this.SHADOW_RADIUS;
        this.ago = true;
        this.agp = true;
        init();
    }

    public XSwitch(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.aeF = eJ(2);
        this.SHADOW_RADIUS = eJ(6);
        this.aeG = getResources().getDimension(R.dimen.x_switch_padding_top);
        this.aeH = getResources().getDimension(R.dimen.x_switch_padding_start);
        this.aeI = this.aeG + eJ(50);
        this.aeJ = eJ(25);
        this.aeK = this.aeH + eJ(90);
        this.aeL = this.aeG + this.aeF;
        this.aeM = eJ(21);
        this.aeN = this.aeL + this.aeF;
        this.aeO = this.aeH + eJ(4);
        this.aeP = this.aeN + (this.aeM * 2.0f);
        this.aeQ = this.aeP + this.aeF;
        this.aeR = this.aeO + (this.aeM * 2.0f);
        this.aeS = eJ(19);
        this.aeT = this.aeO + this.aeF;
        this.aeU = this.aeN + this.aeF;
        this.aeV = this.aeU + (this.aeS * 2.0f);
        this.aeW = this.aeT + (this.aeS * 2.0f);
        this.aeX = ((this.aeI - this.aeG) / 2.0f) + this.aeG;
        this.aeY = eJ(40);
        this.aeZ = this.aeT + eJ(10);
        this.afa = this.aeZ + eJ(46);
        this.afb = this.aeU + eJ(8);
        this.afc = eJ(18);
        this.afd = eJ(6);
        this.afe = eJ(22);
        this.aff = eJ(3);
        this.afK = eJ(5);
        this.afL = this.SHADOW_RADIUS;
        this.ago = true;
        this.agp = true;
        init();
    }

    private void init() {
        setClickable(true);
        this.aeB = new Paint(1);
        this.aeB.setStrokeWidth(eJ(4));
        this.aeC = new Paint(1);
        this.aeE = new PorterDuffXfermode(PorterDuff.Mode.ADD);
        this.mBlurMaskFilter = new BlurMaskFilter(eJ(8), BlurMaskFilter.Blur.NORMAL);
        setButtonDrawable(R.drawable.x_switch_drawable);
        setBackgroundColor(getColor(17170445));
        setGravity(16);
        ry();
        setSoundEffectsEnabled(false);
        this.mArgbEvaluator = new ArgbEvaluator();
    }

    private boolean isNight() {
        if (!isInEditMode()) {
            return com.xiaopeng.xui.c.a.aB(getContext());
        }
        return true;
    }

    private void ry() {
        this.agq = isNight();
        this.afg = getColor(R.color.x_switch_bg_off_enable_normal_start_color);
        this.afh = getColor(R.color.x_switch_bg_off_enable_normal_end_color);
        this.afi = getColor(R.color.x_switch_bg_off_enable_pressed_start_color);
        this.afj = getColor(R.color.x_switch_bg_off_enable_pressed_end_color);
        this.afk = getColor(R.color.x_switch_bg_on_enable_normal_start_color);
        this.afl = getColor(R.color.x_switch_bg_on_enable_normal_end_color);
        this.afm = getColor(R.color.x_switch_bg_on_enable_pressed_start_color);
        this.afn = getColor(R.color.x_switch_bg_on_enable_pressed_end_color);
        this.afo = getColor(R.color.x_switch_bg_off_disable_start_color);
        this.afp = getColor(R.color.x_switch_bg_off_disable_end_color);
        this.afq = getColor(R.color.x_switch_bg_on_disable_start_color);
        this.afr = getColor(R.color.x_switch_bg_on_disable_end_color);
        this.afx = getColor(R.color.x_switch_circle_out_off_enable_start_color);
        this.afy = getColor(R.color.x_switch_circle_out_off_enable_end_color);
        this.afz = getColor(R.color.x_switch_circle_out_on_enable_start_color);
        this.afA = getColor(R.color.x_switch_circle_out_on_enable_end_color);
        this.afB = getColor(R.color.x_switch_circle_out_off_disable_start_color);
        this.afC = getColor(R.color.x_switch_circle_out_off_disable_end_color);
        this.afD = getColor(R.color.x_switch_circle_out_on_disable_start_color);
        this.afE = getColor(R.color.x_switch_circle_out_on_disable_end_color);
        this.afQ = getColor(R.color.x_switch_circle_out_shadow_color);
        this.afU = getColor(R.color.x_switch_circle_inner_enable_start_color);
        this.afV = getColor(R.color.x_switch_circle_inner_enable_end_color);
        this.afW = getColor(R.color.x_switch_circle_inner_disable_start_color);
        this.afX = getColor(R.color.x_switch_circle_inner_disable_end_color);
        this.afZ = getColor(R.color.x_switch_vertical_off_enable_color);
        this.aga = getColor(R.color.x_switch_vertical_on_enable_color);
        this.agb = getColor(R.color.x_switch_vertical_off_disable_color);
        this.agc = getColor(R.color.x_switch_vertical_on_disable_color);
        changeParams();
    }

    private void changeParams() {
        int i;
        int i2;
        if (isEnabled()) {
            if (isPressed()) {
                this.afu = this.afm;
                this.afv = this.afn;
                this.afs = this.afi;
                this.aft = this.afj;
            } else {
                this.afu = this.afk;
                this.afv = this.afl;
                this.afs = this.afg;
                this.aft = this.afh;
            }
            this.afF = this.afx;
            this.afG = this.afy;
            this.afH = this.afz;
            this.afI = this.afA;
            this.aeD = 1.0f;
            i = this.afU;
            i2 = this.afV;
            this.agd = this.afZ;
            this.age = this.aga;
        } else {
            this.afu = this.afq;
            this.afv = this.afr;
            this.afs = this.afo;
            this.aft = this.afp;
            this.afF = this.afB;
            this.afG = this.afC;
            this.afH = this.afD;
            this.afI = this.afE;
            this.aeD = 0.36f;
            i = this.afW;
            i2 = this.afX;
            this.agd = this.agb;
            this.age = this.agc;
        }
        this.afY = new LinearGradient(0.0f, this.aeU, 0.0f, this.aeV, i, i2, Shader.TileMode.CLAMP);
        if (isNight()) {
            this.afM = 0.0f;
            this.afN = 0.0f;
            this.afO = this.afL;
            this.afP = 0.0f;
            this.agg = this.SHADOW_RADIUS;
        } else {
            this.afM = this.afK;
            this.afN = this.afK;
            this.afO = this.afK;
            this.afP = this.afK;
            this.agg = 0.0f;
        }
        rz();
    }

    private void rz() {
        rB();
        rC();
        rD();
        rE();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XCompoundButton, android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (com.xiaopeng.xui.c.a.isThemeChanged(configuration)) {
            ry();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XCompoundButton, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.agp && this.agq != isNight()) {
            this.agp = false;
            ry();
        }
        rA();
        changeParams();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XCompoundButton, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mValueAnimator != null) {
            this.mValueAnimator.cancel();
        }
        this.agp = true;
        this.agq = isNight();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        stopAnimation();
        rA();
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        super.setPressed(z);
        changeParams();
    }

    public void setCheckSoundEnable(boolean z) {
        this.ago = z;
    }

    @Override // com.xiaopeng.xui.widget.XCompoundButton, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.agn != null && this.agn.f(this, z)) {
            return;
        }
        boolean isChecked = isChecked();
        if (isChecked != z && isPressed() && this.ago) {
            f.qN().play(z ? 3 : 4);
        }
        super.setChecked(z);
        if (!isAttachedToWindow() || getWidth() == 0) {
            rA();
            changeParams();
        } else if (isChecked != z) {
            stopAnimation();
            if (z) {
                eI(MediaPlayer2.MEDIA_INFO_VIDEO_TRACK_LAGGING);
            } else {
                eI(0);
            }
        }
    }

    private void rA() {
        if (isChecked()) {
            this.agm = MediaPlayer2.MEDIA_INFO_VIDEO_TRACK_LAGGING;
        } else {
            this.agm = 0;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        changeParams();
    }

    private void eI(int i) {
        this.mValueAnimator = ValueAnimator.ofInt(this.agm, i);
        this.mValueAnimator.setDuration(Math.abs(i - this.agm));
        this.mValueAnimator.addUpdateListener(this);
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.xiaopeng.xui.widget.XSwitch.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                XSwitch.this.mValueAnimator.removeAllUpdateListeners();
                XSwitch.this.mValueAnimator.removeAllListeners();
                XSwitch.this.mValueAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                XSwitch.this.mValueAnimator.removeAllUpdateListeners();
                XSwitch.this.mValueAnimator.removeAllListeners();
                XSwitch.this.mValueAnimator = null;
            }
        });
        this.mValueAnimator.start();
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.agm = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.agr = (this.agm - 200) / 300.0f;
        rz();
    }

    private void stopAnimation() {
        if (this.mValueAnimator != null && this.mValueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
        }
    }

    private void rB() {
        if (this.agm <= 200) {
            z(this.afs, this.aft);
        } else if (this.agm <= 500) {
            z(a(this.afs, this.afu, this.agr), a(this.aft, this.afv, this.agr));
        } else {
            z(this.afu, this.afv);
        }
    }

    private void z(int i, int i2) {
        this.afw = new LinearGradient(0.0f, this.aeG, 0.0f, this.aeI, i, i2, Shader.TileMode.CLAMP);
    }

    private void rC() {
        if (this.agm <= 200) {
            A(this.afF, this.afG);
            this.afR = this.afM;
            this.afS = this.afN;
            float f = (this.aeD * this.agm) / 200.0f;
            B(a(f, this.afF), a(f, this.afG));
        } else if (this.agm <= 500) {
            int a2 = a(this.afF, this.afH, this.agr);
            int a3 = a(this.afG, this.afI, this.agr);
            A(a2, a3);
            this.afR = (this.afO - this.afM) * this.agr;
            this.afS = (this.afP - this.afN) * this.agr;
            B(a(this.aeD, a2), a(this.aeD, a3));
        } else {
            A(this.afH, this.afI);
            this.afR = this.afO;
            this.afS = this.afP;
            float f2 = this.aeD - ((this.aeD * ((this.agm - 300) - 200)) / 200.0f);
            B(a(f2, this.afH), a(f2, this.afI));
        }
    }

    private void A(int i, int i2) {
        this.afJ = new LinearGradient(0.0f, this.aeL, 0.0f, this.aeQ, i, i2, Shader.TileMode.CLAMP);
    }

    private void B(int i, int i2) {
        this.afT = new LinearGradient(0.0f, this.aeL, 0.0f, this.aeQ, i, i2, Shader.TileMode.CLAMP);
    }

    private void rD() {
        if (this.agm <= 200) {
            this.mTranslateX = 0.0f;
        } else if (this.agm <= 500) {
            this.mTranslateX = this.aeY * this.agr;
        } else {
            this.mTranslateX = this.aeY;
        }
    }

    private void rE() {
        if (this.agm <= 200) {
            this.agf = this.agd;
            this.agh = this.aeZ;
            this.agj = this.aeX;
            this.agi = this.afc;
            this.agk = 0.0f;
            this.agl = a((this.aeD * this.agm) / 200.0f, this.agf);
        } else if (this.agm <= 500) {
            this.agf = a(this.agd, this.age, this.agr);
            float f = ((this.agm - 200) - 120) / 180.0f;
            if (f >= 0.0f) {
                this.agh = this.aeZ + ((this.afa - this.aeZ) * f);
                this.agj = this.aeX - ((this.aeX - this.afb) * f);
                this.agi = this.afc - ((this.afc - this.afd) * f);
                this.agk = this.afe * f;
            }
            this.agl = a(this.aeD, this.agf);
        } else {
            this.agf = this.age;
            this.agh = this.afa;
            this.agj = this.afb;
            this.agi = this.afd;
            this.agk = this.afe;
            this.agl = a(this.aeD - ((this.aeD * ((this.agm - 300) - 200)) / 200.0f), this.agf);
        }
    }

    private int getColor(int i) {
        return getResources().getColor(i, getContext().getTheme());
    }

    private int a(float f, int i) {
        return Color.argb((int) (f * 255.0f), Color.red(i), Color.green(i), Color.blue(i));
    }

    private int a(int i, int i2, float f) {
        return ((Integer) this.mArgbEvaluator.evaluate(f, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.aeG = (((getHeight() - getPaddingTop()) - eJ(50)) / 2.0f) + getPaddingTop();
        this.aeI = this.aeG + eJ(50);
        this.aeL = this.aeG + this.aeF;
        this.aeN = this.aeL + this.aeF;
        this.aeP = this.aeN + (this.aeM * 2.0f);
        this.aeQ = this.aeP + this.aeF;
        this.aeU = this.aeN + this.aeF;
        this.aeV = this.aeU + (this.aeS * 2.0f);
        this.aeX = ((this.aeI - this.aeG) / 2.0f) + this.aeG;
        this.afb = this.aeU + eJ(8);
        this.aeH = getPaddingLeft() + eJ(7);
        this.aeK = this.aeH + eJ(90);
        this.aeO = this.aeH + eJ(4);
        this.aeR = this.aeO + (this.aeM * 2.0f);
        this.aeT = this.aeO + this.aeF;
        this.aeW = this.aeT + (2.0f * this.aeS);
        this.aeZ = this.aeT + eJ(10);
        this.afa = this.aeZ + eJ(46);
        changeParams();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getVisibility() == 8) {
            return;
        }
        this.aeB.setStyle(Paint.Style.FILL);
        this.aeB.setShader(this.afw);
        canvas.drawRoundRect(this.aeH, this.aeG, this.aeK, this.aeI, this.aeJ, this.aeJ, this.aeB);
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), this.aeB);
        this.aeB.setStyle(Paint.Style.STROKE);
        this.aeB.setShader(this.afJ);
        if (this.afR > 0.0f) {
            this.aeB.setShadowLayer(this.afR, 0.0f, this.afS, this.afQ);
        }
        canvas.drawRoundRect(this.mTranslateX + this.aeO, this.aeN, this.mTranslateX + this.aeR, this.aeP, this.aeM, this.aeM, this.aeB);
        this.aeB.clearShadowLayer();
        this.aeB.setXfermode(this.aeE);
        this.aeB.setMaskFilter(this.mBlurMaskFilter);
        this.aeB.setShader(this.afT);
        canvas.drawRoundRect(this.mTranslateX + this.aeO, this.aeN, this.mTranslateX + this.aeR, this.aeP, this.aeM, this.aeM, this.aeB);
        canvas.restoreToCount(saveLayer);
        this.aeB.setMaskFilter(null);
        this.aeB.setXfermode(null);
        this.aeB.clearShadowLayer();
        this.aeB.setStyle(Paint.Style.FILL);
        this.aeB.setShader(this.afY);
        canvas.drawRoundRect(this.mTranslateX + this.aeT, this.aeU, this.mTranslateX + this.aeW, this.aeV, this.aeS, this.aeS, this.aeB);
        this.aeC.setColor(this.agf);
        if (this.agg > 0.0f) {
            this.aeC.setShadowLayer(this.agg, 0.0f, 0.0f, this.agf);
        }
        int saveLayer2 = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), this.aeB);
        canvas.drawRoundRect(this.agh, this.agj, this.agi + this.agh, this.agk + this.agj, this.aff, this.aff, this.aeC);
        this.aeC.clearShadowLayer();
        if (isEnabled()) {
            this.aeC.setColor(this.agl);
            this.aeC.setXfermode(this.aeE);
            this.aeC.setMaskFilter(this.mBlurMaskFilter);
            canvas.drawRoundRect(this.agh, this.agj, this.agi + this.agh, this.agk + this.agj, this.aff, this.aff, this.aeC);
        }
        canvas.restoreToCount(saveLayer2);
        this.aeC.setMaskFilter(null);
        this.aeC.setXfermode(null);
    }

    private float eJ(int i) {
        return TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public void setOnInterceptListener(a aVar) {
        this.agn = aVar;
    }
}
