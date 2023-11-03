package com.amap.api.services.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.services.busline.b;
import com.amap.api.services.busline.d;
import com.amap.api.services.c.a;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.cloud.b;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.d.a;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.a;
import com.amap.api.services.geocoder.b;
import com.amap.api.services.help.a;
import com.amap.api.services.poisearch.b;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.routepoisearch.a;
import com.amap.api.services.weather.c;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MessageHandler.java */
/* loaded from: classes11.dex */
public class bv extends Handler {
    private static bv gb;

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class a {
        public com.amap.api.services.busline.a gc;
        public b.a gd;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class b {
        public com.amap.api.services.busline.c ge;
        public d.a gf;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class c {
        public CloudItemDetail gg;
        public b.a gh;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class d {
        public b.a gh;
        public com.amap.api.services.cloud.a gi;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class e {
        public b.a ej;
        public com.amap.api.services.geocoder.a gj;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class f {
        public List<a.InterfaceC0029a> a;
        public com.amap.api.services.c.b gk;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class g {
        public PoiItem gl;
        public b.a gm;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class h {
        public b.a gm;
        public com.amap.api.services.poisearch.a gn;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class i {
        public b.a ej;
        public com.amap.api.services.geocoder.d go;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class j {
        public com.amap.api.services.routepoisearch.b gp;
        public a.InterfaceC0033a gq;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class k {
        public com.amap.api.services.weather.a gr;
        public c.a gs;
    }

    /* compiled from: MessageHandler.java */
    /* loaded from: classes11.dex */
    public static class l {
        public c.a gs;
        public com.amap.api.services.weather.b gt;
    }

    public static synchronized bv bD() {
        bv bvVar;
        synchronized (bv.class) {
            if (gb == null) {
                if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
                    gb = new bv();
                }
                gb = new bv(Looper.getMainLooper());
            }
            bvVar = gb;
        }
        return bvVar;
    }

    bv() {
    }

    bv(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            switch (message.arg1) {
                case 1:
                    k(message);
                    break;
                case 2:
                    h(message);
                    break;
                case 3:
                    j(message);
                    break;
                case 4:
                    i(message);
                    break;
                case 5:
                    g(message);
                    break;
                case 6:
                    f(message);
                    break;
                case 7:
                    e(message);
                    break;
                case 8:
                    d(message);
                    break;
                case 9:
                    c(message);
                    break;
                case 10:
                    b(message);
                    break;
                case 11:
                    a(message);
                    break;
                case 12:
                    l(message);
                    break;
                case 13:
                    m(message);
                    break;
                case 14:
                    n(message);
                    break;
            }
        } catch (Throwable th) {
            br.a(th, "MessageHandler", "handleMessage");
        }
    }

    private void a(Message message) {
        int i2 = message.arg2;
        a.InterfaceC0030a interfaceC0030a = (a.InterfaceC0030a) message.obj;
        String string = message.getData().getString("shareurlkey");
        if (interfaceC0030a == null) {
            return;
        }
        switch (message.what) {
            case 1100:
                interfaceC0030a.j(string, i2);
                return;
            case 1101:
                interfaceC0030a.k(string, i2);
                return;
            case 1102:
                interfaceC0030a.l(string, i2);
                return;
            case 1103:
                interfaceC0030a.m(string, i2);
                return;
            case 1104:
                interfaceC0030a.o(string, i2);
                return;
            case 1105:
                interfaceC0030a.n(string, i2);
                return;
            default:
                return;
        }
    }

    private void b(Message message) {
        List<a.InterfaceC0029a> list = (List) message.obj;
        if (list == null || list.size() == 0) {
            return;
        }
        for (a.InterfaceC0029a interfaceC0029a : list) {
            interfaceC0029a.ak(message.what);
        }
    }

    private void c(Message message) {
        List<a.InterfaceC0029a> list;
        f fVar = (f) message.obj;
        if (fVar == null || (list = fVar.a) == null || list.size() == 0) {
            return;
        }
        com.amap.api.services.c.b bVar = null;
        if (message.what == 1000) {
            bVar = fVar.gk;
        }
        for (a.InterfaceC0029a interfaceC0029a : list) {
            interfaceC0029a.a(bVar, message.what);
        }
    }

    private void d(Message message) {
        List<a.InterfaceC0029a> list = (List) message.obj;
        if (list == null || list.size() == 0) {
            return;
        }
        for (a.InterfaceC0029a interfaceC0029a : list) {
            interfaceC0029a.aj(message.what);
        }
    }

    private void e(Message message) {
        d.a aVar;
        b bVar = (b) message.obj;
        if (bVar == null || (aVar = bVar.gf) == null) {
            return;
        }
        com.amap.api.services.busline.c cVar = null;
        if (message.what == 1000) {
            cVar = bVar.ge;
        }
        aVar.a(cVar, message.what);
    }

    private void f(Message message) {
        g gVar;
        b.a aVar;
        Bundle data;
        if (message.what == 600) {
            h hVar = (h) message.obj;
            if (hVar != null && (aVar = hVar.gm) != null && (data = message.getData()) != null) {
                aVar.a(hVar.gn, data.getInt("errorCode"));
            }
        } else if (message.what != 602 || (gVar = (g) message.obj) == null) {
        } else {
            b.a aVar2 = gVar.gm;
            Bundle data2 = message.getData();
            if (data2 != null) {
                aVar2.a(gVar.gl, data2.getInt("errorCode"));
            }
        }
    }

    private void g(Message message) {
        a.InterfaceC0032a interfaceC0032a = (a.InterfaceC0032a) message.obj;
        if (interfaceC0032a == null) {
            return;
        }
        ArrayList arrayList = null;
        if (message.what == 1000) {
            arrayList = message.getData().getParcelableArrayList("result");
        }
        interfaceC0032a.b(arrayList, message.what);
    }

    private void h(Message message) {
        e eVar;
        b.a aVar;
        b.a aVar2;
        if (message.what == 201) {
            i iVar = (i) message.obj;
            if (iVar == null || (aVar2 = iVar.ej) == null) {
                return;
            }
            aVar2.a(iVar.go, message.arg2);
        } else if (message.what != 200 || (eVar = (e) message.obj) == null || (aVar = eVar.ej) == null) {
        } else {
            aVar.a(eVar.gj, message.arg2);
        }
    }

    private void i(Message message) {
        a.InterfaceC0031a interfaceC0031a = (a.InterfaceC0031a) message.obj;
        if (interfaceC0031a == null) {
            return;
        }
        interfaceC0031a.a((DistrictResult) message.getData().getParcelable("result"));
    }

    private void j(Message message) {
        b.a aVar;
        a aVar2 = (a) message.obj;
        if (aVar2 == null || (aVar = aVar2.gd) == null) {
            return;
        }
        com.amap.api.services.busline.a aVar3 = null;
        if (message.what == 1000) {
            aVar3 = aVar2.gc;
        }
        aVar.a(aVar3, message.what);
    }

    private void k(Message message) {
        Bundle data;
        RouteSearch.a aVar = (RouteSearch.a) message.obj;
        if (aVar == null) {
            return;
        }
        if (message.what == 100) {
            Bundle data2 = message.getData();
            if (data2 != null) {
                aVar.a((BusRouteResult) message.getData().getParcelable("result"), data2.getInt("errorCode"));
            }
        } else if (message.what == 101) {
            Bundle data3 = message.getData();
            if (data3 != null) {
                aVar.a((DriveRouteResult) message.getData().getParcelable("result"), data3.getInt("errorCode"));
            }
        } else if (message.what == 102) {
            Bundle data4 = message.getData();
            if (data4 != null) {
                aVar.a((WalkRouteResult) message.getData().getParcelable("result"), data4.getInt("errorCode"));
            }
        } else if (message.what == 103 && (data = message.getData()) != null) {
            aVar.a((RideRouteResult) message.getData().getParcelable("result"), data.getInt("errorCode"));
        }
    }

    private void l(Message message) {
        c cVar;
        if (message.what == 700) {
            d dVar = (d) message.obj;
            if (dVar == null) {
                return;
            }
            dVar.gh.a(dVar.gi, message.arg2);
        } else if (message.what != 701 || (cVar = (c) message.obj) == null) {
        } else {
            cVar.gh.a(cVar.gg, message.arg2);
        }
    }

    private void m(Message message) {
        k kVar;
        c.a aVar;
        Bundle data;
        c.a aVar2;
        Bundle data2;
        if (message.what == 1301) {
            l lVar = (l) message.obj;
            if (lVar != null && (aVar2 = lVar.gs) != null && (data2 = message.getData()) != null) {
                aVar2.a(lVar.gt, data2.getInt("errorCode"));
            }
        } else if (message.what == 1302 && (kVar = (k) message.obj) != null && (aVar = kVar.gs) != null && (data = message.getData()) != null) {
            aVar.a(kVar.gr, data.getInt("errorCode"));
        }
    }

    private void n(Message message) {
        a.InterfaceC0033a interfaceC0033a;
        Bundle data;
        j jVar = (j) message.obj;
        if (jVar != null && (interfaceC0033a = jVar.gq) != null && (data = message.getData()) != null) {
            interfaceC0033a.a(jVar.gp, data.getInt("errorCode"));
        }
    }
}
