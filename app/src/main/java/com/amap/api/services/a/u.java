package com.amap.api.services.a;

import android.content.Context;
import android.os.Looper;
import java.util.List;

/* compiled from: ExceptionLogProcessor.java */
/* loaded from: classes11.dex */
public class u extends v {
    private static boolean a = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public u(int i) {
        super(i);
    }

    @Override // com.amap.api.services.a.v
    protected boolean a(Context context) {
        if (g.s(context) == 1 && a) {
            a = false;
            synchronized (Looper.getMainLooper()) {
                ai aiVar = new ai(context);
                aj bp = aiVar.bp();
                if (bp == null) {
                    return true;
                }
                if (bp.b()) {
                    bp.b(false);
                    aiVar.a(bp);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override // com.amap.api.services.a.v
    protected String h(List<l> list) {
        return null;
    }
}
