package com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.HeadRequest;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.xmart.IServerCallback;
import com.xiaopeng.lib.framework.netchannelmodule.common.GlobalConfig;
import com.xiaopeng.lib.framework.netchannelmodule.common.TrafficStatsEntry;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class HeadRequestAdapter implements IRequest {
    private HeadRequest<String> mInternalRequest;

    public HeadRequestAdapter(String str) {
        this.mInternalRequest = new HeadRequest<>(str);
        if (GlobalConfig.getApplicationContext() != null) {
            headers(GlobalConfig.PACKAGE_IDENTIFIER, GlobalConfig.getApplicationName());
        }
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public void execute(@NonNull Callback callback) {
        this.mInternalRequest.execute(new StringCallbackAdapter(callback));
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest uploadJson(String str) {
        throw new RuntimeException("HEAD请求不支持POST操作!!");
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest uploadFile(String str) {
        throw new RuntimeException("HEAD请求不支持POST操作!!");
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IResponse execute() throws IOException {
        TrafficStatsEntry.setTraficInfo();
        return new ResponseAdapter(this.mInternalRequest.execute());
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public void execute(@NonNull IServerCallback iServerCallback) {
        throw new RuntimeException("Not supported!!!");
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public String getUrl() {
        return this.mInternalRequest.getUrl();
    }

    public HttpHeaders getHeaders() {
        return this.mInternalRequest.getHeaders();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest headers(String str, String str2) {
        this.mInternalRequest.t(str, str2);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest isMultipart(boolean z) {
        throw new RuntimeException("GET请求不支持Multipart设置!!");
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest removeHeader(String str) {
        this.mInternalRequest.aQ(str);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest removeAllHeaders() {
        this.mInternalRequest.dg();
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest tag(Object obj) {
        this.mInternalRequest.h(obj);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest params(Map<String, String> map, boolean... zArr) {
        this.mInternalRequest.b(map, zArr);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest params(String str, String str2) {
        this.mInternalRequest.b(str, str2, new boolean[0]);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest params(String str, int i) {
        this.mInternalRequest.b(str, i, new boolean[0]);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest params(String str, float f) {
        this.mInternalRequest.b(str, f, new boolean[0]);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest params(String str, boolean z) {
        this.mInternalRequest.b(str, z, new boolean[0]);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest params(String str, File file) {
        throw new RuntimeException("GET请求不支持File参数!!");
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest removeParam(String str) {
        this.mInternalRequest.aR(str);
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public IRequest removeAllParams() {
        this.mInternalRequest.dh();
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    public String header(String str) {
        return this.mInternalRequest.getHeaders().get(str);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest
    @Nullable
    public Map<String, List<String>> headers() {
        if (this.mInternalRequest.dl() == null || this.mInternalRequest.dl().headers() == null) {
            return null;
        }
        return this.mInternalRequest.dl().headers().toMultimap();
    }
}
