package com.xiaopeng.lib.framework.netchannelmodule.http.xmart;

import android.support.annotation.Nullable;
import com.lzy.okgo.model.a;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.xmart.IXmartResponse;

/* loaded from: classes12.dex */
public class ServerResponse implements IXmartResponse {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    a<ServerBean> mInternalResponse;

    public ServerResponse(a<ServerBean> aVar) {
        this.mInternalResponse = aVar;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.xmart.IXmartResponse
    public int code() {
        return this.mInternalResponse.code();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.xmart.IXmartResponse
    @Nullable
    public ServerBean body() {
        return this.mInternalResponse.dd();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.xmart.IXmartResponse
    @Nullable
    public String message() {
        return this.mInternalResponse.message();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.xmart.IXmartResponse
    @Nullable
    public Throwable getException() {
        return this.mInternalResponse.getException();
    }
}
