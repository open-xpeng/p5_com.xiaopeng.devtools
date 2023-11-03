package com.alibaba.sdk.android.man;

import android.app.Activity;
import com.alibaba.sdk.android.man.util.MANLog;
import com.alibaba.sdk.android.man.util.UTWrapper;
import com.ut.mini.UTPageHitHelper;
import java.util.Map;

/* loaded from: classes11.dex */
public class MANPageHitHelper {
    private static final String TAG = MANTracker.class.getSimpleName();
    private volatile boolean isEnabled;

    private MANPageHitHelper() {
        this.isEnabled = true;
    }

    /* loaded from: classes11.dex */
    private static class Singleton {
        static MANPageHitHelper instance = new MANPageHitHelper();

        private Singleton() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static MANPageHitHelper getInstance() {
        return Singleton.instance;
    }

    public void pageAppear(Activity activity) {
        if (!this.isEnabled) {
            MANLog.Loge(TAG, "MAN init failed,can not work for now!");
            return;
        }
        UTPageHitHelper.getInstance().pageAppear(activity);
        UTWrapper.commitPageEvent("1");
    }

    public void pageDisAppear(Activity activity) {
        if (!this.isEnabled) {
            MANLog.Loge(TAG, "MAN init failed,can not work for now!");
            return;
        }
        UTPageHitHelper.getInstance().pageDisAppear(activity);
        UTWrapper.commitPageEvent("1");
    }

    public void updatePageProperties(Map<String, String> map) {
        if (!this.isEnabled) {
            MANLog.Loge(TAG, "MAN init failed,can not work for now!");
            return;
        }
        UTPageHitHelper.getInstance().updatePageProperties(map);
        UTWrapper.commitPageEvent("1");
    }

    public void setEnableStatus(boolean z) {
        this.isEnabled = z;
    }
}
