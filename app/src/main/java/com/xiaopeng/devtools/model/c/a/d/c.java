package com.xiaopeng.devtools.model.c.a.d;

import android.os.Handler;
import android.os.HandlerThread;
import java.net.DatagramSocket;

/* compiled from: UDPClient.java */
/* loaded from: classes12.dex */
public class c {
    private static final byte[] tk = {88, 1, 16};
    private static final byte[] tl = {88, 3};
    private static final byte[] tm = {88, 0};
    private static final byte[] tn = {-75, 98, 6, 35, 44, 0, -1, -1, 76, 102, -64, 0, 0, 0, 0, 0, 5, 24, 12, 0, 0, 0, 0, 0, 75, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -92, -13};
    private static final byte[] to = {-75, 98, 6, 35, 44, 0, -1, -1, 76, 102, -64, 0, 0, 0, 0, 0, 5, 24, 12, 0, 0, 0, 0, 0, 75, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, -91, -8};
    private static c tp;
    private Handler mHandler;
    private HandlerThread mThread;
    private DatagramSocket tq;
    private a tr;
    private boolean mIsStarted = false;
    private int tt = -1;
    private int tu = 0;
    private int tv = 0;
    private Runnable tw = new Runnable() { // from class: com.xiaopeng.devtools.model.c.a.d.c.1
        /* JADX WARN: Code restructure failed: missing block: B:47:0x01f8, code lost:
            r0 = r15.getData();
            r3 = new java.lang.String(r15.getData(), r14, r15.getLength());
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x020d, code lost:
            if (2 != r15.getLength()) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0217, code lost:
            if (com.xiaopeng.devtools.model.c.a.d.c.tm[r14] != r0[r14]) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x0221, code lost:
            if (com.xiaopeng.devtools.model.c.a.d.c.tm[1] != r0[1]) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x0223, code lost:
            r22.tx.tq.send(new java.net.DatagramPacket(com.xiaopeng.devtools.model.c.a.d.c.tm, 0, 2, r7, 29946));
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x023f, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0240, code lost:
            r0.printStackTrace();
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x0249, code lost:
            if (android.text.TextUtils.isEmpty(r3) != false) goto L145;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0251, code lost:
            if (r22.tx.tr == null) goto L145;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0253, code lost:
            r0 = r3.split(",");
            r8 = new float[3];
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x025c, code lost:
            if (r0 == null) goto L144;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x025f, code lost:
            if (r0.length <= 0) goto L143;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0261, code lost:
            r9 = java.lang.Integer.parseInt(r0[r14]);
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x0268, code lost:
            if (r9 == 1) goto L126;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x026b, code lost:
            if (r9 == 4) goto L113;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x026f, code lost:
            if (r9 == 1000) goto L99;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x0274, code lost:
            if (r0.length <= 1) goto L111;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x0276, code lost:
            r0 = java.lang.Integer.parseInt(r0[1]);
            r8 = r0 & 1;
            r0 = (r0 >> 1) & r4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x0286, code lost:
            if (r22.tx.tr == null) goto L106;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0288, code lost:
            r22.tx.tr.o(r8, r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0293, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0294, code lost:
            r0.printStackTrace();
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x029b, code lost:
            if (r0.length <= 5) goto L125;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x029d, code lost:
            r8[r14] = java.lang.Float.parseFloat(r0[1]);
            r8[1] = java.lang.Float.parseFloat(r0[2]);
            r8[2] = java.lang.Float.parseFloat(r0[3]);
            r9 = java.lang.Long.parseLong(r0[4]);
            r0 = java.lang.Float.parseFloat(r0[5]);
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x02c8, code lost:
            if (r22.tx.tr == null) goto L120;
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x02ca, code lost:
            r22.tx.tr.a(r8, r9, r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x02d4, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x02d5, code lost:
            r0.printStackTrace();
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x02db, code lost:
            if (r0.length <= 4) goto L138;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x02dd, code lost:
            r8[r14] = java.lang.Float.parseFloat(r0[1]);
            r8[1] = java.lang.Float.parseFloat(r0[2]);
            r8[2] = java.lang.Float.parseFloat(r0[3]);
            r9 = java.lang.Long.parseLong(r0[4]);
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0302, code lost:
            if (r22.tx.tr == null) goto L133;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x0304, code lost:
            r22.tx.tr.a(r8, r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x030e, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x030f, code lost:
            r0.printStackTrace();
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x0313, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x0314, code lost:
            r0.printStackTrace();
         */
        /* JADX WARN: Removed duplicated region for block: B:186:0x0470 A[SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 1144
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.model.c.a.d.c.AnonymousClass1.run():void");
        }
    };

    /* compiled from: UDPClient.java */
    /* loaded from: classes12.dex */
    public interface a {
        void a(float[] fArr, long j);

        void a(float[] fArr, long j, float f);

        void o(int i, int i2);
    }

    static /* synthetic */ int d(c cVar) {
        int i = cVar.tu;
        cVar.tu = i + 1;
        return i;
    }

    public static c gE() {
        if (tp == null) {
            com.xiaopeng.lib.utils.c.f("UDPClient", "create UDPClient Instance");
            tp = new c();
        }
        return tp;
    }

    public void a(a aVar) {
        this.tr = aVar;
    }

    public void open() {
        if (this.mIsStarted) {
            return;
        }
        this.mThread = new HandlerThread("UDP");
        this.mThread.start();
        this.mHandler = new Handler(this.mThread.getLooper());
        this.mIsStarted = true;
        com.xiaopeng.lib.utils.c.f("UDPClient", "Try to open UDP socket");
        this.mHandler.post(this.tw);
    }

    public void close() {
        if (this.mIsStarted) {
            this.mIsStarted = false;
            this.tq.close();
            this.mHandler.removeCallbacks(this.tw);
            this.mThread.quitSafely();
            this.tr = null;
        }
    }
}
