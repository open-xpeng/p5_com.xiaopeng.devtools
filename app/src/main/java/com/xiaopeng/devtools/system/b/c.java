package com.xiaopeng.devtools.system.b;

/* compiled from: Sleep.java */
/* loaded from: classes12.dex */
public class c implements Runnable {
    final Object oX = new Object();
    private long oY;

    private c(long j) {
        this.oY = 1000L;
        this.oY = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.oX) {
            try {
                this.oX.wait(this.oY);
            } catch (InterruptedException e) {
                com.xiaopeng.lib.utils.c.i("Sleep", e.toString());
            }
        }
    }

    public static void sleep(long j) {
        com.xiaopeng.lib.utils.c.f("Sleep", "sleep start  timeout: " + j);
        Thread thread = new Thread(new c(j));
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            com.xiaopeng.lib.utils.c.i("Sleep", e.toString());
        }
        com.xiaopeng.lib.utils.c.f("Sleep", "sleep end  timeout: " + j);
    }
}
