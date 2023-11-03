package com.xiaopeng.devtools.view.nationalstandard;

import android.car.hardware.CarPropertyValue;
import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.IPNPort;
import com.xiaopeng.devtools.bean.car.TboxCanControlButton;
import com.xiaopeng.devtools.bean.car.TboxCanControlIPMsg;
import com.xiaopeng.devtools.bean.car.TboxCanControlMsg;
import com.xiaopeng.devtools.bean.car.TboxCanControlResponse;
import com.xiaopeng.devtools.model.a.d;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class NationalStandardActivity extends ActionBarActivity implements View.OnClickListener {
    private SparseArray<Button> Hd;
    private Button QZ;
    private Button Ra;
    private Button Rb;
    private Button Rc;
    private Button Rd;
    private Button Re;
    private Button Rf;
    private Button Rg;
    private Button Rh;
    private Button Ri;
    private Button Rj;
    private EditText Rk;
    private EditText Rl;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_nation_detect);
        this.Hd = new SparseArray<>();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Rk = (EditText) findViewById(R.id.et_ip);
        this.Rl = (EditText) findViewById(R.id.et_port);
        this.QZ = (Button) findViewById(R.id.btn_connect);
        this.Ra = (Button) findViewById(R.id.car_login);
        this.Rb = (Button) findViewById(R.id.platform_login);
        this.Rc = (Button) findViewById(R.id.data_collect);
        this.Rd = (Button) findViewById(R.id.data_reissue);
        this.Re = (Button) findViewById(R.id.reserved_1);
        this.Rf = (Button) findViewById(R.id.reserved_2);
        this.Rg = (Button) findViewById(R.id.reserved_3);
        this.Rh = (Button) findViewById(R.id.reserved_4);
        this.Ri = (Button) findViewById(R.id.tbox_offline);
        this.Rj = (Button) findViewById(R.id.alarm_simulate);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Hd.put(1, this.QZ);
        this.QZ.setTag(1);
        this.Hd.put(2, this.Ra);
        this.Ra.setTag(2);
        this.Hd.put(3, this.Rb);
        this.Rb.setTag(3);
        this.Hd.put(4, this.Rc);
        this.Rc.setTag(4);
        this.Hd.put(5, this.Rd);
        this.Rd.setTag(5);
        this.Hd.put(6, this.Re);
        this.Re.setTag(6);
        this.Hd.put(7, this.Rf);
        this.Rf.setTag(7);
        this.Hd.put(8, this.Rg);
        this.Rg.setTag(8);
        this.Hd.put(9, this.Rh);
        this.Rh.setTag(9);
        this.Hd.put(10, this.Ri);
        this.Ri.setTag(10);
        this.Rj.setTag(11);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        EventBus.getDefault().register(this);
        this.QZ.setOnClickListener(this);
        this.Ra.setOnClickListener(this);
        this.Rb.setOnClickListener(this);
        this.Rc.setOnClickListener(this);
        this.Rd.setOnClickListener(this);
        this.Re.setOnClickListener(this);
        this.Rf.setOnClickListener(this);
        this.Rg.setOnClickListener(this);
        this.Rh.setOnClickListener(this);
        this.Ri.setOnClickListener(this);
        this.Rj.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        e(32, "5");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue.getPropertyId() == 554700822) {
            c.f("DesignVerifyActivity", "ID_TBOX_CAN_CONTROLLER");
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
        e(32, "3");
        EventBus.getDefault().unregister(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int intValue = view.getTag() != null ? ((Integer) view.getTag()).intValue() : -1;
        switch (view.getId()) {
            case R.id.alarm_simulate /* 2131296360 */:
                d(AlarmSimulatorActivity.class);
                return;
            case R.id.btn_connect /* 2131296468 */:
                a(intValue, new IPNPort(this.Rk.getText().toString(), this.Rl.getText().toString()));
                return;
            case R.id.car_login /* 2131296591 */:
            case R.id.data_collect /* 2131296659 */:
            case R.id.data_reissue /* 2131296660 */:
            case R.id.platform_login /* 2131296954 */:
            case R.id.reserved_1 /* 2131296989 */:
            case R.id.reserved_2 /* 2131296990 */:
            case R.id.reserved_3 /* 2131296991 */:
            case R.id.reserved_4 /* 2131296992 */:
            case R.id.tbox_offline /* 2131297159 */:
                e(intValue, "1");
                return;
            default:
                return;
        }
    }

    private void a(int i, IPNPort iPNPort) {
        c.f("DesignVerifyActivity", "control key:" + i + ", value:" + iPNPort);
        d.fz().a(new TboxCanControlIPMsg(i, iPNPort));
    }

    private void e(int i, String str) {
        c.f("DesignVerifyActivity", "control key:" + i + ", value:" + str);
        d.fz().a(new TboxCanControlMsg(i, str));
    }

    private void d(Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(335544320);
        startActivity(intent);
    }
}
