package com.alibaba.mtl.log.a;

import android.text.TextUtils;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;
import org.json.JSONObject;

/* compiled from: SystemConfig.java */
/* loaded from: classes11.dex */
public class e {
    public static void j(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SYSTEM")) {
                    i.a("SystemConfig", "server system config ", str);
                    JSONObject optJSONObject = jSONObject.optJSONObject("SYSTEM");
                    if (optJSONObject != null) {
                        try {
                            if (optJSONObject.has("bg_interval")) {
                                a.h(optJSONObject.getInt("bg_interval") + "");
                            }
                        } catch (Throwable th) {
                        }
                        try {
                            if (optJSONObject.has("fg_interval")) {
                                a.i(optJSONObject.getInt("fg_interval") + "");
                            }
                        } catch (Throwable th2) {
                        }
                        i.a("SystemConfig", "UTDC.bSendToNewLogStore:", Boolean.valueOf(com.alibaba.mtl.log.a.bL));
                        i.a("SystemConfig", "Config.BACKGROUND_PERIOD:", Long.valueOf(a.b()));
                        i.a("SystemConfig", "Config.FOREGROUND_PERIOD:", Long.valueOf(a.a()));
                        try {
                            if (optJSONObject.has("discard")) {
                                int i = optJSONObject.getInt("discard");
                                if (i == 1) {
                                    a.bR = true;
                                    com.alibaba.mtl.log.f.a.ba().stop();
                                } else if (i == 0) {
                                    a.bR = false;
                                    com.alibaba.mtl.log.f.a.ba().start();
                                }
                            } else if (a.bR) {
                                a.bR = false;
                                com.alibaba.mtl.log.f.a.ba().start();
                            }
                        } catch (Throwable th3) {
                        }
                        try {
                            if (optJSONObject.has("cdb") && optJSONObject.getInt("cdb") > ai()) {
                                s.aW().b(new Runnable() { // from class: com.alibaba.mtl.log.a.e.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        com.alibaba.mtl.log.c.c.aA().clear();
                                    }
                                });
                            }
                        } catch (Throwable th4) {
                        }
                    }
                }
            } catch (Throwable th5) {
                i.a("SystemConfig", "updateconfig", th5);
            }
        }
    }

    public static int ai() {
        JSONObject jSONObject;
        String h = a.h();
        if (TextUtils.isEmpty(h)) {
            return 0;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(h);
            if (jSONObject2.has("SYSTEM") && (jSONObject = jSONObject2.getJSONObject("SYSTEM")) != null && jSONObject.has("cdb")) {
                return jSONObject.getInt("cdb");
            }
            return 0;
        } catch (Throwable th) {
            return 0;
        }
    }
}
