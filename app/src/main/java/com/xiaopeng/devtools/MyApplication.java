package com.xiaopeng.devtools;

import android.app.Application;
import android.car.Car;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.activeandroid.ActiveAndroid;
import com.xiaopeng.commonfunc.utils.b;
import com.xiaopeng.devtools.system.service.SecurityCheckService;
import com.xiaopeng.devtools.utils.j;
import com.xiaopeng.lib.framework.ipcmodule.IpcModuleEntry;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.ipcmodule.IIpcService;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.framework.netchannelmodule.common.TrafficeStaFlagInterceptor;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.f;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class MyApplication extends Application {
    public static MyApplication qx = null;
    private static Context sContext;
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.xiaopeng.devtools.MyApplication.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            c.g("DevTools", "onServiceConnected, name: " + componentName + ", service: " + iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            c.g("DevTools", "onServiceDisconnected, name: " + componentName);
        }
    };

    public static Context getContext() {
        return sContext;
    }

    public static Car ep() {
        return com.xiaopeng.commonfunc.utils.c.ep();
    }

    public static boolean eZ() {
        return false;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        long currentTimeMillis = System.currentTimeMillis();
        sContext = getApplicationContext();
        qx = this;
        if (getPackageName().endsWith(f.oP())) {
            try {
                c.f("DevTools", "start init application");
                fa();
                com.xiaopeng.commonfunc.utils.c.init(sContext);
                b.init(sContext);
                startService(new Intent(this, SecurityCheckService.class));
                ActiveAndroid.initialize(this);
                com.xiaopeng.lib.utils.d.b.init(this);
                com.xiaopeng.xui.a.a(this);
                j.X(this);
                com.xiaopeng.lib.http.b.a(this, true);
                fb();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            c.f("DevTools", "onCreate time = " + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        if (getPackageName().endsWith(f.oP())) {
            ActiveAndroid.dispose();
            com.xiaopeng.lib.http.b.destroy();
            com.xiaopeng.commonfunc.utils.c.dO();
        }
    }

    private void fa() {
        if (!com.xiaopeng.a.b.qi().aw(this)) {
            c.i("DevTools", "XML config parsing was failed");
        }
    }

    private void fb() {
        fc();
        Module.register(IpcModuleEntry.class, new IpcModuleEntry(this));
        Module.register(NetworkChannelsEntry.class, new NetworkChannelsEntry());
        Module.register(com.xiaopeng.datalog.a.class, new com.xiaopeng.datalog.a(this));
        ((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).config().applicationContext(this).addInterceptor(new TrafficeStaFlagInterceptor()).enableLogging().apply();
        fd().init();
    }

    private void fc() {
        try {
            EventBus.builder().sendNoSubscriberEvent(false).logNoSubscriberMessages(false).logSubscriberExceptions(false).installDefaultEventBus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IIpcService fd() {
        return (IIpcService) Module.get(IpcModuleEntry.class).get(IIpcService.class);
    }
}
