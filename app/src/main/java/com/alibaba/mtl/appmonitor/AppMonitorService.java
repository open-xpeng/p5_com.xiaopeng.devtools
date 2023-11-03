package com.alibaba.mtl.appmonitor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/* loaded from: classes11.dex */
public class AppMonitorService extends Service {
    b ab = null;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.ab == null) {
            this.ab = new c(getApplication());
        }
        return (IBinder) this.ab;
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (this.ab != null) {
            try {
                this.ab.p();
            } catch (RemoteException e) {
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        if (this.ab != null) {
            try {
                this.ab.p();
            } catch (RemoteException e) {
            }
        }
        super.onLowMemory();
    }
}
