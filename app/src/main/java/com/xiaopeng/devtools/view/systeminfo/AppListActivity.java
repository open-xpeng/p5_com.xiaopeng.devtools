package com.xiaopeng.devtools.view.systeminfo;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.g.c;
import com.xiaopeng.devtools.presenter.g.d;
import com.xiaopeng.devtools.view.ActionBarActivity;
import java.util.List;

/* loaded from: classes12.dex */
public class AppListActivity extends ActionBarActivity implements b {
    private ListView TB;
    private AppVersionAdapter TC;
    private c uP;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_app_list);
        this.uP = new d(this);
        this.TC = new AppVersionAdapter();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.TB = (ListView) findViewById(R.id.lv_app_version);
        this.TB.setAdapter((ListAdapter) this.TC);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (this.theme == 1) {
            this.uP.ke();
        } else {
            this.uP.hK();
        }
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(List list) {
        this.TC.setPackageInfos(list);
    }

    @Override // com.xiaopeng.devtools.view.systeminfo.b
    public void u(final List<PackageInfo> list) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.systeminfo.-$$Lambda$AppListActivity$xuIFnpJw_NxBgi5OUmS-u_EJAnY
            @Override // java.lang.Runnable
            public final void run() {
                AppListActivity.this.B(list);
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.systeminfo.b
    public void du(String str) {
    }
}
