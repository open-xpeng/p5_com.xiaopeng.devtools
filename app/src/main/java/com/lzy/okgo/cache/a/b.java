package com.lzy.okgo.cache.a;

import com.lzy.okgo.cache.CacheEntity;

/* compiled from: CachePolicy.java */
/* loaded from: classes11.dex */
public interface b<T> {
    void a(CacheEntity<T> cacheEntity, com.lzy.okgo.b.b<T> bVar);

    CacheEntity<T> cS();

    void onError(com.lzy.okgo.model.a<T> aVar);

    void onSuccess(com.lzy.okgo.model.a<T> aVar);
}
