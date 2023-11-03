package com.alibaba.sdk.android.man;

/* loaded from: classes11.dex */
public class MANServiceProvider implements MANService {
    private MANServiceProvider() {
    }

    /* loaded from: classes11.dex */
    private static class Singleton {
        static MANService instance = new MANServiceProvider();

        private Singleton() {
        }
    }

    public static MANService getService() {
        return Singleton.instance;
    }

    @Override // com.alibaba.sdk.android.man.MANService
    public MANAnalytics getMANAnalytics() {
        return MANAnalytics.getInstance();
    }

    @Override // com.alibaba.sdk.android.man.MANService
    public MANPageHitHelper getMANPageHitHelper() {
        return MANPageHitHelper.getInstance();
    }
}
