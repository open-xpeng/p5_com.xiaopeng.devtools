package com.xiaopeng.commonfunc.utils.tftp;

import java.net.DatagramPacket;

/* compiled from: TFTPReadRequestPacket.java */
/* loaded from: classes11.dex */
public final class i extends j {
    /* JADX INFO: Access modifiers changed from: package-private */
    public i(int i, DatagramPacket datagramPacket) throws TFTPPacketException {
        super(1, i, datagramPacket);
    }

    @Override // com.xiaopeng.commonfunc.utils.tftp.h
    public String toString() {
        return super.toString() + " RRQ " + eQ() + " " + a.bJ(getMode());
    }
}
