package com.xiaopeng.devtools.view.systeminfo;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.g.c;
import com.xiaopeng.devtools.presenter.g.d;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import java.util.List;

/* loaded from: classes12.dex */
public class SystemInfoActivity extends ActionBarActivity implements b {
    private static final String TAG = SystemInfoActivity.class.getSimpleName();
    private TextView Hi;
    private TextView Hj;
    private TextView Ho;
    private ListView TB;
    private AppVersionAdapter TC;
    private TextView TJ;
    private TextView TK;
    private TextView TL;
    private TextView TM;
    private c uP;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_system_info);
        this.uP = new d(this);
        this.TC = new AppVersionAdapter();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Hi = (TextView) findViewById(R.id.tv_device_code);
        this.Ho = (TextView) findViewById(R.id.tv_vehicle_id);
        this.TJ = (TextView) findViewById(R.id.tv_hardware_version);
        this.TK = (TextView) findViewById(R.id.tv_system_version);
        this.TL = (TextView) findViewById(R.id.tv_mcu_version);
        this.TB = (ListView) findViewById(R.id.lv_app_version);
        this.Hj = (TextView) findViewById(R.id.tv_device_number);
        this.TM = (TextView) findViewById(R.id.tv_device_operator);
        this.uP.hK();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Hi.setText(getString(R.string.text_device_code, new Object[]{this.uP.fR()}));
        this.TJ.setText(getString(R.string.text_hw_info, new Object[]{this.uP.hJ()}));
        this.TK.setText(getString(R.string.text_system_info, new Object[]{this.uP.getSystemVersion()}));
        this.TL.setText(getString(R.string.text_mcu_info, new Object[]{this.uP.getMcuVersion()}));
        this.Ho.setText(getString(R.string.text_vehicle_id, new Object[]{this.uP.getVehicleId()}));
        String iccid = r.getIccid();
        TextView textView = this.Hj;
        Object[] objArr = new Object[1];
        if (TextUtils.isEmpty(iccid)) {
            iccid = getString(R.string.get_device_number_fail);
        }
        objArr[0] = iccid;
        textView.setText(getString(R.string.text_device_number, objArr));
        this.TM.setText(getString(R.string.text_device_operator, new Object[]{"LOADING..."}));
        this.TB.setAdapter((ListAdapter) this.TC);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.xiaopeng.devtools.view.systeminfo.b
    public void u(List<PackageInfo> list) {
        this.TC.setPackageInfos(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dw(String str) {
        this.TM.setText(getString(R.string.text_device_operator, new Object[]{str}));
    }

    @Override // com.xiaopeng.devtools.view.systeminfo.b
    public void du(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.systeminfo.-$$Lambda$SystemInfoActivity$d9n544wSWQKgdn8gk8oDOJgSgJw
            @Override // java.lang.Runnable
            public final void run() {
                SystemInfoActivity.this.dw(str);
            }
        });
    }
}
