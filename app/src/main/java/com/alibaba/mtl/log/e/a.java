package com.alibaba.mtl.log.e;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.j;

/* compiled from: BaseRequestAuth.java */
/* loaded from: classes11.dex */
public class a implements b {
    private boolean E;
    private String ac;
    private String g;

    @Override // com.alibaba.mtl.log.e.b
    public String getAppkey() {
        return this.g;
    }

    public a(String str, String str2, boolean z) {
        this.g = null;
        this.ac = null;
        this.E = false;
        this.g = str;
        this.ac = str2;
        this.E = z;
    }

    public boolean isEncode() {
        return this.E;
    }

    @Override // com.alibaba.mtl.log.e.b
    public String getSign(String str) {
        if (this.g == null || this.ac == null) {
            i.a("BaseRequestAuth", "There is no appkey,please check it!");
            return null;
        } else if (str == null) {
            return null;
        } else {
            return j.a(j.m17a((str + this.ac).getBytes()));
        }
    }
}
