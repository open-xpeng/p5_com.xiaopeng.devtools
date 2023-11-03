package com.lzy.okgo.request;

import com.lzy.okgo.request.base.NoBodyRequest;
import okhttp3.Request;
import okhttp3.RequestBody;

/* loaded from: classes11.dex */
public class GetRequest<T> extends NoBodyRequest<T, GetRequest<T>> {
    public GetRequest(String str) {
        super(str);
    }

    @Override // com.lzy.okgo.request.base.Request
    public Request a(RequestBody requestBody) {
        return b(requestBody).get().url(this.url).tag(this.tag).build();
    }
}
