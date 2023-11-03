package com.lzy.okgo.cache.a;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.request.base.Request;
import okhttp3.Response;

/* compiled from: RequestFailedCachePolicy.java */
/* loaded from: classes11.dex */
public class g<T> extends a<T> {
    public g(Request<T, ? extends Request> request) {
        super(request);
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onSuccess(final com.lzy.okgo.model.a<T> aVar) {
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.g.1
            @Override // java.lang.Runnable
            public void run() {
                g.this.lB.onSuccess(aVar);
                g.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onError(final com.lzy.okgo.model.a<T> aVar) {
        if (this.lC != null) {
            final com.lzy.okgo.model.a a = com.lzy.okgo.model.a.a(true, (Object) this.lC.getData(), aVar.de(), aVar.getRawResponse());
            runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.g.2
                @Override // java.lang.Runnable
                public void run() {
                    g.this.lB.onCacheSuccess(a);
                    g.this.lB.onFinish();
                }
            });
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.g.3
            @Override // java.lang.Runnable
            public void run() {
                g.this.lB.onError(aVar);
                g.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.b
    public void a(CacheEntity<T> cacheEntity, com.lzy.okgo.b.b<T> bVar) {
        this.lB = bVar;
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.g.4
            @Override // java.lang.Runnable
            public void run() {
                g.this.lB.onStart(g.this.request);
                try {
                    g.this.cT();
                    g.this.cU();
                } catch (Throwable th) {
                    g.this.lB.onError(com.lzy.okgo.model.a.a(false, g.this.lA, (Response) null, th));
                }
            }
        });
    }
}
