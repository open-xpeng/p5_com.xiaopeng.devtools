package com.xiaopeng.devtools.view.factorytest.powertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.AndroidPwrDebug;
import com.xiaopeng.devtools.bean.car.McuAckPwrDebug;
import com.xiaopeng.devtools.model.a.c;
import com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class FourGModulePowerActivity extends BasePowerTestActivity implements MyPowerView.a {
    private MyPowerView OJ;
    private boolean OR = false;
    private int OM = -3;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.powertest.FourGModulePowerActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 4:
                    switch (message.arg1) {
                        case 1:
                            FourGModulePowerActivity.this.OR = true;
                            AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
                            androidPwrDebug.debugPwrSt = 1;
                            c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
                            return;
                        case 2:
                            FourGModulePowerActivity.this.OR = false;
                            FourGModulePowerActivity.this.OJ.setControlOnEnableStatus(false);
                            FourGModulePowerActivity.this.OJ.setControlOffEnableStatus(false);
                            return;
                        default:
                            FourGModulePowerActivity.this.OJ.setCurrentState(FourGModulePowerActivity.this.getString(R.string.state_off));
                            FourGModulePowerActivity.this.OJ.setControlOnEnableStatus(false);
                            FourGModulePowerActivity.this.OJ.setControlOffEnableStatus(false);
                            return;
                    }
                case 5:
                    switch (message.arg1) {
                        case 1:
                            FourGModulePowerActivity.this.OJ.setCurrentState(FourGModulePowerActivity.this.getString(R.string.state_on));
                            FourGModulePowerActivity.this.OJ.setControlOnEnableStatus(false);
                            FourGModulePowerActivity.this.OJ.setControlOffEnableStatus(true);
                            return;
                        case 2:
                            FourGModulePowerActivity.this.OJ.setCurrentState(FourGModulePowerActivity.this.getString(R.string.state_off));
                            FourGModulePowerActivity.this.OJ.setControlOnEnableStatus(true);
                            FourGModulePowerActivity.this.OJ.setControlOffEnableStatus(false);
                            return;
                        default:
                            FourGModulePowerActivity.this.OJ.setCurrentState(FourGModulePowerActivity.this.getString(R.string.state_unknown));
                            FourGModulePowerActivity.this.OJ.setControlOnEnableStatus(false);
                            FourGModulePowerActivity.this.OJ.setControlOffEnableStatus(false);
                            return;
                    }
                default:
                    return;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_four_gmodule_power);
        setTarget(getString(R.string.text_4g_module_power));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    void a(McuAckPwrDebug mcuAckPwrDebug) {
        Message obtainMessage = this.mHandler.obtainMessage();
        if (!this.OR) {
            if (mcuAckPwrDebug.uP24G_BAT_ON_OFF_RESULT == 0) {
                return;
            }
            obtainMessage.what = 4;
            obtainMessage.arg1 = mcuAckPwrDebug.uP24G_BAT_ON_OFF_RESULT;
        } else if (mcuAckPwrDebug.uP24G_PWR_ON_RESULT == 0) {
            return;
        } else {
            if (this.OM == -3) {
                this.OM = mcuAckPwrDebug.uP24G_PWR_ON_RESULT;
            }
            obtainMessage.what = 5;
            obtainMessage.arg1 = mcuAckPwrDebug.uP24G_PWR_ON_RESULT;
        }
        this.mHandler.sendMessage(obtainMessage);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.OJ = (MyPowerView) findViewById(R.id.view_power_test);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        if (!this.OR) {
            androidPwrDebug.batOn4G = 1;
        } else {
            androidPwrDebug.debugPwrSt = 1;
        }
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.OJ.setOnControlListener(this);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nz() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.pwr4G = 1;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nA() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.pwr4G = 2;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nB() {
        this.OJ.setResult(true);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nC() {
        this.OJ.setResult(false);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        a(getString(R.string.text_4g_module_power), true, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_4g_module_power));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        a(getString(R.string.text_4g_module_power), false, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_4g_module_power));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    public void ny() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.pwr4G = this.OM;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }
}
