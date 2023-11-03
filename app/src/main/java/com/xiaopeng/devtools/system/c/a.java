package com.xiaopeng.devtools.system.c;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.d;
import org.apache.commons.net.ftp.j;

/* compiled from: FtpClientConnector.java */
/* loaded from: classes12.dex */
public class a {
    private org.apache.commons.net.ftp.c Bz;

    public a(String str, int i, String str2, String str3) {
        try {
            com.xiaopeng.lib.utils.c.g("FtpClientConnector", "generate FtpClientConnector");
            destroy();
            this.Bz = a(str, i, str2, str3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        if (this.Bz != null) {
            try {
                this.Bz.uG();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (this.Bz.isConnected()) {
                    this.Bz.disconnect();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean o(String str, String str2, String str3) {
        FTPFile[] uO;
        try {
            if (kV()) {
                this.Bz.fI(str);
                this.Bz.uH();
                boolean z = false;
                for (FTPFile fTPFile : this.Bz.uO()) {
                    if (str3 == null || fTPFile.getName().equals(str3)) {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(str2 + File.separator + fTPFile.getName()));
                        z = this.Bz.a(new String(fTPFile.getName().getBytes("UTF-8"), "ISO-8859-1"), fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        if (!z) {
                            com.xiaopeng.lib.utils.c.i("FtpClientConnector", "ftp download " + fTPFile.getName() + " fail");
                        }
                        if (str3 != null) {
                            break;
                        }
                    }
                }
                return z;
            }
            com.xiaopeng.lib.utils.c.i("FtpClientConnector", "mFTPClient is not available");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean kV() {
        return this.Bz != null && this.Bz.isAvailable();
    }

    private org.apache.commons.net.ftp.c a(String str, int i, String str2, String str3) throws IOException {
        org.apache.commons.net.ftp.c cVar = new org.apache.commons.net.ftp.c();
        cVar.setConnectTimeout(60000);
        cVar.fx(60000);
        d dVar = new d();
        dVar.fP("Asia/Shanghai");
        cVar.a(dVar);
        if (i != -1) {
            cVar.connect(str, i);
        } else {
            cVar.connect(str);
        }
        int ut = cVar.ut();
        if (!j.fB(ut)) {
            cVar.disconnect();
            throw new IOException("connect fail: " + ut);
        }
        cVar.az(str2, str3);
        int ut2 = cVar.ut();
        if (!j.fB(ut2)) {
            cVar.disconnect();
            throw new IOException("login fail: " + ut2);
        }
        cVar.bE(false);
        cVar.fy(2);
        cVar.setSendBufferSize(10485760);
        cVar.setTcpNoDelay(true);
        cVar.setKeepAlive(true);
        return cVar;
    }
}
