package com.xiaopeng.commonfunc.utils.tftp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* compiled from: TFTPClient.java */
/* loaded from: classes11.dex */
public class c extends a {
    private long pB = 0;
    private long pD = 0;
    private int pC = 5;

    /* JADX WARN: Removed duplicated region for block: B:111:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x022f A[LOOP:1: B:136:0x004e->B:127:0x022f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0236 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x020a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int a(java.lang.String r25, int r26, java.io.OutputStream r27, java.net.InetAddress r28, int r29, int r30) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 590
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.commonfunc.utils.tftp.c.a(java.lang.String, int, java.io.OutputStream, java.net.InetAddress, int, int):int");
    }

    public int a(String str, int i, OutputStream outputStream, String str2, int i2) throws UnknownHostException, IOException {
        return a(str, i, outputStream, InetAddress.getByName(str2), 69, i2);
    }
}
