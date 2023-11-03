package com.xiaopeng.xui.vui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.xiaopeng.vui.commons.c;
import com.xiaopeng.xui.vui.listenner.VuiRecyclerViewScrollListener;
import java.util.List;

/* loaded from: classes13.dex */
public class VuiRecyclerView extends RecyclerView implements com.xiaopeng.vui.commons.b, a {
    private boolean abM;
    private com.xiaopeng.xui.vui.listenner.a abN;
    private VuiRecyclerViewScrollListener abO;
    private c abP;
    private int abQ;
    private Runnable abR;
    private String abS;
    private boolean abT;
    private boolean abU;
    private boolean abV;
    private final RecyclerView.AdapterDataObserver abW;
    public boolean abX;
    public boolean abY;
    private boolean abZ;
    private boolean aca;
    private List<String[]> acb;
    private Handler mHandler;
    private int mItemCount;
    private int mStartPosition;

    public VuiRecyclerView(Context context) {
        super(context);
        this.abM = true;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.abQ = 1000;
        this.abR = new Runnable() { // from class: com.xiaopeng.xui.vui.VuiRecyclerView.1
            @Override // java.lang.Runnable
            public void run() {
                if (VuiRecyclerView.this.abP != null && !TextUtils.isEmpty(VuiRecyclerView.this.abS)) {
                    VuiRecyclerView.this.abP.a(VuiRecyclerView.this.abS, VuiRecyclerView.this);
                }
            }
        };
        this.abT = true;
        this.abU = true;
        this.abV = false;
        this.abW = new RecyclerView.AdapterDataObserver() { // from class: com.xiaopeng.xui.vui.VuiRecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i, int i2) {
                if (VuiRecyclerView.this.abV) {
                    VuiRecyclerView.this.qT();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
                if (VuiRecyclerView.this.abV) {
                    VuiRecyclerView.this.qT();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i, int i2) {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i, int i2) {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeMoved(int i, int i2, int i3) {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }
        };
        this.abX = false;
        this.abY = false;
        this.abZ = false;
        this.mStartPosition = 0;
        this.mItemCount = -1;
        this.aca = false;
        this.acb = null;
        qS();
    }

    public VuiRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.abM = true;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.abQ = 1000;
        this.abR = new Runnable() { // from class: com.xiaopeng.xui.vui.VuiRecyclerView.1
            @Override // java.lang.Runnable
            public void run() {
                if (VuiRecyclerView.this.abP != null && !TextUtils.isEmpty(VuiRecyclerView.this.abS)) {
                    VuiRecyclerView.this.abP.a(VuiRecyclerView.this.abS, VuiRecyclerView.this);
                }
            }
        };
        this.abT = true;
        this.abU = true;
        this.abV = false;
        this.abW = new RecyclerView.AdapterDataObserver() { // from class: com.xiaopeng.xui.vui.VuiRecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i, int i2) {
                if (VuiRecyclerView.this.abV) {
                    VuiRecyclerView.this.qT();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
                if (VuiRecyclerView.this.abV) {
                    VuiRecyclerView.this.qT();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i, int i2) {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i, int i2) {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeMoved(int i, int i2, int i3) {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }
        };
        this.abX = false;
        this.abY = false;
        this.abZ = false;
        this.mStartPosition = 0;
        this.mItemCount = -1;
        this.aca = false;
        this.acb = null;
        c(this, attributeSet);
        qS();
    }

    public VuiRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.abM = true;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.abQ = 1000;
        this.abR = new Runnable() { // from class: com.xiaopeng.xui.vui.VuiRecyclerView.1
            @Override // java.lang.Runnable
            public void run() {
                if (VuiRecyclerView.this.abP != null && !TextUtils.isEmpty(VuiRecyclerView.this.abS)) {
                    VuiRecyclerView.this.abP.a(VuiRecyclerView.this.abS, VuiRecyclerView.this);
                }
            }
        };
        this.abT = true;
        this.abU = true;
        this.abV = false;
        this.abW = new RecyclerView.AdapterDataObserver() { // from class: com.xiaopeng.xui.vui.VuiRecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i2, int i22) {
                if (VuiRecyclerView.this.abV) {
                    VuiRecyclerView.this.qT();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i2, int i22, @Nullable Object obj) {
                if (VuiRecyclerView.this.abV) {
                    VuiRecyclerView.this.qT();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i2, int i22) {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i2, int i22) {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeMoved(int i2, int i22, int i3) {
                if (VuiRecyclerView.this.abU && VuiRecyclerView.this.abN != null) {
                    VuiRecyclerView.this.abN.re();
                }
            }
        };
        this.abX = false;
        this.abY = false;
        this.abZ = false;
        this.mStartPosition = 0;
        this.mItemCount = -1;
        this.aca = false;
        this.acb = null;
        c(this, attributeSet);
        qS();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        fl("onAttachedToWindow:" + this.abS);
        if (this.abU && this.abN != null) {
            getViewTreeObserver().addOnGlobalLayoutListener(this.abN);
        }
        if (this.abT && this.abO != null) {
            addOnScrollListener(this.abO);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        fl("onDetachedFromWindow:" + this.abS);
        if (this.abU && this.abN != null) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.abN);
        }
        if (this.abT && this.abO != null) {
            removeOnScrollListener(this.abO);
        }
        this.mHandler.removeCallbacks(this.abR);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        qX();
    }

    private void qS() {
        this.abN = new com.xiaopeng.xui.vui.listenner.a(this);
        this.abO = new VuiRecyclerViewScrollListener(this);
        bf(true);
    }

    public void qT() {
        if (!TextUtils.isEmpty(this.abS) && this.abP != null) {
            this.mHandler.removeCallbacks(this.abR);
            this.mHandler.postDelayed(this.abR, this.abQ);
            return;
        }
        fl("updateVuiScene sceneid is empty");
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        RecyclerView.Adapter adapter2 = getAdapter();
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.abW);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.abW);
        }
        if (this.abU && this.abN != null) {
            this.abN.re();
        }
    }

    public void setCanVuiScrollDown(boolean z) {
        this.abX = z;
    }

    public void setCanVuiScrollRight(boolean z) {
        this.abY = z;
    }

    public void setVuiCanControlScroll(boolean z) {
        this.abM = z;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (!this.abU) {
            qT();
        }
    }

    public void setOrderState(boolean z) {
        this.aca = z;
    }
}
