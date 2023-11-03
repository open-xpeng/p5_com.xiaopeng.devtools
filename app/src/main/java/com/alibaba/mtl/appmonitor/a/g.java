package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.appmonitor.model.MeasureValue;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.log.d.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: StatEvent.java */
/* loaded from: classes11.dex */
public class g extends d {
    private com.alibaba.mtl.appmonitor.model.b aC;
    private Map<DimensionValueSet, a> values;

    public synchronized void a(DimensionValueSet dimensionValueSet, MeasureValueSet measureValueSet) {
        a aVar;
        boolean z;
        if (dimensionValueSet == null) {
            try {
                DimensionValueSet dimensionValueSet2 = (DimensionValueSet) com.alibaba.mtl.appmonitor.c.a.y().a(DimensionValueSet.class, new Object[0]);
                dimensionValueSet2.d(dimensionValueSet);
                dimensionValueSet = dimensionValueSet2;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!this.values.containsKey(dimensionValueSet)) {
            DimensionValueSet dimensionValueSet3 = (DimensionValueSet) com.alibaba.mtl.appmonitor.c.a.y().a(DimensionValueSet.class, new Object[0]);
            dimensionValueSet3.d(dimensionValueSet);
            a aVar2 = new a();
            this.values.put(dimensionValueSet3, aVar2);
            aVar = aVar2;
        } else {
            aVar = this.values.get(dimensionValueSet);
        }
        if (this.aC != null) {
            z = this.aC.b(dimensionValueSet, measureValueSet);
        } else {
            z = false;
        }
        if (z) {
            aVar.h();
            aVar.a(measureValueSet);
        } else {
            aVar.i();
            if (this.aC.X()) {
                aVar.a(measureValueSet);
            }
        }
        i.a("StatEvent", "entity  count:", Integer.valueOf(aVar.count), " noise:", Integer.valueOf(aVar.l));
    }

    @Override // com.alibaba.mtl.appmonitor.a.d
    public synchronized JSONObject r() {
        JSONObject r;
        Set<String> keySet;
        r = super.r();
        try {
            if (this.aC != null) {
                r.put("isCommitDetail", String.valueOf(this.aC.X()));
            }
            JSONArray jSONArray = (JSONArray) com.alibaba.mtl.appmonitor.c.a.y().a(com.alibaba.mtl.appmonitor.c.d.class, new Object[0]);
            if (this.values != null) {
                for (Map.Entry<DimensionValueSet, a> entry : this.values.entrySet()) {
                    JSONObject jSONObject = (JSONObject) com.alibaba.mtl.appmonitor.c.a.y().a(com.alibaba.mtl.appmonitor.c.e.class, new Object[0]);
                    DimensionValueSet key = entry.getKey();
                    a value = entry.getValue();
                    Object valueOf = Integer.valueOf(value.count);
                    Object valueOf2 = Integer.valueOf(value.l);
                    jSONObject.put("count", valueOf);
                    jSONObject.put("noise", valueOf2);
                    jSONObject.put("dimensions", key != null ? new JSONObject(key.getMap()) : "");
                    List<Map<String, Map<String, Double>>> a2 = value.a();
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i = 0; i < a2.size(); i++) {
                        JSONObject jSONObject2 = new JSONObject();
                        Map<String, Map<String, Double>> map = a2.get(i);
                        if (map != null && (keySet = map.keySet()) != null) {
                            for (String str : keySet) {
                                if (map.get(str) != null) {
                                    jSONObject2.put(str, new JSONObject(map.get(str)));
                                } else {
                                    jSONObject2.put(str, "");
                                }
                            }
                        }
                        jSONArray2.put(jSONObject2);
                    }
                    jSONObject.put("measures", jSONArray2);
                    jSONArray.put(jSONObject);
                }
            }
            r.put("values", jSONArray);
        } catch (Exception e) {
        }
        return r;
    }

    @Override // com.alibaba.mtl.appmonitor.a.d, com.alibaba.mtl.appmonitor.c.b
    public synchronized void clean() {
        super.clean();
        this.aC = null;
        for (DimensionValueSet dimensionValueSet : this.values.keySet()) {
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) dimensionValueSet);
        }
        this.values.clear();
    }

    @Override // com.alibaba.mtl.appmonitor.a.d, com.alibaba.mtl.appmonitor.c.b
    public void a(Object... objArr) {
        super.a(objArr);
        if (this.values == null) {
            this.values = new HashMap();
        }
        this.aC = com.alibaba.mtl.appmonitor.model.c.Y().p(this.o, this.p);
    }

    /* compiled from: StatEvent.java */
    /* loaded from: classes11.dex */
    public class a {
        private int count = 0;
        private int l = 0;
        private List<MeasureValueSet> b = new ArrayList();

        public a() {
        }

        public void a(MeasureValueSet measureValueSet) {
            if (measureValueSet != null) {
                if (g.this.aC != null && g.this.aC.X()) {
                    this.b.add(b(measureValueSet));
                } else if (this.b.isEmpty()) {
                    this.b.add(b(measureValueSet));
                } else {
                    this.b.get(0).e(measureValueSet);
                }
            }
        }

        private MeasureValueSet b(MeasureValueSet measureValueSet) {
            List<Measure> M;
            MeasureValueSet measureValueSet2 = (MeasureValueSet) com.alibaba.mtl.appmonitor.c.a.y().a(MeasureValueSet.class, new Object[0]);
            if (g.this.aC != null && g.this.aC.W() != null && (M = g.this.aC.W().M()) != null) {
                int size = M.size();
                for (int i = 0; i < size; i++) {
                    Measure measure = M.get(i);
                    if (measure != null) {
                        MeasureValue measureValue = (MeasureValue) com.alibaba.mtl.appmonitor.c.a.y().a(MeasureValue.class, new Object[0]);
                        MeasureValue o = measureValueSet.o(measure.getName());
                        if (o.O() != null) {
                            measureValue.b(o.O().doubleValue());
                        }
                        measureValue.c(o.Q());
                        measureValueSet2.a(measure.getName(), measureValue);
                    }
                }
            }
            return measureValueSet2;
        }

        public List<Map<String, Map<String, Double>>> a() {
            Map<String, MeasureValue> map;
            if (this.b == null || this.b.isEmpty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                MeasureValueSet measureValueSet = this.b.get(i);
                if (measureValueSet != null && (map = measureValueSet.getMap()) != null && !map.isEmpty()) {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry<String, MeasureValue> entry : map.entrySet()) {
                        HashMap hashMap2 = new HashMap();
                        String key = entry.getKey();
                        MeasureValue value = entry.getValue();
                        hashMap2.put("value", Double.valueOf(value.Q()));
                        if (value.O() != null) {
                            hashMap2.put("offset", value.O());
                        }
                        hashMap.put(key, hashMap2);
                    }
                    arrayList.add(hashMap);
                }
            }
            return arrayList;
        }

        public void h() {
            this.count++;
        }

        public void i() {
            this.l++;
        }
    }
}
