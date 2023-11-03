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
public class FourGPowerActivity extends BasePowerTestActivity implements MyPowerView.a {
    private MyPowerView OJ;
    private int OM = -3;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.powertest.FourGPowerActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 4) {
                switch (message.arg1) {
                    case 1:
                        FourGPowerActivity.this.OJ.setCurrentState(FourGPowerActivity.this.getString(R.string.state_on));
                        FourGPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        FourGPowerActivity.this.OJ.setControlOffEnableStatus(true);
                        return;
                    case 2:
                        FourGPowerActivity.this.OJ.setCurrentState(FourGPowerActivity.this.getString(R.string.state_off));
                        FourGPowerActivity.this.OJ.setControlOnEnableStatus(true);
                        FourGPowerActivity.this.OJ.setControlOffEnableStatus(false);
                        return;
                    default:
                        FourGPowerActivity.this.OJ.setCurrentState(FourGPowerActivity.this.getString(R.string.state_unknown));
                        FourGPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        FourGPowerActivity.this.OJ.setControlOffEnableStatus(false);
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
        setContentView(R.layout.activity_four_gpower);
        setTarget(getString(R.string.text_4g_power));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    void a(McuAckPwrDebug mcuAckPwrDebug) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 4;
        if (mcuAckPwrDebug.uP24G_BAT_ON_OFF_RESULT == 0) {
            return;
        }
        if (this.OM == -3) {
            this.OM = mcuAckPwrDebug.uP24G_BAT_ON_OFF_RESULT;
        }
        obtainMessage.arg1 = mcuAckPwrDebug.uP24G_BAT_ON_OFF_RESULT;
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
        androidPwrDebug.batOn4G = 1;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nA() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.batOn4G = 2;
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
        a(getString(R.string.text_4g_power), true, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_4g_power));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        a(getString(R.string.text_4g_power), false, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_4g_power));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    public void ny() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.batOn4G = this.OM;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }
}
