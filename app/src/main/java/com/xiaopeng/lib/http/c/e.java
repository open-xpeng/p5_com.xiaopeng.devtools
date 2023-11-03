package com.xiaopeng.lib.http.c;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.TlsVersion;

/* compiled from: SSLHelper.java */
/* loaded from: classes12.dex */
public class e {
    private static final boolean Vl;
    private static a Vm;

    /* compiled from: SSLHelper.java */
    /* loaded from: classes12.dex */
    public interface a {
        Socket createSocket(Socket socket, String str) throws IOException;
    }

    static {
        Vl = Build.VERSION.SDK_INT == 19;
    }

    public static List<ConnectionSpec> getConnectionSpecs() {
        ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(build);
        arrayList.add(ConnectionSpec.COMPATIBLE_TLS);
        arrayList.add(ConnectionSpec.CLEARTEXT);
        return arrayList;
    }

    public static SSLSocketFactory T(Context context) {
        if (Vl) {
            return d.T(context);
        }
        return c.of();
    }

    public static X509TrustManager aj(Context context) {
        if (Vl) {
            return d.aj(context);
        }
        return c.ec();
    }

    /* compiled from: SSLHelper.java */
    /* loaded from: classes12.dex */
    public static class b extends SSLSocketFactory {
        final SSLSocketFactory Vn;
        private final String[] Vo = {"TLSv1.2"};

        public b(SSLSocketFactory sSLSocketFactory) {
            this.Vn = sSLSocketFactory;
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getDefaultCipherSuites() {
            return this.Vn.getDefaultCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getSupportedCipherSuites() {
            return this.Vn.getSupportedCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            Socket createSocket = this.Vn.createSocket(socket, str, i, z);
            return a(createSocket, str + ":" + i);
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i) throws IOException {
            Socket createSocket = this.Vn.createSocket(str, i);
            return a(createSocket, str + ":" + i);
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            Socket createSocket = this.Vn.createSocket(str, i, inetAddress, i2);
            return a(createSocket, str + ":" + i);
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            StringBuilder sb;
            if (inetAddress == null) {
                sb = new StringBuilder();
            } else {
                sb = new StringBuilder();
                sb.append(inetAddress.getHostName());
            }
            sb.append(":");
            sb.append(i);
            return a(this.Vn.createSocket(inetAddress, i), sb.toString());
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            StringBuilder sb;
            if (inetAddress == null) {
                sb = new StringBuilder();
            } else {
                sb = new StringBuilder();
                sb.append(inetAddress.getHostName());
            }
            sb.append(":");
            sb.append(i);
            return a(this.Vn.createSocket(inetAddress, i, inetAddress2, i2), sb.toString());
        }

        private Socket a(Socket socket, String str) throws IOException {
            if (e.Vm != null) {
                socket = e.Vm.createSocket(socket, str);
            }
            if (socket instanceof SSLSocket) {
                ((SSLSocket) socket).setEnabledProtocols(this.Vo);
            }
            return socket;
        }
    }

    public static void a(@NonNull a aVar) {
        Vm = aVar;
    }
}
