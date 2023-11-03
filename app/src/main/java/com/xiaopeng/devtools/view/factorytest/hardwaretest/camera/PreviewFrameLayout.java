package com.xiaopeng.devtools.view.factorytest.hardwaretest.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/* loaded from: classes12.dex */
public class PreviewFrameLayout extends RelativeLayout {
    private double JC;

    public PreviewFrameLayout(Context context) {
        this(context, null);
    }

    public PreviewFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreviewFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.JC = 1.7777777777777777d;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int i3 = size - paddingLeft;
        int i4 = size2 - paddingTop;
        boolean z = i3 > i4;
        int i5 = z ? i3 : i4;
        if (z) {
            i3 = i4;
        }
        double d = i5;
        double d2 = i3;
        if (d > this.JC * d2) {
            i5 = (int) (d2 * this.JC);
        } else {
            i3 = (int) (d / this.JC);
        }
        if (z) {
            int i6 = i5;
            i5 = i3;
            i3 = i6;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3 + paddingLeft, 1073741824), View.MeasureSpec.makeMeasureSpec(i5 + paddingTop, 1073741824));
    }

    public void setRatio(double d) {
        this.JC = d;
        requestLayout();
    }
}
