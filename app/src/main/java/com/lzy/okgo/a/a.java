package com.lzy.okgo.a;

import com.lzy.okgo.cache.a.c;
import com.lzy.okgo.cache.a.d;
import com.lzy.okgo.cache.a.e;
import com.lzy.okgo.cache.a.f;
import com.lzy.okgo.cache.a.g;
import com.lzy.okgo.request.base.Request;

/* compiled from: CacheCall.java */
/* loaded from: classes11.dex */
public class a<T> implements b<T> {
    private com.lzy.okgo.cache.a.b<T> ls;
    private Request<T, ? extends Request> request;

    public a(Request<T, ? extends Request> request) {
        this.ls = null;
        this.request = request;
        this.ls = cN();
    }

    @Override // com.lzy.okgo.a.b
    public void execute(com.lzy.okgo.b.b<T> bVar) {
        com.lzy.okgo.f.b.d(bVar, "callback == null");
        this.ls.a(this.ls.cS(), bVar);
    }

    private com.lzy.okgo.cache.a.b<T> cN() {
        switch (this.request.cI()) {
            case DEFAULT:
                this.ls = new c(this.request);
                break;
            case NO_CACHE:
                this.ls = new e(this.request);
                break;
            case IF_NONE_CACHE_REQUEST:
                this.ls = new f(this.request);
                break;
            case FIRST_CACHE_THEN_REQUEST:
                this.ls = new d(this.request);
                break;
            case REQUEST_FAILED_READ_CACHE:
                this.ls = new g(this.request);
                break;
        }
        if (this.request.dk() != null) {
            this.ls = this.request.dk();
        }
        com.lzy.okgo.f.b.d(this.ls, "policy == null");
        return this.ls;
    }

    /* renamed from: cO */
    public b<T> clone() {
        return new a(this.request);
    }
}
