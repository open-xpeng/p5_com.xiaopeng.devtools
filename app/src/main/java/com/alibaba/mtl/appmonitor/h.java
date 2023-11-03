package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploadTask.java */
/* loaded from: classes11.dex */
public class h implements Runnable {
    private static Map<Integer, h> f;
    private static boolean j = false;
    private int d;
    private int e;
    private long startTime = System.currentTimeMillis();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init() {
        com.alibaba.mtl.appmonitor.a.f[] values;
        if (!j) {
            i.a("CommitTask", "init StatisticsAlarmEvent");
            f = new ConcurrentHashMap();
            for (com.alibaba.mtl.appmonitor.a.f fVar : com.alibaba.mtl.appmonitor.a.f.values()) {
                if (fVar.isOpen()) {
                    int a = fVar.a();
                    h hVar = new h(a, 1000 * fVar.v());
                    f.put(Integer.valueOf(a), hVar);
                    s.aW().a(u(a), hVar, hVar.d);
                }
            }
            j = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void destroy() {
        for (com.alibaba.mtl.appmonitor.a.f fVar : com.alibaba.mtl.appmonitor.a.f.values()) {
            s.aW().E(u(fVar.a()));
        }
        j = false;
        f = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(int i, int i2) {
        i.a("CommitTask", "[setStatisticsInterval] eventId" + i + " statisticsInterval:" + i2);
        synchronized (f) {
            h hVar = f.get(Integer.valueOf(i));
            if (hVar == null) {
                if (i2 > 0) {
                    h hVar2 = new h(i, 1000 * i2);
                    f.put(Integer.valueOf(i), hVar2);
                    i.a("CommitTask", "post next eventId" + i + ": uploadTask.interval " + hVar2.d);
                    s.aW().a(u(i), hVar2, (long) hVar2.d);
                }
            } else if (i2 > 0) {
                int i3 = i2 * 1000;
                if (hVar.d != i3) {
                    s.aW().E(u(i));
                    hVar.d = i3;
                    long currentTimeMillis = System.currentTimeMillis();
                    long j2 = hVar.d - (currentTimeMillis - hVar.startTime);
                    if (j2 < 0) {
                        j2 = 0;
                    }
                    i.a("CommitTask", hVar + "post next eventId" + i + " next:" + j2 + "  uploadTask.interval: " + hVar.d);
                    s.aW().a(u(i), hVar, j2);
                    hVar.startTime = currentTimeMillis;
                }
            } else {
                i.a("CommitTask", "uploadTasks.size:" + f.size());
                f.remove(Integer.valueOf(i));
                i.a("CommitTask", "uploadTasks.size:" + f.size());
            }
        }
    }

    private h(int i, int i2) {
        this.d = 180000;
        this.e = i;
        this.d = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        i.a("CommitTask", "check&commit event:", Integer.valueOf(this.e));
        com.alibaba.mtl.appmonitor.a.e.u().a(this.e);
        if (f.containsValue(this)) {
            this.startTime = System.currentTimeMillis();
            i.a("CommitTask", "next:" + this.e);
            s.aW().a(u(this.e), this, (long) this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d() {
        for (com.alibaba.mtl.appmonitor.a.f fVar : com.alibaba.mtl.appmonitor.a.f.values()) {
            com.alibaba.mtl.appmonitor.a.e.u().a(fVar.a());
        }
    }

    private static int u(int i) {
        if (i != 65133) {
            switch (i) {
                case 65501:
                    return 6;
                case 65502:
                    return 9;
                case 65503:
                    return 10;
                default:
                    return 0;
            }
        }
        return 11;
    }
}
