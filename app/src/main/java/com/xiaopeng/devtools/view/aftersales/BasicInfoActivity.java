package com.xiaopeng.devtools.view.aftersales;

import android.view.View;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.systeminfo.AppListActivity;
import com.xiaopeng.devtools.view.systeminfo.SystemVersionActivity;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class BasicInfoActivity extends AfterSalesMenuActivity implements View.OnClickListener {
    private HashMap<Integer, String> DC;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        this.theme = 1;
        this.DC = new HashMap<>();
        this.DC.put(1, getString(R.string.version_info));
        this.DC.put(2, getString(R.string.apps_version_info));
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        a(this.DC);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case 1:
                a(SystemVersionActivity.class, this.theme);
                break;
            case 2:
                a(AppListActivity.class, this.theme);
                break;
        }
        super.onClick(view);
    }
}
