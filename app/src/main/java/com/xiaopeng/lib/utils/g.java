package com.xiaopeng.lib.utils;

import android.content.Context;
import android.content.Intent;
import java.lang.ref.WeakReference;

/* compiled from: SendBroadCastUtil.java */
/* loaded from: classes12.dex */
public class g {
    private static g Wq;
    private WeakReference<Context> Wr;

    private g(Context context) {
        this.Wr = new WeakReference<>(context);
    }

    public static synchronized g au(Context context) {
        g gVar;
        synchronized (g.class) {
            if (Wq == null || !Wq.oQ()) {
                Wq = new g(context);
            }
            gVar = Wq;
        }
        return gVar;
    }

    private boolean oQ() {
        if (this.Wr != null && this.Wr.get() != null) {
            return true;
        }
        return false;
    }

    public void dI(String str) {
        c.g("SendBroadCastUtil", "action:" + str);
        Intent intent = new Intent();
        intent.setAction(str);
        Context context = this.Wr.get();
        if (context != null) {
            context.sendBroadcast(intent);
        }
    }
}
