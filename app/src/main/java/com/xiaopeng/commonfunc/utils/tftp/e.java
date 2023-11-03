package com.xiaopeng.commonfunc.utils.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

/* compiled from: TFTPErrorPacket.java */
/* loaded from: classes11.dex */
public final class e extends h {
    int pH;
    String pI;

    public e(InetAddress inetAddress, int i, int i2, String str) {
        super(5, inetAddress, i);
        this.pH = i2;
        this.pI = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(DatagramPacket datagramPacket) throws TFTPPacketException {
        super(5, datagramPacket.getAddress(), datagramPacket.getPort());
        byte[] data = datagramPacket.getData();
        int length = datagramPacket.getLength();
        if (getType() != data[1]) {
            throw new TFTPPacketException("TFTP operator code does not match type.");
        }
        this.pH = ((data[2] & 255) << 8) | (data[3] & 255);
        if (length < 5) {
            throw new TFTPPacketException("Bad error packet. No message.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 4; i < length && data[i] != 0; i++) {
            sb.append((char) data[i]);
        }
        this.pI = sb.toString();
    }

    @Override // com.xiaopeng.commonfunc.utils.tftp.h
    DatagramPacket a(DatagramPacket datagramPacket, byte[] bArr) {
        int length = this.pI.length();
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        bArr[2] = (byte) ((this.pH & 65535) >> 8);
        bArr[3] = (byte) (this.pH & 255);
        System.arraycopy(this.pI.getBytes(), 0, bArr, 4, length);
        int i = length + 4;
        bArr[i] = 0;
        datagramPacket.setAddress(this.pO);
        datagramPacket.setPort(this.pN);
        datagramPacket.setData(bArr);
        datagramPacket.setLength(i);
        return datagramPacket;
    }

    public int getError() {
        return this.pH;
    }

    public String getMessage() {
        return this.pI;
    }

    @Override // com.xiaopeng.commonfunc.utils.tftp.h
    public String toString() {
        return super.toString() + " ERR " + this.pH + " " + this.pI;
    }
}
