package com.alibaba.mtl.appmonitor.c;

import com.alibaba.mtl.appmonitor.c.b;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: ReuseItemPool.java */
/* loaded from: classes11.dex */
public class c<T extends b> {
    private static AtomicLong ba = new AtomicLong(0);
    private static AtomicLong bb = new AtomicLong(0);
    private final int m = 20;
    private Integer bc = null;
    private AtomicLong a = new AtomicLong(0);
    private AtomicLong aZ = new AtomicLong(0);
    private ConcurrentLinkedQueue<T> bd = new ConcurrentLinkedQueue<>();
    private Set<Integer> be = new HashSet();

    public T z() {
        ba.getAndIncrement();
        this.a.getAndIncrement();
        T poll = this.bd.poll();
        if (poll != null) {
            this.be.remove(Integer.valueOf(System.identityHashCode(poll)));
            this.aZ.getAndIncrement();
            bb.getAndIncrement();
        }
        return poll;
    }

    public void a(T t) {
        t.clean();
        if (this.bd.size() < 20) {
            synchronized (this.be) {
                int identityHashCode = System.identityHashCode(t);
                if (!this.be.contains(Integer.valueOf(identityHashCode))) {
                    this.be.add(Integer.valueOf(identityHashCode));
                    this.bd.offer(t);
                }
            }
        }
    }
}
