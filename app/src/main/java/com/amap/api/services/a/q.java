package com.amap.api.services.a;

import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

/* compiled from: LogUpdateRequest.java */
/* loaded from: classes11.dex */
public class q extends bh {
    private byte[] a;
    private String b;

    public q(byte[] bArr) {
        this.b = "1";
        this.a = (byte[]) bArr.clone();
    }

    public q(byte[] bArr, String str) {
        this.b = "1";
        this.a = (byte[]) bArr.clone();
        this.b = str;
    }

    private String a() {
        byte[] a = m.a(n.a);
        byte[] bArr = new byte[a.length + 50];
        System.arraycopy(this.a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return j.a(bArr);
    }

    @Override // com.amap.api.services.a.bh
    public Map<String, String> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/zip");
        hashMap.put(HttpHeaders.CONTENT_LENGTH, String.valueOf(this.a.length));
        return hashMap;
    }

    @Override // com.amap.api.services.a.bh
    public Map<String, String> b() {
        return null;
    }

    @Override // com.amap.api.services.a.bh
    public String g() {
        return String.format(n.b, "1", this.b, "1", "open", a());
    }

    @Override // com.amap.api.services.a.bh
    public byte[] bk() {
        return this.a;
    }
}
