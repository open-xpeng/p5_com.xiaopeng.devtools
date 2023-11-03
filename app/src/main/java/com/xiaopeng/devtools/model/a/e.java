package com.xiaopeng.devtools.model.a;

import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.xpu.CarXpuManager;
import com.xiaopeng.devtools.MyApplication;
import org.greenrobot.eventbus.EventBus;

/* compiled from: XpuModel.java */
/* loaded from: classes12.dex */
public class e {
    private static e rT;
    private CarXpuManager rU;
    private CarXpuManager.CarXpuEventCallback rV = new CarXpuManager.CarXpuEventCallback() { // from class: com.xiaopeng.devtools.model.a.e.1
        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            EventBus.getDefault().post(carPropertyValue);
        }

        public void onErrorEvent(int i, int i2) {
            com.xiaopeng.lib.utils.c.i("XpuModel", "onErrorEvent propertyId: " + i + " , zone: " + i2);
        }
    };

    private e() {
        fv();
        fw();
    }

    public static e fA() {
        if (rT == null) {
            rT = new e();
        }
        return rT;
    }

    private void fv() {
        try {
            if (MyApplication.ep() != null && MyApplication.ep().isConnected()) {
                this.rU = (CarXpuManager) MyApplication.ep().getCarManager("xp_xpu");
            } else {
                com.xiaopeng.lib.utils.c.i("XpuModel", "Car is not connected yet");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void fw() {
        com.xiaopeng.lib.utils.c.f("XpuModel", "registerCallback");
        try {
            if (this.rU != null) {
                this.rU.registerCallback(this.rV);
            } else {
                com.xiaopeng.lib.utils.c.i("XpuModel", "registerCallback mCarMcuManager == null");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void sendXpuUpdateRequest(String str) {
        try {
            if (this.rU != null) {
                this.rU.sendUpdateRequest(str);
            } else {
                com.xiaopeng.lib.utils.c.i("XpuModel", "sendIcmUpdateRequest error:mCarXpuManager is null ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bV(int i) {
        try {
            if (this.rU != null) {
                this.rU.setUpdateFileTransferStatus(i);
            } else {
                com.xiaopeng.lib.utils.c.i("XpuModel", "sendIcmUpdateRequest error:mCarXpuManager is null ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNedcSwitch(int i) {
        try {
            if (this.rU != null) {
                this.rU.setNedcSwitch(i);
            } else {
                com.xiaopeng.lib.utils.c.i("XpuModel", "setNedcSwitch error:mCarXpuManager is null ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
