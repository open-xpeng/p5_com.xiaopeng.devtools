package com.alibaba.mtl.appmonitor;

import android.app.Application;
import android.os.RemoteException;
import com.alibaba.mtl.appmonitor.a;
import com.alibaba.mtl.appmonitor.b;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.log.d.i;
import java.util.Map;

/* compiled from: Monitor.java */
/* loaded from: classes11.dex */
public class c extends b.a {
    private Application ax;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Application application) {
        this.ax = application;
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void init() throws RemoteException {
        a.a(this.ax);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void destroy() throws RemoteException {
        a.destroy();
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void p() throws RemoteException {
        a.p();
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void h(int i) throws RemoteException {
        a.h(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void f(boolean z) throws RemoteException {
        a.f(z);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void a(int i, int i2) throws RemoteException {
        a.a(r(i), i2);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void a(boolean z, String str, String str2, String str3) throws RemoteException {
        a.a(z, str, str2, str3);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void setChannel(String str) throws RemoteException {
        a.setChannel(str);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void turnOnRealTimeDebug(Map map) throws RemoteException {
        a.turnOnRealTimeDebug(map);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void turnOffRealTimeDebug() throws RemoteException {
        a.turnOffRealTimeDebug();
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void j(int i) throws RemoteException {
        a.b.f(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void k(int i) throws RemoteException {
        a.b.h(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public boolean k(String str, String str2) throws RemoteException {
        return a.b.j(str, str2);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, double d, Map map) throws RemoteException {
        a.b.a(str, str2, d, map);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, String str3, double d, Map map) throws RemoteException {
        a.b.a(str, str2, str3, d, map);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void n(int i) throws RemoteException {
        a.C0012a.f(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void o(int i) throws RemoteException {
        a.C0012a.h(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public boolean m(String str, String str2) throws RemoteException {
        return a.C0012a.j(str, str2);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, Map map) throws RemoteException {
        a.C0012a.a(str, str2, map);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void d(String str, String str2, String str3, Map map) throws RemoteException {
        a.C0012a.c(str, str2, str3, map);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, String str3, String str4, Map map) throws RemoteException {
        a.C0012a.a(str, str2, str3, str4, map);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, String str3, String str4, String str5, Map map) throws RemoteException {
        a.C0012a.a(str, str2, str3, str4, str5, map);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void l(int i) throws RemoteException {
        a.c.f(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void m(int i) throws RemoteException {
        a.c.h(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public boolean l(String str, String str2) throws RemoteException {
        return a.c.j(str, str2);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, double d) throws RemoteException {
        a.c.a(str, str2, d);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void i(int i) throws RemoteException {
        a.f(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, MeasureSet measureSet) throws RemoteException {
        a.a(str, str2, measureSet);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, MeasureSet measureSet, boolean z) throws RemoteException {
        a.a(str, str2, measureSet, z);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet) throws RemoteException {
        a.a(str, str2, measureSet, dimensionSet);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void c(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) throws RemoteException {
        a.b(str, str2, measureSet, dimensionSet, z);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void e(String str, String str2, String str3) throws RemoteException {
        a.d.c(str, str2, str3);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void f(String str, String str2, String str3) throws RemoteException {
        a.d.d(str, str2, str3);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void p(int i) throws RemoteException {
        a.d.f(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void q(int i) throws RemoteException {
        a.d.h(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public boolean n(String str, String str2) throws RemoteException {
        return a.d.j(str, str2);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void c(String str, String str2, double d, Map map) throws RemoteException {
        a.d.a(str, str2, d, map);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, DimensionValueSet dimensionValueSet, double d, Map map) throws RemoteException {
        a.d.a(str, str2, dimensionValueSet, d, map);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(String str, String str2, DimensionValueSet dimensionValueSet, MeasureValueSet measureValueSet, Map map) throws RemoteException {
        i.a("Monitor", "[stat_commit3]");
        a.d.a(str, str2, dimensionValueSet, measureValueSet, map);
    }

    private com.alibaba.mtl.appmonitor.a.f r(int i) {
        return com.alibaba.mtl.appmonitor.a.f.r(i);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void a(Transaction transaction, String str) throws RemoteException {
        e.c(transaction, str);
    }

    @Override // com.alibaba.mtl.appmonitor.b
    public void b(Transaction transaction, String str) throws RemoteException {
        e.d(transaction, str);
    }
}
