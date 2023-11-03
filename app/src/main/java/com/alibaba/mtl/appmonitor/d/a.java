package com.alibaba.mtl.appmonitor.d;

import org.json.JSONObject;

/* compiled from: AbstractSampling.java */
/* loaded from: classes11.dex */
public abstract class a<T extends JSONObject> {
    protected int n;

    public a(int i) {
        this.n = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(T t) {
        try {
            Integer valueOf = Integer.valueOf(t.getInt("sampling"));
            if (valueOf != null) {
                this.n = valueOf.intValue();
            }
        } catch (Exception e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(int i) {
        return i < this.n;
    }
}
