package com.amap.api.services.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* compiled from: ManifestConfig.java */
/* loaded from: classes11.dex */
public class bt {
    public static l eN;
    private static bt fX;
    private static Context fh;
    private a fY;
    private HandlerThread fZ = new HandlerThread("manifestThread") { // from class: com.amap.api.services.a.bt.1
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0099, code lost:
            if (r9.ga.fY != null) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00b9, code lost:
            java.lang.Thread.sleep(10000);
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00bd, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00be, code lost:
            r0.printStackTrace();
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00c1, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:?, code lost:
            return;
         */
        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r9 = this;
                java.lang.String r0 = "run"
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                java.lang.String r2 = "ManifestConfigThread"
                r1.setName(r2)
                android.os.Message r1 = new android.os.Message
                r1.<init>()
                r2 = 3
                android.content.Context r3 = com.amap.api.services.a.bt.bC()     // Catch: java.lang.Throwable -> L9e
                r4 = 0
                com.amap.api.services.a.l r5 = com.amap.api.services.a.bq.l(r4)     // Catch: java.lang.Throwable -> L9e
                java.lang.String r6 = "11K;001"
                r7 = 0
                com.amap.api.services.a.e$a r3 = com.amap.api.services.a.e.a(r3, r5, r6, r7)     // Catch: java.lang.Throwable -> L9e
                if (r3 == 0) goto L36
                com.amap.api.services.a.e$a$a r5 = r3.ez     // Catch: java.lang.Throwable -> L9e
                if (r5 == 0) goto L36
                com.amap.api.services.a.bu r5 = new com.amap.api.services.a.bu     // Catch: java.lang.Throwable -> L9e
                com.amap.api.services.a.e$a$a r6 = r3.ez     // Catch: java.lang.Throwable -> L9e
                boolean r6 = r6.b     // Catch: java.lang.Throwable -> L9e
                com.amap.api.services.a.e$a$a r8 = r3.ez     // Catch: java.lang.Throwable -> L9e
                boolean r8 = r8.a     // Catch: java.lang.Throwable -> L9e
                r5.<init>(r6, r8)     // Catch: java.lang.Throwable -> L9e
                r1.obj = r5     // Catch: java.lang.Throwable -> L9e
            L36:
                if (r3 == 0) goto L91
                com.amap.api.services.a.e$a$d r5 = r3.eA     // Catch: java.lang.Throwable -> L9e
                if (r5 == 0) goto L91
                com.amap.api.services.a.e$a$d r3 = r3.eA     // Catch: java.lang.Throwable -> L9e
                if (r3 == 0) goto L81
                java.lang.String r5 = r3.b     // Catch: java.lang.Throwable -> L9e
                java.lang.String r6 = r3.a     // Catch: java.lang.Throwable -> L9e
                java.lang.String r3 = r3.c     // Catch: java.lang.Throwable -> L9e
                boolean r8 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L9e
                if (r8 != 0) goto L6f
                boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L9e
                if (r8 != 0) goto L6f
                boolean r8 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L9e
                if (r8 == 0) goto L59
                goto L6f
            L59:
                com.amap.api.services.a.al r7 = new com.amap.api.services.a.al     // Catch: java.lang.Throwable -> L9e
                r7.<init>(r6, r5, r3)     // Catch: java.lang.Throwable -> L9e
                com.amap.api.services.a.ak r3 = new com.amap.api.services.a.ak     // Catch: java.lang.Throwable -> L9e
                android.content.Context r5 = com.amap.api.services.a.bt.bC()     // Catch: java.lang.Throwable -> L9e
                com.amap.api.services.a.l r4 = com.amap.api.services.a.bq.l(r4)     // Catch: java.lang.Throwable -> L9e
                r3.<init>(r5, r7, r4)     // Catch: java.lang.Throwable -> L9e
                r3.a()     // Catch: java.lang.Throwable -> L9e
                goto L80
            L6f:
                com.amap.api.services.a.ak r3 = new com.amap.api.services.a.ak     // Catch: java.lang.Throwable -> L9e
                android.content.Context r5 = com.amap.api.services.a.bt.bC()     // Catch: java.lang.Throwable -> L9e
                com.amap.api.services.a.l r4 = com.amap.api.services.a.bq.l(r4)     // Catch: java.lang.Throwable -> L9e
                r3.<init>(r5, r7, r4)     // Catch: java.lang.Throwable -> L9e
                r3.a()     // Catch: java.lang.Throwable -> L9e
            L80:
                goto L91
            L81:
                com.amap.api.services.a.ak r3 = new com.amap.api.services.a.ak     // Catch: java.lang.Throwable -> L9e
                android.content.Context r5 = com.amap.api.services.a.bt.bC()     // Catch: java.lang.Throwable -> L9e
                com.amap.api.services.a.l r4 = com.amap.api.services.a.bq.l(r4)     // Catch: java.lang.Throwable -> L9e
                r3.<init>(r5, r7, r4)     // Catch: java.lang.Throwable -> L9e
                r3.a()     // Catch: java.lang.Throwable -> L9e
            L91:
                r1.what = r2
                com.amap.api.services.a.bt r0 = com.amap.api.services.a.bt.this
                com.amap.api.services.a.bt$a r0 = com.amap.api.services.a.bt.a(r0)
                if (r0 == 0) goto Lb7
                goto Lae
            L9c:
                r0 = move-exception
                goto Lc2
            L9e:
                r3 = move-exception
                java.lang.String r4 = "ManifestConfig"
                com.amap.api.services.a.br.a(r3, r4, r0)     // Catch: java.lang.Throwable -> L9c
                r1.what = r2
                com.amap.api.services.a.bt r0 = com.amap.api.services.a.bt.this
                com.amap.api.services.a.bt$a r0 = com.amap.api.services.a.bt.a(r0)
                if (r0 == 0) goto Lb7
            Lae:
                com.amap.api.services.a.bt r0 = com.amap.api.services.a.bt.this
                com.amap.api.services.a.bt$a r0 = com.amap.api.services.a.bt.a(r0)
                r0.sendMessage(r1)
            Lb7:
                r0 = 10000(0x2710, double:4.9407E-320)
                java.lang.Thread.sleep(r0)     // Catch: java.lang.InterruptedException -> Lbd
                goto Lc1
            Lbd:
                r0 = move-exception
                r0.printStackTrace()
            Lc1:
                return
            Lc2:
                r1.what = r2
                com.amap.api.services.a.bt r2 = com.amap.api.services.a.bt.this
                com.amap.api.services.a.bt$a r2 = com.amap.api.services.a.bt.a(r2)
                if (r2 == 0) goto Ld5
                com.amap.api.services.a.bt r2 = com.amap.api.services.a.bt.this
                com.amap.api.services.a.bt$a r2 = com.amap.api.services.a.bt.a(r2)
                r2.sendMessage(r1)
            Ld5:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bt.AnonymousClass1.run():void");
        }
    };

    private bt(Context context) {
        fh = context;
        eN = bq.l(false);
        try {
            this.fY = new a(Looper.getMainLooper());
            this.fZ.start();
        } catch (Throwable th) {
            br.a(th, "ManifestConfig", "ManifestConfig");
        }
    }

    public static bt R(Context context) {
        if (fX == null) {
            fX = new bt(context);
        }
        return fX;
    }

    /* compiled from: ManifestConfig.java */
    /* loaded from: classes11.dex */
    class a extends Handler {
        String a;

        public a(Looper looper) {
            super(looper);
            this.a = "handleMessage";
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message != null && message.what == 3) {
                try {
                    bu buVar = (bu) message.obj;
                    if (buVar == null) {
                        buVar = new bu(false, false);
                    }
                    r.a(bt.fh, bq.l(buVar.a()));
                    bt.eN = bq.l(buVar.a());
                } catch (Throwable th) {
                    br.a(th, "ManifestConfig", this.a);
                }
            }
        }
    }
}
