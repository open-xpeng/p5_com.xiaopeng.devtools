package com.lzy.okgo.request.base;

import com.lzy.okgo.f.b;
import com.lzy.okgo.request.base.NoBodyRequest;
import okhttp3.Request;
import okhttp3.RequestBody;

/* loaded from: classes11.dex */
public abstract class NoBodyRequest<T, R extends NoBodyRequest> extends Request<T, R> {
    private static final long serialVersionUID = 1200621102761691196L;

    public NoBodyRequest(String str) {
        super(str);
    }

    @Override // com.lzy.okgo.request.base.Request
    public RequestBody df() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Request.Builder b(RequestBody requestBody) {
        this.url = b.b(this.baseUrl, this.params.urlParamsMap);
        return b.a(new Request.Builder(), this.headers);
    }
}
