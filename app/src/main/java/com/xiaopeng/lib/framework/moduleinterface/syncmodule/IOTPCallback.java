package com.xiaopeng.lib.framework.moduleinterface.syncmodule;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: classes12.dex */
public interface IOTPCallback {
    void onError(@NonNull String str, @Nullable Integer num, @Nullable String str2);

    void onGetOTP(@NonNull String str, @NonNull String str2, @NonNull String str3, long j);
}
