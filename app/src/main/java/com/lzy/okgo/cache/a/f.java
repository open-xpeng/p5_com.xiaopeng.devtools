package com.lzy.okgo.cache.a;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.request.base.Request;
import okhttp3.Response;

/* compiled from: NoneCacheRequestPolicy.java */
/* loaded from: classes11.dex */
public class f<T> extends a<T> {
    public f(Request<T, ? extends Request> request) {
        super(request);
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onSuccess(final com.lzy.okgo.model.a<T> aVar) {
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.f.1
            @Override // java.lang.Runnable
            public void run() {
                f.this.lB.onSuccess(aVar);
                f.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onError(final com.lzy.okgo.model.a<T> aVar) {
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.f.2
            @Override // java.lang.Runnable
            public void run() {
                f.this.lB.onError(aVar);
                f.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.b
    public void a(final CacheEntity<T> cacheEntity, com.lzy.okgo.b.b<T> bVar) {
        this.lB = bVar;
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.f.3
            @Override // java.lang.Runnable
            public void run() {
                f.this.lB.onStart(f.this.request);
                try {
                    f.this.cT();
                    if (cacheEntity != null) {
                        f.this.lB.onCacheSuccess(com.lzy.okgo.model.a.a(true, cacheEntity.getData(), f.this.lA, (Response) null));
                        f.this.lB.onFinish();
                        return;
                    }
                    f.this.cU();
                } catch (Throwable th) {
                    f.this.lB.onError(com.lzy.okgo.model.a.a(false, f.this.lA, (Response) null, th));
                }
            }
        });
    }
}
