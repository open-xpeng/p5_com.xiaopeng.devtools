package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.log.d.i;

/* compiled from: TransactionDelegate.java */
/* loaded from: classes11.dex */
public class e {
    public static void c(Transaction transaction, String str) {
        try {
            if (!a.i || transaction == null) {
                return;
            }
            i.a("TransactionDelegate", "statEvent begin. module: ", transaction.o, " monitorPoint: ", transaction.p, " measureName: ", str);
            if (com.alibaba.mtl.appmonitor.a.f.STAT.isOpen() && (a.aw || j.a(com.alibaba.mtl.appmonitor.a.f.STAT, transaction.o, transaction.p))) {
                com.alibaba.mtl.appmonitor.a.e.u().a(transaction.r, transaction.a, transaction.o, transaction.p, str);
                a(transaction);
                return;
            }
            i.a("TransactionDelegate", "log discard", transaction.o, " monitorPoint: ", transaction.p, " measureName: ", str);
        } catch (Throwable th) {
            com.alibaba.mtl.appmonitor.b.b.m16a(th);
        }
    }

    private static void a(Transaction transaction) {
        if (transaction != null && transaction.az != null) {
            com.alibaba.mtl.appmonitor.a.e.u().a(transaction.r, transaction.a, transaction.o, transaction.p, DimensionValueSet.G().d(transaction.az));
        }
    }

    public static void d(Transaction transaction, String str) {
        try {
            if (!a.i || transaction == null) {
                return;
            }
            i.a("TransactionDelegate", "statEvent end. module: ", transaction.o, " monitorPoint: ", transaction.p, " measureName: ", str);
            if (com.alibaba.mtl.appmonitor.a.f.STAT.isOpen() && (a.aw || j.a(com.alibaba.mtl.appmonitor.a.f.STAT, transaction.o, transaction.p))) {
                a(transaction);
                com.alibaba.mtl.appmonitor.a.e.u().a(transaction.r, str, false, transaction.e);
                return;
            }
            i.a("TransactionDelegate", "log discard", transaction.o, " monitorPoint: ", transaction.p, " measureName: ", str);
        } catch (Throwable th) {
            com.alibaba.mtl.appmonitor.b.b.m16a(th);
        }
    }
}
