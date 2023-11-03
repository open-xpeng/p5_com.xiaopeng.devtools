package com.xiaopeng.lib.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.NonNull;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: ThreadUtils.java */
/* loaded from: classes12.dex */
public class j {
    private static volatile HandlerThread WA;
    private static Handler WB;
    private static volatile HandlerThread WC;
    private static Handler WD;
    private static final ExecutorService Wy = Executors.newFixedThreadPool(4, new b().dJ("order-%d").aQ(false).oZ());
    private static ConcurrentHashMap<Object, a> Wz = new ConcurrentHashMap<>();
    private static Handler sMainThreadHandler;

    public static Looper dR(int i) {
        switch (i) {
            case 0:
                oV();
                return WA.getLooper();
            case 1:
                oU();
                return sMainThreadHandler.getLooper();
            case 2:
                oW();
                return WD.getLooper();
            default:
                throw new IllegalArgumentException("invalid threadType:" + i);
        }
    }

    public static void c(Runnable runnable) {
        a(1, runnable);
    }

    public static void a(Runnable runnable, long j) {
        b(1, runnable, j);
    }

    public static void execute(Runnable runnable) {
        execute(runnable, null, 10);
    }

    public static void execute(final Runnable runnable, final Runnable runnable2, final int i) {
        try {
            if (!Wy.isShutdown()) {
                final Handler handler = null;
                if (runnable2 != null) {
                    handler = new Handler(Looper.myLooper());
                }
                Wy.execute(new Runnable() { // from class: com.xiaopeng.lib.utils.j.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Process.setThreadPriority(i);
                        runnable.run();
                        if (handler != null && runnable2 != null) {
                            handler.post(runnable2);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(int i, Runnable runnable) {
        a(i, null, runnable, null, false, 0L);
    }

    public static void b(int i, Runnable runnable, long j) {
        a(i, null, runnable, null, false, j);
    }

    public static void b(Runnable runnable, long j) {
        a(1, null, runnable, null, false, j);
    }

    public static void d(Runnable runnable) {
        a(0, null, runnable, null, false, 0L);
    }

    public static void c(Runnable runnable, long j) {
        a(0, null, runnable, null, false, j);
    }

    private static void a(int i, final Runnable runnable, final Runnable runnable2, final Runnable runnable3, final boolean z, long j) {
        Handler handler;
        if (runnable2 == null) {
            return;
        }
        if (sMainThreadHandler == null) {
            oU();
        }
        switch (i) {
            case 0:
                if (WA == null) {
                    oV();
                }
                handler = WB;
                break;
            case 1:
                handler = sMainThreadHandler;
                break;
            case 2:
                if (WC == null) {
                    oW();
                }
                handler = WD;
                break;
            default:
                handler = sMainThreadHandler;
                break;
        }
        if (handler == null) {
            handler = sMainThreadHandler;
        }
        Looper looper = null;
        if (!z && (looper = Looper.myLooper()) == null) {
            looper = sMainThreadHandler.getLooper();
        }
        final Looper looper2 = looper;
        final Runnable runnable4 = new Runnable() { // from class: com.xiaopeng.lib.utils.j.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable2.run();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                if (runnable3 != null) {
                    if (z || looper2 == j.sMainThreadHandler.getLooper()) {
                        j.sMainThreadHandler.post(runnable3);
                    } else {
                        new Handler(looper2).post(runnable3);
                    }
                }
                j.Wz.remove(runnable2);
            }
        };
        final Handler handler2 = handler;
        Runnable runnable5 = new Runnable() { // from class: com.xiaopeng.lib.utils.j.3
            @Override // java.lang.Runnable
            public void run() {
                if (runnable != null) {
                    if (z || looper2 == j.sMainThreadHandler.getLooper()) {
                        j.sMainThreadHandler.post(new Runnable() { // from class: com.xiaopeng.lib.utils.j.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                runnable.run();
                                handler2.post(runnable4);
                            }
                        });
                        return;
                    } else {
                        new Handler(looper2).post(new Runnable() { // from class: com.xiaopeng.lib.utils.j.3.2
                            @Override // java.lang.Runnable
                            public void run() {
                                runnable.run();
                                handler2.post(runnable4);
                            }
                        });
                        return;
                    }
                }
                runnable4.run();
            }
        };
        handler.postDelayed(runnable5, j);
        if (runnable == null) {
            Wz.put(runnable2, new a(runnable5, Integer.valueOf(i)));
        } else {
            Wz.put(runnable2, new a(runnable4, Integer.valueOf(i)));
        }
    }

    public static void e(Runnable runnable) {
        a remove;
        Runnable runnable2;
        if (runnable != null && (remove = Wz.remove(runnable)) != null && (runnable2 = remove.getRunnable()) != null) {
            switch (remove.getType()) {
                case 0:
                    if (WB != null) {
                        WB.removeCallbacks(runnable2);
                        return;
                    }
                    return;
                case 1:
                    if (sMainThreadHandler != null) {
                        sMainThreadHandler.removeCallbacks(runnable2);
                        return;
                    }
                    return;
                case 2:
                    if (WD != null) {
                        WD.removeCallbacks(runnable2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ThreadUtils.java */
    /* loaded from: classes12.dex */
    public static class a {
        private Integer WO;
        private Runnable mRunnable;

        public a(Runnable runnable, Integer num) {
            this.mRunnable = runnable;
            this.WO = num;
        }

        public Runnable getRunnable() {
            return this.mRunnable;
        }

        public int getType() {
            return this.WO.intValue();
        }
    }

    private static synchronized void oU() {
        synchronized (j.class) {
            if (sMainThreadHandler == null) {
                sMainThreadHandler = new Handler(Looper.getMainLooper());
            }
        }
    }

    private static synchronized void oV() {
        synchronized (j.class) {
            if (WA == null) {
                WA = new HandlerThread("BackgroundHandler", 10);
                WA.start();
                WB = new Handler(WA.getLooper());
            }
        }
    }

    private static synchronized void oW() {
        synchronized (j.class) {
            if (WC == null) {
                WC = new HandlerThread("NormalHandler", 0);
                WC.start();
                WD = new Handler(WC.getLooper());
            }
        }
    }

    /* compiled from: ThreadUtils.java */
    /* loaded from: classes12.dex */
    private static class b {
        private boolean WP;
        private ThreadFactory WQ;
        private String WR;
        private String mName;

        private b() {
            this.mName = "newFixedThreadPool";
            this.WP = false;
        }

        b dJ(String str) {
            this.WR = str;
            return this;
        }

        b aQ(boolean z) {
            this.WP = z;
            return this;
        }

        ThreadFactory oZ() {
            return new ThreadFactory() { // from class: com.xiaopeng.lib.utils.j.b.1
                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(@NonNull Runnable runnable) {
                    Thread newThread = (b.this.WQ != null ? b.this.WQ : Executors.defaultThreadFactory()).newThread(runnable);
                    AtomicLong atomicLong = b.this.WR != null ? new AtomicLong(0L) : null;
                    if (b.this.WR != null) {
                        newThread.setName(String.format(Locale.ROOT, b.this.WR, Long.valueOf(atomicLong.getAndIncrement())));
                    }
                    newThread.setDaemon(b.this.WP);
                    return newThread;
                }
            };
        }
    }
}
