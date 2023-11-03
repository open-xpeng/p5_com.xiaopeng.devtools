package com.xiaopeng.lib.http;

/* compiled from: IrdetoUtils.java */
@Deprecated
/* loaded from: classes12.dex */
public class d {
    public static final String[] UK = {"accessToken", "refreshToken"};
    public static final String[] UL = {"accessToken"};
    private static com.xiaopeng.lib.security.a pm = com.xiaopeng.lib.security.a.a.or();

    public static String a(String[] strArr, byte[] bArr) {
        return pm.a(strArr, bArr);
    }
}
