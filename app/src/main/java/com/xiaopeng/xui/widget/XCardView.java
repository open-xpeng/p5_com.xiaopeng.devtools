package com.xiaopeng.xui.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XCardView extends CardView implements a {
    protected com.xiaopeng.xui.view.a abr;

    /* JADX WARN: Multi-variable type inference failed */
    public XCardView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.abr = com.xiaopeng.xui.view.a.b(this, attributeSet);
        c(this, attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public XCardView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i, 0);
        c(this, attributeSet);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        qX();
    }
}
