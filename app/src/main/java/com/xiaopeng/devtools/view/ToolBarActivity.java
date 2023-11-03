package com.xiaopeng.devtools.view;

import android.os.Bundle;
import android.view.View;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBar;
import com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity;

/* loaded from: classes12.dex */
public class ToolBarActivity extends FactoryTestBaseActivity {
    private ActionBar Cq;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Cq = (ActionBar) findViewById(R.id.action_bar);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Cq.setOnBackListener(new ActionBar.a() { // from class: com.xiaopeng.devtools.view.-$$Lambda$ToolBarActivity$dB93CZbqcEIiBp5RL2PzeOrLUGc
            @Override // com.xiaopeng.devtools.view.ActionBar.a
            public final void onBack(View view) {
                ToolBarActivity.this.g(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
