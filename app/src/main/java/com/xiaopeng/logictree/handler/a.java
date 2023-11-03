package com.xiaopeng.logictree.handler;

import android.annotation.SuppressLint;
import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

/* compiled from: BluetoothLogicAction.java */
/* loaded from: classes12.dex */
public class a extends h {
    @SuppressLint({"HandlerLeak"})
    private static final Handler mHandler = new Handler(com.xiaopeng.lib.utils.j.dR(1)) { // from class: com.xiaopeng.logictree.handler.a.1
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what == 100001) {
                com.xiaopeng.logictree.d.pO();
            }
        }
    };
    private com.xiaopeng.commonfunc.b.c.a.a Yf;
    private int Yg;
    private com.xiaopeng.commonfunc.b.g.a.a oG;

    public a(Application application) {
        super(application);
        this.Yg = 0;
        this.oG = new com.xiaopeng.commonfunc.b.g.a.a() { // from class: com.xiaopeng.logictree.handler.a.2
            @Override // com.xiaopeng.commonfunc.b.g.a.a
            public void onBtPower(boolean z) {
                if (!z && a.this.Yg == 1001) {
                    a.this.Yg = 1000;
                    a.this.Yf.dL();
                } else if (z && a.this.Yg == 1000) {
                    a.this.Yg = 0;
                    if (a.mHandler.hasMessages(100001)) {
                        a.mHandler.removeMessages(100001);
                        com.xiaopeng.logictree.d.pP();
                    }
                }
            }

            @Override // com.xiaopeng.commonfunc.b.g.a.a
            public void onBtPairStatus(int i, BluetoothDevice bluetoothDevice) {
            }

            @Override // com.xiaopeng.commonfunc.b.g.a.a
            public void onBtConnectStatus(BluetoothDevice bluetoothDevice, int i, int i2, int i3) {
            }

            @Override // com.xiaopeng.commonfunc.b.g.a.a
            public void onScanCallback(BluetoothDevice bluetoothDevice, int i) {
            }

            @Override // com.xiaopeng.commonfunc.b.g.a.a
            public void onScanStatus(boolean z) {
            }

            @Override // com.xiaopeng.commonfunc.b.g.a.a
            public void onPairRequest(BluetoothDevice bluetoothDevice, int i, int i2) {
            }
        };
        this.CLASS_NAME = "BluetoothLogicAction";
        this.Yf = new com.xiaopeng.commonfunc.b.c.a.a(this.context, this.oG);
    }

    @Override // com.xiaopeng.logictree.handler.h
    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        super.a(aVar);
        if (a(this.YA, new String[]{"1"})) {
            int dN = this.Yf.dN();
            if (dN == 10) {
                this.Yg = 1000;
                this.Yf.dL();
                mHandler.sendEmptyMessageDelayed(100001, 10000L);
            } else if (dN == 12) {
                this.Yg = 1001;
                this.Yf.dM();
                mHandler.sendEmptyMessageDelayed(100001, 10000L);
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        }
        return null;
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        super.destroy();
        this.Yf.onDestroy();
    }
}
