package com.xiaopeng.devtools.utils.tftp;

import java.net.InetAddress;

/* compiled from: TFTPOptionsWriteRequestPacket.java */
/* loaded from: classes12.dex */
public final class g extends f {
    public g(InetAddress inetAddress, int i, int i2, String str, long j, int i3) {
        super(inetAddress, i, 2, i2, str, j, i3);
    }

    @Override // com.xiaopeng.devtools.utils.tftp.h
    public String toString() {
        return super.toString() + " WRQ " + eQ() + " " + a.bJ(getMode());
    }
}
