package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.media.subtitle.Cea708CCParser;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.vui.commons.b;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.g;
import com.xiaopeng.xui.widget.XTabLayout;

/* loaded from: classes13.dex */
public class XTabsBar extends XRelativeLayout implements b {
    private XTabLayout aht;
    private XImageButton ahu;
    private a ahv;
    private ViewGroup ahw;

    /* loaded from: classes13.dex */
    public interface a extends XTabLayout.b {
        void o(View view);
    }

    public XTabsBar(Context context) {
        this(context, null);
    }

    public XTabsBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XTabsBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XTabsBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        Resources.Theme theme = getContext().getTheme();
        if (i == 0) {
            i = R.style.XTabsBarAppearance_Left;
        }
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, R.styleable.XTabsBar, i, i);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.XTabsBar_tabsBarMarginStart, eN(24));
        float dimension2 = obtainStyledAttributes.getDimension(R.styleable.XTabsBar_tabsBarTabGap, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(R.styleable.XTabsBar_tabsBarTabWidth, eN(ThemeManager.UI_MODE_THEME_MASK));
        float dimension4 = obtainStyledAttributes.getDimension(R.styleable.XTabsBar_tabsBarTabHeight, eN(Cea708CCParser.Const.CODE_C1_CW2));
        int integer = obtainStyledAttributes.getInteger(R.styleable.XTabsBar_tabsBarBtnVisibility, 0);
        int integer2 = obtainStyledAttributes.getInteger(R.styleable.XTabsBar_tabsBarTitleVisibility, 8);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(context).inflate(R.layout.x_tabsbar, this);
        this.ahu = (XImageButton) findViewById(R.id.x_tabsbar_btn_close);
        this.ahu.setVisibility(integer);
        this.ahu.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XTabsBar$iRbI3vo-Z6Z4-ukE-66oGLKzfNw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XTabsBar.this.n(view);
            }
        });
        this.ahw = (ViewGroup) findViewById(R.id.x_x_tabsbar_btn_close_lay);
        ((XTextView) findViewById(R.id.x_tabsbar_tv_title)).setVisibility(integer2);
        this.aht = new XTabLayout(context, attributeSet, i, i2);
        int tabCount = this.aht.getTabCount();
        int i3 = (int) ((dimension3 * tabCount) + (dimension2 * (tabCount - 1)));
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (tabCount == 0) {
            i3 = (int) (displayMetrics.widthPixels - dimension);
        }
        int i4 = getResources().getConfiguration().orientation;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, (int) dimension4);
        if (i4 == 1) {
            layoutParams.addRule(14);
        } else {
            layoutParams.addRule(9);
            layoutParams.leftMargin = (int) dimension;
        }
        addView(this.aht, layoutParams);
        this.aht.setOnTabChangeListener(new XTabLayout.b() { // from class: com.xiaopeng.xui.widget.XTabsBar.1
            @Override // com.xiaopeng.xui.widget.XTabLayout.b
            public boolean b(XTabLayout xTabLayout, int i5, boolean z, boolean z2) {
                if (XTabsBar.this.ahv != null) {
                    return XTabsBar.this.ahv.b(xTabLayout, i5, z, z2);
                }
                return false;
            }

            @Override // com.xiaopeng.xui.widget.XTabLayout.b
            public void c(XTabLayout xTabLayout, int i5, boolean z, boolean z2) {
                if (XTabsBar.this.ahv != null) {
                    XTabsBar.this.ahv.c(xTabLayout, i5, z, z2);
                }
            }

            @Override // com.xiaopeng.xui.widget.XTabLayout.b
            public void d(XTabLayout xTabLayout, int i5, boolean z, boolean z2) {
                if (XTabsBar.this.ahv != null) {
                    XTabsBar.this.ahv.d(xTabLayout, i5, z, z2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(View view) {
        if (this.ahv != null) {
            this.ahv.o(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XRelativeLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        g.a(this.ahu, this.ahw);
    }

    protected int eN(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public void setOnTabsClickListener(a aVar) {
        this.ahv = aVar;
    }

    public int getTabCount() {
        if (this.aht != null) {
            return this.aht.getTabCount();
        }
        return 0;
    }

    public void setTabLayoutVisible(boolean z) {
        if (this.aht != null) {
            this.aht.setVisibility(z ? 0 : 8);
        }
    }

    public int getSelectedTabIndex() {
        if (this.aht != null) {
            return this.aht.getSelectedTabIndex();
        }
        return -1;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (this.aht != null) {
            this.aht.setEnabled(z);
        }
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.aht != null && this.aht.isEnabled();
    }
}
