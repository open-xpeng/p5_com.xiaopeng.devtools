package com.alibaba.mtl.appmonitor.a;

import java.util.HashMap;
import java.util.Map;

/* compiled from: UTEvent.java */
/* loaded from: classes11.dex */
public class h implements com.alibaba.mtl.appmonitor.c.b {
    public String aU;
    public Map<String, String> aV;
    public int e;
    public String u;
    public String v;
    public String w;

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void clean() {
        this.u = null;
        this.e = 0;
        this.v = null;
        this.w = null;
        this.aU = null;
        if (this.aV != null) {
            this.aV.clear();
        }
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void a(Object... objArr) {
        if (this.aV == null) {
            this.aV = new HashMap();
        }
    }
}
