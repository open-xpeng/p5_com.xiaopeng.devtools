package com.xiaopeng.devtools.presenter.g;

import com.xiaopeng.devtools.model.h.a;

/* compiled from: EcuVersionPresenter.java */
/* loaded from: classes12.dex */
public class a implements b {
    private com.xiaopeng.devtools.view.systeminfo.a zv;

    public a(com.xiaopeng.devtools.view.systeminfo.a aVar) {
        this.zv = aVar;
    }

    @Override // com.xiaopeng.devtools.presenter.g.b
    public void b(String[]... strArr) {
        com.xiaopeng.devtools.model.h.a.hH().a(new a.c() { // from class: com.xiaopeng.devtools.presenter.g.a.1
            @Override // com.xiaopeng.devtools.model.h.a.c
            public void onSuccess(String str) {
                if (a.this.zv != null) {
                    a.this.zv.dv(str);
                }
            }
        }, strArr);
    }
}
