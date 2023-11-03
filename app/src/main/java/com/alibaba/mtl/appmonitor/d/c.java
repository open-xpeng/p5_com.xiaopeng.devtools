package com.alibaba.mtl.appmonitor.d;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: AccurateSampling.java */
/* loaded from: classes11.dex */
public class c extends a<JSONObject> {
    private Map<String, b> bk;

    public c(int i) {
        super(i);
        this.bk = new HashMap();
    }

    public void b(JSONObject jSONObject) {
        a((c) jSONObject);
    }

    public Boolean b(int i, Map<String, String> map) {
        if (map == null || this.bk == null) {
            return null;
        }
        for (String str : this.bk.keySet()) {
            if (!this.bk.get(str).b(map.get(str))) {
                return null;
            }
        }
        return Boolean.valueOf(a(i));
    }
}
