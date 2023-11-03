package com.xiaopeng.lib.framework.netchannelmodule.websocket;

import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket.IWebSocketConfig;
import com.xiaopeng.lib.framework.netchannelmodule.http.ConfigImpl;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;

/* loaded from: classes12.dex */
public final class WebSocketConfig implements IWebSocketConfig {
    private OkHttpClient mClient;
    private SSLSocketFactory mSslSocketFactory;
    private X509TrustManager mTrustManager;
    private boolean mShowLog = false;
    private String mLogTag = "RxWebSocket";
    private long mReconnectInterval = 1000;
    private long mPingInterval = 1000;

    public WebSocketConfig() {
        this.mClient = new OkHttpClient();
        this.mClient = ConfigImpl.getCurrentHttpClient();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket.IWebSocketConfig
    public void apply() {
        RxWebSocketInternal rxWebSocketInternal = RxWebSocketInternal.getInstance();
        rxWebSocketInternal.showLog(this.mShowLog, this.mLogTag);
        rxWebSocketInternal.client(this.mClient);
        rxWebSocketInternal.reconnectInterval(this.mReconnectInterval);
        rxWebSocketInternal.pingInterval(this.mPingInterval);
        if (this.mSslSocketFactory != null && this.mTrustManager != null) {
            rxWebSocketInternal.sslSocketFactory(this.mSslSocketFactory, this.mTrustManager);
        }
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket.IWebSocketConfig
    public WebSocketConfig client(OkHttpClient okHttpClient) {
        this.mClient = okHttpClient;
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket.IWebSocketConfig
    public WebSocketConfig sslSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
        this.mSslSocketFactory = sSLSocketFactory;
        this.mTrustManager = x509TrustManager;
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket.IWebSocketConfig
    public WebSocketConfig reconnectInterval(long j) {
        this.mReconnectInterval = j;
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket.IWebSocketConfig
    public WebSocketConfig pingInterval(long j) {
        this.mPingInterval = j;
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket.IWebSocketConfig
    public WebSocketConfig disableLog() {
        this.mShowLog = false;
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket.IWebSocketConfig
    public WebSocketConfig showLog() {
        this.mShowLog = true;
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket.IWebSocketConfig
    public WebSocketConfig showLog(String str) {
        this.mShowLog = true;
        this.mLogTag = str;
        return this;
    }
}
