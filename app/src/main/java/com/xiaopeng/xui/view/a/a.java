package com.xiaopeng.xui.view.a;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.xiaopeng.xpui.R;

/* compiled from: XViewDelegateFontScale.java */
/* loaded from: classes13.dex */
public class a extends b {
    private float abu;
    private float mFontScale;
    private TextView mTextView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a a(@NonNull TextView textView, AttributeSet attributeSet, int i, int i2) {
        float f;
        boolean z;
        int i3;
        if (attributeSet == null) {
            return null;
        }
        Resources.Theme theme = textView.getContext().getTheme();
        TypedArray obtainStyledAttributes = textView.getContext().obtainStyledAttributes(attributeSet, R.styleable.XFontSize, i, i2);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.XFontSize_x_dynamic_font_scale_change_enable, true);
        obtainStyledAttributes.recycle();
        if (!z2) {
            return null;
        }
        TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, new int[]{16842901}, i, i2);
        if (!obtainStyledAttributes2.hasValue(0)) {
            f = 0.0f;
            z = false;
            i3 = 0;
        } else {
            TypedValue typedValue = new TypedValue();
            obtainStyledAttributes2.getValue(0, typedValue);
            i3 = typedValue.getComplexUnit();
            f = TypedValue.complexToFloat(typedValue.data);
            z = true;
        }
        obtainStyledAttributes2.recycle();
        if (!z) {
            TypedArray obtainStyledAttributes3 = theme.obtainStyledAttributes(attributeSet, new int[]{16842804}, i, i2);
            int resourceId = obtainStyledAttributes3.getResourceId(0, -1);
            obtainStyledAttributes3.recycle();
            TypedArray obtainStyledAttributes4 = resourceId != -1 ? theme.obtainStyledAttributes(resourceId, new int[]{16842901}) : null;
            if (obtainStyledAttributes4 != null) {
                if (obtainStyledAttributes4.hasValue(0)) {
                    TypedValue typedValue2 = new TypedValue();
                    obtainStyledAttributes4.getValue(0, typedValue2);
                    int complexUnit = typedValue2.getComplexUnit();
                    f = TypedValue.complexToFloat(typedValue2.data);
                    i3 = complexUnit;
                }
                obtainStyledAttributes4.recycle();
            }
        }
        if (i3 != 2) {
            return null;
        }
        return new a(textView, f);
    }

    private a(TextView textView, float f) {
        this.mTextView = textView;
        this.abu = f;
        this.mFontScale = textView.getContext().getResources().getConfiguration().fontScale;
    }

    @Override // com.xiaopeng.xui.view.a.b
    public void onConfigurationChanged(Configuration configuration) {
        a(configuration, "onConfigurationChanged");
    }

    private void a(Configuration configuration, String str) {
        if (this.mFontScale != configuration.fontScale) {
            this.mFontScale = configuration.fontScale;
            this.mTextView.setTextSize(this.abu);
        }
    }

    @Override // com.xiaopeng.xui.view.a.b
    public void onAttachedToWindow() {
        a(this.mTextView.getResources().getConfiguration(), "onAttachedToWindow");
    }

    @Override // com.xiaopeng.xui.view.a.b
    public void onDetachedFromWindow() {
    }
}
