package com.xiaopeng.devtools.model.a;

import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.icm.CarIcmManager;
import com.xiaopeng.devtools.MyApplication;
import java.util.Collection;
import org.greenrobot.eventbus.EventBus;

/* compiled from: IcmModel.java */
/* loaded from: classes12.dex */
public class b {
    private CarIcmManager rI;
    private CarIcmManager.CarIcmEventCallback rJ = new CarIcmManager.CarIcmEventCallback() { // from class: com.xiaopeng.devtools.model.a.b.1
        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            EventBus.getDefault().post(carPropertyValue);
        }

        public void onErrorEvent(int i, int i2) {
            com.xiaopeng.lib.utils.c.i("IcmModel", "onErrorEvent propertyId: " + i + " , errorCode: " + i2);
        }
    };

    public b() {
        fv();
    }

    private void fv() {
        try {
            if (MyApplication.ep() != null && MyApplication.ep().isConnected()) {
                this.rI = (CarIcmManager) MyApplication.ep().getCarManager("xp_icm");
            } else {
                com.xiaopeng.lib.utils.c.i("IcmModel", "Car is not connected yet");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void a(Collection<Integer> collection) {
        com.xiaopeng.lib.utils.c.f("IcmModel", "registerPropCallback");
        try {
            if (this.rI != null) {
                this.rI.registerPropCallback(collection, this.rJ);
            } else {
                com.xiaopeng.lib.utils.c.i("IcmModel", "registerPropCallback mCarIcmManager == null");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdateRequest(String str) {
        try {
            if (this.rI != null) {
                this.rI.sendUpdateRequest(str);
                com.xiaopeng.lib.utils.c.f("IcmModel", "sendIcmUpdateRequest :" + str);
            } else {
                com.xiaopeng.lib.utils.c.i("IcmModel", "sendIcmUpdateRequest error:mCarIcmManager is null ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUpdateFileTransferStatus(int i) {
        try {
            if (this.rI != null) {
                this.rI.setUpdateFileTransferStatus(i);
                com.xiaopeng.lib.utils.c.f("IcmModel", "setUpdateFileTransferStatus :" + i);
            } else {
                com.xiaopeng.lib.utils.c.i("IcmModel", "sendIcmUpdateRequest error:mCarIcmManager is null ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        try {
            if (this.rI != null) {
                this.rI.unregisterCallback(this.rJ);
                this.rI = null;
            }
            com.xiaopeng.lib.utils.c.f("IcmModel", "disconnect CarIcmManager");
        } catch (CarNotConnectedException e) {
            com.xiaopeng.lib.utils.c.i("IcmModel", "disconnect CarIcmManager failed!" + e);
        }
    }
}
