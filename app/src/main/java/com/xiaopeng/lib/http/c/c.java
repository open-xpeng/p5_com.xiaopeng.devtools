package com.xiaopeng.lib.http.c;

import android.os.Build;
import android.util.Log;
import com.xiaopeng.lib.apirouter.ClientConstants;
import com.xiaopeng.lib.http.c.e;
import java.io.FileInputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

/* compiled from: KeyStoreCert.java */
/* loaded from: classes12.dex */
public class c {
    public static SSLSocketFactory of() {
        b bVar;
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
            KeyStore keyStore = KeyStore.getInstance("XpengAndroidKeyStore");
            keyStore.load(null);
            if (Build.VERSION.SDK_INT > 28) {
                bVar = new b();
            } else {
                bVar = new b(keyStore);
            }
            sSLContext.init(new KeyManager[]{new a(keyStore)}, new TrustManager[]{bVar}, null);
            return new e.b(sSLContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static X509TrustManager ec() {
        try {
            if (Build.VERSION.SDK_INT > 28) {
                b bVar = new b();
                Log.i("KeyStoreCert", "getX509TrustManager from  Android  Q ");
                return bVar;
            }
            Log.i("KeyStoreCert", "getX509TrustManager from  Android  P ");
            KeyStore keyStore = KeyStore.getInstance("XpengAndroidKeyStore");
            keyStore.load(null);
            return new b(keyStore);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* compiled from: KeyStoreCert.java */
    /* loaded from: classes12.dex */
    private static class b implements X509TrustManager {
        private KeyStore Vi;
        private final List<X509TrustManager> os;
        private List<X509Certificate> ot;
        private boolean ou;

        public b(KeyStore keyStore) {
            String[] strArr;
            this.ot = null;
            this.ou = false;
            this.Vi = null;
            Log.i("KeyStoreCert", "init Android P  CompositeX509TrustManager ");
            this.os = new ArrayList();
            this.Vi = keyStore;
            try {
                KeyStore keyStore2 = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore2.load(null);
                for (String str : new String[]{"ca", "client", "server", ClientConstants.ALIAS.P_ALIAS}) {
                    try {
                        Certificate certificate = keyStore.getCertificate(str);
                        if (certificate instanceof X509Certificate) {
                            keyStore2.setCertificateEntry(str, certificate);
                        }
                    } catch (KeyStoreException e) {
                        e.printStackTrace();
                    }
                }
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
                trustManagerFactory.init(keyStore2);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                if (trustManagers != null) {
                    for (TrustManager trustManager : trustManagers) {
                        if (trustManager instanceof X509TrustManager) {
                            this.os.add((X509TrustManager) trustManager);
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public b() {
            this.ot = null;
            this.ou = false;
            this.Vi = null;
            Log.i("KeyStoreCert", "init Android Q  CompositeX509TrustManager ");
            this.os = new ArrayList();
            this.ot = new ArrayList();
            try {
                KeyStore keyStore = KeyStore.getInstance("BKS");
                FileInputStream fileInputStream = new FileInputStream("/system/etc/index_kstp.html");
                keyStore.load(fileInputStream, "chengzi".toCharArray());
                fileInputStream.close();
                Enumeration<String> aliases = keyStore.aliases();
                while (aliases.hasMoreElements()) {
                    Certificate certificate = keyStore.getCertificate(aliases.nextElement());
                    if (certificate instanceof X509Certificate) {
                        this.ot.add((X509Certificate) certificate);
                        this.ou = true;
                    }
                }
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
                trustManagerFactory.init(keyStore);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                if (trustManagers != null) {
                    for (TrustManager trustManager : trustManagers) {
                        if (trustManager instanceof X509TrustManager) {
                            this.os.add((X509TrustManager) trustManager);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            for (X509TrustManager x509TrustManager : this.os) {
                try {
                    x509TrustManager.checkClientTrusted(x509CertificateArr, str);
                    return;
                } catch (CertificateException e) {
                }
            }
            throw new CertificateException("None of the TrustManagers trust this certificate chain");
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            for (X509TrustManager x509TrustManager : this.os) {
                try {
                    x509TrustManager.checkServerTrusted(x509CertificateArr, str);
                    return;
                } catch (CertificateException e) {
                }
            }
            throw new CertificateException("None of the TrustManagers trust this certificate chain");
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            if (Build.VERSION.SDK_INT > 28) {
                Log.i("KeyStoreCert", "getAcceptedIssuers from ANdroid Q ");
                return (X509Certificate[]) this.ot.toArray(new X509Certificate[0]);
            }
            Log.i("KeyStoreCert", "getAcceptedIssuers from ANdroid P ");
            ArrayList arrayList = new ArrayList();
            for (String str : new String[]{"ca", "client", "server", ClientConstants.ALIAS.P_ALIAS}) {
                try {
                    Certificate certificate = this.Vi.getCertificate(str);
                    if (certificate instanceof X509Certificate) {
                        arrayList.add((X509Certificate) certificate);
                    }
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                }
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[0]);
        }
    }

    /* compiled from: KeyStoreCert.java */
    /* loaded from: classes12.dex */
    private static class a implements X509KeyManager {
        private final KeyStore Vi;

        public a(KeyStore keyStore) {
            this.Vi = keyStore;
        }

        @Override // javax.net.ssl.X509KeyManager
        public String[] getClientAliases(String str, Principal[] principalArr) {
            return new String[]{"client"};
        }

        @Override // javax.net.ssl.X509KeyManager
        public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
            return "client";
        }

        @Override // javax.net.ssl.X509KeyManager
        public String[] getServerAliases(String str, Principal[] principalArr) {
            return new String[]{"server"};
        }

        @Override // javax.net.ssl.X509KeyManager
        public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
            return "server";
        }

        @Override // javax.net.ssl.X509KeyManager
        public X509Certificate[] getCertificateChain(String str) {
            Certificate certificate;
            try {
                certificate = this.Vi.getCertificate("client");
            } catch (KeyStoreException e) {
                e.printStackTrace();
                certificate = null;
            }
            return new X509Certificate[]{(X509Certificate) certificate};
        }

        @Override // javax.net.ssl.X509KeyManager
        public PrivateKey getPrivateKey(String str) {
            try {
                return (PrivateKey) this.Vi.getKey("client", null);
            } catch (KeyStoreException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
                return null;
            } catch (UnrecoverableKeyException e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }
}
