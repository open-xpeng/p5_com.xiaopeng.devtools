package com.xiaopeng.devtools.devdebug.drivemode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import com.a.a.a;
import com.a.a.i;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class TabView extends View {
    private int count;
    private int duration;
    private int height;
    private Paint qD;
    private Paint qE;
    private Paint qF;
    private Paint qG;
    private float qH;
    private float qI;
    private float qJ;
    private int qK;
    private int qL;
    private int qM;
    private boolean qN;
    private float qO;
    private String[] qP;
    private int qQ;
    private int qR;
    private int qS;
    private int qT;
    private float qU;
    private float qV;
    private i qW;
    private float qX;
    private a qY;
    private Rect rect;
    private RectF rectF;
    private int textSize;
    private int touchSlop;
    private int width;

    /* loaded from: classes12.dex */
    public interface a {
        void bP(int i);
    }

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.qM = 0;
        a(context, attributeSet);
        init(context);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TabView);
        String string = obtainStyledAttributes.getString(7);
        this.qP = string != null ? string.split(";") : null;
        this.textSize = (int) obtainStyledAttributes.getDimension(6, TypedValue.applyDimension(1, 14.0f, getResources().getDisplayMetrics()));
        this.qO = obtainStyledAttributes.getDimension(6, TypedValue.applyDimension(1, 14.0f, getResources().getDisplayMetrics()));
        int color = obtainStyledAttributes.getColor(0, Color.parseColor("#FF4081"));
        this.qS = color;
        this.qQ = color;
        int color2 = obtainStyledAttributes.getColor(1, Color.parseColor("#ffffff"));
        this.qT = color2;
        this.qR = color2;
        this.qU = (int) obtainStyledAttributes.getDimension(3, 2.0f);
        this.qV = (int) obtainStyledAttributes.getDimension(4, -1.0f);
        this.duration = obtainStyledAttributes.getInteger(2, 250);
        obtainStyledAttributes.recycle();
    }

    private void init(Context context) {
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.count = this.qP != null ? this.qP.length : 0;
        this.rect = new Rect();
        this.rectF = new RectF();
        this.qD = new Paint(1);
        this.qD.setColor(this.qQ);
        this.qE = new Paint(1);
        this.qE.setColor(this.qR);
        this.qF = new Paint(1);
        this.qF.setTextSize(this.textSize);
        this.qF.setTextAlign(Paint.Align.CENTER);
        this.qF.setColor(this.qS);
        this.qG = new Paint(1);
        this.qG.setTextSize(this.textSize);
        this.qG.setTextAlign(Paint.Align.CENTER);
        this.qG.setColor(this.qT);
        this.qW = i.b(0.0f, 1.0f);
        this.qW.h(this.duration);
        this.qW.setInterpolator(new LinearInterpolator());
        this.qW.a(new i.b() { // from class: com.xiaopeng.devtools.devdebug.drivemode.TabView.1
            @Override // com.a.a.i.b
            public void d(i iVar) {
                TabView.this.qX = ((Float) iVar.getAnimatedValue()).floatValue();
                TabView.this.invalidate();
            }
        });
        this.qW.a(new a.InterfaceC0011a() { // from class: com.xiaopeng.devtools.devdebug.drivemode.TabView.2
            @Override // com.a.a.a.InterfaceC0011a
            public void a(com.a.a.a aVar) {
            }

            @Override // com.a.a.a.InterfaceC0011a
            public void b(com.a.a.a aVar) {
                if (TabView.this.qX == 1.0f && TabView.this.qY != null) {
                    TabView.this.qY.bP(TabView.this.qL);
                }
            }

            @Override // com.a.a.a.InterfaceC0011a
            public void c(com.a.a.a aVar) {
            }

            @Override // com.a.a.a.InterfaceC0011a
            public void d(com.a.a.a aVar) {
            }
        });
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.count <= 0) {
            return;
        }
        this.rect.set(0, 0, this.width, this.height);
        this.rectF.set(this.rect);
        canvas.drawRoundRect(this.rectF, this.qO, this.qO, this.qD);
        this.rect.set((int) this.qU, (int) this.qU, (int) (this.width - this.qU), (int) (this.height - this.qU));
        this.rectF.set(this.rect);
        canvas.drawRoundRect(this.rectF, this.qO, this.qO, this.qE);
        float f = this.qV + (this.qH * this.qK);
        float f2 = f + (((this.qV + (this.qH * this.qL)) - f) * this.qX);
        this.rect.set((int) (f2 - this.qV), 0, (int) (this.qH + f2 + this.qV), this.height);
        this.rectF.set(this.rect);
        canvas.drawRoundRect(this.rectF, this.qO, this.qO, this.qD);
        int a2 = (this.height + ((int) a(this.qF))) / 2;
        for (int i = 0; i < this.count; i++) {
            float f3 = this.qV + (this.qH * i) + (this.qH / 2.0f);
            float f4 = ((this.qH / 2.0f) + f2) - this.qV;
            if (f4 < 0.0f) {
                f4 = 0.0f;
            }
            int i2 = (int) (f4 / this.qH);
            if (i2 == i && (i2 == this.qL || i2 == this.qK)) {
                canvas.drawText(this.qP[i], f3, a2, this.qG);
            } else {
                canvas.drawText(this.qP[i], f3, a2, this.qF);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.width = View.MeasureSpec.getSize(i);
        this.height = View.MeasureSpec.getSize(i2);
        this.qV = this.qV == -1.0f ? (int) (this.qO * 0.85f) : this.qV;
        this.qH = (this.width - (this.qV * 2.0f)) / (this.count > 0 ? this.count : 1);
        setMeasuredDimension(this.width, this.height);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.count <= 0 || !(this.qX == 0.0f || this.qX == 1.0f)) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.qI = x;
                this.qJ = y;
                this.qM = (int) ((x - this.qV) / this.qH);
                this.qM = Math.max(this.qM, 0);
                this.qM = Math.min(this.qM, this.count - 1);
                this.qN = true;
                return this.qM != this.qL;
            case 1:
            case 3:
                if (!this.qN || x < 0.0f || x > this.width || y < 0.0f || y > this.height || Math.min(Math.max((int) ((x - this.qV) / this.qH), 0), this.count - 1) != this.qM) {
                    return false;
                }
                this.qK = this.qL;
                this.qL = this.qM;
                start();
                return true;
            case 2:
                if (this.qN && (Math.abs(x - this.qI) > this.touchSlop || Math.abs(y - this.qJ) > this.touchSlop)) {
                    this.qN = false;
                }
                return this.qN;
            default:
                return super.onTouchEvent(motionEvent);
        }
    }

    private float a(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (float) ((Math.ceil(fontMetrics.descent - fontMetrics.top) + 2.0d) / 2.0d);
    }

    public void start() {
        stop();
        if (this.qW != null) {
            this.qW.start();
        }
    }

    public void stop() {
        if (this.qW != null) {
            this.qW.cancel();
        }
    }

    public void setTitle(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        this.qP = strArr;
        this.count = this.qP.length;
        this.qH = (this.width - (this.qV * 2.0f)) / (this.count > 0 ? this.count : 1);
        postInvalidate();
    }

    public void b(int i, boolean z) {
        if (i == this.qL) {
            return;
        }
        this.qK = this.qL;
        this.qL = i;
        if (z) {
            start();
            return;
        }
        this.qX = 1.0f;
        invalidate();
    }

    public void setOnTabSelectedListener(a aVar) {
        this.qY = aVar;
    }
}
