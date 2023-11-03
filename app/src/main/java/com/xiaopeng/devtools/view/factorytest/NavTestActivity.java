package com.xiaopeng.devtools.view.factorytest;

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
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.a.b;
import com.xiaopeng.devtools.system.service.CopyFileService;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class NavTestActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String[] nx = {"/data/gps"};
    private String AH;
    private TextView Fx;
    private Button Ga;
    private Button Gb;
    private Button Gc;
    private TextView Gd;
    private com.xiaopeng.devtools.presenter.factorytest.a.a Ge;
    private a Gf;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.NavTestActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    NavTestActivity.this.Gc.setEnabled(true);
                    NavTestActivity.this.Fx.setVisibility(0);
                    return;
                case 2:
                    NavTestActivity.this.Gc.setEnabled(false);
                    NavTestActivity.this.Fx.setText("");
                    NavTestActivity.this.Fx.setVisibility(8);
                    return;
                case 3:
                    NavTestActivity.this.Gc.setClickable(true);
                    NavTestActivity.this.Fx.setText("拷贝成功");
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
                this.mHandler.sendEmptyMessageDelayed(3, 2000L);
                return;
            case 2:
                this.Gc.setClickable(true);
                this.Fx.setText("拷贝失败");
                return;
            default:
                return;
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
        setContentView(R.layout.activity_nav_test);
        this.Ge = new b();
        this.AH = r.lo();
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Ga = (Button) findViewById(R.id.btn_start_save_data);
        this.Gb = (Button) findViewById(R.id.btn_stop_save_data);
        this.Gc = (Button) findViewById(R.id.btn_copy_data);
        this.Gd = (TextView) findViewById(R.id.tv_gps_version);
        this.Gd.setText(getString(R.string.gps_version, new Object[]{this.Ge.gY()}));
        this.Fx = (TextView) findViewById(R.id.tv_copy_result);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        EventBus.getDefault().register(this);
        this.Ga.setOnClickListener(this);
        this.Gb.setOnClickListener(this);
        this.Gc.setOnClickListener(this);
        this.Gf = new a(this.mHandler);
        this.mStorageManager.registerListener(this.Gf);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_copy_data) {
            this.Fx.setText("正在拷贝中，请稍候...");
            Intent intent = new Intent(this, CopyFileService.class);
            if (TextUtils.isEmpty(this.AH)) {
                this.AH = this.Gf.kI();
            }
            intent.putExtra("storagePath", this.AH);
            intent.putExtra("sourcePath", nx);
            startServiceAsUser(intent, UserHandle.CURRENT);
            this.Gc.setClickable(false);
        } else if (id == R.id.btn_start_save_data) {
            if (!this.Ge.gV()) {
                this.Ge.gW();
            }
        } else if (id == R.id.btn_stop_save_data && this.Ge.gV()) {
            this.Ge.gX();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mStorageManager != null && this.Gf != null) {
            this.mStorageManager.unregisterListener(this.Gf);
        }
        EventBus.getDefault().unregister(this);
    }

    /* loaded from: classes12.dex */
    private static class a extends StorageEventListener {
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
            c.f("NavTestActivity", "path = " + str + ", oldState = " + str2 + ", newState = " + str3);
            if (str3.equals("mounted")) {
                if (this.sz.get() != null) {
                    Message obtainMessage = this.sz.get().obtainMessage();
                    obtainMessage.what = 1;
                    obtainMessage.sendToTarget();
                    this.Ap = str;
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
