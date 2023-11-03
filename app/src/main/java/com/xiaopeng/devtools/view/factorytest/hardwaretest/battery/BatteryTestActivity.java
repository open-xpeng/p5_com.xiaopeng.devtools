package com.xiaopeng.devtools.view.factorytest.hardwaretest.battery;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.BatteryInfo;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.a.b;
import com.xiaopeng.devtools.view.ToolBarActivity;
import com.xiaopeng.lib.utils.c;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class BatteryTestActivity extends ToolBarActivity implements View.OnClickListener, a {
    private Button HY;
    private Button HZ;
    private TextView Ia;
    private TextView Ib;
    private TextView Ic;
    private TextView Id;
    private TextView Ie;
    private TextView If;
    private TextView Ig;
    private TextView Ih;
    private TextView Ii;
    private TextView Ij;
    private b Ik;

    private void init() {
        this.Ik = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.a.a(this);
        this.Ik.init();
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_battery_test);
        init();
        setTarget(getString(R.string.test_battery));
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Ia = (TextView) findViewById(R.id.batterychargemodevalue);
        this.Ib = (TextView) findViewById(R.id.batteryvoltagevalue);
        this.Ic = (TextView) findViewById(R.id.batterysocvalue);
        this.Id = (TextView) findViewById(R.id.dc_impedancevalue);
        this.Ie = (TextView) findViewById(R.id.batterysohvalue);
        this.If = (TextView) findViewById(R.id.cell_connectvalue);
        this.Ig = (TextView) findViewById(R.id.stepup_statevalue);
        this.Ih = (TextView) findViewById(R.id.stepup_voltvalue);
        this.Ii = (TextView) findViewById(R.id.indischargingvalue);
        this.Ij = (TextView) findViewById(R.id.discharge_currentvalue);
        this.HY = (Button) findViewById(R.id.btn_test_stop_discharge);
        this.HZ = (Button) findViewById(R.id.btn_test_enable_discharge);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.HY.setOnClickListener(this);
        this.HZ.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.Ik.release();
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_test_enable_discharge) {
            this.Ik.gj();
        } else if (id == R.id.btn_test_stop_discharge) {
            this.Ik.gi();
        } else {
            super.onClick(view);
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        super.mv();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_battery));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        super.mw();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_battery));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.battery.a
    public void a(BatteryInfo batteryInfo) {
        this.Ia.setText(cQ(batteryInfo.getIncharging()));
        this.Ib.setText(getString(R.string.value_format, new Object[]{Integer.valueOf(batteryInfo.getVoltage())}));
        this.Ic.setText(getString(R.string.value_format, new Object[]{Integer.valueOf(batteryInfo.getSoc())}));
        this.Id.setText(getString(R.string.value_format, new Object[]{Integer.valueOf(batteryInfo.getDc_impedance())}));
        this.Ie.setText(getString(R.string.value_format, new Object[]{Integer.valueOf(batteryInfo.getSoh())}));
        this.If.setText(cR(batteryInfo.getCell_connect()));
        this.Ig.setText(cS(batteryInfo.getStepup_state()));
        this.Ih.setText(getString(R.string.value_format, new Object[]{Integer.valueOf(batteryInfo.getStepup_volt())}));
        this.Ii.setText(cT(batteryInfo.getIndischarging()));
        this.Ij.setText(getString(R.string.value_format, new Object[]{Integer.valueOf(batteryInfo.getDischarge_current())}));
    }

    private String cQ(int i) {
        c.f("BatteryTestActivity", "chargeMode: " + i);
        String string = getString(R.string.battery_charge_mode_unknown);
        switch (i) {
            case 0:
                return getString(R.string.battery_charge_mode_0);
            case 1:
                return getString(R.string.battery_charge_mode_1);
            default:
                return string;
        }
    }

    private String cR(int i) {
        c.f("BatteryTestActivity", "getCellConnectDesc: " + i);
        String string = getString(R.string.battery_charge_mode_unknown);
        switch (i) {
            case 0:
                return getString(R.string.battery_cell_disconnected);
            case 1:
                return getString(R.string.battery_cell_connected);
            default:
                return string;
        }
    }

    private String cS(int i) {
        c.f("BatteryTestActivity", "getStepupStateDesc: " + i);
        String string = getString(R.string.battery_charge_mode_unknown);
        switch (i) {
            case 0:
                return getString(R.string.battery_not_stepup);
            case 1:
                return getString(R.string.battery_stepup);
            default:
                return string;
        }
    }

    private String cT(int i) {
        c.f("BatteryTestActivity", "getDischargingStateDesc: " + i);
        String string = getString(R.string.battery_charge_mode_unknown);
        switch (i) {
            case 0:
                return getString(R.string.battery_not_discharging);
            case 1:
                return getString(R.string.battery_discharging);
            default:
                return string;
        }
    }
}
