package com.lzy.okgo.e;

import com.lzy.okgo.f.d;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* compiled from: HttpsUtils.java */
/* loaded from: classes11.dex */
public class a {
    public static X509TrustManager mc = new X509TrustManager() { // from class: com.lzy.okgo.e.a.1
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };
    public static HostnameVerifier md = new HostnameVerifier() { // from class: com.lzy.okgo.e.a.2
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };

    /* compiled from: HttpsUtils.java */
    /* renamed from: com.lzy.okgo.e.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0036a {
        public SSLSocketFactory me;
        public X509TrustManager trustManager;
    }

    public static C0036a da() {
        return a(null, null, null, new InputStream[0]);
    }

    private static C0036a a(X509TrustManager x509TrustManager, InputStream inputStream, String str, InputStream... inputStreamArr) {
        C0036a c0036a = new C0036a();
        try {
            KeyManager[] a = a(inputStream, str);
            TrustManager[] a2 = a(inputStreamArr);
            if (x509TrustManager == null) {
                if (a2 != null) {
                    x509TrustManager = a(a2);
                } else {
                    x509TrustManager = mc;
                }
            }
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(a, new TrustManager[]{x509TrustManager}, null);
            c0036a.me = sSLContext.getSocketFactory();
            c0036a.trustManager = x509TrustManager;
            return c0036a;
        } catch (KeyManagementException e) {
            throw new AssertionError(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    private static KeyManager[] a(InputStream inputStream, String str) {
        if (inputStream == null || str == null) {
            return null;
        }
        try {
            KeyStore keyStore = KeyStore.getInstance("BKS");
            keyStore.load(inputStream, str.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, str.toCharArray());
            return keyManagerFactory.getKeyManagers();
        } catch (Exception e) {
            d.f(e);
            return null;
        }
    }

    private static TrustManager[] a(InputStream... inputStreamArr) {
        if (inputStreamArr == null || inputStreamArr.length <= 0) {
            return null;
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int length = inputStreamArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                InputStream inputStream = inputStreamArr[i];
                int i3 = i2 + 1;
                keyStore.setCertificateEntry(Integer.toString(i2), certificateFactory.generateCertificate(inputStream));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        d.f(e);
                    }
                }
                i++;
                i2 = i3;
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            return trustManagerFactory.getTrustManagers();
        } catch (Exception e2) {
            d.f(e2);
            return null;
        }
    }

    private static X509TrustManager a(TrustManager[] trustManagerArr) {
        for (TrustManager trustManager : trustManagerArr) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }
}
