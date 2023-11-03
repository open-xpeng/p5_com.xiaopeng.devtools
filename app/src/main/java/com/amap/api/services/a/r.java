package com.amap.api.services.a;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: SDKLogHandler.java */
/* loaded from: classes11.dex */
public class r extends o implements Thread.UncaughtExceptionHandler {
    private static ExecutorService eK;
    private Context ei;

    public static synchronized r a(Context context, l lVar) throws au {
        r rVar;
        synchronized (r.class) {
            if (lVar == null) {
                throw new au("sdk info is null");
            }
            if (lVar.a() == null || "".equals(lVar.a())) {
                throw new au("sdk name is invalid");
            }
            if (o.eH == null) {
                o.eH = new r(context, lVar);
            } else {
                o.eH.c = false;
            }
            o.eH.a(context, lVar, o.eH.c);
            rVar = (r) o.eH;
        }
        return rVar;
    }

    public static synchronized r bn() {
        r rVar;
        synchronized (r.class) {
            rVar = (r) o.eH;
        }
        return rVar;
    }

    public static void b(Throwable th, String str, String str2) {
        if (o.eH != null) {
            o.eH.a(th, 1, str, str2);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (th == null) {
            return;
        }
        a(th, 0, null, null);
        if (this.eI != null) {
            try {
                Thread.setDefaultUncaughtExceptionHandler(this.eI);
            } catch (Throwable th2) {
            }
            this.eI.uncaughtException(thread, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.services.a.o
    public void a(Throwable th, int i, String str, String str2) {
        p.a(this.ei, th, i, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.services.a.o
    public void a(final Context context, final l lVar, final boolean z) {
        try {
            ExecutorService bo = bo();
            if (bo != null && !bo.isShutdown()) {
                bo.submit(new Runnable() { // from class: com.amap.api.services.a.r.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new ah(context, true).a(lVar);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    ai aiVar = new ai(context);
                                    aj ajVar = new aj();
                                    ajVar.c(true);
                                    ajVar.a(true);
                                    ajVar.b(true);
                                    aiVar.a(ajVar);
                                }
                                p.a(r.this.ei);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private r(Context context, l lVar) {
        this.ei = context;
        be.a(new a(context));
        c();
    }

    private void c() {
        try {
            this.eI = Thread.getDefaultUncaughtExceptionHandler();
            if (this.eI == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
            } else if (this.eI.toString().indexOf("com.amap.api") != -1) {
                this.c = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void c(Throwable th, String str, String str2) {
        if (th == null) {
            return;
        }
        try {
            a(th, 1, str, str2);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static synchronized ExecutorService bo() {
        ExecutorService executorService;
        synchronized (r.class) {
            try {
                if (eK == null || eK.isShutdown()) {
                    eK = Executors.newSingleThreadExecutor();
                }
                executorService = eK;
            }
        }
        return executorService;
    }

    /* compiled from: SDKLogHandler.java */
    /* loaded from: classes11.dex */
    private static class a implements bf {
        private Context a;

        a(Context context) {
            this.a = context;
        }

        @Override // com.amap.api.services.a.bf
        public void a() {
            try {
                p.b(this.a);
            } catch (Throwable th) {
                o.a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }
}
