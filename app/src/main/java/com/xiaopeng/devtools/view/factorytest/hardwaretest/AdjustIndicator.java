package com.xiaopeng.devtools.view.factorytest.hardwaretest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.xiaopeng.lib.utils.d.b;

/* loaded from: classes12.dex */
public class AdjustIndicator extends View {
    private Paint Gq;
    int Gr;
    private int Gs;
    private Context mContext;
    private int max;
    private int paddingLeft;
    private int paddingRight;
    private int progress;

    public AdjustIndicator(Context context) {
        this(context, null);
    }

    public AdjustIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AdjustIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paddingLeft = 0;
        this.paddingRight = 0;
        this.max = 0;
        this.progress = 0;
        this.Gr = 0;
        this.Gs = 0;
        init(context);
    }

    public void r(int i, int i2) {
        this.paddingLeft = i;
        this.paddingRight = i2;
    }

    public void setProgress(int i) {
        this.progress = i;
        String str = (this.Gs + i) + "";
        Rect rect = new Rect();
        this.Gq.getTextBounds(str, 0, str.length(), rect);
        this.Gr = rect.width();
        invalidate();
    }

    public void setMax(int i) {
        this.max = i;
    }

    public void setProgressScale(int i) {
        this.Gs = i;
    }

    private void init(Context context) {
        this.mContext = context;
        this.Gq = new Paint();
        this.Gq.setFlags(1);
        this.Gq.setAntiAlias(true);
        this.Gq.setColor(-1);
        this.Gq.setTextSize(b.a(this.mContext, 24.0f));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.max != 0) {
            canvas.drawText((this.progress + this.Gs) + "", this.paddingLeft + ((this.progress * (((getMeasuredWidth() - this.paddingRight) - this.paddingLeft) - ((this.Gr * 5) / 6))) / this.max), (getMeasuredHeight() * 3) / 4, this.Gq);
            return;
        }
        canvas.drawText((this.progress + this.Gs) + "", this.paddingLeft + this.progress, (getMeasuredHeight() * 3) / 4, this.Gq);
    }
}
