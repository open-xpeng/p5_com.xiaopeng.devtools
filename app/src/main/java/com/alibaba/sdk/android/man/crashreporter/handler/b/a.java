package com.alibaba.sdk.android.man.crashreporter.handler.b;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.handler.b.b;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes11.dex */
public class a {
    private AtomicBoolean crashing;
    private AtomicBoolean a = new AtomicBoolean(false);
    private Timer timer = new Timer();

    public a(final Context context, final com.alibaba.sdk.android.man.crashreporter.handler.a aVar, AtomicBoolean atomicBoolean, final int i, final boolean z) {
        this.crashing = atomicBoolean;
        this.timer.schedule(new TimerTask() { // from class: com.alibaba.sdk.android.man.crashreporter.handler.b.a.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                b bVar = new b(i);
                if (z) {
                    bVar.a();
                }
                bVar.a(new b.a() { // from class: com.alibaba.sdk.android.man.crashreporter.handler.b.a.1.1
                    @Override // com.alibaba.sdk.android.man.crashreporter.handler.b.b.a
                    public void a(String str, int i2) {
                        if (a.this.a.compareAndSet(false, true)) {
                            com.alibaba.sdk.android.man.crashreporter.handler.c.a.d("crash_anr");
                        }
                        String m47b = com.alibaba.sdk.android.man.crashreporter.a.c.a.m47b(context);
                        if (m47b == null) {
                            m47b = "-";
                        }
                        com.alibaba.sdk.android.man.crashreporter.handler.c.a.a(m47b, "crash_anr", str, i2);
                    }

                    @Override // com.alibaba.sdk.android.man.crashreporter.handler.b.b.a
                    public void c(String str) {
                        try {
                            if (aVar != null) {
                                aVar.m57b(str);
                            } else {
                                com.alibaba.sdk.android.man.crashreporter.b.a.e("stuck: crash manager is null!");
                            }
                        } finally {
                            try {
                            } finally {
                            }
                        }
                    }
                }).start();
            }
        }, 20000L);
    }

    public void c() {
        this.timer.cancel();
    }
}
