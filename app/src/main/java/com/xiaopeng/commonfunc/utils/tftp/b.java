package com.xiaopeng.commonfunc.utils.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

/* compiled from: TFTPAckPacket.java */
/* loaded from: classes11.dex */
public final class b extends h {
    int pA;

    public b(InetAddress inetAddress, int i, int i2) {
        super(4, inetAddress, i);
        this.pA = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(DatagramPacket datagramPacket) throws TFTPPacketException {
        super(4, datagramPacket.getAddress(), datagramPacket.getPort());
        byte[] data = datagramPacket.getData();
        if ((getType() != 4 || (data[1] != 4 && data[1] != 6)) && getType() != data[1]) {
            throw new TFTPPacketException("TFTP operator code does not match type.");
        }
        if (data[1] == 6) {
            return;
        }
        this.pA = (data[3] & 255) | ((data[2] & 255) << 8);
    }

    @Override // com.xiaopeng.commonfunc.utils.tftp.h
    DatagramPacket a(DatagramPacket datagramPacket, byte[] bArr) {
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        bArr[2] = (byte) ((this.pA & 65535) >> 8);
        bArr[3] = (byte) (this.pA & 255);
        datagramPacket.setAddress(this.pO);
        datagramPacket.setPort(this.pN);
        datagramPacket.setData(bArr);
        datagramPacket.setLength(4);
        return datagramPacket;
    }

    public int eO() {
        return this.pA;
    }

    public void bN(int i) {
        this.pA = i;
    }

    @Override // com.xiaopeng.commonfunc.utils.tftp.h
    public String toString() {
        return super.toString() + " ACK " + this.pA;
    }
}
