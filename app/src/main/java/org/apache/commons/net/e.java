package org.apache.commons.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;

/* compiled from: SocketClient.java */
/* loaded from: classes13.dex */
public abstract class e {
    private static final SocketFactory apY = SocketFactory.getDefault();
    private static final ServerSocketFactory apZ = ServerSocketFactory.getDefault();
    private ProtocolCommandSupport aqa;
    protected int connectTimeout = 0;
    private int aqi = -1;
    private int aqj = -1;
    private Charset charset = Charset.defaultCharset();
    protected Socket aqb = null;
    protected String aqc = null;
    protected InputStream aqe = null;
    protected OutputStream aqf = null;
    protected int apU = 0;
    protected int aqd = 0;
    protected SocketFactory aqg = apY;
    protected ServerSocketFactory aqh = apZ;

    /* JADX INFO: Access modifiers changed from: protected */
    public void um() throws IOException {
        this.aqb.setSoTimeout(this.apU);
        this.aqe = this.aqb.getInputStream();
        this.aqf = this.aqb.getOutputStream();
    }

    public void connect(String str, int i) throws SocketException, IOException {
        this.aqc = str;
        a(InetAddress.getByName(str), i, null, -1);
    }

    private void a(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws SocketException, IOException {
        this.aqb = this.aqg.createSocket();
        if (this.aqi != -1) {
            this.aqb.setReceiveBufferSize(this.aqi);
        }
        if (this.aqj != -1) {
            this.aqb.setSendBufferSize(this.aqj);
        }
        if (inetAddress2 != null) {
            this.aqb.bind(new InetSocketAddress(inetAddress2, i2));
        }
        this.aqb.connect(new InetSocketAddress(inetAddress, i), this.connectTimeout);
        um();
    }

    public void connect(String str) throws SocketException, IOException {
        connect(str, this.aqd);
    }

    public void disconnect() throws IOException {
        closeQuietly(this.aqb);
        closeQuietly(this.aqe);
        closeQuietly(this.aqf);
        this.aqb = null;
        this.aqc = null;
        this.aqe = null;
        this.aqf = null;
    }

    private void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public boolean isConnected() {
        if (this.aqb == null) {
            return false;
        }
        return this.aqb.isConnected();
    }

    public boolean isAvailable() {
        if (isConnected()) {
            try {
                if (this.aqb.getInetAddress() == null || this.aqb.getPort() == 0 || this.aqb.getRemoteSocketAddress() == null || this.aqb.isClosed() || this.aqb.isInputShutdown() || this.aqb.isOutputShutdown()) {
                    return false;
                }
                this.aqb.getInputStream();
                this.aqb.getOutputStream();
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    public void fv(int i) {
        this.aqd = i;
    }

    public void setSoTimeout(int i) throws SocketException {
        this.aqb.setSoTimeout(i);
    }

    public void setSendBufferSize(int i) throws SocketException {
        this.aqj = i;
    }

    public int getSoTimeout() throws SocketException {
        return this.aqb.getSoTimeout();
    }

    public void setTcpNoDelay(boolean z) throws SocketException {
        this.aqb.setTcpNoDelay(z);
    }

    public void setKeepAlive(boolean z) throws SocketException {
        this.aqb.setKeepAlive(z);
    }

    public InetAddress getLocalAddress() {
        return this.aqb.getLocalAddress();
    }

    public InetAddress un() {
        return this.aqb.getInetAddress();
    }

    public boolean b(Socket socket) {
        return socket.getInetAddress().equals(un());
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void k(int i, String str) {
        if (uo().ul() > 0) {
            uo().k(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void au(String str, String str2) {
        if (uo().ul() > 0) {
            uo().au(str, str2);
        }
    }

    protected ProtocolCommandSupport uo() {
        return this.aqa;
    }
}
