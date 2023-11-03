package com.xiaopeng.commonfunc.b.f;

import android.content.Context;
import com.xiaopeng.a.a;

/* compiled from: PsuKeyModel.java */
/* loaded from: classes11.dex */
public class d {
    public static String ow = com.xiaopeng.lib.utils.a.b.q("psu_host", "http://10.0.11.90", "http://pso-int.xiaopeng.com");
    private final com.xiaopeng.commonfunc.b.e.a og;
    private final c oz;
    private boolean ox = true;
    private boolean oy = false;
    private boolean oA = false;

    public d(Context context) {
        this.og = new com.xiaopeng.commonfunc.b.e.a(context);
        this.oz = new c(context);
    }

    public void onDestroy() {
    }

    public boolean ee() {
        return this.oz.aT(a.c.ec("PSO_KEY"));
    }
}
