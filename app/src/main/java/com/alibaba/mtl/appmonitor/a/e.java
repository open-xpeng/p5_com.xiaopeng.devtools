package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.UTDimensionValueSet;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;
import com.alibaba.mtl.log.model.LogField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: EventRepo.java */
/* loaded from: classes11.dex */
public class e {
    private static e aI;
    private AtomicInteger aJ = new AtomicInteger(0);
    private AtomicInteger aK = new AtomicInteger(0);
    private AtomicInteger aL = new AtomicInteger(0);
    private Map<UTDimensionValueSet, com.alibaba.mtl.appmonitor.model.d> aH = new ConcurrentHashMap();
    private Map<String, c> aG = new ConcurrentHashMap();

    public static synchronized e u() {
        e eVar;
        synchronized (e.class) {
            if (aI == null) {
                aI = new e();
            }
            eVar = aI;
        }
        return eVar;
    }

    private e() {
    }

    private UTDimensionValueSet a(int i, Map<String, String> map) {
        UTDimensionValueSet uTDimensionValueSet = (UTDimensionValueSet) com.alibaba.mtl.appmonitor.c.a.y().a(UTDimensionValueSet.class, new Object[0]);
        if (map != null) {
            uTDimensionValueSet.i(map);
        }
        uTDimensionValueSet.o(LogField.ACCESS.toString(), com.alibaba.mtl.log.a.b());
        uTDimensionValueSet.o(LogField.ACCESS_SUBTYPE.toString(), com.alibaba.mtl.log.a.c());
        uTDimensionValueSet.o(LogField.USERID.toString(), com.alibaba.mtl.log.a.d());
        uTDimensionValueSet.o(LogField.USERNICK.toString(), com.alibaba.mtl.log.a.e());
        uTDimensionValueSet.o(LogField.EVENTID.toString(), String.valueOf(i));
        return uTDimensionValueSet;
    }

    public void a(int i, String str, String str2, String str3, Map<String, String> map) {
        UTDimensionValueSet a = a(i, map);
        ((a) a(a, str, str2, str3, a.class)).e();
        if (com.alibaba.mtl.log.a.a.e()) {
            a aVar = (a) com.alibaba.mtl.appmonitor.c.a.y().a(a.class, Integer.valueOf(i), str, str2, str3);
            aVar.e();
            com.alibaba.mtl.appmonitor.f.c.a(a, aVar);
        }
        a(f.r(i), this.aJ);
    }

    public void a(int i, String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        UTDimensionValueSet a = a(i, map);
        a aVar = (a) a(a, str, str2, str3, a.class);
        aVar.f();
        aVar.a(str4, str5);
        if (com.alibaba.mtl.log.a.a.e()) {
            a aVar2 = (a) com.alibaba.mtl.appmonitor.c.a.y().a(a.class, Integer.valueOf(i), str, str2, str3);
            aVar2.f();
            aVar2.a(str4, str5);
            com.alibaba.mtl.appmonitor.f.c.a(a, aVar2);
        }
        a(f.r(i), this.aJ);
    }

    public void a(int i, String str, String str2, String str3, double d, Map<String, String> map) {
        UTDimensionValueSet a = a(i, map);
        ((b) a(a, str, str2, str3, b.class)).a(d);
        if (com.alibaba.mtl.log.a.a.e()) {
            b bVar = (b) com.alibaba.mtl.appmonitor.c.a.y().a(b.class, Integer.valueOf(i), str, str2, str3);
            bVar.a(d);
            com.alibaba.mtl.appmonitor.f.c.a(a, bVar);
        }
        a(f.r(i), this.aK);
    }

    public void a(int i, String str, String str2, MeasureValueSet measureValueSet, DimensionValueSet dimensionValueSet, Map<String, String> map) {
        com.alibaba.mtl.appmonitor.model.b p = com.alibaba.mtl.appmonitor.model.c.Y().p(str, str2);
        if (p != null) {
            if (p.V() != null) {
                p.V().c(dimensionValueSet);
            }
            if (p.W() != null) {
                p.W().d(measureValueSet);
            }
            UTDimensionValueSet a = a(i, map);
            ((g) a(a, str, str2, (String) null, g.class)).a(dimensionValueSet, measureValueSet);
            if (com.alibaba.mtl.log.a.a.e()) {
                g gVar = (g) com.alibaba.mtl.appmonitor.c.a.y().a(g.class, Integer.valueOf(i), str, str2);
                gVar.a(dimensionValueSet, measureValueSet);
                com.alibaba.mtl.appmonitor.f.c.a(a, gVar);
            }
            a(f.r(i), this.aL);
            return;
        }
        i.a("EventRepo", "metric is null");
    }

    public void a(Integer num, String str, String str2, String str3) {
        String a = a(str, str2);
        if (a != null) {
            a(a, num, str, str2, str3);
        }
    }

    public void a(String str, Integer num, String str2, String str3, String str4) {
        c cVar;
        com.alibaba.mtl.appmonitor.model.b p = com.alibaba.mtl.appmonitor.model.c.Y().p(str2, str3);
        if (p != null && p.W() != null && p.W().n(str4) != null) {
            synchronized (c.class) {
                cVar = this.aG.get(str);
                if (cVar == null) {
                    cVar = (c) com.alibaba.mtl.appmonitor.c.a.y().a(c.class, num, str2, str3);
                    this.aG.put(str, cVar);
                }
            }
            cVar.a(str4);
        }
    }

    public void a(String str, String str2, String str3) {
        String a = a(str, str2);
        if (a != null) {
            a(a, str3, true, (Map<String, String>) null);
        }
    }

    public void a(String str, String str2, boolean z, Map<String, String> map) {
        c cVar = this.aG.get(str);
        if (cVar != null && cVar.m14a(str2)) {
            this.aG.remove(str);
            if (z) {
                b(cVar.o, cVar.p);
            }
            a(cVar.e, cVar.o, cVar.p, cVar.s(), cVar.t(), map);
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) cVar);
        }
    }

    public void a(String str, Integer num, String str2, String str3, DimensionValueSet dimensionValueSet) {
        c cVar;
        synchronized (c.class) {
            cVar = this.aG.get(str);
            if (cVar == null) {
                cVar = (c) com.alibaba.mtl.appmonitor.c.a.y().a(c.class, num, str2, str3);
                this.aG.put(str, cVar);
            }
        }
        cVar.a(dimensionValueSet);
    }

    private String a(String str, String str2) {
        com.alibaba.mtl.appmonitor.model.b p = com.alibaba.mtl.appmonitor.model.c.Y().p(str, str2);
        if (p != null) {
            return p.getTransactionId();
        }
        return null;
    }

    private void b(String str, String str2) {
        com.alibaba.mtl.appmonitor.model.b p = com.alibaba.mtl.appmonitor.model.c.Y().p(str, str2);
        if (p != null) {
            p.S();
        }
    }

    private d a(UTDimensionValueSet uTDimensionValueSet, String str, String str2, String str3, Class<? extends d> cls) {
        Integer Z;
        com.alibaba.mtl.appmonitor.model.d dVar;
        if (com.alibaba.mtl.appmonitor.f.b.c(str) && com.alibaba.mtl.appmonitor.f.b.c(str2) && (Z = uTDimensionValueSet.Z()) != null) {
            synchronized (this.aH) {
                dVar = this.aH.get(uTDimensionValueSet);
                if (dVar == null) {
                    dVar = (com.alibaba.mtl.appmonitor.model.d) com.alibaba.mtl.appmonitor.c.a.y().a(com.alibaba.mtl.appmonitor.model.d.class, new Object[0]);
                    this.aH.put(uTDimensionValueSet, dVar);
                }
            }
            return dVar.a(Z, str, str2, str3, cls);
        }
        return null;
    }

    private void a(f fVar, AtomicInteger atomicInteger) {
        int incrementAndGet = atomicInteger.incrementAndGet();
        i.a("EventRepo", fVar.toString(), " EVENT size:", String.valueOf(incrementAndGet));
        if (incrementAndGet >= fVar.b()) {
            i.a("EventRepo", fVar.toString(), " event size exceed trigger count.");
            atomicInteger.set(0);
            a(fVar.a());
        }
    }

    public Map<UTDimensionValueSet, List<d>> t(int i) {
        HashMap hashMap = new HashMap();
        synchronized (this.aH) {
            ArrayList arrayList = new ArrayList(this.aH.keySet());
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                UTDimensionValueSet uTDimensionValueSet = (UTDimensionValueSet) arrayList.get(i2);
                if (uTDimensionValueSet != null && uTDimensionValueSet.Z().intValue() == i) {
                    hashMap.put(uTDimensionValueSet, this.aH.get(uTDimensionValueSet).getEvents());
                    this.aH.remove(uTDimensionValueSet);
                }
            }
        }
        return hashMap;
    }

    public void g() {
        ArrayList arrayList = new ArrayList(this.aG.keySet());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            String str = (String) arrayList.get(i);
            c cVar = this.aG.get(str);
            if (cVar != null && cVar.c()) {
                this.aG.remove(str);
            }
        }
    }

    public void a(int i) {
        final Map<UTDimensionValueSet, List<d>> t = t(i);
        s.aW().b(new Runnable() { // from class: com.alibaba.mtl.appmonitor.a.e.1
            @Override // java.lang.Runnable
            public void run() {
                com.alibaba.mtl.appmonitor.f.c.b(t);
            }
        });
    }
}
