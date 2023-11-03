package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class WifiEnabler implements CompoundButton.OnCheckedChangeListener {
    private Switch MO;
    private boolean MP;
    private a MQ;
    private boolean IB = false;
    private int IC = 0;
    private int IE = 0;
    private int IF = 0;
    private int IG = 0;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.WifiEnabler.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) {
                WifiEnabler.this.dm(intent.getIntExtra("wifi_state", 4));
            }
        }
    };
    private final WifiManager sx = (WifiManager) MyApplication.getContext().getSystemService("wifi");
    private final IntentFilter mIntentFilter = new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED");

    public WifiEnabler(a aVar, Switch r3) {
        this.MO = r3;
        this.MQ = aVar;
    }

    public void resume() {
        MyApplication.getContext().registerReceiver(this.mReceiver, this.mIntentFilter);
        this.MO.setOnCheckedChangeListener(this);
    }

    public void pause() {
        MyApplication.getContext().unregisterReceiver(this.mReceiver);
        this.MO.setOnCheckedChangeListener(null);
    }

    public void setSwitch(Switch r5) {
        if (this.MO == r5) {
            return;
        }
        this.MO.setOnCheckedChangeListener(null);
        this.MO = r5;
        this.MO.setOnCheckedChangeListener(this);
        int wifiState = this.sx.getWifiState();
        boolean z = false;
        boolean z2 = wifiState == 3;
        boolean z3 = wifiState == 1;
        this.MO.setChecked(z2);
        Switch r3 = this.MO;
        if (z2 || z3) {
            z = true;
        }
        r3.setEnabled(z);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.MP) {
            return;
        }
        if (z && !j(MyApplication.getContext(), "wifi")) {
            Toast.makeText(MyApplication.getContext(), (int) R.string.wifi_in_airplane_mode, 0).show();
            compoundButton.setChecked(false);
            return;
        }
        int wifiApState = this.sx.getWifiApState();
        if (z && (wifiApState == 12 || wifiApState == 13)) {
            this.sx.setWifiEnabled(false);
        }
        if (z) {
            if (this.IB) {
                this.IE++;
                mD();
            }
            this.IB = true;
        } else {
            this.IB = true;
            if (this.IB) {
                this.IG++;
                mD();
            }
        }
        this.MO.setEnabled(false);
        if (!this.sx.setWifiEnabled(z)) {
            this.MO.setEnabled(true);
            Toast.makeText(MyApplication.getContext(), (int) R.string.wifi_error, 0).show();
        }
    }

    @TargetApi(17)
    private boolean j(Context context, String str) {
        if (Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0) {
            String string = Settings.Global.getString(context.getContentResolver(), "airplane_mode_toggleable_radios");
            return string != null && string.contains(str);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dm(int i) {
        switch (i) {
            case 0:
                this.MO.setEnabled(false);
                return;
            case 1:
                setSwitchChecked(false);
                this.MO.setEnabled(true);
                if (this.IB) {
                    this.IF++;
                    mD();
                    return;
                }
                return;
            case 2:
                this.MO.setEnabled(false);
                return;
            case 3:
                setSwitchChecked(true);
                this.MO.setEnabled(true);
                this.MQ.mW();
                if (this.IB) {
                    this.IC++;
                    mD();
                }
                this.MQ.d(this.IC, this.IE, this.IF, this.IG);
                return;
            default:
                setSwitchChecked(false);
                this.MO.setEnabled(true);
                return;
        }
    }

    private void setSwitchChecked(boolean z) {
        if (z != this.MO.isChecked()) {
            this.MP = true;
            this.MO.setChecked(z);
            this.MP = false;
        }
    }

    private void mD() {
        this.MQ.c(this.IC, this.IE, this.IF, this.IG);
    }
}
