package com.xiaopeng.devtools.presenter.f.a;

import java.io.File;
import java.util.Calendar;

/* compiled from: AdasCollectPresenter.java */
/* loaded from: classes12.dex */
public class a implements b {
    public static final String[] nx = {"/sdcard/adas"};
    public static final String zt = nx[0] + File.separator + "adas.txt";
    private com.xiaopeng.devtools.model.g.a.a zu = new com.xiaopeng.devtools.model.g.a.a();

    @Override // com.xiaopeng.devtools.presenter.f.a.b
    public void fw() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n---------start collect adas :");
        sb.append(com.xiaopeng.lib.utils.a.l(Calendar.getInstance().getTimeInMillis()));
        sb.append("--------");
        sb.append("\n");
    }

    @Override // com.xiaopeng.devtools.presenter.f.a.b
    public void kc() {
        this.zu.hF();
    }

    @Override // com.xiaopeng.devtools.presenter.f.a.b
    public void hG() {
        this.zu.hG();
    }
}
