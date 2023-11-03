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
public class FaceUsbPowerActivity extends BasePowerTestActivity implements MyPowerView.a {
    private MyPowerView OJ;
    private boolean OO = false;
    private int OM = -3;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.powertest.FaceUsbPowerActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 6) {
                switch (message.arg1) {
                    case 1:
                        FaceUsbPowerActivity.this.OO = true;
                        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
                        androidPwrDebug.debugPwrSt = 1;
                        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
                        return;
                    case 2:
                        FaceUsbPowerActivity.this.OO = false;
                        FaceUsbPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        FaceUsbPowerActivity.this.OJ.setControlOffEnableStatus(false);
                        return;
                    default:
                        FaceUsbPowerActivity.this.OJ.setCurrentState(FaceUsbPowerActivity.this.getString(R.string.state_off));
                        FaceUsbPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        FaceUsbPowerActivity.this.OJ.setControlOffEnableStatus(false);
                        return;
                }
            } else if (i == 9) {
                switch (message.arg1) {
                    case 1:
                        FaceUsbPowerActivity.this.OJ.setCurrentState(FaceUsbPowerActivity.this.getString(R.string.state_on));
                        FaceUsbPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        FaceUsbPowerActivity.this.OJ.setControlOffEnableStatus(true);
                        return;
                    case 2:
                        FaceUsbPowerActivity.this.OJ.setCurrentState(FaceUsbPowerActivity.this.getString(R.string.state_off));
                        FaceUsbPowerActivity.this.OJ.setControlOnEnableStatus(true);
                        FaceUsbPowerActivity.this.OJ.setControlOffEnableStatus(false);
                        return;
                    default:
                        FaceUsbPowerActivity.this.OJ.setCurrentState(FaceUsbPowerActivity.this.getString(R.string.state_unknown));
                        FaceUsbPowerActivity.this.OJ.setControlOnEnableStatus(false);
                        FaceUsbPowerActivity.this.OJ.setControlOffEnableStatus(false);
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
        setContentView(R.layout.activity_face_usb_power);
        setTarget(getString(R.string.text_face_usb_power));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    void a(McuAckPwrDebug mcuAckPwrDebug) {
        Message obtainMessage = this.mHandler.obtainMessage();
        if (!this.OO) {
            if (mcuAckPwrDebug.uP25V25_SW_EN_RESULT == 0) {
                return;
            }
            obtainMessage.what = 6;
            obtainMessage.arg1 = mcuAckPwrDebug.uP25V25_SW_EN_RESULT;
        } else if (mcuAckPwrDebug.uP2FRUSB_PWR_EN_RESULT == 0) {
            return;
        } else {
            if (this.OM == -3) {
                this.OM = mcuAckPwrDebug.uP2FRUSB_PWR_EN_RESULT;
            }
            obtainMessage.what = 9;
            obtainMessage.arg1 = mcuAckPwrDebug.uP2FRUSB_PWR_EN_RESULT;
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
        if (!this.OO) {
            androidPwrDebug.SWEn5v25 = 1;
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
        androidPwrDebug.frUsbPwr = 1;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.MyPowerView.a
    public void nA() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.frUsbPwr = 2;
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
        a(getString(R.string.text_face_usb_power), true, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_face_usb_power));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        a(getString(R.string.text_face_usb_power), false, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_face_usb_power));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.powertest.BasePowerTestActivity
    public void ny() {
        AndroidPwrDebug androidPwrDebug = new AndroidPwrDebug();
        androidPwrDebug.frUsbPwr = this.OM;
        c.fx().sendFactoryPwrDebugMsgToMcu(androidPwrDebug.packToIntArray());
    }
}
