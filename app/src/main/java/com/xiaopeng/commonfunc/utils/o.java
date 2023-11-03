package com.xiaopeng.commonfunc.utils;

import com.xiaopeng.a.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/* compiled from: TftpSender.java */
/* loaded from: classes11.dex */
public class o {
    public static final String ps = a.C0041a.getString("TBOX_CAN_DATA_SERVER_IP");
    public static final boolean pt = a.C0041a.dX("TBOX_SUPPORT_SSH");

    public static void j(String str, String str2, String str3) {
        com.xiaopeng.lib.utils.c.f("TftpSender", "receiveFile remoteFileName = " + str + " localFileName = " + str2 + " host = " + str3);
        com.xiaopeng.commonfunc.utils.tftp.c cVar = new com.xiaopeng.commonfunc.utils.tftp.c() { // from class: com.xiaopeng.commonfunc.utils.o.1
            @Override // com.xiaopeng.commonfunc.utils.tftp.a
            protected void a(String str4, com.xiaopeng.commonfunc.utils.tftp.h hVar) {
                com.xiaopeng.lib.utils.c.f("TftpSender", "direction: " + str4 + ", packet:" + hVar.toString());
            }
        };
        cVar.fu(com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.r);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            try {
                cVar.open();
                try {
                    try {
                        try {
                            try {
                                cVar.a(str, 1, fileOutputStream, str3, 8192);
                                cVar.close();
                                fileOutputStream.close();
                            } catch (IOException e) {
                                com.xiaopeng.lib.utils.c.e("Error: I/O exception occurred while receiving file.");
                                com.xiaopeng.lib.utils.c.e(e.getMessage());
                                cVar.close();
                                fileOutputStream.close();
                            }
                        } catch (UnknownHostException e2) {
                            com.xiaopeng.lib.utils.c.e("Error: could not resolve hostname.");
                            com.xiaopeng.lib.utils.c.e(e2.getMessage());
                            cVar.close();
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        cVar.close();
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            com.xiaopeng.lib.utils.c.e("Error: error closing file.");
                            com.xiaopeng.lib.utils.c.e(e3.getMessage());
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    com.xiaopeng.lib.utils.c.e("Error: error closing file.");
                    com.xiaopeng.lib.utils.c.e(e4.getMessage());
                }
            } catch (SocketException e5) {
                com.xiaopeng.lib.utils.c.e("Error: could not open local UDP socket.");
                com.xiaopeng.lib.utils.c.e(e5.getMessage());
            }
        } catch (IOException e6) {
            cVar.close();
            com.xiaopeng.lib.utils.c.e("Error: could not open local file for writing.");
            com.xiaopeng.lib.utils.c.e(e6.getMessage());
        }
    }
}
