package com.xiaopeng.devtools.model.a;

import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.mcu.CarMcuManager;
import com.xiaopeng.devtools.MyApplication;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;

/* compiled from: McuModel.java */
/* loaded from: classes12.dex */
public class c {
    private static c rL;
    private CarMcuManager rM;
    private CarMcuManager.CarMcuEventCallback rN = new CarMcuManager.CarMcuEventCallback() { // from class: com.xiaopeng.devtools.model.a.c.1
        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            EventBus.getDefault().post(carPropertyValue);
        }

        public void onErrorEvent(int i, int i2) {
            com.xiaopeng.lib.utils.c.i("McuModel", "onErrorEvent propertyId: " + i + " , zone: " + i2);
        }
    };

    private c() {
        fv();
        fw();
    }

    public static c fx() {
        if (rL == null) {
            rL = new c();
        }
        return rL;
    }

    private void fv() {
        try {
            if (MyApplication.ep() != null && MyApplication.ep().isConnected()) {
                this.rM = (CarMcuManager) MyApplication.ep().getCarManager("xp_mcu");
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "Car is not connected yet");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void fw() {
        com.xiaopeng.lib.utils.c.f("McuModel", "registerCallback");
        try {
            if (this.rM != null) {
                this.rM.registerCallback(this.rN);
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "registerCallback mCarMcuManager == null");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void setOtaMcuReqStatus(int i) {
        com.xiaopeng.lib.utils.c.f("McuModel", "setOtaMcuReqStatus data:" + i);
        try {
            if (this.rM != null) {
                this.rM.setOtaMcuReqStatus(i);
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "setOtaMcuReqStatus mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOtaMcuSendUpdatefile(String str) {
        com.xiaopeng.lib.utils.c.f("McuModel", "setOtaMcuSendUpdatefile file:" + str);
        try {
            if (this.rM != null) {
                this.rM.setOtaMcuSendUpdatefile(str);
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "setOtaMcuSendUpdatefile mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOtaMcuReqUpdatefile(int i) {
        com.xiaopeng.lib.utils.c.f("McuModel", "setOtaMcuReqUpdatefile data:" + i);
        try {
            if (this.rM != null) {
                this.rM.setOtaMcuReqUpdatefile(i);
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "setOtaMcuReqUpdatefile mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFactoryMcuBmsMsgToMcu(int i) {
        com.xiaopeng.lib.utils.c.f("McuModel", "sendFactoryMcuBmsMsgToMcu msg:" + i);
        try {
            if (this.rM != null) {
                this.rM.sendFactoryMcuBmsMsgToMcu(i);
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "sendFactoryMcuBmsMsgToMcu mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFactoryPwrDebugMsgToMcu(int[] iArr) {
        com.xiaopeng.lib.utils.c.f("McuModel", "sendFactoryPwrDebugMsgToMcu msg: " + Arrays.toString(iArr));
        try {
            if (this.rM != null) {
                this.rM.sendFactoryPwrDebugMsgToMcu(iArr);
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "sendFactoryPwrDebugMsgToMcu mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPsuTestReq(int i) {
        com.xiaopeng.lib.utils.c.f("McuModel", "setPsuTestReq req:" + i);
        try {
            if (this.rM != null) {
                this.rM.setPsuTestReq(i);
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "setPsuTestReq mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getDvMcuTemp() {
        float f = -1.0f;
        try {
            if (this.rM != null) {
                f = this.rM.getDvMcuTemp();
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "getDvMcuTemp mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.xiaopeng.lib.utils.c.f("McuModel", "getDvMcuTemp res:" + f);
        return f;
    }

    public float getDvBatTemp() {
        float f = -1.0f;
        try {
            if (this.rM != null) {
                f = this.rM.getDvBatTemp();
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "getDvBatTemp mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.xiaopeng.lib.utils.c.f("McuModel", "getDvBatTemp res:" + f);
        return f;
    }

    public float getDvPcbTemp() {
        float f = -1.0f;
        try {
            if (this.rM != null) {
                f = this.rM.getDvPcbTemp();
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "getDvPcbTemp mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.xiaopeng.lib.utils.c.f("McuModel", "getDvPcbTemp res:" + f);
        return f;
    }

    public int getChairWelcomeMode() {
        int i = 0;
        try {
            if (this.rM != null) {
                i = this.rM.getChairWelcomeMode();
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "getChairWelcomeMode mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.xiaopeng.lib.utils.c.f("McuModel", "getChairWelcomeMode res:" + i);
        return i;
    }

    public void setDvTestReq(int i) {
        com.xiaopeng.lib.utils.c.f("McuModel", "setDvTestReq req:" + i);
        try {
            if (this.rM != null) {
                this.rM.setDvTestReq(i);
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "setDvTestReq mCarMcuManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isFactoryMode() {
        int i;
        try {
            if (this.rM != null) {
                i = this.rM.getFactoryModeSwitchStatus();
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "getFactoryModeSwitchStatus mCarMcuManager == null");
                i = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        com.xiaopeng.lib.utils.c.g("McuModel", "getFactoryModeSwitchStatus res:" + i);
        if (i != 1) {
            return false;
        }
        return true;
    }

    public boolean fy() {
        int i;
        try {
            if (this.rM != null) {
                i = this.rM.getTemporaryFactoryStatus();
            } else {
                com.xiaopeng.lib.utils.c.i("McuModel", "getTemporaryFactoryStatus mCarMcuManager == null");
                i = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        com.xiaopeng.lib.utils.c.g("McuModel", "getTemporaryFactoryStatus res:" + i);
        if (i != 1) {
            return false;
        }
        return true;
    }
}
