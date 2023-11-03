package com.xiaopeng.devtools.view.factorytest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/* loaded from: classes12.dex */
public class TestListView extends ListView {
    public TestListView(Context context) {
        super(context);
    }

    public TestListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TestListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
