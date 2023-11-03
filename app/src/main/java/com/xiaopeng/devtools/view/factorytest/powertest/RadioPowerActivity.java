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
public class RadioPowerActivity extends BasePowerTestActivity implements MyPowerView.a {
    private MyPowerView OJ;
    private int OM = -3;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.powertest.RadioPowerActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 10) {
                switch (message.arg1) {
                    case 1:
                        RadioPowerActivity.this.OJ.setCurrentState(RadioPowerActivity.this.getString(R.string.state_on));
                        RadioPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        RadioPowerActivity.this.OJ.setControlOffEnableStatus(true);
                        return;
                    case 2:
                        RadioPowerActivity.this.OJ.setCurrentState(RadioPowerActivity.this.getString(R.string.state_off));
                        RadioPowerActivity.this.OJ.setControlOnEnableStatus(true);
                        RadioPowerActivity.this.OJ.setControlOffEnableStatus(false);
                        return;
                    default:
                        RadioPowerActivity.this.OJ.setCurrentState(RadioPowerActivity.this.getString(R.string.state_unknown));
                        RadioPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        RadioPowerActivity.this.OJ.setControlOffEnableStatus(false);
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
        setContentView(R.layout.activity_radio_power);
        setTarget(getString(R.string.text_radio_wire_power));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    void a(McuAckPwrDebug mcuAckPwrDebug) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 10;
        if (mcuAckPwrDebug.uP2ANT_12V_EN_RESULT == 0) {
            return;
        }
        if (this.OM == -3) {
            this.OM = mcuAckPwrDebug.uP2ANT_12V_EN_RESULT;
        }
        obtainMessage.arg1 = mcuAckPwrDebug.uP2ANT_12V_EN_RESULT;
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
        androidPwrDebug.ant12vEn = 1;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nA() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.ant12vEn = 2;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nB() {
        a(getString(R.string.text_radio_wire_power), true, "");
        this.OJ.setResult(true);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nC() {
        a(getString(R.string.text_radio_wire_power), false, "");
        this.OJ.setResult(false);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        a(getString(R.string.text_radio_wire_power), true, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_radio_power));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        a(getString(R.string.text_radio_wire_power), false, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_radio_power));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    public void ny() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.ant12vEn = this.OM;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }
}
