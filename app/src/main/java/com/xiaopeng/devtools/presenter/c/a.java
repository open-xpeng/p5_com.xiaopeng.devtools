package com.xiaopeng.devtools.presenter.c;

import android.car.hardware.CarPropertyValue;
import com.google.gson.Gson;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.TboxGsmBand;
import com.xiaopeng.devtools.bean.car.TboxLteBand;
import com.xiaopeng.devtools.bean.car.TboxModemBand;
import com.xiaopeng.devtools.bean.car.TboxUmtsBand;
import com.xiaopeng.devtools.model.a.d;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.g;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: TboxBandPresenter.java */
/* loaded from: classes12.dex */
public class a {
    private com.xiaopeng.devtools.view.modemui.a yp;
    private TboxModemBand yq;
    private d wy = d.fz();
    private Gson gson = new Gson();

    public a(com.xiaopeng.devtools.view.modemui.a aVar) {
        this.yp = aVar;
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        int propertyId = carPropertyValue.getPropertyId();
        if (propertyId == 554700821) {
            c.f("TboxBandPresenter", "ID_TBOX_BAND_MODEM");
            this.yq = (TboxModemBand) this.gson.fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxModemBand.class);
            a(this.yq.getGSM());
            a(this.yq.getUMTS());
            a(this.yq.getLTE());
        } else if (propertyId == 557846551) {
            c.f("TboxBandPresenter", "ID_TBOX_CDU_SET_BAND_MODEM_RESP");
            final int intValue = ((Integer) carPropertyValue.getValue()).intValue();
            j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.c.-$$Lambda$a$nVt9rRXXt465Yd68MYTQU3JztY8
                @Override // java.lang.Runnable
                public final void run() {
                    a.cp(intValue);
                }
            });
            requestTBoxBandModemStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void cp(int i) {
        g.show(i == 0 ? R.string.set_band_success : R.string.set_band_fail);
    }

    public void requestTBoxBandModemStatus() {
        this.wy.requestTBoxBandModemStatus();
    }

    public void a(TboxModemBand tboxModemBand) {
        this.wy.a(tboxModemBand);
    }

    public void a(final TboxGsmBand tboxGsmBand) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.c.-$$Lambda$a$nQkd6XRTSUKv85NK1MezR0Nojvk
            @Override // java.lang.Runnable
            public final void run() {
                a.this.b(tboxGsmBand);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(TboxGsmBand tboxGsmBand) {
        if (this.yp != null) {
            this.yp.a(tboxGsmBand);
        }
    }

    public void a(final TboxUmtsBand tboxUmtsBand) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.c.-$$Lambda$a$tgufL4b63adkCJdDao8phoIHF78
            @Override // java.lang.Runnable
            public final void run() {
                a.this.b(tboxUmtsBand);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(TboxUmtsBand tboxUmtsBand) {
        if (this.yp != null) {
            this.yp.a(tboxUmtsBand);
        }
    }

    public void a(final TboxLteBand tboxLteBand) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.c.-$$Lambda$a$yj-LRotmpMGHxERde7P6qj7UQ3E
            @Override // java.lang.Runnable
            public final void run() {
                a.this.b(tboxLteBand);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(TboxLteBand tboxLteBand) {
        if (this.yp != null) {
            this.yp.a(tboxLteBand);
        }
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
