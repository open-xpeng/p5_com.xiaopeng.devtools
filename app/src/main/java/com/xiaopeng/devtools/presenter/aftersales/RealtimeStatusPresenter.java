package com.xiaopeng.devtools.presenter.aftersales;

import android.bluetooth.BluetoothDevice;
import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.aftersales.RealtimeStatus;
import com.xiaopeng.devtools.bean.car.TboxApn;
import com.xiaopeng.devtools.bean.car.TboxTempInfo;
import com.xiaopeng.devtools.bean.event.signal.WifiNameSSEvent;
import com.xiaopeng.devtools.bean.event.signal.WifiStateMacEvent;
import com.xiaopeng.devtools.bean.http.CellularBean;
import com.xiaopeng.devtools.bean.http.CellularServerBean;
import com.xiaopeng.devtools.bean.http.SimStatus;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.utils.j;
import com.xpeng.test.BluetoothTest;
import com.xpeng.test.callback.BluetoothCallback;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class RealtimeStatusPresenter {
    private static final String vJ = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("CELLULAR_DATA_INFO");
    private String mIccid;
    private String mVin;
    private com.xiaopeng.devtools.view.aftersales.d vC;
    private RealtimeStatus[] vG;
    private boolean vH;
    private int vE = 10;
    private boolean vF = false;
    private BluetoothCallback vK = new BluetoothCallback() { // from class: com.xiaopeng.devtools.presenter.aftersales.RealtimeStatusPresenter.1
        public void onBtPower(boolean z) {
            RealtimeStatusPresenter.this.in();
        }

        public void onBtPairStatus(int i, BluetoothDevice bluetoothDevice) {
        }

        public void onBtConnectStatus(BluetoothDevice bluetoothDevice, int i, int i2, int i3) {
            RealtimeStatusPresenter.this.in();
        }

        public void onScanCallback(BluetoothDevice bluetoothDevice, int i) {
        }

        public void onScanStatus(boolean z) {
        }

        public void onBindSuccess() {
        }

        public void onPairRequest(BluetoothDevice bluetoothDevice, int i, int i2) {
        }
    };
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.presenter.aftersales.RealtimeStatusPresenter.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            WifiInfo connectionInfo;
            String action = intent.getAction();
            if (!"android.net.wifi.STATE_CHANGE".equals(action)) {
                if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                    RealtimeStatusPresenter.this.ci(intent.getIntExtra("wifi_state", 4));
                    return;
                }
                return;
            }
            NetworkInfo networkInfo = (NetworkInfo) intent.getExtra("networkInfo");
            WifiNameSSEvent wifiNameSSEvent = new WifiNameSSEvent();
            if (networkInfo != null) {
                if (NetworkInfo.State.CONNECTED.equals(networkInfo.getState()) && (connectionInfo = RealtimeStatusPresenter.this.sx.getConnectionInfo()) != null) {
                    wifiNameSSEvent.setName(connectionInfo.getSSID());
                    wifiNameSSEvent.setStrength(String.valueOf(connectionInfo.getRssi()));
                    wifiNameSSEvent.setIp(Formatter.formatIpAddress(connectionInfo.getIpAddress()));
                }
            }
            RealtimeStatusPresenter.this.a(wifiNameSSEvent);
        }
    };
    private Runnable vL = new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.RealtimeStatusPresenter.4
        @Override // java.lang.Runnable
        public void run() {
            RealtimeStatusPresenter.this.il();
            WifiNameSSEvent wifiNameSSEvent = new WifiNameSSEvent();
            WifiInfo connectionInfo = RealtimeStatusPresenter.this.sx.getConnectionInfo();
            if (connectionInfo != null) {
                wifiNameSSEvent.setName(connectionInfo.getSSID());
                wifiNameSSEvent.setStrength(String.valueOf(connectionInfo.getRssi()));
                wifiNameSSEvent.setIp(Formatter.formatIpAddress(connectionInfo.getIpAddress()));
            }
            RealtimeStatusPresenter.this.a(wifiNameSSEvent);
            RealtimeStatusPresenter.this.ci(RealtimeStatusPresenter.this.sx.getWifiState());
            RealtimeStatusPresenter.this.in();
            RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(0, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_cpu_internal_with_value, Double.valueOf(RealtimeStatusPresenter.this.vD.gP() / 10.0d)));
            RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(1, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_cpu_outer_with_value, Double.valueOf(RealtimeStatusPresenter.this.vD.gQ())));
            RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(2, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_ddr_with_value, Double.valueOf(RealtimeStatusPresenter.this.vD.gS())));
            RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(3, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_ufs_with_value, Double.valueOf(RealtimeStatusPresenter.this.vD.gR())));
            RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(4, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_amp_with_value, Double.valueOf(RealtimeStatusPresenter.this.vD.gT())));
            RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(5, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_pmic_with_value, Double.valueOf(RealtimeStatusPresenter.this.vD.gU())));
            RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(6, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_amcu_with_value, Float.valueOf(com.xiaopeng.devtools.model.a.c.fx().getDvMcuTemp())));
            if (RealtimeStatusPresenter.this.vH) {
                try {
                    TboxTempInfo tboxTempInfo = (TboxTempInfo) new Gson().fromJson(com.xiaopeng.devtools.model.a.d.fz().getDvTempMsg(), (Class<Object>) TboxTempInfo.class);
                    if (tboxTempInfo != null) {
                        RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(7, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_tbox_with_value, Float.valueOf(tboxTempInfo.getFloatTbox())));
                        RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(8, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_tmcu_with_value, Float.valueOf(tboxTempInfo.getFloatTmcu())));
                        RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(9, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_batt_with_value, Float.valueOf(tboxTempInfo.getFloatBbat())));
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else {
                RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(7, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_pcb_with_value, Float.valueOf(com.xiaopeng.devtools.model.a.c.fx().getDvPcbTemp())));
                RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.TEMP.ordinal()].setStatus(8, com.xiaopeng.devtools.utils.d.getString(R.string.temperature_batt_with_value, Float.valueOf(com.xiaopeng.devtools.model.a.c.fx().getDvBatTemp())));
            }
            RealtimeStatus realtimeStatus = RealtimeStatusPresenter.this.vG[REALTIME_QUEUE.OTHERS.ordinal()];
            Object[] objArr = new Object[1];
            objArr[0] = com.xiaopeng.devtools.utils.d.getString(com.xiaopeng.devtools.model.a.c.fx().getChairWelcomeMode() == 1 ? R.string.state_on : R.string.state_off);
            realtimeStatus.setStatus(0, com.xiaopeng.devtools.utils.d.getString(R.string.welcome_mode_status, objArr));
            RealtimeStatusPresenter.this.vC.a(RealtimeStatusPresenter.this.vG);
            if (RealtimeStatusPresenter.this.vF) {
                j.b(0, RealtimeStatusPresenter.this.vL, RealtimeStatusPresenter.this.vE * 1000);
            }
        }
    };
    private SimStatus vI = new SimStatus();
    private WifiManager sx = (WifiManager) MyApplication.getContext().getApplicationContext().getSystemService("wifi");
    private BluetoothTest sP = new BluetoothTest(MyApplication.getContext());
    private com.xiaopeng.devtools.model.c.a.i.a vD = new com.xiaopeng.devtools.model.c.a.i.a();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum REALTIME_QUEUE {
        LTE,
        WIFI,
        BLUETOOTH,
        TEMP,
        OTHERS
    }

    public RealtimeStatusPresenter(com.xiaopeng.devtools.view.aftersales.d dVar) {
        this.vH = false;
        this.vC = dVar;
        if ("Q1".equals(Car.getXpCduType())) {
            this.vH = true;
        }
        dJ();
        registerReceiver();
        this.sP.registerCallback(this.vK);
        this.sP.registerReceiver();
        com.xiaopeng.devtools.model.a.d.fz();
        com.xiaopeng.devtools.utils.f.k(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue.getPropertyId() == 554700817) {
            TboxApn tboxApn = (TboxApn) new Gson().fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxApn.class);
            this.vG[REALTIME_QUEUE.LTE.ordinal()].setStatus(1, com.xiaopeng.devtools.utils.d.getString(R.string.text_imsi, tboxApn.getImsi()));
            this.vG[REALTIME_QUEUE.LTE.ordinal()].setStatus(2, com.xiaopeng.devtools.utils.d.getString(R.string.text_imei, tboxApn.getImei()));
            this.vG[REALTIME_QUEUE.LTE.ordinal()].setStatus(3, com.xiaopeng.devtools.utils.d.getString(R.string.lte_rssi, String.valueOf(tboxApn.getRssi())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void il() {
        this.vI.resetData();
        if (this.mVin.length() > 0) {
            HashMap hashMap = new HashMap(1);
            hashMap.put("vins", this.mVin);
            ((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).bizHelper().post(vJ, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().tag(vJ).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.aftersales.RealtimeStatusPresenter.3
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    com.xiaopeng.lib.utils.c.g("RealtimeStatusPresenter", "iResponse : " + iResponse.body());
                    RealtimeStatusPresenter.this.vI.setActivate("UNKNOWN");
                    RealtimeStatusPresenter.this.vI.setTotalFlow("UNKNOWN");
                    RealtimeStatusPresenter.this.vI.setRestFlow("UNKNOWN");
                    if (iResponse.code() == 200) {
                        com.xiaopeng.lib.http.a.a a = com.xiaopeng.devtools.utils.d.a(iResponse);
                        if (a.getCode() == 200) {
                            CellularServerBean cellularServerBean = (CellularServerBean) new Gson().fromJson(a.getData(), (Class<Object>) CellularServerBean.class);
                            if (!"0000".equals(cellularServerBean.getRespCode())) {
                                RealtimeStatusPresenter.this.vI.setActivate(cellularServerBean.getRespDesc());
                            } else {
                                CellularBean cellularBean = (CellularBean) ((List) new Gson().fromJson(cellularServerBean.getRespInfo(), new TypeToken<List<CellularBean>>() { // from class: com.xiaopeng.devtools.presenter.aftersales.RealtimeStatusPresenter.3.1
                                }.getType())).get(0);
                                if ("0000".equals(cellularBean.getRespCode())) {
                                    if (RealtimeStatusPresenter.this.mIccid.equals(cellularBean.getIccid())) {
                                        RealtimeStatusPresenter.this.vI.setActivate(com.xiaopeng.devtools.utils.d.getString(R.string.sim_activated));
                                        SimStatus simStatus = RealtimeStatusPresenter.this.vI;
                                        simStatus.setTotalFlow(cellularBean.getTotalFlow() + cellularBean.getFlowUnit());
                                        SimStatus simStatus2 = RealtimeStatusPresenter.this.vI;
                                        simStatus2.setRestFlow(cellularBean.getRestFlow() + cellularBean.getFlowUnit());
                                    } else {
                                        RealtimeStatusPresenter.this.vI.setActivate(com.xiaopeng.devtools.utils.d.getString(R.string.sim_mismatch_with_server));
                                    }
                                } else {
                                    RealtimeStatusPresenter.this.vI.setActivate(cellularBean.getRespDesc());
                                }
                            }
                        }
                    }
                    RealtimeStatusPresenter.this.im();
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    RealtimeStatusPresenter.this.vI.setActivate("UNKNOWN");
                    RealtimeStatusPresenter.this.vI.setTotalFlow("UNKNOWN");
                    RealtimeStatusPresenter.this.vI.setRestFlow("UNKNOWN");
                    RealtimeStatusPresenter.this.im();
                }
            });
            return;
        }
        com.xiaopeng.lib.utils.c.i("RealtimeStatusPresenter", "fail getCellularDataInfo VIN = " + this.mVin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void im() {
        this.vG[REALTIME_QUEUE.LTE.ordinal()].setStatus(4, com.xiaopeng.devtools.utils.d.getString(R.string.sim_activate_status, this.vI.getActivate()));
        this.vG[REALTIME_QUEUE.LTE.ordinal()].setStatus(5, com.xiaopeng.devtools.utils.d.getString(R.string.text_total_flow, this.vI.getTotalFlow()));
        this.vG[REALTIME_QUEUE.LTE.ordinal()].setStatus(6, com.xiaopeng.devtools.utils.d.getString(R.string.text_reset_flow, this.vI.getRestFlow()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void ci(int i) {
        com.xiaopeng.lib.utils.c.g("RealtimeStatusPresenter", "getNUpdateWifiStateMac state : " + i);
        WifiStateMacEvent wifiStateMacEvent = new WifiStateMacEvent();
        if (i == 3) {
            wifiStateMacEvent.setState(1);
        }
        WifiInfo connectionInfo = this.sx.getConnectionInfo();
        if (connectionInfo != null) {
            wifiStateMacEvent.setMac(connectionInfo.getMacAddress());
        }
        a(wifiStateMacEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(WifiNameSSEvent wifiNameSSEvent) {
        com.xiaopeng.lib.utils.c.g("RealtimeStatusPresenter", "updateWifiSSID event: " + wifiNameSSEvent.toString());
        this.vG[REALTIME_QUEUE.WIFI.ordinal()].setStatus(2, com.xiaopeng.devtools.utils.d.getString(R.string.wlan_ssid, wifiNameSSEvent.getName()));
        this.vG[REALTIME_QUEUE.WIFI.ordinal()].setStatus(3, com.xiaopeng.devtools.utils.d.getString(R.string.wlan_ip_address, wifiNameSSEvent.getIp()));
        this.vG[REALTIME_QUEUE.WIFI.ordinal()].setStatus(4, com.xiaopeng.devtools.utils.d.getString(R.string.wlan_rssi, wifiNameSSEvent.getStrength()));
    }

    private synchronized void a(WifiStateMacEvent wifiStateMacEvent) {
        com.xiaopeng.lib.utils.c.g("RealtimeStatusPresenter", "updateWifiStateMac event: " + wifiStateMacEvent.toString());
        RealtimeStatus realtimeStatus = this.vG[REALTIME_QUEUE.WIFI.ordinal()];
        Object[] objArr = new Object[1];
        objArr[0] = wifiStateMacEvent.getState() == 1 ? com.xiaopeng.devtools.utils.d.getString(R.string.state_on) : com.xiaopeng.devtools.utils.d.getString(R.string.state_off);
        realtimeStatus.setStatus(0, com.xiaopeng.devtools.utils.d.getString(R.string.wlan_status_with_value, objArr));
        this.vG[REALTIME_QUEUE.WIFI.ordinal()].setStatus(1, com.xiaopeng.devtools.utils.d.getString(R.string.wlan_mac_address, wifiStateMacEvent.getMac()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void in() {
        RealtimeStatus realtimeStatus = this.vG[REALTIME_QUEUE.BLUETOOTH.ordinal()];
        Object[] objArr = new Object[1];
        objArr[0] = this.sP.getState() == 12 ? com.xiaopeng.devtools.utils.d.getString(R.string.state_on) : com.xiaopeng.devtools.utils.d.getString(R.string.state_off);
        realtimeStatus.setStatus(0, com.xiaopeng.devtools.utils.d.getString(R.string.bt_status_with_value, objArr));
        this.vG[REALTIME_QUEUE.BLUETOOTH.ordinal()].setStatus(1, com.xiaopeng.devtools.utils.d.getString(R.string.bt_mac_address, this.sP.getMacAddress()));
        this.vG[REALTIME_QUEUE.BLUETOOTH.ordinal()].setStatus(2, com.xiaopeng.devtools.utils.d.getString(R.string.bt_connected_devices_size, Integer.valueOf(this.sP.getConnectedDevicesSize())));
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        MyApplication.getContext().registerReceiver(this.mReceiver, intentFilter);
    }

    private void dJ() {
        this.mVin = r.eH();
        this.mIccid = r.getIccid();
        RealtimeStatus[] realtimeStatusArr = new RealtimeStatus[5];
        realtimeStatusArr[0] = new RealtimeStatus(com.xiaopeng.devtools.utils.d.getString(R.string.diagnosis_module_4g), new String[]{com.xiaopeng.devtools.utils.d.getString(R.string.text_iccid, this.mIccid), com.xiaopeng.devtools.utils.d.getString(R.string.text_imsi, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.text_imei, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.lte_rssi, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.sim_activate_status, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.text_total_flow, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.text_reset_flow, "LOADING...")});
        realtimeStatusArr[1] = new RealtimeStatus(com.xiaopeng.devtools.utils.d.getString(R.string.diagnosis_module_wifi), new String[]{com.xiaopeng.devtools.utils.d.getString(R.string.wlan_status_with_value, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.wlan_mac_address, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.wlan_ssid, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.wlan_ip_address, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.wlan_rssi, "LOADING...")});
        realtimeStatusArr[2] = new RealtimeStatus(com.xiaopeng.devtools.utils.d.getString(R.string.diagnosis_module_bluetooth), new String[]{com.xiaopeng.devtools.utils.d.getString(R.string.bt_status_with_value, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.bt_mac_address, "LOADING..."), com.xiaopeng.devtools.utils.d.getString(R.string.bt_connected_devices_size, 0)});
        realtimeStatusArr[3] = new RealtimeStatus(com.xiaopeng.devtools.utils.d.getString(R.string.temp_status), this.vH ? new String[]{com.xiaopeng.devtools.utils.d.getString(R.string.temperature_cpu_internal_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_cpu_outer_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_ddr_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_ufs_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_amp_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_pmic_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_amcu_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_tbox_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_tmcu_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_batt_with_value, Double.valueOf(-1.0d))} : new String[]{com.xiaopeng.devtools.utils.d.getString(R.string.temperature_cpu_internal_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_cpu_outer_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_ddr_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_ufs_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_amp_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_pmic_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_amcu_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_pcb_with_value, Double.valueOf(-1.0d)), com.xiaopeng.devtools.utils.d.getString(R.string.temperature_batt_with_value, Double.valueOf(-1.0d))});
        realtimeStatusArr[4] = new RealtimeStatus(com.xiaopeng.devtools.utils.d.getString(R.string.other_realtime_status), new String[]{com.xiaopeng.devtools.utils.d.getString(R.string.welcome_mode_status, "LOADING...")});
        this.vG = realtimeStatusArr;
    }

    public RealtimeStatus[] io() {
        return this.vG;
    }

    public void ip() {
        com.xiaopeng.lib.utils.c.f("RealtimeStatusPresenter", "readRealtimeStatus");
        this.vF = true;
        j.e(this.vL);
        j.execute(this.vL);
    }

    public void destroy() {
        com.xiaopeng.lib.utils.c.f("RealtimeStatusPresenter", "destroy");
        this.vF = false;
        j.e(this.vL);
        MyApplication.getContext().unregisterReceiver(this.mReceiver);
        this.sP.unregisterReceiver();
        this.sP.releaseCallback();
        com.xiaopeng.devtools.utils.f.l(this);
    }
}
