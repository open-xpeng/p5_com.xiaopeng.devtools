package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.u;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes12.dex */
public class WifiSettings extends RestrictedSettingsFragment {
    private final IntentFilter Oc;
    private final b Od;
    private WifiManager.ActionListener Oe;
    private AccessPoint Of;
    private NetworkInfo.DetailedState Og;
    private WifiInfo Oh;
    private final AtomicBoolean Oi;
    private f Oj;
    private TextView Ok;
    private boolean Ol;
    private boolean Om;
    private boolean On;
    private AccessPoint Oo;
    private Bundle Op;
    private boolean Oq;
    private final BroadcastReceiver mReceiver;
    private WifiManager sx;
    private WifiManager.ActionListener xx;
    private WifiManager.ActionListener xy;

    public WifiSettings() {
        super("no_config_wifi");
        this.Oi = new AtomicBoolean(false);
        this.Oc = new IntentFilter();
        this.Oc.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        this.Oc.addAction("android.net.wifi.SCAN_RESULTS");
        this.Oc.addAction("android.net.wifi.NETWORK_IDS_CHANGED");
        this.Oc.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        this.Oc.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
        this.Oc.addAction("android.net.wifi.LINK_CONFIGURATION_CHANGED");
        this.Oc.addAction("android.net.wifi.STATE_CHANGE");
        this.Oc.addAction("android.net.wifi.RSSI_CHANGED");
        this.mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.WifiSettings.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                WifiSettings.this.a(context, intent);
            }
        };
        this.Od = new b();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.RestrictedSettingsFragment, com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.SettingsPreferenceFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        this.Oq = getActivity().getIntent().getBooleanExtra("firstRun", false);
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.SettingsPreferenceFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        ConnectivityManager connectivityManager;
        super.onActivityCreated(bundle);
        this.sx = (WifiManager) getSystemService("wifi");
        this.xx = new WifiManager.ActionListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.WifiSettings.2
            public void onSuccess() {
            }

            public void onFailure(int i) {
                Activity activity = WifiSettings.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, (int) R.string.wifi_failed_connect_message, 0).show();
                }
            }
        };
        this.Oe = new WifiManager.ActionListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.WifiSettings.3
            public void onSuccess() {
            }

            public void onFailure(int i) {
                Activity activity = WifiSettings.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, (int) R.string.wifi_failed_save_message, 0).show();
                }
            }
        };
        this.xy = new WifiManager.ActionListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.WifiSettings.4
            public void onSuccess() {
            }

            public void onFailure(int i) {
                Activity activity = WifiSettings.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, (int) R.string.wifi_failed_forget_message, 0).show();
                }
            }
        };
        if (bundle != null && bundle.containsKey("wifi_ap_state")) {
            this.On = bundle.getBoolean("edit_mode");
            this.Op = bundle.getBundle("wifi_ap_state");
        }
        Activity activity = getActivity();
        Intent intent = activity.getIntent();
        this.Om = intent.getBooleanExtra("wifi_auto_finish_on_connect", false);
        if (this.Om) {
            if (hasNextButton()) {
                getNextButton().setVisibility(8);
            }
            ConnectivityManager connectivityManager2 = (ConnectivityManager) activity.getSystemService("connectivity");
            if (connectivityManager2 != null && connectivityManager2.getNetworkInfo(1).isConnected()) {
                activity.setResult(-1);
                activity.finish();
                return;
            }
        }
        this.Ol = intent.getBooleanExtra("wifi_enable_next_on_connect", false);
        if (this.Ol && hasNextButton() && (connectivityManager = (ConnectivityManager) activity.getSystemService("connectivity")) != null) {
            aB(connectivityManager.getNetworkInfo(1).isConnected());
        }
        addPreferencesFromResource(R.xml.wifi_settings);
        if (this.Oq) {
            getView().setSystemUiVisibility(27525120);
        }
        this.Ok = (TextView) getView().findViewById(16908292);
        this.Ok.setTextSize(28.0f);
        getListView().setEmptyView(this.Ok);
        getListView().setDivider(null);
        if (!this.Oq) {
            registerForContextMenu(getListView());
        }
        setHasOptionsMenu(true);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.RestrictedSettingsFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(this.mReceiver, this.Oc);
        np();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.RestrictedSettingsFragment, android.app.Fragment
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(this.mReceiver);
        this.Od.pause();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.RestrictedSettingsFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.Oj != null && this.Oj.isShowing()) {
            bundle.putBoolean("edit_mode", this.On);
            if (this.Oo != null) {
                this.Op = new Bundle();
                this.Oo.a(this.Op);
                bundle.putBundle("wifi_ap_state", this.Op);
            }
        }
    }

    @Override // android.preference.PreferenceFragment
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference instanceof AccessPoint) {
            this.Of = (AccessPoint) preference;
            if (this.Of.security == 0 && this.Of.networkId == -1) {
                this.Of.mZ();
                this.sx.connect(this.Of.getConfig(), this.xx);
                return true;
            } else if (this.Of.security == 3) {
                u.cr(R.string.wifi_not_support_eap);
                return true;
            } else {
                a(this.Of, false);
                return true;
            }
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void a(AccessPoint accessPoint, boolean z) {
        if (this.Oj != null) {
            removeDialog(1);
            this.Oj = null;
        }
        this.Oo = accessPoint;
        this.On = z;
        showDialog(1);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.SettingsPreferenceFragment, com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.b
    public Dialog onCreateDialog(int i) {
        if (i == 1) {
            AccessPoint accessPoint = this.Oo;
            if (accessPoint == null && this.Op != null) {
                accessPoint = new AccessPoint(getActivity(), this.Op);
                this.Oo = accessPoint;
                this.Op = null;
            }
            this.Of = accessPoint;
            this.Oj = new f(getActivity(), accessPoint, this.On);
            this.Oj.a(new f.a() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.WifiSettings.5
                @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.f.a
                public void a(f fVar) {
                    if (fVar != null) {
                        WifiSettings.this.a(fVar.nw());
                        fVar.dismiss();
                    }
                }
            });
            this.Oj.b(new f.a() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.WifiSettings.6
                @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.f.a
                public void a(f fVar) {
                    fVar.dismiss();
                }
            });
            this.Oj.c(new f.a() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.WifiSettings.7
                @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.f.a
                public void a(f fVar) {
                    if (WifiSettings.this.Of != null) {
                        WifiSettings.this.ns();
                        fVar.dismiss();
                    }
                }
            });
            WindowManager.LayoutParams attributes = this.Oj.getWindow().getAttributes();
            attributes.gravity = 17;
            this.Oj.getWindow().setAttributes(attributes);
            return this.Oj;
        }
        return super.onCreateDialog(i);
    }

    private void np() {
        if (getActivity() == null) {
            return;
        }
        switch (this.sx.getWifiState()) {
            case 0:
                m150do(R.string.wifi_stopping);
                return;
            case 1:
                nq();
                return;
            case 2:
                getPreferenceScreen().removeAll();
                return;
            case 3:
                List<AccessPoint> nr = nr();
                getPreferenceScreen().removeAll();
                if (nr.size() == 0) {
                    m150do(R.string.wifi_empty_list_wifi_on);
                }
                for (AccessPoint accessPoint : nr) {
                    getPreferenceScreen().addPreference(accessPoint);
                }
                return;
            default:
                return;
        }
    }

    private void nq() {
        int i;
        if (this.Ok != null) {
            this.Ok.setText(R.string.wifi_empty_list_wifi_off);
            if (Settings.Global.getInt(getActivity().getContentResolver(), "wifi_scan_always_enabled", 0) == 1) {
                this.Ok.append("\n\n");
                if (Settings.Secure.isLocationProviderEnabled(getActivity().getContentResolver(), "network")) {
                    i = R.string.wifi_scan_notify_text_location_on;
                } else {
                    i = R.string.wifi_scan_notify_text_location_off;
                }
                this.Ok.append(getText(i));
            }
        }
        getPreferenceScreen().removeAll();
    }

    /* renamed from: do  reason: not valid java name */
    private void m150do(int i) {
        if (this.Ok != null) {
            this.Ok.setText(i);
        }
        getPreferenceScreen().removeAll();
    }

    private List<AccessPoint> nr() {
        ArrayList arrayList = new ArrayList();
        a aVar = new a();
        List<WifiConfiguration> configuredNetworks = this.sx.getConfiguredNetworks();
        if (configuredNetworks != null) {
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                AccessPoint accessPoint = new AccessPoint(getActivity(), wifiConfiguration);
                accessPoint.a(this.Oh, this.Og);
                arrayList.add(accessPoint);
                aVar.put(accessPoint.ssid, accessPoint);
            }
        }
        List<ScanResult> scanResults = this.sx.getScanResults();
        if (scanResults != null) {
            for (ScanResult scanResult : scanResults) {
                if (scanResult.SSID != null && scanResult.SSID.length() != 0 && !scanResult.capabilities.contains("[IBSS]")) {
                    boolean z = false;
                    for (AccessPoint accessPoint2 : aVar.n(scanResult.SSID)) {
                        if (accessPoint2.d(scanResult)) {
                            z = true;
                        }
                    }
                    if (!z) {
                        AccessPoint accessPoint3 = new AccessPoint(getActivity(), scanResult);
                        arrayList.add(accessPoint3);
                        aVar.put(accessPoint3.ssid, accessPoint3);
                    }
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class a<K, V> {
        private final HashMap<K, List<V>> Os;

        private a() {
            this.Os = new HashMap<>();
        }

        List<V> n(K k) {
            List<V> list = this.Os.get(k);
            return list != null ? list : Collections.emptyList();
        }

        void put(K k, V v) {
            List<V> list = this.Os.get(k);
            if (list == null) {
                list = new ArrayList<>(3);
                this.Os.put(k, list);
            }
            list.add(v);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, Intent intent) {
        Activity activity;
        String action = intent.getAction();
        if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
            dp(intent.getIntExtra("wifi_state", 4));
        } else if ("android.net.wifi.SCAN_RESULTS".equals(action) || "android.net.wifi.CONFIGURED_NETWORKS_CHANGE".equals(action) || "android.net.wifi.LINK_CONFIGURATION_CHANGED".equals(action)) {
            np();
        } else if ("android.net.wifi.supplicant.STATE_CHANGE".equals(action)) {
            SupplicantState supplicantState = (SupplicantState) intent.getParcelableExtra("newState");
            if (!this.Oi.get() && SupplicantState.isHandshakeState(supplicantState)) {
                a(WifiInfo.getDetailedStateOf(supplicantState));
            } else {
                a((NetworkInfo.DetailedState) null);
            }
        } else if (!"android.net.wifi.STATE_CHANGE".equals(action)) {
            if ("android.net.wifi.RSSI_CHANGED".equals(action)) {
                a((NetworkInfo.DetailedState) null);
            }
        } else {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            this.Oi.set(networkInfo.isConnected());
            aB(networkInfo.isConnected());
            np();
            a(networkInfo.getDetailedState());
            if (this.Om && networkInfo.isConnected() && (activity = getActivity()) != null) {
                activity.setResult(-1);
                activity.finish();
            }
        }
    }

    private void a(NetworkInfo.DetailedState detailedState) {
        if (!this.sx.isWifiEnabled()) {
            this.Od.pause();
            return;
        }
        if (detailedState == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
            this.Od.pause();
        } else {
            this.Od.resume();
        }
        this.Oh = this.sx.getConnectionInfo();
        if (detailedState != null) {
            this.Og = detailedState;
        }
        for (int preferenceCount = getPreferenceScreen().getPreferenceCount() - 1; preferenceCount >= 0; preferenceCount--) {
            Preference preference = getPreferenceScreen().getPreference(preferenceCount);
            if (preference instanceof AccessPoint) {
                ((AccessPoint) preference).a(this.Oh, this.Og);
            }
        }
    }

    private void dp(int i) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
        switch (i) {
            case 1:
                nq();
                break;
            case 2:
                m150do(R.string.wifi_starting);
                break;
            case 3:
                this.Od.resume();
                return;
        }
        this.Oh = null;
        this.Og = null;
        this.Od.pause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class b extends Handler {
        private int Ot;

        private b() {
            this.Ot = 0;
        }

        void resume() {
            if (!hasMessages(0)) {
                sendEmptyMessage(0);
            }
        }

        void pause() {
            this.Ot = 0;
            removeMessages(0);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (WifiSettings.this.sx.startScan()) {
                this.Ot = 0;
            } else {
                int i = this.Ot + 1;
                this.Ot = i;
                if (i >= 3) {
                    this.Ot = 0;
                    Activity activity = WifiSettings.this.getActivity();
                    if (activity != null) {
                        Toast.makeText(activity, (int) R.string.wifi_fail_to_scan, 1).show();
                        return;
                    }
                    return;
                }
            }
            sendEmptyMessageDelayed(0, 10000L);
        }
    }

    private void aB(boolean z) {
        if (this.Ol && hasNextButton()) {
            getNextButton().setEnabled(z);
        }
    }

    void a(WifiConfigController wifiConfigController) {
        WifiConfiguration config = wifiConfigController.getConfig();
        if (config != null) {
            if (config.networkId != -1) {
                if (this.Of != null) {
                    this.sx.save(config, this.Oe);
                }
            } else if (wifiConfigController.no()) {
                this.sx.save(config, this.Oe);
            } else {
                this.sx.connect(config, this.xx);
            }
        } else if (this.Of != null && this.Of.networkId != -1) {
            this.sx.connect(this.Of.networkId, this.xx);
        }
        if (this.sx.isWifiEnabled()) {
            this.Od.resume();
        }
        np();
    }

    void ns() {
        if (this.Of.networkId == -1) {
            Log.e("WifiSettings", "Failed to forget invalid network " + this.Of.getConfig());
            return;
        }
        this.sx.forget(this.Of.networkId, this.xy);
        if (this.sx.isWifiEnabled()) {
            this.Od.resume();
        }
        np();
        aB(false);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.SettingsPreferenceFragment
    protected int na() {
        if (this.Oq) {
            return 0;
        }
        return R.string.help_url_wifi;
    }
}
