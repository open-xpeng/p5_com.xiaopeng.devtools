package com.google.android.flexbox;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FlexLine.java */
/* loaded from: classes11.dex */
public class b {
    int hp;
    int hq;
    int hr;
    int hs;
    float ht;
    float hu;
    int hv;
    int hw;
    int hy;
    int hz;
    int mItemCount;
    int mLeft = Integer.MAX_VALUE;
    int mTop = Integer.MAX_VALUE;
    int mRight = Integer.MIN_VALUE;
    int mBottom = Integer.MIN_VALUE;
    List<Integer> hx = new ArrayList();

    public int cb() {
        return this.hr;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    public int cc() {
        return this.mItemCount - this.hs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        this.mLeft = Math.min(this.mLeft, (view.getLeft() - flexItem.bX()) - i);
        this.mTop = Math.min(this.mTop, (view.getTop() - flexItem.bY()) - i2);
        this.mRight = Math.max(this.mRight, view.getRight() + flexItem.bZ() + i3);
        this.mBottom = Math.max(this.mBottom, view.getBottom() + flexItem.ca() + i4);
    }
}
