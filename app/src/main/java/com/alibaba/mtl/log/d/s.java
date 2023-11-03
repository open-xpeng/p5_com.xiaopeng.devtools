package com.alibaba.mtl.log.d;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.xiaopeng.lib.framework.moduleinterface.carcontroller.IInputController;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: TaskExecutor.java */
/* loaded from: classes11.dex */
public class s {
    private static ThreadPoolExecutor dj;
    public static s dk;
    private HandlerThread b = new HandlerThread("AppMonitor");
    private Handler mHandler;
    private static int G = 1;
    private static int H = 3;
    private static int I = 10;
    private static int J = 60;
    private static final AtomicInteger dl = new AtomicInteger();

    static /* synthetic */ ThreadPoolExecutor aY() {
        return aV();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TaskExecutor.java */
    /* loaded from: classes11.dex */
    public static class a implements ThreadFactory {
        private int priority;

        public a(int i) {
            this.priority = i;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            int andIncrement = s.dl.getAndIncrement();
            Thread thread = new Thread(runnable, "AppMonitor:" + andIncrement);
            thread.setPriority(this.priority);
            return thread;
        }
    }

    @TargetApi(9)
    private static ThreadPoolExecutor a(int i, int i2, int i3, int i4, int i5) {
        LinkedBlockingQueue linkedBlockingQueue;
        if (i5 > 0) {
            linkedBlockingQueue = new LinkedBlockingQueue(i5);
        } else {
            linkedBlockingQueue = new LinkedBlockingQueue();
        }
        return new ThreadPoolExecutor(i2, i3, i4, TimeUnit.SECONDS, linkedBlockingQueue, new a(i), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    private static synchronized ThreadPoolExecutor aV() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (s.class) {
            if (dj == null) {
                dj = a(G, H, I, J, IInputController.KEYCODE_KNOB_WIND_SPD_UP);
            }
            threadPoolExecutor = dj;
        }
        return threadPoolExecutor;
    }

    public static synchronized s aW() {
        s sVar;
        synchronized (s.class) {
            if (dk == null) {
                dk = new s();
            }
            sVar = dk;
        }
        return sVar;
    }

    private s() {
        this.b.start();
        this.mHandler = new Handler(this.b.getLooper()) { // from class: com.alibaba.mtl.log.d.s.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                try {
                    if (message.obj != null && (message.obj instanceof Runnable)) {
                        s.aY().submit((Runnable) message.obj);
                    }
                } catch (Throwable th) {
                }
            }
        };
    }

    public final void a(int i, Runnable runnable, long j) {
        try {
            Message obtain = Message.obtain(this.mHandler, i);
            obtain.obj = runnable;
            this.mHandler.sendMessageDelayed(obtain, j);
        } catch (Exception e) {
            com.alibaba.mtl.appmonitor.b.b.m16a((Throwable) e);
        }
    }

    public final void E(int i) {
        this.mHandler.removeMessages(i);
    }

    public final boolean F(int i) {
        return this.mHandler.hasMessages(i);
    }

    public void b(Runnable runnable) {
        try {
            aV().submit(runnable);
        } catch (Throwable th) {
        }
    }
}
