package com.xiaopeng.devtools.view;

import android.os.Bundle;
import android.view.View;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBar;

/* loaded from: classes12.dex */
public class ActionBarActivity extends AppBaseActivity {
    protected ActionBar Cq;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Cq = (ActionBar) findViewById(R.id.action_bar);
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.Cq.setOnBackListener(new ActionBar.a() { // from class: com.xiaopeng.devtools.view.-$$Lambda$ActionBarActivity$pJw2pNwvijRReTrfNzWP8-loTFo
            @Override // com.xiaopeng.devtools.view.ActionBar.a
            public final void onBack(View view) {
                ActionBarActivity.this.f(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(View view) {
        finish();
    }
}
