package com.xiaopeng.lib.framework.moduleinterface.configurationmodule;

import android.app.Application;
import android.support.annotation.NonNull;

/* loaded from: classes12.dex */
public interface IConfiguration {
    String getConfiguration(String str, String str2);

    void init(@NonNull Application application, @NonNull String str);
}
