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
public class GPSWirePowerActivity extends BasePowerTestActivity implements MyPowerView.a {
    private MyPowerView OJ;
    private boolean OV = false;
    private int OM = -3;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.powertest.GPSWirePowerActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 2:
                    switch (message.arg1) {
                        case 1:
                            GPSWirePowerActivity.this.OV = true;
                            AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
                            androidPwrDebug.debugPwrSt = 1;
                            c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
                            return;
                        case 2:
                            GPSWirePowerActivity.this.OV = false;
                            GPSWirePowerActivity.this.OJ.setControlOnEnableStatus(false);
                            GPSWirePowerActivity.this.OJ.setControlOffEnableStatus(false);
                            return;
                        default:
                            GPSWirePowerActivity.this.OJ.setCurrentState(GPSWirePowerActivity.this.getString(R.string.state_off));
                            GPSWirePowerActivity.this.OJ.setControlOnEnableStatus(false);
                            GPSWirePowerActivity.this.OJ.setControlOffEnableStatus(false);
                            return;
                    }
                case 3:
                    switch (message.arg1) {
                        case 1:
                            GPSWirePowerActivity.this.OJ.setCurrentState(GPSWirePowerActivity.this.getString(R.string.state_on));
                            GPSWirePowerActivity.this.OJ.setControlOnEnableStatus(false);
                            GPSWirePowerActivity.this.OJ.setControlOffEnableStatus(true);
                            return;
                        case 2:
                            GPSWirePowerActivity.this.OJ.setCurrentState(GPSWirePowerActivity.this.getString(R.string.state_off));
                            GPSWirePowerActivity.this.OJ.setControlOnEnableStatus(true);
                            GPSWirePowerActivity.this.OJ.setControlOffEnableStatus(false);
                            return;
                        default:
                            GPSWirePowerActivity.this.OJ.setCurrentState(GPSWirePowerActivity.this.getString(R.string.state_unknown));
                            GPSWirePowerActivity.this.OJ.setControlOnEnableStatus(false);
                            GPSWirePowerActivity.this.OJ.setControlOffEnableStatus(false);
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
        setContentView(R.layout.activity_gpswire_power);
        setTarget(getString(R.string.text_gps_wire_power));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    void a(McuAckPwrDebug mcuAckPwrDebug) {
        Message obtainMessage = this.mHandler.obtainMessage();
        if (!this.OV) {
            if (mcuAckPwrDebug.uP2GNSS_PWR_EN_RESULT == 0) {
                return;
            }
            obtainMessage.what = 2;
            obtainMessage.arg1 = mcuAckPwrDebug.uP2GNSS_PWR_EN_RESULT;
        } else if (mcuAckPwrDebug.uP2GNSS_ANT_PWR_RESULT == 0) {
            return;
        } else {
            if (this.OM == -3) {
                this.OM = mcuAckPwrDebug.uP2GNSS_ANT_PWR_RESULT;
            }
            obtainMessage.what = 3;
            obtainMessage.arg1 = mcuAckPwrDebug.uP2GNSS_ANT_PWR_RESULT;
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
        if (!this.OV) {
            androidPwrDebug.gnssPwr = 1;
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
        androidPwrDebug.gnssAntPwr = 1;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nA() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.gnssAntPwr = 2;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nB() {
        a(getString(R.string.text_gps_wire_power), true, "");
        this.OJ.setResult(true);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nC() {
        a(getString(R.string.text_gps_wire_power), false, "");
        this.OJ.setResult(false);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        a(getString(R.string.text_gps_wire_power), true, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_gps_wire_power));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        a(getString(R.string.text_gps_wire_power), false, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_gps_wire_power));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    public void ny() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.gnssAntPwr = this.OM;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }
}
