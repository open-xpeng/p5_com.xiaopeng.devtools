package com.xiaopeng.devtools.model.c.a.b;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.xiaopeng.lib.utils.c;
import com.xpeng.test.BluetoothTest;
import com.xpeng.test.callback.BluetoothCallback;

/* compiled from: BluetoothModel.java */
/* loaded from: classes12.dex */
public class a {
    private BluetoothTest sP;

    public a(Context context, BluetoothCallback bluetoothCallback) {
        this.sP = new BluetoothTest(context);
        this.sP.registerCallback(bluetoothCallback);
        this.sP.registerReceiver();
    }

    public void onInit() {
    }

    public String gk() {
        String macAddress = this.sP.getMacAddress();
        c.g("BluetoothModel", "getBluetoothAddr = " + macAddress);
        return macAddress;
    }

    public void getProfileProxy(int i) {
        c.g("BluetoothModel", "getProfileProxy");
        this.sP.getProfileProxy(i);
    }

    public void gl() {
        c.g("BluetoothModel", "startScan");
        this.sP.startDiscovery();
    }

    public void dL() {
        c.g("BluetoothModel", "powerOnBluetooth");
        this.sP.enable();
    }

    public void dM() {
        c.g("BluetoothModel", "powerOffBluetooth");
        this.sP.disable();
    }

    public int dN() {
        int state = this.sP.getState();
        c.g("BluetoothModel", "getBtPowerStatus = " + state);
        return state;
    }

    public void stopScan() {
        c.g("BluetoothModel", "stopScan");
        this.sP.cancelDiscovery();
    }

    public BluetoothDevice getConnectedDevice() {
        return this.sP.getConnectedDevice();
    }

    public void by(String str) {
        c.g("BluetoothModel", "unPairDevice address = " + str);
        this.sP.unpair(this.sP.getRemoteDevice(str));
    }

    public void bz(String str) {
        c.g("BluetoothModel", "pairDevice address = " + str);
        this.sP.pairOrAcceptPair(this.sP.getRemoteDevice(str), true);
    }

    public void q(String str, int i) {
        this.sP.connectProfile(this.sP.getRemoteDevice(str), i);
    }

    public boolean isConnected() {
        return this.sP.isConnected();
    }

    public void bA(String str) {
        this.sP.disconnectDevice(this.sP.getRemoteDevice(str));
    }

    public boolean gm() {
        return this.sP.isDiscovering();
    }

    public void onDestroy() {
        this.sP.unregisterReceiver();
        this.sP.releaseCallback();
        this.sP.closeProfileProxy();
    }
}
