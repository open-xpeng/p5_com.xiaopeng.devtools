package com.xiaopeng.devtools.view.smartdrive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.f.d;
import com.xiaopeng.devtools.view.smartdrive.smartdrive.SmartDriveActivity;

/* loaded from: classes12.dex */
public class ControlPanelActivity extends Activity implements View.OnClickListener, b {
    private Button SZ;
    private Button Ta;
    private d uF;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_control_panel);
        this.uF = new com.xiaopeng.devtools.presenter.f.b(this);
        this.SZ = (Button) findViewById(R.id.control_manual);
        this.Ta = (Button) findViewById(R.id.read_file);
        this.SZ.setOnClickListener(this);
        this.Ta.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.control_manual) {
            startActivity(new Intent(this, CanTestActivity.class));
            finish();
        } else if (id == R.id.read_file) {
            this.uF.hE();
        }
    }

    @Override // com.xiaopeng.devtools.view.smartdrive.b
    public void cj(String str) {
        Intent intent = new Intent(MyApplication.getContext(), SmartDriveActivity.class);
        intent.putExtra("json", str);
        startActivity(intent);
        finish();
    }
}
