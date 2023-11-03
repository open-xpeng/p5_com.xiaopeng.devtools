package com.alibaba.mtl.appmonitor;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.alibaba.mtl.appmonitor.b;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.log.d.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public final class AppMonitor {

    /* renamed from: a  reason: collision with other field name */
    private static volatile boolean f2a;
    protected static com.alibaba.mtl.appmonitor.b ab;
    private static boolean c;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static Context mContext;
    private static Application W = null;
    protected static c aa = null;
    private static HandlerThread a = null;

    /* renamed from: a  reason: collision with other field name */
    private static Object f0a = new Object();

    /* renamed from: a  reason: collision with other field name */
    private static List<a> f1a = Collections.synchronizedList(new ArrayList());

    /* renamed from: b  reason: collision with other field name */
    private static boolean f3b = false;
    private static b ae = b.Local;
    private static ServiceConnection af = new ServiceConnection() { // from class: com.alibaba.mtl.appmonitor.AppMonitor.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (b.Service == AppMonitor.ae) {
                AppMonitor.ab = b.a.a(iBinder);
                if (AppMonitor.f3b && AppMonitor.aa != null) {
                    AppMonitor.aa.postAtFrontOfQueue(new Runnable() { // from class: com.alibaba.mtl.appmonitor.AppMonitor.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AppMonitor.restart();
                        }
                    });
                }
            }
            synchronized (AppMonitor.f0a) {
                AppMonitor.f0a.notifyAll();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            i.a("AppMonitor", "[onServiceDisconnected]");
            synchronized (AppMonitor.f0a) {
                AppMonitor.f0a.notifyAll();
            }
            boolean unused = AppMonitor.f3b = true;
        }
    };
    private static Map<String, Object> b = Collections.synchronizedMap(new HashMap());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public enum b {
        Local,
        Service
    }

    public static synchronized void a(Application application) {
        synchronized (AppMonitor.class) {
            i.a("AppMonitor", "[init]");
            try {
                if (!f2a) {
                    W = application;
                    if (W != null) {
                        mContext = W.getApplicationContext();
                    }
                    a = new HandlerThread("AppMonitor_Client");
                    a.start();
                    aa = new c(a.getLooper());
                    if (ae == b.Local) {
                        m11a();
                    } else if (m12a()) {
                        aa.a(true);
                    }
                    n().run();
                    f2a = true;
                }
            } catch (Throwable th) {
            }
        }
    }

    public static void a(boolean z, String str, String str2, String str3) {
        if (!l()) {
            return;
        }
        aa.a(b(z, str, str2, str3));
        c = z;
        g = str;
        h = str2;
        i = str3;
    }

    public static void setChannel(String str) {
        if (!l()) {
            return;
        }
        aa.a(l(str));
        f = str;
    }

    public static void turnOnRealTimeDebug(final Map<String, String> map) {
        if (!l()) {
            return;
        }
        aa.a(new Runnable() { // from class: com.alibaba.mtl.appmonitor.AppMonitor.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AppMonitor.ab.turnOnRealTimeDebug(map);
                } catch (RemoteException e) {
                    AppMonitor.a(e);
                }
            }
        });
    }

    public static void turnOffRealTimeDebug() {
        if (!l()) {
            return;
        }
        aa.a(new Runnable() { // from class: com.alibaba.mtl.appmonitor.AppMonitor.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AppMonitor.ab.turnOffRealTimeDebug();
                } catch (RemoteException e) {
                    AppMonitor.a(e);
                }
            }
        });
    }

    /* renamed from: a  reason: collision with other method in class */
    private static boolean m12a() {
        if (W == null) {
            return false;
        }
        boolean bindService = W.getApplicationContext().bindService(new Intent(W.getApplicationContext(), AppMonitorService.class), af, 1);
        if (!bindService) {
            m11a();
        }
        i.a("AppMonitor", "bindsuccess:", Boolean.valueOf(bindService));
        return bindService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class c extends Handler {
        private boolean h;

        public c(Looper looper) {
            super(looper);
            this.h = false;
        }

        public void a(Runnable runnable) {
            if (runnable == null) {
                return;
            }
            try {
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = runnable;
                sendMessage(obtain);
            } catch (Throwable th) {
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                if (this.h) {
                    this.h = false;
                    synchronized (AppMonitor.f0a) {
                        try {
                            AppMonitor.f0a.wait(5000L);
                        } catch (InterruptedException e) {
                            AppMonitor.m11a();
                        }
                    }
                }
                if (message.obj != null) {
                    if (message.obj instanceof Runnable) {
                        try {
                            ((Runnable) message.obj).run();
                        } catch (Throwable th) {
                        }
                    }
                }
            } catch (Throwable th2) {
            }
            super.handleMessage(message);
        }

        public void a(boolean z) {
            this.h = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Exception exc) {
        i.a("AppMonitor", "", exc);
        if (exc instanceof DeadObjectException) {
            restart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void restart() {
        synchronized (AppMonitor.class) {
            i.a("AppMonitor", "[restart]");
            try {
                if (f3b) {
                    f3b = false;
                    m11a();
                    n().run();
                    b(c, g, h, i).run();
                    l(f).run();
                    synchronized (f1a) {
                        for (int i2 = 0; i2 < f1a.size(); i2++) {
                            a aVar = f1a.get(i2);
                            if (aVar != null) {
                                try {
                                    a(aVar.o, aVar.p, aVar.aj, aVar.ak, aVar.g).run();
                                } catch (Throwable th) {
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: collision with other method in class */
    public static void m11a() {
        ab = new com.alibaba.mtl.appmonitor.c(W);
        ae = b.Local;
        i.a("AppMonitor", "Start AppMonitor Service failed,AppMonitor run in local Mode...");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class a {
        public MeasureSet aj;
        public DimensionSet ak;
        public boolean g;
        public String o;
        public String p;

        a() {
        }
    }

    public static boolean l() {
        if (!f2a) {
            i.a("AppMonitor", "Please call UTAnalytics.getInstance().setAppApplicationInstance()||.setAppApplicationInstance4sdk() before call other method");
        }
        return f2a;
    }

    private static Runnable n() {
        return new Runnable() { // from class: com.alibaba.mtl.appmonitor.AppMonitor.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AppMonitor.ab.init();
                } catch (RemoteException e) {
                    AppMonitor.m11a();
                    try {
                        AppMonitor.ab.init();
                    } catch (Throwable th) {
                    }
                }
            }
        };
    }

    private static Runnable b(final boolean z, final String str, final String str2, final String str3) {
        return new Runnable() { // from class: com.alibaba.mtl.appmonitor.AppMonitor.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AppMonitor.ab.a(z, str, str2, str3);
                } catch (Throwable th) {
                }
            }
        };
    }

    private static Runnable l(final String str) {
        return new Runnable() { // from class: com.alibaba.mtl.appmonitor.AppMonitor.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AppMonitor.ab.setChannel(str);
                } catch (Throwable th) {
                }
            }
        };
    }

    private static Runnable a(final String str, final String str2, final MeasureSet measureSet, final DimensionSet dimensionSet, final boolean z) {
        return new Runnable() { // from class: com.alibaba.mtl.appmonitor.AppMonitor.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    i.a("AppMonitor", "register stat event. module: ", str, " monitorPoint: ", str2);
                    AppMonitor.ab.c(str, str2, measureSet, dimensionSet, z);
                } catch (RemoteException e) {
                    AppMonitor.a(e);
                }
            }
        };
    }
}
