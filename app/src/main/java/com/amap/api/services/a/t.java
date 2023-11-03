package com.amap.api.services.a;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

/* compiled from: CrashLogProcessor.java */
/* loaded from: classes11.dex */
public class t extends v {
    private static boolean a = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public t(int i) {
        super(i);
    }

    @Override // com.amap.api.services.a.v
    protected boolean a(Context context) {
        if (a) {
            a = false;
            synchronized (Looper.getMainLooper()) {
                ai aiVar = new ai(context);
                aj bp = aiVar.bp();
                if (bp == null) {
                    return true;
                }
                if (bp.a()) {
                    bp.a(false);
                    aiVar.a(bp);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override // com.amap.api.services.a.v
    protected String a(String str) {
        String a2 = m.a(new Date().getTime());
        return j.c(str + a2);
    }

    @Override // com.amap.api.services.a.v
    protected String h(List<l> list) {
        return null;
    }
}
