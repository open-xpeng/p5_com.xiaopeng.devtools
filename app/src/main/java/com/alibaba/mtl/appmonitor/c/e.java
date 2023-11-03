package com.alibaba.mtl.appmonitor.c;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ReuseJSONObject.java */
/* loaded from: classes11.dex */
public class e extends JSONObject implements b {
    @Override // com.alibaba.mtl.appmonitor.c.b
    public void clean() {
        Iterator<String> keys = keys();
        if (keys != null) {
            while (keys.hasNext()) {
                try {
                    Object obj = get(keys.next());
                    if (obj != null && (obj instanceof b)) {
                        a.y().a((a) ((b) obj));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void a(Object... objArr) {
    }
}
