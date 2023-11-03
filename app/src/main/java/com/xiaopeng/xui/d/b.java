package com.xiaopeng.xui.d;

import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import com.xiaopeng.xpui.R;

/* compiled from: XBackgroundPaddingUtils.java */
/* loaded from: classes13.dex */
public class b {
    public static Rect a(@NonNull View view, @NonNull AttributeSet attributeSet) {
        int i;
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.XBackgroundPadding);
        if (obtainStyledAttributes.getBoolean(R.styleable.XBackgroundPadding_x_bg_padding_enable, false)) {
            if (obtainStyledAttributes.hasValue(R.styleable.XBackgroundPadding_x_bg_padding)) {
                i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBackgroundPadding_x_bg_padding, -1);
            } else {
                i = -1;
            }
            int i2 = i;
            int i3 = i2;
            int i4 = i3;
            if (obtainStyledAttributes.hasValue(R.styleable.XBackgroundPadding_x_bg_padding_left)) {
                i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBackgroundPadding_x_bg_padding_left, -1);
            }
            if (obtainStyledAttributes.hasValue(R.styleable.XBackgroundPadding_x_bg_padding_right)) {
                i3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBackgroundPadding_x_bg_padding_right, -1);
            }
            if (obtainStyledAttributes.hasValue(R.styleable.XBackgroundPadding_x_bg_padding_top)) {
                i2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBackgroundPadding_x_bg_padding_top, -1);
            }
            if (obtainStyledAttributes.hasValue(R.styleable.XBackgroundPadding_x_bg_padding_bottom)) {
                i4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBackgroundPadding_x_bg_padding_bottom, -1);
            }
            obtainStyledAttributes.recycle();
            return b(view, i, i2, i3, i4);
        }
        obtainStyledAttributes.recycle();
        return null;
    }

    public static Rect b(@NonNull View view, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        Drawable drawable;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        log(String.format("insetLeft %s, insetTop %s, insetRight %s, insetBottom %s, ", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        Drawable background = view.getBackground();
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        log(String.format("paddingLeft %s, paddingRight %s, paddingTop %s, paddingBottom %s, ", Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight), Integer.valueOf(paddingTop), Integer.valueOf(paddingBottom)));
        if (background != null) {
            if (background instanceof InsetDrawable) {
                InsetDrawable insetDrawable = (InsetDrawable) background;
                drawable = insetDrawable.getDrawable();
                Rect rect = new Rect();
                insetDrawable.getPadding(rect);
                i5 = i;
                if (i5 == -1) {
                    i5 = rect.left;
                }
                i6 = i2;
                if (i6 == -1) {
                    i6 = rect.top;
                }
                i7 = i3;
                if (i7 == -1) {
                    i7 = rect.right;
                }
                if (i4 == -1) {
                    i11 = rect.bottom;
                } else {
                    i11 = i4;
                }
                i12 = i5 - rect.left;
                i9 = i6 - rect.top;
                i8 = i7 - rect.right;
                i10 = i11 - rect.bottom;
            } else {
                i5 = i;
                i6 = i2;
                i7 = i3;
                drawable = background;
                i8 = 0;
                i9 = 0;
                i10 = 0;
                i11 = i4;
                i12 = 0;
            }
            log(String.format("paddingLeftOffset %s, paddingTopOffset %s, paddingRightOffset %s, paddingBottomOffset %s, ", Integer.valueOf(i12), Integer.valueOf(i9), Integer.valueOf(i8), Integer.valueOf(i10)));
            InsetDrawable insetDrawable2 = new InsetDrawable(drawable, i5, i6, i7, i11);
            view.setBackground(insetDrawable2);
            insetDrawable2.setDrawable(drawable);
            view.setPadding(paddingLeft + i12, paddingTop + i9, paddingRight + i8, paddingBottom + i10);
            log(String.format("paddingLeft %s, paddingRight %s, paddingTop %s, paddingBottom %s, ", Integer.valueOf(view.getPaddingLeft()), Integer.valueOf(view.getPaddingRight()), Integer.valueOf(view.getPaddingTop()), Integer.valueOf(view.getPaddingBottom())));
            return new Rect(i5, i6, i7, i11);
        }
        return null;
    }

    private static void log(String str) {
        f.f("xpui-bgPadding", str);
    }
}
