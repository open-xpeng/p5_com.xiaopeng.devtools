package com.lzy.okgo.b;

import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;

/* compiled from: Callback.java */
/* loaded from: classes11.dex */
public interface b<T> extends com.lzy.okgo.c.a<T> {
    void onCacheSuccess(com.lzy.okgo.model.a<T> aVar);

    void onError(com.lzy.okgo.model.a<T> aVar);

    void onFinish();

    void onStart(Request<T, ? extends Request> request);

    void onSuccess(com.lzy.okgo.model.a<T> aVar);

    void uploadProgress(Progress progress);
}
