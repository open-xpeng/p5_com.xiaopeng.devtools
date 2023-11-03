package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread;

/* compiled from: DynamicExceptionHandler.java */
/* loaded from: classes11.dex */
public class as implements Thread.UncaughtExceptionHandler {
    private static as fg;
    private Thread.UncaughtExceptionHandler eI = Thread.getDefaultUncaughtExceptionHandler();
    private Context fh;
    private l fi;

    private as(Context context, l lVar) {
        this.fh = context.getApplicationContext();
        this.fi = lVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized as f(Context context, l lVar) {
        as asVar;
        synchronized (as.class) {
            if (fg == null) {
                fg = new as(context, lVar);
            }
            asVar = fg;
        }
        return asVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        String a = m.a(th);
        try {
            if (!TextUtils.isEmpty(a)) {
                if ((a.contains("amapdynamic") || a.contains("admic")) && a.contains("com.amap.api")) {
                    x xVar = new x(this.fh, at.br());
                    if (a.contains("loc")) {
                        aq.a(xVar, this.fh, "loc");
                    }
                    if (a.contains("navi")) {
                        aq.a(xVar, this.fh, "navi");
                    }
                    if (a.contains("sea")) {
                        aq.a(xVar, this.fh, "sea");
                    }
                    if (a.contains("2dmap")) {
                        aq.a(xVar, this.fh, "2dmap");
                    }
                    if (a.contains("3dmap")) {
                        aq.a(xVar, this.fh, "3dmap");
                    }
                } else if (a.contains("com.autonavi.aps.amapapi.offline")) {
                    aq.a(new x(this.fh, at.br()), this.fh, "OfflineLocation");
                } else if (a.contains("com.data.carrier_v4")) {
                    aq.a(new x(this.fh, at.br()), this.fh, "Collection");
                }
            }
        } catch (Throwable th2) {
            o.a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
        if (this.eI != null) {
            this.eI.uncaughtException(thread, th);
        }
    }
}
