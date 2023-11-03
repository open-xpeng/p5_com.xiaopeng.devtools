package com.xiaopeng.lib.security;

import android.os.Build;
import com.xiaopeng.lib.security.xmartv1.RandomKeySecurity;

/* compiled from: SecurityModuleFactory.java */
/* loaded from: classes12.dex */
public final class c {
    public static a om() {
        if (Build.VERSION.SDK_INT == 19) {
            return com.xiaopeng.lib.security.b.a.ot();
        }
        return RandomKeySecurity.oI();
    }
}
