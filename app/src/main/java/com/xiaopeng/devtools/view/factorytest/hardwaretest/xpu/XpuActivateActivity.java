package com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.xui.app.c;
import com.xiaopeng.xui.app.d;

/* loaded from: classes12.dex */
public class XpuActivateActivity extends ActionBarActivity implements View.OnClickListener, a {
    private c DS;
    private TextView OD;
    private Button OE;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.a OF;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_xpu_activate);
        this.OF = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.a(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.DS = new c(this);
        this.OD = (TextView) findViewById(R.id.tv_xpu_status);
        this.OE = (Button) findViewById(R.id.bt_activate_xpu);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.OE.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.OF.iF();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.bt_activate_xpu) {
            nx();
        }
    }

    private void nx() {
        this.DS.dV(R.string.test_xpu_activate).dW(R.string.confirm_activate_xpu).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.-$$Lambda$XpuActivateActivity$qOrIht6dYZwqo_XQFKD14BWpKcg
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(c cVar, int i) {
                XpuActivateActivity.this.s(cVar, i);
            }
        }).b(getString(R.string.cancel), new d.a() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.-$$Lambda$XpuActivateActivity$d809lz9DeqMN2MAZXelUb19NRj8
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(c cVar, int i) {
                XpuActivateActivity.this.r(cVar, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(c cVar, int i) {
        this.OE.setEnabled(false);
        this.OD.setText(R.string.xpu_activating);
        this.OF.jw();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(c cVar, int i) {
        this.DS.dismiss();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.OF.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.a
    public void d(final int i, final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.-$$Lambda$XpuActivateActivity$lfkXwy-4A0DRqFb73qM01vb0cuQ
            @Override // java.lang.Runnable
            public final void run() {
                XpuActivateActivity.this.s(str, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(String str, int i) {
        this.OD.setText(str);
        switch (i) {
            case -1:
                this.OD.setTextColor(getResources().getColor(17170443));
                this.OE.setEnabled(false);
                return;
            case 0:
                this.OD.setTextColor(getResources().getColor(17170443));
                this.OE.setEnabled(true);
                return;
            case 1:
            default:
                this.OD.setTextColor(getResources().getColor(17170457));
                this.OE.setEnabled(false);
                return;
            case 2:
                this.OD.setTextColor(getResources().getColor(17170453));
                this.OE.setEnabled(false);
                return;
            case 3:
                this.OD.setTextColor(getResources().getColor(17170455));
                this.OE.setEnabled(true);
                return;
        }
    }
}
