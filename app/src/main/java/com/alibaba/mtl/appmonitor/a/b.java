package com.alibaba.mtl.appmonitor.a;

import org.json.JSONObject;

/* compiled from: CountEvent.java */
/* loaded from: classes11.dex */
public class b extends d {
    public int count;
    public double value;

    public synchronized void a(double d) {
        this.value += d;
        this.count++;
    }

    @Override // com.alibaba.mtl.appmonitor.a.d
    public synchronized JSONObject r() {
        JSONObject r;
        r = super.r();
        try {
            r.put("count", this.count);
            r.put("value", this.value);
        } catch (Exception e) {
        }
        return r;
    }

    @Override // com.alibaba.mtl.appmonitor.a.d, com.alibaba.mtl.appmonitor.c.b
    public synchronized void a(Object... objArr) {
        super.a(objArr);
        this.value = 0.0d;
        this.count = 0;
    }
}
