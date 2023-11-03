package com.xiaopeng.devtools.view.smartdrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.smartdrive.adas.AdasCollectActivity;
import com.xiaopeng.devtools.view.smartdrive.bcan.BCanCollectActivity;
import com.xiaopeng.devtools.view.smartdrive.smartdrive.SmartDriveActivity;

/* loaded from: classes12.dex */
public class DriveAllTestActivity extends ActionBarActivity implements View.OnClickListener {
    private Button Tb;
    private Button Tc;
    private Button Td;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.layout_drive_all_test);
        this.Tb = (Button) findViewById(R.id.btn_drive_record);
        this.Tc = (Button) findViewById(R.id.btn_bcan_collect);
        this.Td = (Button) findViewById(R.id.btn_adas_record);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Tb.setOnClickListener(this);
        this.Tc.setOnClickListener(this);
        this.Td.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_adas_record) {
            d(AdasCollectActivity.class);
        } else if (id == R.id.btn_bcan_collect) {
            d(BCanCollectActivity.class);
        } else if (id == R.id.btn_drive_record) {
            d(SmartDriveActivity.class);
        }
    }

    private void d(Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(67108864);
        startActivity(intent);
    }
}
