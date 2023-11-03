package com.xiaopeng.logictree.handler;

import android.app.Application;

/* compiled from: DidInfo.java */
/* loaded from: classes12.dex */
public class c extends h {
    public c(Application application) {
        super(application);
        this.CLASS_NAME = "DidInfo";
    }

    @Override // com.xiaopeng.logictree.handler.h
    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        super.a(aVar);
        if (a(this.YA, new String[]{"1"})) {
            com.xiaopeng.lib.utils.j.execute(new Runnable() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$c$w3W5lECE4l9Cf5kVKelLus-CzAM
                @Override // java.lang.Runnable
                public final void run() {
                    c.this.qd();
                }
            });
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void qd() {
        Object a = com.xiaopeng.commonfunc.b.b.b.a(Integer.parseInt(this.YA[1]), Integer.parseInt(this.YA[2]), Integer.parseInt(this.YA[3]), this.YA[4]);
        if (a != null) {
            if (a instanceof String) {
                com.xiaopeng.logictree.d.dR((String) a);
                return;
            } else if (a instanceof Integer) {
                com.xiaopeng.logictree.d.a((Integer) a);
                return;
            } else if (a instanceof Long) {
                com.xiaopeng.logictree.d.a((Long) a);
                return;
            } else {
                return;
            }
        }
        com.xiaopeng.logictree.d.pQ();
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        super.destroy();
    }
}
