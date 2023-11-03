package com.amap.api.services.geocoder;

import android.content.Context;
import com.amap.api.services.a.am;
import com.amap.api.services.a.au;
import com.amap.api.services.a.bq;

/* compiled from: GeocodeSearch.java */
/* loaded from: classes11.dex */
public final class b {
    private com.amap.api.services.b.a gR;

    /* compiled from: GeocodeSearch.java */
    /* loaded from: classes11.dex */
    public interface a {
        void a(com.amap.api.services.geocoder.a aVar, int i);

        void a(d dVar, int i);
    }

    public b(Context context) {
        try {
            this.gR = (com.amap.api.services.b.a) am.a(context, bq.l(true), "com.amap.api.services.dynamic.GeocodeSearchWrapper", com.amap.api.services.a.c.class, new Class[]{Context.class}, new Object[]{context});
        } catch (au e) {
            e.printStackTrace();
        }
        if (this.gR == null) {
            this.gR = new com.amap.api.services.a.c(context);
        }
    }

    public void a(a aVar) {
        if (this.gR != null) {
            this.gR.a(aVar);
        }
    }

    public void b(c cVar) {
        if (this.gR != null) {
            this.gR.b(cVar);
        }
    }
}
