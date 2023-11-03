package com.xiaopeng.devtools.system.c;

import com.xiaopeng.lib.utils.j;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/* compiled from: UDPClient.java */
/* loaded from: classes12.dex */
public class c {
    private DatagramSocket BG;
    private DatagramPacket BH;
    private DatagramPacket BI;
    private b BJ;
    private String BK;
    private InetAddress BL;
    private int uR;
    private boolean Bo = false;
    private a xM = null;
    private final com.xiaopeng.devtools.utils.b.a BF = new com.xiaopeng.devtools.utils.b.a(13107200);

    /* compiled from: UDPClient.java */
    /* loaded from: classes12.dex */
    public interface a {
        void d(byte[] bArr, int i);

        void jC();

        void jD();

        void jE();
    }

    public void y(byte[] bArr) {
        this.BF.returnBuf(bArr);
    }

    public void connect(String str, int i) {
        this.BK = str;
        this.uR = i;
        this.BJ = new b(this.BK, this.uR);
        this.BJ.start();
    }

    public boolean kX() {
        if (this.BG != null) {
            return this.BG.isConnected();
        }
        return false;
    }

    public void T(boolean z) {
        if (z) {
            this.Bo = z;
        }
        if (this.BG != null) {
            this.BG.close();
            this.BG = null;
        }
        if (this.BJ != null) {
            this.BJ.interrupt();
        }
    }

    public void z(final byte[] bArr) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.system.c.-$$Lambda$c$SuGt0O3PYRFNTcl_0Yv2QrB_8E0
            @Override // java.lang.Runnable
            public final void run() {
                c.this.A(bArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A(byte[] bArr) {
        try {
            if (this.Bo) {
                com.xiaopeng.lib.utils.c.i("UDPClient", "sendByteCmd it is already stopped");
                return;
            }
            if (!kX()) {
                connect(this.BK, this.uR);
                com.xiaopeng.devtools.system.b.c.sleep(1000L);
            }
            if (kX()) {
                this.BH = new DatagramPacket(bArr, bArr.length, this.BL, this.uR);
                if (this.BG != null) {
                    this.BG.send(this.BH);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(a aVar) {
        this.xM = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UDPClient.java */
    /* loaded from: classes12.dex */
    public class b extends Thread {
        private final String ip;
        private final int port;

        public b(String str, int i) {
            this.ip = str;
            this.port = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (c.this.Bo) {
                com.xiaopeng.lib.utils.c.i("UDPClient", "it is already stopped");
                return;
            }
            com.xiaopeng.lib.utils.c.g("UDPClient", "SocketThread start ");
            super.run();
            try {
                if (c.this.BG != null) {
                    c.this.BG.close();
                    c.this.BG = null;
                }
                c.this.BL = InetAddress.getByName(this.ip);
                c.this.BG = new DatagramSocket();
                c.this.BG.connect(c.this.BL, this.port);
                com.xiaopeng.lib.utils.c.g("UDPClient", "udp connect = " + c.this.kX());
                if (c.this.kX()) {
                    if (c.this.xM != null) {
                        c.this.xM.jC();
                    }
                    com.xiaopeng.lib.utils.c.g("UDPClient", "SocketThread connect over ");
                    while (c.this.kX() && !c.this.Bo && !isInterrupted()) {
                        try {
                            byte[] buf = c.this.BF.getBuf(65536);
                            c.this.BI = new DatagramPacket(buf, buf.length);
                            c.this.BG.receive(c.this.BI);
                            if (!c.this.BI.getAddress().equals(c.this.BL)) {
                                com.xiaopeng.lib.utils.c.i("UDPClient", "Received packet from an unknown source: " + c.this.BI.getAddress() + "wanted address : " + c.this.BL);
                                if (c.this.xM != null) {
                                    c.this.xM.jD();
                                }
                                c.this.T(false);
                                return;
                            }
                            int length = c.this.BI.getLength();
                            com.xiaopeng.lib.utils.c.g("UDPClient", "pre data size = " + c.this.BI.getData().length + ", value data size = " + length);
                            if (length > 0 && c.this.xM != null) {
                                c.this.xM.d(buf, length);
                            }
                            com.xiaopeng.lib.utils.c.g("UDPClient", "SocketThread read listening");
                        } catch (IOException e) {
                            com.xiaopeng.lib.utils.c.i("UDPClient", "SocketThread read io exception = " + e.getMessage());
                            e.printStackTrace();
                            c.this.T(false);
                            if (c.this.xM != null) {
                                c.this.xM.jE();
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                com.xiaopeng.lib.utils.c.i("UDPClient", "SocketThread connect fail");
                if (c.this.xM != null) {
                    c.this.xM.jD();
                }
                c.this.T(false);
            } catch (IOException e2) {
                com.xiaopeng.lib.utils.c.i("UDPClient", "SocketThread connect io exception = " + e2.getMessage());
                e2.printStackTrace();
                if (c.this.xM != null) {
                    c.this.xM.jD();
                }
                c.this.T(false);
            }
        }
    }
}
