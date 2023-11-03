package com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okio.ByteString;

/* loaded from: classes12.dex */
public abstract class WebSocketSubscriber implements Observer<IWebSocketInfo> {
    private Disposable disposable;
    private boolean hasOpened;

    public final void onNext(@NonNull IWebSocketInfo iWebSocketInfo) {
        if (iWebSocketInfo.isOnOpen()) {
            this.hasOpened = true;
            onOpen();
        } else if (iWebSocketInfo.isClosed()) {
            this.hasOpened = false;
            onClosed(iWebSocketInfo.closedReasonCode(), iWebSocketInfo.closedReason());
        } else if (iWebSocketInfo.stringMessage() != null) {
            onMessage(iWebSocketInfo.stringMessage());
        } else if (iWebSocketInfo.byteStringMessage() != null) {
            onMessage(iWebSocketInfo.byteStringMessage());
        } else if (iWebSocketInfo.isOnReconnect()) {
            onReconnect();
        }
    }

    protected void onOpen() {
    }

    protected void onClosed(int i, String str) {
    }

    protected void onMessage(@NonNull String str) {
    }

    protected void onMessage(@NonNull ByteString byteString) {
    }

    protected void onReconnect() {
    }

    protected void onClose() {
    }

    public final void onSubscribe(Disposable disposable) {
        this.disposable = disposable;
    }

    public final void dispose() {
        if (this.disposable != null) {
            this.disposable.dispose();
        }
    }

    public final void onComplete() {
        if (this.hasOpened) {
            onClose();
        }
    }

    public void onError(Throwable th) {
        th.printStackTrace();
    }
}
