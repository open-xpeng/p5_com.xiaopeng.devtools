package org.apache.commons.net;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;

/* compiled from: DatagramSocketClient.java */
/* loaded from: classes13.dex */
public abstract class a {
    private static final b apT = new c();
    private Charset charset = Charset.defaultCharset();
    protected DatagramSocket apV = null;
    protected int apU = 0;
    protected boolean apW = false;
    protected b apX = apT;

    public void open() throws SocketException {
        this.apV = this.apX.createDatagramSocket();
        this.apV.setSoTimeout(this.apU);
        this.apW = true;
    }

    public void close() {
        if (this.apV != null) {
            this.apV.close();
        }
        this.apV = null;
        this.apW = false;
    }

    public void fu(int i) {
        this.apU = i;
    }

    public void setSoTimeout(int i) throws SocketException {
        this.apV.setSoTimeout(i);
    }

    public int getSoTimeout() throws SocketException {
        return this.apV.getSoTimeout();
    }
}
