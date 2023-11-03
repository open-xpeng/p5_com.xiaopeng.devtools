package com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.websocket;

import android.support.annotation.NonNull;
import io.reactivex.Observable;
import okio.ByteString;

/* loaded from: classes12.dex */
public interface IRxWebSocket {
    void close(@NonNull String str);

    IWebSocketConfig config();

    Observable<IWebSocketInfo> get(@NonNull String str);

    Observable<IWebSocketInfo> get(@NonNull String str, long j);

    IRxWebSocket header(@NonNull String str, @NonNull String str2);

    void send(@NonNull String str, @NonNull String str2) throws Exception;

    void send(@NonNull String str, @NonNull ByteString byteString) throws Exception;
}
