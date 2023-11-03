package com.alibaba.mtl.log.d;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* compiled from: MutiProcessLock.java */
/* loaded from: classes11.dex */
public class k {
    static File a = null;
    static FileChannel df;
    static FileLock dg;

    public static synchronized boolean c(Context context) {
        FileLock fileLock;
        synchronized (k.class) {
            if (a == null) {
                a = new File(context.getFilesDir() + File.separator + "ap.Lock");
            }
            boolean exists = a.exists();
            if (!exists) {
                try {
                    exists = a.createNewFile();
                } catch (IOException e) {
                }
            }
            if (!exists) {
                return true;
            }
            if (df == null) {
                try {
                    df = new RandomAccessFile(a, "rw").getChannel();
                } catch (Exception e2) {
                    return false;
                }
            }
            try {
                fileLock = df.tryLock();
                if (fileLock != null) {
                    dg = fileLock;
                    return true;
                }
            } catch (Throwable th) {
                fileLock = null;
            }
            Log.d("TAG", "mLock:" + fileLock);
            return false;
        }
    }

    public static synchronized void release() {
        synchronized (k.class) {
            if (dg != null) {
                try {
                    dg.release();
                    dg = null;
                } catch (IOException e) {
                    dg = null;
                } catch (Throwable th) {
                    dg = null;
                    throw th;
                }
            }
            if (df != null) {
                try {
                    df.close();
                    df = null;
                } catch (Exception e2) {
                    df = null;
                } catch (Throwable th2) {
                    df = null;
                    throw th2;
                }
            }
        }
    }
}
