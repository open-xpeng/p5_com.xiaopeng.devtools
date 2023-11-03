package com.xiaopeng.devtools.view.aftersales;

import android.os.Handler;
import android.os.Message;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.xui.app.d;

/* loaded from: classes12.dex */
public class EncryptShActivity extends ActionBarActivity implements View.OnClickListener, c {
    private String AH;
    private com.xiaopeng.devtools.system.a.b CL;
    private com.xiaopeng.xui.app.c DS;
    private Button DY;
    private com.xiaopeng.devtools.presenter.aftersales.e DZ;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.aftersales.EncryptShActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    EncryptShActivity.this.DY.setEnabled(true);
                    EncryptShActivity.this.AH = EncryptShActivity.this.CL.kI();
                    EncryptShActivity.this.DZ.bT(EncryptShActivity.this.AH);
                    return;
                case 2:
                    EncryptShActivity.this.DY.setEnabled(false);
                    EncryptShActivity.this.AH = null;
                    EncryptShActivity.this.DZ.bT(EncryptShActivity.this.AH);
                    return;
                default:
                    return;
            }
        }
    };
    private StorageManager mStorageManager;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_encrypt_sh);
        this.AH = g.V(this);
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
        this.CL = new com.xiaopeng.devtools.system.a.b(this.mHandler);
        this.mStorageManager.registerListener(this.CL);
        this.DZ = new com.xiaopeng.devtools.presenter.aftersales.e(this);
        this.DZ.bT(this.AH);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.DY = (Button) findViewById(R.id.btn_exec_encrypt_sh);
        this.DS = new com.xiaopeng.xui.app.c(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.DY.setEnabled(!TextUtils.isEmpty(this.AH));
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.DY.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.DZ.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_exec_encrypt_sh) {
            this.DZ.ii();
            this.DS.dV(R.string.title_dialog).dW(R.string.confirm_to_run_encrypt_sh).aV(true).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$EncryptShActivity$mrssK2W1ToNpNhpFhNHNgZsevdw
                @Override // com.xiaopeng.xui.app.d.a
                public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                    EncryptShActivity.this.j(cVar, i);
                }
            }).b(getString(R.string.cancel), new d.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$EncryptShActivity$mDSCFk00Q0sShsgR_w1lbENMACk
                @Override // com.xiaopeng.xui.app.d.a
                public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                    cVar.dismiss();
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(com.xiaopeng.xui.app.c cVar, int i) {
        this.DZ.ij();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void cU(String str) {
        this.DS.f(str);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.c
    public void cS(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$EncryptShActivity$EevTJxQ_ZUnjRX05t3euoKJrats
            @Override // java.lang.Runnable
            public final void run() {
                EncryptShActivity.this.cU(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lW() {
        this.DS.dismiss();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.c
    public void dismissDialog() {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$EncryptShActivity$OmUucpBQ0i1ay56ot2VRKKIhM8c
            @Override // java.lang.Runnable
            public final void run() {
                EncryptShActivity.this.lW();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ac(boolean z) {
        this.DS.aX(z);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.c
    public void ab(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$EncryptShActivity$9KrgbfEahPF6DBjXqb9wBx9hiyg
            @Override // java.lang.Runnable
            public final void run() {
                EncryptShActivity.this.ac(z);
            }
        });
    }
}
