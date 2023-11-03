package com.xiaopeng.devtools.presenter.h;

import android.car.hardware.CarPropertyValue;
import com.google.gson.Gson;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.bean.car.TboxUpdateReq;
import com.xiaopeng.devtools.bean.car.TboxUpdateResponse;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.s;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: TboxUpdatePresenter.java */
/* loaded from: classes12.dex */
public class e {
    private String Aa;
    private String Ab;
    private com.xiaopeng.devtools.view.update.d zW;
    private int zX = 0;
    private int zY = 0;
    private int zZ = -99;
    private com.xiaopeng.devtools.model.a.d wy = com.xiaopeng.devtools.model.a.d.fz();

    public e(com.xiaopeng.devtools.view.update.d dVar) {
        this.zW = dVar;
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue.getPropertyId() == 554700845) {
            TboxUpdateResponse tboxUpdateResponse = (TboxUpdateResponse) new Gson().fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxUpdateResponse.class);
            com.xiaopeng.lib.utils.c.f("TboxUpdatePresenter", "ID_TBOX_OTA_UDISK :" + tboxUpdateResponse.toString());
            switch (tboxUpdateResponse.getKey()) {
                case 100:
                    this.zX = tboxUpdateResponse.getValue();
                    updateView();
                    return;
                case 101:
                    this.zY = tboxUpdateResponse.getValue();
                    updateView();
                    return;
                case 102:
                    this.zZ = tboxUpdateResponse.getValue();
                    updateView();
                    return;
                default:
                    return;
            }
        }
    }

    private void updateView() {
        if (this.zW != null) {
            this.zW.dC(0);
        }
    }

    public int kx() {
        return this.zX;
    }

    public int ky() {
        return this.zY;
    }

    public int getUpdateResult() {
        return this.zZ;
    }

    public boolean kz() {
        return this.zZ != -99;
    }

    public boolean kA() {
        return this.zZ == 0;
    }

    public boolean kB() {
        String[] strArr = null;
        this.Ab = null;
        this.Aa = g.V(MyApplication.getContext()) + "/tbox/";
        try {
            strArr = g.J(this.Aa, "TBOX_");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (strArr != null && strArr.length > 0) {
            this.Ab = strArr[0];
        }
        com.xiaopeng.lib.utils.c.f("TboxUpdatePresenter", "hasTboxFileFromUsb mTboxUdiskPath:" + this.Aa + " , mTboxBinPath:" + this.Ab);
        return this.Ab != null;
    }

    public void kC() {
        com.xiaopeng.lib.utils.c.i("TboxUpdatePresenter", "updateTboxBin");
        this.zX = 0;
        this.zY = 0;
        this.zZ = -99;
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.h.-$$Lambda$e$mSQWYDj9X1aNRjSGV7whQHxRBgE
            @Override // java.lang.Runnable
            public final void run() {
                e.this.kD();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void kD() {
        try {
            s.p(this.Aa + this.Ab, "/mnt/sdcard/tftpboot/" + this.Ab, "192.168.225.1");
            updateView();
            sendUpgradingTboxByUdiskReq(new Gson().toJson(new TboxUpdateReq(100, "/mnt/sdcard/tftpboot/" + this.Ab)));
        } catch (Exception e) {
            e.printStackTrace();
            this.zZ = -98;
            updateView();
        }
    }

    public void sendUpgradingTboxByUdiskReq(String str) {
        this.wy.sendUpgradingTboxByUdiskReq(str);
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
