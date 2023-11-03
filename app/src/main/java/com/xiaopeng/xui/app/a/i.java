package com.xiaopeng.xui.app.a;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.CallSuper;
import com.xiaopeng.xpui.R;

/* compiled from: XActivityTemplateExtend.java */
/* loaded from: classes13.dex */
abstract class i implements h {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(com.xiaopeng.xui.app.a.f fVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(g gVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(j jVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(k kVar);

    i() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i b(Activity activity, int i) {
        switch (i) {
            case 1:
                return new e(activity);
            case 2:
                return new c(activity);
            case 3:
                return new b(activity);
            case 4:
                return new f(activity);
            default:
                return new d(activity);
        }
    }

    /* compiled from: XActivityTemplateExtend.java */
    /* loaded from: classes13.dex */
    private static class a extends i {
        g aaK;
        Activity mActivity;

        a(Activity activity) {
            this.mActivity = activity;
        }

        @Override // com.xiaopeng.xui.app.a.h
        @CallSuper
        public void onCreate(Bundle bundle) {
        }

        @Override // com.xiaopeng.xui.app.a.i
        void a(j jVar) {
        }

        @Override // com.xiaopeng.xui.app.a.i
        @CallSuper
        void a(com.xiaopeng.xui.app.a.f fVar) {
            fVar.qH();
        }

        @Override // com.xiaopeng.xui.app.a.i
        @CallSuper
        void a(k kVar) {
        }

        @Override // com.xiaopeng.xui.app.a.i
        @CallSuper
        void a(g gVar) {
            this.aaK = gVar;
        }
    }

    /* compiled from: XActivityTemplateExtend.java */
    /* loaded from: classes13.dex */
    private static class d extends a {
        d(Activity activity) {
            super(activity);
        }
    }

    /* compiled from: XActivityTemplateExtend.java */
    /* loaded from: classes13.dex */
    private static class f extends a {
        f(Activity activity) {
            super(activity);
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.h
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
        }
    }

    /* compiled from: XActivityTemplateExtend.java */
    /* loaded from: classes13.dex */
    private static class c extends a {
        c(Activity activity) {
            super(activity);
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.h
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
        }
    }

    /* compiled from: XActivityTemplateExtend.java */
    /* loaded from: classes13.dex */
    private static class b extends a {
        b(Activity activity) {
            super(activity);
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.h
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.i
        void a(com.xiaopeng.xui.app.a.f fVar) {
            super.a(fVar);
            fVar.qE();
            fVar.qG();
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.i
        void a(j jVar) {
            jVar.ep(-2).eq(-2).er(17);
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.i
        void a(k kVar) {
            super.a(kVar);
            kVar.bd(true);
        }
    }

    /* compiled from: XActivityTemplateExtend.java */
    /* loaded from: classes13.dex */
    private static class e extends a {
        e(Activity activity) {
            super(activity);
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.h
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.mActivity.requestWindowFeature(14);
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.i
        void a(com.xiaopeng.xui.app.a.f fVar) {
            super.a(fVar);
            fVar.qE();
            fVar.qG();
            fVar.qF();
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.i
        void a(j jVar) {
            int i = this.mActivity.getResources().getConfiguration().orientation;
            int e = com.xiaopeng.xui.d.h.e(this.mActivity, R.dimen.x_compat_app_panel_x);
            int e2 = com.xiaopeng.xui.d.h.e(this.mActivity, R.dimen.x_compat_app_panel_y);
            int e3 = com.xiaopeng.xui.d.h.e(this.mActivity, R.dimen.x_compat_app_panel_width);
            int e4 = com.xiaopeng.xui.d.h.e(this.mActivity, R.dimen.x_compat_app_panel_height);
            switch (i) {
                case 1:
                    jVar.en(e).eo(e2).ep(e3).eq(e4).er(80);
                    return;
                case 2:
                    jVar.en(e).eo(e2).ep(e3).eq(e4).er(8388627);
                    return;
                default:
                    return;
            }
        }

        @Override // com.xiaopeng.xui.app.a.i.a, com.xiaopeng.xui.app.a.i
        void a(k kVar) {
            super.a(kVar);
            kVar.bd(true);
        }
    }
}
