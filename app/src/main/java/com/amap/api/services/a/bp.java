package com.amap.api.services.a;

/* compiled from: ThreadTask.java */
/* loaded from: classes11.dex */
public abstract class bp implements Runnable {
    a fW;

    /* compiled from: ThreadTask.java */
    /* loaded from: classes11.dex */
    interface a {
        void a(bp bpVar);

        void b(bp bpVar);
    }

    public abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.fW != null) {
                this.fW.a(this);
            }
            if (Thread.interrupted()) {
                return;
            }
            a();
            if (!Thread.interrupted() && this.fW != null) {
                this.fW.b(this);
            }
        } catch (Throwable th) {
            r.b(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }
}
