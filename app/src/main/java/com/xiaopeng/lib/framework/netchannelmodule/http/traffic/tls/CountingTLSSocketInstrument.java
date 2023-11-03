package com.xiaopeng.lib.framework.netchannelmodule.http.traffic.tls;

import android.os.Build;
import com.xiaopeng.lib.http.b;
import com.xiaopeng.lib.http.c.e;
import com.xiaopeng.lib.utils.c;
import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

/* loaded from: classes12.dex */
public class CountingTLSSocketInstrument implements e.a {
    public static synchronized void initialize() {
        synchronized (CountingTLSSocketInstrument.class) {
            b.a(new CountingTLSSocketInstrument());
        }
    }

    public static synchronized void reset() {
        synchronized (CountingTLSSocketInstrument.class) {
            b.a(null);
        }
    }

    @Override // com.xiaopeng.lib.http.c.e.a
    public Socket createSocket(Socket socket, String str) throws IOException {
        try {
        } catch (Exception e) {
            c.d("hook tls socket failed for " + e);
        }
        if (isKitkat()) {
            HookTLSKitkatSocket.createSocket(socket, str);
            return socket;
        }
        return new HookTLSPSocket((SSLSocket) socket, str);
    }

    private static boolean isKitkat() {
        return Build.VERSION.SDK_INT == 19;
    }
}
