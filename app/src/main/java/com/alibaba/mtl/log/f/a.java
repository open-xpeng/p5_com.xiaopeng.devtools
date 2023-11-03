package com.alibaba.mtl.log.f;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;
import java.util.Random;

/* compiled from: UploadEngine.java */
/* loaded from: classes11.dex */
public class a {
    static a ed = new a();
    private int B;
    protected long z = com.alibaba.mtl.log.a.a.a();
    private boolean ee = false;

    public static a ba() {
        return ed;
    }

    public synchronized void start() {
        this.ee = true;
        if (s.aW().F(2)) {
            s.aW().E(2);
        }
        c();
        Random random = new Random();
        if (!b.isRunning()) {
            s.aW().a(2, new b() { // from class: com.alibaba.mtl.log.f.a.1
                @Override // com.alibaba.mtl.log.f.b
                public void bc() {
                    if (a.this.ee) {
                        com.alibaba.mtl.log.b.a.aw();
                        a.this.c();
                        i.a("UploadTask", "mPeriod:", Long.valueOf(a.this.z));
                        if (s.aW().F(2)) {
                            s.aW().E(2);
                        }
                        if (!b.isRunning()) {
                            s.aW().a(2, this, a.this.z);
                        }
                    }
                }

                @Override // com.alibaba.mtl.log.f.b
                public void bd() {
                    a.this.bb();
                }
            }, random.nextInt((int) this.z));
        }
    }

    public void bb() {
        if (this.B == 0) {
            this.B = 7000;
        } else {
            this.B = 0;
        }
    }

    public synchronized void stop() {
        this.ee = false;
        s.aW().E(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long c() {
        long a;
        i.a("UploadEngine", "UTDC.bBackground:", Boolean.valueOf(com.alibaba.mtl.log.a.bG), "AppInfoUtil.isForeground(UTDC.getContext()) ", Boolean.valueOf(com.alibaba.mtl.log.d.b.b(com.alibaba.mtl.log.a.getContext())));
        com.alibaba.mtl.log.a.bG = !com.alibaba.mtl.log.d.b.b(com.alibaba.mtl.log.a.getContext());
        boolean z = com.alibaba.mtl.log.a.bG;
        com.alibaba.mtl.log.a.a.a();
        if (z) {
            a = com.alibaba.mtl.log.a.a.b() + this.B;
        } else {
            a = com.alibaba.mtl.log.a.a.a() + this.B;
        }
        this.z = a;
        if (com.alibaba.mtl.log.a.a.e()) {
            this.z = 3000L;
        }
        return this.z;
    }
}
