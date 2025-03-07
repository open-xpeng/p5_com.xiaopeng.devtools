package com.alibaba.mtl.appmonitor.d;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AlarmSampling.java */
/* loaded from: classes11.dex */
public class f extends g {
    String TAG;
    private int o;
    private int p;

    @Override // com.alibaba.mtl.appmonitor.d.g
    public /* bridge */ /* synthetic */ boolean a(int i, String str, String str2, Map map) {
        return super.a(i, str, str2, map);
    }

    public f(com.alibaba.mtl.appmonitor.a.f fVar, int i) {
        super(fVar, i);
        this.TAG = "AlarmSampling";
        this.o = 0;
        this.p = 0;
        this.o = i;
        this.p = i;
    }

    public boolean a(int i, String str, String str2, Boolean bool, Map<String, String> map) {
        h hVar;
        String str3 = this.TAG;
        com.alibaba.mtl.log.d.i.a(str3, "samplingSeed:", Integer.valueOf(i), "isSuccess:", bool, "successSampling:", Integer.valueOf(this.o), "failSampling:" + this.p);
        if (this.bm == null || (hVar = this.bm.get(str)) == null || !(hVar instanceof d)) {
            return bool.booleanValue() ? i < this.o : i < this.p;
        }
        return ((d) hVar).a(i, str2, bool, map);
    }

    @Override // com.alibaba.mtl.appmonitor.d.g
    public void b(JSONObject jSONObject) {
        a(jSONObject);
        c(jSONObject);
        this.bm.clear();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("metrics");
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("module");
                    if (com.alibaba.mtl.appmonitor.f.b.c(string)) {
                        h hVar = this.bm.get(string);
                        if (hVar == null) {
                            hVar = new d(string, this.o, this.p);
                            this.bm.put(string, hVar);
                        }
                        hVar.b(jSONObject2);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alibaba.mtl.appmonitor.d.a
    public void a(JSONObject jSONObject) {
        super.a((f) jSONObject);
        this.o = this.n;
        this.p = this.n;
        try {
            Integer valueOf = Integer.valueOf(jSONObject.getInt("successSampling"));
            if (valueOf != null) {
                this.o = valueOf.intValue();
            }
            Integer valueOf2 = Integer.valueOf(jSONObject.getInt("failSampling"));
            if (valueOf2 != null) {
                this.p = valueOf2.intValue();
            }
        } catch (Exception e) {
        }
    }

    @Override // com.alibaba.mtl.appmonitor.d.g
    public void h(int i) {
        super.h(i);
        this.o = i;
        this.p = i;
    }
}
