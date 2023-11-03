package com.xiaopeng.devtools.view.factorytest.hardwaretest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.SeekBar;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class BrightnessTestActivity extends AsOrFacBaseActivity implements SeekBar.OnSeekBarChangeListener {
    private AdjustIndicator FY;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.a GA;
    private SeekBar Gt;
    int Gu;
    int Gv;
    int Gw;
    private boolean Gx = false;
    private Button Gy;
    private Button Gz;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.GA = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.a(this);
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_brightness_test);
        setTarget(getString(R.string.test_back_light));
        this.Gu = this.GA.fN();
        this.Gv = this.GA.fM();
        this.Gw = this.GA.fO();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.FY = (AdjustIndicator) findViewById(R.id.brightness_adjust_indicator);
        this.Gt = (SeekBar) findViewById(R.id.brightness_seekbar);
        this.Gy = (Button) findViewById(R.id.btn_bright_down);
        this.Gz = (Button) findViewById(R.id.btn_bright_up);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Gt.setMax(this.Gv - this.Gu);
        this.Gt.setProgress(this.Gw - this.Gu);
        this.Gt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.BrightnessTestActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                BrightnessTestActivity.this.FY.r(BrightnessTestActivity.this.Gt.getPaddingLeft(), BrightnessTestActivity.this.Gt.getPaddingRight() * 3);
                BrightnessTestActivity.this.FY.setProgressScale(0);
                BrightnessTestActivity.this.FY.setMax(BrightnessTestActivity.this.Gt.getMax());
                BrightnessTestActivity.this.FY.setProgress(BrightnessTestActivity.this.Gt.getProgress());
                BrightnessTestActivity.this.Gt.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Gt.setOnSeekBarChangeListener(this);
        this.Gy.setOnClickListener(this);
        this.Gz.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.theme == 1) {
            recordRepairModeAction("back light diagnosis", "triggered");
        }
        new Thread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.BrightnessTestActivity.2
            @Override // java.lang.Runnable
            public void run() {
                final int i = BrightnessTestActivity.this.Gw;
                if (!BrightnessTestActivity.this.Gx) {
                    while (i >= BrightnessTestActivity.this.Gu) {
                        try {
                            Thread.sleep(12L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        BrightnessTestActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.BrightnessTestActivity.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                BrightnessTestActivity.this.Gt.setProgress(i - BrightnessTestActivity.this.Gu);
                            }
                        });
                        if (BrightnessTestActivity.this.Gx) {
                            break;
                        }
                        i--;
                    }
                }
                if (!BrightnessTestActivity.this.Gx) {
                    while (i <= BrightnessTestActivity.this.Gv) {
                        try {
                            Thread.sleep(6L);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        BrightnessTestActivity.this.runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.BrightnessTestActivity.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                BrightnessTestActivity.this.Gt.setProgress(i - BrightnessTestActivity.this.Gu);
                            }
                        });
                        if (!BrightnessTestActivity.this.Gx) {
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            }
        }, "AdjustVolumeThread").start();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.Gx = true;
        }
        this.GA.bZ(this.Gu + i);
        if (i <= 0) {
            this.Gy.setEnabled(false);
            this.Gz.setEnabled(true);
        } else if (i < this.Gv - this.Gu) {
            this.Gy.setEnabled(true);
            this.Gz.setEnabled(true);
        } else {
            this.Gz.setEnabled(false);
            this.Gy.setEnabled(true);
        }
        this.FY.setProgress(i + this.Gu);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_bright_down /* 2131296455 */:
                int progress = (this.Gt.getProgress() + this.Gu) - 1;
                if (progress >= this.Gu) {
                    this.Gt.setProgress(progress - this.Gu);
                    return;
                }
                return;
            case R.id.btn_bright_up /* 2131296456 */:
                int progress2 = this.Gt.getProgress() + this.Gu + 1;
                if (progress2 <= this.Gv) {
                    this.Gt.setProgress(progress2 - this.Gu);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.GA.bZ(this.Gw);
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        super.mv();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_back_light));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        super.mw();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_back_light));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }
}
