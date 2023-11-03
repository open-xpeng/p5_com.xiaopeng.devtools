package com.xiaopeng.xui.widget.quicksidebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.b.f;
import com.xiaopeng.xui.view.XView;
import com.xiaopeng.xui.view.a;
import com.xiaopeng.xui.view.b.b;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes13.dex */
public class XQuickSideBarView extends XView implements a.InterfaceC0085a {
    private com.xiaopeng.xui.widget.quicksidebar.a.a ajo;
    private List<String> ajp;
    private int ajq;
    private float ajr;
    private int ajs;
    private float ajt;
    private float aju;
    private final float ajv;
    private boolean ajw;
    private boolean ajx;
    private int mHeight;
    private Paint mPaint;
    private int mTextColor;
    private float mTextSize;
    private int mWidth;

    public XQuickSideBarView(Context context) {
        this(context, null);
    }

    public XQuickSideBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XQuickSideBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ajq = -1;
        this.mPaint = new Paint();
        this.ajv = 14.0f;
        this.ajx = false;
        b(context, attributeSet);
    }

    private void b(Context context, AttributeSet attributeSet) {
        this.ajp = Arrays.asList(context.getResources().getStringArray(R.array.x_quick_side_bar_letters));
        rV();
        this.mTextSize = context.getResources().getDimensionPixelSize(R.dimen.x_sidebar_textsize);
        this.ajr = context.getResources().getDimensionPixelSize(R.dimen.x_sidebar_choose_textsize);
        this.aju = context.getResources().getDimension(R.dimen.x_sidebar_item_height);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.XQuickSideBarView);
            this.mTextColor = obtainStyledAttributes.getColor(R.styleable.XQuickSideBarView_sidebarTextColor, this.mTextColor);
            this.ajs = obtainStyledAttributes.getColor(R.styleable.XQuickSideBarView_sidebarTextColorChoose, this.ajs);
            this.mTextSize = obtainStyledAttributes.getDimension(R.styleable.XQuickSideBarView_sidebarTextSize, this.mTextSize);
            this.ajr = obtainStyledAttributes.getDimension(R.styleable.XQuickSideBarView_sidebarTextSizeChoose, this.ajr);
            this.ajw = obtainStyledAttributes.getBoolean(R.styleable.XQuickSideBarView_sidebarHighlight, false);
            final b a = b.a(obtainStyledAttributes, R.styleable.XQuickSideBarView_sidebarTextSize, R.dimen.x_sidebar_textsize);
            final b a2 = b.a(obtainStyledAttributes, R.styleable.XQuickSideBarView_sidebarTextSizeChoose, R.dimen.x_sidebar_choose_textsize);
            if (this.abr != null) {
                this.abr.a(new a.InterfaceC0085a() { // from class: com.xiaopeng.xui.widget.quicksidebar.-$$Lambda$XQuickSideBarView$WYQGIw5Mmn7cQl96ps5lacCAFJQ
                    @Override // com.xiaopeng.xui.view.a.InterfaceC0085a
                    public final void onFontScaleChanged() {
                        XQuickSideBarView.this.a(a, a2);
                    }
                });
            }
            this.aju = obtainStyledAttributes.getDimension(R.styleable.XQuickSideBarView_sidebarItemHeight, this.aju);
            obtainStyledAttributes.recycle();
        }
        if (this.abr != null && this.abr.qP() != null) {
            this.abr.qP().setCallback(new ThemeViewModel.OnCallback() { // from class: com.xiaopeng.xui.widget.quicksidebar.-$$Lambda$XQuickSideBarView$_6Sgv2FGYju35dJAqO5gDUyuq70
                @Override // com.xiaopeng.libtheme.ThemeViewModel.OnCallback
                public final void onThemeChanged() {
                    XQuickSideBarView.this.er();
                }
            });
        }
        setSoundEffectsEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(b bVar, b bVar2) {
        if (bVar != null) {
            this.mTextSize = bVar.a(getResources().getDisplayMetrics());
        }
        if (bVar2 != null) {
            this.ajr = bVar2.a(getResources().getDisplayMetrics());
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void er() {
        rV();
        invalidate();
    }

    @Override // com.xiaopeng.xui.view.a.InterfaceC0085a
    public void onFontScaleChanged() {
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mHeight = getMeasuredHeight();
        this.mWidth = getMeasuredWidth();
        float size = this.mHeight / this.ajp.size();
        if (this.aju <= size) {
            size = this.aju;
        }
        this.aju = size;
        this.ajt = this.aju;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = 0;
        while (i < this.ajp.size()) {
            Paint paint = new Paint();
            if (i == this.ajq) {
                this.mPaint.setTextSize(this.ajr);
                this.mPaint.setColor(this.ajs);
                paint.setColor(getColor(R.color.x_side_bar_item_choose_bg_color));
            } else {
                this.mPaint.setTextSize(this.mTextSize);
                this.mPaint.setColor(this.mTextColor);
                paint.setColor(getColor(R.color.x_side_bar_item_bg_color));
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            float f = (this.mWidth - this.ajt) / 2.0f;
            int i2 = i + 1;
            RectF rectF = new RectF(f, (this.aju * i) + 14.0f, this.ajt + f, 14.0f + (i2 * this.aju));
            canvas.drawRoundRect(rectF, 4.0f, 4.0f, paint);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setTextAlign(Paint.Align.CENTER);
            this.mPaint.setFakeBoldText(true);
            this.mPaint.setTypeface(Typeface.create(getResources().getString(R.string.x_font_typeface_medium), 0));
            Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
            canvas.drawText(this.ajp.get(i), rectF.centerX(), rectF.centerY() + (((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom), this.mPaint);
            this.mPaint.reset();
            i = i2;
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.ajq;
        int i2 = (int) ((y - 14.0f) / this.aju);
        switch (action) {
            case 0:
            case 2:
                this.ajx = true;
                if (i != i2 && i2 >= 0 && i2 < this.ajp.size()) {
                    this.ajq = i2;
                    invalidate();
                    f.qN().play(2);
                    if (this.ajo != null) {
                        this.ajo.br(true);
                        this.ajo.G(this.ajp.get(i2), this.ajq);
                        break;
                    }
                }
                break;
            case 1:
                this.ajx = false;
                this.ajq = -1;
                if (this.ajo != null) {
                    this.ajo.br(false);
                }
                if (!this.ajw) {
                    invalidate();
                    break;
                }
                break;
            case 3:
                this.ajx = false;
                f.qN().release(2);
                if (this.ajo != null) {
                    this.ajo.br(false);
                }
                this.ajq = -1;
                invalidate();
                break;
        }
        return true;
    }

    public com.xiaopeng.xui.widget.quicksidebar.a.a getListener() {
        return this.ajo;
    }

    public void setOnQuickSideBarTouchListener(com.xiaopeng.xui.widget.quicksidebar.a.a aVar) {
        this.ajo = aVar;
    }

    public List<String> getLetters() {
        return this.ajp;
    }

    public void setLetters(List<String> list) {
        this.ajp = list;
        invalidate();
    }

    private void rV() {
        this.mTextColor = getColor(R.color.x_theme_text_03);
        this.ajs = getColor(R.color.x_side_bar_text_choose_color);
    }

    private int getColor(int i) {
        return getResources().getColor(i, getContext().getTheme());
    }

    public void setSideBarHighLight(String str) {
        k(str, false);
    }

    public void k(String str, boolean z) {
        int indexOf;
        if (!this.ajw || this.ajx) {
            return;
        }
        fl("setSideBarHighLight");
        if (!TextUtils.isEmpty(str)) {
            if (!z) {
                indexOf = this.ajp.indexOf(str.toUpperCase());
            } else {
                indexOf = this.ajp.indexOf(str);
            }
            if (indexOf >= 0 && this.ajq != indexOf) {
                this.ajq = indexOf;
                invalidate();
            }
        }
    }

    private void setSideBarHighLight(int i) {
        if (!this.ajw || this.ajx) {
            return;
        }
        int size = this.ajp.size();
        if (i >= 0 && i < size && this.ajq != i) {
            this.ajq = i;
            invalidate();
        }
    }
}
