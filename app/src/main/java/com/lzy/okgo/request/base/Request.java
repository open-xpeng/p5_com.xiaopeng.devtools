package com.lzy.okgo.request.base;

import android.text.TextUtils;
import com.lzy.okgo.a.b;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.lzy.okgo.request.base.a;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes11.dex */
public abstract class Request<T, R extends Request> implements Serializable {
    private static final long serialVersionUID = -7174118653689916252L;
    protected String baseUrl;
    protected String cacheKey;
    protected CacheMode cacheMode;
    protected long cacheTime;
    protected transient OkHttpClient client;
    protected transient okhttp3.Request mF;
    protected transient b<T> mG;
    protected transient com.lzy.okgo.c.a<T> mH;
    protected transient com.lzy.okgo.cache.a.b<T> mI;
    protected transient a.b mJ;
    protected transient com.lzy.okgo.b.b<T> mz;
    protected int retryCount;
    protected transient Object tag;
    protected String url;
    protected HttpParams params = new HttpParams();
    protected HttpHeaders headers = new HttpHeaders();

    public abstract okhttp3.Request a(RequestBody requestBody);

    protected abstract RequestBody df();

    public Request(String str) {
        this.url = str;
        this.baseUrl = str;
        com.lzy.okgo.a cE = com.lzy.okgo.a.cE();
        String db = HttpHeaders.db();
        if (!TextUtils.isEmpty(db)) {
            t("Accept-Language", db);
        }
        String dc = HttpHeaders.dc();
        if (!TextUtils.isEmpty(dc)) {
            t(com.alibaba.sdk.android.oss.common.utils.HttpHeaders.USER_AGENT, dc);
        }
        if (cE.cK() != null) {
            b(cE.cK());
        }
        if (cE.cL() != null) {
            c(cE.cL());
        }
        this.retryCount = cE.cH();
        this.cacheMode = cE.cI();
        this.cacheTime = cE.cJ();
    }

    public R h(Object obj) {
        this.tag = obj;
        return this;
    }

    public R a(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
        return this;
    }

    public R aP(String str) {
        com.lzy.okgo.f.b.d(str, "cacheKey == null");
        this.cacheKey = str;
        return this;
    }

    public R c(HttpHeaders httpHeaders) {
        this.headers.b(httpHeaders);
        return this;
    }

    public R t(String str, String str2) {
        this.headers.put(str, str2);
        return this;
    }

    public R aQ(String str) {
        this.headers.aI(str);
        return this;
    }

    public R dg() {
        this.headers.clear();
        return this;
    }

    public R b(HttpParams httpParams) {
        this.params.a(httpParams);
        return this;
    }

    public R b(Map<String, String> map, boolean... zArr) {
        this.params.a(map, zArr);
        return this;
    }

    public R b(String str, String str2, boolean... zArr) {
        this.params.a(str, str2, zArr);
        return this;
    }

    public R b(String str, int i, boolean... zArr) {
        this.params.a(str, i, zArr);
        return this;
    }

    public R b(String str, float f, boolean... zArr) {
        this.params.a(str, f, zArr);
        return this;
    }

    public R b(String str, boolean z, boolean... zArr) {
        this.params.a(str, z, zArr);
        return this;
    }

    public R aR(String str) {
        this.params.remove(str);
        return this;
    }

    public R dh() {
        this.params.clear();
        return this;
    }

    public HttpParams di() {
        return this.params;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public String getUrl() {
        return this.url;
    }

    public String dj() {
        return this.baseUrl;
    }

    public CacheMode cI() {
        return this.cacheMode;
    }

    public com.lzy.okgo.cache.a.b<T> dk() {
        return this.mI;
    }

    public String getCacheKey() {
        return this.cacheKey;
    }

    public long cJ() {
        return this.cacheTime;
    }

    public int cH() {
        return this.retryCount;
    }

    public okhttp3.Request dl() {
        return this.mF;
    }

    public com.lzy.okgo.c.a<T> dm() {
        if (this.mH == null) {
            this.mH = this.mz;
        }
        com.lzy.okgo.f.b.d(this.mH, "converter == null, do you forget to call Request#converter(Converter<T>) ?");
        return this.mH;
    }

    public Call de() {
        RequestBody df = df();
        if (df != null) {
            a aVar = new a(df, this.mz);
            aVar.a(this.mJ);
            this.mF = a(aVar);
        } else {
            this.mF = a((RequestBody) null);
        }
        if (this.client == null) {
            this.client = com.lzy.okgo.a.cE().cG();
        }
        return this.client.newCall(this.mF);
    }

    public b<T> dn() {
        if (this.mG == null) {
            return new com.lzy.okgo.a.a(this);
        }
        return this.mG;
    }

    public Response execute() throws IOException {
        return de().execute();
    }

    public void execute(com.lzy.okgo.b.b<T> bVar) {
        com.lzy.okgo.f.b.d(bVar, "callback == null");
        this.mz = bVar;
        dn().execute(bVar);
    }
}
