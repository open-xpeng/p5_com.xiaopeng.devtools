package com.alibaba.mtl.appmonitor.e;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.t;
import com.alibaba.mtl.log.model.LogField;
import java.util.Map;

/* compiled from: UTAggregationPlugin.java */
/* loaded from: classes11.dex */
public class a {
    private static final String TAG = null;
    private static a bt;

    private a() {
    }

    public static synchronized a C() {
        a aVar;
        synchronized (a.class) {
            if (bt == null) {
                bt = new a();
            }
            aVar = bt;
        }
        return aVar;
    }

    public void a(Map<String, String> map) {
        if (map == null) {
            return;
        }
        i.a(TAG, "[sendToUT]:", " args:", map);
        if (com.alibaba.mtl.log.a.bL) {
            if (map != null) {
                com.alibaba.mtl.log.a.c(map.get(LogField.PAGE.toString()), map.get(LogField.EVENTID.toString()), map.get(LogField.ARG1.toString()), map.get(LogField.ARG2.toString()), map.get(LogField.ARG3.toString()), map);
                return;
            }
            return;
        }
        map.put("_fuamf", "yes");
        t.send(map);
    }
}
