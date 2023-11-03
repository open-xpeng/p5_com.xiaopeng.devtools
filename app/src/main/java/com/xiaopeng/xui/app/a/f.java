package com.xiaopeng.xui.app.a;

import android.app.Activity;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xui.app.a.e;
import java.util.Iterator;

/* compiled from: XActivityDismissCauseGroup.java */
/* loaded from: classes13.dex */
abstract class f implements e.a, e.InterfaceC0084e, e.g, e.i, h {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void qE();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void qF();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void qG();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void qH();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract e.InterfaceC0084e qI();

    f() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static f e(Activity activity, e.d dVar) {
        return new a(activity, dVar);
    }

    /* compiled from: XActivityDismissCauseGroup.java */
    /* loaded from: classes13.dex */
    private static class a extends f {
        private e.d aaA;
        private e.InterfaceC0084e aaD;
        private e.i aaE;
        private e.g aaF;
        private e.a aaG;
        private ArraySet<h> aay = new ArraySet<>();
        private Activity mActivity;

        a(Activity activity, e.d dVar) {
            this.mActivity = activity;
            this.aaA = dVar;
        }

        @Override // com.xiaopeng.xui.app.a.f
        public void qE() {
            this.aaD = e.b(this.mActivity, this.aaA);
            this.aay.add(this.aaD);
        }

        @Override // com.xiaopeng.xui.app.a.f
        public void qF() {
            this.aaE = e.c(this.mActivity, this.aaA);
            this.aay.add(this.aaE);
        }

        @Override // com.xiaopeng.xui.app.a.f
        public void qG() {
            this.aaF = e.d(this.mActivity, this.aaA);
            this.aay.add(this.aaF);
        }

        @Override // com.xiaopeng.xui.app.a.f
        public void qH() {
            this.aaG = e.a(this.mActivity, this.aaA);
            this.aay.add(this.aaG);
        }

        @Override // com.xiaopeng.xui.app.a.f
        public e.InterfaceC0084e qI() {
            return this.aaD;
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onCreate(Bundle bundle) {
            Iterator<h> it = this.aay.iterator();
            while (it.hasNext()) {
                it.next().onCreate(bundle);
            }
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onStart() {
            Iterator<h> it = this.aay.iterator();
            while (it.hasNext()) {
                it.next().onStart();
            }
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onResume() {
            Iterator<h> it = this.aay.iterator();
            while (it.hasNext()) {
                it.next().onResume();
            }
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void qA() {
            Iterator<h> it = this.aay.iterator();
            while (it.hasNext()) {
                it.next().qA();
            }
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onPause() {
            Iterator<h> it = this.aay.iterator();
            while (it.hasNext()) {
                it.next().onPause();
            }
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onStop() {
            Iterator<h> it = this.aay.iterator();
            while (it.hasNext()) {
                it.next().onStop();
            }
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onDestroy() {
            Iterator<h> it = this.aay.iterator();
            while (it.hasNext()) {
                it.next().onDestroy();
            }
        }

        @Override // com.xiaopeng.xui.app.a.e.a
        public void onBackPressed() {
            if (this.aaG != null) {
                this.aaG.onBackPressed();
            }
        }

        @Override // com.xiaopeng.xui.app.a.e.InterfaceC0084e
        public void qC() {
            if (this.aaD != null) {
                this.aaD.qC();
            }
        }

        @Override // com.xiaopeng.xui.app.a.e.g
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return this.aaF != null && this.aaF.onTouchEvent(motionEvent);
        }

        @Override // com.xiaopeng.xui.app.a.e.i
        public void qD() {
            if (this.aaE != null) {
                this.aaE.qD();
            }
        }

        @NonNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{ mBackSceneInspect=");
            sb.append(this.aaG != null ? "has" : "no");
            sb.append(", mOnPauseSceneInspect=");
            sb.append(this.aaD != null ? "has" : "no");
            sb.append(", mOutSideSceneInspect=");
            sb.append(this.aaF != null ? "has" : "no");
            sb.append(", mSpeedTimeOutSceneInspect=");
            sb.append(this.aaE != null ? "has" : "no");
            sb.append('}');
            return sb.toString();
        }
    }
}
