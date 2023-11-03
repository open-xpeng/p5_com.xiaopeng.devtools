package com.ut.mini.core.sign;

import com.alibaba.mtl.log.d.i;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes11.dex */
public class UTSecuritySDKRequestAuthentication implements IUTRequestAuthentication {
    private String ad;
    private String g;
    private Object b = null;
    private Object c = null;
    private Class a = null;

    /* renamed from: a  reason: collision with other field name */
    private Field f145a = null;

    /* renamed from: b  reason: collision with other field name */
    private Field f147b = null;

    /* renamed from: c  reason: collision with other field name */
    private Field f148c = null;

    /* renamed from: a  reason: collision with other field name */
    private Method f146a = null;
    private int z = 1;
    private boolean F = false;

    @Override // com.ut.mini.core.sign.IUTRequestAuthentication
    public String getAppkey() {
        return this.g;
    }

    public UTSecuritySDKRequestAuthentication(String str, String str2) {
        this.g = null;
        this.g = str;
        this.ad = str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0051 A[Catch: Throwable -> 0x00da, TRY_ENTER, TRY_LEAVE, TryCatch #4 {, blocks: (B:3:0x0001, B:41:0x00e6, B:15:0x0046, B:17:0x0051, B:23:0x008f, B:36:0x00be, B:18:0x0079, B:26:0x009f), top: B:51:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void D() {
        /*
            Method dump skipped, instructions count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication.D():void");
    }

    @Override // com.ut.mini.core.sign.IUTRequestAuthentication
    public String getSign(String str) {
        if (!this.F) {
            D();
        }
        if (this.g == null) {
            i.a("UTSecuritySDKRequestAuthentication:getSign", "There is no appkey,please check it!");
            return null;
        } else if (str == null) {
            return null;
        } else {
            if (this.b != null && this.a != null && this.f145a != null && this.f147b != null && this.f148c != null && this.f146a != null && this.c != null) {
                try {
                    Object newInstance = this.a.newInstance();
                    this.f145a.set(newInstance, this.g);
                    ((Map) this.f147b.get(newInstance)).put("INPUT", str);
                    this.f148c.set(newInstance, Integer.valueOf(this.z));
                    return (String) this.f146a.invoke(this.c, newInstance, this.ad);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                } catch (InstantiationException e3) {
                    e3.printStackTrace();
                } catch (InvocationTargetException e4) {
                    e4.printStackTrace();
                }
            }
            return null;
        }
    }

    public String getAuthCode() {
        return this.ad;
    }
}
