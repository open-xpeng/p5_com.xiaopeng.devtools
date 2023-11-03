package com.xiaopeng.devtools.view.signal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import com.kyleduo.switchbutton.SwitchButton;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.system.service.CollectionService;
import com.xiaopeng.devtools.utils.q;
import com.xiaopeng.devtools.view.ActionBarActivity;

/* loaded from: classes12.dex */
public class SignalShowActivity extends ActionBarActivity {
    private SwitchButton Gh;
    private CollectionService SA;
    private boolean Sy;
    private Button Sz;
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.xiaopeng.devtools.view.signal.SignalShowActivity.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SignalShowActivity.this.Sy = true;
            SignalShowActivity.this.SA = ((CollectionService.a) iBinder).kR();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            SignalShowActivity.this.Sy = false;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent(this, CollectionService.class);
        if (!this.Sy) {
            bindServiceAsUser(intent, this.mServiceConnection, 1, UserHandle.CURRENT);
        }
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_signal_show);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Sz = (Button) findViewById(R.id.bt_sync);
        this.Gh = (SwitchButton) findViewById(R.id.switch_sync);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Sz.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.signal.SignalShowActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SignalShowActivity.this.SA != null) {
                    SignalShowActivity.this.SA.kQ();
                }
            }
        });
        this.Gh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.signal.SignalShowActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (SignalShowActivity.this.SA != null) {
                    SignalShowActivity.this.SA.R(z);
                }
                q.lg().putBoolean("k_s_d", z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.Sy) {
            unbindService(this.mServiceConnection);
        }
    }
}
