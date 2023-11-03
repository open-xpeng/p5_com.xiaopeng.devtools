package com.alibaba.mtl.log.c;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: LogStoreMgr.java */
/* loaded from: classes11.dex */
public class c {
    private static c cL;
    private static int A = 0;
    private static final Object d = new Object();
    private List<com.alibaba.mtl.log.model.a> cN = new CopyOnWriteArrayList();
    private Runnable cO = new Runnable() { // from class: com.alibaba.mtl.log.c.c.1
        @Override // java.lang.Runnable
        public void run() {
            c.this.aB();
        }
    };
    private com.alibaba.mtl.log.c.a cM = new com.alibaba.mtl.log.c.b(com.alibaba.mtl.log.a.getContext());

    private c() {
        com.alibaba.mtl.log.f.a.ba().start();
        s.aW().b(new a());
    }

    public static synchronized c aA() {
        c cVar;
        synchronized (c.class) {
            if (cL == null) {
                cL = new c();
            }
            cVar = cL;
        }
        return cVar;
    }

    public void a(com.alibaba.mtl.log.model.a aVar) {
        i.a("LogStoreMgr", "[add] :", aVar.ec);
        com.alibaba.mtl.log.b.a.x(aVar.X);
        this.cN.add(aVar);
        if (this.cN.size() >= 100) {
            s.aW().E(1);
            s.aW().a(1, this.cO, 0L);
        } else if (!s.aW().F(1)) {
            s.aW().a(1, this.cO, 5000L);
        }
        synchronized (d) {
            A++;
            if (A > 5000) {
                A = 0;
                s.aW().b(new b());
            }
        }
    }

    public int d(List<com.alibaba.mtl.log.model.a> list) {
        i.a("LogStoreMgr", list);
        return this.cM.d(list);
    }

    public List<com.alibaba.mtl.log.model.a> g(String str, int i) {
        List<com.alibaba.mtl.log.model.a> g = this.cM.g(str, i);
        i.a("LogStoreMgr", "[get]", g);
        return g;
    }

    public synchronized void aB() {
        i.a("LogStoreMgr", "[store]");
        ArrayList arrayList = null;
        try {
            synchronized (this.cN) {
                if (this.cN.size() > 0) {
                    arrayList = new ArrayList(this.cN);
                    this.cN.clear();
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                this.cM.c(arrayList);
            }
        } catch (Throwable th) {
        }
    }

    public void clear() {
        i.a("LogStoreMgr", "[clear]");
        this.cM.clear();
        this.cN.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aC() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -3);
        this.cM.c("time", String.valueOf(calendar.getTimeInMillis()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(int i) {
        if (i > 9000) {
            this.cM.C((i - 9000) + 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LogStoreMgr.java */
    /* loaded from: classes11.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i.a("LogStoreMgr", "CleanLogTask");
            int az = c.this.cM.az();
            if (az > 9000) {
                c.this.C(az);
            }
        }
    }

    /* compiled from: LogStoreMgr.java */
    /* loaded from: classes11.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.aC();
            int az = c.this.cM.az();
            if (az > 9000) {
                c.this.C(az);
            }
        }
    }
}
