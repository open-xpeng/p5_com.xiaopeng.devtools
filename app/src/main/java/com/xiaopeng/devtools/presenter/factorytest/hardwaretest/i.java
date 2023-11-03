package com.xiaopeng.devtools.presenter.factorytest.hardwaretest;

import android.car.hardware.CarPropertyValue;
import android.os.Handler;
import com.google.gson.Gson;
import com.xiaopeng.devtools.bean.car.TboxApn;
import com.xiaopeng.devtools.utils.m;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: MobileNetworkPresenter.java */
/* loaded from: classes12.dex */
public class i implements f {
    private com.xiaopeng.devtools.model.c.a.e ww;
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork.a wx;
    private com.xiaopeng.devtools.model.a.d wy;

    public i(Handler handler, com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork.a aVar) {
        this.ww = new com.xiaopeng.devtools.model.c.a.g(handler);
        this.wy = com.xiaopeng.devtools.model.a.d.fz();
        this.wx = aVar;
    }

    public i() {
        this.ww = new com.xiaopeng.devtools.model.c.a.g();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f
    public void onInit() {
        iv();
        this.ww.onInit();
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        int propertyId = carPropertyValue.getPropertyId();
        if (propertyId == 554700817) {
            com.xiaopeng.lib.utils.c.f("MobileNetworkPresenter", "ID_TBOX_CDU_APN");
            a((TboxApn) new Gson().fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxApn.class));
        } else if (propertyId == 557846546) {
            com.xiaopeng.lib.utils.c.f("MobileNetworkPresenter", "ID_TBOX_SIM_ST");
            int intValue = ((Integer) carPropertyValue.getValue()).intValue();
            com.xiaopeng.lib.utils.c.f("MobileNetworkPresenter", "ID_TBOX_SIM_ST simState = " + intValue);
            cm(intValue);
        }
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f
    public void fT() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        this.ww.fT();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f
    public void fU() {
        this.ww.fU();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f
    public void getSimStatusAsync() {
        this.wy.getSimStatusAsync();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f
    public boolean fV() {
        return this.ww.fV();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f
    public void iT() {
        com.xiaopeng.lib.utils.j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.-$$Lambda$i$KOtL1IVCFscah2Qk5AxF2y5NdT8
            @Override // java.lang.Runnable
            public final void run() {
                i.this.iU();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void iU() {
        if (this.wx != null) {
            this.wx.aw(m.cF("192.168.225.1"));
        }
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f
    public void p(String str, int i) {
        this.ww.p(str, i);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f
    public void bu(String str) {
        this.ww.bu(str);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f
    public boolean bv(String str) {
        return this.ww.bv(str);
    }

    public void cm(final int i) {
        com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.-$$Lambda$i$w2xVpHMEiaYJW2JwXlnkSNXuAXk
            @Override // java.lang.Runnable
            public final void run() {
                i.this.cn(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void cn(int i) {
        if (this.wx != null) {
            this.wx.cm(i);
        }
    }

    public void a(final TboxApn tboxApn) {
        com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.-$$Lambda$i$9vE65q9O272ay1o1qi15k2NqLV0
            @Override // java.lang.Runnable
            public final void run() {
                i.this.b(tboxApn);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(TboxApn tboxApn) {
        if (this.wx != null) {
            this.wx.a(tboxApn);
        }
    }
}
