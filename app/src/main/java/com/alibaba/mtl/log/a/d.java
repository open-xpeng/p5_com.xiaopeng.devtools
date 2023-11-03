package com.alibaba.mtl.log.a;

import com.alibaba.mtl.log.d.i;
import com.xiaopeng.lib.apirouter.ClientConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: HostConfigMgr.java */
/* loaded from: classes11.dex */
public class d {
    private static d cb = new d();
    private Map<String, c> cc = Collections.synchronizedMap(new HashMap());
    private String cd;

    public static d ah() {
        return cb;
    }

    public void b(String str) {
        JSONObject jSONObject;
        i.a("HostConfigMgr", "host config:" + str);
        if (str != null) {
            try {
                JSONObject jSONObject2 = new JSONObject(str);
                JSONObject jSONObject3 = jSONObject2.getJSONObject(ClientConstants.BINDER.SCHEME);
                if (jSONObject3 != null && (jSONObject = jSONObject3.getJSONObject("hosts")) != null) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (next != null) {
                            c cVar = new c();
                            JSONObject jSONObject4 = jSONObject.getJSONObject(next);
                            if (jSONObject4 != null) {
                                cVar.V = next.substring(1);
                                cVar.U = jSONObject4.getString("host");
                                JSONArray jSONArray = jSONObject4.getJSONArray("eids");
                                if (jSONArray != null) {
                                    cVar.a = new ArrayList<>();
                                    for (int i = 0; i < jSONArray.length(); i++) {
                                        cVar.a.add(jSONArray.getString(i));
                                    }
                                }
                            }
                            this.cc.put(cVar.V + "", cVar);
                        }
                    }
                }
                this.cd = jSONObject2.getString("timestamp");
            } catch (Throwable th) {
            }
        }
    }

    public Map<String, c> b() {
        return this.cc;
    }
}
