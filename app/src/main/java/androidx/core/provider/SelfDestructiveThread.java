package androidx.core.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes8.dex */
public class SelfDestructiveThread {
    private static final int MSG_DESTRUCTION = 0;
    private static final int MSG_INVOKE_RUNNABLE = 1;
    private final int mDestructAfterMillisec;
    @GuardedBy("mLock")
    private Handler mHandler;
    private final int mPriority;
    @GuardedBy("mLock")
    private HandlerThread mThread;
    private final String mThreadName;
    private final Object mLock = new Object();
    private Handler.Callback mCallback = new Handler.Callback() { // from class: androidx.core.provider.SelfDestructiveThread.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    SelfDestructiveThread.this.onDestruction();
                    return true;
                case 1:
                    SelfDestructiveThread.this.onInvokeRunnable((Runnable) message.obj);
                    return true;
                default:
                    return true;
            }
        }
    };
    @GuardedBy("mLock")
    private int mGeneration = 0;

    /* loaded from: classes8.dex */
    public interface ReplyCallback<T> {
        void onReply(T t);
    }

    public SelfDestructiveThread(String str, int i, int i2) {
        this.mThreadName = str;
        this.mPriority = i;
        this.mDestructAfterMillisec = i2;
    }

    @VisibleForTesting
    public boolean isRunning() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mThread != null;
        }
        return z;
    }

    @VisibleForTesting
    public int getGeneration() {
        int i;
        synchronized (this.mLock) {
            i = this.mGeneration;
        }
        return i;
    }

    private void post(Runnable runnable) {
        synchronized (this.mLock) {
            if (this.mThread == null) {
                this.mThread = new HandlerThread(this.mThreadName, this.mPriority);
                this.mThread.start();
                this.mHandler = new Handler(this.mThread.getLooper(), this.mCallback);
                this.mGeneration++;
            }
            this.mHandler.removeMessages(0);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, runnable));
        }
    }

    public <T> void postAndReply(final Callable<T> callable, final ReplyCallback<T> replyCallback) {
        final Handler handler = new Handler();
        post(new Runnable() { // from class: androidx.core.provider.SelfDestructiveThread.2
            @Override // java.lang.Runnable
            public void run() {
                final Object obj;
                try {
                    obj = callable.call();
                } catch (Exception e) {
                    obj = null;
                }
                handler.post(new Runnable() { // from class: androidx.core.provider.SelfDestructiveThread.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        replyCallback.onReply(obj);
                    }
                });
            }
        });
    }

    public <T> T postAndWait(final Callable<T> callable, int i) throws InterruptedException {
        T t;
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition newCondition = reentrantLock.newCondition();
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        post(new Runnable() { // from class: androidx.core.provider.SelfDestructiveThread.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    atomicReference.set(callable.call());
                } catch (Exception e) {
                }
                reentrantLock.lock();
                try {
                    atomicBoolean.set(false);
                    newCondition.signal();
                } finally {
                    reentrantLock.unlock();
                }
            }
        });
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                t = (T) atomicReference.get();
            } else {
                long nanos = TimeUnit.MILLISECONDS.toNanos(i);
                while (true) {
                    try {
                        nanos = newCondition.awaitNanos(nanos);
                    } catch (InterruptedException e) {
                    }
                    if (!atomicBoolean.get()) {
                        t = (T) atomicReference.get();
                        break;
                    } else if (nanos <= 0) {
                        throw new InterruptedException("timeout");
                    }
                }
            }
            return t;
        } finally {
            reentrantLock.unlock();
        }
    }

    void onInvokeRunnable(Runnable runnable) {
        runnable.run();
        synchronized (this.mLock) {
            this.mHandler.removeMessages(0);
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), this.mDestructAfterMillisec);
        }
    }

    void onDestruction() {
        synchronized (this.mLock) {
            if (this.mHandler.hasMessages(1)) {
                return;
            }
            this.mThread.quit();
            this.mThread = null;
            this.mHandler = null;
        }
    }
}
