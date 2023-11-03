package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import java.util.Map;

/* compiled from: BasicHandler.java */
/* loaded from: classes11.dex */
public abstract class a<T, V> extends bh {
    protected T a;
    protected Context ei;
    protected int b = 1;
    protected String c = "";
    private int h = 1;

    protected abstract V a(String str) throws AMapException;

    public a(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.ei = context;
        this.a = t;
        this.b = 1;
        b(com.amap.api.services.core.a.bH().bG());
        a(com.amap.api.services.core.a.bH().bF());
    }

    protected V m(byte[] bArr) throws AMapException {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e) {
            br.a(e, "ProtocalHandler", "loadData");
            str = null;
        }
        if (str == null || str.equals("")) {
            return null;
        }
        br.b(str);
        return a(str);
    }

    public V a() throws AMapException {
        if (this.a != null) {
            return bg();
        }
        return null;
    }

    private V bg() throws AMapException {
        V n;
        V v = null;
        int i = 0;
        while (i < this.b) {
            try {
                try {
                    int protocol = com.amap.api.services.core.a.bH().getProtocol();
                    bg k = bg.k(false);
                    a(k.G(this.ei));
                    n = n(a(protocol, k, this));
                } catch (au e) {
                    e = e;
                } catch (AMapException e2) {
                    e = e2;
                }
                try {
                    i = this.b;
                    v = n;
                } catch (au e3) {
                    e = e3;
                    v = n;
                    i++;
                    if (i < this.b) {
                        try {
                            Thread.sleep(this.h * 1000);
                        } catch (InterruptedException e4) {
                            if ("http连接失败 - ConnectionException".equals(e.getMessage()) || "socket 连接异常 - SocketException".equals(e.getMessage()) || "服务器连接失败 - UnknownServiceException".equals(e.getMessage())) {
                                throw new AMapException("http或socket连接失败 - ConnectionException");
                            }
                            throw new AMapException(e.a());
                        }
                    } else {
                        bh();
                        if ("http连接失败 - ConnectionException".equals(e.getMessage()) || "socket 连接异常 - SocketException".equals(e.getMessage()) || "未知的错误".equals(e.a()) || "服务器连接失败 - UnknownServiceException".equals(e.getMessage())) {
                            throw new AMapException("http或socket连接失败 - ConnectionException");
                        }
                        throw new AMapException(e.a());
                    }
                } catch (AMapException e5) {
                    e = e5;
                    v = n;
                    i++;
                    if (i >= this.b) {
                        throw new AMapException(e.getErrorMessage());
                    }
                }
            } catch (Throwable th) {
                throw new AMapException("未知错误");
            }
        }
        return v;
    }

    protected byte[] a(int i, bg bgVar, bh bhVar) throws au {
        if (i == 1) {
            return bgVar.b(bhVar);
        }
        if (i == 2) {
            return bgVar.a(bhVar);
        }
        return null;
    }

    @Override // com.amap.api.services.a.bh
    public Map<String, String> b() {
        return null;
    }

    @Override // com.amap.api.services.a.bh
    public Map<String, String> c() {
        return null;
    }

    private V n(byte[] bArr) throws AMapException {
        return m(bArr);
    }

    protected V bh() {
        return null;
    }
}
