package com.xiaopeng.devtools.devdebug.drivemode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.devdebug.drivemode.TabView;
import com.xiaopeng.devtools.utils.q;
import com.xiaopeng.devtools.view.ActionBarActivity;

/* loaded from: classes12.dex */
public class DriveModeActivity extends ActionBarActivity implements SharedPreferences.OnSharedPreferenceChangeListener, TabView.a, c {
    private TabView qA;
    private b qz;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_drive_mode);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.qA = (TabView) findViewById(R.id.tab_drive_mode);
        this.qA.setTitle(getResources().getStringArray(R.array.drive_mode));
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.qA.setOnTabSelectedListener(this);
        this.qz = new b(this);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        q.lg().lh().registerOnSharedPreferenceChangeListener(this);
        this.qz.fh();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        q.lg().lh().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.qz.onDestroy();
    }

    @Override // com.xiaopeng.devtools.devdebug.drivemode.c
    public void bO(int i) {
        com.xiaopeng.lib.utils.c.f("DriveModeActivity", "updateDriveMode mode = " + i);
        switch (i) {
            case 1:
                this.qA.b(0, true);
                return;
            case 2:
                this.qA.b(2, true);
                return;
            case 3:
                this.qA.b(3, true);
                return;
            case 4:
                this.qA.b(1, true);
                return;
            default:
                return;
        }
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("pref_select_drive_mode".equals(str)) {
            int i = sharedPreferences.getInt("pref_select_drive_mode", 1);
            com.xiaopeng.lib.utils.c.e("DriveModeActivity", "onSharedPreferenceChanged mode =" + i);
            this.qz.bQ(i);
        }
    }

    @Override // com.xiaopeng.devtools.devdebug.drivemode.TabView.a
    public void bP(int i) {
        int i2;
        com.xiaopeng.lib.utils.c.e("DriveModeActivity", "onTabSelected=" + i);
        switch (i) {
            case 0:
                i2 = 1;
                break;
            case 1:
                i2 = 4;
                break;
            case 2:
                i2 = 2;
                break;
            default:
                i2 = 3;
                break;
        }
        Intent intent = new Intent("com.xiaopeng.action.SET_DRIVE_MODEFROM_FACTORYTEST");
        intent.putExtra("extra_boolean_remote_set_drive_mode_which", i2);
        sendBroadcast(intent);
    }
}
