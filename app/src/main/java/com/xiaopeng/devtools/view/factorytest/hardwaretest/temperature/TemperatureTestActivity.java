package com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.McuTempInfo;
import com.xiaopeng.devtools.bean.car.TboxTempInfo;
import com.xiaopeng.devtools.bean.factorytest.AndTempInfo;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class TemperatureTestActivity extends AsOrFacBaseActivity implements a {
    private static final Integer[] LV = {1, 5, 10, 15, 20};
    private Spinner LW;
    private TextView LX;
    private TextView LY;
    private TextView LZ;
    private TextView Ma;
    private TextView Mb;
    private TextView Mc;
    private TextView Md;
    private TextView Me;
    private TextView Mf;
    private TextView Mg;
    private TextView Mh;
    private LinearLayout Mi;
    private LinearLayout Mj;
    private LinearLayout Mk;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.k.a Ml;

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_temp_test);
        setTarget(getString(R.string.test_temperature));
        this.Ml = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.k.a(this);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.LX = (TextView) findViewById(R.id.temp_cpu_inter_value);
        this.LY = (TextView) findViewById(R.id.temp_cpu_outter_value);
        this.LZ = (TextView) findViewById(R.id.temp_ddr_value);
        this.Ma = (TextView) findViewById(R.id.temp_ufs_value);
        this.Mb = (TextView) findViewById(R.id.temp_amp_value);
        this.Mc = (TextView) findViewById(R.id.temp_pmic_value);
        this.Md = (TextView) findViewById(R.id.temp_tbox_value);
        this.Me = (TextView) findViewById(R.id.temp_tmcu_value);
        this.Mf = (TextView) findViewById(R.id.temp_batt_value);
        this.Mg = (TextView) findViewById(R.id.temp_amcu_value);
        this.Mh = (TextView) findViewById(R.id.temp_pcb_value);
        this.LW = (Spinner) findViewById(R.id.temp_update_interval_value);
        this.Mi = (LinearLayout) findViewById(R.id.temp_tbox);
        this.Mj = (LinearLayout) findViewById(R.id.temp_tmcu);
        this.Mk = (LinearLayout) findViewById(R.id.temp_pcb);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.LW.setAdapter((SpinnerAdapter) new ArrayAdapter(this, 17367048, LV));
        if (!a.b.getBoolean("SUPPORT_PCB_TEMP")) {
            this.Mk.setVisibility(8);
        }
        if (!a.b.getBoolean("SUPPORT_TMCU")) {
            this.Mi.setVisibility(8);
            this.Mj.setVisibility(8);
        }
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.LW.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.TemperatureTestActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                TemperatureTestActivity.this.Ml.cq(TemperatureTestActivity.LV[i].intValue());
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.Ml.jv();
        if (this.theme == 1) {
            recordRepairModeAction("temperature diagnosis", "triggered");
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Ml.destroy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        super.mv();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_temp));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        super.mw();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_temp));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.a
    public void a(final TboxTempInfo tboxTempInfo) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.-$$Lambda$TemperatureTestActivity$nblUIU0zl-_yMH9h9IA0dKmbGlw
            @Override // java.lang.Runnable
            public final void run() {
                TemperatureTestActivity.this.b(tboxTempInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(TboxTempInfo tboxTempInfo) {
        this.Md.setText(String.valueOf(tboxTempInfo.getFloatTbox()));
        this.Me.setText(String.valueOf(tboxTempInfo.getFloatTmcu()));
        this.Mf.setText(String.valueOf(tboxTempInfo.getFloatBbat()));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.a
    public void a(final AndTempInfo andTempInfo) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.-$$Lambda$TemperatureTestActivity$JZG6qimO3WnOhtpTCkGLgcMlITE
            @Override // java.lang.Runnable
            public final void run() {
                TemperatureTestActivity.this.b(andTempInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AndTempInfo andTempInfo) {
        this.LX.setText(String.valueOf(andTempInfo.getCpuTemp()));
        this.LY.setText(String.valueOf(andTempInfo.getSocTemp()));
        this.Ma.setText(String.valueOf(andTempInfo.getUfsTemp()));
        this.LZ.setText(String.valueOf(andTempInfo.getDdrTemp()));
        this.Mc.setText(String.valueOf(andTempInfo.getPmicTemp()));
        this.Mb.setText(String.valueOf(andTempInfo.getAmpTemp()));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.a
    public void a(final McuTempInfo mcuTempInfo) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.-$$Lambda$TemperatureTestActivity$AnEyGq1qhawHXEZ5syb1uQjznMg
            @Override // java.lang.Runnable
            public final void run() {
                TemperatureTestActivity.this.b(mcuTempInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(McuTempInfo mcuTempInfo) {
        this.Mg.setText(String.valueOf(mcuTempInfo.getMcuTemp()));
        if (a.b.getBoolean("SUPPORT_PCB_TEMP")) {
            this.Mh.setText(String.valueOf(mcuTempInfo.getPcbTemp()));
        }
        if (!a.b.getBoolean("SUPPORT_TMCU")) {
            this.Mf.setText(String.valueOf(mcuTempInfo.getBattTemp()));
        }
    }
}
