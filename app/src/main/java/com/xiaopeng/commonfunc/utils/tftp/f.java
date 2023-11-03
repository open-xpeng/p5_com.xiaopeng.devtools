package com.xiaopeng.commonfunc.utils.tftp;

import java.net.InetAddress;

/* compiled from: TFTPOptionsReadRequestPacket.java */
/* loaded from: classes11.dex */
public class f extends g {
    public f(InetAddress inetAddress, int i, int i2, String str, long j, int i3) {
        super(inetAddress, i, 1, i2, str, j, i3);
    }

    @Override // com.xiaopeng.commonfunc.utils.tftp.h
    public String toString() {
        return super.toString() + " RRQ " + eQ() + " " + a.bJ(getMode());
    }
}
