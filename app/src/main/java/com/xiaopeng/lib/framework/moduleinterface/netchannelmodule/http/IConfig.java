package com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http;

import android.app.Application;
import android.support.annotation.NonNull;
import okhttp3.Interceptor;

/* loaded from: classes12.dex */
public interface IConfig {
    IConfig addInterceptor(@NonNull Interceptor interceptor);

    IConfig applicationContext(@NonNull Application application);

    void apply();

    int connectTimeout();

    IConfig connectTimeout(int i);

    int dnsTimeout();

    IConfig dnsTimeout(int i);

    IConfig enableLogging();

    IConfig enableTracing();

    IConfig enableTrafficStats();

    int readTimeout();

    IConfig readTimeout(int i);

    int retryCount();

    IConfig retryCount(int i);

    int writeTimeout();

    IConfig writeTimeout(int i);
}
