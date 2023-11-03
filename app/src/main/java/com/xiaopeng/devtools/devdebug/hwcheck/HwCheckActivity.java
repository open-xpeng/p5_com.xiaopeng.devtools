package com.xiaopeng.devtools.devdebug.hwcheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.EtcPwmEvent;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.factorytest.LcdDebugActivity;
import com.xiaopeng.devtools.view.factorytest.NavTestActivity;
import com.xiaopeng.devtools.view.factorytest.TestRebootActivity;
import com.xiaopeng.devtools.view.factorytest.audio.AudioTestActivity;
import com.xiaopeng.devtools.view.factorytest.powertest.PowerTestActivity;
import com.xiaopeng.devtools.view.hwcheck.ImageDispActivity;
import com.xiaopeng.devtools.view.signal.SignalShowActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class HwCheckActivity extends ActionBarActivity implements View.OnClickListener {
    private Button ra;
    private Button rb;
    private Button rc;
    private Button rd;
    private Button re;
    private Button rf;
    private Button rg;
    private Button rh;
    private b ri = new b();
    private BroadcastReceiver pj = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.devdebug.hwcheck.HwCheckActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!"com.xiaopeng.broadcast.START_UPGRADE_MODEM".equals(action)) {
                "com.xiaopeng.broadcast.STOP_UPGRADE_MODEM".equals(action);
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EtcPwmEvent etcPwmEvent) {
        if (this.rf != null) {
            this.rf.setEnabled(true);
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
        setContentView(R.layout.activity_hw_check);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaopeng.broadcast.START_UPGRADE_MODEM");
        intentFilter.addAction("com.xiaopeng.broadcast.STOP_UPGRADE_MODEM");
        registerReceiver(this.pj, intentFilter);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.ra = (Button) findViewById(R.id.btn_test_nav);
        this.rb = (Button) findViewById(R.id.btn_test_power_control);
        this.rc = (Button) findViewById(R.id.btn_test_audio);
        this.rd = (Button) findViewById(R.id.btn_test_signal);
        this.re = (Button) findViewById(R.id.btn_lcd_debug);
        this.rf = (Button) findViewById(R.id.btn_test_etc);
        this.rg = (Button) findViewById(R.id.btn_test_reboot);
        this.rh = (Button) findViewById(R.id.btn_test_image_disp);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.ra.setOnClickListener(this);
        this.rb.setOnClickListener(this);
        this.rc.setOnClickListener(this);
        this.rd.setOnClickListener(this);
        this.re.setOnClickListener(this);
        this.rg.setOnClickListener(this);
        this.rf.setOnClickListener(this);
        this.rh.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lcd_debug /* 2131296482 */:
                d(LcdDebugActivity.class);
                return;
            case R.id.btn_test_audio /* 2131296518 */:
                d(AudioTestActivity.class);
                return;
            case R.id.btn_test_etc /* 2131296536 */:
                this.ri.bS(4000);
                this.rf.setEnabled(false);
                return;
            case R.id.btn_test_image_disp /* 2131296543 */:
                d(ImageDispActivity.class);
                return;
            case R.id.btn_test_nav /* 2131296547 */:
                d(NavTestActivity.class);
                return;
            case R.id.btn_test_power_control /* 2131296550 */:
                d(PowerTestActivity.class);
                return;
            case R.id.btn_test_reboot /* 2131296555 */:
                d(TestRebootActivity.class);
                return;
            case R.id.btn_test_signal /* 2131296562 */:
                d(SignalShowActivity.class);
                return;
            default:
                return;
        }
    }

    private void bm(String str) {
        sendBroadcast(new Intent(str));
    }

    private void d(Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(67108864);
        startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        bm("com.xiaopeng.broadcast.STOP_UPGRADE_MODEM");
        EventBus.getDefault().unregister(this);
    }
}
