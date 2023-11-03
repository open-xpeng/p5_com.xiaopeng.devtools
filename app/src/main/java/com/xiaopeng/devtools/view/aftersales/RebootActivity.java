package com.xiaopeng.devtools.view.aftersales;

import android.view.View;
import android.widget.Button;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.m;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.app.g;

/* loaded from: classes12.dex */
public class RebootActivity extends ActionBarActivity implements View.OnClickListener {
    private Button Ct;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_reboot);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Ct = (Button) findViewById(R.id.btn_reboot);
        this.Ct.setBackgroundResource(R.drawable.btn_test_background);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Ct.setOnClickListener(this);
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
        if (view.getId() == R.id.btn_reboot) {
            mf();
        }
    }

    private void mf() {
        new com.xiaopeng.xui.app.c(this).dV(R.string.title_dialog).dW(R.string.reboot_confirm).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$RebootActivity$86N1M8q8_6P7WpcEfHKBA5cVmbw
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                RebootActivity.this.l(cVar, i);
            }
        }).b(getString(R.string.cancel), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$RebootActivity$Pzvhj7G5RPDJh5mpKYjMaJsyaNM
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                cVar.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(com.xiaopeng.xui.app.c cVar, int i) {
        recordRepairModeAction("reboot system", "triggered");
        g.ee(R.string.text_reboot_policy_3sec);
        j.b(0, new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$RebootActivity$vHMJ1Bf8Fb7mvR8u_aEx5ZfswEU
            @Override // java.lang.Runnable
            public final void run() {
                RebootActivity.this.mg();
            }
        }, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void mg() {
        m.f(this, "RebootActivity");
    }
}
