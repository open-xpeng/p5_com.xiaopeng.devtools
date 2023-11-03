package com.alibaba.mtl.appmonitor.d;

import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.xiaopeng.lib.apirouter.ClientConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ModuleSampling.java */
/* loaded from: classes11.dex */
class h extends a<JSONObject> {
    protected Map<String, i> bn;
    private String o;

    public h(String str, int i) {
        super(i);
        this.o = str;
        this.bn = new HashMap();
    }

    public boolean a(int i, String str, Map<String, String> map) {
        i iVar;
        if (this.bn != null && (iVar = this.bn.get(str)) != null) {
            return iVar.c(i, map);
        }
        return a(i);
    }

    public void b(JSONObject jSONObject) {
        a((h) jSONObject);
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("monitorPoints");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("monitorPoint");
                    String optString2 = jSONObject2.optString("metric_comment_detail");
                    if (com.alibaba.mtl.appmonitor.f.b.c(optString)) {
                        i iVar = this.bn.get(optString);
                        if (iVar == null) {
                            iVar = new i(optString, this.n);
                            this.bn.put(optString, iVar);
                        }
                        iVar.b(jSONObject2);
                        com.alibaba.mtl.appmonitor.model.b p = com.alibaba.mtl.appmonitor.model.c.Y().p(this.o, optString);
                        if (p != null) {
                            p.p(optString2);
                        }
                        Object opt = jSONObject2.opt("measures");
                        if (opt instanceof JSONArray) {
                            JSONArray jSONArray = (JSONArray) opt;
                            MeasureSet L = MeasureSet.L();
                            int length = jSONArray.length();
                            for (int i2 = 0; i2 < length; i2++) {
                                JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                                if (jSONObject3 != null) {
                                    String optString3 = jSONObject3.optString(ClientConstants.ALIAS.P_NAME);
                                    Double valueOf = Double.valueOf(jSONObject3.optDouble("min"));
                                    Double valueOf2 = Double.valueOf(jSONObject3.optDouble("max"));
                                    if (optString3 != null && valueOf != null && valueOf2 != null) {
                                        L.a(new Measure(optString3, Double.valueOf(0.0d), valueOf, valueOf2));
                                    }
                                }
                            }
                            com.alibaba.mtl.appmonitor.model.b p2 = com.alibaba.mtl.appmonitor.model.c.Y().p("config_prefix" + this.o, "config_prefix" + optString);
                            if (p2 != null) {
                                com.alibaba.mtl.appmonitor.model.c.Y().b(p2);
                            }
                            com.alibaba.mtl.appmonitor.model.c.Y().a(new com.alibaba.mtl.appmonitor.model.a("config_prefix" + this.o, "config_prefix" + optString, L));
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
