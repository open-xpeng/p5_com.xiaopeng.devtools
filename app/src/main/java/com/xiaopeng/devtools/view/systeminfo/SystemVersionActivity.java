package com.xiaopeng.devtools.view.systeminfo;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.g.c;
import com.xiaopeng.devtools.presenter.g.d;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.j;
import java.util.List;

/* loaded from: classes12.dex */
public class SystemVersionActivity extends ActionBarActivity implements b {
    private TextView Hi;
    private ImageView Hl;
    private TextView Ho;
    private com.xiaopeng.devtools.presenter.d.a Ro;
    private TextView TJ;
    private TextView TK;
    private TextView TL;
    private TextView TM;
    private TextView TN;
    private TextView TO;
    private TextView TP;
    private TextView TQ;
    private TextView TT;
    private TextView TU;
    private c uP;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_system_version);
        this.uP = new d(this);
        this.Ro = new com.xiaopeng.devtools.presenter.d.a();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Hi = (TextView) findViewById(R.id.tv_device_code);
        this.Ho = (TextView) findViewById(R.id.tv_vehicle_id);
        this.Hl = (ImageView) findViewById(R.id.iv_device_code);
        this.TN = (TextView) findViewById(R.id.tv_vin);
        this.TJ = (TextView) findViewById(R.id.tv_hardware_version);
        this.TK = (TextView) findViewById(R.id.tv_system_version);
        this.TL = (TextView) findViewById(R.id.tv_mcu_version);
        this.TO = (TextView) findViewById(R.id.tv_iccid);
        this.TP = (TextView) findViewById(R.id.tv_tbox_version);
        this.TQ = (TextView) findViewById(R.id.tv_tmcu_version);
        this.TT = (TextView) findViewById(R.id.tv_vmap_create_time);
        this.TU = (TextView) findViewById(R.id.tv_vmap_version);
        this.TM = (TextView) findViewById(R.id.tv_device_operator);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Hi.setText(getString(R.string.text_device_code, new Object[]{this.uP.fR()}));
        this.uP.a(this.uP.fR(), this.Hl);
        this.TJ.setText(getString(R.string.text_hw_info, new Object[]{this.uP.hJ()}));
        this.TK.setText(getString(R.string.text_system_info, new Object[]{this.uP.getSystemVersion()}));
        this.TL.setText(getString(R.string.text_mcu_info, new Object[]{this.uP.getMcuVersion()}));
        this.TO.setText(getString(R.string.text_iccid, new Object[]{this.uP.getIccid()}));
        this.TP.setText(getString(R.string.text_tbox_info, new Object[]{r.lr()}));
        if (a.b.getBoolean("SUPPORT_TMCU")) {
            this.TQ.setText(getString(R.string.text_tmcu_info, new Object[]{r.ls()}));
        } else {
            this.TQ.setVisibility(8);
        }
        this.Ho.setText(getString(R.string.text_vehicle_id, new Object[]{this.uP.getVehicleId()}));
        this.TN.setText(getString(R.string.text_vin, new Object[]{this.uP.eH()}));
        this.TM.setText(getString(R.string.text_device_operator, new Object[]{"LOADING..."}));
        nV();
    }

    private void nV() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.systeminfo.-$$Lambda$SystemVersionActivity$nfP5odNBdMaQFTbZua2KPfsW4FE
            @Override // java.lang.Runnable
            public final void run() {
                SystemVersionActivity.this.nW();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void nW() {
        final String jL = this.Ro.jL();
        final String jM = this.Ro.jM();
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.systeminfo.-$$Lambda$SystemVersionActivity$aLs0E6sMfub1ypSooxF82hK5qvA
            @Override // java.lang.Runnable
            public final void run() {
                SystemVersionActivity.this.W(jL, jM);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void W(String str, String str2) {
        this.TU.setText(getString(R.string.text_vmap_info, new Object[]{str}));
        this.TT.setText(getString(R.string.text_vmap_create_time, new Object[]{str2}));
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.uP.kd();
    }

    @Override // com.xiaopeng.devtools.view.systeminfo.b
    public void u(List<PackageInfo> list) {
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.uP.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dx(String str) {
        this.TM.setText(getString(R.string.text_device_operator, new Object[]{str}));
    }

    @Override // com.xiaopeng.devtools.view.systeminfo.b
    public void du(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.systeminfo.-$$Lambda$SystemVersionActivity$MJebklBknqYbkPnq0-OHhDAuEMM
            @Override // java.lang.Runnable
            public final void run() {
                SystemVersionActivity.this.dx(str);
            }
        });
    }
}
