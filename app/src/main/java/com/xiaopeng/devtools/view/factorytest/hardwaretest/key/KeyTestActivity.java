package com.xiaopeng.devtools.view.factorytest.hardwaretest.key;

import android.support.v4.media.MediaPlayer2;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.system.a.a;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;

/* loaded from: classes12.dex */
public class KeyTestActivity extends AsOrFacBaseActivity implements View.OnClickListener {
    private a.InterfaceC0069a Ao = new a.InterfaceC0069a() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.key.KeyTestActivity.1
        @Override // com.xiaopeng.devtools.system.a.a.InterfaceC0069a
        public void b(KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case MediaPlayer2.MEDIA_INFO_BAD_INTERLEAVING /* 800 */:
                    KeyTestActivity.this.JZ.setTextColor(KeyTestActivity.this.getResources().getColor(17170450));
                    KeyTestActivity.this.JZ.setText(String.valueOf(KeyTestActivity.d(KeyTestActivity.this)));
                    return;
                case MediaPlayer2.MEDIA_INFO_NOT_SEEKABLE /* 801 */:
                    KeyTestActivity.this.JY.setTextColor(KeyTestActivity.this.getResources().getColor(17170450));
                    KeyTestActivity.this.JY.setText(String.valueOf(KeyTestActivity.f(KeyTestActivity.this)));
                    return;
                case MediaPlayer2.MEDIA_INFO_METADATA_UPDATE /* 802 */:
                    KeyTestActivity.this.JX.setTextColor(KeyTestActivity.this.getResources().getColor(17170450));
                    KeyTestActivity.this.JX.setText(String.valueOf(KeyTestActivity.b(KeyTestActivity.this)));
                    return;
                case MediaPlayer2.MEDIA_INFO_EXTERNAL_METADATA_UPDATE /* 803 */:
                    KeyTestActivity.this.Kc.setTextColor(KeyTestActivity.this.getResources().getColor(17170450));
                    KeyTestActivity.this.Kc.setText(String.valueOf(KeyTestActivity.j(KeyTestActivity.this)));
                    return;
                case MediaPlayer2.MEDIA_INFO_AUDIO_NOT_PLAYING /* 804 */:
                    KeyTestActivity.this.Kb.setTextColor(KeyTestActivity.this.getResources().getColor(17170450));
                    KeyTestActivity.this.Kb.setText(String.valueOf(KeyTestActivity.l(KeyTestActivity.this)));
                    return;
                case MediaPlayer2.MEDIA_INFO_VIDEO_NOT_PLAYING /* 805 */:
                    KeyTestActivity.this.Ka.setTextColor(KeyTestActivity.this.getResources().getColor(17170450));
                    KeyTestActivity.this.Ka.setText(String.valueOf(KeyTestActivity.h(KeyTestActivity.this)));
                    return;
                default:
                    return;
            }
        }
    };
    private Button JV;
    private Button JW;
    private TextView JX;
    private TextView JY;
    private TextView JZ;
    private TextView Ka;
    private TextView Kb;
    private TextView Kc;
    private TextView Kd;
    private a Ke;
    private int Kf;
    private int Kg;
    private int Kh;
    private int Ki;
    private int Kj;
    private int Kk;

    static /* synthetic */ int b(KeyTestActivity keyTestActivity) {
        int i = keyTestActivity.Kf + 1;
        keyTestActivity.Kf = i;
        return i;
    }

    static /* synthetic */ int d(KeyTestActivity keyTestActivity) {
        int i = keyTestActivity.Kh + 1;
        keyTestActivity.Kh = i;
        return i;
    }

    static /* synthetic */ int f(KeyTestActivity keyTestActivity) {
        int i = keyTestActivity.Kg + 1;
        keyTestActivity.Kg = i;
        return i;
    }

    static /* synthetic */ int h(KeyTestActivity keyTestActivity) {
        int i = keyTestActivity.Ki + 1;
        keyTestActivity.Ki = i;
        return i;
    }

    static /* synthetic */ int j(KeyTestActivity keyTestActivity) {
        int i = keyTestActivity.Kk + 1;
        keyTestActivity.Kk = i;
        return i;
    }

    static /* synthetic */ int l(KeyTestActivity keyTestActivity) {
        int i = keyTestActivity.Kj + 1;
        keyTestActivity.Kj = i;
        return i;
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_test_key);
        setTarget(getString(R.string.test_key));
        this.Ke = new a(this.Ao);
        this.Kh = 0;
        this.Kf = 0;
        this.Kg = 0;
        this.Ki = 0;
        this.Kk = 0;
        this.Kj = 0;
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.JX = (TextView) findViewById(R.id.key_temp_minus_result);
        this.JY = (TextView) findViewById(R.id.key_temp_plus_result);
        this.JZ = (TextView) findViewById(R.id.key_temp_degree_result);
        this.Ka = (TextView) findViewById(R.id.key_volume_minus_result);
        this.Kb = (TextView) findViewById(R.id.key_volume_plus_result);
        this.Kc = (TextView) findViewById(R.id.key_volume_mute_result);
        this.Kd = (TextView) findViewById(R.id.key_alarm_result);
        this.JV = (Button) findViewById(R.id.key_alarm_success_bt);
        this.JW = (Button) findViewById(R.id.key_alarm_fail_bt);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.JV.setOnClickListener(this);
        this.JW.setOnClickListener(this);
        this.Ke.register();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.key_alarm_fail_bt) {
            this.Kd.setText(getString(R.string.test_fail));
            this.Kd.setTextColor(getResources().getColor(17170454));
        } else if (id == R.id.key_alarm_success_bt) {
            this.Kd.setTextColor(getResources().getColor(17170450));
            this.Kd.setText(getString(R.string.test_succeed));
        } else {
            super.onClick(view);
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }
}
