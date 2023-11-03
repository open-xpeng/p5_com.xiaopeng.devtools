package com.xiaopeng.devtools.system.c;

import com.alibaba.sdk.android.man.util.MANConfig;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.utils.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* compiled from: TcpClientConnector.java */
/* loaded from: classes12.dex */
public class b {
    private Socket BA;
    private String BB;
    private int BC;
    private OutputStream BD;
    byte[] BE = new byte[4096];
    private com.xiaopeng.devtools.utils.b.a BF = new com.xiaopeng.devtools.utils.b.a(67108864);
    private InputStream mInputStream;

    public b(String str, int i) {
        this.BB = str;
        this.BC = i;
    }

    public byte[] cB(int i) throws IOException {
        byte[] bArr;
        kW();
        if (this.mInputStream == null) {
            this.mInputStream = this.BA.getInputStream();
        }
        if (MyApplication.eZ()) {
            com.xiaopeng.lib.utils.c.f("TcpClientConnector", "to read from SerIP: " + this.BB + "SerPort: " + this.BC + " length: " + i);
        }
        if (this.mInputStream != null) {
            bArr = this.BF.getBuf(i);
            int i2 = 10;
            int i3 = 0;
            while (i > 0) {
                int read = this.mInputStream.read(this.BE, 0, Math.min(4096, i));
                if (read != -1) {
                    d.a(bArr, i3, this.BE, read);
                    i3 += read;
                    i -= read;
                } else {
                    int i4 = i2 - 1;
                    if (i2 <= 0) {
                        return null;
                    }
                    com.xiaopeng.lib.utils.c.f("TcpClientConnector", "read garbage from SerIP: " + this.BB + "SerPort: " + this.BC);
                    i2 = i4;
                }
            }
        } else {
            com.xiaopeng.lib.utils.c.d("TcpClientConnector", "send", "mInputStream is null");
            bArr = null;
        }
        if (MyApplication.eZ()) {
            StringBuilder sb = new StringBuilder();
            sb.append("successfully read from SerIP: ");
            sb.append(this.BB);
            sb.append("SerPort: ");
            sb.append(this.BC);
            sb.append(" ret.length: ");
            sb.append(bArr != null ? bArr.length : 0);
            sb.append(" ret: ");
            sb.append(d.a(bArr, true));
            com.xiaopeng.lib.utils.c.f("TcpClientConnector", sb.toString());
        }
        return bArr;
    }

    public void x(byte[] bArr) throws IOException {
        if (MyApplication.eZ()) {
            com.xiaopeng.lib.utils.c.f("TcpClientConnector", "send to SerIP: " + this.BB + "SerPort: " + this.BC + " data: " + bArr);
        }
        kW();
        if (this.BD == null) {
            this.BD = this.BA.getOutputStream();
        }
        if (this.BD != null && bArr != null) {
            this.BD.write(bArr);
            if (MyApplication.eZ()) {
                com.xiaopeng.lib.utils.c.g("TcpClientConnector", "sent");
                return;
            }
            return;
        }
        com.xiaopeng.lib.utils.c.d("TcpClientConnector", "send", "mOutput is null");
    }

    private void kW() throws IOException {
        if (this.BA == null) {
            com.xiaopeng.lib.utils.c.f("TcpClientConnector", "connect socket SerIP: " + this.BB + "SerPort: " + this.BC);
            this.BA = new Socket(this.BB, this.BC);
            this.BA.setReceiveBufferSize(10485760);
            this.BA.setTcpNoDelay(true);
            this.BA.setSoTimeout(MANConfig.AGGREGATION_INTERVAL);
        }
    }

    public void y(byte[] bArr) {
        this.BF.returnBuf(bArr);
    }

    public void disconnect() {
        com.xiaopeng.lib.utils.c.g("TcpClientConnector", "disconnect socket SerIP: " + this.BB + "SerPort: " + this.BC);
        try {
            if (this.mInputStream != null) {
                this.mInputStream.close();
                this.mInputStream = null;
            }
            if (this.BD != null) {
                this.BD.flush();
                this.BD.close();
                this.BD = null;
            }
            if (this.BA != null) {
                this.BA.close();
                this.BA = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
