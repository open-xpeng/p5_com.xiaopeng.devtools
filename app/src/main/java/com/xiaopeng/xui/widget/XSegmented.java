package com.xiaopeng.xui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.vui.commons.VuiElementType;
import com.xiaopeng.vui.commons.b;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.f;
import com.xiaopeng.xui.drawable.XIndicatorDrawable;

/* loaded from: classes13.dex */
public class XSegmented extends XLinearLayout implements View.OnClickListener, b {
    private static final String TAG = XSegmented.class.getSimpleName();
    private a aeA;
    @ColorRes
    private int aew;
    @DrawableRes
    private int[] aex;
    private int aey;
    private final XIndicatorDrawable aez;
    private float mTextSize;

    /* loaded from: classes13.dex */
    public interface a {
        void a(XSegmented xSegmented, int i, boolean z);

        boolean a(XSegmented xSegmented, int i);
    }

    public XSegmented(Context context) {
        this(context, null);
    }

    public XSegmented(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XSegmented(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "CustomViewStyleable"})
    public XSegmented(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.aez = new XIndicatorDrawable();
        this.aez.inflateAttrs(getResources(), attributeSet, context.getTheme());
        this.aez.setCallback(this);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.XSegmented, i, i2);
        CharSequence[] textArray = obtainStyledAttributes.getTextArray(R.styleable.XSegmented_segment_vui_labels);
        int i3 = obtainStyledAttributes.getInt(R.styleable.XSegmented_segment_selected_pos, -1);
        if (obtainStyledAttributes.hasValue(R.styleable.XSegmented_segment_texts)) {
            this.mTextSize = obtainStyledAttributes.getDimension(R.styleable.XSegmented_segment_text_size, R.dimen.x_font_title_03_size);
            this.aew = obtainStyledAttributes.getResourceId(R.styleable.XSegmented_segment_text_color, R.color.x_tabs_layout_title_color);
            a(obtainStyledAttributes.getTextArray(R.styleable.XSegmented_segment_texts), textArray);
        } else {
            TypedArray obtainTypedArray = getResources().obtainTypedArray(obtainStyledAttributes.getResourceId(R.styleable.XSegmented_segment_icons, 0));
            this.aex = new int[obtainTypedArray.length()];
            for (int i4 = 0; i4 < obtainTypedArray.length(); i4++) {
                this.aex[i4] = obtainTypedArray.getResourceId(i4, 0);
            }
            obtainTypedArray.recycle();
            a(this.aex, textArray);
        }
        obtainStyledAttributes.recycle();
        a(i3, false, false);
        setGravity(17);
        setWillNotDraw(false);
    }

    public void a(CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2) {
        if (charSequenceArr != null) {
            int i = 0;
            while (i < charSequenceArr.length) {
                b(charSequenceArr[i], (charSequenceArr2 == null || charSequenceArr2.length <= i) ? null : charSequenceArr2[i]);
                i++;
            }
        }
    }

    public void b(CharSequence charSequence, CharSequence charSequence2) {
        a(charSequence, charSequence2, getChildCount());
    }

    public void a(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence != null) {
            XTextView xTextView = new XTextView(getContext());
            xTextView.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0f));
            xTextView.setGravity(17);
            xTextView.setText(charSequence);
            xTextView.setTextSize(this.mTextSize);
            xTextView.setTextColor(getContext().getColorStateList(this.aew));
            if (charSequence2 != null) {
                xTextView.fk(charSequence2.toString());
            }
            xTextView.a(VuiElementType.TEXTVIEW);
            xTextView.setSoundEffectsEnabled(isSoundEffectsEnabled());
            xTextView.setOnClickListener(this);
            addView(xTextView, i);
            int i2 = this.aey;
            if (i <= i2) {
                i2++;
            }
            setSelection(i2);
        }
    }

    public void setTitleTextSize(float f) {
        this.mTextSize = f;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof TextView) {
                ((TextView) childAt).setTextSize(f);
            }
        }
    }

    public void setTitleTextColor(@ColorRes int i) {
        this.aew = i;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof TextView) {
                ((TextView) childAt).setTextColor(getContext().getColorStateList(i));
            }
        }
    }

    public void a(@DrawableRes int[] iArr, CharSequence[] charSequenceArr) {
        this.aex = iArr;
        int i = 0;
        while (i < iArr.length) {
            a(iArr[i], (charSequenceArr == null || charSequenceArr.length <= i) ? null : charSequenceArr[i]);
            i++;
        }
    }

    public void a(@DrawableRes int i, CharSequence charSequence) {
        a(i, charSequence, getChildCount());
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void a(@DrawableRes int i, CharSequence charSequence, int i2) {
        if (this.aex == null) {
            this.aex = new int[0];
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 >= this.aex.length) {
            int[] iArr = this.aex;
            this.aex = new int[iArr.length + 1];
            System.arraycopy(iArr, 0, this.aex, 0, iArr.length);
            this.aex[iArr.length] = i;
        } else if (i2 < getChildCount()) {
            int[] iArr2 = this.aex;
            this.aex = new int[iArr2.length + 1];
            System.arraycopy(iArr2, 0, this.aex, 0, i2);
            this.aex[i2] = i;
            System.arraycopy(iArr2, i2, this.aex, i2 + 1, iArr2.length - i2);
        }
        XImageView xImageView = new XImageView(getContext());
        xImageView.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0f));
        if (i != 0) {
            xImageView.setImageDrawable(getContext().getDrawable(i));
        }
        xImageView.setScaleType(ImageView.ScaleType.CENTER);
        if (charSequence != null) {
            xImageView.fk(charSequence.toString());
        }
        xImageView.a(VuiElementType.TEXTVIEW);
        xImageView.setSoundEffectsEnabled(isSoundEffectsEnabled());
        xImageView.setOnClickListener(this);
        addView(xImageView, i2);
        int i3 = this.aey;
        if (i2 <= i3) {
            i3++;
        }
        setSelection(i3);
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void b(@DrawableRes int i, CharSequence charSequence, int i2) {
        if (getChildCount() > i2) {
            View childAt = getChildAt(i2);
            if (childAt instanceof XImageView) {
                XImageView xImageView = (XImageView) childAt;
                xImageView.setImageDrawable(getContext().getDrawable(i));
                if (charSequence != null) {
                    xImageView.fk(charSequence.toString());
                }
                if (this.aex != null && this.aex.length > i2) {
                    this.aex[i2] = i;
                    return;
                }
                return;
            }
            f.h(TAG, "setSegmentIcon failed, view is not XImageView");
            return;
        }
        f.h(TAG, "setSegmentIcon failed, index > child count");
    }

    public void setSelection(int i) {
        a(i, true, false);
    }

    private void a(int i, boolean z, boolean z2) {
        int i2 = this.aey;
        this.aey = i;
        if (this.aey < 0 || this.aey >= getChildCount()) {
            this.aey = -1;
        }
        int i3 = 0;
        while (i3 < getChildCount()) {
            getChildAt(i3).setSelected(this.aey == i3);
            i3++;
        }
        if (this.aez != null) {
            this.aez.setSelection(getChildCount(), this.aey, z);
        }
        rx();
        if (this.aeA != null && i2 != this.aey) {
            this.aeA.a(this, this.aey, z2);
        }
        l(this);
    }

    public int getSelection() {
        return this.aey;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i = 0; i < getChildCount(); i++) {
            e(z, i);
        }
    }

    public void e(boolean z, int i) {
        if (i >= 0 && i < getChildCount()) {
            getChildAt(i).setEnabled(z);
            rx();
        }
    }

    private void rx() {
        if (this.aez != null && this.aey >= 0 && this.aey < getChildCount()) {
            this.aez.setEnable(getChildAt(this.aey).isEnabled());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XLinearLayout, android.view.View
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (com.xiaopeng.xui.c.a.isThemeChanged(configuration)) {
            setTitleTextColor(this.aew);
            if (this.aex != null && this.aex.length == getChildCount()) {
                for (int i = 0; i < this.aex.length; i++) {
                    b(this.aex[i], null, i);
                }
            }
            if (this.aez != null) {
                this.aez.onConfigurationChanged(getResources(), getContext().getTheme());
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    @SuppressLint({"DrawAllocation"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.aez != null) {
            this.aez.setBounds(new Rect(i, i2, i3, i4));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.aez != null) {
            this.aez.setState(getDrawableState());
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.aez != null) {
            this.aez.draw(canvas);
        }
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(@NonNull Drawable drawable) {
        super.invalidateDrawable(drawable);
        if (drawable instanceof XIndicatorDrawable) {
            invalidate();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int indexOfChild = indexOfChild(view);
        if (this.aeA == null || !this.aeA.a(this, indexOfChild)) {
            a(indexOfChild(view), true, true);
        }
    }

    public void setSegmentListener(a aVar) {
        this.aeA = aVar;
    }
}
