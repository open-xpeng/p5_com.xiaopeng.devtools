package com.ut.mini.sdkevents;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import com.alibaba.mtl.log.b;
import com.alibaba.mtl.log.c;
import com.alibaba.mtl.log.d.i;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTInterfaceCallDelegate;
import com.ut.mini.UTTracker;
import com.ut.mini.core.appstatus.UTMCAppStatusCallbacks;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import com.ut.mini.plugin.UTPlugin;
import java.util.List;

/* loaded from: classes11.dex */
public class UTMI1010_2001Event extends UTPlugin implements UTMCAppStatusCallbacks {
    private long C = 0;
    private long D = 0;
    private long E = 0;

    @Override // com.ut.mini.plugin.UTPlugin
    public int[] returnRequiredMsgIds() {
        return new int[]{3};
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    @Override // com.ut.mini.plugin.UTPlugin
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onPluginMsgArrivedFromSDK(int r5, java.lang.Object r6) {
        /*
            r4 = this;
            r0 = 3
            if (r5 != r0) goto L5c
            java.util.Map r6 = (java.util.Map) r6
            com.alibaba.mtl.log.model.LogField r5 = com.alibaba.mtl.log.model.LogField.EVENTID
            java.lang.String r5 = r5.toString()
            boolean r5 = r6.containsKey(r5)
            if (r5 == 0) goto L5c
            com.alibaba.mtl.log.model.LogField r5 = com.alibaba.mtl.log.model.LogField.EVENTID
            java.lang.String r5 = r5.toString()
            java.lang.Object r5 = r6.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r0 = "2001"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L5c
        L26:
            com.alibaba.mtl.log.model.LogField r5 = com.alibaba.mtl.log.model.LogField.ARG3
            java.lang.String r5 = r5.toString()
            boolean r5 = r6.containsKey(r5)
            r0 = 0
            if (r5 == 0) goto L49
            com.alibaba.mtl.log.model.LogField r5 = com.alibaba.mtl.log.model.LogField.ARG3
            java.lang.String r5 = r5.toString()
            java.lang.Object r5 = r6.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            long r5 = java.lang.Long.parseLong(r5)     // Catch: java.lang.Exception -> L45
            goto L4a
        L45:
            r5 = move-exception
            r5.printStackTrace()
        L49:
            r5 = r0
        L4a:
            long r2 = r4.C
            long r2 = r2 + r5
            r4.C = r2
            boolean r5 = m()
            if (r5 == 0) goto L5c
            long r5 = r4.C
            r4.a(r5)
            r4.C = r0
        L5c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.sdkevents.UTMI1010_2001Event.onPluginMsgArrivedFromSDK(int, java.lang.Object):void");
    }

    private static boolean m() {
        String packageName;
        ActivityManager activityManager;
        ComponentName componentName;
        try {
            Context context = b.aj().getContext();
            if (context != null && (packageName = context.getPackageName()) != null && (activityManager = (ActivityManager) context.getSystemService("activity")) != null) {
                try {
                    List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
                    if (runningTasks != null && runningTasks.size() > 0 && (componentName = runningTasks.get(0).topActivity) != null) {
                        if (packageName.contains(componentName.getPackageName())) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    private void a(long j) {
        if (!c.ax().d()) {
            long j2 = 0;
            if (j > 0) {
                if (0 != this.E) {
                    j2 = SystemClock.elapsedRealtime() - this.E;
                }
                UTOriginalCustomHitBuilder uTOriginalCustomHitBuilder = new UTOriginalCustomHitBuilder("UT", 1010, "" + j, "" + j2, null, null);
                UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
                if (defaultTracker != null) {
                    defaultTracker.send(uTOriginalCustomHitBuilder.build());
                } else {
                    i.a("Record app display event error", "Fatal Error,must call setRequestAuthentication method first.");
                }
            }
        }
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onSwitchBackground() {
        a(SystemClock.elapsedRealtime() - this.D);
        this.E = SystemClock.elapsedRealtime();
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onSwitchForeground() {
        this.D = SystemClock.elapsedRealtime();
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityPaused(Activity activity) {
        UTInterfaceCallDelegate.pageDisAppearByAuto(activity);
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityResumed(Activity activity) {
        UTInterfaceCallDelegate.pageAppearByAuto(activity);
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // com.ut.mini.core.appstatus.UTMCAppStatusCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
