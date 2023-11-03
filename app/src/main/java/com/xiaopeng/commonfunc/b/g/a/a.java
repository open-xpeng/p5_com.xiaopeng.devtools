package com.xiaopeng.commonfunc.b.g.a;

import android.bluetooth.BluetoothDevice;

/* compiled from: BluetoothCallback.java */
/* loaded from: classes11.dex */
public interface a {
    void onBtConnectStatus(BluetoothDevice bluetoothDevice, int i, int i2, int i3);

    void onBtPairStatus(int i, BluetoothDevice bluetoothDevice);

    void onBtPower(boolean z);

    void onPairRequest(BluetoothDevice bluetoothDevice, int i, int i2);

    void onScanCallback(BluetoothDevice bluetoothDevice, int i);

    void onScanStatus(boolean z);
}
