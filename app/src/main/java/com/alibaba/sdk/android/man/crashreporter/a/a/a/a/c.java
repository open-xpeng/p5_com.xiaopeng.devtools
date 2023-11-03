package com.alibaba.sdk.android.man.crashreporter.a.a.a.a;

import android.os.Build;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import java.util.Map;

/* loaded from: classes11.dex */
public class c implements com.alibaba.sdk.android.man.crashreporter.a.a.a.b {
    private static String e() {
        if ((i.b((CharSequence) System.getProperty("java.vm.name")) && System.getProperty("java.vm.name").toLowerCase().contains("lemur")) || i.b((CharSequence) "ro.yunos.version")) {
            return "aliyunos";
        }
        return "Android";
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.a.a.c
    public void a(Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.OS, e());
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.OS_VERSION, Build.VERSION.RELEASE);
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.CODENAME, Build.VERSION.CODENAME);
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.BRAND, Build.BRAND);
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.DEVICE_MODEL, Build.MODEL);
    }
}
