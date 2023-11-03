package com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.xmart;

import android.support.annotation.Nullable;

/* loaded from: classes12.dex */
public interface IXmartResponse {
    @Nullable
    IServerBean body();

    int code();

    @Nullable
    Throwable getException();

    @Nullable
    String message();
}
