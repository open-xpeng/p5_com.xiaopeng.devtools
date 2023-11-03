package com.alibaba.mtl.appmonitor;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BackgroundTrigger.java */
/* loaded from: classes11.dex */
public class f implements Runnable {
    private static boolean j = false;
    private static boolean l = false;
    private Application ax;
    private boolean k = true;

    @TargetApi(14)
    public static void a(Application application) {
        if (!j) {
            i.a("BackgroundTrigger", "init BackgroundTrigger");
            l = a(application.getApplicationContext());
            f fVar = new f(application);
            if (l) {
                s.aW().a(4, fVar, 60000L);
            } else if (Build.VERSION.SDK_INT >= 14) {
                fVar.getClass();
                application.registerActivityLifecycleCallbacks(new a(fVar));
            }
            j = true;
        }
    }

    public f(Application application) {
        this.ax = application;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = 0;
        i.a("BackgroundTrigger", "[bg check]");
        boolean b = com.alibaba.mtl.log.d.b.b(this.ax.getApplicationContext());
        if (this.k != b) {
            this.k = b;
            if (b) {
                j.A().B();
                com.alibaba.mtl.appmonitor.a.f[] values = com.alibaba.mtl.appmonitor.a.f.values();
                int length = values.length;
                while (i < length) {
                    com.alibaba.mtl.appmonitor.a.f fVar = values[i];
                    com.alibaba.mtl.appmonitor.a.a(fVar, fVar.v());
                    i++;
                }
                com.alibaba.mtl.log.a.ab();
            } else {
                com.alibaba.mtl.appmonitor.a.f[] values2 = com.alibaba.mtl.appmonitor.a.f.values();
                int length2 = values2.length;
                while (i < length2) {
                    com.alibaba.mtl.appmonitor.a.f fVar2 = values2[i];
                    com.alibaba.mtl.appmonitor.a.a(fVar2, fVar2.w());
                    i++;
                }
                com.alibaba.mtl.appmonitor.a.p();
                com.alibaba.mtl.log.a.aa();
            }
        }
        if (l) {
            s.aW().a(4, this, 60000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BackgroundTrigger.java */
    @TargetApi(14)
    /* loaded from: classes11.dex */
    public class a implements Application.ActivityLifecycleCallbacks {
        private Runnable a;

        a(Runnable runnable) {
            this.a = runnable;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            s.aW().E(4);
            s.aW().a(4, this.a, 60000L);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            s.aW().E(4);
            s.aW().a(4, this.a, 60000L);
        }
    }

    private static boolean a(Context context) {
        String a2 = com.alibaba.mtl.log.d.b.a(context);
        i.a("BackgroundTrigger", "[checkRuningProcess]:", a2);
        return (TextUtils.isEmpty(a2) || a2.indexOf(":") == -1) ? false : true;
    }
}
