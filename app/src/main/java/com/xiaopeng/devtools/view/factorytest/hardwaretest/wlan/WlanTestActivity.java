package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.l.b;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class WlanTestActivity extends AsOrFacBaseActivity implements a {
    private TextView Ip;
    private TextView Iq;
    private TextView Ir;
    private TextView It;
    private TextView Iu;
    private TextView Iv;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.l.a MS;
    private Switch MT;
    private WifiEnabler MU;
    private TextView MV;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.WlanTestActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NetworkInfo networkInfo;
            if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction()) && (networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo")) != null) {
                if (NetworkInfo.State.CONNECTED.equals(networkInfo.getState())) {
                    WifiInfo wifiInfo = WlanTestActivity.this.MS.getWifiInfo();
                    if (wifiInfo == null) {
                        WlanTestActivity.this.MV.setText(R.string.wlan_connect_fail);
                        return;
                    } else {
                        WlanTestActivity.this.MV.setText(WlanTestActivity.this.getString(R.string.wlan_connect_addr_tv, new Object[]{wifiInfo.getSSID(), Formatter.formatIpAddress(wifiInfo.getIpAddress()), wifiInfo.getBSSID(), wifiInfo.getSupplicantState().toString(), Integer.valueOf(wifiInfo.getRssi()), Integer.valueOf(wifiInfo.getLinkSpeed()), Integer.valueOf(wifiInfo.getNetworkId())}));
                        return;
                    }
                }
                WlanTestActivity.this.MV.setText(R.string.wlan_connect_fail);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_wifi_test);
        this.MS = new b();
        this.MS.onInit();
        setTarget(getString(R.string.test_wifi));
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.MT = (Switch) findViewById(R.id.switch_wlan_on_off);
        this.Iu = (TextView) findViewById(R.id.wlan_show_addr_tv);
        this.MV = (TextView) findViewById(R.id.wlan_connect_addr_tv);
        this.Ip = (TextView) findViewById(R.id.result_on_off_tv);
        this.Iv = (TextView) findViewById(R.id.wifi_on_off_status);
        this.Iq = (TextView) findViewById(R.id.result_addr_tv);
        this.Ir = (TextView) findViewById(R.id.result_scan_tv);
        this.It = (TextView) findViewById(R.id.result_connect_tv);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (this.theme == 1) {
            findViewById(R.id.layout_wlan_scan).setVisibility(8);
            findViewById(R.id.layout_wlan_connect).setVisibility(8);
        }
        c(0, 0, 0, 0);
        this.MU = new WifiEnabler(this, this.MT);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(this.mReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.MU != null) {
            this.MU.resume();
        }
        if (this.theme == 1) {
            recordRepairModeAction("wifi diagnosis", "triggered");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.MU != null) {
            this.MU.pause();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
    }

    public void onResult(View view) {
        int id = view.getId();
        if (id == R.id.connect_fail_bt) {
            a(getString(R.string.wlan_connect_test), false, "");
            a(this.It, false);
        } else if (id == R.id.connect_success_bt) {
            a(getString(R.string.wlan_connect_test), true, "");
            a(this.It, true);
        } else if (id == R.id.search_fail_bt) {
            a(getString(R.string.wlan_search_test), false, "");
            a(this.Ir, false);
        } else if (id == R.id.search_success_bt) {
            a(getString(R.string.wlan_search_test), true, "");
            a(this.Ir, true);
        }
    }

    private void a(TextView textView, boolean z) {
        if (z) {
            textView.setText(R.string.test_succeed);
            textView.setTextColor(getResources().getColor(17170450));
            return;
        }
        textView.setText(R.string.test_fail);
        textView.setTextColor(getResources().getColor(17170454));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_wifi));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_wifi));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.a
    public void mW() {
        this.Iu.setVisibility(0);
        String wlanLocalAddr = this.MS.getWlanLocalAddr();
        if (getString(R.string.status_unavailable).equals(wlanLocalAddr)) {
            a(getString(R.string.wlan_addr_test), false, "");
            a(this.Iq, false);
        } else {
            a(getString(R.string.wlan_addr_test), true, "");
            a(this.Iq, true);
        }
        this.Iu.setText(getString(R.string.wlan_addr, new Object[]{wlanLocalAddr}));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.a
    public void c(int i, int i2, int i3, int i4) {
        this.Iv.setText(getString(R.string.onoff_success_total_times, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)}));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.a
    public void d(int i, int i2, int i3, int i4) {
        if (this.MT.isChecked()) {
            a(getString(R.string.wlan_on_off_test), true, getString(R.string.onoff_success_total_times, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)}));
            a(this.Ip, true);
            return;
        }
        a(getString(R.string.wlan_on_off_test), false, getString(R.string.onoff_success_total_times, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)}));
        a(this.Ip, false);
    }
}
