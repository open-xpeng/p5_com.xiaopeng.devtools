package com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http;

import android.support.annotation.NonNull;

/* loaded from: classes12.dex */
public interface IHttp {
    IBizHelper bizHelper();

    void cancelTag(Object obj);

    IConfig config();

    IRequest get(@NonNull String str);

    IRequest head(@NonNull String str);

    IRequest post(@NonNull String str);

    IRequest requestXmartBiz(@NonNull String str);
}
