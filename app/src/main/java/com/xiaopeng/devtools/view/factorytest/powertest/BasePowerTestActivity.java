package com.xiaopeng.devtools.view.factorytest.powertest;

import android.car.hardware.CarPropertyValue;
import com.xiaopeng.devtools.bean.car.McuAckPwrDebug;
import com.xiaopeng.devtools.view.ToolBarActivity;
import com.xiaopeng.lib.utils.c;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public abstract class BasePowerTestActivity extends ToolBarActivity {
    protected String mTag = "BasePowerTestActivity";

    abstract void a(McuAckPwrDebug mcuAckPwrDebug);

    abstract void ny();

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue.getPropertyId() == 356516106) {
            c.f(this.mTag, "ID_MCU_ACK_PWR_DEBUG");
            a(new McuAckPwrDebug((int[]) carPropertyValue.getValue()));
        }
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ny();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
