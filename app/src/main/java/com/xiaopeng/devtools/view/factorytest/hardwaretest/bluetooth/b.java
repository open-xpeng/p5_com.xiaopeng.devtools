package com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth;

import com.xiaopeng.devtools.bean.factorytest.ScanDevice;
import java.util.List;

/* compiled from: IBluetoothView.java */
/* loaded from: classes12.dex */
public interface b {
    void as(boolean z);

    void c(int i, String str);

    void c(String str, int i, int i2, int i3);

    void mE();

    void setScanDeviceItem(List<ScanDevice> list);
}
