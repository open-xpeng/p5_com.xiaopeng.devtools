package com.xiaopeng.devtools.view.factorytest;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.SeekBar;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.b;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.AdjustIndicator;

/* loaded from: classes12.dex */
public class LcdDebugActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener, a {
    private SeekBar FX;
    private AdjustIndicator FY;
    private com.xiaopeng.devtools.presenter.factorytest.a sj;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        this.sj = new b(this);
        setContentView(R.layout.activity_lcd_debug);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.FX = (SeekBar) findViewById(R.id.lcd_adjust_seekbar);
        this.FY = (AdjustIndicator) findViewById(R.id.lcd_adjust_indicator);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.sj.fJ();
        this.FX.setMax(80);
        this.FX.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.xiaopeng.devtools.view.factorytest.LcdDebugActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LcdDebugActivity.this.FY.r(LcdDebugActivity.this.FX.getPaddingLeft(), LcdDebugActivity.this.FX.getPaddingRight());
                LcdDebugActivity.this.FY.setProgressScale(50);
                LcdDebugActivity.this.FY.setMax(80);
                LcdDebugActivity.this.FX.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.FX.setOnSeekBarChangeListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.sj.bW(i + 40);
        this.FY.setProgress(i);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override // com.xiaopeng.devtools.view.factorytest.a
    public void cl(int i) {
        int i2 = i - 40;
        this.FX.setProgress(i2);
        this.FY.setProgress(i2);
    }
}
