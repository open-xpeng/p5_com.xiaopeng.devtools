package com.xiaopeng.devtools.utils.tftp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.SocketException;

/* compiled from: TFTP.java */
/* loaded from: classes12.dex */
public class a extends org.apache.commons.net.a {
    byte[] pw;
    private byte[] px;
    private DatagramPacket py;
    private DatagramPacket pz;

    int bK(int i) {
        return i + 4;
    }

    public static final String bJ(int i) {
        return j.pQ[i];
    }

    public a() {
        fu(60000);
        this.px = null;
        this.py = null;
    }

    public final void bL(int i) throws IOException {
        int bK = bK(i);
        DatagramPacket datagramPacket = new DatagramPacket(new byte[bK], bK);
        int soTimeout = getSoTimeout();
        setSoTimeout(1);
        while (true) {
            try {
                this.apV.receive(datagramPacket);
            } catch (InterruptedIOException | SocketException e) {
                setSoTimeout(soTimeout);
                return;
            }
        }
    }

    public final h lK() throws IOException, InterruptedIOException, SocketException, TFTPPacketException {
        this.py.setData(this.px);
        this.py.setLength(this.px.length);
        this.apV.receive(this.py);
        h b = h.b(this.py);
        a("<", b);
        return b;
    }

    public final void a(h hVar) throws IOException {
        a(">", hVar);
        this.apV.send(hVar.a(this.pz, this.pw));
    }

    public final void bM(int i) {
        int bK = bK(i);
        this.px = new byte[bK];
        this.py = new DatagramPacket(this.px, this.px.length);
        this.pw = new byte[bK];
        this.pz = new DatagramPacket(this.pw, this.pw.length);
    }

    public final void eN() {
        this.px = null;
        this.py = null;
        this.pw = null;
        this.pz = null;
    }

    protected void a(String str, h hVar) {
    }
}
