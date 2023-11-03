package com.xiaopeng.lib.http.c;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.systemdelegate.ISystemDelegate;
import com.xiaopeng.lib.http.c.e;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* compiled from: LocalFileCert.java */
/* loaded from: classes12.dex */
public class d {
    private static TrustManager[] Vj;
    private static KeyManager[] Vk;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [javax.net.ssl.TrustManager[]] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.security.KeyStore] */
    private static synchronized TrustManager[] ai(Context context) {
        synchronized (d.class) {
            KeyStore keyStore = Vj;
            if (keyStore != 0) {
                return Vj;
            }
            try {
                try {
                    keyStore = KeyStore.getInstance("bks");
                    InputStream open = context.getResources().getAssets().open("index_kstp.html");
                    try {
                        try {
                            keyStore.load(open, "chengzi".toCharArray());
                            open.close();
                        } catch (Throwable th) {
                            try {
                                open.close();
                            } catch (Exception e) {
                            }
                            throw th;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        open.close();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return null;
                }
            } catch (Exception e4) {
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init(keyStore);
            Vj = trustManagerFactory.getTrustManagers();
            return Vj;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.security.KeyStore] */
    private static synchronized KeyManager[] S(Context context) {
        String certificate;
        ByteArrayInputStream byteArrayInputStream;
        synchronized (d.class) {
            if (Vk != null) {
                return Vk;
            }
            try {
                if (com.xiaopeng.lib.security.b.am(context)) {
                    com.xiaopeng.lib.utils.c.g("LocalFileCert", "get cert content from file");
                    certificate = com.xiaopeng.lib.http.a.readTextFile(new File("/private/sec/xp0109.png"), 0, null);
                } else {
                    com.xiaopeng.lib.utils.c.g("LocalFileCert", "get cert content from SystemDelegate");
                    certificate = ((ISystemDelegate) Module.get(com.xiaopeng.system.delegate.module.a.class).get(ISystemDelegate.class)).getCertificate();
                }
                KeyStore isEmpty = TextUtils.isEmpty(certificate);
                if (isEmpty == 0) {
                    try {
                        isEmpty = KeyStore.getInstance("PKCS12");
                        byteArrayInputStream = new ByteArrayInputStream(Base64.decode(certificate, 0));
                    } catch (Exception e) {
                    }
                    try {
                        try {
                            isEmpty.load(byteArrayInputStream, "chengzi".toCharArray());
                            byteArrayInputStream.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            byteArrayInputStream.close();
                        }
                        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
                        keyManagerFactory.init(isEmpty, "chengzi".toCharArray());
                        Vk = keyManagerFactory.getKeyManagers();
                        return Vk;
                    } catch (Throwable th) {
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception e3) {
                        }
                        throw th;
                    }
                }
                throw new RuntimeException("certContent can't be empty");
            } catch (Exception e4) {
                com.xiaopeng.lib.utils.c.b("LocalFileCert", "getKeyManagers error!", e4);
                return null;
            }
        }
    }

    public static SSLSocketFactory T(Context context) {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
            sSLContext.init(S(context), ai(context), new SecureRandom());
            return new e.b(sSLContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static X509TrustManager aj(Context context) {
        try {
            TrustManager[] ai = ai(context);
            if (ai != null) {
                for (TrustManager trustManager : ai) {
                    if (trustManager instanceof X509TrustManager) {
                        return (X509TrustManager) trustManager;
                    }
                }
                return null;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
