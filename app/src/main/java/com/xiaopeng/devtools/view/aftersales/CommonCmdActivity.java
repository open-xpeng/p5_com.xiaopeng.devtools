package com.xiaopeng.devtools.view.aftersales;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.app.g;

/* loaded from: classes12.dex */
public class CommonCmdActivity extends ActionBarActivity implements View.OnClickListener, b {
    private EditText DD;
    private EditText DE;
    private EditText DF;
    private EditText DG;
    private EditText DH;
    private Button DI;
    private Button DJ;
    private Button DK;
    private Button DL;
    private Button DM;
    private Button DN;
    private Button DO;
    private Button DQ;
    private Button DR;
    private com.xiaopeng.xui.app.c DS;
    private com.xiaopeng.devtools.presenter.aftersales.b DT;
    private String DU;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_common_cmd);
        this.DT = new com.xiaopeng.devtools.presenter.aftersales.b(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.DS = new com.xiaopeng.xui.app.c(this, 2131821107);
        this.DI = (Button) findViewById(R.id.bt_getprop);
        this.DD = (EditText) findViewById(R.id.et_prop);
        this.DJ = (Button) findViewById(R.id.bt_readfile);
        this.DE = (EditText) findViewById(R.id.et_readfilepath);
        this.DK = (Button) findViewById(R.id.bt_blockinfo);
        this.DL = (Button) findViewById(R.id.bt_mount);
        this.DF = (EditText) findViewById(R.id.et_dirinfo);
        this.DM = (Button) findViewById(R.id.bt_dirinfo);
        this.DG = (EditText) findViewById(R.id.et_cuptop);
        this.DN = (Button) findViewById(R.id.bt_cuptop);
        this.DO = (Button) findViewById(R.id.bt_memtop);
        this.DH = (EditText) findViewById(R.id.et_ls_al);
        this.DQ = (Button) findViewById(R.id.bt_ls_al);
        this.DR = (Button) findViewById(R.id.bt_ifconfig);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.DI.setOnClickListener(this);
        this.DJ.setOnClickListener(this);
        this.DK.setOnClickListener(this);
        this.DL.setOnClickListener(this);
        this.DM.setOnClickListener(this);
        this.DN.setOnClickListener(this);
        this.DO.setOnClickListener(this);
        this.DQ.setOnClickListener(this);
        this.DR.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_blockinfo /* 2131296410 */:
                this.DU = getString(R.string.block_info);
                this.DS.e(this.DU).dW(R.string.trying_to_read).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$xNjU_sNzVOzSs_zieMOnk_91Iyo
                    @Override // com.xiaopeng.xui.app.d.a
                    public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                        CommonCmdActivity.this.g(cVar, i);
                    }
                }).h("").show();
                this.DT.ia();
                return;
            case R.id.bt_cuptop /* 2131296419 */:
                String obj = this.DG.getText().toString();
                obj = (obj == null || obj.length() <= 0) ? "" : "";
                this.DU = getString(R.string.cputop_info_with_param, new Object[]{obj});
                this.DS.e(this.DU).dW(R.string.trying_to_read).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$wHolMx-koL_JzfXyd86VtD0-0Gw
                    @Override // com.xiaopeng.xui.app.d.a
                    public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                        CommonCmdActivity.this.d(cVar, i);
                    }
                }).h("").show();
                this.DT.bR(obj);
                return;
            case R.id.bt_dirinfo /* 2131296420 */:
                String obj2 = this.DF.getText().toString();
                obj2 = (obj2 == null || obj2.length() <= 0) ? "" : "";
                this.DU = getString(R.string.dir_info_with_param, new Object[]{obj2});
                this.DS.e(this.DU).dW(R.string.trying_to_read).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$X3tkOEvlquMBrjmGlnZfNVokB2k
                    @Override // com.xiaopeng.xui.app.d.a
                    public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                        CommonCmdActivity.this.e(cVar, i);
                    }
                }).h("").show();
                this.DT.bQ(obj2);
                return;
            case R.id.bt_getprop /* 2131296428 */:
                String obj3 = this.DD.getText().toString();
                if (obj3 != null && obj3.length() > 0) {
                    this.DU = getString(R.string.get_prop_with_param, new Object[]{obj3});
                    this.DS.e(this.DU).dW(R.string.trying_to_read).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$oaiLcXnLjQJcK721g7ZZSutWGCQ
                        @Override // com.xiaopeng.xui.app.d.a
                        public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                            CommonCmdActivity.this.j(cVar, i);
                        }
                    }).h("").show();
                    this.DT.bO(obj3);
                    return;
                }
                g.show(R.string.tips_input_prop_name);
                return;
            case R.id.bt_ifconfig /* 2131296430 */:
                this.DU = getString(R.string.ifconfig_info);
                this.DS.e(this.DU).dW(R.string.trying_to_read).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$jk_KSDJHhBLZRcXsF07HnSc59pU
                    @Override // com.xiaopeng.xui.app.d.a
                    public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                        CommonCmdActivity.this.b(cVar, i);
                    }
                }).h("").show();
                this.DT.ie();
                return;
            case R.id.bt_ls_al /* 2131296432 */:
                String obj4 = this.DH.getText().toString();
                obj4 = (obj4 == null || obj4.length() <= 0) ? "" : "";
                this.DU = getString(R.string.ls_al_with_param, new Object[]{obj4});
                this.DS.e(this.DU).dW(R.string.trying_to_read).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$YbPzcbNT7RyAFu_mHM8i9ZqQJfg
                    @Override // com.xiaopeng.xui.app.d.a
                    public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                        CommonCmdActivity.this.i(cVar, i);
                    }
                }).h("").show();
                this.DT.bS(obj4);
                return;
            case R.id.bt_memtop /* 2131296433 */:
                this.DU = getString(R.string.memtop_info);
                this.DS.e(this.DU).dW(R.string.trying_to_read).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$HPT7Pun7FfDjGmLZoYkb3WS_4ls
                    @Override // com.xiaopeng.xui.app.d.a
                    public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                        CommonCmdActivity.this.c(cVar, i);
                    }
                }).h("").show();
                this.DT.ic();
                return;
            case R.id.bt_mount /* 2131296434 */:
                this.DU = getString(R.string.mount_info);
                this.DS.e(this.DU).dW(R.string.trying_to_read).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$eT7hRZeMEGKg4AyxQhstV7CZVzc
                    @Override // com.xiaopeng.xui.app.d.a
                    public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                        CommonCmdActivity.this.f(cVar, i);
                    }
                }).h("").show();
                this.DT.ib();
                return;
            case R.id.bt_readfile /* 2131296435 */:
                String obj5 = this.DE.getText().toString();
                if (obj5 != null && obj5.length() > 0) {
                    this.DU = getString(R.string.read_file_with_param, new Object[]{obj5});
                    this.DS.e(this.DU).dW(R.string.trying_to_read).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$lcjbviMFyktD1UG9LvDLaIzc-lA
                        @Override // com.xiaopeng.xui.app.d.a
                        public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                            CommonCmdActivity.this.h(cVar, i);
                        }
                    }).h("").show();
                    this.DT.bP(obj5);
                    return;
                }
                g.show(R.string.tips_input_read_file);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(com.xiaopeng.xui.app.c cVar, int i) {
        this.DS.dismiss();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.b
    public void cS(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$CommonCmdActivity$sowKqRlBa-4G1ZC4qh2FR1GkYBc
            @Override // java.lang.Runnable
            public final void run() {
                CommonCmdActivity.this.cT(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void cT(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) this.DU);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF8800")), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new StyleSpan(1), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append((CharSequence) "\r\n\r\n");
        spannableStringBuilder.append((CharSequence) str);
        this.DS.f(spannableStringBuilder);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.DT.onDestroy();
    }
}
