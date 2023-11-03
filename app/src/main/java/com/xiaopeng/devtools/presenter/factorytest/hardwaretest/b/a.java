package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.xiaopeng.devtools.bean.factorytest.ScanDevice;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.a;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.b;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import com.xpeng.test.callback.BluetoothCallback;
import java.util.List;

/* compiled from: BluetoothPresenter.java */
/* loaded from: classes12.dex */
public class a {
    private static byte[] wJ = {49, 50, 51, 52};
    private com.xiaopeng.devtools.model.c.a.b.a wF;
    private b wG;
    private com.xiaopeng.devtools.model.c.a.b.b wH;
    private InterfaceC0067a wI = null;
    private BluetoothCallback vK = new AnonymousClass1();

    /* compiled from: BluetoothPresenter.java */
    /* renamed from: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public interface InterfaceC0067a {
        void iW();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BluetoothPresenter.java */
    /* renamed from: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.a$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass1 implements BluetoothCallback {
        AnonymousClass1() {
        }

        public void onBtPower(final boolean z) {
            c.f("BluetoothPresenter", "onBtPower isOn =" + z);
            if (a.this.wG != null) {
                j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.-$$Lambda$a$1$sBJ_IiOyzLiO00nEqqQsYktJvW0
                    @Override // java.lang.Runnable
                    public final void run() {
                        a.AnonymousClass1.this.J(z);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void J(boolean z) {
            a.this.wG.as(z);
        }

        public void onBtPairStatus(final int i, BluetoothDevice bluetoothDevice) {
            final String address = bluetoothDevice.getAddress();
            c.f("BluetoothPresenter", "onBtPairStatus status =" + i + " address = " + address);
            if (a.this.wG != null) {
                j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.-$$Lambda$a$1$ezqRvTdkovjjOjl_Ip74NKoeQWo
                    @Override // java.lang.Runnable
                    public final void run() {
                        a.AnonymousClass1.this.b(i, address);
                    }
                });
            }
            if (i == 12) {
                a.this.getProfileProxy(11);
                a.this.getProfileProxy(16);
                j.b(0, new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.a.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.bU(address);
                    }
                }, 5000L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(int i, String str) {
            a.this.wG.c(i, str);
        }

        public void onBtConnectStatus(final BluetoothDevice bluetoothDevice, final int i, final int i2, final int i3) {
            c.d("BluetoothPresenter", "onBtConnectStatus status[%d] preState[%d] profileType[%d] mBluetoothModel.isConnected()[%s]", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Boolean.valueOf(a.this.wF.isConnected()));
            if ((!a.this.wF.isConnected() || i == 2) && a.this.wG != null) {
                j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.-$$Lambda$a$1$VtuZKvYS76O-ZMJsyhKi4X6RsDA
                    @Override // java.lang.Runnable
                    public final void run() {
                        a.AnonymousClass1.this.a(bluetoothDevice, i, i2, i3);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(BluetoothDevice bluetoothDevice, int i, int i2, int i3) {
            a.this.wG.c(bluetoothDevice.getAddress(), i, i2, i3);
        }

        public void onScanCallback(BluetoothDevice bluetoothDevice, int i) {
            c.f("BluetoothPresenter", "onScanCallback device =" + bluetoothDevice.toString() + " rssi = " + i);
            if (a.this.wH.bB(bluetoothDevice.getAddress()) != null) {
                return;
            }
            final ScanDevice scanDevice = new ScanDevice(bluetoothDevice.getName(), bluetoothDevice.getAddress(), "0000", i);
            j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.-$$Lambda$a$1$piO8azTVmrNDYqhDhvh83mB5bWo
                @Override // java.lang.Runnable
                public final void run() {
                    a.AnonymousClass1.this.b(scanDevice);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(ScanDevice scanDevice) {
            a.this.wH.a(scanDevice);
            a.this.setScanDeviceItem(a.this.wH.getItems());
        }

        public void onScanStatus(boolean z) {
            c.f("BluetoothPresenter", "onScanStatus isDiscovering =" + z);
            if (a.this.wG != null && !z) {
                a.this.wG.mE();
            }
            if (!z && a.this.wI != null) {
                a.this.wI.iW();
            }
        }

        public void onBindSuccess() {
            c.f("BluetoothPresenter", "onBindSuccess ");
        }

        public void onPairRequest(BluetoothDevice bluetoothDevice, int i, int i2) {
            c.f("BluetoothPresenter", "onPairRequest variant = " + i + " passkey = " + i2);
            switch (i) {
                case 0:
                case 7:
                    bluetoothDevice.setPin(a.wJ);
                    return;
                case 1:
                    bluetoothDevice.setPasskey(i2);
                    return;
                case 2:
                case 3:
                    bluetoothDevice.setPairingConfirmation(true);
                    return;
                case 4:
                case 5:
                default:
                    return;
                case 6:
                    bluetoothDevice.setRemoteOutOfBandData();
                    return;
            }
        }
    }

    public a(b bVar, Context context) {
        this.wG = null;
        this.wF = new com.xiaopeng.devtools.model.c.a.b.a(context, this.vK);
        this.wG = bVar;
    }

    public void onInit() {
        this.wF.onInit();
        this.wH = com.xiaopeng.devtools.model.c.a.b.b.gn();
    }

    public String gk() {
        return this.wF.gk();
    }

    public void getProfileProxy(int i) {
        this.wF.getProfileProxy(i);
    }

    public void gl() {
        this.wF.gl();
    }

    public void dL() {
        this.wF.dL();
    }

    public void dM() {
        this.wF.dM();
    }

    public int dN() {
        return this.wF.dN();
    }

    public BluetoothDevice getConnectedDevice() {
        return this.wF.getConnectedDevice();
    }

    public void setScanDeviceItem(List<ScanDevice> list) {
        if (this.wG != null) {
            this.wG.setScanDeviceItem(list);
        }
    }

    public void a(InterfaceC0067a interfaceC0067a) {
        this.wI = interfaceC0067a;
        this.wF.stopScan();
    }

    public void bz(String str) {
        c.f("BluetoothPresenter", "pairDevice address = " + str);
        if (this.wH.bB(str) != null) {
            this.wF.bz(str);
        }
    }

    public void by(String str) {
        this.wF.by(str);
    }

    public void bA(String str) {
        this.wF.bA(str);
    }

    public void bU(String str) {
        c.f("BluetoothPresenter", "connectDevice address:" + str);
        bW(str);
        bV(str);
    }

    private void bV(String str) {
        c.f("BluetoothPresenter", "connectHeadsetClient address = " + str);
        this.wF.q(str, 16);
    }

    private void bW(String str) {
        c.f("BluetoothPresenter", "connectA2dpSink address = " + str);
        this.wF.q(str, 11);
    }

    public boolean gm() {
        return this.wF.gm();
    }

    public void onDestroy() {
        this.wF.onDestroy();
        this.wG = null;
    }
}
