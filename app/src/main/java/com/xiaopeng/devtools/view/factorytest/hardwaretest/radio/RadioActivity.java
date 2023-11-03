package com.xiaopeng.devtools.view.factorytest.hardwaretest.radio;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ToolBarActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.LongClickButton;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class RadioActivity extends ToolBarActivity implements LongClickButton.a {
    private TextView Ld;
    private Button Li;
    private Button Lj;
    private Button Lk;
    private Button Ll;
    private TextView KX = null;
    private LongClickButton KY = null;
    private LongClickButton KZ = null;
    private TextView La = null;
    private LongClickButton Lb = null;
    private LongClickButton Lc = null;
    private TextView Le = null;
    private Button Lf = null;
    private Button Lg = null;
    private Button Lh = null;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.h.a Lm = null;
    public int Ln = 87500;
    private int Lo = 999;
    Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.RadioActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    String str = (String) message.obj;
                    if (RadioActivity.this.Le != null) {
                        RadioActivity.this.Le.setText(str);
                        return;
                    }
                    return;
                case 2:
                    if (RadioActivity.this.KX != null) {
                        TextView textView = RadioActivity.this.KX;
                        textView.setText(((((Integer) message.obj).intValue() * 1.0d) / 1000.0d) + "");
                        RadioActivity.this.di(1);
                        return;
                    }
                    return;
                case 3:
                    if (RadioActivity.this.La != null) {
                        TextView textView2 = RadioActivity.this.La;
                        textView2.setText(((Integer) message.obj).intValue() + "");
                        RadioActivity.this.di(0);
                        return;
                    }
                    return;
                case 4:
                    if (RadioActivity.this.Ld != null) {
                        RadioActivity.this.Ld.setText(RadioActivity.this.getString(R.string.radio_current_volume, new Object[]{(String) message.obj}));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    a xc = new a() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.RadioActivity.3
        @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.a
        public void di(String str) {
            RadioActivity.this.mHandler.obtainMessage(1, str).sendToTarget();
        }

        @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.a
        public void df(int i) {
            RadioActivity.this.Ln = i;
            RadioActivity.this.mHandler.obtainMessage(2, Integer.valueOf(i)).sendToTarget();
        }

        @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.a
        public void dg(int i) {
            RadioActivity.this.Lo = i;
            RadioActivity.this.mHandler.obtainMessage(3, Integer.valueOf(i)).sendToTarget();
        }

        @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.a
        public void dh(int i) {
            RadioActivity.this.di(i);
        }

        @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.a
        public void dj(String str) {
            Message obtainMessage = RadioActivity.this.mHandler.obtainMessage();
            obtainMessage.obj = str;
            obtainMessage.what = 4;
            RadioActivity.this.mHandler.sendMessage(obtainMessage);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_radio);
        dJ();
        setTarget(getString(R.string.test_radio));
    }

    private void dJ() {
        this.Lm = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.h.a(getApplicationContext(), this.xc);
        this.Lm.jl();
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.KX = (TextView) findViewById(R.id.txt_fm_channel);
        this.KY = (LongClickButton) findViewById(R.id.btn_fm_last);
        this.KZ = (LongClickButton) findViewById(R.id.btn_fm_next);
        this.Ld = (TextView) findViewById(R.id.tv_current_volume);
        this.La = (TextView) findViewById(R.id.txt_am_channel);
        this.Lb = (LongClickButton) findViewById(R.id.btn_am_last);
        this.Lc = (LongClickButton) findViewById(R.id.btn_am_next);
        this.Li = (Button) findViewById(R.id.btn_volume_decrease);
        this.Lj = (Button) findViewById(R.id.btn_volume_increase);
        this.Ll = (Button) findViewById(R.id.btn_last_station);
        this.Lk = (Button) findViewById(R.id.btn_next_station);
        this.Le = (TextView) findViewById(R.id.txt_radio_result);
        this.Lg = (Button) findViewById(R.id.btn_radio_scan);
        this.Lh = (Button) findViewById(R.id.btn_radio_stop_scan);
        this.Lf = (Button) findViewById(R.id.btn_change_band);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        TextView textView = this.KX;
        textView.setText(((this.Ln * 1.0d) / 1000.0d) + "");
        TextView textView2 = this.La;
        textView2.setText(this.Lo + "");
        j.b(1, new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.RadioActivity.1
            @Override // java.lang.Runnable
            public void run() {
                RadioActivity.this.Lm.jn();
                RadioActivity.this.Lm.q(1, 87500);
            }
        }, 1000L);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.KY.setOnClickListener(this);
        this.KY.setLongClickRepeatListener(this);
        this.KZ.setOnClickListener(this);
        this.KZ.setLongClickRepeatListener(this);
        this.Lb.setOnClickListener(this);
        this.Lb.setLongClickRepeatListener(this);
        this.Lc.setOnClickListener(this);
        this.Lc.setLongClickRepeatListener(this);
        this.Lg.setOnClickListener(this);
        this.Lh.setOnClickListener(this);
        this.Lf.setOnClickListener(this);
        this.Li.setOnClickListener(this);
        this.Lj.setOnClickListener(this);
        this.Ll.setOnClickListener(this);
        this.Lk.setOnClickListener(this);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_am_last /* 2131296451 */:
                if (dk(this.Lo - 9)) {
                    this.Lm.q(0, this.Lo);
                    return;
                }
                return;
            case R.id.btn_am_next /* 2131296452 */:
                if (dk(this.Lo + 9)) {
                    this.Lm.q(0, this.Lo);
                    return;
                }
                return;
            case R.id.btn_change_band /* 2131296464 */:
                this.Lm.jp();
                return;
            case R.id.btn_fm_last /* 2131296476 */:
                if (dj(this.Ln - 100)) {
                    this.Lm.q(1, this.Ln);
                    return;
                }
                return;
            case R.id.btn_fm_next /* 2131296477 */:
                if (dj(this.Ln + 100)) {
                    this.Lm.q(1, this.Ln);
                    return;
                }
                return;
            case R.id.btn_last_station /* 2131296481 */:
                this.Lm.searchStationDown();
                return;
            case R.id.btn_next_station /* 2131296485 */:
                this.Lm.searchStationUp();
                return;
            case R.id.btn_radio_scan /* 2131296490 */:
                this.Lm.gM();
                return;
            case R.id.btn_radio_stop_scan /* 2131296491 */:
                this.Lm.stopScan();
                return;
            case R.id.btn_volume_decrease /* 2131296582 */:
                this.Lm.jr();
                return;
            case R.id.btn_volume_increase /* 2131296583 */:
                this.Lm.jq();
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        super.mv();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_radio));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        super.mw();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_radio));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Lm.jo();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.LongClickButton.a
    public void repeatAction(View view) {
        switch (view.getId()) {
            case R.id.btn_am_last /* 2131296451 */:
                if (dk(this.Lo - 9)) {
                    this.Lm.q(0, this.Lo);
                    return;
                }
                return;
            case R.id.btn_am_next /* 2131296452 */:
                if (dk(this.Lo + 9)) {
                    this.Lm.q(0, this.Lo);
                    return;
                }
                return;
            case R.id.btn_fm_last /* 2131296476 */:
                if (dj(this.Ln - 100)) {
                    this.Lm.q(1, this.Ln);
                    return;
                }
                return;
            case R.id.btn_fm_next /* 2131296477 */:
                if (dj(this.Ln + 100)) {
                    this.Lm.q(1, this.Ln);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void di(int i) {
        if (i == 0) {
            this.Lf.setText(R.string.radio_am_band);
            this.KY.setEnabled(false);
            this.KZ.setEnabled(false);
            this.Lb.setEnabled(true);
            this.Lc.setEnabled(true);
            return;
        }
        this.Lf.setText(R.string.radio_fm_band);
        this.KY.setEnabled(true);
        this.KZ.setEnabled(true);
        this.Lb.setEnabled(false);
        this.Lc.setEnabled(false);
    }

    public boolean dj(int i) {
        if (i > 108000) {
            this.Ln = 108000;
            return false;
        } else if (i < 87500) {
            this.Ln = 87500;
            return false;
        } else {
            this.Ln = i;
            return true;
        }
    }

    public boolean dk(int i) {
        if (i > 1710) {
            this.Lo = 1710;
            return false;
        } else if (i < 522) {
            this.Lo = 522;
            return false;
        } else {
            this.Lo = i;
            return true;
        }
    }
}
