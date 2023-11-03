package com.xiaopeng.devtools.presenter.f;

/* compiled from: CanTestPresenter.java */
/* loaded from: classes12.dex */
public class a implements c {
    private com.xiaopeng.devtools.model.g.c zm = new com.xiaopeng.devtools.model.g.a(this);
    private com.xiaopeng.devtools.view.smartdrive.a zn;

    public a(com.xiaopeng.devtools.view.smartdrive.a aVar) {
        this.zn = aVar;
    }

    @Override // com.xiaopeng.devtools.presenter.a
    public void onDestroy() {
        this.zm.onDestroy();
    }

    @Override // com.xiaopeng.devtools.presenter.f.c
    public void a(int i, int i2, int i3, String str, int[] iArr) {
        this.zm.a(i, i2, i3, str, iArr);
    }
}
