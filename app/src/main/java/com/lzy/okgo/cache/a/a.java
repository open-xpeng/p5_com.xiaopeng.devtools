package com.lzy.okgo.cache.a;

import android.graphics.Bitmap;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.request.base.Request;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/* compiled from: BaseCachePolicy.java */
/* loaded from: classes11.dex */
public abstract class a<T> implements b<T> {
    protected volatile boolean canceled;
    protected volatile int currentRetryCount = 0;
    protected boolean executed;
    protected Call lA;
    protected com.lzy.okgo.b.b<T> lB;
    protected CacheEntity<T> lC;
    protected Request<T, ? extends Request> request;

    public a(Request<T, ? extends Request> request) {
        this.request = request;
    }

    public boolean a(Call call, Response response) {
        return false;
    }

    @Override // com.lzy.okgo.cache.a.b
    public CacheEntity<T> cS() {
        if (this.request.getCacheKey() == null) {
            this.request.aP(com.lzy.okgo.f.b.b(this.request.dj(), this.request.di().urlParamsMap));
        }
        if (this.request.cI() == null) {
            this.request.a(CacheMode.NO_CACHE);
        }
        CacheMode cI = this.request.cI();
        if (cI != CacheMode.NO_CACHE) {
            this.lC = (CacheEntity<T>) com.lzy.okgo.d.b.cW().aF(this.request.getCacheKey());
            com.lzy.okgo.f.a.a(this.request, this.lC, cI);
            if (this.lC != null && this.lC.a(cI, this.request.cJ(), System.currentTimeMillis())) {
                this.lC.w(true);
            }
        }
        if (this.lC == null || this.lC.cR() || this.lC.getData() == null || this.lC.cP() == null) {
            this.lC = null;
        }
        return this.lC;
    }

    public synchronized Call cT() throws Throwable {
        if (this.executed) {
            throw HttpException.aH("Already executed!");
        }
        this.executed = true;
        this.lA = this.request.de();
        if (this.canceled) {
            this.lA.cancel();
        }
        return this.lA;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cU() {
        this.lA.enqueue(new Callback() { // from class: com.lzy.okgo.cache.a.a.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                if ((iOException instanceof SocketTimeoutException) && a.this.currentRetryCount < a.this.request.cH()) {
                    a.this.currentRetryCount++;
                    a.this.lA = a.this.request.de();
                    if (a.this.canceled) {
                        a.this.lA.cancel();
                    } else {
                        a.this.lA.enqueue(this);
                    }
                } else if (!call.isCanceled()) {
                    a.this.onError(com.lzy.okgo.model.a.a(false, call, (Response) null, (Throwable) iOException));
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if (code == 404 || code >= 500) {
                    a.this.onError(com.lzy.okgo.model.a.a(false, call, response, (Throwable) HttpException.cZ()));
                } else if (a.this.a(call, response)) {
                } else {
                    try {
                        T convertResponse = a.this.request.dm().convertResponse(response);
                        a.this.a(response.headers(), (Headers) convertResponse);
                        a.this.onSuccess(com.lzy.okgo.model.a.a(false, (Object) convertResponse, call, response));
                    } catch (Throwable th) {
                        a.this.onError(com.lzy.okgo.model.a.a(false, call, response, th));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Headers headers, T t) {
        if (this.request.cI() == CacheMode.NO_CACHE || (t instanceof Bitmap)) {
            return;
        }
        CacheEntity<T> a = com.lzy.okgo.f.a.a(headers, t, this.request.cI(), this.request.getCacheKey());
        if (a == null) {
            com.lzy.okgo.d.b.cW().remove(this.request.getCacheKey());
        } else {
            com.lzy.okgo.d.b.cW().a(this.request.getCacheKey(), a);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        com.lzy.okgo.a.cE().cF().post(runnable);
    }
}
