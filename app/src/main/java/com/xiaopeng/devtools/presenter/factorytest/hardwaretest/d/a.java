package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d;

import android.car.hardware.CarPropertyValue;
import android.location.GpsSatellite;
import android.location.Location;
import android.text.TextUtils;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.model.a.d;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.g;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: GPSPresenter.java */
/* loaded from: classes12.dex */
public class a implements b {
    private String mAction;
    private int theme;
    private com.xiaopeng.devtools.model.c.a.d.b wQ;
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.a wR;
    private d wy;

    public a(com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.a aVar) {
        this.theme = -1;
        this.wQ = new com.xiaopeng.devtools.model.c.a.d.a(this);
        this.wy = d.fz();
        this.wR = aVar;
        this.mAction = "";
        iv();
    }

    public a() {
        this.theme = -1;
        this.wQ = new com.xiaopeng.devtools.model.c.a.d.a();
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue.getPropertyId() == 557846560) {
            final int intValue = ((Integer) carPropertyValue.getValue()).intValue();
            c.f("GPSPresenter", "ID_TBOX_GPS_RESET status = " + intValue);
            j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.-$$Lambda$a$xxRKS4IQaEmKanfp5pEyKQW4_wo
                @Override // java.lang.Runnable
                public final void run() {
                    a.this.cp(intValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void cp(int i) {
        g.show(i == 0 ? R.string.gps_reboot_success : R.string.gps_reboot_fail);
        if (this.theme == 1 && !TextUtils.isEmpty(this.mAction)) {
            com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, i == 0 ? EcuUpdateResult.RESULT_SUCCESS : EcuUpdateResult.RESULT_FAIL);
            this.mAction = "";
        }
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public void setTheme(int i) {
        this.theme = i;
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public void setGpsReset(int i) {
        this.wy.setGpsReset(i);
        switch (i) {
            case 0:
                this.mAction = "GPS hot reset";
                break;
            case 1:
                this.mAction = "GPS warm reset";
                break;
            case 2:
                this.mAction = "GPS cold reset";
                break;
        }
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public void gw() {
        this.wQ.gw();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public Location getLastKnownLocation() {
        return this.wQ.getLastKnownLocation();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public void a(Location location) {
        this.wQ.a(location);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public boolean gz() {
        return this.wQ.gz();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public boolean gA() {
        return this.wQ.gA();
    }

    public void iX() {
        this.wR.iX();
    }

    public void bX(String str) {
        this.wR.bX(str);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public void gy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        this.wQ.gy();
    }

    public void a(int i, List<GpsSatellite> list) {
        this.wR.a(i, list);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public void gB() {
        this.wQ.gB();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public void gC() {
        this.wQ.gC();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b
    public void gD() {
        this.wQ.gD();
    }

    public void a(float[] fArr, long j) {
        this.wR.a(fArr, j);
    }

    public void a(float[] fArr, long j, float f) {
        this.wR.a(fArr, j, f);
    }

    public void o(int i, int i2) {
        this.wR.o(i, i2);
    }
}
