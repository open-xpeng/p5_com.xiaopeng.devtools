package com.alibaba.mtl.appmonitor.model;

import android.text.TextUtils;
import java.util.List;
import java.util.UUID;

/* compiled from: Metric.java */
/* loaded from: classes11.dex */
public class b implements com.alibaba.mtl.appmonitor.c.b {
    private MeasureSet aj;
    private DimensionSet ak;
    private String bB;
    private boolean g;
    private String o;
    private String p;
    private String r;
    private String s;

    @Deprecated
    public b() {
        this.bB = null;
    }

    public b(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) {
        this.bB = null;
        this.o = str;
        this.p = str2;
        this.ak = dimensionSet;
        this.aj = measureSet;
        this.s = null;
        this.g = z;
    }

    public synchronized String getTransactionId() {
        if (this.r == null) {
            this.r = UUID.randomUUID().toString() + "$" + this.o + "$" + this.p;
        }
        return this.r;
    }

    public void S() {
        this.r = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean b(com.alibaba.mtl.appmonitor.model.DimensionValueSet r6, com.alibaba.mtl.appmonitor.model.MeasureValueSet r7) {
        /*
            r5 = this;
            com.alibaba.mtl.appmonitor.model.DimensionSet r0 = r5.ak
            r1 = 1
            if (r0 == 0) goto Ld
            com.alibaba.mtl.appmonitor.model.DimensionSet r0 = r5.ak
            boolean r6 = r0.b(r6)
            goto Le
        Ld:
            r6 = r1
        Le:
            com.alibaba.mtl.appmonitor.model.c r0 = com.alibaba.mtl.appmonitor.model.c.Y()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "config_prefix"
            r2.append(r3)
            java.lang.String r3 = r5.o
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "config_prefix"
            r3.append(r4)
            java.lang.String r4 = r5.p
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.alibaba.mtl.appmonitor.model.b r0 = r0.p(r2, r3)
            r2 = 0
            if (r0 == 0) goto L92
            com.alibaba.mtl.appmonitor.model.MeasureSet r3 = r0.W()
            if (r3 == 0) goto L92
            if (r7 == 0) goto L92
            java.util.Map r3 = r7.getMap()
            if (r3 == 0) goto L92
            com.alibaba.mtl.appmonitor.model.MeasureSet r3 = r5.aj
            if (r3 == 0) goto L92
            com.alibaba.mtl.appmonitor.model.MeasureSet r0 = r0.W()
            java.util.List r0 = r0.M()
            java.util.Map r1 = r7.getMap()
            java.util.Set r1 = r1.keySet()
            java.util.Iterator r1 = r1.iterator()
        L65:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L91
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            com.alibaba.mtl.appmonitor.model.Measure r4 = r5.a(r3, r0)
            if (r4 != 0) goto L81
            com.alibaba.mtl.appmonitor.model.MeasureSet r4 = r5.aj
            java.util.List r4 = r4.M()
            com.alibaba.mtl.appmonitor.model.Measure r4 = r5.a(r3, r4)
        L81:
            if (r4 == 0) goto L90
            com.alibaba.mtl.appmonitor.model.MeasureValue r3 = r7.o(r3)
            boolean r3 = r4.a(r3)
            if (r3 != 0) goto L8e
            return r2
        L8e:
            goto L65
        L90:
            return r2
        L91:
            return r6
        L92:
            com.alibaba.mtl.appmonitor.model.MeasureSet r0 = r5.aj
            if (r0 == 0) goto La4
            if (r6 == 0) goto La2
            com.alibaba.mtl.appmonitor.model.MeasureSet r6 = r5.aj
            boolean r6 = r6.c(r7)
            if (r6 == 0) goto La2
            r6 = r1
            goto La4
        La2:
            r6 = r2
        La4:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.appmonitor.model.b.b(com.alibaba.mtl.appmonitor.model.DimensionValueSet, com.alibaba.mtl.appmonitor.model.MeasureValueSet):boolean");
    }

    private Measure a(String str, List<Measure> list) {
        if (list != null) {
            for (Measure measure : list) {
                if (TextUtils.equals(str, measure.name)) {
                    return measure;
                }
            }
            return null;
        }
        return null;
    }

    public String T() {
        return this.o;
    }

    public String U() {
        return this.p;
    }

    public DimensionSet V() {
        return this.ak;
    }

    public MeasureSet W() {
        return this.aj;
    }

    public synchronized boolean X() {
        if ("1".equalsIgnoreCase(this.bB)) {
            return true;
        }
        if ("0".equalsIgnoreCase(this.bB)) {
            return false;
        }
        return this.g;
    }

    public int hashCode() {
        return (31 * ((((this.s == null ? 0 : this.s.hashCode()) + 31) * 31) + (this.o == null ? 0 : this.o.hashCode()))) + (this.p != null ? this.p.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.s == null) {
            if (bVar.s != null) {
                return false;
            }
        } else if (!this.s.equals(bVar.s)) {
            return false;
        }
        if (this.o == null) {
            if (bVar.o != null) {
                return false;
            }
        } else if (!this.o.equals(bVar.o)) {
            return false;
        }
        if (this.p == null) {
            if (bVar.p != null) {
                return false;
            }
        } else if (!this.p.equals(bVar.p)) {
            return false;
        }
        return true;
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void clean() {
        this.o = null;
        this.p = null;
        this.s = null;
        this.g = false;
        this.ak = null;
        this.aj = null;
        this.r = null;
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void a(Object... objArr) {
        this.o = (String) objArr[0];
        this.p = (String) objArr[1];
        if (objArr.length > 2) {
            this.s = (String) objArr[2];
        }
    }

    public synchronized void p(String str) {
        this.bB = str;
    }
}
