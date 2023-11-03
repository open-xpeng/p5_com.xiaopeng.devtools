package com.amap.api.services.a;

import android.content.Context;
import java.util.List;

/* compiled from: UpdateLogDBOperation.java */
/* loaded from: classes11.dex */
public class ai {
    private Context b;
    private x eW;

    public ai(Context context) {
        this.b = context;
        this.eW = K(this.b);
    }

    private x K(Context context) {
        try {
            return new x(context, x.b(ae.class));
        } catch (Throwable th) {
            o.a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    public aj bp() {
        try {
            if (this.eW == null) {
                this.eW = K(this.b);
            }
            List b = this.eW.b("1=1", aj.class);
            if (b.size() <= 0) {
                return null;
            }
            return (aj) b.get(0);
        } catch (Throwable th) {
            o.a(th, "UpdateLogDB", "getUpdateLog");
            return null;
        }
    }

    public void a(aj ajVar) {
        if (ajVar == null) {
            return;
        }
        try {
            if (this.eW == null) {
                this.eW = K(this.b);
            }
            List b = this.eW.b("1=1", aj.class);
            if (b != null && b.size() != 0) {
                this.eW.a("1=1", ajVar);
                return;
            }
            this.eW.a((x) ajVar);
        } catch (Throwable th) {
            o.a(th, "UpdateLogDB", "updateLog");
        }
    }
}
