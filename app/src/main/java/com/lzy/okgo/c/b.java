package com.lzy.okgo.c;

import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: StringConvert.java */
/* loaded from: classes11.dex */
public class b implements a<String> {
    @Override // com.lzy.okgo.c.a
    public String convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        return body.string();
    }
}
