package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.a;

import android.car.hardware.CarPropertyValue;
import com.google.gson.Gson;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.bean.car.BatteryInfo;
import com.xiaopeng.devtools.model.a.d;
import com.xiaopeng.lib.utils.c;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: BatteryPresenter.java */
/* loaded from: classes12.dex */
public class a implements b {
    private com.xiaopeng.devtools.model.c.a.a.a wC = new com.xiaopeng.devtools.model.c.a.a.a();
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.battery.a wD;
    private BatteryInfo wE;

    public a(com.xiaopeng.devtools.view.factorytest.hardwaretest.battery.a aVar) {
        d.fz();
        this.wD = aVar;
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.a.b
    public void init() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CarPropertyValue carPropertyValue) {
        int propertyId = carPropertyValue.getPropertyId();
        if (propertyId == 554700812) {
            if (a.b.getBoolean("SUPPORT_TMCU")) {
                this.wE = (BatteryInfo) new Gson().fromJson((String) carPropertyValue.getValue(), (Class<Object>) BatteryInfo.class);
                c.f("BatteryPresenter", "ID_TBOX_DV_BATT batteryInfo:" + this.wE.toString());
                if (this.wD != null) {
                    this.wD.a(this.wE);
                }
            }
        } else if (propertyId == 560993331 && !a.b.getBoolean("SUPPORT_TMCU")) {
            this.wE = new BatteryInfo((byte[]) carPropertyValue.getValue());
            c.f("BatteryPresenter", "ID_MCU_DVTEST_BBAT batteryInfo:" + this.wE.toString());
            if (this.wD != null) {
                this.wD.a(this.wE);
            }
        }
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.a.b
    public void gi() {
        this.wC.gi();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.a.b
    public void gj() {
        this.wC.gj();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.a.b
    public void release() {
        EventBus.getDefault().unregister(this);
    }
}
