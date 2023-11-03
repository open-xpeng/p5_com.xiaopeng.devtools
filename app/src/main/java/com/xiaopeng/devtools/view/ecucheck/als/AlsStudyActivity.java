package com.xiaopeng.devtools.view.ecucheck.als;

import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBarActivity;

/* loaded from: classes12.dex */
public class AlsStudyActivity extends ActionBarActivity implements a {
    private TextView FN;
    private com.xiaopeng.devtools.presenter.a.a FO;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_als_init);
        this.FO = new com.xiaopeng.devtools.presenter.a.a(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.FN = (TextView) findViewById(R.id.tv_als_init_result);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.FO.init();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.FO.iv();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.FO.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.ecucheck.als.a
    public void cj(final int i) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.ecucheck.als.AlsStudyActivity.1
            @Override // java.lang.Runnable
            public void run() {
                switch (i) {
                    case 0:
                        AlsStudyActivity.this.FN.setText(R.string.als_init_fail_need_elec_check);
                        AlsStudyActivity.this.FN.setTextColor(AlsStudyActivity.this.getResources().getColor(17170455));
                        return;
                    case 1:
                        AlsStudyActivity.this.FN.setText(R.string.als_init_not_finished);
                        AlsStudyActivity.this.FN.setTextColor(AlsStudyActivity.this.getResources().getColor(17170443));
                        return;
                    case 2:
                        AlsStudyActivity.this.FN.setText(R.string.als_init_fail_need_reset);
                        AlsStudyActivity.this.FN.setTextColor(AlsStudyActivity.this.getResources().getColor(17170455));
                        return;
                    case 3:
                        AlsStudyActivity.this.FN.setText(R.string.als_init_success);
                        AlsStudyActivity.this.FN.setTextColor(AlsStudyActivity.this.getResources().getColor(17170453));
                        return;
                    default:
                        return;
                }
            }
        });
    }
}
