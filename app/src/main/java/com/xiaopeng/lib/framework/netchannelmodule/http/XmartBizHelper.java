package com.xiaopeng.lib.framework.netchannelmodule.http;

import android.support.annotation.NonNull;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.GetRequestAdapter;
import com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.PostRequestAdapter;
import com.xiaopeng.lib.framework.netchannelmodule.http.xmart.bizapi.BizConstants;
import com.xiaopeng.lib.framework.netchannelmodule.http.xmart.bizapi.BizRequestBuilder;
import java.util.Map;

/* loaded from: classes12.dex */
public final class XmartBizHelper implements IBizHelper {
    private BizRequestBuilder mBizRequestBuilder = null;

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public /* bridge */ /* synthetic */ IBizHelper needAuthorizationInfo(@NonNull Map map) {
        return needAuthorizationInfo((Map<String, String>) map);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public XmartBizHelper get(@NonNull String str) {
        this.mBizRequestBuilder = new BizRequestBuilder(new GetRequestAdapter(str), BizConstants.METHOD.GET);
        return this;
    }

    @NonNull
    public XmartBizHelper get(@NonNull String str, @NonNull Map<String, String> map) {
        GetRequestAdapter getRequestAdapter = new GetRequestAdapter(str);
        this.mBizRequestBuilder = new BizRequestBuilder(getRequestAdapter, BizConstants.METHOD.GET);
        getRequestAdapter.params(map, true);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public XmartBizHelper post(@NonNull String str, @NonNull String str2) {
        this.mBizRequestBuilder = new BizRequestBuilder(new PostRequestAdapter(str), BizConstants.METHOD.POST);
        this.mBizRequestBuilder.body(str2);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public XmartBizHelper enableIrdetoEncoding() {
        this.mBizRequestBuilder.enableIrdetoEncoding();
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public IBizHelper enableSecurityEncoding() {
        this.mBizRequestBuilder.enableSecurityEncoding();
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public XmartBizHelper needAuthorizationInfo() {
        this.mBizRequestBuilder.needAuthorizationInfo(null);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public XmartBizHelper needAuthorizationInfo(@NonNull Map<String, String> map) {
        this.mBizRequestBuilder.needAuthorizationInfo(map);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public XmartBizHelper appId(@NonNull String str) {
        this.mBizRequestBuilder.appId(str);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public XmartBizHelper uid(@NonNull String str) {
        this.mBizRequestBuilder.uid(str);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public XmartBizHelper extendBizHeader(@NonNull String str, @NonNull String str2) {
        this.mBizRequestBuilder.setExtHeader(str, str2);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public XmartBizHelper customTokensForAuth(@NonNull String[] strArr) {
        this.mBizRequestBuilder.customTokensForAuth(strArr);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public IRequest build() {
        return this.mBizRequestBuilder.build(null);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IBizHelper
    @NonNull
    public IRequest buildWithSecretKey(@NonNull String str) {
        return this.mBizRequestBuilder.build(str);
    }
}
