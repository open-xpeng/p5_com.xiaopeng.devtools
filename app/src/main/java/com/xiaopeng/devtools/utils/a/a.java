package com.xiaopeng.devtools.utils.a;

import android.content.Context;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;

/* compiled from: IIndivInternal.java */
/* loaded from: classes12.dex */
public interface a {
    void Y(Context context);

    IRequest a(Context context, com.xiaopeng.lib.security.a aVar) throws Exception;

    IRequest b(Context context, com.xiaopeng.lib.security.a aVar) throws Exception;

    String b(IResponse iResponse);

    boolean c(IResponse iResponse);
}
