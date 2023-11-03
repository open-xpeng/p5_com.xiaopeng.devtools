package com.alibaba.mtl.appmonitor.d;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: EventTypeSampling.java */
/* loaded from: classes11.dex */
class g extends a<JSONObject> {
    private com.alibaba.mtl.appmonitor.a.f bl;
    protected Map<String, h> bm;
    protected int q;

    public g(com.alibaba.mtl.appmonitor.a.f fVar, int i) {
        super(i);
        this.q = -1;
        this.bl = fVar;
        this.bm = Collections.synchronizedMap(new HashMap());
    }

    public boolean a(int i, String str, String str2, Map<String, String> map) {
        h hVar;
        if (this.bm != null && (hVar = this.bm.get(str)) != null) {
            return hVar.a(i, str2, map);
        }
        if (i < this.n) {
            return true;
        }
        return false;
    }

    public void b(JSONObject jSONObject) {
        a((g) jSONObject);
        c(jSONObject);
        this.bm.clear();
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("metrics");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("module");
                    if (com.alibaba.mtl.appmonitor.f.b.c(optString)) {
                        h hVar = this.bm.get(optString);
                        if (hVar == null) {
                            hVar = new h(optString, this.n);
                            this.bm.put(optString, hVar);
                        }
                        hVar.b(jSONObject2);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(JSONObject jSONObject) {
        com.alibaba.mtl.log.d.i.a("EventTypeSampling", "[updateEventTypeTriggerCount]", this, jSONObject);
        if (jSONObject == null) {
            return;
        }
        try {
            int optInt = jSONObject.optInt("cacheCount");
            if (optInt > 0 && this.bl != null) {
                this.bl.b(optInt);
            }
        } catch (Throwable th) {
            com.alibaba.mtl.log.d.i.a("EventTypeSampling", "updateTriggerCount", th);
        }
    }

    public void h(int i) {
        this.n = i;
    }
}
