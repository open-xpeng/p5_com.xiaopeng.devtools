package com.xiaopeng.devtools.model.f;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.Formatter;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.signal.BtNameEvent;
import com.xiaopeng.devtools.bean.event.signal.BtPowerEvent;
import com.xiaopeng.devtools.bean.event.signal.BtRssiEvent;
import com.xiaopeng.devtools.bean.event.signal.WifiNameSSEvent;
import com.xiaopeng.devtools.bean.event.signal.WifiStateMacEvent;
import com.xiaopeng.libbluetooth.e;
import com.xiaopeng.libbluetooth.f;
import org.greenrobot.eventbus.EventBus;

/* compiled from: WifiBtModel.java */
/* loaded from: classes12.dex */
public class d {
    private static volatile d us;
    private int uA;
    private boolean uB;
    private int wifiState;
    private String ut = "";
    private String uu = "";
    private String uv = "";
    private String uw = "";
    private String ux = "";
    private String uy = "";
    private String uz = "";
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.model.f.d.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    WifiInfo connectionInfo = d.this.sx.getConnectionInfo();
                    d.this.ut = connectionInfo.getSSID();
                    d dVar = d.this;
                    dVar.uv = connectionInfo.getLinkSpeed() + "";
                    d dVar2 = d.this;
                    dVar2.uu = connectionInfo.getRssi() + "";
                    d.this.uz = Formatter.formatIpAddress(connectionInfo.getIpAddress());
                    EventBus.getDefault().post(new WifiNameSSEvent(d.this.ut, d.this.uu, d.this.uv, d.this.uz));
                    if (d.this.mHandler.hasMessages(1)) {
                        d.this.mHandler.removeMessages(1);
                    }
                    d.this.mHandler.sendEmptyMessageDelayed(1, 2000L);
                    return;
                case 2:
                    d.this.hD();
                    if (d.this.mHandler.hasMessages(2)) {
                        d.this.mHandler.removeMessages(2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private com.xiaopeng.libbluetooth.b uC = new com.xiaopeng.libbluetooth.b() { // from class: com.xiaopeng.devtools.model.f.d.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaopeng.libbluetooth.b
        public void r(String str, int i) {
            super.r(str, i);
            d dVar = d.this;
            dVar.uy = i + "";
            if (d.this.mHandler.hasMessages(2)) {
                d.this.mHandler.removeMessages(2);
            }
            d.this.mHandler.sendEmptyMessageDelayed(2, 2000L);
            EventBus.getDefault().post(new BtRssiEvent(d.this.uy));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaopeng.libbluetooth.b
        public void onBtPower(boolean z) {
            super.onBtPower(z);
            if (z) {
                d.this.uA = 1;
            } else {
                d.this.uA = 0;
                d.this.ux = "";
                d.this.uy = "";
            }
            EventBus.getDefault().post(new BtPowerEvent(d.this.uA));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaopeng.libbluetooth.b
        public void a(String str, boolean z, int i, int i2, int i3) {
            super.a(str, z, i, i2, i3);
            if (z) {
                if (i2 == 2 || i2 == 0) {
                    String[] strArr = new String[1];
                    d.this.ur.a(i2, strArr, new String[1]);
                    if (!d.this.ux.equals(strArr[0])) {
                        d.this.ux = strArr[0];
                        d.this.hD();
                        EventBus.getDefault().post(new BtNameEvent(d.this.ux));
                    }
                }
            }
        }
    };
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.model.f.d.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!"android.net.wifi.STATE_CHANGE".equals(action)) {
                if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                    if (intent.getIntExtra("wifi_state", 4) == 3) {
                        d.this.wifiState = 1;
                    } else {
                        d.this.wifiState = 0;
                    }
                    if (TextUtils.isEmpty(d.this.uw)) {
                        d.this.uw = d.this.getWlanLocalAddr();
                    }
                    EventBus.getDefault().post(new WifiStateMacEvent(d.this.wifiState, d.this.uw));
                    return;
                }
                return;
            }
            NetworkInfo networkInfo = (NetworkInfo) intent.getExtra("networkInfo");
            if (networkInfo != null) {
                if (NetworkInfo.State.CONNECTED.equals(networkInfo.getState())) {
                    WifiInfo connectionInfo = d.this.sx.getConnectionInfo();
                    if (connectionInfo == null) {
                        d.this.ut = MyApplication.getContext().getString(R.string.status_unavailable);
                        d.this.uv = MyApplication.getContext().getString(R.string.status_unavailable);
                        d.this.uu = MyApplication.getContext().getString(R.string.status_unavailable);
                        d.this.uz = MyApplication.getContext().getString(R.string.status_unavailable);
                    } else {
                        d.this.ut = connectionInfo.getSSID();
                        d dVar = d.this;
                        dVar.uv = connectionInfo.getLinkSpeed() + "";
                        d dVar2 = d.this;
                        dVar2.uu = connectionInfo.getRssi() + "";
                        d.this.uz = Formatter.formatIpAddress(connectionInfo.getIpAddress());
                        if (d.this.mHandler.hasMessages(1)) {
                            d.this.mHandler.removeMessages(1);
                        }
                        d.this.mHandler.sendEmptyMessageDelayed(1, 2000L);
                    }
                } else {
                    d.this.ut = MyApplication.getContext().getString(R.string.status_unavailable);
                    d.this.uv = MyApplication.getContext().getString(R.string.status_unavailable);
                    d.this.uu = MyApplication.getContext().getString(R.string.status_unavailable);
                    d.this.uz = MyApplication.getContext().getString(R.string.status_unavailable);
                }
            } else {
                d.this.ut = MyApplication.getContext().getString(R.string.status_unavailable);
                d.this.uv = MyApplication.getContext().getString(R.string.status_unavailable);
                d.this.uu = MyApplication.getContext().getString(R.string.status_unavailable);
                d.this.uz = MyApplication.getContext().getString(R.string.status_unavailable);
            }
            EventBus.getDefault().post(new WifiNameSSEvent(d.this.ut, d.this.uu, d.this.uv, d.this.uz));
        }
    };
    private WifiManager sx = (WifiManager) MyApplication.getContext().getApplicationContext().getSystemService("wifi");
    private f ur = e.pv().a(this.uC);

    private d() {
        e.pv().a((com.xiaopeng.libbluetooth.a) this.ur);
        if (!this.uB) {
            registerReceiver();
            this.uB = true;
        }
        this.uA = this.ur.dN();
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        MyApplication.getContext().registerReceiver(this.mReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getWlanLocalAddr() {
        String string = MyApplication.getContext().getString(R.string.status_unavailable);
        if (this.sx != null) {
            WifiInfo connectionInfo = this.sx.getConnectionInfo();
            return connectionInfo == null ? string : connectionInfo.getMacAddress();
        }
        return string;
    }

    public static d hv() {
        if (us == null) {
            synchronized (d.class) {
                if (us == null) {
                    us = new d();
                }
            }
        }
        return us;
    }

    public String hw() {
        return this.ut;
    }

    public String hx() {
        return this.uw;
    }

    public String hy() {
        return this.uv;
    }

    public String hz() {
        return this.uu;
    }

    public int getWifiState() {
        return this.wifiState;
    }

    public int hA() {
        return this.uA;
    }

    public String hB() {
        return this.ux;
    }

    public String hC() {
        return this.uz;
    }

    public void hD() {
        if (!TextUtils.isEmpty(this.ux) && this.uA == 1) {
            this.ur.dO(this.ux);
        }
    }
}
