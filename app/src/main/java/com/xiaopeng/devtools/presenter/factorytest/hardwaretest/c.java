package com.xiaopeng.devtools.presenter.factorytest.hardwaretest;

import android.content.Context;
import android.graphics.Bitmap;

/* compiled from: HWAndSWInfoPresenter.java */
/* loaded from: classes12.dex */
public class c implements e {
    private com.xiaopeng.devtools.model.c.a.d wu = new com.xiaopeng.devtools.model.c.a.c(this);
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.a wv;

    public c(com.xiaopeng.devtools.view.factorytest.hardwaretest.a aVar) {
        this.wv = aVar;
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public void init(Context context) {
        this.wu.init(context);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public String fP() {
        return this.wu.fP();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public String fQ() {
        return this.wu.fQ();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public String fR() {
        return this.wu.fR();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public void ca(int i) {
        this.wu.ca(i);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public void cb(int i) {
        this.wu.cb(i);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public void cc(int i) {
        this.wu.cc(i);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public void fS() {
        this.wu.fS();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public String getVehicleId() {
        return this.wu.getVehicleId();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public void b(Bitmap bitmap) {
        if (this.wv != null) {
            this.wv.b(bitmap);
        }
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public void c(Bitmap bitmap) {
        if (this.wv != null) {
            this.wv.c(bitmap);
        }
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public void d(Bitmap bitmap) {
        if (this.wv != null) {
            this.wv.d(bitmap);
        }
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e
    public void onDestroy() {
        this.wv = null;
        this.wu.onDestroy();
    }
}
