package com.xiaopeng.devtools.presenter.f;

import com.xiaopeng.devtools.bean.smartdrive.SmartDriveData;
import com.xiaopeng.devtools.model.g.b.b;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: SmartDrivePresenter.java */
/* loaded from: classes12.dex */
public class f implements e {
    private com.xiaopeng.devtools.model.g.b.a zq = new com.xiaopeng.devtools.model.g.b.b();
    private com.xiaopeng.devtools.view.smartdrive.smartdrive.a zr;

    public f(com.xiaopeng.devtools.view.smartdrive.smartdrive.a aVar) {
        this.zr = aVar;
    }

    @Override // com.xiaopeng.devtools.presenter.f.e
    public void onCreate() {
        this.zq.onCreate();
    }

    @Override // com.xiaopeng.devtools.presenter.a
    public void onDestroy() {
        this.zq.onDestroy();
        this.zr = null;
    }

    @Override // com.xiaopeng.devtools.presenter.f.e
    public void b(SmartDriveData smartDriveData) {
        this.zq.a(smartDriveData);
    }

    @Override // com.xiaopeng.devtools.presenter.f.e
    public void ka() {
    }

    @Override // com.xiaopeng.devtools.presenter.f.e
    public void kb() {
        this.zq.a(new a(this.zr));
    }

    /* compiled from: SmartDrivePresenter.java */
    /* loaded from: classes12.dex */
    private static class a implements b.a {
        private WeakReference<com.xiaopeng.devtools.view.smartdrive.smartdrive.a> zs;

        public a(com.xiaopeng.devtools.view.smartdrive.smartdrive.a aVar) {
            this.zs = new WeakReference<>(aVar);
        }

        @Override // com.xiaopeng.devtools.model.g.b.b.a
        public void a(int[] iArr) {
            if (this.zs.get() != null) {
                this.zs.get().a(iArr);
            }
        }

        @Override // com.xiaopeng.devtools.model.g.b.b.a
        public void b(int[] iArr) {
            if (this.zs.get() != null) {
                this.zs.get().b(iArr);
            }
        }

        @Override // com.xiaopeng.devtools.model.g.b.b.a
        public void c(int[] iArr) {
            if (this.zs.get() != null) {
                this.zs.get().c(iArr);
            }
        }

        @Override // com.xiaopeng.devtools.model.g.b.b.a
        public void s(List<SmartDriveData> list) {
            if (this.zs.get() != null) {
                this.zs.get().s(list);
            }
        }
    }
}
