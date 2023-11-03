package com.xiaopeng.devtools.view.modemui;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.TboxApn;
import com.xiaopeng.devtools.view.ActionBarActivity;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class TboxNetworkInfoActivity extends ActionBarActivity implements b {
    private TextView QA;
    private ListView QB;
    private NeighbourNetworkAdapter QC;
    private com.xiaopeng.devtools.presenter.c.b QD;
    private TextView Qz;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_network_info);
        this.QC = new NeighbourNetworkAdapter();
        this.QD = new com.xiaopeng.devtools.presenter.c.b(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.QD.onDestroy();
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Qz = (TextView) findViewById(R.id.apn_info);
        this.QA = (TextView) findViewById(R.id.current_network_info);
        this.QB = (ListView) findViewById(R.id.lv_neighbours_signal);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.QB.setAdapter((ListAdapter) this.QC);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.QD.iv();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.QD.requestTBoxModemStatus();
    }

    @Override // com.xiaopeng.devtools.view.modemui.b
    public void a(TboxApn tboxApn) {
        this.Qz.setText(tboxApn.toString());
    }

    @Override // com.xiaopeng.devtools.view.modemui.b
    public void ch(String str) {
        this.QA.setText(str);
    }

    @Override // com.xiaopeng.devtools.view.modemui.b
    public void a(ArrayList<String> arrayList) {
        this.QC.setNeighbourNetwork(arrayList);
        this.QC.notifyDataSetChanged();
    }
}
