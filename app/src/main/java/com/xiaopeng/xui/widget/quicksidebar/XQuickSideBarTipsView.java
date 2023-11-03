package com.xiaopeng.xui.widget.quicksidebar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.xiaopeng.lib.framework.moduleinterface.carcontroller.IInputController;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.XRelativeLayout;
import java.lang.ref.WeakReference;

/* loaded from: classes13.dex */
public class XQuickSideBarTipsView extends XRelativeLayout {
    private XQuickSideBarTipsItemView aji;
    private a ajj;
    private long ajk;
    private boolean ajl;

    public XQuickSideBarTipsView(Context context) {
        this(context, null);
    }

    public XQuickSideBarTipsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XQuickSideBarTipsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ajl = true;
        b(context, attributeSet);
    }

    private void b(Context context, AttributeSet attributeSet) {
        this.ajj = new a(this);
        if (attributeSet != null) {
            this.aji = new XQuickSideBarTipsItemView(context, attributeSet);
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.XQuickSideBarView);
            this.ajk = obtainStyledAttributes.getInteger(R.styleable.XQuickSideBarView_sidebarTipsDelayTime, IInputController.KEYCODE_KNOB_WIND_SPD_UP);
            obtainStyledAttributes.recycle();
            addView(this.aji, new RelativeLayout.LayoutParams(-2, -2));
        }
    }

    public void setAnimationHideTips(boolean z) {
        this.ajl = z;
    }

    public void setText(String str) {
        this.aji.setText(str);
    }

    public void setDelayedTime(long j) {
        this.ajk = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class a extends Handler {
        private final WeakReference<XQuickSideBarTipsView> ajn;

        public a(XQuickSideBarTipsView xQuickSideBarTipsView) {
            this.ajn = new WeakReference<>(xQuickSideBarTipsView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            XQuickSideBarTipsView xQuickSideBarTipsView = this.ajn.get();
            if (xQuickSideBarTipsView != null && message.what == 1) {
                xQuickSideBarTipsView.bq(xQuickSideBarTipsView.ajl);
                xQuickSideBarTipsView.ajj.removeMessages(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bq(boolean z) {
        if (z) {
            animate().alpha(0.0f).setDuration(200L).setListener(new AnimatorListenerAdapter() { // from class: com.xiaopeng.xui.widget.quicksidebar.XQuickSideBarTipsView.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    XQuickSideBarTipsView.this.setVisibility(4);
                    XQuickSideBarTipsView.this.setAlpha(1.0f);
                }
            });
        } else {
            setVisibility(4);
        }
    }
}
