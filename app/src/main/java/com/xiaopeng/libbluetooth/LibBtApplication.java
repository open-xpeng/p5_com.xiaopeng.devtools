package com.xiaopeng.libbluetooth;

import android.app.Application;

/* loaded from: classes12.dex */
public abstract class LibBtApplication extends Application {
    private e XE;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        this.XE = e.pv();
        this.XE.setContext(this);
        this.XE.pw();
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        this.XE.px();
    }
}
