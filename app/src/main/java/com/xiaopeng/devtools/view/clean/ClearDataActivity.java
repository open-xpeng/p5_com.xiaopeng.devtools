package com.xiaopeng.devtools.view.clean;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.c;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.app.g;

/* loaded from: classes12.dex */
public class ClearDataActivity extends ActionBarActivity implements View.OnClickListener {
    private Button Cu;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_clean_data);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Cu = (Button) findViewById(R.id.btn_recovery);
        this.Cu.setBackgroundResource(R.drawable.btn_test_background);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Cu.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_recovery) {
            mq();
        }
    }

    private void mq() {
        new c(this).dV(R.string.title_dialog).dW(R.string.recovery_confirm).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.clean.-$$Lambda$ClearDataActivity$TMwLzgDm1qvSbagJnslTR0LV9cc
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(c cVar, int i) {
                ClearDataActivity.this.q(cVar, i);
            }
        }).h(getString(R.string.cancel)).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(c cVar, int i) {
        recordRepairModeAction("reset system", "triggered");
        g.ee(R.string.text_rest_policy_3sec);
        j.b(0, new Runnable() { // from class: com.xiaopeng.devtools.view.clean.-$$Lambda$ClearDataActivity$G6Z_Q5DpBzU99HCbD7PhJtyYNT0
            @Override // java.lang.Runnable
            public final void run() {
                ClearDataActivity.this.mr();
            }
        }, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void mr() {
        Intent intent = new Intent("android.intent.action.MASTER_CLEAR");
        intent.addFlags(16777216);
        sendBroadcast(intent);
    }
}
