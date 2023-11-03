package com.amap.api.services.a;

import android.text.TextUtils;
import java.net.Proxy;
import java.util.Map;

/* compiled from: Request.java */
/* loaded from: classes11.dex */
public abstract class bh {
    int e = 20000;
    int f = 20000;
    Proxy fS = null;

    public abstract Map<String, String> b();

    public abstract Map<String, String> c();

    public abstract String g();

    /* JADX INFO: Access modifiers changed from: package-private */
    public String k() {
        byte[] bk = bk();
        if (bk == null || bk.length == 0) {
            return g();
        }
        Map<String, String> b = b();
        if (b == null) {
            return g();
        }
        String a = be.a(b);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(g());
        stringBuffer.append("?");
        stringBuffer.append(a);
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] bB() {
        byte[] bk = bk();
        if (bk == null || bk.length == 0) {
            String a = be.a(b());
            if (!TextUtils.isEmpty(a)) {
                return m.a(a);
            }
            return bk;
        }
        return bk;
    }

    public final void a(int i) {
        this.e = i;
    }

    public final void b(int i) {
        this.f = i;
    }

    public byte[] bk() {
        return null;
    }

    public final void a(Proxy proxy) {
        this.fS = proxy;
    }
}
