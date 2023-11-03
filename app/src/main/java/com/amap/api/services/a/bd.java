package com.amap.api.services.a;

import java.net.Proxy;

/* compiled from: DownloadManager.java */
/* loaded from: classes11.dex */
public class bd {
    private be fG;
    private bh fH;

    /* compiled from: DownloadManager.java */
    /* loaded from: classes11.dex */
    public interface a {
        void a(Throwable th);

        void a(byte[] bArr, long j);

        void b();

        void c();
    }

    public bd(bh bhVar) {
        this(bhVar, 0L, -1L);
    }

    public bd(bh bhVar, long j, long j2) {
        Proxy proxy;
        this.fH = bhVar;
        if (bhVar.fS == null) {
            proxy = null;
        } else {
            proxy = bhVar.fS;
        }
        this.fG = new be(this.fH.e, this.fH.f, proxy);
        this.fG.b(j2);
        this.fG.a(j);
    }

    public void a(a aVar) {
        this.fG.a(this.fH.g(), this.fH.c(), this.fH.b(), aVar);
    }
}
