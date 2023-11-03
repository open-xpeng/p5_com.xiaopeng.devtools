package com.xiaopeng.xui.widget.pageindicator;

import android.content.Context;
import android.content.res.Configuration;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.xpui.R;

/* loaded from: classes13.dex */
public class XNumberPagerIndicator extends XViewPagerIndicatorAbs {
    private int aiX;
    private int mCount;
    private String mFormat;
    private int mPosition;
    private TextView mTextView;

    public XNumberPagerIndicator(Context context) {
        super(context);
    }

    public XNumberPagerIndicator(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public XNumberPagerIndicator(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.pageindicator.XViewPagerIndicatorAbs
    public void init() {
        super.init();
        int dimensionPixelOffset = getContext().getResources().getDimensionPixelOffset(R.dimen.x_pager_indicator_number_padding);
        setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        this.aiX = getContext().getResources().getDimensionPixelSize(R.dimen.x_pager_indicator_view_size);
        this.mFormat = getContext().getString(R.string.x_number_indicator_desc_format);
    }

    @Override // com.xiaopeng.xui.widget.pageindicator.XViewPagerIndicatorAbs
    public void eU(int i) {
        if (this.mTextView == null) {
            this.mTextView = new TextView(getContext());
            this.mTextView.setTextAppearance(R.style.XNumberIndicatorSelectedTextStyle);
            this.mTextView.setGravity(17);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, this.aiX);
            layoutParams.weight = 1.0f;
            addView(this.mTextView, layoutParams);
        }
        this.mCount = i;
    }

    @Override // com.xiaopeng.xui.widget.pageindicator.XViewPagerIndicatorAbs
    public void eV(int i) {
        this.mPosition = i + 1;
        rU();
    }

    private void rU() {
        if (this.mTextView != null) {
            if (this.mPosition > this.mCount) {
                throw new IllegalArgumentException("Position cannot greater than count");
            }
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(getContext(), R.style.XNumberIndicatorTextStyle);
            String format = String.format(this.mFormat, Integer.valueOf(this.mPosition), Integer.valueOf(this.mCount));
            SpannableString spannableString = new SpannableString(format);
            spannableString.setSpan(textAppearanceSpan, format.indexOf(47), format.length(), 17);
            this.mTextView.setText(spannableString);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XLinearLayout, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (ThemeManager.isThemeChanged(configuration)) {
            if (this.mTextView != null) {
                this.mTextView.setTextAppearance(R.style.XNumberIndicatorSelectedTextStyle);
            }
            rU();
        }
    }
}
