package com.xiaopeng.lib.security;

import android.content.Context;
import com.xiaopeng.lib.http.Security;

/* compiled from: ISecurityModule.java */
/* loaded from: classes12.dex */
public interface a {
    String a(String[] strArr, byte[] bArr);

    void aP(boolean z);

    a ak(Context context) throws Exception;

    a al(Context context) throws Exception;

    boolean dC(String str) throws Exception;

    void destroy();

    Security.EncryptionType oh();

    boolean oi();

    void oj();

    void ok();
}
