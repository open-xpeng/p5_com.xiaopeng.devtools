package com.xiaopeng.devtools.view.smartdrive.adas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.FileEvent;
import com.xiaopeng.devtools.presenter.f.a.b;
import com.xiaopeng.devtools.system.service.CopyFileService;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class AdasCollectActivity extends ActionBarActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private String AH;
    private TextView Fx;
    private CheckBox Te;
    private Button Tf;
    private Button Tg;
    private a Th;
    private b Ti = new com.xiaopeng.devtools.presenter.f.a.a();
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.smartdrive.adas.AdasCollectActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    AdasCollectActivity.this.Tf.setEnabled(true);
                    AdasCollectActivity.this.Fx.setVisibility(0);
                    return;
                case 2:
                    AdasCollectActivity.this.Tf.setEnabled(false);
                    AdasCollectActivity.this.Fx.setText("");
                    AdasCollectActivity.this.Fx.setVisibility(8);
                    return;
                case 3:
                    AdasCollectActivity.this.Tf.setEnabled(true);
                    AdasCollectActivity.this.Fx.setText("日志拷贝成功");
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
                this.mHandler.sendEmptyMessageDelayed(3, 1000L);
                return;
            case 2:
                this.Tf.setEnabled(true);
                this.Fx.setText("日志拷贝失败");
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FileEvent fileEvent) {
        if (fileEvent.getStatus() == 1) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.xiaopeng.devtools.view.smartdrive.adas.AdasCollectActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    AdasCollectActivity.this.Tg.setEnabled(true);
                    AdasCollectActivity.this.Tg.setText(R.string.clear_log);
                    Toast.makeText(MyApplication.getContext(), (int) R.string.clear_log_success, 0).show();
                }
            }, 2000L);
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
        setContentView(R.layout.activity_adas_collect);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Te = (CheckBox) findViewById(R.id.checkbox_collect_adas);
        this.Tf = (Button) findViewById(R.id.copy_adas_bt);
        this.Fx = (TextView) findViewById(R.id.tv_copy_result);
        this.Tg = (Button) findViewById(R.id.bt_clear_adas);
        this.AH = r.lo();
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Te.setChecked(false);
        if (TextUtils.isEmpty(this.AH)) {
            this.Tf.setEnabled(false);
        } else {
            this.Tf.setEnabled(true);
        }
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Th = new a(this.mHandler);
        this.mStorageManager.registerListener(this.Th);
        this.Tg.setOnClickListener(this);
        this.Te.setOnCheckedChangeListener(this);
        this.Tf.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        this.Ti.kc();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_clear_adas) {
            if (this.Te.isChecked()) {
                this.Te.setChecked(false);
            }
            this.Tg.setEnabled(false);
            this.Tg.setText(R.string.log_clearing);
            this.Ti.hG();
        } else if (id == R.id.copy_adas_bt) {
            this.Fx.setText("正在拷贝中，请稍候...");
            Intent intent = new Intent(this, CopyFileService.class);
            if (TextUtils.isEmpty(this.AH)) {
                this.AH = this.Th.kI();
            }
            c.f("AdasCollectActivity", "mStoragePath = " + this.AH);
            intent.putExtra("storagePath", this.AH);
            intent.putExtra("sourcePath", com.xiaopeng.devtools.presenter.f.a.a.nx);
            startServiceAsUser(intent, UserHandle.CURRENT);
            this.Tf.setEnabled(false);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.Ti.fw();
        } else {
            this.Ti.kc();
        }
    }

    /* loaded from: classes12.dex */
    static class a extends StorageEventListener {
        private String Ap;
        private WeakReference<Handler> sz;

        public a(Handler handler) {
            this.sz = new WeakReference<>(handler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String kI() {
            return this.Ap;
        }

        public void onStorageStateChanged(String str, String str2, String str3) {
            super.onStorageStateChanged(str, str2, str3);
            c.f("AdasCollectActivity", "path = " + str + ", oldState = " + str2 + ", newState = " + str3);
            if (str3.equals("mounted")) {
                if (this.sz.get() != null) {
                    Message obtainMessage = this.sz.get().obtainMessage();
                    obtainMessage.what = 1;
                    obtainMessage.sendToTarget();
                    this.Ap = r.lo();
                }
            } else if (this.sz.get() != null) {
                Message obtainMessage2 = this.sz.get().obtainMessage();
                obtainMessage2.what = 2;
                obtainMessage2.sendToTarget();
                this.Ap = null;
            }
        }
    }
}
