package com.xiaopeng.devtools.view.factorytest;

import android.os.Bundle;
import android.widget.CompoundButton;
import com.kyleduo.switchbutton.SwitchButton;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.RebootPolicyEvent;
import com.xiaopeng.devtools.model.c.e;
import com.xiaopeng.devtools.presenter.factorytest.c;
import com.xiaopeng.devtools.view.ActionBarActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class TestRebootActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener {
    private SwitchButton Gh;
    private c Gi = new c();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RebootPolicyEvent rebootPolicyEvent) {
        if (this.Gh != null) {
            this.Gh.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_test_reboot);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Gh = (SwitchButton) findViewById(R.id.switch_reboot_policy);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Gi.a(new e.b() { // from class: com.xiaopeng.devtools.view.factorytest.TestRebootActivity.1
            @Override // com.xiaopeng.devtools.model.c.e.b
            public void bY(int i) {
                if (i == 5) {
                    TestRebootActivity.this.Gh.setChecked(true);
                } else {
                    TestRebootActivity.this.Gh.setChecked(false);
                }
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Gh.setOnCheckedChangeListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.Gh.setEnabled(false);
            this.Gi.bX(5);
            return;
        }
        this.Gh.setEnabled(false);
        this.Gi.bX(0);
    }
}
