package com.xiaopeng.devtools.view.aftersales.cdu;

import android.content.Intent;
import android.view.View;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class ModuleDiagnosisActivity extends AfterSalesMenuActivity implements View.OnClickListener {
    private HashMap<Integer, String> DC;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        this.theme = 1;
        this.DC = new HashMap<>();
        this.DC.put(4, getString(R.string.diagnosis_module_wifi));
        this.DC.put(3, getString(R.string.diagnosis_module_bluetooth));
        this.DC.put(9, getString(R.string.diagnosis_module_navi));
        this.DC.put(1, getString(R.string.diagnosis_module_audio));
        this.DC.put(2, getString(R.string.diagnosis_module_camera));
        this.DC.put(13, getString(R.string.diagnosis_module_usb));
        this.DC.put(10, getString(R.string.diagnosis_module_4g));
        this.DC.put(8, getString(R.string.diagnosis_module_lcd));
        if (a.b.getBoolean("SUPPORT_ETH")) {
            this.DC.put(15, getString(R.string.diagnosis_module_phy));
        }
        this.DC.put(11, getString(R.string.diagnosis_module_ufs));
        if (a.b.getBoolean("SUPPORT_XPU") && r.lA()) {
            this.DC.put(93872, getString(R.string.diagnosis_module_xpu));
        }
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        a(this.DC);
        this.Cq.setCenterText(getString(R.string.text_module_diagnosis));
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
        if (this.DC.containsKey(Integer.valueOf(view.getId()))) {
            Intent intent = new Intent(this, EachModuleDiagnosisActivity.class);
            intent.putExtra("diagnosis_module", view.getId());
            a(intent);
        }
        super.onClick(view);
    }
}
