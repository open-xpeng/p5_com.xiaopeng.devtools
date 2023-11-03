package com.xiaopeng.xui.app.a;

import android.app.Activity;
import android.os.Handler;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xui.app.a.d;
import com.xiaopeng.xui.app.a.g;

/* compiled from: XActivityDismissExtend.java */
/* loaded from: classes13.dex */
abstract class g implements d, h {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: XActivityDismissExtend.java */
    /* loaded from: classes13.dex */
    public interface a {
        void eh(int i);

        void ei(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(a aVar);

    g() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g b(Activity activity) {
        return new b(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: XActivityDismissExtend.java */
    /* loaded from: classes13.dex */
    public static class b extends g {
        private Runnable aaH;
        private d.a aaI;
        @Nullable
        private a aaJ;
        private Activity mActivity;
        private int aal = 0;
        private long aaj = 0;
        private final Handler mHandler = new Handler();

        b(Activity activity) {
            this.mActivity = activity;
        }

        @Override // com.xiaopeng.xui.app.a.g
        void a(a aVar) {
            this.aaJ = aVar;
        }

        @Override // com.xiaopeng.xui.app.a.d
        @CallSuper
        public void dismiss(final int i) {
            if (this.aaI != null && this.aaI.x(i, this.aal)) {
                com.xiaopeng.xui.d.f.g("XActivityDismiss", "dismiss: intercept for " + ek(i));
                return;
            }
            if (this.aaJ != null) {
                this.aaJ.eh(i);
            }
            if (this.aaj == 0) {
                ej(i);
                return;
            }
            com.xiaopeng.xui.d.f.g("XActivityDismiss", "dismiss: type : " + el(this.aal) + " , cause : " + ek(i) + " " + this.aaH);
            if (this.aaH == null) {
                this.aaH = new Runnable() { // from class: com.xiaopeng.xui.app.a.-$$Lambda$g$b$XBFPcneMD4tCdTuiLXKLBIipwXw
                    @Override // java.lang.Runnable
                    public final void run() {
                        g.b.this.em(i);
                    }
                };
                this.mHandler.postDelayed(this.aaH, this.aaj);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void em(int i) {
            ej(i);
            this.aaH = null;
            if (this.aaJ != null) {
                this.aaJ.ei(i);
            }
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onDestroy() {
            if (this.aaH != null) {
                this.mHandler.removeCallbacks(this.aaH);
                this.aaH = null;
            }
        }

        private void ej(int i) {
            com.xiaopeng.xui.d.f.g("XActivityDismiss", "_dismiss: type : " + el(this.aal) + " , cause : " + ek(i));
            switch (this.aal) {
                case 0:
                    com.xiaopeng.xui.d.a.a(this.mActivity);
                    return;
                case 1:
                    com.xiaopeng.xui.d.a.a(this.mActivity, true);
                    return;
                default:
                    return;
            }
        }

        private String ek(int i) {
            switch (i) {
                case 1:
                    return "back";
                case 2:
                    return "pause";
                case 3:
                    return "outside";
                case 4:
                    return "timeout";
                default:
                    return "user";
            }
        }

        private String el(int i) {
            if (i == 1) {
                return "moveToBack";
            }
            return "finish";
        }

        @NonNull
        public String toString() {
            return "{mDismissType=" + this.aal + ", mDismissDelay=" + this.aaj + '}';
        }
    }
}
