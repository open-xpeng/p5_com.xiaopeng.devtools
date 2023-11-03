package com.xiaopeng.lib.framework.moduleinterface.accountmodule;

/* loaded from: classes12.dex */
public interface ICallback<T, K> {
    void onFail(K k);

    void onSuccess(T t);
}
