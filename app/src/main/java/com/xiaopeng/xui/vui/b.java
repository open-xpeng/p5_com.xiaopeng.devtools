package com.xiaopeng.xui.vui;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import com.xiaopeng.vui.commons.VuiUpdateType;
import com.xiaopeng.vui.commons.c;
import com.xiaopeng.vui.commons.d;
import com.xiaopeng.xui.d.f;
import java.util.List;

/* compiled from: VuiViewScene.java */
/* loaded from: classes13.dex */
public abstract class b implements d {
    private View mRootView;
    private c abP = null;
    private String abS = "";
    private com.xiaopeng.vui.commons.b acx = null;
    private List<Integer> acy = null;
    private Handler mHandler = new Handler();
    private View.OnAttachStateChangeListener acz = new View.OnAttachStateChangeListener() { // from class: com.xiaopeng.xui.vui.b.1
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            if (com.xiaopeng.xui.a.qn()) {
                b.this.rb();
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (com.xiaopeng.xui.a.qn()) {
                b.this.rc();
            }
        }
    };
    com.xiaopeng.vui.commons.a acA = new com.xiaopeng.vui.commons.a() { // from class: com.xiaopeng.xui.vui.-$$Lambda$b$A9DB7XYbQl-6bQIWkZI5BnaV8EQ
        @Override // com.xiaopeng.vui.commons.a
        public final void onVuiElementChaned(View view, VuiUpdateType vuiUpdateType) {
            b.this.b(view, vuiUpdateType);
        }
    };
    private boolean acB = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public void m(@NonNull View view) {
        log("initVui");
        if (!com.xiaopeng.xui.a.qn()) {
            return;
        }
        if (this.mRootView != null) {
            this.mRootView.removeOnAttachStateChangeListener(this.acz);
        }
        this.mRootView = view;
        view.addOnAttachStateChangeListener(this.acz);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rb() {
        c cVar = this.abP;
        String str = this.abS;
        View view = this.mRootView;
        if (cVar != null && str != null && view != null) {
            log("createVuiScene");
            try {
                if (this.acB) {
                    cVar.a(str, view, this, this.acA);
                } else {
                    cVar.a(str, view, this);
                }
            } catch (AbstractMethodError e) {
                cVar.a(str, view, this);
            }
            cVar.dU(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rc() {
        if (this.abP != null && this.abS != null) {
            log("destroyVuiScene");
            this.abP.dV(this.abS);
            this.abP.dW(this.abS);
            this.abP = null;
        }
        if (this.acx != null) {
            this.acx = null;
        }
    }

    private void log(String str) {
        f.f("VuiViewScene", " mSceneId " + this.abS + "  " + str + " hashcode " + hashCode() + " name " + getClass().getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(View view, VuiUpdateType vuiUpdateType) {
        c cVar = this.abP;
        String str = this.abS;
        if (cVar != null && !TextUtils.isEmpty(str) && view != null) {
            if (VuiUpdateType.UPDATE_VIEW.equals(vuiUpdateType)) {
                cVar.a(str, view);
            } else {
                cVar.b(str, view);
            }
        }
    }
}
