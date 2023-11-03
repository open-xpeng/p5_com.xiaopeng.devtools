package com.xiaopeng.devtools.view.modemui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.TboxGsmBand;
import com.xiaopeng.devtools.bean.car.TboxLteBand;
import com.xiaopeng.devtools.bean.car.TboxModemBand;
import com.xiaopeng.devtools.bean.car.TboxUmtsBand;
import com.xiaopeng.devtools.view.ActionBarActivity;

/* loaded from: classes12.dex */
public class TboxBandModeActivity extends ActionBarActivity implements View.OnClickListener, a {
    private ListView Qr;
    private ListView Qs;
    private ListView Qt;
    private Button Qu;
    private ModemBandAdapter Qv;
    private ModemBandAdapter Qw;
    private ModemBandAdapter Qx;
    private com.xiaopeng.devtools.presenter.c.a Qy;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.Qy.onDestroy();
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_band_mode);
        this.Qv = new ModemBandAdapter();
        this.Qw = new ModemBandAdapter();
        this.Qx = new ModemBandAdapter();
        this.Qy = new com.xiaopeng.devtools.presenter.c.a(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Qr = (ListView) findViewById(R.id.lv_gsm_band);
        this.Qs = (ListView) findViewById(R.id.lv_umts_band);
        this.Qt = (ListView) findViewById(R.id.lv_lte_band);
        this.Qu = (Button) findViewById(R.id.btn_set_modem_band);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Qr.setAdapter((ListAdapter) this.Qv);
        this.Qs.setAdapter((ListAdapter) this.Qw);
        this.Qt.setAdapter((ListAdapter) this.Qx);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Qy.iv();
        this.Qu.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.Qy.requestTBoxBandModemStatus();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_set_modem_band) {
            this.Qy.a(new TboxModemBand(this.Qv.getGsmBands(), this.Qw.getUmtsBands(), this.Qx.getLteBands()));
        }
    }

    @Override // com.xiaopeng.devtools.view.modemui.a
    public void a(TboxGsmBand tboxGsmBand) {
        this.Qv.setGsmBand(tboxGsmBand);
        this.Qv.notifyDataSetChanged();
    }

    @Override // com.xiaopeng.devtools.view.modemui.a
    public void a(TboxUmtsBand tboxUmtsBand) {
        this.Qw.setUmtsBand(tboxUmtsBand);
        this.Qw.notifyDataSetChanged();
    }

    @Override // com.xiaopeng.devtools.view.modemui.a
    public void a(TboxLteBand tboxLteBand) {
        this.Qx.setLteBand(tboxLteBand);
        this.Qx.notifyDataSetChanged();
    }
}
