package com.alibaba.mtl.appmonitor.model;

import com.alibaba.mtl.appmonitor.a.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MetricValueSet.java */
/* loaded from: classes11.dex */
public class d implements com.alibaba.mtl.appmonitor.c.b {
    private Map<b, com.alibaba.mtl.appmonitor.a.d> bE = Collections.synchronizedMap(new HashMap());

    public List<com.alibaba.mtl.appmonitor.a.d> getEvents() {
        return new ArrayList(this.bE.values());
    }

    public com.alibaba.mtl.appmonitor.a.d a(Integer num, String str, String str2, String str3, Class<? extends com.alibaba.mtl.appmonitor.a.d> cls) {
        b bVar;
        boolean z;
        com.alibaba.mtl.appmonitor.a.d dVar;
        if (num.intValue() == f.STAT.a()) {
            bVar = c.Y().p(str, str2);
            z = false;
        } else {
            bVar = (b) com.alibaba.mtl.appmonitor.c.a.y().a(b.class, str, str2, str3);
            z = true;
        }
        com.alibaba.mtl.appmonitor.a.d dVar2 = null;
        if (bVar != null) {
            if (this.bE.containsKey(bVar)) {
                dVar2 = this.bE.get(bVar);
            } else {
                synchronized (d.class) {
                    dVar = (com.alibaba.mtl.appmonitor.a.d) com.alibaba.mtl.appmonitor.c.a.y().a(cls, num, str, str2, str3);
                    this.bE.put(bVar, dVar);
                }
                dVar2 = dVar;
                z = false;
            }
            if (z) {
                com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) bVar);
            }
        }
        return dVar2;
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void clean() {
        for (com.alibaba.mtl.appmonitor.a.d dVar : this.bE.values()) {
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) dVar);
        }
        this.bE.clear();
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void a(Object... objArr) {
        if (this.bE == null) {
            this.bE = Collections.synchronizedMap(new HashMap());
        }
    }
}
