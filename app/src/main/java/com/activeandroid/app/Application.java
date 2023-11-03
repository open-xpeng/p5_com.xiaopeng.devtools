package com.activeandroid.app;

import com.activeandroid.ActiveAndroid;

/* loaded from: classes11.dex */
public class Application extends android.app.Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
