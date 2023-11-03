package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.g;

import android.car.hardware.CarPropertyValue;
import android.os.Handler;
import android.os.Message;
import com.xiaopeng.lib.utils.c;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: PsuTestPresenter.java */
/* loaded from: classes12.dex */
public class a {
    private WeakReference<Handler> sz;
    private boolean xa = false;
    public com.xiaopeng.devtools.model.c.a.e.a wZ = new com.xiaopeng.devtools.model.c.a.e.a();

    public a(Handler handler) {
        this.sz = new WeakReference<>(handler);
    }

    public void init() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue.getPropertyId() == 557847607) {
            c.f("PsuTestPresenter", "ID_MCU_PSU_TEST");
            int intValue = ((Integer) carPropertyValue.getValue()).intValue();
            if (this.xa) {
                this.xa = false;
                if (this.sz != null) {
                    sendMessage(1, intValue);
                }
            }
        }
    }

    private void sendMessage(int i, int i2) {
        if (this.sz.get() != null) {
            Message obtainMessage = this.sz.get().obtainMessage();
            obtainMessage.what = i;
            obtainMessage.arg1 = i2;
            this.sz.get().sendMessage(obtainMessage);
        }
    }

    public void jj() {
        this.xa = true;
        com.xiaopeng.devtools.model.a.c.fx().setPsuTestReq(1);
    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
    }

    public boolean jk() {
        return this.wZ.p(1155, 22336);
    }
}
