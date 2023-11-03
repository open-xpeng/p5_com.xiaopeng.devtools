package com.xiaopeng.devtools.view.nationalstandard;

import android.car.hardware.CarPropertyValue;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.TboxCanControlButton;
import com.xiaopeng.devtools.bean.car.TboxCanControlMsg;
import com.xiaopeng.devtools.bean.car.TboxCanControlResponse;
import com.xiaopeng.devtools.model.a.d;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class AlarmSimulatorActivity extends ActionBarActivity implements View.OnClickListener {
    private SparseArray<Button> Hd;
    private Button QE;
    private Button QF;
    private Button QG;
    private Button QH;
    private Button QI;
    private Button QJ;
    private Button QK;
    private Button QL;
    private Button QM;
    private Button QN;
    private Button QO;
    private Button QP;
    private Button QQ;
    private Button QR;
    private Button QS;
    private Button QT;
    private Button QU;
    private Button QV;
    private Button QW;
    private Button QX;
    private Button QY;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_alarm_simulator);
        this.Hd = new SparseArray<>();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.QE = (Button) findViewById(R.id.alarm_temp_diff);
        this.QF = (Button) findViewById(R.id.alarm_batt_high_temp);
        this.QG = (Button) findViewById(R.id.alarm_energy_high_pressure);
        this.QH = (Button) findViewById(R.id.alarm_energy_low_pressure);
        this.QI = (Button) findViewById(R.id.alarm_low_soc);
        this.QJ = (Button) findViewById(R.id.alarm_batt_high_pressure);
        this.QK = (Button) findViewById(R.id.alarm_batt_low_pressure);
        this.QL = (Button) findViewById(R.id.alarm_high_soc);
        this.QM = (Button) findViewById(R.id.alarm_jump_soc);
        this.QN = (Button) findViewById(R.id.alarm_energy_system_mismatch);
        this.QO = (Button) findViewById(R.id.alarm_batt_bad_consistency);
        this.QP = (Button) findViewById(R.id.alarm_insulation);
        this.QQ = (Button) findViewById(R.id.alarm_dcdc_temp);
        this.QR = (Button) findViewById(R.id.alarm_brake_system);
        this.QS = (Button) findViewById(R.id.alarm_dcdc_status);
        this.QT = (Button) findViewById(R.id.alarm_motor_brake_temp);
        this.QU = (Button) findViewById(R.id.alarm_high_pressure_interlock);
        this.QV = (Button) findViewById(R.id.alarm_motor_temp);
        this.QW = (Button) findViewById(R.id.alarm_energy_storage_over_charge);
        this.QX = (Button) findViewById(R.id.alarm_above_all);
        this.QY = (Button) findViewById(R.id.return_main);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Hd.put(12, this.QE);
        this.QE.setTag(12);
        this.Hd.put(13, this.QF);
        this.QF.setTag(13);
        this.Hd.put(14, this.QG);
        this.QG.setTag(14);
        this.Hd.put(15, this.QH);
        this.QH.setTag(15);
        this.Hd.put(16, this.QI);
        this.QI.setTag(16);
        this.Hd.put(17, this.QJ);
        this.QJ.setTag(17);
        this.Hd.put(18, this.QK);
        this.QK.setTag(18);
        this.Hd.put(19, this.QL);
        this.QL.setTag(19);
        this.Hd.put(20, this.QM);
        this.QM.setTag(20);
        this.Hd.put(21, this.QN);
        this.QN.setTag(21);
        this.Hd.put(22, this.QO);
        this.QO.setTag(22);
        this.Hd.put(23, this.QP);
        this.QP.setTag(23);
        this.Hd.put(24, this.QQ);
        this.QQ.setTag(24);
        this.Hd.put(25, this.QR);
        this.QR.setTag(25);
        this.Hd.put(26, this.QS);
        this.QS.setTag(26);
        this.Hd.put(27, this.QT);
        this.QT.setTag(27);
        this.Hd.put(28, this.QU);
        this.QU.setTag(28);
        this.Hd.put(29, this.QV);
        this.QV.setTag(29);
        this.Hd.put(30, this.QW);
        this.QW.setTag(30);
        this.Hd.put(31, this.QX);
        this.QX.setTag(31);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        EventBus.getDefault().register(this);
        this.QE.setOnClickListener(this);
        this.QF.setOnClickListener(this);
        this.QG.setOnClickListener(this);
        this.QH.setOnClickListener(this);
        this.QI.setOnClickListener(this);
        this.QJ.setOnClickListener(this);
        this.QK.setOnClickListener(this);
        this.QL.setOnClickListener(this);
        this.QM.setOnClickListener(this);
        this.QN.setOnClickListener(this);
        this.QO.setOnClickListener(this);
        this.QP.setOnClickListener(this);
        this.QQ.setOnClickListener(this);
        this.QR.setOnClickListener(this);
        this.QS.setOnClickListener(this);
        this.QT.setOnClickListener(this);
        this.QU.setOnClickListener(this);
        this.QV.setOnClickListener(this);
        this.QW.setOnClickListener(this);
        this.QX.setOnClickListener(this);
        this.QY.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        e(11, "4");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue.getPropertyId() == 554700822) {
            c.f("AlarmSimulatorActivity", "ID_TBOX_CAN_CONTROLLER");
            for (TboxCanControlButton tboxCanControlButton : ((TboxCanControlResponse) new Gson().fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxCanControlResponse.class)).getButton()) {
                Button button = this.Hd.get(tboxCanControlButton.getKey());
                if (button != null) {
                    switch (tboxCanControlButton.getColor()) {
                        case 1:
                            button.setBackgroundResource(R.drawable.btn_red_background);
                            break;
                        case 2:
                            button.setBackgroundResource(R.drawable.btn_green_background);
                            break;
                        case 3:
                            button.setBackgroundResource(R.drawable.btn_orange_background);
                            break;
                        default:
                            button.setBackgroundResource(R.drawable.btn_background);
                            break;
                    }
                    button.setText(tboxCanControlButton.getLabel());
                }
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int intValue = view.getTag() != null ? ((Integer) view.getTag()).intValue() : -1;
        int id = view.getId();
        if (id != R.id.alarm_temp_diff) {
            if (id == R.id.return_main) {
                finish();
                return;
            }
            switch (id) {
                case R.id.alarm_above_all /* 2131296341 */:
                case R.id.alarm_batt_bad_consistency /* 2131296342 */:
                case R.id.alarm_batt_high_pressure /* 2131296343 */:
                case R.id.alarm_batt_high_temp /* 2131296344 */:
                case R.id.alarm_batt_low_pressure /* 2131296345 */:
                case R.id.alarm_brake_system /* 2131296346 */:
                case R.id.alarm_dcdc_status /* 2131296347 */:
                case R.id.alarm_dcdc_temp /* 2131296348 */:
                case R.id.alarm_energy_high_pressure /* 2131296349 */:
                case R.id.alarm_energy_low_pressure /* 2131296350 */:
                case R.id.alarm_energy_storage_over_charge /* 2131296351 */:
                case R.id.alarm_energy_system_mismatch /* 2131296352 */:
                case R.id.alarm_high_pressure_interlock /* 2131296353 */:
                case R.id.alarm_high_soc /* 2131296354 */:
                case R.id.alarm_insulation /* 2131296355 */:
                case R.id.alarm_jump_soc /* 2131296356 */:
                case R.id.alarm_low_soc /* 2131296357 */:
                case R.id.alarm_motor_brake_temp /* 2131296358 */:
                case R.id.alarm_motor_temp /* 2131296359 */:
                    break;
                default:
                    return;
            }
        }
        e(intValue, "1");
    }

    private void e(int i, String str) {
        c.f("AlarmSimulatorActivity", "control key:" + i + ", value:" + str);
        d.fz().a(new TboxCanControlMsg(i, str));
    }
}
