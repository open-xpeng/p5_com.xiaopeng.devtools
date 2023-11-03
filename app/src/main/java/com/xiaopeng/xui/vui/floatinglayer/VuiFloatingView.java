package com.xiaopeng.xui.vui.floatinglayer;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.xiaopeng.xui.d.f;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes13.dex */
public class VuiFloatingView extends FrameLayout {
    private AnimatedImageDrawable acD;
    private a acE;
    private boolean acF;
    private Drawable mDrawable;
    private int mType;

    /* loaded from: classes13.dex */
    public interface a {
        void ex(int i);
    }

    public void rd() {
        this.acF = true;
    }

    public void setOnTouchListener(a aVar) {
        this.acE = aVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() == 0 || motionEvent.getAction() == 4) && this.acE != null) {
            this.acE.ex(this.mType);
            if (this.acD != null) {
                rd();
                return true;
            }
            return true;
        }
        return true;
    }

    public int getVisibleHeight() {
        if (this.mDrawable != null) {
            return this.mDrawable.getIntrinsicHeight();
        }
        return 0;
    }

    public int getVisibleWidth() {
        if (this.mDrawable != null) {
            return this.mDrawable.getIntrinsicWidth();
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        f.g("VuiFloatingView", "onAttachedToWindow type : " + this.mType);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f.f("VuiFloatingView", "onDetachedFromWindow type : " + this.mType);
    }
}
