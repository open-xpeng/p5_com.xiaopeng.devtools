package com.xiaopeng.commonfunc.b.g;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAvrcpController;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothHearingAid;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothHidHost;
import android.bluetooth.BluetoothMapClient;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothPbap;
import android.bluetooth.BluetoothPbapClient;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSap;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/* compiled from: BluetoothTest.java */
/* loaded from: classes11.dex */
public class a {
    private final BluetoothAdapter mAdapter;
    Context mContext;
    private com.xiaopeng.commonfunc.b.g.a.a oG;
    private C0054a oH;
    private BluetoothA2dp oI;
    private BluetoothA2dpSink oJ;
    private BluetoothPbap oK;
    private BluetoothPbapClient oL;
    private BluetoothHeadsetClient oM;
    private BluetoothAvrcpController oN;
    private BluetoothHeadset oO;
    private BluetoothHearingAid oP;
    private BluetoothHidDevice oQ;
    private BluetoothHidHost oR;
    private BluetoothMapClient oS;
    private BluetoothPan oT;
    private BluetoothSap oU;
    private BluetoothProfile.ServiceListener oV;

    public a(Context context) {
        this.oI = null;
        this.oJ = null;
        this.oK = null;
        this.oL = null;
        this.oM = null;
        this.oN = null;
        this.oO = null;
        this.oP = null;
        this.oQ = null;
        this.oR = null;
        this.oS = null;
        this.oT = null;
        this.oU = null;
        this.oV = new BluetoothProfile.ServiceListener() { // from class: com.xiaopeng.commonfunc.b.g.a.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                Log.d("BluetoothTest", "onServiceConnected profile = " + i);
                synchronized (this) {
                    switch (i) {
                        case 1:
                            a.this.oO = (BluetoothHeadset) bluetoothProfile;
                            break;
                        case 2:
                            a.this.oI = (BluetoothA2dp) bluetoothProfile;
                            break;
                        case 4:
                            a.this.oR = (BluetoothHidHost) bluetoothProfile;
                            break;
                        case 5:
                            a.this.oT = (BluetoothPan) bluetoothProfile;
                            break;
                        case 6:
                            a.this.oK = (BluetoothPbap) bluetoothProfile;
                            break;
                        case 10:
                            a.this.oU = (BluetoothSap) bluetoothProfile;
                            break;
                        case 11:
                            a.this.oJ = (BluetoothA2dpSink) bluetoothProfile;
                            break;
                        case 12:
                            a.this.oN = (BluetoothAvrcpController) bluetoothProfile;
                            break;
                        case 16:
                            a.this.oM = (BluetoothHeadsetClient) bluetoothProfile;
                            break;
                        case 17:
                            a.this.oL = (BluetoothPbapClient) bluetoothProfile;
                            break;
                        case 18:
                            a.this.oS = (BluetoothMapClient) bluetoothProfile;
                            break;
                        case 19:
                            a.this.oQ = (BluetoothHidDevice) bluetoothProfile;
                            break;
                        case 21:
                            a.this.oP = (BluetoothHearingAid) bluetoothProfile;
                            break;
                    }
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                Log.d("BluetoothTest", "onServiceDisconnected profile = " + i);
                synchronized (this) {
                    switch (i) {
                        case 1:
                            a.this.oO = null;
                            break;
                        case 2:
                            a.this.oI = null;
                            break;
                        case 4:
                            a.this.oR = null;
                            break;
                        case 5:
                            a.this.oT = null;
                            break;
                        case 6:
                            a.this.oK = null;
                            break;
                        case 10:
                            a.this.oU = null;
                            break;
                        case 11:
                            a.this.oJ = null;
                            break;
                        case 12:
                            a.this.oN = null;
                            break;
                        case 16:
                            a.this.oM = null;
                            break;
                        case 17:
                            a.this.oL = null;
                            break;
                        case 18:
                            a.this.oS = null;
                            break;
                        case 19:
                            a.this.oQ = null;
                            break;
                        case 21:
                            a.this.oP = null;
                            break;
                    }
                }
            }
        };
        this.mContext = context;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.oH = new C0054a();
    }

    public a() {
        this.oI = null;
        this.oJ = null;
        this.oK = null;
        this.oL = null;
        this.oM = null;
        this.oN = null;
        this.oO = null;
        this.oP = null;
        this.oQ = null;
        this.oR = null;
        this.oS = null;
        this.oT = null;
        this.oU = null;
        this.oV = new BluetoothProfile.ServiceListener() { // from class: com.xiaopeng.commonfunc.b.g.a.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                Log.d("BluetoothTest", "onServiceConnected profile = " + i);
                synchronized (this) {
                    switch (i) {
                        case 1:
                            a.this.oO = (BluetoothHeadset) bluetoothProfile;
                            break;
                        case 2:
                            a.this.oI = (BluetoothA2dp) bluetoothProfile;
                            break;
                        case 4:
                            a.this.oR = (BluetoothHidHost) bluetoothProfile;
                            break;
                        case 5:
                            a.this.oT = (BluetoothPan) bluetoothProfile;
                            break;
                        case 6:
                            a.this.oK = (BluetoothPbap) bluetoothProfile;
                            break;
                        case 10:
                            a.this.oU = (BluetoothSap) bluetoothProfile;
                            break;
                        case 11:
                            a.this.oJ = (BluetoothA2dpSink) bluetoothProfile;
                            break;
                        case 12:
                            a.this.oN = (BluetoothAvrcpController) bluetoothProfile;
                            break;
                        case 16:
                            a.this.oM = (BluetoothHeadsetClient) bluetoothProfile;
                            break;
                        case 17:
                            a.this.oL = (BluetoothPbapClient) bluetoothProfile;
                            break;
                        case 18:
                            a.this.oS = (BluetoothMapClient) bluetoothProfile;
                            break;
                        case 19:
                            a.this.oQ = (BluetoothHidDevice) bluetoothProfile;
                            break;
                        case 21:
                            a.this.oP = (BluetoothHearingAid) bluetoothProfile;
                            break;
                    }
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                Log.d("BluetoothTest", "onServiceDisconnected profile = " + i);
                synchronized (this) {
                    switch (i) {
                        case 1:
                            a.this.oO = null;
                            break;
                        case 2:
                            a.this.oI = null;
                            break;
                        case 4:
                            a.this.oR = null;
                            break;
                        case 5:
                            a.this.oT = null;
                            break;
                        case 6:
                            a.this.oK = null;
                            break;
                        case 10:
                            a.this.oU = null;
                            break;
                        case 11:
                            a.this.oJ = null;
                            break;
                        case 12:
                            a.this.oN = null;
                            break;
                        case 16:
                            a.this.oM = null;
                            break;
                        case 17:
                            a.this.oL = null;
                            break;
                        case 18:
                            a.this.oS = null;
                            break;
                        case 19:
                            a.this.oQ = null;
                            break;
                        case 21:
                            a.this.oP = null;
                            break;
                    }
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void a(com.xiaopeng.commonfunc.b.g.a.a aVar) {
        this.oG = aVar;
    }

    public void releaseCallback() {
        this.oG = null;
    }

    public void getProfileProxy(int i) {
        Log.d("BluetoothTest", "getProfileProxy profile = " + i);
        if (!this.mAdapter.getProfileProxy(this.mContext, this.oV, i)) {
            Log.d("BluetoothTest", "getProfileProxy profile = " + i + " result = fail");
        }
    }

    public void closeProfileProxy() {
        if (this.oL != null) {
            this.mAdapter.closeProfileProxy(17, this.oL);
        }
        if (this.oM != null) {
            this.mAdapter.closeProfileProxy(16, this.oM);
        }
        if (this.oJ != null) {
            this.mAdapter.closeProfileProxy(11, this.oJ);
        }
        if (this.oO != null) {
            this.mAdapter.closeProfileProxy(1, this.oO);
        }
        if (this.oI != null) {
            this.mAdapter.closeProfileProxy(2, this.oI);
        }
        if (this.oK != null) {
            this.mAdapter.closeProfileProxy(6, this.oK);
        }
        if (this.oU != null) {
            this.mAdapter.closeProfileProxy(10, this.oU);
        }
        if (this.oP != null) {
            this.mAdapter.closeProfileProxy(21, this.oP);
        }
        if (this.oS != null) {
            this.mAdapter.closeProfileProxy(18, this.oS);
        }
        if (this.oR != null) {
            this.mAdapter.closeProfileProxy(4, this.oR);
        }
        if (this.oT != null) {
            this.mAdapter.closeProfileProxy(5, this.oT);
        }
        if (this.oQ != null) {
            this.mAdapter.closeProfileProxy(19, this.oQ);
        }
        if (this.oN != null) {
            this.mAdapter.closeProfileProxy(12, this.oN);
        }
    }

    public void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter.addAction("android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.sap.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED");
        this.mContext.registerReceiver(this.oH, intentFilter);
    }

    public void unregisterReceiver() {
        this.mContext.unregisterReceiver(this.oH);
    }

    /* compiled from: BluetoothTest.java */
    /* renamed from: com.xiaopeng.commonfunc.b.g.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    private class C0054a extends BroadcastReceiver {
        private C0054a() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            String action = intent.getAction();
            Log.d("BluetoothTest", "BluetoothReceiver onReceive " + action);
            switch (action.hashCode()) {
                case -1780914469:
                    if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1765782003:
                    if (action.equals("android.bluetooth.sap.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case -1676167190:
                    if (action.equals("android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case -1530327060:
                    if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1021360715:
                    if (action.equals("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 15;
                        break;
                    }
                    c = 65535;
                    break;
                case -843338808:
                    if (action.equals("android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 16;
                        break;
                    }
                    c = 65535;
                    break;
                case -612790895:
                    if (action.equals("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 14;
                        break;
                    }
                    c = 65535;
                    break;
                case -223687943:
                    if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -83749707:
                    if (action.equals("android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 6759640:
                    if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 40146574:
                    if (action.equals("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 448318136:
                    if (action.equals("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 545516589:
                    if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = '\r';
                        break;
                    }
                    c = 65535;
                    break;
                case 655892284:
                    if (action.equals("android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 19;
                        break;
                    }
                    c = 65535;
                    break;
                case 921398788:
                    if (action.equals("android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 17;
                        break;
                    }
                    c = 65535;
                    break;
                case 1123270207:
                    if (action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 1167529923:
                    if (action.equals("android.bluetooth.device.action.FOUND")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1244161670:
                    if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 1347806984:
                    if (action.equals("android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 18;
                        break;
                    }
                    c = 65535;
                    break;
                case 2116862345:
                    if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    Log.d("BluetoothTest", "BluetoothReceiver ACTION_STATE_CHANGED state = " + intExtra);
                    if (intExtra == 12) {
                        a.this.oG.onBtPower(true);
                        return;
                    } else if (intExtra == 10) {
                        a.this.oG.onBtPower(false);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    if (a.this.isDiscovering()) {
                        a.this.oG.onScanStatus(true);
                        return;
                    }
                    return;
                case 2:
                    if (!a.this.isDiscovering()) {
                        a.this.oG.onScanStatus(false);
                        return;
                    }
                    return;
                case 3:
                    a.this.oG.onScanCallback((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE));
                    return;
                case 4:
                    a.this.oG.onBtPairStatus(intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1), (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                    return;
                case 5:
                    a.this.oG.onPairRequest((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", -1), intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", -1));
                    return;
                case 6:
                    a.this.a(intent, 22);
                    return;
                case 7:
                    a.this.a(intent, 17);
                    return;
                case '\b':
                    a.this.a(intent, 16);
                    return;
                case '\t':
                    a.this.a(intent, 11);
                    return;
                case '\n':
                    a.this.a(intent, 2);
                    return;
                case 11:
                    a.this.a(intent, 10);
                    return;
                case '\f':
                    a.this.a(intent, 6);
                    return;
                case '\r':
                    a.this.a(intent, 1);
                    return;
                case 14:
                    a.this.a(intent, 21);
                    return;
                case 15:
                    a.this.a(intent, 4);
                    return;
                case 16:
                    a.this.a(intent, 5);
                    return;
                case 17:
                    a.this.a(intent, 19);
                    return;
                case 18:
                    a.this.a(intent, 12);
                    return;
                case 19:
                    a.this.a(intent, 18);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent, int i) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        if (intExtra == 2) {
            getProfileProxy(i);
        }
        this.oG.onBtConnectStatus(bluetoothDevice, intExtra, intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", Integer.MIN_VALUE), i);
    }

    public int getState() {
        return this.mAdapter.getState();
    }

    public boolean enable() {
        if (!this.mAdapter.isEnabled()) {
            return this.mAdapter.enable();
        }
        return true;
    }

    public boolean disable() {
        if (this.mAdapter.isEnabled()) {
            return this.mAdapter.disable();
        }
        return true;
    }

    public boolean isDiscovering() {
        return this.mAdapter.isDiscovering();
    }
}
