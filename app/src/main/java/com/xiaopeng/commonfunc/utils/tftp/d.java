package com.xiaopeng.commonfunc.utils.tftp;

import java.net.DatagramPacket;

/* compiled from: TFTPDataPacket.java */
/* loaded from: classes11.dex */
public final class d extends h {
    int pA;
    int pE;
    int pF;
    byte[] pG;

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

    @Override // com.xiaopeng.commonfunc.utils.tftp.h
    DatagramPacket a(DatagramPacket datagramPacket, byte[] bArr) {
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

    public int eO() {
        return this.pA;
    }

    public int getDataLength() {
        return this.pE;
    }

    public int getDataOffset() {
        return this.pF;
    }

    public byte[] getData() {
        return this.pG;
    }

    @Override // com.xiaopeng.commonfunc.utils.tftp.h
    public String toString() {
        return super.toString() + " DATA " + this.pA + " " + this.pE;
    }
}
