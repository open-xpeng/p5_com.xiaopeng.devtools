package com.xiaopeng.devtools.view.aftersales;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.system.service.PcCommandService;
import com.xiaopeng.devtools.utils.f;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.e;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class WlanPcToolsActivity extends ActionBarActivity implements View.OnClickListener {
    private CheckBox EP;
    private Intent ER;
    private boolean ES = false;
    private boolean ET = false;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.aftersales.WlanPcToolsActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!"android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
                if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                    com.xiaopeng.lib.utils.c.g("WlanPcToolsActivity", "WlanPcToolsActivity NETWORK_STATE_CHANGED_ACTION");
                    WlanPcToolsActivity.this.mh();
                    return;
                }
                return;
            }
            switch (intent.getIntExtra("wifi_state", 0)) {
                case 11:
                    com.xiaopeng.lib.utils.c.g("WlanPcToolsActivity", "WlanPcToolsActivity WIFI_AP_STATE_DISABLED mIsServiceQuit : " + WlanPcToolsActivity.this.ES);
                    WlanPcToolsActivity.this.ET = true;
                    if (WlanPcToolsActivity.this.ES) {
                        if (WlanPcToolsActivity.this.pe != null) {
                            WlanPcToolsActivity.this.pe.dismiss();
                        }
                        WlanPcToolsActivity.this.EP.setChecked(false);
                    } else {
                        WlanPcToolsActivity.this.stopService(WlanPcToolsActivity.this.ER);
                    }
                    WlanPcToolsActivity.this.EP.setText(WlanPcToolsActivity.this.getString(R.string.pc_tool_with_ip, new Object[]{"UNKNOWN"}));
                    return;
                case 12:
                    WlanPcToolsActivity.this.ET = false;
                    return;
                case 13:
                    com.xiaopeng.lib.utils.c.g("WlanPcToolsActivity", "WlanPcToolsActivity WIFI_AP_STATE_ENABLED");
                    WlanPcToolsActivity.this.ET = false;
                    WlanPcToolsActivity.this.mh();
                    return;
                default:
                    return;
            }
        }
    };
    private com.xiaopeng.xui.app.e pe;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        switch (num.intValue()) {
            case 5:
                this.ES = false;
                if (this.pe != null) {
                    this.pe.dismiss();
                }
                this.EP.setChecked(true);
                return;
            case 6:
                this.ES = true;
                if (this.ET) {
                    if (this.pe != null) {
                        this.pe.dismiss();
                    }
                    this.EP.setChecked(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.pc_tools);
        this.ER = new Intent(MyApplication.getContext(), PcCommandService.class);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.EP = (CheckBox) findViewById(R.id.pc_tools_service);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        mh();
        this.EP.setChecked(com.xiaopeng.devtools.utils.a.cp("com.xiaopeng.devtools.system.service.PcCommandService"));
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.EP.setOnClickListener(this);
        f.k(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(this.mReceiver, intentFilter);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.pc_tools_service && (view instanceof CheckBox)) {
            lV();
            if (((CheckBox) view).isChecked()) {
                startService(this.ER);
            } else {
                stopService(this.ER);
            }
        }
    }

    private void lV() {
        if (this.pe == null) {
            this.pe = com.xiaopeng.xui.app.e.a(this, getString(R.string.switching_wlan_pc_tools), true, new DialogInterface.OnCancelListener() { // from class: com.xiaopeng.devtools.view.aftersales.WlanPcToolsActivity.2
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    WlanPcToolsActivity.this.stopService(WlanPcToolsActivity.this.ER);
                }
            }, true, com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.r, new e.a() { // from class: com.xiaopeng.devtools.view.aftersales.WlanPcToolsActivity.3
                @Override // com.xiaopeng.xui.app.e.a
                public void a(com.xiaopeng.xui.app.e eVar) {
                    WlanPcToolsActivity.this.stopService(WlanPcToolsActivity.this.ER);
                }
            });
        } else {
            this.pe.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mh() {
        j.b(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.WlanPcToolsActivity.4
            @Override // java.lang.Runnable
            public void run() {
                String cr = com.xiaopeng.devtools.utils.d.cr("wlan0");
                if (cr != null) {
                    WlanPcToolsActivity.this.EP.setText(WlanPcToolsActivity.this.getString(R.string.pc_tool_with_ip, new Object[]{cr}));
                } else {
                    WlanPcToolsActivity.this.EP.setText(WlanPcToolsActivity.this.getString(R.string.pc_tool_with_ip, new Object[]{"UNKNOWN"}));
                }
            }
        }, 2000L);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
        f.l(this);
    }
}
