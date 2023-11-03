package com.xiaopeng.devtools.view.aftersales;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.clean.ClearDataActivity;
import com.xiaopeng.devtools.view.log.GrabLogActivity;

/* loaded from: classes12.dex */
public class AfterSalesBaseActivity extends ActionBarActivity implements View.OnClickListener {
    private Button Cr;
    private Button Cs;
    private Button Ct;
    private Button Cu;
    private Button Cv;
    private Button Cw;
    private LinearLayout Cx;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Cs = (Button) findViewById(R.id.btn_log);
        this.Ct = (Button) findViewById(R.id.btn_reboot);
        this.Cu = (Button) findViewById(R.id.btn_recovery);
        this.Cv = (Button) findViewById(R.id.btn_common_cmd);
        this.Cw = (Button) findViewById(R.id.btn_udisk_sh);
        this.Cr = (Button) findViewById(R.id.btn_pc_tool);
        this.Cx = (LinearLayout) findViewById(R.id.layout_aftersales_common);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (this.theme != 1) {
            this.Cx.setVisibility(8);
        }
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Cs.setOnClickListener(this);
        this.Ct.setOnClickListener(this);
        this.Cu.setOnClickListener(this);
        this.Cv.setOnClickListener(this);
        this.Cw.setOnClickListener(this);
        this.Cr.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_common_cmd /* 2131296466 */:
                a(CommonCmdActivity.class, 1);
                return;
            case R.id.btn_log /* 2131296483 */:
                a(GrabLogActivity.class, 1);
                return;
            case R.id.btn_pc_tool /* 2131296488 */:
                a(WlanPcToolsActivity.class, 1);
                return;
            case R.id.btn_reboot /* 2131296492 */:
                a(RebootActivity.class, 1);
                return;
            case R.id.btn_recovery /* 2131296497 */:
                a(ClearDataActivity.class, 1);
                return;
            case R.id.btn_udisk_sh /* 2131296572 */:
                a(EncryptShActivity.class, 1);
                return;
            default:
                return;
        }
    }
}
