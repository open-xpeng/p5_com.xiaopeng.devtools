package com.lzy.okgo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.e.a;
import com.lzy.okgo.f.b;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import okhttp3.Call;
import okhttp3.OkHttpClient;

/* compiled from: OkGo.java */
/* loaded from: classes11.dex */
public class a {
    public static long lj = 300;
    private Application lk;
    private Handler ll;
    private OkHttpClient lm;
    private HttpParams ln;
    private HttpHeaders lo;
    private CacheMode lp;
    private long lq;
    private int mRetryCount;

    private a() {
        this.ll = new Handler(Looper.getMainLooper());
        this.mRetryCount = 3;
        this.lq = -1L;
        this.lp = CacheMode.NO_CACHE;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("OkGo");
        httpLoggingInterceptor.a(HttpLoggingInterceptor.Level.BODY);
        httpLoggingInterceptor.a(Level.INFO);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.readTimeout(60000L, TimeUnit.MILLISECONDS);
        builder.writeTimeout(60000L, TimeUnit.MILLISECONDS);
        builder.connectTimeout(60000L, TimeUnit.MILLISECONDS);
        a.C0036a da = com.lzy.okgo.e.a.da();
        builder.sslSocketFactory(da.me, da.trustManager);
        builder.hostnameVerifier(com.lzy.okgo.e.a.md);
        this.lm = builder.build();
    }

    public static a cE() {
        return C0035a.lr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: OkGo.java */
    /* renamed from: com.lzy.okgo.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0035a {
        private static a lr = new a();
    }

    public a b(Application application) {
        this.lk = application;
        return this;
    }

    public Context getContext() {
        b.d(this.lk, "please call OkGo.getInstance().init() first in application!");
        return this.lk;
    }

    public Handler cF() {
        return this.ll;
    }

    public OkHttpClient cG() {
        b.d(this.lm, "please call OkGo.getInstance().setOkHttpClient() first in application!");
        return this.lm;
    }

    public a a(OkHttpClient okHttpClient) {
        b.d(okHttpClient, "okHttpClient == null");
        this.lm = okHttpClient;
        return this;
    }

    public a bF(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("retryCount must > 0");
        }
        this.mRetryCount = i;
        return this;
    }

    public int cH() {
        return this.mRetryCount;
    }

    public CacheMode cI() {
        return this.lp;
    }

    public long cJ() {
        return this.lq;
    }

    public HttpParams cK() {
        return this.ln;
    }

    public HttpHeaders cL() {
        return this.lo;
    }

    public void cancelTag(Object obj) {
        if (obj == null) {
            return;
        }
        for (Call call : cG().dispatcher().queuedCalls()) {
            if (obj.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call2 : cG().dispatcher().runningCalls()) {
            if (obj.equals(call2.request().tag())) {
                call2.cancel();
            }
        }
    }
}
