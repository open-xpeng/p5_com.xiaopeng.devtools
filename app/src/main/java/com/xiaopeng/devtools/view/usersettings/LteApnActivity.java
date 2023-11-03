package com.xiaopeng.devtools.view.usersettings;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.m;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.j;

/* loaded from: classes12.dex */
public class LteApnActivity extends ActionBarActivity {
    private CheckBox FH;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_4g_apn);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.FH = (CheckBox) findViewById(R.id.lte_3apn_onoff);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.FH.setChecked("on".equals(r.lm()));
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.FH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.usersettings.-$$Lambda$LteApnActivity$q01M0pNXAuwc1eQE-HZA1Di0vUM
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LteApnActivity.f(compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f(CompoundButton compoundButton, boolean z) {
        if (z) {
            r.cH("on");
        } else {
            r.cH("off");
        }
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.usersettings.-$$Lambda$LteApnActivity$ENBnGqzUcwNxsdG0XbgnvJLVO_w
            @Override // java.lang.Runnable
            public final void run() {
                LteApnActivity.ob();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void ob() {
        m.f(MyApplication.getContext(), "LteApnActivity");
    }
}
