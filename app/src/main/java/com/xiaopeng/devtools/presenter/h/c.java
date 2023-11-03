package com.xiaopeng.devtools.presenter.h;

import android.car.hardware.CarPropertyValue;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.utils.g;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: McuUpdatePresenter.java */
/* loaded from: classes12.dex */
public class c {
    private com.xiaopeng.devtools.view.update.b zN;
    private com.xiaopeng.devtools.model.a.c zO = com.xiaopeng.devtools.model.a.c.fx();
    private String zP;
    private String zQ;
    private String zR;

    public c(com.xiaopeng.devtools.view.update.b bVar) {
        this.zN = bVar;
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        switch (carPropertyValue.getPropertyId()) {
            case 557847592:
                int intValue = ((Integer) carPropertyValue.getValue()).intValue();
                com.xiaopeng.lib.utils.c.f("McuUpdatePresenter", "ID_OTA_MCU_REQ_STATUS status: " + intValue);
                this.zN.dz(intValue);
                return;
            case 557847593:
                int intValue2 = ((Integer) carPropertyValue.getValue()).intValue();
                com.xiaopeng.lib.utils.c.f("McuUpdatePresenter", "ID_OTA_MCU_REQ_UPDATEFILE status: " + intValue2);
                switch (intValue2) {
                    case 0:
                        cl(this.zP);
                        return;
                    case 1:
                        cl(this.zQ);
                        return;
                    case 2:
                        cl(this.zR);
                        return;
                    default:
                        return;
                }
            case 557847594:
            default:
                return;
            case 557847595:
                int intValue3 = ((Integer) carPropertyValue.getValue()).intValue();
                com.xiaopeng.lib.utils.c.f("McuUpdatePresenter", "ID_OTA_MCU_UPDATE_STATUS status: " + intValue3);
                this.zN.dA(intValue3);
                return;
        }
    }

    private void cl(String str) {
        com.xiaopeng.lib.utils.c.f("McuUpdatePresenter", "sendOtaMcuUpdateFile path : " + str);
        this.zO.setOtaMcuSendUpdatefile(str);
        com.xiaopeng.devtools.system.b.c.sleep(100L);
        this.zO.setOtaMcuReqUpdatefile(0);
    }

    public boolean hasMcuFileFromUsb() {
        String[] strArr = null;
        this.zP = null;
        this.zQ = null;
        this.zR = null;
        String str = g.V(MyApplication.getContext()) + "/mcu/";
        try {
            strArr = g.J(str, ".hex");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (strArr != null) {
            for (String str2 : strArr) {
                if (str2.contains("_FlashDriver.hex")) {
                    this.zP = str + str2;
                } else if (str2.contains("_A.hex")) {
                    this.zQ = str + str2;
                } else if (str2.contains("_B.hex")) {
                    this.zR = str + str2;
                }
            }
        }
        com.xiaopeng.lib.utils.c.f("McuUpdatePresenter", "hasMcuFileFromUsb mPathMcuFlashDriver:" + this.zP + ", mPathMcuABlock:" + this.zQ + ", mPathMcuBBlock:" + this.zR);
        return (this.zP == null || this.zQ == null || this.zR == null) ? false : true;
    }

    public void setOtaMcuReqStatus(int i) {
        this.zO.setOtaMcuReqStatus(i);
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
