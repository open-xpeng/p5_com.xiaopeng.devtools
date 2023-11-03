package com.xiaopeng.xui.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XConstraintLayout extends ConstraintLayout implements a {
    protected com.xiaopeng.xui.view.a abr;

    public XConstraintLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public XConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i, 0);
        c(this, attributeSet);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        qX();
    }
}
