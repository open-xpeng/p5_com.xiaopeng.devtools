package com.lzy.okgo.request;

import com.lzy.okgo.request.base.BodyRequest;
import okhttp3.Request;
import okhttp3.RequestBody;

/* loaded from: classes11.dex */
public class PostRequest<T> extends BodyRequest<T, PostRequest<T>> {
    public PostRequest(String str) {
        super(str);
    }

    @Override // com.lzy.okgo.request.base.Request
    public Request a(RequestBody requestBody) {
        return b(requestBody).post(requestBody).url(this.url).tag(this.tag).build();
    }
}
