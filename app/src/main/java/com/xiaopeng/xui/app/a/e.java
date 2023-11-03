package com.xiaopeng.xui.app.a;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.xiaopeng.xui.app.a.e;

/* compiled from: XActivityDismissCause.java */
/* loaded from: classes13.dex */
class e {

    /* compiled from: XActivityDismissCause.java */
    /* loaded from: classes13.dex */
    interface a extends com.xiaopeng.xui.app.a.h {
        void onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: XActivityDismissCause.java */
    /* loaded from: classes13.dex */
    public interface d {
        void eg(int i);
    }

    /* compiled from: XActivityDismissCause.java */
    /* renamed from: com.xiaopeng.xui.app.a.e$e  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    interface InterfaceC0084e extends com.xiaopeng.xui.app.a.h {
        void qC();
    }

    /* compiled from: XActivityDismissCause.java */
    /* loaded from: classes13.dex */
    interface g extends com.xiaopeng.xui.app.a.h {
        boolean onTouchEvent(MotionEvent motionEvent);
    }

    /* compiled from: XActivityDismissCause.java */
    /* loaded from: classes13.dex */
    interface i extends com.xiaopeng.xui.app.a.h {
        void qD();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a a(Activity activity, d dVar) {
        return new b(activity, dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InterfaceC0084e b(Activity activity, d dVar) {
        return new f(activity, dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i c(Activity activity, d dVar) {
        return new j(activity, dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g d(Activity activity, d dVar) {
        return new h(activity, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: XActivityDismissCause.java */
    /* loaded from: classes13.dex */
    public static class c {
        private d aaA;
        Activity mActivity;

        c(Activity activity, d dVar) {
            this.mActivity = activity;
            this.aaA = dVar;
        }

        void dismiss(int i) {
            if (this.aaA != null) {
                this.aaA.eg(i);
            }
        }
    }

    /* compiled from: XActivityDismissCause.java */
    /* loaded from: classes13.dex */
    private static class b extends c implements a {
        b(Activity activity, d dVar) {
            super(activity, dVar);
        }

        @Override // com.xiaopeng.xui.app.a.e.a
        public void onBackPressed() {
            com.xiaopeng.xui.d.f.g("XActivityDismissCause", "onBackPressed");
            dismiss(1);
        }
    }

    /* compiled from: XActivityDismissCause.java */
    /* loaded from: classes13.dex */
    private static class f extends c implements InterfaceC0084e {
        private boolean aaB;

        f(Activity activity, d dVar) {
            super(activity, dVar);
            this.aaB = true;
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void qA() {
            com.xiaopeng.xui.d.f.g("XActivityDismissCause", "onRecreate");
            this.aaB = false;
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onPause() {
            boolean z = this.aaB;
            if (this.aaB) {
                dismiss(2);
            }
            this.aaB = true;
            com.xiaopeng.xui.d.f.g("XActivityDismissCause", "onPause : last " + z);
        }

        @Override // com.xiaopeng.xui.app.a.e.InterfaceC0084e
        public void qC() {
            com.xiaopeng.xui.d.f.g("XActivityDismissCause", "ignoreDismissOneshot mDismissOnPause false");
            this.aaB = false;
        }
    }

    /* compiled from: XActivityDismissCause.java */
    /* loaded from: classes13.dex */
    private static class h extends c implements g {
        private boolean aag;

        h(Activity activity, d dVar) {
            super(activity, dVar);
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onCreate(Bundle bundle) {
            TypedArray obtainStyledAttributes = this.mActivity.getTheme().obtainStyledAttributes(new int[]{16843611});
            this.aag = obtainStyledAttributes.getBoolean(0, false);
            obtainStyledAttributes.recycle();
        }

        @Override // com.xiaopeng.xui.app.a.e.g
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (d(motionEvent)) {
                dismiss(3);
                return true;
            }
            return false;
        }

        private boolean a(Context context, MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int scaledWindowTouchSlop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
            View decorView = this.mActivity.getWindow().getDecorView();
            int i = -scaledWindowTouchSlop;
            return x < i || y < i || x > decorView.getWidth() + scaledWindowTouchSlop || y > decorView.getHeight() + scaledWindowTouchSlop;
        }

        private boolean d(MotionEvent motionEvent) {
            return this.aag && ((motionEvent.getAction() == 0 && a(this.mActivity, motionEvent)) || motionEvent.getAction() == 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: XActivityDismissCause.java */
    /* loaded from: classes13.dex */
    public static class j extends c implements i {
        private static final Uri aac = Settings.System.getUriFor("key_panel_car_speed");
        private float aad;
        private final Runnable aae;
        private ContentObserver aan;
        final Handler mHandler;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void hU() {
            if (getSpeed() > 0.0f) {
                dismiss(4);
            }
        }

        j(Activity activity, d dVar) {
            super(activity, dVar);
            this.aad = 0.0f;
            this.mHandler = new Handler();
            this.aae = new Runnable() { // from class: com.xiaopeng.xui.app.a.-$$Lambda$e$j$4ea9n5N8wmuu2AVvNSvr6I0SPqI
                @Override // java.lang.Runnable
                public final void run() {
                    e.j.this.hU();
                }
            };
            this.aan = new ContentObserver(this.mHandler) { // from class: com.xiaopeng.xui.app.a.e.j.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z, Uri uri) {
                    super.onChange(z, uri);
                    j.this.qz();
                }
            };
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onResume() {
            bc(false);
            ay(this.mActivity.getApplicationContext());
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onPause() {
            az(this.mActivity.getApplicationContext());
            this.mHandler.removeCallbacks(this.aae);
        }

        @Override // com.xiaopeng.xui.app.a.h
        public void onDestroy() {
            this.mHandler.removeCallbacks(this.aae);
        }

        @Override // com.xiaopeng.xui.app.a.e.i
        public void qD() {
            bc(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void qz() {
            bc(true);
        }

        private void bc(boolean z) {
            float speed = getSpeed();
            boolean z2 = true;
            if (z && this.aad * speed > 0.0f) {
                z2 = false;
            }
            if (speed <= 0.0f) {
                this.mHandler.removeCallbacks(this.aae);
            } else if (z2) {
                this.mHandler.removeCallbacks(this.aae);
                this.mHandler.postDelayed(this.aae, 30000L);
            }
            this.aad = speed;
        }

        private float getSpeed() {
            return aA(this.mActivity);
        }

        static float aA(Context context) {
            try {
                return Settings.System.getFloat(context.getContentResolver(), "key_panel_car_speed", 0.0f);
            } catch (Exception e) {
                e.printStackTrace();
                return 0.0f;
            }
        }

        private void ay(Context context) {
            try {
                context.getContentResolver().registerContentObserver(aac, true, this.aan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void az(Context context) {
            try {
                context.getContentResolver().unregisterContentObserver(this.aan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
