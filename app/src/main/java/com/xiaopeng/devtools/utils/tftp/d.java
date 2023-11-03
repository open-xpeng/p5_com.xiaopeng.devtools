package com.xiaopeng.devtools.utils.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

/* compiled from: TFTPDataPacket.java */
/* loaded from: classes12.dex */
public final class d extends h {
    int pA;
    int pE;
    int pF;
    byte[] pG;

    public d(InetAddress inetAddress, int i, int i2, byte[] bArr, int i3, int i4) {
        super(3, inetAddress, i);
        this.pA = i2;
        this.pG = bArr;
        this.pF = i3;
        if (i4 > 8192) {
            this.pE = 8192;
        } else {
            this.pE = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(DatagramPacket datagramPacket) throws TFTPPacketException {
        super(3, datagramPacket.getAddress(), datagramPacket.getPort());
        this.pG = datagramPacket.getData();
        this.pF = 4;
        if (getType() != this.pG[1]) {
            throw new TFTPPacketException("TFTP operator code does not match type.");
        }
        this.pA = ((this.pG[2] & 255) << 8) | (this.pG[3] & 255);
        this.pE = datagramPacket.getLength() - 4;
        if (this.pE > 8192) {
            this.pE = 8192;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.xiaopeng.devtools.utils.tftp.h
    public DatagramPacket a(DatagramPacket datagramPacket, byte[] bArr) {
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        bArr[2] = (byte) ((this.pA & 65535) >> 8);
        bArr[3] = (byte) (this.pA & 255);
        if (bArr != this.pG) {
            System.arraycopy(this.pG, this.pF, bArr, 4, this.pE);
        }
        datagramPacket.setAddress(this.pO);
        datagramPacket.setPort(this.pN);
        datagramPacket.setData(bArr);
        datagramPacket.setLength(this.pE + 4);
        return datagramPacket;
    }

    public void bN(int i) {
        this.pA = i;
    }

    public void setData(byte[] bArr, int i, int i2) {
        this.pG = bArr;
        this.pF = i;
        this.pE = i2;
        if (i2 > 8192) {
            this.pE = 8192;
        } else {
            this.pE = i2;
        }
    }

    @Override // com.xiaopeng.devtools.utils.tftp.h
    public String toString() {
        return super.toString() + " DATA " + this.pA + " " + this.pE;
    }
}
