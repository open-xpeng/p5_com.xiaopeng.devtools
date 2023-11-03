package com.alibaba.sdk.android.man.crashreporter.a.a.a.a;

import java.util.Map;

/* loaded from: classes11.dex */
public class d implements com.alibaba.sdk.android.man.crashreporter.a.a.a.b {
    private final com.alibaba.sdk.android.man.crashreporter.global.a a;

    public d(com.alibaba.sdk.android.man.crashreporter.global.a aVar) {
        this.a = aVar;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.a.a.c
    public void a(Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
        if (com.alibaba.sdk.android.man.crashreporter.global.a.SYS_LOG.equals(this.a)) {
            map.put(com.alibaba.sdk.android.man.crashreporter.global.a.SYS_LOG, com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.a("", false));
        } else if (com.alibaba.sdk.android.man.crashreporter.global.a.EVENTS_LOG.equals(this.a)) {
            map.put(com.alibaba.sdk.android.man.crashreporter.global.a.EVENTS_LOG, com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.a("events", true));
        } else if (com.alibaba.sdk.android.man.crashreporter.global.a.RADIO_LOG.equals(this.a)) {
            map.put(com.alibaba.sdk.android.man.crashreporter.global.a.RADIO_LOG, com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.a("radios", true));
        }
    }
}
