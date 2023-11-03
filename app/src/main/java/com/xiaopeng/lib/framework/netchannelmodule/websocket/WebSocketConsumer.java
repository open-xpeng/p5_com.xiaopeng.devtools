package com.xiaopeng.lib.framework.netchannelmodule.websocket;

import android.support.annotation.CallSuper;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.WebSocket;
import okio.ByteString;

@Deprecated
/* loaded from: classes12.dex */
abstract class WebSocketConsumer implements Consumer<WebSocketInfo> {
    public abstract void onClosed(@NonNull WebSocket webSocket);

    public abstract void onMessage(@NonNull String str);

    public abstract void onMessage(@NonNull ByteString byteString);

    public abstract void onOpen(@NonNull WebSocket webSocket);

    WebSocketConsumer() {
    }

    @CallSuper
    public void accept(WebSocketInfo webSocketInfo) throws Exception {
        if (webSocketInfo.isOnOpen()) {
            onOpen(webSocketInfo.getWebSocket());
        } else if (webSocketInfo.isClosed()) {
            onClosed(webSocketInfo.getWebSocket());
        } else if (webSocketInfo.stringMessage() != null) {
            onMessage(webSocketInfo.stringMessage());
        } else if (webSocketInfo.byteStringMessage() != null) {
            onMessage(webSocketInfo.byteStringMessage());
        }
    }
}
