package com.lzy.okgo.b;

import com.lzy.okgo.f.d;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;

/* compiled from: AbsCallback.java */
/* loaded from: classes11.dex */
public abstract class a<T> implements b<T> {
    @Override // com.lzy.okgo.b.b
    public void onStart(Request<T, ? extends Request> request) {
    }

    @Override // com.lzy.okgo.b.b
    public void onCacheSuccess(com.lzy.okgo.model.a<T> aVar) {
    }

    @Override // com.lzy.okgo.b.b
    public void onError(com.lzy.okgo.model.a<T> aVar) {
        d.f(aVar.getException());
    }

    @Override // com.lzy.okgo.b.b
    public void onFinish() {
    }

    @Override // com.lzy.okgo.b.b
    public void uploadProgress(Progress progress) {
    }

    public void downloadProgress(Progress progress) {
    }
}
