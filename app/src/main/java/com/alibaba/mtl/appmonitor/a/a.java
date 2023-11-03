package com.alibaba.mtl.appmonitor.a;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AlarmEvent.java */
/* loaded from: classes11.dex */
public class a extends d {
    public int f = 0;
    public int g = 0;

    /* renamed from: g  reason: collision with other field name */
    public Map<String, String> f4g;
    public Map<String, Integer> h;

    public synchronized void e() {
        this.f++;
    }

    public synchronized void f() {
        this.g++;
    }

    public synchronized void a(String str, String str2) {
        if (com.alibaba.mtl.appmonitor.f.b.d(str)) {
            return;
        }
        if (this.f4g == null) {
            this.f4g = new HashMap();
        }
        if (this.h == null) {
            this.h = new HashMap();
        }
        if (com.alibaba.mtl.appmonitor.f.b.c(str2)) {
            int i = 100;
            if (str2.length() <= 100) {
                i = str2.length();
            }
            this.f4g.put(str, str2.substring(0, i));
        }
        if (!this.h.containsKey(str)) {
            this.h.put(str, 1);
        } else {
            this.h.put(str, Integer.valueOf(this.h.get(str).intValue() + 1));
        }
    }

    @Override // com.alibaba.mtl.appmonitor.a.d
    public synchronized JSONObject r() {
        JSONObject r;
        r = super.r();
        try {
            r.put("successCount", this.f);
            r.put("failCount", this.g);
            if (this.h != null) {
                JSONArray jSONArray = (JSONArray) com.alibaba.mtl.appmonitor.c.a.y().a(com.alibaba.mtl.appmonitor.c.d.class, new Object[0]);
                for (Map.Entry<String, Integer> entry : this.h.entrySet()) {
                    JSONObject jSONObject = (JSONObject) com.alibaba.mtl.appmonitor.c.a.y().a(com.alibaba.mtl.appmonitor.c.e.class, new Object[0]);
                    String key = entry.getKey();
                    jSONObject.put("errorCode", key);
                    jSONObject.put("errorCount", entry.getValue());
                    if (this.f4g.containsKey(key)) {
                        jSONObject.put("errorMsg", this.f4g.get(key));
                    }
                    jSONArray.put(jSONObject);
                }
                r.put("errors", jSONArray);
            }
        } catch (Exception e) {
        }
        return r;
    }

    @Override // com.alibaba.mtl.appmonitor.a.d, com.alibaba.mtl.appmonitor.c.b
    public synchronized void clean() {
        super.clean();
        this.f = 0;
        this.g = 0;
        if (this.f4g != null) {
            this.f4g.clear();
        }
        if (this.h != null) {
            this.h.clear();
        }
    }
}
