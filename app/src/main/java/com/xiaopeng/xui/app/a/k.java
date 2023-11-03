package com.xiaopeng.xui.app.a;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;

/* compiled from: XActivityWindowVisible.java */
/* loaded from: classes13.dex */
public class k implements h {
    private float aai;
    private boolean aak;
    private Activity mActivity;

    public k(Activity activity) {
        this.mActivity = activity;
    }

    public void bd(boolean z) {
        if (z != this.aak) {
            com.xiaopeng.xui.d.f.g("XActivityWindowVisible", "setAutoVisibleEnableOnPause: " + z);
        }
        this.aak = z;
    }

    public void be(boolean z) {
        if (this.aak) {
            com.xiaopeng.xui.d.f.g("XActivityWindowVisible", "changeWindowVisible: " + z);
            Window window = this.mActivity.getWindow();
            if (window != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                if (attributes != null) {
                    attributes.alpha = z ? 1.0f : 0.0f;
                    attributes.dimAmount = z ? this.aai : 0.0f;
                }
                window.setAttributes(attributes);
            }
        }
    }

    @Override // com.xiaopeng.xui.app.a.h
    public void onCreate(Bundle bundle) {
        TypedArray obtainStyledAttributes = this.mActivity.getTheme().obtainStyledAttributes(new int[]{16842802});
        this.aai = obtainStyledAttributes.getFloat(0, 1.0f);
        obtainStyledAttributes.recycle();
    }

    @Override // com.xiaopeng.xui.app.a.h
    public void onResume() {
        be(true);
    }

    @Override // com.xiaopeng.xui.app.a.h
    public void onPause() {
        be(false);
    }

    @NonNull
    public String toString() {
        return "{mDimAlpha=" + this.aai + ", mAutoVisibility=" + this.aak + ", mActivity=" + this.mActivity + '}';
    }
}
