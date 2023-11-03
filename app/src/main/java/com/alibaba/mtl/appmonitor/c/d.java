package com.alibaba.mtl.appmonitor.c;

import org.json.JSONArray;

/* compiled from: ReuseJSONArray.java */
/* loaded from: classes11.dex */
public class d extends JSONArray implements b {
    @Override // com.alibaba.mtl.appmonitor.c.b
    public void clean() {
        for (int i = 0; i < length(); i++) {
            Object opt = opt(i);
            if (opt != null && (opt instanceof b)) {
                a.y().a((a) ((b) opt));
            }
        }
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void a(Object... objArr) {
    }
}
