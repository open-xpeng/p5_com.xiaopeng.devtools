package com.alibaba.mtl.appmonitor.b;

import android.content.Context;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.a.h;
import com.alibaba.mtl.appmonitor.c.e;
import com.alibaba.mtl.appmonitor.d;
import com.alibaba.mtl.appmonitor.f.c;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: ExceptionEventBuilder.java */
/* loaded from: classes11.dex */
public class b {
    public static void a(Context context, Throwable th) {
        if (th != null) {
            try {
                h hVar = (h) com.alibaba.mtl.appmonitor.c.a.y().a(h.class, new Object[0]);
                hVar.e = f.ALARM.a();
                HashMap hashMap = new HashMap();
                hashMap.put("meta", d.q());
                com.alibaba.mtl.appmonitor.c.d dVar = (com.alibaba.mtl.appmonitor.c.d) com.alibaba.mtl.appmonitor.c.a.y().a(com.alibaba.mtl.appmonitor.c.d.class, new Object[0]);
                dVar.put(b(context, th));
                hashMap.put("data", dVar);
                hVar.aV.put(f.ALARM.m15a(), new JSONObject(hashMap).toString());
                hVar.v = "APPMONITOR";
                hVar.w = "sdk-exception";
                c.a(hVar);
                com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) dVar);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public static void m16a(Throwable th) {
        a(null, th);
    }

    private static JSONObject b(Context context, Throwable th) throws IOException {
        JSONObject jSONObject = (JSONObject) com.alibaba.mtl.appmonitor.c.a.y().a(e.class, new Object[0]);
        if (context != null) {
            try {
                jSONObject.put("pname", com.alibaba.mtl.log.d.b.a(context));
            } catch (Exception e) {
            }
        }
        jSONObject.put("page", "APPMONITOR");
        jSONObject.put("monitorPoint", "sdk-exception");
        jSONObject.put("arg", th.getClass().getSimpleName());
        jSONObject.put("successCount", 0);
        jSONObject.put("failCount", 1);
        ArrayList arrayList = new ArrayList();
        String a = a(th);
        if (a != null) {
            JSONObject jSONObject2 = (JSONObject) com.alibaba.mtl.appmonitor.c.a.y().a(e.class, new Object[0]);
            jSONObject2.put("errorCode", a);
            jSONObject2.put("errorCount", 1);
            arrayList.add(jSONObject2);
        }
        jSONObject.put("errors", arrayList);
        return jSONObject;
    }

    private static String a(Throwable th) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(th.getClass().getName());
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement.toString());
            }
        }
        String sb2 = sb.toString();
        if (com.alibaba.mtl.appmonitor.f.b.d(sb2)) {
            return th.toString();
        }
        return sb2;
    }
}
