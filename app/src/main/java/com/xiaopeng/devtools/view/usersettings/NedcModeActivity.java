package com.xiaopeng.devtools.view.usersettings;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.model.a.e;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;

/* loaded from: classes12.dex */
public class NedcModeActivity extends ActionBarActivity {
    private CheckBox Uw;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_nedc_mode);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Uw = (CheckBox) findViewById(R.id.nedc_mode_onoff);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Uw.setChecked(false);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Uw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.usersettings.-$$Lambda$NedcModeActivity$QjguzFZsHGh1A7Cx6A57rITWK70
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                NedcModeActivity.g(compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(CompoundButton compoundButton, boolean z) {
        c.f("NedcModeActivity", "onCheckedChanged isChecked = " + z);
        if (z) {
            e.fA().setNedcSwitch(1);
        }
    }
}
