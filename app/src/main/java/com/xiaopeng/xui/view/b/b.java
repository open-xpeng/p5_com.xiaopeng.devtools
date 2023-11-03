package com.xiaopeng.xui.view.b;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;

/* compiled from: XFontScaleHelper.java */
/* loaded from: classes13.dex */
public class b {
    private float abu;

    @Nullable
    public static b a(@NonNull Resources resources, @DimenRes int i) {
        if (com.xiaopeng.xui.a.qo()) {
            float b = b(resources, i);
            if (b != -1.0f) {
                return new b(b);
            }
            return null;
        }
        return null;
    }

    @Nullable
    public static b a(@NonNull TypedArray typedArray, @StyleableRes int i) {
        return a(typedArray, i, 0);
    }

    @Nullable
    public static b a(@NonNull TypedArray typedArray, @StyleableRes int i, @DimenRes int i2) {
        if (com.xiaopeng.xui.a.qo()) {
            float b = b(typedArray, i, i2);
            if (b != -1.0f) {
                return new b(b);
            }
            return null;
        }
        return null;
    }

    private b(float f) {
        this.abu = f;
    }

    public float a(@NonNull DisplayMetrics displayMetrics) {
        return TypedValue.applyDimension(2, this.abu, displayMetrics);
    }

    public void a(@NonNull TextView textView) {
        textView.setTextSize(this.abu);
    }

    private static float b(@NonNull TypedArray typedArray, @StyleableRes int i, @DimenRes int i2) {
        if (typedArray.hasValue(i)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i, typedValue);
            if (typedValue.getComplexUnit() == 2) {
                return TypedValue.complexToFloat(typedValue.data);
            }
            return -1.0f;
        }
        return b(typedArray.getResources(), i2);
    }

    private static float b(@NonNull Resources resources, @DimenRes int i) {
        if (i != 0) {
            TypedValue typedValue = new TypedValue();
            resources.getValue(i, typedValue, true);
            if (typedValue.getComplexUnit() == 2) {
                return TypedValue.complexToFloat(typedValue.data);
            }
            return -1.0f;
        }
        return -1.0f;
    }
}
