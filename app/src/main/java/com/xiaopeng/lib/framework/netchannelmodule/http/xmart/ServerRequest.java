package com.xiaopeng.lib.framework.netchannelmodule.http.xmart;

import android.support.annotation.NonNull;
import com.lzy.okgo.model.a;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.xmart.IServerCallback;
import com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.BasePostRequestAdapter;

/* loaded from: classes12.dex */
public class ServerRequest extends BasePostRequestAdapter<ServerBean> {
    public ServerRequest(String str) {
        super(str);
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.BasePostRequestAdapter, com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public void execute(@NonNull IServerCallback iServerCallback) {
        super.execute(new ServerCallbackImplAdapter(iServerCallback));
    }

    /* loaded from: classes12.dex */
    public class ServerCallbackImplAdapter extends ServerCallbackImpl {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private IServerCallback mOuterCallback;

        public ServerCallbackImplAdapter(IServerCallback iServerCallback) {
            this.mOuterCallback = iServerCallback;
        }

        @Override // com.lzy.okgo.b.a, com.lzy.okgo.b.b
        public void onError(a<ServerBean> aVar) {
            super.onError(aVar);
            this.mOuterCallback.onFailure(new ServerResponse(aVar));
        }

        @Override // com.lzy.okgo.b.b
        public void onSuccess(a<ServerBean> aVar) {
            this.mOuterCallback.onSuccess(new ServerResponse(aVar));
        }
    }
}
