package com.alibaba.sdk.android.man.crashreporter.a.b;

import android.os.Looper;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes11.dex */
public class a extends Error {
    private static final long serialVersionUID = 1;
    private final Map<Thread, StackTraceElement[]> b;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.alibaba.sdk.android.man.crashreporter.a.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0021a {
        private final StackTraceElement[] a;
        private final String r;

        /* renamed from: com.alibaba.sdk.android.man.crashreporter.a.b.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        private class C0022a extends Throwable {
            private C0022a(C0022a c0022a) {
                super(C0021a.this.r, c0022a);
            }

            @Override // java.lang.Throwable
            public Throwable fillInStackTrace() {
                setStackTrace(C0021a.this.a);
                return this;
            }
        }

        private C0021a(String str, StackTraceElement[] stackTraceElementArr) {
            this.r = str;
            this.a = stackTraceElementArr;
        }
    }

    private a(C0021a.C0022a c0022a, Map<Thread, StackTraceElement[]> map) {
        super("Application Not Responding", c0022a);
        this.b = map;
    }

    public Map<Thread, StackTraceElement[]> c() {
        return this.b;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public static a a(String str, boolean z) {
        final Thread thread = Looper.getMainLooper().getThread();
        TreeMap treeMap = new TreeMap(new Comparator<Thread>() { // from class: com.alibaba.sdk.android.man.crashreporter.a.b.a.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(Thread thread2, Thread thread3) {
                if (thread2 == thread3) {
                    return 0;
                }
                if (thread2 == thread) {
                    return 1;
                }
                if (thread3 == thread) {
                    return -1;
                }
                return thread3.getName().compareTo(thread2.getName());
            }
        });
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            if (entry.getKey() == thread || (entry.getKey().getName().startsWith(str) && (z || entry.getValue().length > 0))) {
                treeMap.put(entry.getKey(), entry.getValue());
            }
        }
        C0021a.C0022a c0022a = null;
        for (Map.Entry entry2 : treeMap.entrySet()) {
            C0021a c0021a = new C0021a(((Thread) entry2.getKey()).getName(), (StackTraceElement[]) entry2.getValue());
            c0021a.getClass();
            c0022a = new C0021a.C0022a(c0022a);
        }
        return new a(c0022a, treeMap);
    }

    public static a a() {
        Thread thread = Looper.getMainLooper().getThread();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        HashMap hashMap = new HashMap(1);
        hashMap.put(thread, stackTrace);
        C0021a c0021a = new C0021a(thread.getName(), stackTrace);
        c0021a.getClass();
        return new a(new C0021a.C0022a(null), hashMap);
    }

    public static Map<Thread, StackTraceElement[]> d() {
        Thread currentThread = Thread.currentThread();
        if (currentThread == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        hashMap.put(currentThread, stackTrace);
        return hashMap;
    }

    public static Map<Thread, StackTraceElement[]> e() {
        Thread thread = Looper.getMainLooper().getThread();
        if (thread == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        hashMap.put(thread, stackTrace);
        return hashMap;
    }
}
