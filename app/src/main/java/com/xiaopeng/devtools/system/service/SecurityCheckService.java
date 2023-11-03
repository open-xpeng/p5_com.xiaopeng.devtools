package com.xiaopeng.devtools.system.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.aftersales.RepairModeListener;
import com.xiaopeng.devtools.presenter.factorytest.b.a;
import com.xiaopeng.lib.framework.moduleinterface.ipcmodule.IIpcService;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class SecurityCheckService extends Service {
    private a Bw;
    private RepairModeListener Bx = new RepairModeListener() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$SecurityCheckService$u7MQk2T3pPWpw-yABUi3q1PgyaM
        public final void onRepairModeChanged(boolean z, int i) {
            SecurityCheckService.b(z, i);
        }
    };
    private AfterSalesManager vl;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        c.g("SecurityCheckService", "onCreate");
        this.Bw = new a();
        EventBus.getDefault().register(this);
        this.vl = (AfterSalesManager) getSystemService("xiaopeng_aftersales");
        this.vl.registerRepairModeListener(this.Bx);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        c.g("SecurityCheckService", "onStartCommand");
        if (intent != null) {
            co(intent.getStringExtra("code"));
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        c.g("SecurityCheckService", "onBind");
        return null;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        c.g("SecurityCheckService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onReceiveData(IIpcService.IpcMessageEvent ipcMessageEvent) {
        c.e("SecurityCheckService", "onReceiveData event=" + ipcMessageEvent);
        if (ipcMessageEvent == null) {
            return;
        }
        String senderPackageName = ipcMessageEvent.getSenderPackageName();
        Bundle payloadData = ipcMessageEvent.getPayloadData();
        int msgID = ipcMessageEvent.getMsgID();
        if (TextUtils.isEmpty(senderPackageName) || payloadData == null) {
            return;
        }
        char c = 65535;
        if (senderPackageName.hashCode() == -96368120 && senderPackageName.equals("com.xiaopeng.btphone")) {
            c = 0;
        }
        if (c == 0 && msgID == 1001) {
            co(payloadData.getString("string_msg"));
        }
    }

    private void co(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c.g("SecurityCheckService", "onReceive-----> code = " + str);
        if (this.Bw.cg(str)) {
            c.g("SecurityCheckService", str + " isSecretKey.");
            this.Bw.e(str, getApplicationContext());
        } else if (com.xiaopeng.devtools.model.c.c.bs(str)) {
            c.g("SecurityCheckService", str + " isFactoryCode.");
            this.Bw.f(str, getApplicationContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(final boolean z, final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$SecurityCheckService$yBU7oLtHhzWUh8V-R21Zh0S1WqU
            @Override // java.lang.Runnable
            public final void run() {
                SecurityCheckService.c(z, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(boolean z, int i) {
        if (!z && i == 1) {
            com.xiaopeng.devtools.utils.a.kY();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        this.vl.unregisterRepairModeListener(this.Bx);
    }
}
