package com.lzy.okgo.cache.a;

import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.exception.CacheException;
import com.lzy.okgo.request.base.Request;
import okhttp3.Call;
import okhttp3.Response;

/* compiled from: DefaultCachePolicy.java */
/* loaded from: classes11.dex */
public class c<T> extends a<T> {
    public c(Request<T, ? extends Request> request) {
        super(request);
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onSuccess(final com.lzy.okgo.model.a<T> aVar) {
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.c.1
            @Override // java.lang.Runnable
            public void run() {
                c.this.lB.onSuccess(aVar);
                c.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.b
    public void onError(final com.lzy.okgo.model.a<T> aVar) {
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.c.2
            @Override // java.lang.Runnable
            public void run() {
                c.this.lB.onError(aVar);
                c.this.lB.onFinish();
            }
        });
    }

    @Override // com.lzy.okgo.cache.a.a
    public boolean a(Call call, Response response) {
        if (response.code() != 304) {
            return false;
        }
        if (this.lC == null) {
            final com.lzy.okgo.model.a a = com.lzy.okgo.model.a.a(true, call, response, (Throwable) CacheException.aG(this.request.getCacheKey()));
            runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.c.3
                @Override // java.lang.Runnable
                public void run() {
                    c.this.lB.onError(a);
                    c.this.lB.onFinish();
                }
            });
        } else {
            final com.lzy.okgo.model.a a2 = com.lzy.okgo.model.a.a(true, (Object) this.lC.getData(), call, response);
            runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.c.4
                @Override // java.lang.Runnable
                public void run() {
                    c.this.lB.onCacheSuccess(a2);
                    c.this.lB.onFinish();
                }
            });
        }
        return true;
    }

    @Override // com.lzy.okgo.cache.a.b
    public void a(CacheEntity<T> cacheEntity, com.lzy.okgo.b.b<T> bVar) {
        this.lB = bVar;
        runOnUiThread(new Runnable() { // from class: com.lzy.okgo.cache.a.c.5
            @Override // java.lang.Runnable
            public void run() {
                c.this.lB.onStart(c.this.request);
                try {
                    c.this.cT();
                    c.this.cU();
                } catch (Throwable th) {
                    c.this.lB.onError(com.lzy.okgo.model.a.a(false, c.this.lA, (Response) null, th));
                }
            }
        });
    }
}
