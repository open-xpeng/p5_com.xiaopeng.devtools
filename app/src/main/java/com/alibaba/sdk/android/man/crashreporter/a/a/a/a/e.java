package com.alibaba.sdk.android.man.crashreporter.a.a.a.a;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import java.util.Map;

/* loaded from: classes11.dex */
public final class e implements com.alibaba.sdk.android.man.crashreporter.a.a.a.b {
    private Context a;

    public e(Context context) {
        this.a = context;
        a();
    }

    private void a() {
    }

    /* loaded from: classes11.dex */
    private class a implements ComponentCallbacks {
        private a() {
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.a.a.c
    public void a(Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
    }
}
