package com.xiaopeng.devtools.system.b;

import com.xiaopeng.devtools.utils.g;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import org.greenrobot.eventbus.EventBus;

/* compiled from: CopyFileTask.java */
/* loaded from: classes12.dex */
public class a implements Runnable {
    RandomAccessFile Aq;
    RandomAccessFile Ar;
    private String As;
    private String At;
    private long Au;
    private long Av;

    public a(String str, String str2, long j, long j2) {
        this.As = str;
        this.At = str2;
        this.Au = j;
        this.Av = j2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (g.aY(this.As)) {
            try {
                try {
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (g.cv(this.At)) {
                    try {
                        long currentTimeMillis = System.currentTimeMillis();
                        com.xiaopeng.lib.utils.c.f("CopyFileTask", "beginTime = " + currentTimeMillis + " mSrcPath = " + this.As + "destPath = " + this.At);
                        this.Aq = new RandomAccessFile(this.As, "r");
                        this.Ar = new RandomAccessFile(this.At, "rw");
                        this.Aq.seek(this.Au);
                        this.Ar.seek(this.Au);
                        FileChannel channel = this.Aq.getChannel();
                        FileChannel channel2 = this.Ar.getChannel();
                        FileLock lock = channel2.lock(this.Au, this.Av - this.Au, false);
                        channel.transferTo(this.Au, this.Av - this.Au, channel2);
                        lock.release();
                        this.Ar.close();
                        this.Aq.close();
                        long currentTimeMillis2 = System.currentTimeMillis();
                        EventBus.getDefault().post(1);
                        com.xiaopeng.lib.utils.c.f("CopyFileTask", "endTime = " + currentTimeMillis2 + " mSrcPath = " + this.As + "destPath = " + this.At);
                        if (this.Ar != null) {
                            this.Ar.close();
                        }
                        if (this.Aq != null) {
                            this.Aq.close();
                        }
                    } catch (Exception e2) {
                        EventBus.getDefault().post(2);
                        e2.printStackTrace();
                        if (this.Ar != null) {
                            this.Ar.close();
                        }
                        if (this.Aq != null) {
                            this.Aq.close();
                        }
                    }
                    return;
                }
            } catch (Throwable th) {
                try {
                    if (this.Ar != null) {
                        this.Ar.close();
                    }
                    if (this.Aq != null) {
                        this.Aq.close();
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        EventBus.getDefault().post(2);
    }
}
