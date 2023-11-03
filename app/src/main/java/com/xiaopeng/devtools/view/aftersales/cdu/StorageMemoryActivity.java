package com.xiaopeng.devtools.view.aftersales.cdu;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.aftersales.f;
import com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity;

/* loaded from: classes12.dex */
public class StorageMemoryActivity extends AfterSalesBaseActivity implements View.OnClickListener, b {
    private f Fj;
    private TextView Fk;
    private TextView Fl;
    private Button Fm;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_storage_memory);
        this.Fj = new f(this);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Fk = (TextView) findViewById(R.id.removable_storage);
        this.Fl = (TextView) findViewById(R.id.free_storage_result);
        this.Fm = (Button) findViewById(R.id.bt_free_storage);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Fm.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.Fj.iq();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.bt_free_storage) {
            this.Fm.setEnabled(false);
            this.Fk.setText("");
            this.Fl.setText(R.string.removing_unused_files);
            this.Fl.setTextColor(getResources().getColor(17170457));
            this.Fj.ir();
        }
        super.onClick(view);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void df(String str) {
        this.Fk.setText(str);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.cdu.b
    public void de(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.cdu.-$$Lambda$StorageMemoryActivity$x_jS3LhNPQkJhzAeTRFPJZBaXzc
            @Override // java.lang.Runnable
            public final void run() {
                StorageMemoryActivity.this.df(str);
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.aftersales.cdu.b
    public void mj() {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.cdu.-$$Lambda$StorageMemoryActivity$EVs_7TYKXLJ8AFfFcIILaWggLiE
            @Override // java.lang.Runnable
            public final void run() {
                StorageMemoryActivity.this.mk();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void mk() {
        this.Fm.setEnabled(true);
        this.Fl.setText(R.string.removed_unused_files);
        this.Fl.setTextColor(getResources().getColor(17170453));
        this.Fj.iq();
    }
}
