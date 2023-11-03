package com.alibaba.sdk.android.httpdns.d;

import android.text.TextUtils;
import com.alibaba.sdk.android.httpdns.h;
import java.net.SocketTimeoutException;

/* loaded from: classes11.dex */
public class b {
    public static int a(Throwable th) {
        if (th instanceof h) {
            return ((h) th).getErrorCode();
        }
        if (th instanceof SocketTimeoutException) {
            return 10001;
        }
        return com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.r;
    }

    /* renamed from: a  reason: collision with other method in class */
    public static String m34a(Throwable th) {
        return (th == null || TextUtils.isEmpty(th.getMessage())) ? th instanceof SocketTimeoutException ? "time out exception" : "default error" : th.getMessage();
    }

    public static int b() {
        return 0;
    }
}
