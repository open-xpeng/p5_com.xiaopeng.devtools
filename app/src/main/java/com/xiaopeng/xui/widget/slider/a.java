package com.xiaopeng.xui.widget.slider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import androidx.core.content.ContextCompat;
import com.xiaopeng.xpui.R;

/* compiled from: IndicatorDrawable.java */
/* loaded from: classes13.dex */
class a extends Drawable {
    private int Gr;
    NinePatchDrawable ajE;
    NinePatchDrawable ajF;
    NinePatchDrawable ajG;
    NinePatchDrawable ajH;
    private int ajK;
    private boolean isEnabled;
    private boolean isNight;
    private static int ajy = 116;
    private static float ajz = 24.0f;
    private static int ajA = 56;
    private static int ajB = 10;
    private static int ajC = 42;
    private final Paint ajD = new Paint(1);
    private String ajJ = "";
    private float ajI;
    private Rect ajL = new Rect((int) (this.ajI - (ajA / 2)), ajB, (int) (this.ajI + (ajA / 2)), ajB + 50);

    public a(Context context) {
        this.ajD.setTextSize(ajz);
        this.ajD.setStyle(Paint.Style.FILL);
        this.ajD.setTextAlign(Paint.Align.CENTER);
        this.ajE = (NinePatchDrawable) ContextCompat.getDrawable(context, R.drawable.x_slider_tag_day);
        this.ajF = (NinePatchDrawable) ContextCompat.getDrawable(context, R.drawable.x_slider_tag_night);
        this.ajG = (NinePatchDrawable) ContextCompat.getDrawable(context, R.drawable.x_slider_tag_day_disable);
        this.ajH = (NinePatchDrawable) ContextCompat.getDrawable(context, R.drawable.x_slider_tag_night_disable);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.isNight) {
            if (this.isEnabled) {
                this.ajF.setBounds(this.ajL);
                this.ajF.draw(canvas);
            } else {
                this.ajH.setBounds(this.ajL);
                this.ajH.draw(canvas);
            }
        } else if (this.isEnabled) {
            this.ajE.setBounds(this.ajL);
            this.ajE.draw(canvas);
        } else {
            this.ajG.setBounds(this.ajL);
            this.ajG.draw(canvas);
        }
        this.ajD.setColor(this.isEnabled ? -1 : 1560281087);
        canvas.drawText(this.ajJ, (this.ajL.left + this.ajL.right) / 2, ajC, this.ajD);
    }

    public void a(float f, String str, boolean z, int i) {
        this.isNight = z;
        this.ajJ = str;
        this.ajI = f;
        this.Gr = (int) this.ajD.measureText(str);
        this.ajK = i;
        rW();
        invalidateSelf();
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

    public void a(Canvas canvas, boolean z, boolean z2) {
        this.isNight = z;
        this.isEnabled = z2;
        draw(canvas);
    }

    private void rW() {
        int max = Math.max(this.Gr + 50, ajA);
        float f = max / 2;
        float f2 = (this.ajK - this.ajI) - f;
        if (this.ajI - f < 0.0f) {
            this.ajL.left = 0;
            this.ajL.right = max;
        } else if (f2 < 0.0f) {
            this.ajL.left = this.ajK - max;
            this.ajL.right = this.ajK;
        } else {
            this.ajL.left = (int) (this.ajI - f);
            this.ajL.right = (int) (this.ajI + f);
        }
    }
}
