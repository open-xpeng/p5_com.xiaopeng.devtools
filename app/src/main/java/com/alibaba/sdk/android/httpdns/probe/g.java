package com.alibaba.sdk.android.httpdns.probe;

import android.support.annotation.NonNull;
import com.alibaba.sdk.android.httpdns.i;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes11.dex */
class g implements Runnable {
    private ConcurrentHashMap<String, Long> a;

    /* renamed from: a  reason: collision with other field name */
    private CountDownLatch f48a;
    private String k;
    private int port;

    public g(@NonNull String str, int i, CountDownLatch countDownLatch, ConcurrentHashMap<String, Long> concurrentHashMap) {
        this.f48a = null;
        this.k = str;
        this.port = i;
        this.f48a = countDownLatch;
        this.a = concurrentHashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x007e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private long a(java.lang.String r8, int r9) {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r4 = 0
            java.net.Socket r5 = new java.net.Socket     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            r5.<init>()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.net.InetSocketAddress r4 = new java.net.InetSocketAddress     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3c
            r4.<init>(r8, r9)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3c
            r8 = 5000(0x1388, float:7.006E-42)
            r5.connect(r4, r8)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3c
            long r8 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3c
            r5.close()     // Catch: java.io.IOException -> L1f
            goto L7a
        L1f:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "socket close failed:"
            r5.append(r6)
            java.lang.String r4 = r4.toString()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            com.alibaba.sdk.android.httpdns.i.f(r4)
            goto L7a
        L39:
            r8 = move-exception
            r4 = r5
            goto L81
        L3c:
            r8 = move-exception
            r4 = r5
            goto L42
        L3f:
            r8 = move-exception
            goto L81
        L41:
            r8 = move-exception
        L42:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r9.<init>()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r5 = "connect failed:"
            r9.append(r5)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L3f
            r9.append(r8)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r8 = r9.toString()     // Catch: java.lang.Throwable -> L3f
            com.alibaba.sdk.android.httpdns.i.f(r8)     // Catch: java.lang.Throwable -> L3f
            if (r4 == 0) goto L79
            r4.close()     // Catch: java.io.IOException -> L60
            goto L79
        L60:
            r8 = move-exception
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r4 = "socket close failed:"
            r9.append(r4)
            java.lang.String r8 = r8.toString()
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            com.alibaba.sdk.android.httpdns.i.f(r8)
        L79:
            r8 = r2
        L7a:
            int r4 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r4 != 0) goto L7f
            return r2
        L7f:
            long r8 = r8 - r0
            return r8
        L81:
            if (r4 == 0) goto La0
            r4.close()     // Catch: java.io.IOException -> L87
            goto La0
        L87:
            r9 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "socket close failed:"
            r0.append(r1)
            java.lang.String r9 = r9.toString()
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            com.alibaba.sdk.android.httpdns.i.f(r9)
        La0:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.probe.g.a(java.lang.String, int):long");
    }

    private boolean a(int i) {
        return i >= 1 && i <= 65535;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.k == null || !a(this.port)) {
            i.f("invalid params, give up");
        } else {
            long a = a(this.k, this.port);
            i.d("connect cost for ip:" + this.k + " is " + a);
            if (this.a != null) {
                this.a.put(this.k, Long.valueOf(a));
            }
        }
        if (this.f48a != null) {
            this.f48a.countDown();
        }
    }
}
