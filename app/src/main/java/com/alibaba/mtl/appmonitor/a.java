package com.alibaba.mtl.appmonitor;

import android.app.Application;
import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.l;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: AppMonitorDelegate.java */
/* loaded from: classes11.dex */
public final class a {
    private static Application ax;
    public static boolean aw = false;
    static volatile boolean i = false;

    public static synchronized void a(Application application) {
        synchronized (a.class) {
            i.a("AppMonitorDelegate", "start init");
            if (!i) {
                ax = application;
                com.alibaba.mtl.log.a.a(application.getApplicationContext());
                g.init();
                h.init();
                f.a(application);
                l.b(application.getApplicationContext());
                i = true;
            }
        }
    }

    public static synchronized void destroy() {
        synchronized (a.class) {
            try {
                i.a("AppMonitorDelegate", "start destory");
                if (i) {
                    h.d();
                    h.destroy();
                    g.destroy();
                    if (ax != null) {
                        l.c(ax.getApplicationContext());
                    }
                    i = false;
                }
            } finally {
            }
        }
    }

    public static synchronized void p() {
        synchronized (a.class) {
            try {
                i.a("AppMonitorDelegate", "triggerUpload");
                if (i && com.alibaba.mtl.log.a.a.f()) {
                    h.d();
                }
            } finally {
            }
        }
    }

    public static void f(int i2) {
        com.alibaba.mtl.appmonitor.a.f[] values;
        for (com.alibaba.mtl.appmonitor.a.f fVar : com.alibaba.mtl.appmonitor.a.f.values()) {
            fVar.f(i2);
            a(fVar, i2);
        }
    }

    public static void h(int i2) {
        com.alibaba.mtl.appmonitor.a.f[] values;
        i.a("AppMonitorDelegate", "[setSampling]");
        for (com.alibaba.mtl.appmonitor.a.f fVar : com.alibaba.mtl.appmonitor.a.f.values()) {
            fVar.c(i2);
            j.A().b(fVar, i2);
        }
    }

    public static void f(boolean z) {
        i.a("AppMonitorDelegate", "[enableLog]");
        i.d(z);
    }

    public static void a(String str, String str2, MeasureSet measureSet) {
        a(str, str2, measureSet, (DimensionSet) null);
    }

    public static void a(String str, String str2, MeasureSet measureSet, boolean z) {
        b(str, str2, measureSet, null, z);
    }

    public static void a(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet) {
        b(str, str2, measureSet, dimensionSet, false);
    }

    public static void b(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) {
        try {
            if (i) {
                if (!com.alibaba.mtl.appmonitor.f.b.d(str) && !com.alibaba.mtl.appmonitor.f.b.d(str2)) {
                    com.alibaba.mtl.appmonitor.model.c.Y().a(new com.alibaba.mtl.appmonitor.model.b(str, str2, measureSet, dimensionSet, z));
                    return;
                }
                i.a("AppMonitorDelegate", "register stat event. module: ", str, " monitorPoint: ", str2);
                if (aw) {
                    throw new com.alibaba.mtl.appmonitor.b.a("register error. module and monitorPoint can't be null");
                }
            }
        } catch (Throwable th) {
            com.alibaba.mtl.appmonitor.b.b.m16a(th);
        }
    }

    /* compiled from: AppMonitorDelegate.java */
    /* renamed from: com.alibaba.mtl.appmonitor.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0012a {
        public static void f(int i) {
            com.alibaba.mtl.appmonitor.a.f.ALARM.f(i);
            a.a(com.alibaba.mtl.appmonitor.a.f.ALARM, i);
        }

        public static void h(int i) {
            j.A().b(com.alibaba.mtl.appmonitor.a.f.ALARM, i);
        }

        @Deprecated
        public static boolean j(String str, String str2) {
            return j.a(com.alibaba.mtl.appmonitor.a.f.ALARM, str, str2);
        }

        public static void a(String str, String str2, Map<String, String> map) {
            c(str, str2, null, map);
        }

        public static void c(String str, String str2, String str3, Map<String, String> map) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    com.alibaba.mtl.log.b.a.au();
                    if (a.i && com.alibaba.mtl.log.a.a.f() && com.alibaba.mtl.appmonitor.a.f.ALARM.isOpen() && (a.aw || j.a(str, str2, (Boolean) true, (Map<String, String>) null))) {
                        i.a("AppMonitorDelegate", "commitSuccess module:", str, " monitorPoint:", str2);
                        com.alibaba.mtl.log.b.a.av();
                        com.alibaba.mtl.appmonitor.a.e.u().a(com.alibaba.mtl.appmonitor.a.f.ALARM.a(), str, str2, str3, map);
                    } else {
                        i.a("log discard !", "");
                    }
                    return;
                }
                i.a("AppMonitorDelegate", "module & monitorPoint must not null");
            } catch (Throwable th) {
                com.alibaba.mtl.appmonitor.b.b.m16a(th);
            }
        }

        public static void a(String str, String str2, String str3, String str4, Map<String, String> map) {
            a(str, str2, null, str3, str4, map);
        }

        public static void a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    com.alibaba.mtl.log.b.a.au();
                    HashMap hashMap = new HashMap();
                    hashMap.put("_status", "0");
                    if (a.i && com.alibaba.mtl.log.a.a.f() && com.alibaba.mtl.appmonitor.a.f.ALARM.isOpen() && (a.aw || j.a(str, str2, (Boolean) false, (Map<String, String>) hashMap))) {
                        i.a("AppMonitorDelegate", "commitFail module:", str, " monitorPoint:", str2, " errorCode:", str4, "errorMsg:", str5);
                        com.alibaba.mtl.log.b.a.av();
                        com.alibaba.mtl.appmonitor.a.e.u().a(com.alibaba.mtl.appmonitor.a.f.ALARM.a(), str, str2, str3, str4, str5, map);
                    } else {
                        i.a("log discard !", "");
                    }
                    return;
                }
                i.a("AppMonitorDelegate", "module & monitorPoint must not null");
            } catch (Throwable th) {
                com.alibaba.mtl.appmonitor.b.b.m16a(th);
            }
        }
    }

    /* compiled from: AppMonitorDelegate.java */
    /* loaded from: classes11.dex */
    public static class b {
        public static void f(int i) {
            com.alibaba.mtl.appmonitor.a.f.COUNTER.f(i);
            a.a(com.alibaba.mtl.appmonitor.a.f.COUNTER, i);
        }

        public static void h(int i) {
            j.A().b(com.alibaba.mtl.appmonitor.a.f.COUNTER, i);
        }

        @Deprecated
        public static boolean j(String str, String str2) {
            return j.a(com.alibaba.mtl.appmonitor.a.f.COUNTER, str, str2);
        }

        public static void a(String str, String str2, double d, Map<String, String> map) {
            a(str, str2, null, d, map);
        }

        public static void a(String str, String str2, String str3, double d, Map<String, String> map) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    com.alibaba.mtl.log.b.a.as();
                    if (a.i && com.alibaba.mtl.log.a.a.f() && com.alibaba.mtl.appmonitor.a.f.COUNTER.isOpen() && (a.aw || j.a(com.alibaba.mtl.appmonitor.a.f.COUNTER, str, str2))) {
                        i.a("AppMonitorDelegate", "commitCount module: ", str, " monitorPoint: ", str2, " value: ", Double.valueOf(d));
                        com.alibaba.mtl.log.b.a.at();
                        com.alibaba.mtl.appmonitor.a.e.u().a(com.alibaba.mtl.appmonitor.a.f.COUNTER.a(), str, str2, str3, d, map);
                    }
                    return;
                }
                i.a("AppMonitorDelegate", "module & monitorPoint must not null");
            } catch (Throwable th) {
                com.alibaba.mtl.appmonitor.b.b.m16a(th);
            }
        }
    }

    /* compiled from: AppMonitorDelegate.java */
    /* loaded from: classes11.dex */
    public static class c {
        public static void f(int i) {
            com.alibaba.mtl.appmonitor.a.f.OFFLINE_COUNTER.f(i);
            a.a(com.alibaba.mtl.appmonitor.a.f.OFFLINE_COUNTER, i);
        }

        public static void h(int i) {
            j.A().b(com.alibaba.mtl.appmonitor.a.f.OFFLINE_COUNTER, i);
        }

        @Deprecated
        public static boolean j(String str, String str2) {
            return j.a(com.alibaba.mtl.appmonitor.a.f.OFFLINE_COUNTER, str, str2);
        }

        public static void a(String str, String str2, double d) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    com.alibaba.mtl.log.b.a.aq();
                    if (a.i && com.alibaba.mtl.log.a.a.f() && com.alibaba.mtl.appmonitor.a.f.OFFLINE_COUNTER.isOpen() && (a.aw || j.a(com.alibaba.mtl.appmonitor.a.f.OFFLINE_COUNTER, str, str2))) {
                        i.a("AppMonitorDelegate", "commitOffLineCount module: ", str, " monitorPoint: ", str2, " value: ", Double.valueOf(d));
                        com.alibaba.mtl.log.b.a.ar();
                        com.alibaba.mtl.appmonitor.a.e.u().a(com.alibaba.mtl.appmonitor.a.f.OFFLINE_COUNTER.a(), str, str2, (String) null, d, (Map<String, String>) null);
                    }
                    return;
                }
                i.a("AppMonitorDelegate", "module & monitorPoint must not null");
            } catch (Throwable th) {
                com.alibaba.mtl.appmonitor.b.b.m16a(th);
            }
        }
    }

    /* compiled from: AppMonitorDelegate.java */
    /* loaded from: classes11.dex */
    public static class d {
        public static void f(int i) {
            com.alibaba.mtl.appmonitor.a.f.STAT.f(i);
            a.a(com.alibaba.mtl.appmonitor.a.f.STAT, i);
        }

        public static void h(int i) {
            j.A().b(com.alibaba.mtl.appmonitor.a.f.STAT, i);
        }

        @Deprecated
        public static boolean j(String str, String str2) {
            return j.a(com.alibaba.mtl.appmonitor.a.f.STAT, str, str2);
        }

        public static void c(String str, String str2, String str3) {
            try {
                if (a.i && com.alibaba.mtl.log.a.a.f() && com.alibaba.mtl.appmonitor.a.f.STAT.isOpen()) {
                    if (a.aw || j.a(com.alibaba.mtl.appmonitor.a.f.STAT, str, str2)) {
                        i.a("AppMonitorDelegate", "statEvent begin. module: ", str, " monitorPoint: ", str2, " measureName: ", str3);
                        com.alibaba.mtl.appmonitor.a.e.u().a(Integer.valueOf(com.alibaba.mtl.appmonitor.a.f.STAT.a()), str, str2, str3);
                    }
                }
            } catch (Throwable th) {
                com.alibaba.mtl.appmonitor.b.b.m16a(th);
            }
        }

        public static void d(String str, String str2, String str3) {
            try {
                if (a.i && com.alibaba.mtl.log.a.a.f() && com.alibaba.mtl.appmonitor.a.f.STAT.isOpen()) {
                    if (a.aw || j.a(com.alibaba.mtl.appmonitor.a.f.STAT, str, str2)) {
                        i.a("AppMonitorDelegate", "statEvent end. module: ", str, " monitorPoint: ", str2, " measureName: ", str3);
                        com.alibaba.mtl.appmonitor.a.e.u().a(str, str2, str3);
                    }
                }
            } catch (Throwable th) {
                com.alibaba.mtl.appmonitor.b.b.m16a(th);
            }
        }

        public static void a(String str, String str2, double d, Map<String, String> map) {
            a(str, str2, (DimensionValueSet) null, d, map);
        }

        public static void a(String str, String str2, DimensionValueSet dimensionValueSet, double d, Map<String, String> map) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    com.alibaba.mtl.log.b.a.ao();
                    if (a.i && com.alibaba.mtl.log.a.a.f() && com.alibaba.mtl.appmonitor.a.f.STAT.isOpen() && (a.aw || j.a(com.alibaba.mtl.appmonitor.a.f.STAT, str, str2))) {
                        i.a("AppMonitorDelegate", "statEvent commit. module: ", str, " monitorPoint: ", str2);
                        com.alibaba.mtl.appmonitor.model.b p = com.alibaba.mtl.appmonitor.model.c.Y().p(str, str2);
                        com.alibaba.mtl.log.b.a.ap();
                        if (p != null) {
                            List<Measure> M = p.W().M();
                            if (M.size() == 1) {
                                a(str, str2, dimensionValueSet, ((MeasureValueSet) com.alibaba.mtl.appmonitor.c.a.y().a(MeasureValueSet.class, new Object[0])).a(M.get(0).getName(), d), map);
                            }
                        }
                    }
                    return;
                }
                i.a("AppMonitorDelegate", "module & monitorPoint must not null");
            } catch (Throwable th) {
                com.alibaba.mtl.appmonitor.b.b.m16a(th);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x003a, code lost:
            if (com.alibaba.mtl.appmonitor.d.j.a(com.alibaba.mtl.appmonitor.a.f.STAT, r10, r11, r12 != null ? r12.getMap() : null) != false) goto L21;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static void a(java.lang.String r10, java.lang.String r11, com.alibaba.mtl.appmonitor.model.DimensionValueSet r12, com.alibaba.mtl.appmonitor.model.MeasureValueSet r13, java.util.Map<java.lang.String, java.lang.String> r14) {
            /*
                boolean r1 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L81
                if (r1 != 0) goto L79
                boolean r1 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> L81
                if (r1 == 0) goto Le
                goto L79
            Le:
                com.alibaba.mtl.log.b.a.ao()     // Catch: java.lang.Throwable -> L81
                boolean r1 = com.alibaba.mtl.appmonitor.a.i     // Catch: java.lang.Throwable -> L81
                r2 = 3
                r3 = 2
                r5 = 1
                r6 = 0
                r7 = 4
                if (r1 == 0) goto L65
                boolean r1 = com.alibaba.mtl.log.a.a.f()     // Catch: java.lang.Throwable -> L81
                if (r1 == 0) goto L65
                com.alibaba.mtl.appmonitor.a.f r1 = com.alibaba.mtl.appmonitor.a.f.STAT     // Catch: java.lang.Throwable -> L81
                boolean r1 = r1.isOpen()     // Catch: java.lang.Throwable -> L81
                if (r1 == 0) goto L65
                boolean r1 = com.alibaba.mtl.appmonitor.a.aw     // Catch: java.lang.Throwable -> L81
                if (r1 != 0) goto L3c
                com.alibaba.mtl.appmonitor.a.f r1 = com.alibaba.mtl.appmonitor.a.f.STAT     // Catch: java.lang.Throwable -> L81
                if (r12 == 0) goto L35
                java.util.Map r9 = r12.getMap()     // Catch: java.lang.Throwable -> L81
                goto L36
            L35:
                r9 = 0
            L36:
                boolean r1 = com.alibaba.mtl.appmonitor.d.j.a(r1, r10, r11, r9)     // Catch: java.lang.Throwable -> L81
                if (r1 == 0) goto L65
            L3c:
                java.lang.String r1 = "statEvent commit success"
                java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L81
                java.lang.String r9 = "statEvent commit. module: "
                r7[r6] = r9     // Catch: java.lang.Throwable -> L81
                r7[r5] = r10     // Catch: java.lang.Throwable -> L81
                java.lang.String r5 = " monitorPoint: "
                r7[r3] = r5     // Catch: java.lang.Throwable -> L81
                r7[r2] = r11     // Catch: java.lang.Throwable -> L81
                com.alibaba.mtl.log.d.i.a(r1, r7)     // Catch: java.lang.Throwable -> L81
                com.alibaba.mtl.log.b.a.ap()     // Catch: java.lang.Throwable -> L81
                com.alibaba.mtl.appmonitor.a.e r1 = com.alibaba.mtl.appmonitor.a.e.u()     // Catch: java.lang.Throwable -> L81
                com.alibaba.mtl.appmonitor.a.f r2 = com.alibaba.mtl.appmonitor.a.f.STAT     // Catch: java.lang.Throwable -> L81
                int r2 = r2.a()     // Catch: java.lang.Throwable -> L81
                r3 = r10
                r4 = r11
                r5 = r13
                r6 = r12
                r7 = r14
                r1.a(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L81
                goto L78
            L65:
                java.lang.String r1 = "statEvent commit failed,log discard"
                java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L81
                java.lang.String r8 = " ,. module: "
                r7[r6] = r8     // Catch: java.lang.Throwable -> L81
                r7[r5] = r10     // Catch: java.lang.Throwable -> L81
                java.lang.String r0 = " monitorPoint: "
                r7[r3] = r0     // Catch: java.lang.Throwable -> L81
                r7[r2] = r11     // Catch: java.lang.Throwable -> L81
                com.alibaba.mtl.log.d.i.a(r1, r7)     // Catch: java.lang.Throwable -> L81
            L78:
                goto L85
            L79:
                java.lang.String r0 = "AppMonitorDelegate"
                java.lang.String r1 = "module & monitorPoint must not null"
                com.alibaba.mtl.log.d.i.a(r0, r1)     // Catch: java.lang.Throwable -> L81
                return
            L81:
                r0 = move-exception
                com.alibaba.mtl.appmonitor.b.b.m16a(r0)
            L85:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.appmonitor.a.d.a(java.lang.String, java.lang.String, com.alibaba.mtl.appmonitor.model.DimensionValueSet, com.alibaba.mtl.appmonitor.model.MeasureValueSet, java.util.Map):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(com.alibaba.mtl.appmonitor.a.f fVar, int i2) {
        try {
            if (i && fVar != null) {
                h.b(fVar.a(), i2);
                if (i2 > 0) {
                    fVar.b(true);
                } else {
                    fVar.b(false);
                }
            }
        } catch (Throwable th) {
            com.alibaba.mtl.appmonitor.b.b.m16a(th);
        }
    }

    public static void a(boolean z, String str, String str2, String str3) {
        com.alibaba.mtl.log.e.b aVar;
        if (z) {
            aVar = new com.alibaba.mtl.log.e.c(str, str3);
        } else {
            boolean z2 = false;
            if ("1".equalsIgnoreCase(str3)) {
                z2 = true;
            }
            aVar = new com.alibaba.mtl.log.e.a(str, str2, z2);
        }
        com.alibaba.mtl.log.a.a(aVar);
        com.alibaba.mtl.log.a.a.a(ax);
    }

    public static void setChannel(String str) {
        com.alibaba.mtl.log.a.setChannel(str);
    }

    public static void turnOnRealTimeDebug(Map<String, String> map) {
        com.alibaba.mtl.log.a.a.turnOnRealTimeDebug(map);
    }

    public static void turnOffRealTimeDebug() {
        i.a("AppMonitorDelegate", "[turnOffRealTimeDebug]");
    }
}
