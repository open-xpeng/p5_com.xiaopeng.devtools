package com.alibaba.sdk.android.utils;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.sdk.android.utils.crashdefend.SDKMessageCallback;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class AlicloudTrackerManager {
    private static AlicloudTrackerManager a = null;

    /* renamed from: a  reason: collision with other field name */
    private c f105a = new c();

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.utils.crashdefend.b f106a;
    private Map<String, AlicloudTracker> e;

    private AlicloudTrackerManager(Application application) {
        this.f106a = null;
        HashMap hashMap = new HashMap(4);
        hashMap.put("kVersion", "2.0.0");
        hashMap.put("packageName", application.getPackageName());
        this.f105a.a(application, hashMap);
        this.e = new HashMap();
        this.f106a = com.alibaba.sdk.android.utils.crashdefend.b.a(application, this.f105a);
    }

    public static synchronized AlicloudTrackerManager getInstance(Application application) {
        synchronized (AlicloudTrackerManager.class) {
            if (application == null) {
                return null;
            }
            if (a == null) {
                a = new AlicloudTrackerManager(application);
            }
            return a;
        }
    }

    public AlicloudTracker getTracker(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e("AlicloudTrackerManager", "sdkId or sdkVersion is null");
            return null;
        }
        String str3 = str + str2;
        if (this.e.containsKey(str3)) {
            return this.e.get(str3);
        }
        AlicloudTracker alicloudTracker = new AlicloudTracker(this.f105a, str, str2);
        this.e.put(str3, alicloudTracker);
        return alicloudTracker;
    }

    public boolean registerCrashDefend(String str, String str2, int i, int i2, SDKMessageCallback sDKMessageCallback) {
        if (this.f106a != null) {
            com.alibaba.sdk.android.utils.crashdefend.c cVar = new com.alibaba.sdk.android.utils.crashdefend.c();
            cVar.f113c = str;
            cVar.f114d = str2;
            cVar.b = i;
            cVar.c = i2;
            return this.f106a.m66a(cVar, sDKMessageCallback);
        }
        return false;
    }

    public void unregisterCrashDefend(String str, String str2) {
        this.f106a.d(str, str2);
    }
}
