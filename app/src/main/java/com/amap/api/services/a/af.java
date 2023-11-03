package com.amap.api.services.a;

import android.content.Context;
import java.util.List;

/* compiled from: LogDBOperation.java */
/* loaded from: classes11.dex */
public class af {
    private x eW;

    public af(Context context) {
        try {
            this.eW = new x(context, x.b(ae.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, Class<? extends ag> cls) {
        try {
            d(str, cls);
        } catch (Throwable th) {
            o.a(th, "LogDB", "delLog");
        }
    }

    public void c(String str, Class<? extends ag> cls) {
        try {
            d(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void d(String str, Class<? extends ag> cls) {
        this.eW.a(ag.c(str), (Class) cls);
    }

    public List<? extends ag> a(int i, Class<? extends ag> cls) {
        try {
            return this.eW.b(ag.I(i), cls);
        } catch (Throwable th) {
            o.a(th, "LogDB", "ByState");
            return null;
        }
    }

    public void a(ag agVar) {
        if (agVar == null) {
            return;
        }
        String c = ag.c(agVar.b());
        List a = this.eW.a(c, (Class) agVar.getClass(), true);
        if (a == null || a.size() == 0) {
            this.eW.a((x) agVar, true);
            return;
        }
        ag agVar2 = (ag) a.get(0);
        if (agVar.a() == 0) {
            agVar2.b(agVar2.v() + 1);
        } else {
            agVar2.b(0);
        }
        this.eW.a(c, (Object) agVar2, true);
    }

    public void b(ag agVar) {
        try {
            this.eW.a(ag.c(agVar.b()), agVar);
        } catch (Throwable th) {
            o.a(th, "LogDB", "updateLogInfo");
        }
    }
}
