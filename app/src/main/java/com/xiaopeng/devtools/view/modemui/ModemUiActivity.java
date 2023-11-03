package com.xiaopeng.devtools.view.modemui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBarActivity;

/* loaded from: classes12.dex */
public class ModemUiActivity extends ActionBarActivity implements View.OnClickListener {
    private Button Qn;
    private Button Qo;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_modem_ui);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Qn = (Button) findViewById(R.id.btn_network_info);
        this.Qo = (Button) findViewById(R.id.btn_band_mode);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Qn.setOnClickListener(this);
        this.Qo.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_band_mode) {
            d(TboxBandModeActivity.class);
        } else if (id == R.id.btn_network_info) {
            d(TboxNetworkInfoActivity.class);
        }
    }

    private void d(Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(67108864);
        startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
