package com.xiaopeng.devtools.view.aftersales.cdu;

import android.content.Intent;
import android.view.View;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.ButtonData;
import com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.BrightnessTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.USBTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.BluetoothTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.CameraTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.GPSTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.key.KeyTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd.ScreenSelftestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork.MobileNetWorkTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.PhyTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.record.RecordActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.rgb.RGBActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.SoundTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.touch.TouchTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.WlanTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.XpuImageTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.XpuTestActivity;
import com.xiaopeng.devtools.view.navi.NaviActivity;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class EachModuleDiagnosisActivity extends AfterSalesMenuActivity implements View.OnClickListener {
    private int EY = 0;
    private ArrayList<ButtonData> Fi;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        this.theme = 1;
        this.EY = getIntent().getIntExtra("diagnosis_module", -1);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Fi = new ArrayList<>();
        if (this.EY <= 19) {
            this.Fi.add(new ButtonData(getString(R.string.diagnosis_error_code), DiagnosisRecordActivity.class));
        }
        int i = this.EY;
        if (i == 13) {
            this.Fi.add(new ButtonData(getString(R.string.status_and_action), USBTestActivity.class));
            this.Cq.setCenterText(getString(R.string.diagnosis_module_usb));
        } else if (i == 15) {
            this.Fi.add(new ButtonData(getString(R.string.status_and_action), PhyTestActivity.class));
            this.Cq.setCenterText(getString(R.string.diagnosis_module_phy));
        } else if (i != 93872) {
            switch (i) {
                case 1:
                    this.Fi.add(new ButtonData(getString(R.string.status_and_action_spk), SoundTestActivity.class));
                    this.Fi.add(new ButtonData(getString(R.string.status_and_action_mic), RecordActivity.class));
                    this.Cq.setCenterText(getString(R.string.diagnosis_module_audio));
                    break;
                case 2:
                    this.Fi.add(new ButtonData(getString(R.string.status_and_action), CameraTestActivity.class));
                    this.Cq.setCenterText(getString(R.string.diagnosis_module_camera));
                    break;
                case 3:
                    this.Fi.add(new ButtonData(getString(R.string.status_and_action), BluetoothTestActivity.class));
                    this.Cq.setCenterText(getString(R.string.diagnosis_module_bluetooth));
                    break;
                case 4:
                    this.Fi.add(new ButtonData(getString(R.string.status_and_action), WlanTestActivity.class));
                    this.Cq.setCenterText(getString(R.string.diagnosis_module_wifi));
                    break;
                default:
                    switch (i) {
                        case 8:
                            this.Fi.add(new ButtonData(getString(R.string.status_and_action_touch_panel), TouchTestActivity.class));
                            this.Fi.add(new ButtonData(getString(R.string.status_and_action_back_light), BrightnessTestActivity.class));
                            this.Fi.add(new ButtonData(getString(R.string.status_and_action_rgb), RGBActivity.class));
                            if (a.b.getBoolean("SUPPORT_LCD_SELF_DIAGNOSIS")) {
                                this.Fi.add(new ButtonData(getString(R.string.lcd_self_diagnosis), ScreenSelftestActivity.class));
                            }
                            if (a.b.getBoolean("SUPPORT_TOUCH_KEY")) {
                                this.Fi.add(new ButtonData(getString(R.string.status_and_action_touch_key), KeyTestActivity.class));
                            }
                            this.Cq.setCenterText(getString(R.string.diagnosis_module_lcd));
                            break;
                        case 9:
                            this.Fi.add(new ButtonData(getString(R.string.status_and_action), GPSTestActivity.class));
                            if (a.b.getBoolean("SUPPORT_NAVI_ACTIVATE_UUID")) {
                                this.Fi.add(new ButtonData(getString(R.string.navi_activate), NaviActivity.class));
                            }
                            this.Cq.setCenterText(getString(R.string.diagnosis_module_navi));
                            break;
                        case 10:
                            this.Fi.add(new ButtonData(getString(R.string.status_and_action), MobileNetWorkTestActivity.class));
                            this.Cq.setCenterText(getString(R.string.diagnosis_module_4g));
                            break;
                        case 11:
                            this.Fi.add(new ButtonData(getString(R.string.status_and_action), StorageMemoryActivity.class));
                            this.Cq.setCenterText(getString(R.string.diagnosis_module_ufs));
                            break;
                    }
            }
        } else {
            if (a.b.getBoolean("XPU_LVDS")) {
                this.Fi.add(new ButtonData(getString(R.string.status_and_action), XpuTestActivity.class));
            } else {
                this.Fi.add(new ButtonData(getString(R.string.status_and_action), XpuImageTestActivity.class));
            }
            this.Cq.setCenterText(getString(R.string.diagnosis_module_xpu));
        }
        b(this.Fi);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() > this.Fi.size()) {
            super.onClick(view);
            return;
        }
        ButtonData buttonData = this.Fi.get(view.getId() - 1);
        if (buttonData != null) {
            if (this.EY > 19) {
                a(buttonData.getTriggerClass(), this.theme);
            } else if (view.getId() == 1) {
                Intent intent = new Intent(this, buttonData.getTriggerClass());
                intent.putExtra("diagnosis_module", this.EY);
                b(intent, this.theme);
            } else {
                a(buttonData.getTriggerClass(), this.theme);
            }
        }
    }
}
