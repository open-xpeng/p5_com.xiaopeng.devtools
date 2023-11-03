package com.xiaopeng.commonfunc.utils;

import android.car.Car;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import org.greenrobot.eventbus.EventBus;

/* compiled from: CarHelper.java */
/* loaded from: classes11.dex */
public class c {
    private static final ServiceConnection oZ = new ServiceConnection() { // from class: com.xiaopeng.commonfunc.utils.c.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.xiaopeng.lib.utils.c.g("CarHelper", "onServiceConnected, name: " + componentName + ", service: " + iBinder);
            EventBus.getDefault().post(66442200);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            com.xiaopeng.lib.utils.c.g("CarHelper", "onServiceDisconnected, name: " + componentName);
        }
    };
    private static Car pa;

    public static Car ep() {
        return pa;
    }

    public static void init(Context context) {
        pa = Car.createCar(context, oZ);
        pa.connect();
    }

    public static void dO() {
        if (pa != null) {
            pa.disconnect();
        }
    }
}
