package com.xiaopeng.lib.framework.moduleinterface.syncmodule;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

/* loaded from: classes12.dex */
public interface ISync {
    void init(@NonNull Application application, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5);

    void onNetworkAvailable();

    void onUserChanged(@NonNull Long l);

    void restore();

    void save(@Nullable List<SyncData> list);

    void saveAll(@Nullable List<SyncData> list);
}
