package com.xiaopeng.commonfunc.system.a;

import com.xiaopeng.lib.utils.c;

/* compiled from: Sleep.java */
/* loaded from: classes11.dex */
public class a implements Runnable {
    final Object oX = new Object();
    private long oY;

    private a(long j) {
        this.oY = 1000L;
        this.oY = j;
    }

    public static void sleep(long j) {
        c.f("Sleep", "sleep start  timeout: " + j);
        Thread thread = new Thread(new a(j));
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            c.i("Sleep", e.toString());
        }
        c.f("Sleep", "sleep end  timeout: " + j);
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.oX) {
            try {
                this.oX.wait(this.oY);
            } catch (InterruptedException e) {
                c.i("Sleep", e.toString());
            }
        }
    }
}
