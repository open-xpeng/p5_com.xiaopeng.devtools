package com.xiaopeng.devtools.view.factorytest.hardwaretest;

import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.clean.ClearDataActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.battery.BatteryTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.BluetoothTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.CameraTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.GPSTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd.ScreenSelftestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork.MobileNetWorkTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.psu.PsuTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.RadioActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.record.RecordActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.SoundTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.TemperatureTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.WlanTestActivity;
import com.xiaopeng.xui.app.g;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class DesignVerifyActivity extends ActionBarActivity implements View.OnClickListener {
    private d Ep;
    private Button GE;
    private Button GF;
    private Button GG;
    private Button GH;
    private Button GI;
    private Button GJ;
    private Button GK;
    private Button GL;
    private Button GM;
    private Button GN;
    private Button GO;
    private Button GP;
    private Button GQ;
    private Button GR;
    private Button GS;
    private Button GU;
    private Button GV;
    private Button GW;
    private Button GX;
    private Button GY;
    private Button GZ;
    private Button Ha;
    private Button Hb;
    private Button Hc;
    private SparseArray<Button> Hd;
    private boolean He;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        Button button = this.Hd.get(num.intValue());
        if (button != null) {
            button.setBackgroundResource(R.drawable.btn_test_background);
        }
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_design_verify);
        this.Hd = new SparseArray<>();
        this.Ep = new b();
        this.Ep.fn();
        this.Ep.iP();
        this.Ep.iR();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.GE = (Button) findViewById(R.id.btn_test_usb);
        this.GF = (Button) findViewById(R.id.btn_test_bt);
        this.GH = (Button) findViewById(R.id.btn_test_report);
        this.GG = (Button) findViewById(R.id.btn_test_wifi);
        this.GI = (Button) findViewById(R.id.btn_test_camera);
        this.GJ = (Button) findViewById(R.id.btn_test_4g);
        this.GK = (Button) findViewById(R.id.btn_test_back_light);
        this.GL = (Button) findViewById(R.id.btn_test_radio);
        this.GM = (Button) findViewById(R.id.btn_test_quiet);
        this.GN = (Button) findViewById(R.id.btn_test_face);
        this.GO = (Button) findViewById(R.id.btn_test_touch_screen);
        this.GP = (Button) findViewById(R.id.btn_test_rgb);
        this.GQ = (Button) findViewById(R.id.btn_test_record);
        this.GR = (Button) findViewById(R.id.btn_test_sound);
        this.GS = (Button) findViewById(R.id.btn_test_sdcard);
        this.GU = (Button) findViewById(R.id.btn_test_gps);
        this.GV = (Button) findViewById(R.id.btn_test_eeprom);
        this.GW = (Button) findViewById(R.id.btn_test_can);
        this.GX = (Button) findViewById(R.id.btn_test_sensor);
        this.GY = (Button) findViewById(R.id.btn_test_battery);
        this.GZ = (Button) findViewById(R.id.btn_test_psu);
        this.Ha = (Button) findViewById(R.id.btn_test_blower_power);
        this.Hb = (Button) findViewById(R.id.btn_test_temp);
        this.Hc = (Button) findViewById(R.id.btn_screen_selftest);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (!a.C0041a.dX("TEST_RADIO")) {
            this.GL.setVisibility(8);
        }
        if (!a.C0041a.dX("SREEN_SELTEST")) {
            this.Hc.setVisibility(8);
        }
        this.Hd.put(R.id.btn_test_usb, this.GE);
        this.Hd.put(R.id.btn_test_bt, this.GF);
        this.Hd.put(R.id.btn_test_wifi, this.GG);
        this.Hd.put(R.id.btn_test_camera, this.GI);
        this.Hd.put(R.id.btn_test_4g, this.GJ);
        this.Hd.put(R.id.btn_test_back_light, this.GK);
        this.Hd.put(R.id.btn_test_radio, this.GL);
        this.Hd.put(R.id.btn_test_quiet, this.GM);
        this.Hd.put(R.id.btn_test_touch_screen, this.GO);
        this.Hd.put(R.id.btn_test_rgb, this.GP);
        this.Hd.put(R.id.btn_test_record, this.GQ);
        this.Hd.put(R.id.btn_test_sound, this.GR);
        this.Hd.put(R.id.btn_test_sdcard, this.GS);
        this.Hd.put(R.id.btn_test_gps, this.GU);
        this.Hd.put(R.id.btn_test_eeprom, this.GV);
        this.Hd.put(R.id.btn_test_can, this.GW);
        this.Hd.put(R.id.btn_test_sensor, this.GX);
        this.Hd.put(R.id.btn_test_blower_power, this.Ha);
        this.Hd.put(R.id.btn_test_battery, this.GY);
        this.Hd.put(R.id.btn_test_psu, this.GZ);
        this.Hd.put(R.id.btn_test_temp, this.Hb);
        this.Hd.put(R.id.btn_screen_selftest, this.Hc);
        EventBus.getDefault().register(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.GE.setOnClickListener(this);
        this.GF.setOnClickListener(this);
        this.GH.setOnClickListener(this);
        this.GG.setOnClickListener(this);
        this.GI.setOnClickListener(this);
        this.GJ.setOnClickListener(this);
        this.GK.setOnClickListener(this);
        this.GL.setOnClickListener(this);
        this.GM.setOnClickListener(this);
        this.GO.setOnClickListener(this);
        this.GP.setOnClickListener(this);
        this.GQ.setOnClickListener(this);
        this.GR.setOnClickListener(this);
        this.GS.setOnClickListener(this);
        this.GU.setOnClickListener(this);
        this.GV.setOnClickListener(this);
        this.GW.setOnClickListener(this);
        this.GX.setOnClickListener(this);
        this.Ha.setOnClickListener(this);
        this.GY.setOnClickListener(this);
        this.GZ.setOnClickListener(this);
        this.Hb.setOnClickListener(this);
        this.Hc.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (!this.He) {
            this.Ep.bt("xp_hardware_report_");
            g.show(R.string.generate_report_auto);
        }
        this.Ep.iQ();
        this.Ep.iS();
        EventBus.getDefault().unregister(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recovery /* 2131296497 */:
                d(ClearDataActivity.class);
                return;
            case R.id.btn_screen_selftest /* 2131296505 */:
                d(ScreenSelftestActivity.class);
                return;
            case R.id.btn_test_4g /* 2131296514 */:
                d(MobileNetWorkTestActivity.class);
                return;
            case R.id.btn_test_back_light /* 2131296519 */:
                d(BrightnessTestActivity.class);
                return;
            case R.id.btn_test_battery /* 2131296521 */:
                d(BatteryTestActivity.class);
                return;
            case R.id.btn_test_blower_power /* 2131296522 */:
            case R.id.btn_test_can /* 2131296527 */:
            case R.id.btn_test_eeprom /* 2131296534 */:
            case R.id.btn_test_face /* 2131296537 */:
            case R.id.btn_test_quiet /* 2131296552 */:
            case R.id.btn_test_rgb /* 2131296558 */:
            case R.id.btn_test_sdcard /* 2131296560 */:
            case R.id.btn_test_sensor /* 2131296561 */:
            case R.id.btn_test_touch_screen /* 2131296569 */:
            default:
                return;
            case R.id.btn_test_bt /* 2131296524 */:
                d(BluetoothTestActivity.class);
                return;
            case R.id.btn_test_camera /* 2131296526 */:
                d(CameraTestActivity.class);
                return;
            case R.id.btn_test_gps /* 2131296540 */:
                d(GPSTestActivity.class);
                return;
            case R.id.btn_test_psu /* 2131296551 */:
                d(PsuTestActivity.class);
                return;
            case R.id.btn_test_radio /* 2131296553 */:
                d(RadioActivity.class);
                return;
            case R.id.btn_test_record /* 2131296556 */:
                d(RecordActivity.class);
                return;
            case R.id.btn_test_report /* 2131296557 */:
                d(ShowReportActivity.class);
                this.Ep.bt("xp_hardware_report_");
                this.He = true;
                g.show(R.string.generate_report);
                return;
            case R.id.btn_test_sound /* 2131296563 */:
                d(SoundTestActivity.class);
                return;
            case R.id.btn_test_temp /* 2131296568 */:
                d(TemperatureTestActivity.class);
                return;
            case R.id.btn_test_usb /* 2131296570 */:
                d(USBTestActivity.class);
                return;
            case R.id.btn_test_wifi /* 2131296571 */:
                d(WlanTestActivity.class);
                return;
        }
    }

    private void d(Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(335544320);
        startActivity(intent);
    }
}
