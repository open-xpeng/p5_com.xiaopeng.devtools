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
public class BlowerPowerActivity extends BasePowerTestActivity implements MyPowerView.a {
    private MyPowerView OJ;
    private int HP = -3;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.powertest.BlowerPowerActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 12) {
                switch (message.arg1) {
                    case 1:
                        BlowerPowerActivity.this.OJ.setCurrentState(BlowerPowerActivity.this.getString(R.string.state_on));
                        BlowerPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        BlowerPowerActivity.this.OJ.setControlOffEnableStatus(true);
                        return;
                    case 2:
                        BlowerPowerActivity.this.OJ.setCurrentState(BlowerPowerActivity.this.getString(R.string.state_off));
                        BlowerPowerActivity.this.OJ.setControlOnEnableStatus(true);
                        BlowerPowerActivity.this.OJ.setControlOffEnableStatus(false);
                        return;
                    default:
                        BlowerPowerActivity.this.OJ.setCurrentState(BlowerPowerActivity.this.getString(R.string.state_unknown));
                        BlowerPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        BlowerPowerActivity.this.OJ.setControlOffEnableStatus(false);
                        return;
                }
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
        setContentView(R.layout.activity_blower_power);
        setTarget(getString(R.string.text_blower_power));
        this.mTag = "BlowerPowerActivity";
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    void a(McuAckPwrDebug mcuAckPwrDebug) {
        if (mcuAckPwrDebug.uP2FAN_12V_EN_RESULT == 0) {
            return;
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 12;
        if (this.HP == -3) {
            this.HP = mcuAckPwrDebug.uP2FAN_12V_EN_RESULT;
        }
        obtainMessage.arg1 = mcuAckPwrDebug.uP2FAN_12V_EN_RESULT;
        this.mHandler.sendMessage(obtainMessage);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.OJ = (MyPowerView) findViewById(R.id.view_power_test);
        this.OJ.setCurrentState(getString(R.string.state_unknown));
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.debugPwrSt = 1;
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
        androidPwrDebug.fan12vEn = 1;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nA() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.fan12vEn = 2;
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
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_blower_power));
        a(getString(R.string.text_blower_power), true, "");
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_blower_power));
        a(getString(R.string.text_blower_power), false, "");
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
        finish();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    public void ny() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.fan12vEn = this.HP;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }
}
