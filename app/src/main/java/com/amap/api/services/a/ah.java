package com.amap.api.services.a;

import android.content.Context;
import java.util.List;

/* compiled from: SDKDBOperation.java */
/* loaded from: classes11.dex */
public class ah {
    private Context b;
    private x eW;

    public ah(Context context, boolean z) {
        this.b = context;
        this.eW = c(this.b, z);
    }

    private x c(Context context, boolean z) {
        try {
            return new x(context, x.b(ae.class));
        } catch (Throwable th) {
            if (!z) {
                o.a(th, "SDKDB", "getDB");
            } else {
                th.printStackTrace();
            }
            return null;
        }
    }

    public void a(l lVar) {
        if (lVar == null) {
            return;
        }
        try {
            if (this.eW == null) {
                this.eW = c(this.b, false);
            }
            String a = l.a(lVar.a());
            List b = this.eW.b(a, l.class);
            if (b != null && b.size() != 0) {
                this.eW.a(a, lVar);
                return;
            }
            this.eW.a((x) lVar);
        } catch (Throwable th) {
            o.a(th, "SDKDB", "insert");
            th.printStackTrace();
        }
    }

    public List<l> a() {
        try {
            return this.eW.a(l.e(), l.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
