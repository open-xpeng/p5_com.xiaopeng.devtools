package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j;

import com.xiaopeng.lib.utils.j;

/* compiled from: SoundPresenter.java */
/* loaded from: classes12.dex */
public class b implements a {
    private com.xiaopeng.devtools.model.c.a.h.a xm = new com.xiaopeng.devtools.model.c.a.h.b();
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.a xn;

    public b(com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.a aVar) {
        this.xn = aVar;
    }

    public b() {
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j.a
    public int getCurrentMediaVolume() {
        return this.xm.getCurrentMediaVolume();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j.a
    public int getMediaMaxVolume() {
        return this.xm.getMediaMaxVolume();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j.a
    public void ju() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j.b.1
            @Override // java.lang.Runnable
            public void run() {
                String gO = b.this.xm.gO();
                if (b.this.xn != null) {
                    b.this.xn.dk(gO);
                }
            }
        });
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j.a
    public void onVolumeChanged(int i, int i2) {
        this.xm.onVolumeChanged(i, i2);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j.a
    public void cg(int i) {
        this.xm.cg(i);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j.a
    public void destroy(int i) {
        this.xm.destroy(i);
    }
}
