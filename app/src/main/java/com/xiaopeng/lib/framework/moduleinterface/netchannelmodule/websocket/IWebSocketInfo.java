package com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket;

import android.support.annotation.Nullable;
import okio.ByteString;

/* loaded from: classes12.dex */
public interface IWebSocketInfo {
    public static final int CODE_NORMAL = 0;

    /* loaded from: classes12.dex */
    public enum STATE {
        OPEN,
        CLOSED
    }

    @Nullable
    ByteString byteStringMessage();

    String closedReason();

    int closedReasonCode();

    boolean isClosed();

    boolean isOnOpen();

    boolean isOnReconnect();

    @Nullable
    String stringMessage();
}
