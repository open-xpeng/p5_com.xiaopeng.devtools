package com.alibaba.mtl.appmonitor.d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MonitorPointSampling.java */
/* loaded from: classes11.dex */
class i extends a<JSONObject> {
    protected List<c> bo;
    private String p;

    public i(String str, int i) {
        super(i);
        this.p = str;
    }

    public boolean c(int i, Map<String, String> map) {
        if (this.bo != null && map != null) {
            for (c cVar : this.bo) {
                Boolean b = cVar.b(i, map);
                if (b != null) {
                    return b.booleanValue();
                }
            }
        }
        return a(i);
    }

    public void b(JSONObject jSONObject) {
        a((i) jSONObject);
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("extra");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    c cVar = new c(this.n);
                    if (this.bo == null) {
                        this.bo = new ArrayList();
                    }
                    this.bo.add(cVar);
                    cVar.b(jSONObject2);
                }
            }
        } catch (Exception e) {
        }
    }
}
