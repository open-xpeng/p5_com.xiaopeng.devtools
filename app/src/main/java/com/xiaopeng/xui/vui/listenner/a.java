package com.xiaopeng.xui.vui.listenner;

import android.view.ViewTreeObserver;
import com.xiaopeng.xui.vui.VuiRecyclerView;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: VuiRecyclerViewGlobalLayoutListener.java */
/* loaded from: classes13.dex */
public class a implements ViewTreeObserver.OnGlobalLayoutListener {
    private AtomicInteger acG = new AtomicInteger(0);
    private VuiRecyclerView acH;

    public a(VuiRecyclerView vuiRecyclerView) {
        this.acH = null;
        this.acH = vuiRecyclerView;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (this.acG.get() > 0 && this.acH != null) {
            this.acG.decrementAndGet();
            this.acH.qT();
        }
    }

    public void re() {
        this.acG.incrementAndGet();
    }
}
