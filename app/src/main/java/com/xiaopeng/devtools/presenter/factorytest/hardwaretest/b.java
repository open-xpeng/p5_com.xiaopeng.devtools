package com.xiaopeng.devtools.presenter.factorytest.hardwaretest;

/* compiled from: FactoryTestPresenter.java */
/* loaded from: classes12.dex */
public class b implements d {
    private com.xiaopeng.devtools.model.c.a.b wt = new com.xiaopeng.devtools.model.c.a.b();

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void a(String str, boolean z, String str2) {
        this.wt.a(str, z, str2);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void iM() {
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void bt(String str) {
        this.wt.bt(str);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void fn() {
        this.wt.fn();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void d(String str, boolean z) {
        this.wt.d(str, z);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public boolean iN() {
        return com.xiaopeng.lib.utils.c.b.pi() > 6;
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void iO() {
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void iP() {
        com.xiaopeng.devtools.model.a.c.fx().setDvTestReq(1);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void iQ() {
        com.xiaopeng.devtools.model.a.c.fx().setDvTestReq(0);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void iR() {
        com.xiaopeng.devtools.model.a.d.fz().setDvTestReq(1);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d
    public void iS() {
        com.xiaopeng.devtools.model.a.d.fz().setDvTestReq(0);
    }
}
