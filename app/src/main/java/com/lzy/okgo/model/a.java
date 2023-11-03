package com.lzy.okgo.model;

import okhttp3.Call;
import okhttp3.Response;

/* compiled from: Response.java */
/* loaded from: classes11.dex */
public final class a<T> {
    private Call lA;
    private T mv;
    private boolean mw;
    private Response mx;
    private Throwable throwable;

    public static <T> a<T> a(boolean z, T t, Call call, Response response) {
        a<T> aVar = new a<>();
        aVar.x(z);
        aVar.g(t);
        aVar.a(call);
        aVar.a(response);
        return aVar;
    }

    public static <T> a<T> a(boolean z, Call call, Response response, Throwable th) {
        a<T> aVar = new a<>();
        aVar.x(z);
        aVar.a(call);
        aVar.a(response);
        aVar.setException(th);
        return aVar;
    }

    public int code() {
        if (this.mx == null) {
            return -1;
        }
        return this.mx.code();
    }

    public String message() {
        if (this.mx == null) {
            return null;
        }
        return this.mx.message();
    }

    public void g(T t) {
        this.mv = t;
    }

    public T dd() {
        return this.mv;
    }

    public Throwable getException() {
        return this.throwable;
    }

    public void setException(Throwable th) {
        this.throwable = th;
    }

    public Call de() {
        return this.lA;
    }

    public void a(Call call) {
        this.lA = call;
    }

    public Response getRawResponse() {
        return this.mx;
    }

    public void a(Response response) {
        this.mx = response;
    }

    public void x(boolean z) {
        this.mw = z;
    }
}
