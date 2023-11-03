package com.xiaopeng.devtools.view.aftersales;

import android.widget.ListAdapter;
import android.widget.ListView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.aftersales.RealtimeStatus;
import com.xiaopeng.devtools.presenter.aftersales.RealtimeStatusPresenter;

/* loaded from: classes12.dex */
public class RealtimeStatusActivity extends AfterSalesBaseActivity implements d {
    private RealtimeStatusPresenter Eo;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d Ep;
    private RealtimeStatusAdapter Eq;
    private ListView mListView;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_realtime_status);
        this.Ep = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b();
        this.Ep.iP();
        this.Ep.iR();
        this.Eo = new RealtimeStatusPresenter(this);
        this.Eq = new RealtimeStatusAdapter();
        this.Eq.setAllStatus(this.Eo.io());
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.mListView = (ListView) findViewById(R.id.status_list);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.mListView.setAdapter((ListAdapter) this.Eq);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.Eo.ip();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(RealtimeStatus[] realtimeStatusArr) {
        this.Eq.setAllStatus(realtimeStatusArr);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.d
    public void a(final RealtimeStatus[] realtimeStatusArr) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$RealtimeStatusActivity$UJiFFTDut86wKCFrjUHdgtMzVsg
            @Override // java.lang.Runnable
            public final void run() {
                RealtimeStatusActivity.this.b(realtimeStatusArr);
            }
        });
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Eo.destroy();
        this.Ep.iQ();
        this.Ep.iS();
    }
}
