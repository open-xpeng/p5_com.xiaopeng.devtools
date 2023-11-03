package com.xiaopeng.devtools.presenter.aftersales;

import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;

/* compiled from: DiagnosisCodePresenter.java */
/* loaded from: classes12.dex */
public class c {
    private com.xiaopeng.devtools.view.aftersales.cdu.a vr;
    private com.xiaopeng.commonfunc.b.b.a vs;
    private int vt;

    public c(com.xiaopeng.devtools.view.aftersales.cdu.a aVar, int i) {
        this.vt = 0;
        if (i > 0) {
            this.vt = i;
        }
        this.vr = aVar;
        this.vs = new com.xiaopeng.commonfunc.b.b.a(MyApplication.getContext(), R.array.diagnosis_module_name);
        this.vs.dG();
    }

    public void dJ() {
        this.vs.dJ();
        this.vs.dK();
        this.vr.v(this.vs.dH());
        this.vr.a(this.vs.dH().get(this.vt));
    }

    public String[] dI() {
        return this.vs.dI();
    }

    /* renamed from: if  reason: not valid java name */
    public String m98if() {
        return this.vs.bG(this.vt);
    }

    public void ch(int i) {
        this.vt = i;
        if (this.vs.dH() != null) {
            this.vr.a(this.vs.dH().get(this.vt));
        }
    }

    public void onDestroy() {
        this.vs.onDestroy();
    }
}
