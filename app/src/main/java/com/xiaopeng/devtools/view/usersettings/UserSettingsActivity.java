package com.xiaopeng.devtools.view.usersettings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.i.b;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.xui.app.e;

/* loaded from: classes12.dex */
public class UserSettingsActivity extends ActionBarActivity implements View.OnClickListener, a {
    private com.xiaopeng.devtools.presenter.i.a Qa;
    private CheckBox UC;
    private CheckBox UD;
    private CheckBox UE;
    private CheckBox UF;
    private Button UG;
    private e pe;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.Qa = new b(this);
        this.UC = (CheckBox) findViewById(R.id.showDialog);
        this.UC.setChecked(r.lu());
        this.UC.setOnClickListener(this);
        this.UD = (CheckBox) findViewById(R.id.debug);
        this.UD.setChecked(this.Qa.hM());
        this.UD.setOnClickListener(this);
        this.UE = (CheckBox) findViewById(R.id.wifi_debug);
        this.UE.setChecked(this.Qa.kG());
        dy(this.Qa.kH());
        this.UE.setOnClickListener(this);
        this.UF = (CheckBox) findViewById(R.id.udisk_readonly);
        this.UF.setChecked(this.Qa.hL());
        this.UF.setOnClickListener(this);
        this.UG = (Button) findViewById(R.id.bt_grab_upload_can);
        this.UG.setOnClickListener(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.user_settings);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_grab_upload_can /* 2131296429 */:
                this.Qa.bH("10");
                return;
            case R.id.debug /* 2131296666 */:
                if (view instanceof CheckBox) {
                    boolean isChecked = ((CheckBox) view).isChecked();
                    dN(R.string.switching_debugmode);
                    this.Qa.setDebugStatus(isChecked);
                    return;
                }
                return;
            case R.id.showDialog /* 2131297078 */:
                this.Qa.H(((CheckBox) view).isChecked());
                return;
            case R.id.udisk_readonly /* 2131297347 */:
                if (view instanceof CheckBox) {
                    dN(R.string.switching_udisk_mount_mode);
                    this.Qa.F(((CheckBox) view).isChecked());
                    return;
                }
                return;
            case R.id.wifi_debug /* 2131297378 */:
                if (view instanceof CheckBox) {
                    dN(R.string.switching_wifi_debugmode);
                    this.Qa.N(((CheckBox) view).isChecked());
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.usersettings.a
    public void oa() {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.usersettings.UserSettingsActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (UserSettingsActivity.this.pe != null) {
                    UserSettingsActivity.this.pe.dismiss();
                }
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.usersettings.a
    public void dy(final String str) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.usersettings.-$$Lambda$UserSettingsActivity$CpkE_DRsoJj9nXoxDyWEiXNv1fc
            @Override // java.lang.Runnable
            public final void run() {
                UserSettingsActivity.this.dz(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dz(String str) {
        this.UE.setText(getString(R.string.wifi_debug, new Object[]{str}));
    }

    private void dN(int i) {
        if (this.pe != null) {
            this.pe.dismiss();
        }
        this.pe = e.a(this, getString(i), true, new DialogInterface.OnCancelListener() { // from class: com.xiaopeng.devtools.view.usersettings.UserSettingsActivity.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                c.g("UserSettingsActivity", "showLoadingDialog onCancel");
            }
        });
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.Qa.onDestroy();
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }
}
