package com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http;

import android.support.annotation.Nullable;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import okhttp3.Response;

/* loaded from: classes12.dex */
public interface IResponse {
    @Nullable
    String body();

    @Nullable
    InputStream byteStream();

    int code();

    @Nullable
    Throwable getException();

    @Nullable
    Response getRawResponse();

    @Nullable
    String header(String str);

    @Nullable
    Map<String, List<String>> headers();

    @Nullable
    String message();
}
