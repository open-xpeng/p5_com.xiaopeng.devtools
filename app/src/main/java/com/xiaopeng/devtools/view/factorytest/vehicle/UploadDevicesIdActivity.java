package com.xiaopeng.devtools.view.factorytest.vehicle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.c.b;
import com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity;
import com.xiaopeng.xui.app.c;
import com.xiaopeng.xui.app.d;

/* loaded from: classes12.dex */
public class UploadDevicesIdActivity extends AfterSalesBaseActivity implements View.OnClickListener, a {
    private c DS;
    private TextView Pw;
    private TextView Px;
    private Button Py;
    private com.xiaopeng.devtools.presenter.factorytest.c.a Pz;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_upload_devices);
        this.Pz = new b(this);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.DS = new c(this);
        this.Pw = (TextView) findViewById(R.id.mac_value_header);
        this.Px = (TextView) findViewById(R.id.update_mac_result);
        this.Py = (Button) findViewById(R.id.bt_update_mac);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Py.setOnClickListener(this);
        this.Pz.iv();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        dr(view.getId());
    }

    private void dr(final int i) {
        this.DS.dV(R.string.update_new_devices_id).f(cO(i)).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.factorytest.vehicle.-$$Lambda$UploadDevicesIdActivity$Y9sYKTsw48JIurUx9LN-eTLRomE
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(c cVar, int i2) {
                UploadDevicesIdActivity.this.b(i, cVar, i2);
            }
        }).b(getString(R.string.cancel), new d.a() { // from class: com.xiaopeng.devtools.view.factorytest.vehicle.-$$Lambda$UploadDevicesIdActivity$-r5JZYjvm3l7pZlNU6pWW_O6bCU
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(c cVar, int i2) {
                UploadDevicesIdActivity.this.u(cVar, i2);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, c cVar, int i2) {
        if (i == R.id.bt_update_mac) {
            this.Py.setEnabled(false);
            this.Px.setText(R.string.test_updating);
            this.Px.setTextColor(getResources().getColor(17170457));
            this.Pz.jG();
        }
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(c cVar, int i) {
        this.DS.dismiss();
    }

    private String cO(int i) {
        if (i != R.id.bt_update_mac) {
            return "";
        }
        return getString(R.string.confirm_update_device_id, new Object[]{getString(R.string.mac_header), this.Pz.iz()});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        com.xiaopeng.lib.utils.c.f("UploadDevicesIdActivity", "onResume");
        super.onResume();
        this.Pz.ix();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Pz.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.vehicle.a
    public void cX(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.vehicle.-$$Lambda$UploadDevicesIdActivity$pu0hv-sSeOYduaRTwszAqc0O9Gc
            @Override // java.lang.Runnable
            public final void run() {
                UploadDevicesIdActivity.this.m152do(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: do  reason: not valid java name */
    public /* synthetic */ void m152do(String str) {
        this.Pw.setText(str);
        this.Py.setEnabled(!TextUtils.isEmpty(str));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.vehicle.a
    public void aC(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.vehicle.-$$Lambda$UploadDevicesIdActivity$ztmMVhOjHLfx1fnLEHOc0fVftUg
            @Override // java.lang.Runnable
            public final void run() {
                UploadDevicesIdActivity.this.aD(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void aD(boolean z) {
        if (z) {
            this.Px.setText(R.string.test_succeed);
            this.Px.setTextColor(getResources().getColor(17170453));
            this.DS.dV(R.string.notice).f(getString(R.string.tips_ble_replace_need_reconstrct)).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.factorytest.vehicle.-$$Lambda$UploadDevicesIdActivity$n2B5BpKHKDgGZUuS-vqscNkhXyo
                @Override // com.xiaopeng.xui.app.d.a
                public final void onClick(c cVar, int i) {
                    UploadDevicesIdActivity.this.t(cVar, i);
                }
            }).h("").show();
        } else {
            this.Px.setText(R.string.test_fail);
            this.Px.setTextColor(getResources().getColor(17170455));
        }
        this.Py.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(c cVar, int i) {
        this.DS.dismiss();
    }
}
