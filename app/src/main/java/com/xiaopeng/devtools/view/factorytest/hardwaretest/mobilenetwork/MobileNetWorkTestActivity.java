package com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.TboxApn;
import com.xiaopeng.devtools.bean.event.CommonEvent;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.lib.utils.c;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class MobileNetWorkTestActivity extends AsOrFacBaseActivity implements a {
    private TextView It;
    private TextView KA;
    private TextView KC;
    private TextView KD;
    private TextView KE;
    private TextView KF;
    private EditText KG;
    private f Kv;
    private Button Kw;
    private Button Kx;
    private TextView Ky;
    private TextView Kz;
    private String info;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork.MobileNetWorkTestActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 200001) {
                MobileNetWorkTestActivity.this.info = (String) message.obj;
                if (MobileNetWorkTestActivity.this.KF == null || TextUtils.isEmpty(MobileNetWorkTestActivity.this.info)) {
                    MobileNetWorkTestActivity.this.KF.setText(R.string.network_connect_fail);
                    MobileNetWorkTestActivity.this.a(MobileNetWorkTestActivity.this.It, false);
                    MobileNetWorkTestActivity.this.a(MobileNetWorkTestActivity.this.getString(R.string.network_connect_test), false, "");
                    return;
                }
                MobileNetWorkTestActivity.this.KF.setText(MobileNetWorkTestActivity.this.info);
                MobileNetWorkTestActivity.this.a(MobileNetWorkTestActivity.this.It, true);
                MobileNetWorkTestActivity.this.a(MobileNetWorkTestActivity.this.getString(R.string.network_connect_test), true, "");
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CommonEvent commonEvent) {
        if (commonEvent.getState() == 10001) {
            String msg = commonEvent.getMsg();
            c.f("MobileNetWork", "HTTP_PING_EVENT : " + msg);
            if (msg != null) {
                this.KF.setText(msg);
                if (msg.toLowerCase().indexOf("ttl") > 0) {
                    a(this.KD, true);
                    a(getString(R.string.network_ping_test), true, "");
                    return;
                }
                return;
            }
            mP();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_mobile_network_test);
        this.Kv = new i(this.mHandler, this);
        this.Kv.onInit();
        setTarget(getString(R.string.test_4g));
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Ky = (TextView) findViewById(R.id.network_sim_status_result);
        this.Kz = (TextView) findViewById(R.id.network_sim_signal_result);
        this.KA = (TextView) findViewById(R.id.network_4g_module_result);
        this.Kw = (Button) findViewById(R.id.network_get_connect_bt);
        this.Kx = (Button) findViewById(R.id.network_ping_bt);
        this.It = (TextView) findViewById(R.id.result_connect_tv);
        this.KD = (TextView) findViewById(R.id.result_ping_tv);
        this.KE = (TextView) findViewById(R.id.result_ping_tbox_tv);
        this.KF = (TextView) findViewById(R.id.network_connect_info_tv);
        this.KF.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.KC = (TextView) findViewById(R.id.network_sim_id_result);
        this.KG = (EditText) findViewById(R.id.et_ping_packagesize);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        boolean fV = this.Kv.fV();
        a(this.KA, fV);
        a(getString(R.string.network_4g_module_test), fV, "");
        String iccid = r.getIccid();
        TextView textView = this.KC;
        if (TextUtils.isEmpty(iccid)) {
            iccid = getString(R.string.network_no_sim_id);
        }
        textView.setText(iccid);
        a(this.KD, false);
        a(getString(R.string.network_ping_test), false, "");
        mP();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Kw.setOnClickListener(this);
        this.Kx.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.Kv.getSimStatusAsync();
        this.Kv.fU();
        this.Kv.iT();
        if (this.theme == 1) {
            recordRepairModeAction("lte diagnosis", "triggered");
        }
    }

    public void onResult(View view) {
        view.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(TextView textView, boolean z) {
        if (z) {
            textView.setText(R.string.test_succeed);
            textView.setTextColor(getResources().getColor(17170450));
            return;
        }
        textView.setText(R.string.test_fail);
        textView.setTextColor(getResources().getColor(17170454));
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.network_get_connect_bt) {
            this.Kv.fU();
        } else if (id == R.id.network_ping_bt) {
            String charSequence = this.Kx.getText().toString();
            if (getString(R.string.test).equals(charSequence)) {
                this.Kx.setText(R.string.testing);
                String obj = this.KG.getText().toString();
                this.Kv.p("47.99.202.252", Integer.parseInt((obj == null || obj.length() == 0) ? "8" : "8"));
            } else if (getString(R.string.testing).equals(charSequence)) {
                this.Kv.bu("47.99.202.252");
                this.Kx.setText(R.string.test);
            }
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_4g));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_4g));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.Kv.fT();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork.a
    public void a(TboxApn tboxApn) {
        this.Kz.setText(String.valueOf(tboxApn.getRssi()));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork.a
    public void cm(int i) {
        if (i != 6) {
            switch (i) {
                case 0:
                    break;
                case 1:
                    this.Ky.setText(R.string.sim_ready);
                    return;
                default:
                    return;
            }
        }
        this.Ky.setText(R.string.no_sim);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ax(boolean z) {
        a(this.KE, z);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork.a
    public void aw(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.mobilenetwork.-$$Lambda$MobileNetWorkTestActivity$VvRdrHbb1vGxzXrz3vI9hobfDhs
            @Override // java.lang.Runnable
            public final void run() {
                MobileNetWorkTestActivity.this.ax(z);
            }
        });
    }

    private void mP() {
        if (this.Kv.bv("47.99.202.252")) {
            this.Kx.setText(R.string.testing);
        } else {
            this.Kx.setText(R.string.test);
        }
    }
}
