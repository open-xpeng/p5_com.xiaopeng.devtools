package com.xiaopeng.devtools.utils.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

/* compiled from: TFTPOptionsRequestPacket.java */
/* loaded from: classes12.dex */
public abstract class f extends j {
    private static final byte[] pJ = {98, 108, 107, 115, 105, 122, 101};
    private static final byte[] pK = {116, 115, 105, 122, 101};
    protected long pL;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(InetAddress inetAddress, int i, int i2, int i3, String str, long j, int i4) {
        super(inetAddress, i, i2, i3, str, i4);
        this.pL = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.xiaopeng.devtools.utils.tftp.j, com.xiaopeng.devtools.utils.tftp.h
    public DatagramPacket a(DatagramPacket datagramPacket, byte[] bArr) {
        byte[] eP = eP();
        System.arraycopy(eP, 0, bArr, 0, eP.length);
        datagramPacket.setAddress(this.pO);
        datagramPacket.setPort(this.pN);
        datagramPacket.setData(bArr);
        datagramPacket.setLength(eP.length);
        return datagramPacket;
    }

    private byte[] eP() {
        int length = this.pS.length();
        int length2 = pP[this.pR].length;
        int length3 = pJ.length;
        String valueOf = String.valueOf(this.pM);
        int length4 = valueOf.getBytes().length;
        ByteBuffer allocate = ByteBuffer.allocate(2 + length + 1 + length2 + length3 + 1 + length4 + 1 + pK.length + 1 + String.valueOf(this.pL).getBytes().length + 1);
        allocate.put((byte) 0);
        allocate.put((byte) this._type);
        allocate.put(this.pS.getBytes());
        allocate.put((byte) 0);
        allocate.put(pP[this.pR]);
        allocate.put(pJ);
        allocate.put((byte) 0);
        allocate.put(valueOf.getBytes());
        allocate.put((byte) 0);
        allocate.put(pK);
        allocate.put((byte) 0);
        allocate.put(String.valueOf(this.pL).getBytes());
        allocate.put((byte) 0);
        return allocate.array();
    }
}
