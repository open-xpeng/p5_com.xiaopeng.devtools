package com.alibaba.sdk.android.man.network;

import com.alibaba.sdk.android.man.util.MANConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class MANNetworkPerformanceHitBuilder {
    private static final String TAG = "MAN_MANNetworkPerformanceHitBuilder";
    private String requestHost;
    private String requestMethod;
    private NetworkEvent networkEvent = new NetworkEvent();
    private Map<String, String> requestProperties = new HashMap();

    private MANNetworkPerformanceHitBuilder() {
    }

    public MANNetworkPerformanceHitBuilder(String str, String str2) {
        this.requestHost = str;
        if (str2 != null && (str2.equalsIgnoreCase("GET") || str2.equalsIgnoreCase("POST"))) {
            this.requestMethod = str2.toUpperCase();
        } else {
            this.requestMethod = "GET";
        }
    }

    public MANNetworkPerformanceHitBuilder hitRequestStart() {
        this.networkEvent.requestStart();
        return this;
    }

    public MANNetworkPerformanceHitBuilder hitConnectFinished() {
        this.networkEvent.connectionEnd();
        return this;
    }

    public MANNetworkPerformanceHitBuilder hitRecievedFirstByte() {
        this.networkEvent.firstByteEnd();
        return this;
    }

    public MANNetworkPerformanceHitBuilder hitRequestEndWithLoadBytes(long j) {
        this.networkEvent.requestEndNormally(j);
        return this;
    }

    public MANNetworkPerformanceHitBuilder withExtraInfo(String str, String str2) {
        if (str != null && str2 != null) {
            this.requestProperties.put(str, str2);
        }
        return this;
    }

    public MANNetworkPerformanceHitBuilder hitRequestEndWithError(MANNetworkErrorInfo mANNetworkErrorInfo) {
        this.networkEvent.requestEndWithError(mANNetworkErrorInfo.getProperties());
        return this;
    }

    public NetworkEvent build() {
        this.requestProperties.put("Host", this.requestHost);
        this.requestProperties.put(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY, this.requestMethod);
        this.networkEvent.addMANEventProperty(this.requestProperties);
        return this.networkEvent;
    }
}
