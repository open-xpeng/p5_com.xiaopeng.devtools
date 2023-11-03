package com.xiaopeng.devtools.model.a;

import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.tbox.CarTboxManager;
import com.google.gson.Gson;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.bean.car.TboxCanControlIPMsg;
import com.xiaopeng.devtools.bean.car.TboxCanControlMsg;
import com.xiaopeng.devtools.bean.car.TboxModemBand;
import org.greenrobot.eventbus.EventBus;

/* compiled from: TboxModel.java */
/* loaded from: classes12.dex */
public class d {
    private static d rP;
    private CarTboxManager rQ;
    private CarTboxManager.CarTboxEventCallback rR = new CarTboxManager.CarTboxEventCallback() { // from class: com.xiaopeng.devtools.model.a.d.1
        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            EventBus.getDefault().post(carPropertyValue);
        }

        public void onErrorEvent(int i, int i2) {
        }
    };

    private d() {
        fv();
        fw();
    }

    public static d fz() {
        if (rP == null) {
            rP = new d();
        }
        return rP;
    }

    private void fv() {
        try {
            if (MyApplication.ep() != null && MyApplication.ep().isConnected()) {
                this.rQ = (CarTboxManager) MyApplication.ep().getCarManager("xp_tbox");
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "Car is not connected yet");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void fw() {
        com.xiaopeng.lib.utils.c.f("TboxModel", "registerCallback");
        try {
            if (this.rQ != null) {
                this.rQ.registerCallback(this.rR);
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "registerCallback mCarTboxManager == null");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void setGpsReset(int i) {
        com.xiaopeng.lib.utils.c.f("TboxModel", "setGpsReset type:" + i);
        try {
            if (this.rQ != null) {
                this.rQ.setGpsReset(i);
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "setGpsReset mCarTboxManager == null type = " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSimStatusAsync() {
        com.xiaopeng.lib.utils.c.f("TboxModel", "getSimStatusAsync");
        try {
            if (this.rQ != null) {
                this.rQ.getSimStatusAsync();
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "getSimStatusAsync mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestTBoxModemStatus() {
        com.xiaopeng.lib.utils.c.f("TboxModel", "requestTBoxModemStatus");
        try {
            if (this.rQ != null) {
                this.rQ.requestTboxModemStatus();
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "requestTBoxModemStatus mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestTBoxBandModemStatus() {
        com.xiaopeng.lib.utils.c.f("TboxModel", "requestTBoxBandModemStatus");
        try {
            if (this.rQ != null) {
                this.rQ.requestTboxBandModemStatus();
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "requestTBoxBandModemStatus mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(TboxModemBand tboxModemBand) {
        com.xiaopeng.lib.utils.c.f("TboxModel", "setTBoxBandModem bands:" + tboxModemBand.toString());
        try {
            if (this.rQ != null) {
                this.rQ.setTboxBandModem(new Gson().toJson(tboxModemBand));
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "setTBoxBandModem mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(TboxCanControlMsg tboxCanControlMsg) {
        com.xiaopeng.lib.utils.c.f("TboxModel", "setTboxCanControlMsg msg:" + tboxCanControlMsg.toString());
        try {
            if (this.rQ != null) {
                this.rQ.setTboxCanControlMsg(new Gson().toJson(tboxCanControlMsg));
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "setTboxCanControlMsg mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(TboxCanControlIPMsg tboxCanControlIPMsg) {
        com.xiaopeng.lib.utils.c.f("TboxModel", "setTboxCanControlMsg msg:" + tboxCanControlIPMsg.toString());
        try {
            if (this.rQ != null) {
                this.rQ.setTboxCanControlMsg(new Gson().toJson(tboxCanControlIPMsg));
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "setTboxCanControlMsg mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDvTestReq(int i) {
        com.xiaopeng.lib.utils.c.f("TboxModel", "setDvTestReq req:" + i);
        try {
            if (this.rQ != null) {
                this.rQ.setDvTestReq(i);
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "setDvTestReq mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDvTempMsg() {
        String str = null;
        try {
            if (this.rQ != null) {
                str = this.rQ.getDvTempMsg();
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "getDvTempMsg mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.xiaopeng.lib.utils.c.f("TboxModel", "getDvTempMsg res:" + str);
        return str;
    }

    public void sendUpgradingTboxByUdiskReq(String str) {
        com.xiaopeng.lib.utils.c.f("TboxModel", "sendUpgradingTboxByUdiskReq msg:" + str);
        try {
            if (this.rQ != null) {
                this.rQ.sendUpgradingTboxByUdiskReq(str);
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "sendUpgradingTboxByUdiskReq mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRenewalPartsRequest(String str) {
        com.xiaopeng.lib.utils.c.f("TboxModel", "sendRenewalPartsRequest msg:" + str);
        try {
            if (this.rQ != null) {
                this.rQ.sendRenewalPartsRequest(str);
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "sendRenewalPartsRequest mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMultiBleRenewalRequest(String str) {
        com.xiaopeng.lib.utils.c.f("TboxModel", "sendMultiBleRenewalRequest msg:" + str);
        try {
            if (this.rQ != null) {
                this.rQ.sendMultiBleRenewalRequest(str);
            } else {
                com.xiaopeng.lib.utils.c.i("TboxModel", "sendMultiBleRenewalRequest mCarTboxManager == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
