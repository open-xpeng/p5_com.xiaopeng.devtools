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
public class SWPowerActivity extends BasePowerTestActivity implements MyPowerView.a {
    private MyPowerView OJ;
    private int OM = -3;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.powertest.SWPowerActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 6) {
                switch (message.arg1) {
                    case 1:
                        SWPowerActivity.this.OJ.setCurrentState(SWPowerActivity.this.getString(R.string.state_on));
                        SWPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        SWPowerActivity.this.OJ.setControlOffEnableStatus(true);
                        return;
                    case 2:
                        SWPowerActivity.this.OJ.setCurrentState(SWPowerActivity.this.getString(R.string.state_off));
                        SWPowerActivity.this.OJ.setControlOnEnableStatus(true);
                        SWPowerActivity.this.OJ.setControlOffEnableStatus(false);
                        return;
                    default:
                        SWPowerActivity.this.OJ.setCurrentState(SWPowerActivity.this.getString(R.string.state_unknown));
                        SWPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        SWPowerActivity.this.OJ.setControlOffEnableStatus(false);
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
        setContentView(R.layout.activity_swpower);
        setTarget(getString(R.string.text_sw_power));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    void a(McuAckPwrDebug mcuAckPwrDebug) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 6;
        if (mcuAckPwrDebug.uP25V25_SW_EN_RESULT == 0) {
            return;
        }
        if (this.OM == -3) {
            this.OM = mcuAckPwrDebug.uP25V25_SW_EN_RESULT;
        }
        obtainMessage.arg1 = mcuAckPwrDebug.uP25V25_SW_EN_RESULT;
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
        androidPwrDebug.SWEn5v25 = 1;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nA() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.SWEn5v25 = 2;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nB() {
        a(getString(R.string.text_sw_power), true, "");
        this.OJ.setResult(true);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nC() {
        a(getString(R.string.text_sw_power), false, "");
        this.OJ.setResult(false);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        a(getString(R.string.text_sw_power), true, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_sw_power));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        a(getString(R.string.text_sw_power), false, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_sw_power));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    public void ny() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.SWEn5v25 = this.OM;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }
}
