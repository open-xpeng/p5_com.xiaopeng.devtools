package com.alibaba.sdk.android.man.crashreporter.handler.b;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;

/* loaded from: classes11.dex */
public class b extends Thread {
    private static final a a = new a() { // from class: com.alibaba.sdk.android.man.crashreporter.handler.b.b.1
        @Override // com.alibaba.sdk.android.man.crashreporter.handler.b.b.a
        public void c(String str) {
        }

        @Override // com.alibaba.sdk.android.man.crashreporter.handler.b.b.a
        public void a(String str, int i) {
        }
    };

    /* renamed from: a  reason: collision with other field name */
    private static final InterfaceC0026b f91a = new InterfaceC0026b() { // from class: com.alibaba.sdk.android.man.crashreporter.handler.b.b.2
        @Override // com.alibaba.sdk.android.man.crashreporter.handler.b.b.InterfaceC0026b
        public void a(InterruptedException interruptedException) {
            Log.w("ANRWatchdog", "Interrupted: " + interruptedException.getMessage());
        }
    };
    private static final int y = 5000;
    private volatile int A;

    /* renamed from: a  reason: collision with other field name */
    private final Handler f92a;

    /* renamed from: a  reason: collision with other field name */
    private final Runnable f93a;
    private a b;

    /* renamed from: b  reason: collision with other field name */
    private InterfaceC0026b f94b;

    /* renamed from: b  reason: collision with other field name */
    private boolean f95b;
    private String s;
    private final int z;

    /* loaded from: classes11.dex */
    public interface a {
        void a(String str, int i);

        void c(String str);
    }

    /* renamed from: com.alibaba.sdk.android.man.crashreporter.handler.b.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0026b {
        void a(InterruptedException interruptedException);
    }

    public b() {
        this(y);
    }

    public b(int i) {
        this.b = a;
        this.f94b = f91a;
        this.f92a = new Handler(Looper.getMainLooper());
        this.s = "";
        this.f95b = false;
        this.A = 0;
        this.f93a = new Runnable() { // from class: com.alibaba.sdk.android.man.crashreporter.handler.b.b.3
            @Override // java.lang.Runnable
            public void run() {
                b.this.A = (b.this.A + 1) % 10;
            }
        };
        this.z = i;
    }

    public b a(a aVar) {
        if (aVar == null) {
            this.b = a;
        } else {
            this.b = aVar;
        }
        return this;
    }

    public b a(InterfaceC0026b interfaceC0026b) {
        if (interfaceC0026b == null) {
            this.f94b = f91a;
        } else {
            this.f94b = interfaceC0026b;
        }
        return this;
    }

    public b a(String str) {
        if (str == null) {
            str = "";
        }
        this.s = str;
        return this;
    }

    public b a() {
        this.s = null;
        return this;
    }

    public void a(boolean z) {
        this.f95b = z;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        setName("ANR-WatchDog");
        int i = 1;
        while (!isInterrupted()) {
            int i2 = this.A;
            this.f92a.post(this.f93a);
            try {
                int i3 = this.z / 1000;
                int i4 = 1;
                while (true) {
                    if (i4 > i3) {
                        break;
                    }
                    Thread.sleep(1000L);
                    if (this.A != i2) {
                        this.b.a(String.valueOf(i4), 1);
                        i++;
                        if (i > 3) {
                            Thread.sleep(60000L);
                            i = 1;
                        } else {
                            Thread.sleep((i3 - i4) * 1000);
                        }
                    } else {
                        i4++;
                    }
                }
                if (this.A == i2 && MotuCrashReporter.getInstance().getCrashReporterState() == -1) {
                    this.b.c(this.s);
                    return;
                }
            } catch (InterruptedException e) {
                this.f94b.a(e);
                return;
            }
        }
    }
}
