package com.xiaopeng.logictree.handler;

import android.app.Application;

/* compiled from: Security.java */
/* loaded from: classes12.dex */
public class j extends h {
    private final com.xiaopeng.commonfunc.b.f.e YC;
    private final com.xiaopeng.commonfunc.b.d.a YD;
    private int mStep;
    private com.xiaopeng.commonfunc.a.c nZ;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(int i, int i2) {
        if (this.mStep == i) {
            this.mStep = -1;
            if (i2 == 1) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        }
    }

    public j(Application application) {
        super(application);
        this.mStep = -1;
        this.nZ = new com.xiaopeng.commonfunc.a.c() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$j$5HWREDyrVuz-ToHGjSLFuTk6vPU
            @Override // com.xiaopeng.commonfunc.a.c
            public final void onReceiveResult(int i, int i2) {
                j.this.w(i, i2);
            }
        };
        this.CLASS_NAME = "Security";
        this.YC = new com.xiaopeng.commonfunc.b.f.e(this.context, this.nZ);
        this.YD = new com.xiaopeng.commonfunc.b.d.a(this.context, this.nZ);
        this.YD.init();
    }

    @Override // com.xiaopeng.logictree.handler.h
    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        super.a(aVar);
        if (a(this.YA, new String[]{"1"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "verifyCduKey");
            this.mStep = 1;
            this.YC.ei();
        } else if (a(this.YA, new String[]{"2"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "verifyV18CduKey");
            this.mStep = 5;
            this.YC.ej();
        } else if (a(this.YA, new String[]{"3"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "verifyWifiKey");
            this.mStep = 3;
            this.YC.eh();
        } else if (a(this.YA, new String[]{"4"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "verifyPsuKey");
            this.mStep = 2;
            this.YC.ef();
        } else if (a(this.YA, new String[]{"5"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "changeV18CaCert");
            if (this.YC.dY()) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        } else if (a(this.YA, new String[]{"6"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "changeChnCaCert");
            if (this.YC.dZ()) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        } else if (a(this.YA, new String[]{"7"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "checkIndiv");
            this.mStep = 0;
            this.YD.dS();
        } else if (a(this.YA, new String[]{"8"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "asyncInitIndivService");
            this.mStep = 6;
            this.YD.dQ();
            this.YD.dR();
        } else if (a(this.YA, new String[]{"9"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "isTeeInitSuccess");
            if (this.YD.dP()) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        }
        return null;
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        this.YC.onDestroy();
        this.YD.dO();
        super.destroy();
    }
}
