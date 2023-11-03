package com.amap.api.services.a;

import com.amap.api.services.a.bp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* compiled from: ThreadPool.java */
/* loaded from: classes11.dex */
public final class bo {
    private static bo fU = null;
    private ExecutorService b;
    private ConcurrentHashMap<bp, Future<?>> fV = new ConcurrentHashMap<>();
    private bp.a fW = new bp.a() { // from class: com.amap.api.services.a.bo.1
        @Override // com.amap.api.services.a.bp.a
        public void a(bp bpVar) {
        }

        @Override // com.amap.api.services.a.bp.a
        public void b(bp bpVar) {
            bo.this.a(bpVar, false);
        }
    };

    public static synchronized bo N(int i) {
        bo boVar;
        synchronized (bo.class) {
            if (fU == null) {
                fU = new bo(i);
            }
            boVar = fU;
        }
        return boVar;
    }

    private bo(int i) {
        try {
            this.b = Executors.newFixedThreadPool(i);
        } catch (Throwable th) {
            r.b(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(bp bpVar, boolean z) {
        try {
            Future<?> remove = this.fV.remove(bpVar);
            if (z && remove != null) {
                remove.cancel(true);
            }
        } finally {
        }
    }
}
