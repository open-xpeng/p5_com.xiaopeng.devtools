package com.alibaba.mtl.log.e;

import com.alibaba.mtl.log.d.i;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/* compiled from: SecurityRequestAuth.java */
/* loaded from: classes11.dex */
public class c implements b {
    private String ad;
    private String g;
    private Object b = null;
    private Object c = null;
    private Class a = null;

    /* renamed from: a  reason: collision with other field name */
    private Field f8a = null;

    /* renamed from: b  reason: collision with other field name */
    private Field f10b = null;

    /* renamed from: c  reason: collision with other field name */
    private Field f11c = null;

    /* renamed from: a  reason: collision with other field name */
    private Method f9a = null;
    private int z = 1;
    private boolean F = false;

    @Override // com.alibaba.mtl.log.e.b
    public String getAppkey() {
        return this.g;
    }

    public c(String str, String str2) {
        this.g = null;
        this.g = str;
        this.ad = str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004b A[Catch: Throwable -> 0x00d0, TRY_ENTER, TRY_LEAVE, TryCatch #5 {, blocks: (B:3:0x0001, B:41:0x00da, B:15:0x0042, B:17:0x004b, B:23:0x0087, B:36:0x00b4, B:18:0x0073, B:26:0x0097), top: B:51:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void D() {
        /*
            Method dump skipped, instructions count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.e.c.D():void");
    }

    @Override // com.alibaba.mtl.log.e.b
    public String getSign(String str) {
        if (!this.F) {
            D();
        }
        if (this.g == null) {
            i.a("SecurityRequestAuth", "There is no appkey,please check it!");
            return null;
        } else if (str == null) {
            return null;
        } else {
            if (this.b != null && this.a != null && this.f8a != null && this.f10b != null && this.f11c != null && this.f9a != null && this.c != null) {
                try {
                    Object newInstance = this.a.newInstance();
                    this.f8a.set(newInstance, this.g);
                    ((Map) this.f10b.get(newInstance)).put("INPUT", str);
                    this.f11c.set(newInstance, Integer.valueOf(this.z));
                    return (String) this.f9a.invoke(this.c, newInstance, this.ad);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
