package com.alibaba.mtl.appmonitor.f;

import com.alibaba.mtl.appmonitor.a.d;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.a.h;
import com.alibaba.mtl.appmonitor.model.UTDimensionValueSet;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.model.LogField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: UTUtil.java */
/* loaded from: classes11.dex */
public class c {
    public static void b(Map<UTDimensionValueSet, List<d>> map) {
        Integer Z;
        for (Map.Entry<UTDimensionValueSet, List<d>> entry : map.entrySet()) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            UTDimensionValueSet key = entry.getKey();
            List<d> value = entry.getValue();
            if (value.size() != 0 && (Z = key.Z()) != null) {
                f r = f.r(Z.intValue());
                int i = 0;
                h hVar = (h) com.alibaba.mtl.appmonitor.c.a.y().a(h.class, new Object[0]);
                hVar.e = Z.intValue();
                if (key.getMap() != null) {
                    hVar.aV.putAll(key.getMap());
                }
                HashMap hashMap = new HashMap();
                hashMap.put("meta", com.alibaba.mtl.appmonitor.d.q());
                com.alibaba.mtl.appmonitor.c.d dVar = (com.alibaba.mtl.appmonitor.c.d) com.alibaba.mtl.appmonitor.c.a.y().a(com.alibaba.mtl.appmonitor.c.d.class, new Object[0]);
                for (d dVar2 : value) {
                    dVar.put(dVar2.r());
                    if (i == 0) {
                        sb.append(dVar2.o);
                        sb2.append(dVar2.p);
                    } else {
                        sb.append(",");
                        sb.append(dVar2.o);
                        sb2.append(",");
                        sb2.append(dVar2.p);
                    }
                    i++;
                    com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) dVar2);
                }
                hashMap.put("data", dVar);
                hVar.aV.put(r.m15a(), new JSONObject(hashMap).toString());
                String sb3 = sb.toString();
                String sb4 = sb2.toString();
                hVar.aV.put(LogField.ARG1.toString(), sb3);
                hVar.aV.put(LogField.ARG2.toString(), sb4);
                hVar.v = sb3;
                hVar.w = sb4;
                b(hVar);
                com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) dVar);
            }
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) key);
        }
    }

    public static void a(UTDimensionValueSet uTDimensionValueSet, d dVar) {
        Integer Z = uTDimensionValueSet.Z();
        if (Z != null) {
            f r = f.r(Z.intValue());
            h hVar = (h) com.alibaba.mtl.appmonitor.c.a.y().a(h.class, new Object[0]);
            hVar.e = 6699;
            if (uTDimensionValueSet.getMap() != null) {
                hVar.aV.putAll(uTDimensionValueSet.getMap());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("meta", com.alibaba.mtl.appmonitor.d.q());
            hashMap.put("_event_id", Z);
            com.alibaba.mtl.appmonitor.c.d dVar2 = (com.alibaba.mtl.appmonitor.c.d) com.alibaba.mtl.appmonitor.c.a.y().a(com.alibaba.mtl.appmonitor.c.d.class, new Object[0]);
            dVar2.put(dVar.r());
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) dVar);
            hashMap.put("data", dVar2);
            hVar.aV.put(r.m15a(), new JSONObject(hashMap).toString());
            hVar.aV.put(LogField.EVENTID.toString(), String.valueOf(6699));
            b(hVar);
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) dVar2);
        }
    }

    public static void a(h hVar) {
        if (hVar == null) {
            return;
        }
        com.alibaba.mtl.log.a.c(hVar.u, String.valueOf(hVar.e), hVar.v, hVar.w, hVar.aU, hVar.aV);
        com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) hVar);
    }

    public static void b(h hVar) {
        i.a("UTUtil", "upload without flowback. args:", hVar.aV);
        com.alibaba.mtl.appmonitor.e.a.C().a(hVar.aV);
        com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) hVar);
    }
}
