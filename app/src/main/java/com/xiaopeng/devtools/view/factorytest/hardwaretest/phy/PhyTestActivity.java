package com.xiaopeng.devtools.view.factorytest.hardwaretest.phy;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;

/* loaded from: classes12.dex */
public class PhyTestActivity extends AsOrFacBaseActivity implements View.OnClickListener, a {
    private TextView KD;
    private TextView KI;
    private TextView KJ;
    private TextView KK;
    private EditText KL;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f.a KM;
    private Button Kx;

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_phy_test);
        setTarget(getString(R.string.test_phy));
        this.KM = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.f.a(this);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.KI = (TextView) findViewById(R.id.link_stat_value);
        this.KJ = (TextView) findViewById(R.id.masterslave_mode_value);
        this.KK = (TextView) findViewById(R.id.sqi_value);
        this.KD = (TextView) findViewById(R.id.result_ping_tv);
        this.KL = (EditText) findViewById(R.id.et_ping_ip);
        this.Kx = (Button) findViewById(R.id.phy_ping_bt);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Kx.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.KM.je();
        this.KM.jf();
        this.KM.jd();
        if (this.theme == 1) {
            recordRepairModeAction("ethernet diagnosis", "triggered");
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.phy_ping_bt) {
            this.Kx.setText(R.string.testing);
            this.KM.bZ(this.KL.getText().toString());
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

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.a
    public void cZ(final int i) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.-$$Lambda$PhyTestActivity$XnDttHNslP_gHseZtDI9RhO9its
            @Override // java.lang.Runnable
            public final void run() {
                PhyTestActivity.this.de(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void de(int i) {
        switch (i) {
            case 0:
                this.KI.setText(R.string.phy_unlinked);
                return;
            case 1:
                this.KI.setText(R.string.phy_linked);
                return;
            default:
                this.KI.setText(R.string.phy_unknown_link_stat);
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.a
    public void da(final int i) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.-$$Lambda$PhyTestActivity$_kt_OlCe3TnPHYskJAjhr-ZcS-Q
            @Override // java.lang.Runnable
            public final void run() {
                PhyTestActivity.this.dd(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dd(int i) {
        switch (i) {
            case 0:
                this.KJ.setText(R.string.phy_slave_mode);
                return;
            case 1:
                this.KJ.setText(R.string.phy_master_mode);
                return;
            default:
                this.KJ.setText(R.string.phy_unknown_mode);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dc(int i) {
        this.KK.setText(String.valueOf(i));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.a
    public void db(final int i) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.-$$Lambda$PhyTestActivity$yX_P11HHNl8v5eiXQzrmPmkceFk
            @Override // java.lang.Runnable
            public final void run() {
                PhyTestActivity.this.dc(i);
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.a
    public void ay(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.phy.-$$Lambda$PhyTestActivity$U82LO0xuAIhiesUNQrQxncfMJBg
            @Override // java.lang.Runnable
            public final void run() {
                PhyTestActivity.this.az(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void az(boolean z) {
        this.Kx.setText(R.string.test);
        if (z) {
            this.KD.setText(R.string.test_succeed);
            this.KD.setTextColor(getResources().getColor(17170450));
            return;
        }
        this.KD.setText(R.string.test_fail);
        this.KD.setTextColor(getResources().getColor(17170454));
    }
}
