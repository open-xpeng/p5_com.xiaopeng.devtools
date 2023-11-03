package com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter;

import com.lzy.okgo.b.c;
import com.lzy.okgo.model.a;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;

/* loaded from: classes12.dex */
public class StringCallbackAdapter extends c {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Callback mOuterCallback;

    public StringCallbackAdapter(Callback callback) {
        this.mOuterCallback = callback;
    }

    @Override // com.lzy.okgo.b.a, com.lzy.okgo.b.b
    public void onError(a<String> aVar) {
        super.onError(aVar);
        this.mOuterCallback.onFailure(new ResponseAdapter(aVar));
    }

    @Override // com.lzy.okgo.b.b
    public void onSuccess(a<String> aVar) {
        this.mOuterCallback.onSuccess(new ResponseAdapter(aVar));
    }
}
