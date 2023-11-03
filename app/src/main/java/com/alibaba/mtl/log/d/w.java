package com.alibaba.mtl.log.d;

import android.text.TextUtils;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* compiled from: UtHostnameVerifier.java */
/* loaded from: classes11.dex */
class w implements HostnameVerifier {
    public String dp;

    public w(String str) {
        this.dp = str;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(this.dp, sSLSession);
    }

    public boolean equals(Object obj) {
        if (TextUtils.isEmpty(this.dp) || !(obj instanceof w)) {
            return false;
        }
        String str = ((w) obj).dp;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.dp.equals(str);
    }

    public String getHost() {
        return this.dp;
    }
}
