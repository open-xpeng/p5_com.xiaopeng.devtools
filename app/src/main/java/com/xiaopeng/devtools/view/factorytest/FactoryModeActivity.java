package com.xiaopeng.devtools.view.factorytest;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;

/* loaded from: classes12.dex */
public class FactoryModeActivity extends ActionBarActivity {
    private CheckBox FQ;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_factory_mode);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.FQ = (CheckBox) findViewById(R.id.factory_mode_onoff);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.FQ.setChecked(r.isFactoryMode());
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.FQ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.factorytest.-$$Lambda$FactoryModeActivity$MSJqovAImaPVoaNuROB8fnNU_-8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FactoryModeActivity.g(compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(CompoundButton compoundButton, boolean z) {
        c.f("FactoryModeActivity", "onCheckedChanged isChecked = " + z);
        r.Y(z);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
