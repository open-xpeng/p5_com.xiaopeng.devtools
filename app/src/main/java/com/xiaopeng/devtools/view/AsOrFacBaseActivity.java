package com.xiaopeng.devtools.view;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.aftersales.CommonCmdActivity;
import com.xiaopeng.devtools.view.aftersales.EncryptShActivity;
import com.xiaopeng.devtools.view.aftersales.RebootActivity;
import com.xiaopeng.devtools.view.aftersales.WlanPcToolsActivity;
import com.xiaopeng.devtools.view.clean.ClearDataActivity;
import com.xiaopeng.devtools.view.log.GrabLogActivity;

/* loaded from: classes12.dex */
public class AsOrFacBaseActivity extends ToolBarActivity implements View.OnClickListener {
    private Button Cr;
    private Button Cs;
    private Button Ct;
    private Button Cu;
    private Button Cv;
    private Button Cw;
    private LinearLayout Cx;

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Cr = (Button) findViewById(R.id.btn_pc_tool);
        this.Cs = (Button) findViewById(R.id.btn_log);
        this.Ct = (Button) findViewById(R.id.btn_reboot);
        this.Cu = (Button) findViewById(R.id.btn_recovery);
        this.Cv = (Button) findViewById(R.id.btn_common_cmd);
        this.Cw = (Button) findViewById(R.id.btn_udisk_sh);
        this.Cx = (LinearLayout) findViewById(R.id.layout_aftersales_common);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (this.theme == 1) {
            my();
        } else {
            this.Cx.setVisibility(8);
        }
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Cr.setOnClickListener(this);
        this.Cs.setOnClickListener(this);
        this.Ct.setOnClickListener(this);
        this.Cu.setOnClickListener(this);
        this.Cv.setOnClickListener(this);
        this.Cw.setOnClickListener(this);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_common_cmd /* 2131296466 */:
                a(CommonCmdActivity.class, this.theme);
                return;
            case R.id.btn_log /* 2131296483 */:
                a(GrabLogActivity.class, this.theme);
                return;
            case R.id.btn_pc_tool /* 2131296488 */:
                a(WlanPcToolsActivity.class, this.theme);
                return;
            case R.id.btn_reboot /* 2131296492 */:
                a(RebootActivity.class, this.theme);
                return;
            case R.id.btn_recovery /* 2131296497 */:
                a(ClearDataActivity.class, this.theme);
                return;
            case R.id.btn_udisk_sh /* 2131296572 */:
                a(EncryptShActivity.class, this.theme);
                return;
            default:
                return;
        }
    }
}
