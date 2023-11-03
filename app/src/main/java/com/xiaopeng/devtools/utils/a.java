package com.xiaopeng.devtools.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import com.xiaopeng.devtools.MyApplication;
import java.util.Iterator;

/* compiled from: ActivityUtil.java */
/* loaded from: classes12.dex */
public class a {
    public static void a(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(335544320);
        context.startActivity(intent);
    }

    public static boolean kY() {
        ActivityManager activityManager = (ActivityManager) MyApplication.getContext().getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        try {
            for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
                com.xiaopeng.lib.utils.c.h("ActivityUtil", "will finish and remove task: id=" + appTask.getTaskInfo().id);
                appTask.finishAndRemoveTask();
            }
            return true;
        } catch (SecurityException e) {
            com.xiaopeng.lib.utils.c.i("ActivityUtil", e.toString());
            return false;
        }
    }

    public static boolean cp(String str) {
        boolean z;
        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) MyApplication.getContext().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
        while (true) {
            if (it.hasNext()) {
                if (str.equals(it.next().service.getClassName())) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        com.xiaopeng.lib.utils.c.i("ActivityUtil", "[%s ] isRunService : %s", str, Boolean.valueOf(z));
        return z;
    }
}
