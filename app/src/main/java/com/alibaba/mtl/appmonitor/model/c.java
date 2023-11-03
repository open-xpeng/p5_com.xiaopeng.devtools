package com.alibaba.mtl.appmonitor.model;

import java.util.ArrayList;
import java.util.List;

/* compiled from: MetricRepo.java */
/* loaded from: classes11.dex */
public class c {
    private static c bD;
    public List<b> bC;

    public static c Y() {
        if (bD == null) {
            bD = new c(3);
        }
        return bD;
    }

    private c(int i) {
        this.bC = new ArrayList(i);
    }

    public void a(b bVar) {
        if (!this.bC.contains(bVar)) {
            this.bC.add(bVar);
        }
    }

    public boolean b(b bVar) {
        if (this.bC.contains(bVar)) {
            return this.bC.remove(bVar);
        }
        return true;
    }

    public b p(String str, String str2) {
        if (str == null || str2 == null || this.bC == null) {
            return null;
        }
        int size = this.bC.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.bC.get(i);
            if (bVar != null && bVar.T().equals(str) && bVar.U().equals(str2)) {
                return bVar;
            }
        }
        return null;
    }
}
