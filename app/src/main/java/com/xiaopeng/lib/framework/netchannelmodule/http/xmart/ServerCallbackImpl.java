package com.xiaopeng.lib.framework.netchannelmodule.http.xmart;

import com.lzy.okgo.b.a;
import com.lzy.okgo.b.b;
import okhttp3.Response;

/* loaded from: classes12.dex */
public abstract class ServerCallbackImpl extends a<ServerBean> implements b<ServerBean> {
    private ServerConverter convert = new ServerConverter();

    @Override // com.lzy.okgo.c.a
    public ServerBean convertResponse(Response response) throws Throwable {
        ServerBean convertResponse = this.convert.convertResponse(response);
        response.close();
        return convertResponse;
    }
}
