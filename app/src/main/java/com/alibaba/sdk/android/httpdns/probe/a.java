package com.alibaba.sdk.android.httpdns.probe;

import android.support.annotation.NonNull;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
class a implements Runnable {
    private f a;

    /* renamed from: a  reason: collision with other field name */
    private ConcurrentHashMap<String, Long> f44a = new ConcurrentHashMap<>();

    /* renamed from: a  reason: collision with other field name */
    private String[] f45a;
    private String h;
    private long i;
    private int port;

    public a(long j, @NonNull String str, @NonNull String[] strArr, int i, f fVar) {
        this.a = null;
        this.i = j;
        this.h = str;
        this.f45a = strArr;
        this.port = i;
        this.a = fVar;
    }

    private c a(String[] strArr) {
        if (this.f45a == null || this.f45a.length == 0 || strArr == null || strArr.length == 0) {
            return null;
        }
        String str = this.f45a[0];
        String str2 = strArr[0];
        return new c(this.h, strArr, str, str2, this.f44a.containsKey(str) ? this.f44a.get(str).longValue() : 2147483647L, this.f44a.containsKey(str2) ? this.f44a.get(str2).longValue() : 2147483647L);
    }

    private String[] a(ConcurrentHashMap<String, Long> concurrentHashMap) {
        if (concurrentHashMap == null) {
            return null;
        }
        String[] strArr = new String[concurrentHashMap.size()];
        int i = 0;
        for (String str : concurrentHashMap.keySet()) {
            strArr[i] = new String(str);
            i++;
        }
        for (int i2 = 0; i2 < strArr.length - 1; i2++) {
            int i3 = 0;
            while (i3 < (strArr.length - i2) - 1) {
                int i4 = i3 + 1;
                if (concurrentHashMap.get(strArr[i3]).longValue() > concurrentHashMap.get(strArr[i4]).longValue()) {
                    String str2 = strArr[i3];
                    strArr[i3] = strArr[i4];
                    strArr[i4] = str2;
                }
                i3 = i4;
            }
        }
        return strArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        String[] a;
        if (this.f45a == null || this.f45a.length == 0) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(this.f45a.length);
        for (int i = 0; i < this.f45a.length; i++) {
            com.alibaba.sdk.android.httpdns.c.a().execute(new g(this.f45a[i], this.port, countDownLatch, this.f44a));
        }
        try {
            countDownLatch.await(10000L, TimeUnit.MILLISECONDS);
            if (this.a == null || (a = a(this.f44a)) == null || a.length == 0) {
                return;
            }
            this.a.a(this.i, a(a));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
