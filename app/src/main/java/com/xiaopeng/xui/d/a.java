package com.xiaopeng.xui.d;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/* compiled from: XActivityUtils.java */
/* loaded from: classes13.dex */
public class a {
    public static void a(Activity activity) {
        if (activity != null) {
            try {
                boolean isChild = activity.isChild();
                activity.finish();
                if (!activity.isFinishing() && !isChild) {
                    startHome(activity);
                }
            } catch (Exception e) {
                f.h("ActivityUtils", "finish e=" + e);
            }
        }
    }

    public static boolean a(Activity activity, boolean z) {
        boolean z2 = false;
        if (activity == null) {
            return false;
        }
        try {
            boolean isChild = activity.isChild();
            boolean moveTaskToBack = activity.moveTaskToBack(z);
            if (!moveTaskToBack && !isChild) {
                try {
                    startHome(activity);
                } catch (Exception e) {
                    e = e;
                    z2 = moveTaskToBack;
                    f.h("ActivityUtils", "moveTaskToBack e=" + e);
                    return z2;
                }
            }
            return moveTaskToBack;
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static void startHome(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.addFlags(270532608);
            context.startActivity(intent);
        } catch (Exception e) {
            f.h("ActivityUtils", "startHome e=" + e);
        }
    }
}
