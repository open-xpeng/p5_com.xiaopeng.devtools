package com.alibaba.sdk.android.man.network;

import com.alibaba.sdk.android.man.util.MANConfig;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class MANNetworkErrorCodeBuilder {
    private static final int CLIENT_ERROR_4XX = 2001;
    private static final int INTERRUPTED_IO_EXCEPTION = 2004;
    private static final int IO_EXCEPTION = 2006;
    private static final int MALFORMED_URL_EXCEPTION = 2003;
    private static final int SERVER_ERROR_5XX = 2002;
    private static final int SOCKET_TIMEOUT_EXCEPTION = 2005;

    private MANNetworkErrorCodeBuilder() {
    }

    public static MANNetworkErrorInfo buildHttpCodeClientError4XX() {
        return buildErrorCode(2001);
    }

    public static MANNetworkErrorInfo buildHttpCodeServerError5XX() {
        return buildErrorCode(2002);
    }

    public static MANNetworkErrorInfo buildMalformedURLException() {
        return buildErrorCode(MALFORMED_URL_EXCEPTION);
    }

    public static MANNetworkErrorInfo buildInterruptedIOException() {
        return buildErrorCode(INTERRUPTED_IO_EXCEPTION);
    }

    public static MANNetworkErrorInfo buildSocketTimeoutException() {
        return buildErrorCode(SOCKET_TIMEOUT_EXCEPTION);
    }

    public static MANNetworkErrorInfo buildIOException() {
        return buildErrorCode(IO_EXCEPTION);
    }

    public static MANNetworkErrorInfo buildCustomErrorCode(int i) {
        return buildErrorCode(i);
    }

    private static MANNetworkErrorInfo buildErrorCode(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(MANConfig.NETWORK_SINGLE_REQUEST_ERROR_MSG, String.valueOf(i));
        return new MANNetworkErrorInfo(hashMap);
    }
}
