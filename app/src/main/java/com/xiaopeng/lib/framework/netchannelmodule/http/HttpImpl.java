package com.xiaopeng.lib.framework.netchannelmodule.http;

import android.support.annotation.NonNull;
import com.lzy.okgo.a;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IConfig;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.GetRequestAdapter;
import com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.HeadRequestAdapter;
import com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.PostRequestAdapter;
import com.xiaopeng.lib.framework.netchannelmodule.http.xmart.ServerRequest;

/* loaded from: classes12.dex */
public class HttpImpl implements IHttp {
    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp
    public void cancelTag(Object obj) {
        a.cE().cancelTag(obj);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp
    public IRequest get(@NonNull String str) {
        return new GetRequestAdapter(str);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp
    public IRequest head(@NonNull String str) {
        return new HeadRequestAdapter(str);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp
    public IRequest post(@NonNull String str) {
        return new PostRequestAdapter(str);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp
    public IRequest requestXmartBiz(@NonNull String str) {
        return new ServerRequest(str);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp
    public IConfig config() {
        return new ConfigImpl();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp
    public IBizHelper bizHelper() {
        return new XmartBizHelper();
    }
}
