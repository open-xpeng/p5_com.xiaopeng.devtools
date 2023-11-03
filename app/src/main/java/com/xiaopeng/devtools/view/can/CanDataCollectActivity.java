package com.xiaopeng.devtools.view.can;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.model.can.CanCollectModel;
import com.xiaopeng.devtools.system.a.b;
import com.xiaopeng.devtools.system.service.CanDataCollectService;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.u;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.c;
import com.xiaopeng.xui.app.d;
import java.io.File;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class CanDataCollectActivity extends ActionBarActivity implements View.OnClickListener {
    private String AH;
    private b CL;
    private c DS;
    private CheckBox Fn;
    private CheckBox Fo;
    private CheckBox Fp;
    private CheckBox Fq;
    private CheckBox Fr;
    private CheckBox Fs;
    private Button Ft;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.can.CanDataCollectActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    CanDataCollectActivity.this.AH = CanDataCollectActivity.this.CL.kI();
                    CanDataCollectActivity.this.mn();
                    return;
                case 2:
                    CanDataCollectActivity.this.AH = null;
                    CanDataCollectActivity.this.mn();
                    return;
                default:
                    return;
            }
        }
    };
    private StorageManager mStorageManager;
    private CanCollectModel vg;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        if (num.intValue() == 4) {
            com.xiaopeng.lib.utils.c.f("CanDataCollectActivity", "POST_CAN_COLLECT_STATUS_CHANGED");
            mm();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_candata_collect);
        this.vg = CanCollectModel.fo();
        this.AH = g.V(this);
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
        this.CL = new b(this.mHandler);
        this.mStorageManager.registerListener(this.CL);
        this.DS = new c(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Fn = (CheckBox) findViewById(R.id.ican);
        this.Fo = (CheckBox) findViewById(R.id.dcan);
        this.Fp = (CheckBox) findViewById(R.id.bcan);
        this.Fq = (CheckBox) findViewById(R.id.ccan);
        this.Fr = (CheckBox) findViewById(R.id.ecan);
        this.Fs = (CheckBox) findViewById(R.id.adcan);
        this.Ft = (Button) findViewById(R.id.bt_collect_candata);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Fn.setChecked(this.vg.bU(CanCollectModel.CanData.ICAN.ordinal()));
        this.Fo.setChecked(this.vg.bU(CanCollectModel.CanData.DCAN.ordinal()));
        this.Fp.setChecked(this.vg.bU(CanCollectModel.CanData.BCAN.ordinal()));
        this.Fq.setChecked(this.vg.bU(CanCollectModel.CanData.CCAN.ordinal()));
        this.Fr.setChecked(this.vg.bU(CanCollectModel.CanData.ECAN.ordinal()));
        this.Fs.setChecked(this.vg.bU(CanCollectModel.CanData.ADCAN.ordinal()));
        this.Fn.setVisibility(a.C0041a.dX("ICAN") ? 0 : 8);
        this.Fo.setVisibility(a.C0041a.dX("DCAN") ? 0 : 8);
        this.Fp.setVisibility(a.C0041a.dX("BCAN") ? 0 : 8);
        this.Fq.setVisibility(a.C0041a.dX("CCAN") ? 0 : 8);
        this.Fr.setVisibility(a.C0041a.dX("ECAN") ? 0 : 8);
        this.Fs.setVisibility(a.C0041a.dX("ADCAN") ? 0 : 8);
        mn();
        mm();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Fn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$PQXiYilGWjQ-XStgJhWO2Wb7P9k
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CanDataCollectActivity.this.g(compoundButton, z);
            }
        });
        this.Fo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$-OUmqsmGbjB2s-bLzr_TcaKT3g8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CanDataCollectActivity.this.f(compoundButton, z);
            }
        });
        this.Fp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$mvDyXDtWWsZhukOxwd2FmrIH6gI
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CanDataCollectActivity.this.e(compoundButton, z);
            }
        });
        this.Fq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$Cch2HjwCT8Q9_54YqGS8VGtvg4g
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CanDataCollectActivity.this.d(compoundButton, z);
            }
        });
        this.Fr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$wnKhbLVGXVPABCFQ2sPGZ_ctNws
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CanDataCollectActivity.this.c(compoundButton, z);
            }
        });
        this.Fs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$EzpnZAFB_s3wc10nC8Xr04bcRZo
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CanDataCollectActivity.this.b(compoundButton, z);
            }
        });
        this.Ft.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(CompoundButton compoundButton, boolean z) {
        this.vg.c(CanCollectModel.CanData.ICAN.ordinal(), z);
        mn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(CompoundButton compoundButton, boolean z) {
        this.vg.c(CanCollectModel.CanData.DCAN.ordinal(), z);
        mn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(CompoundButton compoundButton, boolean z) {
        this.vg.c(CanCollectModel.CanData.BCAN.ordinal(), z);
        mn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(CompoundButton compoundButton, boolean z) {
        this.vg.c(CanCollectModel.CanData.CCAN.ordinal(), z);
        mn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(CompoundButton compoundButton, boolean z) {
        this.vg.c(CanCollectModel.CanData.ECAN.ordinal(), z);
        mn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(CompoundButton compoundButton, boolean z) {
        this.vg.c(CanCollectModel.CanData.ADCAN.ordinal(), z);
        mn();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.bt_collect_candata || !u.lF()) {
            return;
        }
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$d7yYsumyMZL9KQZF3WpWFCa15jc
            @Override // java.lang.Runnable
            public final void run() {
                CanDataCollectActivity.this.mp();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void mp() {
        Intent intent = new Intent(MyApplication.getContext(), CanDataCollectService.class);
        if (this.vg.fq()) {
            am(false);
            stopService(intent);
            return;
        }
        recordRepairModeAction("collect candata", "triggered");
        this.vg.ft();
        am(true);
        if (TextUtils.isEmpty(this.AH)) {
            this.AH = this.CL.kI();
        }
        String str = this.AH + File.separatorChar + "canData" + File.separatorChar;
        g.aW(str);
        if (g.cA(str) > CanCollectModel.rq.longValue()) {
            com.xiaopeng.lib.utils.c.f("CanDataCollectActivity", "startServiceAsUser storagePath: " + str);
            this.vg.bT(10002);
            intent.putExtra("storagePath", str);
            startService(intent);
            return;
        }
        ml();
    }

    private void ml() {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$aAcrM9ACNOApKKzhTUOqc7BwKnk
            @Override // java.lang.Runnable
            public final void run() {
                CanDataCollectActivity.this.mo();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void mo() {
        Toast.makeText(MyApplication.getContext(), (int) R.string.make_sure_Udisk_size, 1).show();
        this.Ft.setText(R.string.collect_candata_to_udisk);
    }

    private void am(final boolean z) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$SyaQBHBcqhtcX4iNGME11uJG2kw
            @Override // java.lang.Runnable
            public final void run() {
                CanDataCollectActivity.this.ao(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ao(boolean z) {
        if (z) {
            this.Ft.setText(R.string.starting_collecting_candata_to_udisk);
        } else {
            this.Ft.setText(R.string.stoping_collecting_candata_to_udisk);
        }
    }

    private void mm() {
        if (this.vg.fq()) {
            this.Ft.setText(R.string.collecting_candata_to_udisk);
            an(false);
            return;
        }
        this.Ft.setText(R.string.collect_candata_to_udisk);
        an(true);
        String errorMsg = this.vg.getErrorMsg();
        if (errorMsg != null) {
            if (this.DS.isShowing()) {
                this.DS.dismiss();
            }
            this.DS.dV(R.string.title_dialog).f(errorMsg).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.can.-$$Lambda$CanDataCollectActivity$aUgcHWFTs7-5zzz-EZNw4YUAMt8
                @Override // com.xiaopeng.xui.app.d.a
                public final void onClick(c cVar, int i) {
                    CanDataCollectActivity.this.p(cVar, i);
                }
            }).h("").show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(c cVar, int i) {
        this.vg.ft();
    }

    private void an(boolean z) {
        this.Fn.setEnabled(z);
        this.Fo.setEnabled(z);
        this.Fp.setEnabled(z);
        this.Fq.setEnabled(z);
        this.Fr.setEnabled(z);
        this.Fs.setEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mn() {
        this.Ft.setEnabled(this.vg.fr() && !(TextUtils.isEmpty(this.AH) && TextUtils.isEmpty(this.CL.kI())));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
