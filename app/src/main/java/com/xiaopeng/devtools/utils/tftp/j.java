package com.xiaopeng.devtools.utils.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Locale;

/* compiled from: TFTPRequestPacket.java */
/* loaded from: classes12.dex */
public abstract class j extends h {
    protected int pR;
    protected String pS;
    static final String[] pQ = {"netascii", "octet"};
    protected static final byte[][] pP = {new byte[]{110, 101, 116, 97, 115, 99, 105, 105, 0}, new byte[]{111, 99, 116, 101, 116, 0}};

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(InetAddress inetAddress, int i, int i2, int i3, String str, int i4) {
        super(i2, inetAddress, i, i3);
        this.pS = str;
        this.pR = i4;
        this.pM = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(int i, int i2, DatagramPacket datagramPacket) throws TFTPPacketException {
        super(i, datagramPacket.getAddress(), datagramPacket.getPort(), i2);
        this.pM = i2;
        byte[] data = datagramPacket.getData();
        if (getType() != data[1]) {
            throw new TFTPPacketException("TFTP operator code does not match type.");
        }
        StringBuilder sb = new StringBuilder();
        int i3 = 2;
        int length = datagramPacket.getLength();
        while (i3 < length && data[i3] != 0) {
            sb.append((char) data[i3]);
            i3++;
        }
        this.pS = sb.toString();
        if (i3 >= length) {
            throw new TFTPPacketException("Bad filename and mode format.");
        }
        int i4 = 0;
        sb.setLength(0);
        for (int i5 = i3 + 1; i5 < length && data[i5] != 0; i5++) {
            sb.append((char) data[i5]);
        }
        String lowerCase = sb.toString().toLowerCase(Locale.ENGLISH);
        int length2 = pQ.length;
        int i6 = 0;
        while (true) {
            if (i6 < length2) {
                if (!lowerCase.equals(pQ[i6])) {
                    i6++;
                } else {
                    i4 = i6;
                    break;
                }
            } else {
                break;
            }
        }
        this.pR = i4;
        if (i6 >= length2) {
            throw new TFTPPacketException("Unrecognized TFTP transfer mode: " + lowerCase);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.xiaopeng.devtools.utils.tftp.h
    public DatagramPacket a(DatagramPacket datagramPacket, byte[] bArr) {
        int length = this.pS.length();
        int length2 = pP[this.pR].length;
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        System.arraycopy(this.pS.getBytes(), 0, bArr, 2, length);
        bArr[length + 2] = 0;
        System.arraycopy(pP[this.pR], 0, bArr, length + 3, length2);
        datagramPacket.setAddress(this.pO);
        datagramPacket.setPort(this.pN);
        datagramPacket.setData(bArr);
        datagramPacket.setLength(length + length2 + 3);
        return datagramPacket;
    }

    public final int getMode() {
        return this.pR;
    }

    public final String eQ() {
        return this.pS;
    }
}
