package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CleanTask.java */
/* loaded from: classes11.dex */
public class g implements Runnable {
    private static g aW;
    private static boolean j = false;
    private static long a = 300000;

    private g() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init() {
        if (!j) {
            i.a("CleanTask", "init TimeoutEventManager");
            aW = new g();
            s.aW().a(5, aW, a);
            j = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void destroy() {
        s.aW().E(5);
        j = false;
        aW = null;
    }

    @Override // java.lang.Runnable
    public void run() {
        i.a("CleanTask", "clean TimeoutEvent");
        com.alibaba.mtl.appmonitor.a.e.u().g();
        s.aW().a(5, aW, a);
    }
}
