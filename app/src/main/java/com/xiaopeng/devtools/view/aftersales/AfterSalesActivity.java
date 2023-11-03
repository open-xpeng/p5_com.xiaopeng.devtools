package com.xiaopeng.devtools.view.aftersales;

import android.view.View;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.aftersales.cdu.DiagnosisRecordActivity;
import com.xiaopeng.devtools.view.aftersales.cdu.ModuleDiagnosisActivity;
import com.xiaopeng.devtools.view.can.CanDataCollectActivity;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class AfterSalesActivity extends AfterSalesMenuActivity implements View.OnClickListener {
    private HashMap<Integer, String> DC;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        this.theme = 1;
        this.DC = new HashMap<>();
        if (a.C0041a.dX("UPDATE_NEW_DEVICES_ID")) {
            this.DC.put(1, getString(R.string.update_new_devices_id));
        }
        if (a.C0041a.dX("MODULE_DIAGNOSIS")) {
            this.DC.put(2, getString(R.string.text_module_diagnosis));
        }
        if (a.C0041a.dX("DIAGNOSIS_RECORD")) {
            this.DC.put(3, getString(R.string.diagnosis_error_code));
        }
        if (a.C0041a.dX("ONE_CLICK_DIAGNOSIS")) {
            this.DC.put(4, getString(R.string.one_click_diagnosis));
        }
        if (a.C0041a.dX("VERSION_INFO")) {
            this.DC.put(5, getString(R.string.basic_info));
        }
        if (a.C0041a.dX("CANDATA_COLLECTOR")) {
            this.DC.put(6, getString(R.string.collect_candata));
        }
        if (a.C0041a.dX("REALTIME_STATUS")) {
            this.DC.put(7, getString(R.string.realtime_status));
        }
        if (a.C0041a.dX("DID_INFO")) {
            this.DC.put(8, getString(R.string.did_info));
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
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesMenuActivity, com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case 1:
                e(UpdateDevicesIdActivity.class);
                break;
            case 2:
                e(ModuleDiagnosisActivity.class);
                break;
            case 3:
                a(DiagnosisRecordActivity.class, 1);
                break;
            case 4:
                a(OneClickDiagnosisActivity.class, 1);
                break;
            case 5:
                a(BasicInfoActivity.class, 1);
                break;
            case 6:
                a(CanDataCollectActivity.class, 1);
                break;
            case 7:
                a(RealtimeStatusActivity.class, 1);
                break;
            case 8:
                a(DIDInfoActivity.class, 1);
                break;
        }
        super.onClick(view);
    }
}
