package com.xiaopeng.devtools.presenter.factorytest;

import com.xiaopeng.devtools.model.c.d;

/* compiled from: LcdDebugPresenter.java */
/* loaded from: classes12.dex */
public class b implements a {
    private com.xiaopeng.devtools.view.factorytest.a wq;
    private d wr = new d(this);

    public b(com.xiaopeng.devtools.view.factorytest.a aVar) {
        this.wq = aVar;
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.a
    public void fJ() {
        this.wr.fJ();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.a
    public void bW(int i) {
        this.wr.bW(i);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.a
    public void cl(int i) {
        if (this.wq != null) {
            this.wq.cl(i);
        }
    }
}
