package com.xiaopeng.devtools.view.navi;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity;

/* loaded from: classes12.dex */
public class NaviActivity extends AfterSalesBaseActivity implements View.OnClickListener, a {
    private TextView Rm;
    private Button Rn;
    private com.xiaopeng.devtools.presenter.d.a Ro;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_navi);
        this.Ro = new com.xiaopeng.devtools.presenter.d.a(this);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Rm = (TextView) findViewById(R.id.tv_uuid_value);
        this.Rn = (Button) findViewById(R.id.bt_get_uuid);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        nK();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Rn.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.bt_get_uuid) {
            this.Ro.jJ();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void nL() {
        this.Rm.setText(this.Ro.jK());
    }

    @Override // com.xiaopeng.devtools.view.navi.a
    public void nK() {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.navi.-$$Lambda$NaviActivity$vU3TI-rPP5i_MEjiUSAENi0vtis
            @Override // java.lang.Runnable
            public final void run() {
                NaviActivity.this.nL();
            }
        });
    }
}
