package com.alibaba.mtl.appmonitor.d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONObject;

/* compiled from: SampleRules.java */
/* loaded from: classes11.dex */
public class j {
    private static final String TAG = null;
    private static j bq;
    private Map<com.alibaba.mtl.appmonitor.a.f, g> bp = new HashMap();
    private String br;
    private int r;

    private j() {
        com.alibaba.mtl.appmonitor.a.f[] values;
        for (com.alibaba.mtl.appmonitor.a.f fVar : com.alibaba.mtl.appmonitor.a.f.values()) {
            if (fVar == com.alibaba.mtl.appmonitor.a.f.ALARM) {
                this.bp.put(fVar, new f(fVar, fVar.x()));
            } else {
                this.bp.put(fVar, new g(fVar, fVar.x()));
            }
        }
    }

    public static j A() {
        if (bq == null) {
            synchronized (j.class) {
                if (bq == null) {
                    bq = new j();
                }
            }
        }
        return bq;
    }

    public void a(Context context) {
        B();
    }

    public static boolean a(com.alibaba.mtl.appmonitor.a.f fVar, String str, String str2) {
        return A().b(fVar, str, str2, (Map<String, String>) null);
    }

    public static boolean a(com.alibaba.mtl.appmonitor.a.f fVar, String str, String str2, Map<String, String> map) {
        return A().b(fVar, str, str2, map);
    }

    public static boolean a(String str, String str2, Boolean bool, Map<String, String> map) {
        return A().b(str, str2, bool, map);
    }

    public boolean b(com.alibaba.mtl.appmonitor.a.f fVar, String str, String str2, Map<String, String> map) {
        g gVar = this.bp.get(fVar);
        if (gVar != null) {
            return gVar.a(this.r, str, str2, map);
        }
        return false;
    }

    public boolean b(String str, String str2, Boolean bool, Map<String, String> map) {
        g gVar = this.bp.get(com.alibaba.mtl.appmonitor.a.f.ALARM);
        if (gVar != null && (gVar instanceof f)) {
            return ((f) gVar).a(this.r, str, str2, bool, map);
        }
        return false;
    }

    public void B() {
        this.r = new Random(System.currentTimeMillis()).nextInt(com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.r);
    }

    public void b(String str) {
        com.alibaba.mtl.appmonitor.a.f[] values;
        com.alibaba.mtl.log.d.i.a("SampleRules", "config:", str);
        synchronized (this) {
            if (!com.alibaba.mtl.appmonitor.f.b.d(str) && (this.br == null || !this.br.equals(str))) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    for (com.alibaba.mtl.appmonitor.a.f fVar : com.alibaba.mtl.appmonitor.a.f.values()) {
                        JSONObject optJSONObject = jSONObject.optJSONObject(fVar.toString());
                        g gVar = this.bp.get(fVar);
                        if (optJSONObject != null && gVar != null) {
                            com.alibaba.mtl.log.d.i.a(TAG, fVar, optJSONObject);
                            gVar.b(optJSONObject);
                        }
                    }
                    this.br = str;
                } catch (Throwable th) {
                }
            }
        }
    }

    public void b(com.alibaba.mtl.appmonitor.a.f fVar, int i) {
        g gVar = this.bp.get(fVar);
        if (gVar != null) {
            gVar.h(i);
        }
    }
}
