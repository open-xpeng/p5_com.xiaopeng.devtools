package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f;

import com.xiaopeng.devtools.utils.m;
import com.xiaopeng.lib.utils.j;

/* compiled from: PhyPresenter.java */
/* loaded from: classes12.dex */
public class a {
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.a wY;

    public a(com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.a aVar) {
        this.wY = aVar;
    }

    public void jd() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f.-$$Lambda$a$XX9CKB5cnqG-Jz6AflyCtk37dZU
            @Override // java.lang.Runnable
            public final void run() {
                a.this.ji();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ji() {
        int charAt = m.eG().charAt(5) - '0';
        if (this.wY != null) {
            this.wY.db(charAt);
        }
    }

    public void je() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f.-$$Lambda$a$vQ3ySF4-RXypTwacAgArWXbWxCo
            @Override // java.lang.Runnable
            public final void run() {
                a.this.jh();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void jh() {
        int i;
        String eF = m.eF();
        if ("LINK_DOWN".equalsIgnoreCase(eF)) {
            i = 0;
        } else if ("LINK_UP".equalsIgnoreCase(eF)) {
            i = 1;
        } else {
            i = -1;
        }
        if (this.wY != null) {
            this.wY.cZ(i);
        }
    }

    public void jf() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f.-$$Lambda$a$8nIvItS9AKZsM8YY3cP6vc6Lvlw
            @Override // java.lang.Runnable
            public final void run() {
                a.this.jg();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void jg() {
        int i;
        String lf = m.lf();
        if ("SLPS".equalsIgnoreCase(lf)) {
            i = 0;
        } else if ("SLPM".equalsIgnoreCase(lf)) {
            i = 1;
        } else {
            i = -1;
        }
        if (this.wY != null) {
            this.wY.da(i);
        }
    }

    public void bZ(final String str) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f.-$$Lambda$a$RipAgedD81PaPPxg7KsJQkMGsU0
            @Override // java.lang.Runnable
            public final void run() {
                a.this.ca(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ca(String str) {
        boolean cF = m.cF(str);
        if (this.wY != null) {
            this.wY.ay(cF);
        }
    }
}
