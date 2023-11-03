package com.xiaopeng.devtools.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/* compiled from: FtpSender.java */
/* loaded from: classes12.dex */
public class h {
    private static boolean BO = true;

    public static boolean a(String str, String str2, int i, String str3, String str4, String str5) {
        org.apache.commons.net.ftp.c cVar;
        org.apache.commons.net.ftp.c cVar2 = null;
        try {
            try {
                cVar = lc();
                try {
                    if (cVar == null) {
                        com.xiaopeng.lib.utils.c.f("FtpSender", "getFTPClient fail........ ");
                        if (Objects.nonNull(cVar)) {
                            try {
                                cVar.uG();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                cVar.disconnect();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        return false;
                    }
                    if (i != -1) {
                        cVar.connect(str2, i);
                    } else {
                        cVar.connect(str2);
                    }
                    cVar.az(str3, str4);
                    cVar.bE(false);
                    cVar.fy(2);
                    boolean a = a(cVar, str, str5);
                    if (Objects.nonNull(cVar)) {
                        try {
                            cVar.uG();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        try {
                            cVar.disconnect();
                            return a;
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            return a;
                        }
                    }
                    return a;
                } catch (Exception e5) {
                    e = e5;
                    cVar2 = cVar;
                    e.printStackTrace();
                    if (Objects.nonNull(cVar2)) {
                        try {
                            cVar2.uG();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                        try {
                            cVar2.disconnect();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (Objects.nonNull(cVar)) {
                        try {
                            cVar.uG();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                        try {
                            cVar.disconnect();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cVar = cVar2;
            }
        } catch (Exception e10) {
            e = e10;
        }
    }

    private static /* synthetic */ void a(Throwable th, AutoCloseable autoCloseable) {
        if (th == null) {
            autoCloseable.close();
            return;
        }
        try {
            autoCloseable.close();
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    private static boolean a(org.apache.commons.net.ftp.c cVar, String str, String str2) {
        boolean z;
        Path path = Paths.get(str, new String[0]);
        try {
            InputStream newInputStream = Files.newInputStream(path, new OpenOption[0]);
            cVar.fI(str2);
            z = cVar.a(path.getFileName().toString(), newInputStream);
            if (newInputStream != null) {
                try {
                    a(null, newInputStream);
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return z;
                }
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
        return z;
    }

    private static org.apache.commons.net.ftp.c lc() {
        org.apache.commons.net.ftp.c cVar = new org.apache.commons.net.ftp.c();
        org.apache.commons.net.ftp.d dVar = new org.apache.commons.net.ftp.d();
        dVar.fP("Asia/Shanghai");
        cVar.a(dVar);
        return cVar;
    }
}
