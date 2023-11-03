package com.xiaopeng.xui.vui.listenner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.xiaopeng.xui.vui.VuiRecyclerView;

/* loaded from: classes13.dex */
public class VuiRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private VuiRecyclerView acH;

    public VuiRecyclerViewScrollListener(VuiRecyclerView vuiRecyclerView) {
        this.acH = null;
        this.acH = vuiRecyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0 && this.acH != null) {
            this.acH.qT();
        }
    }
}
