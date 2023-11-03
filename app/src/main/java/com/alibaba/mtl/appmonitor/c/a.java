package com.alibaba.mtl.appmonitor.c;

import java.util.HashMap;
import java.util.Map;

/* compiled from: BalancedPool.java */
/* loaded from: classes11.dex */
public class a {
    private static a aY = new a();
    private Map<Class<? extends b>, c<? extends b>> aX = new HashMap();

    public static a y() {
        return aY;
    }

    private a() {
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T extends com.alibaba.mtl.appmonitor.c.b> T a(java.lang.Class<T> r2, java.lang.Object... r3) {
        /*
            r1 = this;
            com.alibaba.mtl.appmonitor.c.c r0 = r1.a(r2)
            com.alibaba.mtl.appmonitor.c.b r0 = r0.z()
            if (r0 != 0) goto L15
            java.lang.Object r2 = r2.newInstance()     // Catch: java.lang.Exception -> L11
            com.alibaba.mtl.appmonitor.c.b r2 = (com.alibaba.mtl.appmonitor.c.b) r2     // Catch: java.lang.Exception -> L11
            goto L16
        L11:
            r2 = move-exception
            com.alibaba.mtl.appmonitor.b.b.m16a(r2)
        L15:
            r2 = r0
        L16:
            if (r2 == 0) goto L1b
            r2.a(r3)
        L1b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.appmonitor.c.a.a(java.lang.Class, java.lang.Object[]):com.alibaba.mtl.appmonitor.c.b");
    }

    public <T extends b> void a(T t) {
        if (t == null || (t instanceof e) || (t instanceof d)) {
            return;
        }
        a(t.getClass()).a(t);
    }

    private synchronized <T extends b> c<T> a(Class<T> cls) {
        c<T> cVar;
        cVar = (c<T>) this.aX.get(cls);
        if (cVar == null) {
            cVar = new c<>();
            this.aX.put(cls, cVar);
        }
        return cVar;
    }
}
