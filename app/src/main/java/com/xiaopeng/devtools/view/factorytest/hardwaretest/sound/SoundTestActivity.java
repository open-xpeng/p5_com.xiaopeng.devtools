package com.xiaopeng.devtools.view.factorytest.hardwaretest.sound;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j.b;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.AdjustIndicator;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class SoundTestActivity extends AsOrFacBaseActivity implements SeekBar.OnSeekBarChangeListener, a {
    private AdjustIndicator FY;
    private boolean Gx = false;
    private TextView LG;
    private TextView LH;
    private TextView LI;
    private Button LJ;
    private Button LK;
    private SeekBar LL;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.j.a LM;
    private int LN;
    private int LO;
    private int LP;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_sound_test);
        this.LM = new b(this);
        this.LN = this.LM.getCurrentMediaVolume();
        this.LO = this.LM.getMediaMaxVolume();
        this.LP = 0;
        setTarget(getString(R.string.test_sound));
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.LL = (SeekBar) findViewById(R.id.media_adjust_seekbar);
        this.LG = (TextView) findViewById(R.id.result_sound_track_tv);
        this.LH = (TextView) findViewById(R.id.result_sound_adjust_tv);
        this.LI = (TextView) findViewById(R.id.tv_pa_status);
        this.LJ = (Button) findViewById(R.id.sound_track_test);
        this.LK = (Button) findViewById(R.id.bt_update_pa_status);
        this.FY = (AdjustIndicator) findViewById(R.id.media_adjust_indicator);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (this.theme == 1) {
            findViewById(R.id.sound_adjust_success_bt).setVisibility(8);
            findViewById(R.id.sound_adjust_fail_bt).setVisibility(8);
            findViewById(R.id.sound_track_success_bt).setVisibility(8);
            findViewById(R.id.sound_track_fail_bt).setVisibility(8);
        }
        this.LL.setMax(this.LO - this.LP);
        this.LL.setProgress(this.LN - this.LP);
        this.LL.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.SoundTestActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                SoundTestActivity.this.FY.r(SoundTestActivity.this.LL.getPaddingLeft(), SoundTestActivity.this.LL.getPaddingRight());
                SoundTestActivity.this.FY.setProgressScale(0);
                SoundTestActivity.this.FY.setMax(SoundTestActivity.this.LL.getMax());
                SoundTestActivity.this.FY.setProgress(SoundTestActivity.this.LL.getProgress());
                SoundTestActivity.this.LL.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        this.LM.ju();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.LL.setOnSeekBarChangeListener(this);
        this.LJ.setOnClickListener(this);
        this.LK.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.theme == 1) {
            recordRepairModeAction("sound diagnosis", "triggered");
        }
        new Thread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.SoundTestActivity.2
            @Override // java.lang.Runnable
            public void run() {
                final int i = SoundTestActivity.this.LN;
                if (!SoundTestActivity.this.Gx) {
                    while (i > 0) {
                        try {
                            Thread.sleep(60L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        SoundTestActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.SoundTestActivity.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                SoundTestActivity.this.LL.setProgress(i);
                            }
                        });
                        if (SoundTestActivity.this.Gx) {
                            break;
                        }
                        i--;
                    }
                }
                if (!SoundTestActivity.this.Gx) {
                    while (i < SoundTestActivity.this.LO - 2) {
                        try {
                            Thread.sleep(60L);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        SoundTestActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.SoundTestActivity.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                SoundTestActivity.this.LL.setProgress(i);
                            }
                        });
                        if (!SoundTestActivity.this.Gx) {
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            }
        }, "AdjustVolumeThread").start();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.bt_update_pa_status) {
            this.LI.setText("");
            this.LM.ju();
        } else if (id == R.id.sound_track_test) {
            this.LM.cg(R.raw.sound_track);
        }
    }

    public void onResult(View view) {
        switch (view.getId()) {
            case R.id.sound_adjust_fail_bt /* 2131297095 */:
                a(this.LH, false);
                a(getString(R.string.sound_adjust_test), false, "");
                return;
            case R.id.sound_adjust_header /* 2131297096 */:
            default:
                return;
            case R.id.sound_adjust_success_bt /* 2131297097 */:
                a(this.LH, true);
                a(getString(R.string.sound_adjust_test), true, "");
                return;
            case R.id.sound_track_fail_bt /* 2131297098 */:
                a(this.LG, false);
                a(getString(R.string.sound_track_test), false, "");
                return;
            case R.id.sound_track_success_bt /* 2131297099 */:
                a(this.LG, true);
                a(getString(R.string.sound_track_test), true, "");
                return;
        }
    }

    private void a(TextView textView, boolean z) {
        if (z) {
            textView.setText(R.string.test_succeed);
            textView.setTextColor(getResources().getColor(17170450));
            return;
        }
        textView.setText(R.string.test_fail);
        textView.setTextColor(getResources().getColor(17170454));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_sound));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_sound));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        c.f("SoundTest", "fromUser = " + z);
        if (z) {
            this.Gx = true;
        }
        this.LM.onVolumeChanged(i, this.LP);
        this.FY.setProgress(i);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.LM.destroy(this.LN);
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.a
    public void dk(final String str) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.sound.SoundTestActivity.3
            @Override // java.lang.Runnable
            public void run() {
                SoundTestActivity.this.LI.setText(str);
            }
        });
    }
}
