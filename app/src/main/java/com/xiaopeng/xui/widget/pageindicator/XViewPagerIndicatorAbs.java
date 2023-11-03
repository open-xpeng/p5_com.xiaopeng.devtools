package com.xiaopeng.xui.widget.pageindicator;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.XLinearLayout;

/* loaded from: classes13.dex */
public abstract class XViewPagerIndicatorAbs extends XLinearLayout {
    private ViewPager aiY;
    private PagerAdapter aiZ;
    private ViewPager.OnAdapterChangeListener aja;
    private ViewPager.SimpleOnPageChangeListener ajb;
    private DataSetObserver mDataSetObserver;

    public abstract void eU(int i);

    public abstract void eV(int i);

    public XViewPagerIndicatorAbs(Context context) {
        this(context, null);
    }

    public XViewPagerIndicatorAbs(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.style.XPagerIndicator);
    }

    public XViewPagerIndicatorAbs(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XPagerIndicator);
    }

    public XViewPagerIndicatorAbs(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDataSetObserver = new DataSetObserver() { // from class: com.xiaopeng.xui.widget.pageindicator.XViewPagerIndicatorAbs.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                XViewPagerIndicatorAbs.this.eU(XViewPagerIndicatorAbs.this.aiZ != null ? XViewPagerIndicatorAbs.this.aiZ.getCount() : 0);
            }
        };
        this.aja = new ViewPager.OnAdapterChangeListener() { // from class: com.xiaopeng.xui.widget.pageindicator.XViewPagerIndicatorAbs.2
            @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2) {
                XViewPagerIndicatorAbs.this.setAdapter(pagerAdapter2);
            }
        };
        this.ajb = new ViewPager.SimpleOnPageChangeListener() { // from class: com.xiaopeng.xui.widget.pageindicator.XViewPagerIndicatorAbs.3
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i3) {
                XViewPagerIndicatorAbs.this.eV(i3);
            }
        };
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init() {
        setOrientation(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdapter(PagerAdapter pagerAdapter) {
        if (pagerAdapter == null) {
            return;
        }
        this.aiZ = pagerAdapter;
        this.aiZ.registerDataSetObserver(this.mDataSetObserver);
        eU(this.aiZ.getCount());
    }

    public void setupWithViewPager(ViewPager viewPager) {
        if (viewPager == null) {
            return;
        }
        this.aiY = viewPager;
        setAdapter(viewPager.getAdapter());
        int currentItem = viewPager.getCurrentItem();
        if (currentItem > -1) {
            eV(currentItem);
        }
        viewPager.removeOnAdapterChangeListener(this.aja);
        viewPager.addOnAdapterChangeListener(this.aja);
        viewPager.removeOnPageChangeListener(this.ajb);
        viewPager.addOnPageChangeListener(this.ajb);
    }
}
