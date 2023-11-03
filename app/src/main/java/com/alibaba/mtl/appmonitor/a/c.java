package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.appmonitor.model.MeasureValue;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.log.d.i;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: DurationEvent.java */
/* loaded from: classes11.dex */
public class c extends d {
    private static final Long aB = 300000L;
    private com.alibaba.mtl.appmonitor.model.b aC;
    private MeasureValueSet aD;
    private Map<String, MeasureValue> aE;
    private Long aF;
    private DimensionValueSet az;

    public boolean c() {
        long currentTimeMillis = System.currentTimeMillis();
        List<Measure> M = this.aC.W().M();
        if (M != null) {
            int size = M.size();
            for (int i = 0; i < size; i++) {
                Measure measure = M.get(i);
                if (measure != null) {
                    double doubleValue = measure.H() != null ? measure.H().doubleValue() : aB.longValue();
                    MeasureValue measureValue = this.aE.get(measure.getName());
                    if (measureValue != null && !measureValue.P() && currentTimeMillis - measureValue.Q() > doubleValue) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.aE.isEmpty()) {
            this.aF = Long.valueOf(currentTimeMillis);
        }
        this.aE.put(str, (MeasureValue) com.alibaba.mtl.appmonitor.c.a.y().a(MeasureValue.class, Double.valueOf(currentTimeMillis), Double.valueOf(currentTimeMillis - this.aF.longValue())));
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m14a(String str) {
        MeasureValue measureValue = this.aE.get(str);
        if (measureValue != null) {
            double currentTimeMillis = System.currentTimeMillis();
            i.a("DurationEvent", "statEvent consumeTime. module:", this.o, " monitorPoint:", this.p, " measureName:", str, " time:", Double.valueOf(currentTimeMillis - measureValue.Q()));
            measureValue.c(currentTimeMillis - measureValue.Q());
            measureValue.g(true);
            this.aD.a(str, measureValue);
            if (this.aC.W().c(this.aD)) {
                return true;
            }
        }
        return false;
    }

    public void a(DimensionValueSet dimensionValueSet) {
        if (this.az == null) {
            this.az = dimensionValueSet;
        } else {
            this.az.d(dimensionValueSet);
        }
    }

    public MeasureValueSet s() {
        return this.aD;
    }

    public DimensionValueSet t() {
        return this.az;
    }

    @Override // com.alibaba.mtl.appmonitor.a.d, com.alibaba.mtl.appmonitor.c.b
    public void clean() {
        super.clean();
        this.aC = null;
        this.aF = null;
        for (MeasureValue measureValue : this.aE.values()) {
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) measureValue);
        }
        this.aE.clear();
        if (this.aD != null) {
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) this.aD);
            this.aD = null;
        }
        if (this.az != null) {
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) this.az);
            this.az = null;
        }
    }

    @Override // com.alibaba.mtl.appmonitor.a.d, com.alibaba.mtl.appmonitor.c.b
    public void a(Object... objArr) {
        super.a(objArr);
        if (this.aE == null) {
            this.aE = new HashMap();
        }
        this.aC = com.alibaba.mtl.appmonitor.model.c.Y().p(this.o, this.p);
        if (this.aC.V() != null) {
            this.az = (DimensionValueSet) com.alibaba.mtl.appmonitor.c.a.y().a(DimensionValueSet.class, new Object[0]);
            this.aC.V().c(this.az);
        }
        this.aD = (MeasureValueSet) com.alibaba.mtl.appmonitor.c.a.y().a(MeasureValueSet.class, new Object[0]);
    }
}
