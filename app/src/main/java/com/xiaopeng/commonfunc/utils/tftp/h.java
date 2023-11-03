package com.xiaopeng.commonfunc.utils.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

/* compiled from: TFTPPacket.java */
/* loaded from: classes11.dex */
public abstract class h {
    int _type;
    int pM;
    int pN;
    InetAddress pO;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract DatagramPacket a(DatagramPacket datagramPacket, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(int i, InetAddress inetAddress, int i2) {
        this._type = i;
        this.pO = inetAddress;
        this.pN = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(int i, InetAddress inetAddress, int i2, int i3) {
        this._type = i;
        this.pO = inetAddress;
        this.pN = i2;
        this.pM = i3;
    }

    public static final h a(DatagramPacket datagramPacket) throws TFTPPacketException {
        if (datagramPacket.getLength() < 4) {
            throw new TFTPPacketException("Bad packet. Datagram data length is too short.");
        }
        byte[] data = datagramPacket.getData();
        int length = datagramPacket.getLength() - 4;
        switch (data[1]) {
            case 1:
                return new i(length, datagramPacket);
            case 2:
                return new k(length, datagramPacket);
            case 3:
                return new d(datagramPacket);
            case 4:
            case 6:
                return new b(datagramPacket);
            case 5:
                return new e(datagramPacket);
            default:
                throw new TFTPPacketException("Bad packet.  Invalid TFTP operator code.");
        }
    }

    public final int getType() {
        return this._type;
    }

    public final InetAddress getAddress() {
        return this.pO;
    }

    public final void setAddress(InetAddress inetAddress) {
        this.pO = inetAddress;
    }

    public final int getPort() {
        return this.pN;
    }

    public final void setPort(int i) {
        this.pN = i;
    }

    public String toString() {
        return this.pO + " " + this.pN + " " + this._type + " " + this.pM;
    }
}
