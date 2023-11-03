package com.amap.api.services.a;

import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: BaseNetManager.java */
/* loaded from: classes11.dex */
public class bb {
    private static bb fF;

    /* compiled from: BaseNetManager.java */
    /* loaded from: classes11.dex */
    public interface a {
        URLConnection a(Proxy proxy, URL url);
    }

    public static bb bw() {
        if (fF == null) {
            fF = new bb();
        }
        return fF;
    }

    public byte[] a(bh bhVar) throws au {
        try {
            bj a2 = a(bhVar, true);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (au e) {
            throw e;
        } catch (Throwable th) {
            throw new au("未知的错误");
        }
    }

    public byte[] b(bh bhVar) throws au {
        try {
            bj a2 = a(bhVar, false);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (au e) {
            throw e;
        } catch (Throwable th) {
            o.a(th, "BaseNetManager", "makeSyncPostRequest");
            throw new au("未知的错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(bh bhVar) throws au {
        if (bhVar == null) {
            throw new au("requeust is null");
        }
        if (bhVar.g() == null || "".equals(bhVar.g())) {
            throw new au("request url is empty");
        }
    }

    public bj a(bh bhVar, boolean z) throws au {
        Proxy proxy;
        try {
            c(bhVar);
            if (bhVar.fS == null) {
                proxy = null;
            } else {
                proxy = bhVar.fS;
            }
            return new be(bhVar.e, bhVar.f, proxy, z).a(bhVar.k(), bhVar.c(), bhVar.bB());
        } catch (au e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new au("未知的错误");
        }
    }
}
