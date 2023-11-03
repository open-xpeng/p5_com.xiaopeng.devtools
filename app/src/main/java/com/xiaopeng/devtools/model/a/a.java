package com.xiaopeng.devtools.model.a;

import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.bcm.CarBcmManager;
import com.xiaopeng.devtools.MyApplication;
import org.greenrobot.eventbus.EventBus;

/* compiled from: BcmModel.java */
/* loaded from: classes12.dex */
public class a {
    private static a rE;
    private CarBcmManager rF;
    private CarBcmManager.CarBcmEventCallback rG = new CarBcmManager.CarBcmEventCallback() { // from class: com.xiaopeng.devtools.model.a.a.1
        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            EventBus.getDefault().post(carPropertyValue);
        }

        public void onErrorEvent(int i, int i2) {
        }
    };

    private a() {
        fv();
        fw();
    }

    public static a fu() {
        if (rE == null) {
            rE = new a();
        }
        return rE;
    }

    private void fv() {
        try {
            if (MyApplication.ep() != null && MyApplication.ep().isConnected()) {
                this.rF = (CarBcmManager) MyApplication.ep().getCarManager("xp_bcm");
            } else {
                com.xiaopeng.lib.utils.c.i("BcmModel", "Car is not connected yet");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void fw() {
        com.xiaopeng.lib.utils.c.f("BcmModel", "registerCallback");
        try {
            if (this.rF != null) {
                this.rF.registerCallback(this.rG);
            } else {
                com.xiaopeng.lib.utils.c.i("BcmModel", "registerCallback mCarBcmManager == null");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public int[] getAlsInitializationStudyAndErrorState() {
        com.xiaopeng.lib.utils.c.f("BcmModel", "getAlsInitializationStudyAndErrorState");
        int[] iArr = null;
        try {
            if (this.rF != null) {
                iArr = this.rF.getAlsInitializationStudyAndErrorState();
            } else {
                com.xiaopeng.lib.utils.c.i("BcmModel", "getAlsInitializationStudyAndErrorState mCarBcmManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iArr;
    }
}
