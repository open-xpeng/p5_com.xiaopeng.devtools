package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ClassLoaderFactory.java */
/* loaded from: classes11.dex */
public class ao {
    private static final ao fe = new ao();
    private final Map<String, an> b = new HashMap();

    private ao() {
    }

    public static ao bq() {
        return fe;
    }

    public synchronized an d(Context context, l lVar) throws Exception {
        an anVar;
        if (!b(lVar) || context == null) {
            throw new Exception("sdkInfo or context referance is null");
        }
        String a = lVar.a();
        anVar = this.b.get(a);
        if (anVar == null) {
            try {
                ar arVar = new ar(context.getApplicationContext(), lVar, true);
                try {
                    this.b.put(a, arVar);
                    as.f(context, lVar);
                    anVar = arVar;
                } catch (Throwable th) {
                    anVar = arVar;
                }
            } catch (Throwable th2) {
            }
        }
        return anVar;
    }

    public an e(Context context, l lVar) throws Exception {
        an anVar = this.b.get(lVar.a());
        if (anVar != null) {
            anVar.c(context, lVar);
            return anVar;
        }
        ar arVar = new ar(context.getApplicationContext(), lVar, false);
        arVar.c(context, lVar);
        this.b.put(lVar.a(), arVar);
        as.f(context, lVar);
        return arVar;
    }

    private boolean b(l lVar) {
        if (lVar != null && !TextUtils.isEmpty(lVar.b()) && !TextUtils.isEmpty(lVar.a())) {
            return true;
        }
        return false;
    }
}
