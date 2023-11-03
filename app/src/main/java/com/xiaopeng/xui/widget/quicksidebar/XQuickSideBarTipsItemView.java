package com.xiaopeng.xui.widget.quicksidebar;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.view.XView;
import com.xiaopeng.xui.view.a;
import com.xiaopeng.xui.view.b.b;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class XQuickSideBarTipsItemView extends XView {
    private RectF ajd;
    private Paint aje;
    private Paint ajf;
    private int ajg;
    private boolean ajh;
    private int mBackgroundColor;
    private String mText;
    private int mTextColor;
    private float mTextSize;
    private int mWidth;

    public XQuickSideBarTipsItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XQuickSideBarTipsItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ajd = new RectF();
        this.mText = "";
        this.ajh = false;
        b(context, attributeSet);
    }

    private void b(Context context, AttributeSet attributeSet) {
        rV();
        this.mTextSize = context.getResources().getDimension(R.dimen.x_sidebar_tips_textsize);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.XQuickSideBarView);
            this.mTextColor = obtainStyledAttributes.getColor(R.styleable.XQuickSideBarView_sidebarTextColor, this.mTextColor);
            this.mBackgroundColor = obtainStyledAttributes.getColor(R.styleable.XQuickSideBarView_sidebarBackgroundColor, this.mBackgroundColor);
            obtainStyledAttributes.recycle();
        }
        final b a = b.a(getResources(), R.dimen.x_sidebar_tips_textsize);
        if (a != null && this.abr != null) {
            this.abr.a(new a.InterfaceC0085a() { // from class: com.xiaopeng.xui.widget.quicksidebar.-$$Lambda$XQuickSideBarTipsItemView$tsJAzTi-F2758oOSGEFbNSH2I6c
                @Override // com.xiaopeng.xui.view.a.InterfaceC0085a
                public final void onFontScaleChanged() {
                    XQuickSideBarTipsItemView.this.b(a);
                }
            });
        }
        this.aje = new Paint(1);
        this.ajf = new Paint(1);
        this.aje.setColor(this.mBackgroundColor);
        this.ajf.setColor(this.mTextColor);
        this.ajf.setTextSize(this.mTextSize);
        this.ajf.setStyle(Paint.Style.FILL);
        this.ajf.setTypeface(Typeface.create(getResources().getString(R.string.x_font_typeface_number_bold), 0));
        this.ajf.setTextAlign(Paint.Align.CENTER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(b bVar) {
        this.mTextSize = bVar.a(getResources().getDisplayMetrics());
        this.ajf.setTextSize(this.mTextSize);
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWidth = getMeasuredWidth();
        this.ajg = this.mWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(this.mText)) {
            return;
        }
        canvas.drawColor(getResources().getColor(17170445, null));
        this.ajd.set(0.0f, 0.0f, this.mWidth, this.ajg);
        canvas.drawRoundRect(this.ajd, 16.0f, 16.0f, this.aje);
        Paint.FontMetrics fontMetrics = this.ajf.getFontMetrics();
        canvas.drawText(this.mText, this.ajd.centerX(), (int) ((this.ajd.centerY() - (fontMetrics.top / 2.0f)) - (fontMetrics.bottom / 8.0f)), this.ajf);
    }

    public void setText(String str) {
        this.mText = str;
        this.ajf.getTextBounds(this.mText, 0, this.mText.length(), new Rect());
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.view.XView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (com.xiaopeng.xui.c.a.isThemeChanged(configuration)) {
            rV();
            this.aje.setColor(this.mBackgroundColor);
            this.ajf.setColor(this.mTextColor);
            invalidate();
        }
    }

    private void rV() {
        this.mTextColor = getColor(R.color.x_side_bar_tips_text_color);
        this.mBackgroundColor = getColor(R.color.x_side_bar_tips_bg_color);
    }

    private int getColor(int i) {
        return getResources().getColor(i, getContext().getTheme());
    }
}
