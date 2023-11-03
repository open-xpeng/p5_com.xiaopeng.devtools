package com.alibaba.mtl.appmonitor;

import java.util.HashMap;
import java.util.Map;

/* compiled from: SdkMeta.java */
/* loaded from: classes11.dex */
public class d {
    private static final Map<String, String> d = new HashMap();

    public static Map<String, String> q() {
        com.alibaba.mtl.log.a.getContext();
        if (!d.containsKey("sdk-version")) {
            d.put("sdk-version", "2.6.4.10_for_bc");
        }
        return d;
    }

    static {
        d.put("sdk-version", "2.6.4.10_for_bc");
    }
}
