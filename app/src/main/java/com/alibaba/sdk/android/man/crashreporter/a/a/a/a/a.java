package com.alibaba.sdk.android.man.crashreporter.a.a.a.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import java.util.Map;

/* loaded from: classes11.dex */
public final class a implements com.alibaba.sdk.android.man.crashreporter.a.a.a.b {
    private ComponentName a;

    /* renamed from: a  reason: collision with other field name */
    private Context f61a;

    /* renamed from: a  reason: collision with other field name */
    com.alibaba.sdk.android.man.crashreporter.a.b f62a;

    /* renamed from: a  reason: collision with other field name */
    com.alibaba.sdk.android.man.crashreporter.d.c f63a;

    /* renamed from: a  reason: collision with other field name */
    private Object f64a = new Object();
    private String q;

    public a(Context context, com.alibaba.sdk.android.man.crashreporter.d.c cVar, com.alibaba.sdk.android.man.crashreporter.a.b bVar) {
        this.f63a = null;
        this.f62a = null;
        this.f61a = context;
        a();
        this.f63a = cVar;
        this.f62a = bVar;
    }

    @TargetApi(14)
    private void a() {
        if (Build.VERSION.SDK_INT >= 14) {
            if (this.f61a.getApplicationContext() instanceof Application) {
                ((Application) this.f61a.getApplicationContext()).registerActivityLifecycleCallbacks(new C0020a());
                return;
            }
            return;
        }
        com.alibaba.sdk.android.man.crashreporter.b.a.g(String.format("build version %s not suppert registerActivityLifecycleCallbacks, registerActivityLifecycleCallbacks failed", Integer.valueOf(Build.VERSION.SDK_INT)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(14)
    /* renamed from: com.alibaba.sdk.android.man.crashreporter.a.a.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0020a implements Application.ActivityLifecycleCallbacks {
        C0020a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            a.this.a = activity.getComponentName();
            a.this.q = "onActivityCreated";
            a.this.q = String.format("%s:%s", "onActivityCreated", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityCreated");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = "onActivityDestroyed";
            a.this.q = String.format("%s:%s", "onActivityDestroyed", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityDestroyed");
            synchronized (a.this.f64a) {
                if (a.this.f63a != null) {
                    a.this.a(2);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = "onActivityStopped";
            a.this.q = String.format("%s:%s", "onActivityStopped", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityStopped");
            synchronized (a.this.f64a) {
                if (a.this.f63a != null) {
                    a.this.a(2);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = "onActivityResumed";
            a.this.q = String.format("%s:%s", "onActivityResumed", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityResumed");
            synchronized (a.this.f64a) {
                if (a.this.f63a != null) {
                    a.this.a(1);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = String.format("%s:%s", "onActivityStarted", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityStarted");
            synchronized (a.this.f64a) {
                if (a.this.f63a != null) {
                    a.this.a(1);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = String.format("%s:%s", "onActivityPaused", Long.valueOf(System.currentTimeMillis()));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            a.this.a = activity.getComponentName();
            a.this.q = String.format("%s:%s", "onActivitySaveInstanceState", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivitySaveInstanceState");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        try {
            BaseDataContent a = this.f63a.a();
            if (a == null) {
                a = new BaseDataContent();
            }
            if (i == 2) {
                this.f62a.a(MotuCrashReporter.getInstance().getConfigure(), a, 2);
            } else if (i == 1) {
                this.f62a.a(MotuCrashReporter.getInstance().getConfigure(), a, 1);
            }
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("write app status err", e);
        }
    }

    private String c() {
        String str = "";
        if (this.f61a != null) {
            try {
                PackageManager packageManager = this.f61a.getPackageManager();
                if (packageManager != null && this.a != null) {
                    try {
                        ActivityInfo activityInfo = packageManager.getActivityInfo(this.a, 128);
                        if (activityInfo != null && activityInfo.metaData != null) {
                            str = activityInfo.metaData.getString("bundleLocation");
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        com.alibaba.sdk.android.man.crashreporter.b.a.d("get bundle failed.", e);
                    }
                }
            } catch (Exception e2) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("system error, getBundle failed", e2);
            }
        }
        return str != null ? str : "";
    }

    private String d() {
        return this.a != null ? this.a.getClassName() : "";
    }

    public String b() {
        return !i.a((CharSequence) this.q) ? this.q : "";
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.a.a.c
    public void a(Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.ACTIVITY, d());
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.ACTIVITY_STATUS, b());
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.BUNDLE, c());
    }
}
