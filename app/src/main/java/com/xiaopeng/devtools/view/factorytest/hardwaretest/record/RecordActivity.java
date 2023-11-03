package com.xiaopeng.devtools.view.factorytest.hardwaretest.record;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.system.a.b;
import com.xiaopeng.devtools.system.service.CopyFileService;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.u;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.lib.utils.c;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class RecordActivity extends AsOrFacBaseActivity implements a {
    private static final String[] Lw = {Environment.getExternalStorageDirectory() + "/record/"};
    private String AH;
    private b CL;
    private TextView Fx;
    private Button Lq = null;
    private Button Lr = null;
    private Button Ls = null;
    private Button Lt = null;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.a Lu = null;
    private Button Lv = null;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.record.RecordActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    RecordActivity.this.Lv.setEnabled(true);
                    RecordActivity.this.Fx.setVisibility(0);
                    return;
                case 2:
                    RecordActivity.this.Lv.setEnabled(false);
                    RecordActivity.this.Fx.setText("");
                    RecordActivity.this.Fx.setVisibility(8);
                    RecordActivity.this.AH = null;
                    return;
                default:
                    return;
            }
        }
    };
    private StorageManager mStorageManager;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        switch (num.intValue()) {
            case 1:
                this.Lv.setEnabled(true);
                this.Fx.setText(R.string.copy_record_success);
                return;
            case 2:
                this.Lv.setEnabled(true);
                this.Fx.setText(R.string.copy_record_fail);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_record);
        setTarget(getString(R.string.test_record));
        this.AH = g.V(this);
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
        dJ();
    }

    private void dJ() {
        this.Lu = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.b(getApplicationContext(), this);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Lq = (Button) findViewById(R.id.btn_record_start);
        this.Lr = (Button) findViewById(R.id.btn_record_stop);
        this.Ls = (Button) findViewById(R.id.btn_record_play);
        this.Lt = (Button) findViewById(R.id.btn_record_stop_play);
        this.Lv = (Button) findViewById(R.id.btn_copy_media);
        this.Fx = (TextView) findViewById(R.id.tv_copy_result);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (TextUtils.isEmpty(this.AH)) {
            this.Lv.setEnabled(false);
        } else {
            this.Lv.setEnabled(true);
        }
        dl(2);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        EventBus.getDefault().register(this);
        this.Lq.setOnClickListener(this);
        this.Lr.setOnClickListener(this);
        this.Ls.setOnClickListener(this);
        this.Lt.setOnClickListener(this);
        this.Lv.setOnClickListener(this);
        this.CL = new b(this.mHandler);
        this.mStorageManager.registerListener(this.CL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.theme == 1) {
            recordRepairModeAction("mic diagnosis", "triggered");
        }
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id != R.id.btn_copy_media) {
            switch (id) {
                case R.id.btn_record_play /* 2131296493 */:
                    dl(3);
                    this.Lu.play();
                    return;
                case R.id.btn_record_start /* 2131296494 */:
                    dl(1);
                    this.Lu.startRecord();
                    return;
                case R.id.btn_record_stop /* 2131296495 */:
                    dl(2);
                    this.Lu.stopRecord();
                    return;
                case R.id.btn_record_stop_play /* 2131296496 */:
                    dl(4);
                    this.Lu.stopPlay();
                    return;
                default:
                    return;
            }
        } else if (!u.lF()) {
        } else {
            this.Fx.setText(R.string.copying_record);
            Intent intent = new Intent(this, CopyFileService.class);
            if (TextUtils.isEmpty(this.AH)) {
                this.AH = this.CL.kI();
            }
            c.f("RecordActivity", "mStoragePath = " + this.AH);
            intent.putExtra("storagePath", this.AH);
            intent.putExtra("sourcePath", Lw);
            startServiceAsUser(intent, UserHandle.CURRENT);
            this.Lv.setEnabled(false);
            recordRepairModeAction("copy record diagnosis media", "triggered");
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.record.a
    public void dl(int i) {
        switch (i) {
            case 1:
                this.Lq.setEnabled(false);
                this.Lr.setEnabled(true);
                this.Ls.setEnabled(false);
                this.Lt.setEnabled(false);
                return;
            case 2:
            case 4:
                this.Lq.setEnabled(true);
                this.Lr.setEnabled(false);
                this.Ls.setEnabled(this.Lu.gN());
                this.Lt.setEnabled(false);
                return;
            case 3:
                this.Lq.setEnabled(false);
                this.Lr.setEnabled(false);
                this.Ls.setEnabled(false);
                this.Lt.setEnabled(true);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        super.mv();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_record));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        super.mw();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_record));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.Lu.releaseAll();
        if (this.mStorageManager != null && this.CL != null) {
            this.mStorageManager.unregisterListener(this.CL);
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
