package com.alibaba.mtl.log.d;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: UtSslSocketFactory.java */
/* loaded from: classes11.dex */
class x extends SSLSocketFactory {
    private Method b = null;
    private String dp;

    public x(String str) {
        this.dp = str;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return null;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        i.a("UtSslSocketFactory", "bizHost", this.dp, "host", str, "port", Integer.valueOf(i), "autoClose", Boolean.valueOf(z));
        if (TextUtils.isEmpty(this.dp)) {
            throw new IOException("SDK set empty bizHost");
        }
        i.a("UtSslSocketFactory", "customized createSocket. host: " + this.dp);
        try {
            SSLCertificateSocketFactory sSLCertificateSocketFactory = (SSLCertificateSocketFactory) SSLCertificateSocketFactory.getDefault(com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.r, new SSLSessionCache(com.alibaba.mtl.log.a.getContext()));
            if (Build.VERSION.SDK_INT < 24) {
                sSLCertificateSocketFactory.setTrustManagers(y.getTrustManagers());
            } else {
                sSLCertificateSocketFactory.setTrustManagers(v.getTrustManagers());
            }
            SSLSocket sSLSocket = (SSLSocket) sSLCertificateSocketFactory.createSocket(socket, this.dp, i, z);
            sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
            if (Build.VERSION.SDK_INT < 17) {
                try {
                    if (this.b == null) {
                        this.b = sSLSocket.getClass().getMethod("setHostname", String.class);
                        this.b.setAccessible(true);
                    }
                    this.b.invoke(sSLSocket, this.dp);
                } catch (Exception e) {
                }
            } else {
                sSLCertificateSocketFactory.setUseSessionTickets(sSLSocket, true);
                sSLCertificateSocketFactory.setHostname(sSLSocket, this.dp);
            }
            sSLSocket.startHandshake();
            return sSLSocket;
        } catch (Throwable th) {
            throw new IOException("createSocket exception: " + th);
        }
    }

    public boolean equals(Object obj) {
        if (TextUtils.isEmpty(this.dp) || !(obj instanceof x)) {
            return false;
        }
        String str = ((x) obj).dp;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.dp.equals(str);
    }

    public String getHost() {
        return this.dp;
    }
}
