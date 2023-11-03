package com.xiaopeng.devtools.view.usersettings;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.aftersales.RepairModeListener;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;

/* loaded from: classes12.dex */
public class RepairModeActivity extends ActionBarActivity {
    private RepairModeListener Bx = new RepairModeListener() { // from class: com.xiaopeng.devtools.view.usersettings.RepairModeActivity.1
        public void onRepairModeChanged(final boolean z, int i) {
            j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.usersettings.RepairModeActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    RepairModeActivity.this.Ux.setChecked(z);
                    RepairModeActivity.this.Uy.setVisibility(8);
                }
            });
        }
    };
    private CheckBox Ux;
    private View Uy;
    private AfterSalesManager vl;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_repair_mode);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Ux = (CheckBox) findViewById(R.id.repair_mode_onoff);
        this.Uy = findViewById(R.id.loading_panel);
        this.vl = (AfterSalesManager) getSystemService("xiaopeng_aftersales");
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Ux.setChecked(this.vl.getRepairMode());
        this.Uy.setVisibility(8);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.vl.registerRepairModeListener(this.Bx);
        this.Ux.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.usersettings.-$$Lambda$RepairModeActivity$htlZ-9Dw_1F6ey1n13JwSVOLHTA
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RepairModeActivity.this.g(compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(CompoundButton compoundButton, boolean z) {
        c.f("RepairModeActivity", "onCheckedChanged isChecked = " + z);
        this.Uy.setVisibility(0);
        if (z == this.vl.getRepairMode()) {
            c.f("RepairModeActivity", "RepairMode is already " + z);
        } else if (z) {
            this.vl.enableRepairMode();
        } else {
            this.vl.disableRepairMode();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.vl.unregisterRepairModeListener(this.Bx);
    }
}
