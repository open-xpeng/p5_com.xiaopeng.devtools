package com.xiaopeng.devtools.utils.tftp;

import java.net.DatagramPacket;

/* compiled from: TFTPWriteRequestPacket.java */
/* loaded from: classes12.dex */
public final class k extends j {
    /* JADX INFO: Access modifiers changed from: package-private */
    public k(int i, DatagramPacket datagramPacket) throws TFTPPacketException {
        super(2, i, datagramPacket);
    }

    @Override // com.xiaopeng.devtools.utils.tftp.h
    public String toString() {
        return super.toString() + " WRQ " + eQ() + " " + a.bJ(getMode());
    }
}
