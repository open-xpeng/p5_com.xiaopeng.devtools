package com.lzy.okgo.b;

import okhttp3.Response;

/* compiled from: StringCallback.java */
/* loaded from: classes11.dex */
public abstract class c extends a<String> {
    private com.lzy.okgo.c.b convert = new com.lzy.okgo.c.b();

    @Override // com.lzy.okgo.c.a
    public String convertResponse(Response response) throws Throwable {
        String convertResponse = this.convert.convertResponse(response);
        response.close();
        return convertResponse;
    }
}
