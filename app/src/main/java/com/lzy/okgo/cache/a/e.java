package com.lzy.okgo.cache.a;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.request.base.Request;
import okhttp3.Response;

/* compiled from: NoCachePolicy.java */
/* loaded from: classes11.dex */
public class e<T> extends a<T> {
    public e(Request<T, ? extends Request> request) {
        super(request);
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onSuccess(final com.lzy.okgo.model.a<T> aVar) {
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.e.1
            @Override // java.lang.Runnable
            public void run() {
                e.this.lB.onSuccess(aVar);
                e.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onError(final com.lzy.okgo.model.a<T> aVar) {
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.e.2
            @Override // java.lang.Runnable
            public void run() {
                e.this.lB.onError(aVar);
                e.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.b
    public void a(CacheEntity<T> cacheEntity, com.lzy.okgo.b.b<T> bVar) {
        this.lB = bVar;
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.e.3
            @Override // java.lang.Runnable
            public void run() {
                e.this.lB.onStart(e.this.request);
                try {
                    e.this.cT();
                    e.this.cU();
                } catch (Throwable th) {
                    e.this.lB.onError(com.lzy.okgo.model.a.a(false, e.this.lA, (Response) null, th));
                }
            }
        });
    }
}
