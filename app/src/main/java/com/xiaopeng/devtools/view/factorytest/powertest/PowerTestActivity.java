package com.xiaopeng.devtools.view.factorytest.powertest;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.ShowReportActivity;
import com.xiaopeng.xui.app.g;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class PowerTestActivity extends ActionBarActivity implements View.OnClickListener {
    private d Ep;
    private SparseArray<Button> Hd;
    private boolean He;
    private Button Ph;
    private Button Pi;
    private Button Pj;
    private Button Pk;
    private Button Pl;
    private Button Pm;
    private Button Pn;
    private Button Po;
    private Button Pp;
    private Button Pq;
    private Button Pr;
    private Button Ps;
    private Button Pt;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        Button button = this.Hd.get(num.intValue());
        if (button != null) {
            button.setBackgroundResource(R.drawable.btn_test_background);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_power_test);
        this.Hd = new SparseArray<>();
        this.Ep = new b();
        this.Ep.fn();
        this.Ep.iM();
        this.Ep.iP();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Ph = (Button) findViewById(R.id.btn_test_bright_power);
        this.Pi = (Button) findViewById(R.id.btn_test_gps_power);
        this.Pj = (Button) findViewById(R.id.btn_test_gps_wire_power);
        this.Pk = (Button) findViewById(R.id.btn_test_4g_power);
        this.Pl = (Button) findViewById(R.id.btn_test_4g_module_power);
        this.Pm = (Button) findViewById(R.id.btn_test_sw_power);
        this.Pp = (Button) findViewById(R.id.btn_test_dvr_power);
        this.Pn = (Button) findViewById(R.id.btn_test_media_usb_power);
        this.Po = (Button) findViewById(R.id.btn_test_face_usb_power);
        this.Pq = (Button) findViewById(R.id.btn_test_radio_power);
        this.Ps = (Button) findViewById(R.id.btn_test_blower_power);
        this.Pr = (Button) findViewById(R.id.btn_test_mic_power);
        this.Pt = (Button) findViewById(R.id.btn_test_report);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Hd.put(R.id.btn_test_bright_power, this.Ph);
        this.Hd.put(R.id.btn_test_gps_power, this.Pi);
        this.Hd.put(R.id.btn_test_gps_wire_power, this.Pj);
        this.Hd.put(R.id.btn_test_4g_power, this.Pk);
        this.Hd.put(R.id.btn_test_4g_module_power, this.Pl);
        this.Hd.put(R.id.btn_test_sw_power, this.Pm);
        this.Hd.put(R.id.btn_test_dvr_power, this.Pp);
        this.Hd.put(R.id.btn_test_media_usb_power, this.Pn);
        this.Hd.put(R.id.btn_test_face_usb_power, this.Po);
        this.Hd.put(R.id.btn_test_radio_power, this.Pq);
        this.Hd.put(R.id.btn_test_blower_power, this.Ps);
        this.Hd.put(R.id.btn_test_mic_power, this.Pr);
        EventBus.getDefault().register(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Ph.setOnClickListener(this);
        this.Pi.setOnClickListener(this);
        this.Pj.setOnClickListener(this);
        this.Pk.setOnClickListener(this);
        this.Pl.setOnClickListener(this);
        this.Pm.setOnClickListener(this);
        this.Pp.setOnClickListener(this);
        this.Pn.setOnClickListener(this);
        this.Po.setOnClickListener(this);
        this.Pq.setOnClickListener(this);
        this.Ps.setOnClickListener(this);
        this.Pr.setOnClickListener(this);
        this.Pt.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test_4g_module_power /* 2131296515 */:
                d(FourGModulePowerActivity.class);
                return;
            case R.id.btn_test_4g_power /* 2131296516 */:
                d(FourGPowerActivity.class);
                return;
            case R.id.btn_test_blower_power /* 2131296522 */:
                d(BlowerPowerActivity.class);
                return;
            case R.id.btn_test_bright_power /* 2131296523 */:
                d(BrightPowerActivity.class);
                return;
            case R.id.btn_test_dvr_power /* 2131296533 */:
                d(DvrPowerActivity.class);
                return;
            case R.id.btn_test_face_usb_power /* 2131296538 */:
                d(FaceUsbPowerActivity.class);
                return;
            case R.id.btn_test_gps_power /* 2131296541 */:
                d(GpsPowerActivity.class);
                return;
            case R.id.btn_test_gps_wire_power /* 2131296542 */:
                d(GPSWirePowerActivity.class);
                return;
            case R.id.btn_test_media_usb_power /* 2131296544 */:
                d(MediaUsbPowerActivity.class);
                return;
            case R.id.btn_test_mic_power /* 2131296545 */:
                d(MicPowerActivity.class);
                return;
            case R.id.btn_test_radio_power /* 2131296554 */:
                d(RadioPowerActivity.class);
                return;
            case R.id.btn_test_report /* 2131296557 */:
                d(ShowReportActivity.class);
                this.Ep.bt("xp_power_report_");
                this.He = true;
                g.show(R.string.generate_report);
                return;
            case R.id.btn_test_sw_power /* 2131296567 */:
                d(SWPowerActivity.class);
                return;
            default:
                return;
        }
    }

    private void d(Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(335544320);
        startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Ep.iQ();
        if (!this.He) {
            this.Ep.bt("xp_power_report_");
            g.show(R.string.generate_report_auto);
        }
        this.Ep.iO();
        EventBus.getDefault().unregister(this);
    }
}
