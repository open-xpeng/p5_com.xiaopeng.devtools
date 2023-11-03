package com.xiaopeng.devtools.view.factorytest.hardwaretest.rgb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBar;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;

/* loaded from: classes12.dex */
public class RGBActivity extends AsOrFacBaseActivity {
    private ActionBar Cq = null;
    private Button Ly = null;
    private Button Lz = null;
    private Button LA = null;
    private Button LB = null;
    private Button LC = null;
    private Button LD = null;
    private RelativeLayout LE = null;
    private LinearLayout LF = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_rgb);
        setTarget(getString(R.string.test_rgb));
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Cq = (ActionBar) findViewById(R.id.action_bar);
        this.Ly = (Button) findViewById(R.id.btn_rgb_red);
        this.Lz = (Button) findViewById(R.id.btn_rgb_green);
        this.LA = (Button) findViewById(R.id.btn_rgb_blue);
        this.LB = (Button) findViewById(R.id.btn_rgb_white);
        this.LC = (Button) findViewById(R.id.btn_rgb_gray);
        this.LD = (Button) findViewById(R.id.btn_rgb_black);
        this.LE = (RelativeLayout) findViewById(R.id.rl_rgb);
        this.LF = (LinearLayout) findViewById(R.id.include_common);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Ly.setOnClickListener(this);
        this.Lz.setOnClickListener(this);
        this.LA.setOnClickListener(this);
        this.LB.setOnClickListener(this);
        this.LC.setOnClickListener(this);
        this.LD.setOnClickListener(this);
        this.LE.setOnClickListener(this);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id != R.id.rl_rgb) {
            switch (id) {
                case R.id.btn_rgb_black /* 2131296499 */:
                    this.LD.setBackgroundResource(R.drawable.btn_test_background);
                    t(4, -16777216);
                    return;
                case R.id.btn_rgb_blue /* 2131296500 */:
                    this.LA.setBackgroundResource(R.drawable.btn_test_background);
                    t(4, -16776961);
                    return;
                case R.id.btn_rgb_gray /* 2131296501 */:
                    this.LC.setBackgroundResource(R.drawable.btn_test_background);
                    t(4, -8421505);
                    return;
                case R.id.btn_rgb_green /* 2131296502 */:
                    this.Lz.setBackgroundResource(R.drawable.btn_test_background);
                    t(4, -16711936);
                    return;
                case R.id.btn_rgb_red /* 2131296503 */:
                    this.Ly.setBackgroundResource(R.drawable.btn_test_background);
                    t(4, -65536);
                    return;
                case R.id.btn_rgb_white /* 2131296504 */:
                    this.LB.setBackgroundResource(R.drawable.btn_test_background);
                    t(4, -1);
                    return;
                default:
                    return;
            }
        }
        t(0, getResources().getColor(R.color.bg_main));
    }

    private void t(int i, int i2) {
        this.LE.setBackgroundColor(i2);
        this.Cq.setVisibility(i);
        this.LA.setVisibility(i);
        this.Lz.setVisibility(i);
        this.Ly.setVisibility(i);
        this.LB.setVisibility(i);
        this.LC.setVisibility(i);
        this.LD.setVisibility(i);
        this.LF.setVisibility(i);
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
