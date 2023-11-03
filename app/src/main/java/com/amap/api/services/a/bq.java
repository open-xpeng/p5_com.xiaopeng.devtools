package com.amap.api.services.a;

import com.amap.api.services.a.l;

/* compiled from: ConfigableConst.java */
/* loaded from: classes11.dex */
public class bq {
    public static final String[] a = {"com.amap.api.services"};

    public static String a() {
        if (com.amap.api.services.core.a.bH().getProtocol() == 1) {
            return "http://restapi.amap.com/v3";
        }
        return "https://restapi.amap.com/v3";
    }

    public static String c() {
        return com.amap.api.services.core.a.bH().getLanguage();
    }

    public static l l(boolean z) {
        try {
            return new l.a("sea", "3.5.0", "AMAP SDK Android Search 3.5.0").b(a).h(z).bm();
        } catch (au e) {
            br.a(e, "ConfigableConst", "getSDKInfo");
            return null;
        }
    }
}
