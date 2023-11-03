package com.xiaopeng.devtools.view.aftersales;

import android.content.DialogInterface;
import android.view.ViewGroup;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.u;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class DIDInfoActivity extends AfterSalesBaseActivity implements a {
    private com.xiaopeng.devtools.presenter.aftersales.d DV;
    private ViewGroup mContainer;
    private com.xiaopeng.xui.app.e pe;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_didinfo);
        this.DV = new com.xiaopeng.devtools.presenter.aftersales.d(this);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.mContainer = (ViewGroup) findViewById(R.id.container);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        lV();
        this.DV.ig();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.a
    public void c(final ArrayList<String> arrayList) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.DIDInfoActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (DIDInfoActivity.this.pe != null) {
                    DIDInfoActivity.this.pe.dismiss();
                }
                u.a(DIDInfoActivity.this.mContainer, DIDInfoActivity.this.getApplicationContext(), arrayList, 2);
            }
        });
    }

    private void lV() {
        if (this.pe == null) {
            this.pe = com.xiaopeng.xui.app.e.a(this, getString(R.string.reading_did_info), true, new DialogInterface.OnCancelListener() { // from class: com.xiaopeng.devtools.view.aftersales.DIDInfoActivity.2
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    com.xiaopeng.lib.utils.c.g("DIDInfoActivity", "onCancel");
                    DIDInfoActivity.this.finish();
                }
            });
        } else {
            this.pe.show();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
