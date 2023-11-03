package com.xiaopeng.commonfunc.b.f;

import android.content.Context;
import android.os.FileUtils;
import android.text.TextUtils;
import android.util.Base64;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.xiaopeng.lib.http.c.e;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: CertVerifyModel.java */
/* loaded from: classes11.dex */
public class b {
    private Context mContext;
    private final String oj;
    private final String ok;
    private final String ol;
    private final String om;
    private final boolean on = true;
    private final boolean oo = true;

    public b(Context context, String str, String str2, String str3, String str4) {
        this.mContext = context;
        this.oj = str;
        this.ok = str2;
        this.ol = str3;
        this.om = str4;
    }

    private synchronized KeyManager[] S(Context context) {
        InputStream fileInputStream;
        try {
            try {
                try {
                    try {
                        KeyStore keyStore = KeyStore.getInstance("PKCS12");
                        if (this.on) {
                            com.xiaopeng.lib.utils.c.g("CertVerifyModel", "get client cert content from file mClientCertPath:" + this.oj);
                            String readTextFile = FileUtils.readTextFile(new File(this.oj), 0, null);
                            fileInputStream = !TextUtils.isEmpty(readTextFile) ? new ByteArrayInputStream(Base64.decode(readTextFile, 0)) : null;
                        } else {
                            fileInputStream = new FileInputStream(new File(this.oj));
                        }
                        try {
                            if (fileInputStream != null) {
                                try {
                                    try {
                                        keyStore.load(fileInputStream, this.om.toCharArray());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e2) {
                                            e = e2;
                                            e.printStackTrace();
                                            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
                                            keyManagerFactory.init(keyStore, this.om.toCharArray());
                                            return keyManagerFactory.getKeyManagers();
                                        }
                                    } catch (CertificateException e3) {
                                        e3.printStackTrace();
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e4) {
                                            e = e4;
                                            e.printStackTrace();
                                            KeyManagerFactory keyManagerFactory2 = KeyManagerFactory.getInstance("X509");
                                            keyManagerFactory2.init(keyStore, this.om.toCharArray());
                                            return keyManagerFactory2.getKeyManagers();
                                        }
                                    }
                                } catch (NoSuchAlgorithmException e5) {
                                    e5.printStackTrace();
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e6) {
                                        e = e6;
                                        e.printStackTrace();
                                        KeyManagerFactory keyManagerFactory22 = KeyManagerFactory.getInstance("X509");
                                        keyManagerFactory22.init(keyStore, this.om.toCharArray());
                                        return keyManagerFactory22.getKeyManagers();
                                    }
                                }
                                try {
                                    fileInputStream.close();
                                } catch (IOException e7) {
                                    e = e7;
                                    e.printStackTrace();
                                    KeyManagerFactory keyManagerFactory222 = KeyManagerFactory.getInstance("X509");
                                    keyManagerFactory222.init(keyStore, this.om.toCharArray());
                                    return keyManagerFactory222.getKeyManagers();
                                }
                            }
                            KeyManagerFactory keyManagerFactory2222 = KeyManagerFactory.getInstance("X509");
                            keyManagerFactory2222.init(keyStore, this.om.toCharArray());
                            return keyManagerFactory2222.getKeyManagers();
                        } catch (Throwable th) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e8) {
                                e8.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (UnrecoverableKeyException e9) {
                        e9.printStackTrace();
                        return null;
                    }
                } catch (KeyStoreException e10) {
                    e10.printStackTrace();
                    return null;
                }
            } catch (NoSuchAlgorithmException e11) {
                e11.printStackTrace();
                return null;
            }
        } catch (IOException e12) {
            e12.printStackTrace();
            return null;
        }
    }

    private SSLSocketFactory T(Context context) {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
            a aVar = new a(this.ok);
            if (!aVar.ed()) {
                com.xiaopeng.lib.utils.c.i("CertVerifyModel", "getTLS2SocketFactory trustManager has no valid cert");
            }
            sSLContext.init(S(context), new TrustManager[]{aVar}, new SecureRandom());
            return new e.b(sSLContext.getSocketFactory());
        } catch (KeyManagementException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public X509TrustManager ec() {
        try {
            a aVar = new a(this.ok);
            if (!aVar.ed()) {
                com.xiaopeng.lib.utils.c.i("CertVerifyModel", "getX509TrustManager trustManager has no valid cert");
            }
            return aVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private OkHttpClient U(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("CertVerifyModel");
        httpLoggingInterceptor.a(HttpLoggingInterceptor.Level.BODY);
        httpLoggingInterceptor.a(Level.INFO);
        return new OkHttpClient.Builder().sslSocketFactory(T(context), ec()).connectionSpecs(com.xiaopeng.lib.http.c.e.getConnectionSpecs()).connectionPool(new ConnectionPool()).addInterceptor(httpLoggingInterceptor).readTimeout(30L, TimeUnit.SECONDS).hostnameVerifier(com.xiaopeng.lib.http.c.b.Vh).build();
    }

    public void a(final com.xiaopeng.lib.http.c<String, String> cVar) {
        U(this.mContext).newCall(new Request.Builder().url(this.ol).get().build()).enqueue(new Callback() { // from class: com.xiaopeng.commonfunc.b.f.b.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                com.xiaopeng.lib.utils.c.i("CertVerifyModel", "onFailure:" + iOException);
                if (cVar != null) {
                    cVar.j(null);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                com.xiaopeng.lib.utils.c.g("CertVerifyModel", "onResponse:" + response.code());
                if (cVar != null) {
                    cVar.onSuccess(String.valueOf(response.code()));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CertVerifyModel.java */
    /* loaded from: classes11.dex */
    public class a implements X509TrustManager {
        private final List<X509TrustManager> os = new ArrayList();
        private final List<X509Certificate> ot = new ArrayList();
        private boolean ou;

        /* JADX WARN: Removed duplicated region for block: B:48:0x00b3 A[Catch: Exception -> 0x00ef, TryCatch #1 {Exception -> 0x00ef, blocks: (B:3:0x0016, B:5:0x003d, B:7:0x0051, B:13:0x0075, B:24:0x0086, B:45:0x00a9, B:46:0x00ad, B:48:0x00b3, B:50:0x00c1, B:52:0x00cc, B:54:0x00db, B:56:0x00de, B:58:0x00e4, B:21:0x0081, B:28:0x008e, B:34:0x0098, B:9:0x005b, B:12:0x006c, B:20:0x007e, B:27:0x008b, B:33:0x0095), top: B:64:0x0016, inners: #4 }] */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00db A[Catch: Exception -> 0x00ef, TryCatch #1 {Exception -> 0x00ef, blocks: (B:3:0x0016, B:5:0x003d, B:7:0x0051, B:13:0x0075, B:24:0x0086, B:45:0x00a9, B:46:0x00ad, B:48:0x00b3, B:50:0x00c1, B:52:0x00cc, B:54:0x00db, B:56:0x00de, B:58:0x00e4, B:21:0x0081, B:28:0x008e, B:34:0x0098, B:9:0x005b, B:12:0x006c, B:20:0x007e, B:27:0x008b, B:33:0x0095), top: B:64:0x0016, inners: #4 }] */
        /* JADX WARN: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public a(java.lang.String r6) {
            /*
                Method dump skipped, instructions count: 244
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.commonfunc.b.f.b.a.<init>(com.xiaopeng.commonfunc.b.f.b, java.lang.String):void");
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
            throw new CertificateException("None of the TrustManagers trust this client certificate chain");
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
            throw new CertificateException("None of the TrustManagers trust this server certificate chain");
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return (X509Certificate[]) this.ot.toArray(new X509Certificate[0]);
        }

        public boolean ed() {
            return this.ou;
        }
    }
}
