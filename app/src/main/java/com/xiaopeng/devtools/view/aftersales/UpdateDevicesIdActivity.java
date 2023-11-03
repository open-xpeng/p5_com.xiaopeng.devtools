package com.xiaopeng.devtools.view.aftersales;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.aftersales.g;
import com.xiaopeng.xui.app.d;

/* loaded from: classes12.dex */
public class UpdateDevicesIdActivity extends AfterSalesBaseActivity implements View.OnClickListener, e {
    private com.xiaopeng.xui.app.c DS;
    private TextView EA;
    private TextView EB;
    private TextView EC;
    private TextView ED;
    private Button EE;
    private Button EF;
    private Button EG;
    private Button EH;
    private Button EI;
    private LinearLayout EJ;
    private LinearLayout EK;
    private LinearLayout EL;
    private LinearLayout EM;
    private LinearLayout EN;
    private g EO;
    private TextView Eu;
    private TextView Ev;
    private TextView Ew;
    private TextView Ex;
    private TextView Ey;
    private TextView Ez;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_change_devices);
        this.EO = new g(this);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.DS = new com.xiaopeng.xui.app.c(this);
        this.Eu = (TextView) findViewById(R.id.iccid_value_header);
        this.Ev = (TextView) findViewById(R.id.update_iccid_result);
        this.EE = (Button) findViewById(R.id.bt_update_iccid);
        this.Ew = (TextView) findViewById(R.id.cduid_value_header);
        this.Ex = (TextView) findViewById(R.id.update_cduid_result);
        this.EF = (Button) findViewById(R.id.bt_update_cduid);
        this.Ey = (TextView) findViewById(R.id.seid_value_header);
        this.Ez = (TextView) findViewById(R.id.update_seid_result);
        this.EG = (Button) findViewById(R.id.bt_update_seid);
        this.EA = (TextView) findViewById(R.id.mac_value_header);
        this.EB = (TextView) findViewById(R.id.update_mac_result);
        this.EH = (Button) findViewById(R.id.bt_update_mac);
        this.EC = (TextView) findViewById(R.id.xpu_value_header);
        this.ED = (TextView) findViewById(R.id.update_xpu_result);
        this.EI = (Button) findViewById(R.id.bt_update_xpu);
        this.EJ = (LinearLayout) findViewById(R.id.layout_iccid);
        this.EK = (LinearLayout) findViewById(R.id.layout_cduid);
        this.EL = (LinearLayout) findViewById(R.id.layout_seid);
        this.EM = (LinearLayout) findViewById(R.id.layout_mac);
        this.EN = (LinearLayout) findViewById(R.id.layout_xpu);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        cY(this.EO.getCduId());
        if (!a.b.getBoolean("SUPPORT_XPU")) {
            this.EN.setVisibility(8);
        }
        if (!a.b.getBoolean("SUPPORT_NFC_KEY")) {
            this.EL.setVisibility(8);
        }
        if (!a.b.getBoolean("SUPPORT_BLE_KEY")) {
            this.EM.setVisibility(8);
        }
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.EF.setOnClickListener(this);
        this.EE.setOnClickListener(this);
        this.EH.setOnClickListener(this);
        this.EG.setOnClickListener(this);
        this.EI.setOnClickListener(this);
        this.EO.iv();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.EO.ix();
        if (a.b.getBoolean("SUPPORT_XPU")) {
            this.EO.iF();
        }
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        cN(view.getId());
    }

    private void cN(final int i) {
        this.DS.dV(R.string.update_new_devices_id).f(cO(i)).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$OSqNBjGPMJ0lUI5MnUHX3QSLqCE
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(com.xiaopeng.xui.app.c cVar, int i2) {
                UpdateDevicesIdActivity.this.a(i, cVar, i2);
            }
        }).b(getString(R.string.cancel), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$Z7L6k-7lNle_DDRdss37hquvgc0
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(com.xiaopeng.xui.app.c cVar, int i2) {
                UpdateDevicesIdActivity.this.o(cVar, i2);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, com.xiaopeng.xui.app.c cVar, int i2) {
        switch (i) {
            case R.id.bt_update_cduid /* 2131296444 */:
                this.EF.setEnabled(false);
                this.Ex.setText(R.string.test_updating);
                this.Ex.setTextColor(getResources().getColor(17170457));
                this.EO.iA();
                break;
            case R.id.bt_update_iccid /* 2131296445 */:
                this.EE.setEnabled(false);
                this.Ev.setText(R.string.test_updating);
                this.Ev.setTextColor(getResources().getColor(17170457));
                this.EO.iB();
                break;
            case R.id.bt_update_mac /* 2131296446 */:
                this.EH.setEnabled(false);
                this.EB.setText(R.string.test_updating);
                this.EB.setTextColor(getResources().getColor(17170457));
                this.EO.iC();
                break;
            case R.id.bt_update_seid /* 2131296448 */:
                this.EG.setEnabled(false);
                this.Ez.setText(R.string.test_updating);
                this.Ez.setTextColor(getResources().getColor(17170457));
                this.EO.iD();
                break;
            case R.id.bt_update_xpu /* 2131296449 */:
                this.EI.setEnabled(false);
                this.ED.setText(R.string.test_updating);
                this.EO.iE();
                break;
        }
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    private String cO(int i) {
        switch (i) {
            case R.id.bt_update_cduid /* 2131296444 */:
                return getString(R.string.confirm_update_device_id, new Object[]{getString(R.string.cduid_header), this.EO.getCduId()});
            case R.id.bt_update_iccid /* 2131296445 */:
                return getString(R.string.confirm_update_device_id, new Object[]{getString(R.string.iccid_header), this.EO.getIccid()});
            case R.id.bt_update_mac /* 2131296446 */:
                return getString(R.string.confirm_update_device_id, new Object[]{getString(R.string.mac_header), this.EO.iz()});
            case R.id.bt_update_pa_status /* 2131296447 */:
            default:
                return "";
            case R.id.bt_update_seid /* 2131296448 */:
                return getString(R.string.confirm_update_device_id, new Object[]{getString(R.string.seid_header), this.EO.iy()});
            case R.id.bt_update_xpu /* 2131296449 */:
                return getString(R.string.confirm_activate, new Object[]{getString(R.string.xpu_header), this.EO.getXpuSn()});
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.EO.onDestroy();
    }

    public void cY(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$ozb7a0XwSA5XfQzefzg7iOqNFGo
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDevicesIdActivity.this.dc(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dc(String str) {
        this.Ew.setText(str);
        this.EF.setEnabled(!TextUtils.isEmpty(str) && this.EO.getVin().length() > 0);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.e
    public void cV(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$4LeeKqsvTysRTV8J-oK76z1Sbpo
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDevicesIdActivity.this.db(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void db(String str) {
        this.Eu.setText(str);
        this.EE.setEnabled(!TextUtils.isEmpty(str) && this.EO.getVin().length() > 0);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.e
    public void cW(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$ygdsnMsDaAgGyKXB_kqfP67_Va0
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDevicesIdActivity.this.da(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void da(String str) {
        this.Ey.setText(str);
        this.EG.setEnabled(!TextUtils.isEmpty(str) && this.EO.getVin().length() > 0);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.e
    public void cX(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$rl_mjabQz4LXGO0u5oPguzdlgok
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDevicesIdActivity.this.cZ(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void cZ(String str) {
        this.EA.setText(str);
        this.EH.setEnabled(!TextUtils.isEmpty(str) && this.EO.getVin().length() > 0);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.e
    public void ad(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$K2UizKxlgd64Vn5nUCy8Ai5vdwY
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDevicesIdActivity.this.al(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void al(boolean z) {
        if (z) {
            this.Ex.setText(R.string.test_succeed);
            this.Ex.setTextColor(getResources().getColor(17170453));
        } else {
            this.Ex.setText(R.string.test_fail);
            this.Ex.setTextColor(getResources().getColor(17170455));
        }
        this.EF.setEnabled(true);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.e
    public void ae(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$iUatw364FklTeQAv1gz_nSFvzgo
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDevicesIdActivity.this.ak(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ak(boolean z) {
        if (z) {
            this.Ev.setText(R.string.test_succeed);
            this.Ev.setTextColor(getResources().getColor(17170453));
        } else {
            this.Ev.setText(R.string.test_fail);
            this.Ev.setTextColor(getResources().getColor(17170455));
        }
        this.EE.setEnabled(true);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.e
    public void af(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$7Rr_j36mcXTDua0KhHIkCMH9WSM
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDevicesIdActivity.this.aj(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void aj(boolean z) {
        if (z) {
            this.Ez.setText(R.string.test_succeed);
            this.Ez.setTextColor(getResources().getColor(17170453));
            this.DS.dV(R.string.notice).f(getString(R.string.tips_nfc_replace_need_reconstrct)).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$oXNixMyNu71JUAlih0pb886HLAs
                @Override // com.xiaopeng.xui.app.d.a
                public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                    UpdateDevicesIdActivity.this.n(cVar, i);
                }
            }).h("").show();
        } else {
            this.Ez.setText(R.string.test_fail);
            this.Ez.setTextColor(getResources().getColor(17170455));
        }
        this.EG.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.e
    public void ag(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$LGraWV1xlOWzCBoehMxAWA4EL4s
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDevicesIdActivity.this.ai(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ai(boolean z) {
        if (z) {
            this.EB.setText(R.string.test_succeed);
            this.EB.setTextColor(getResources().getColor(17170453));
            this.DS.dV(R.string.notice).f(getString(R.string.tips_ble_replace_need_reconstrct)).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$neSiWxfOfNHAZa-3O-w7McAXHgo
                @Override // com.xiaopeng.xui.app.d.a
                public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                    UpdateDevicesIdActivity.this.m(cVar, i);
                }
            }).h("").show();
        } else {
            this.EB.setText(R.string.test_fail);
            this.EB.setTextColor(getResources().getColor(17170455));
        }
        this.EH.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.e
    public void S(final String str, final String str2) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$UpdateDevicesIdActivity$Br6bSdgM-CH8kEQimyGg3JMmFxo
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDevicesIdActivity.this.T(str, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void T(String str, String str2) {
        if (str != null) {
            this.EC.setText(str);
        }
        this.ED.setText(str2);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.e
    public void ah(boolean z) {
        this.EI.setEnabled(z);
    }
}
