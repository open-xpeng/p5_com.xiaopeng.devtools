package com.lzy.okgo.cache.a;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.request.base.Request;
import okhttp3.Response;

/* compiled from: FirstCacheRequestPolicy.java */
/* loaded from: classes11.dex */
public class d<T> extends a<T> {
    public d(Request<T, ? extends Request> request) {
        super(request);
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onSuccess(final com.lzy.okgo.model.a<T> aVar) {
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.d.1
            @Override // java.lang.Runnable
            public void run() {
                d.this.lB.onSuccess(aVar);
                d.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onError(final com.lzy.okgo.model.a<T> aVar) {
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.d.2
            @Override // java.lang.Runnable
            public void run() {
                d.this.lB.onError(aVar);
                d.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.b
    public void a(final CacheEntity<T> cacheEntity, com.lzy.okgo.b.b<T> bVar) {
        this.lB = bVar;
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.d.3
            @Override // java.lang.Runnable
            public void run() {
                d.this.lB.onStart(d.this.request);
                try {
                    d.this.cT();
                    if (cacheEntity != null) {
                        d.this.lB.onCacheSuccess(com.lzy.okgo.model.a.a(true, cacheEntity.getData(), d.this.lA, (Response) null));
                    }
                    d.this.cU();
                } catch (Throwable th) {
                    d.this.lB.onError(com.lzy.okgo.model.a.a(false, d.this.lA, (Response) null, th));
                }
            }
        });
    }
}
