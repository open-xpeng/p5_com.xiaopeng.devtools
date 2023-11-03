package com.xiaopeng.devtools.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* compiled from: TftpSender.java */
/* loaded from: classes12.dex */
public class s {
    private static boolean BO = true;

    public static void p(String str, String str2, String str3) throws Exception {
        com.xiaopeng.devtools.utils.tftp.c cVar;
        FileInputStream fileInputStream;
        if (BO) {
            cVar = new com.xiaopeng.devtools.utils.tftp.c() { // from class: com.xiaopeng.devtools.utils.s.1
                @Override // com.xiaopeng.devtools.utils.tftp.a
                protected void a(String str4, com.xiaopeng.devtools.utils.tftp.h hVar) {
                    com.xiaopeng.lib.utils.c.f("TftpSender", "direction: " + str4 + ", packet:" + hVar.toString());
                }
            };
        } else {
            cVar = new com.xiaopeng.devtools.utils.tftp.c();
        }
        cVar.fu(com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.r);
        try {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    try {
                        cVar.open();
                        cVar.a(str2, 1, fileInputStream, InetAddress.getByName(str3), 8192);
                        cVar.close();
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            com.xiaopeng.lib.utils.c.b("TftpSender", e);
                        }
                    } catch (IOException e2) {
                        com.xiaopeng.lib.utils.c.b("TftpSender", e2);
                        throw new Exception(e2);
                    }
                } catch (UnknownHostException e3) {
                    com.xiaopeng.lib.utils.c.b("TftpSender", e3);
                    throw new Exception(e3);
                }
            } catch (Throwable th) {
                cVar.close();
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    com.xiaopeng.lib.utils.c.b("TftpSender", e4);
                }
                throw th;
            }
        } catch (IOException e5) {
            cVar.close();
            com.xiaopeng.lib.utils.c.b("TftpSender", e5);
            throw new Exception(e5);
        }
    }
}
