package com.xiaopeng.devtools.view.copy;

import android.content.Intent;
import android.os.Bundle;
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
import com.xiaopeng.devtools.a;
import com.xiaopeng.devtools.system.a.b;
import com.xiaopeng.devtools.system.service.ZipFileService;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class VideoCopyActivity extends ActionBarActivity implements View.OnClickListener {
    private String AH;
    private b CL;
    private Button Fw;
    private TextView Fx;
    private TextView Fy;
    private StorageManager mStorageManager;
    Double Fv = Double.valueOf(0.0d);
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.copy.VideoCopyActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    VideoCopyActivity.this.Fw.setEnabled(true);
                    VideoCopyActivity.this.Fx.setVisibility(0);
                    return;
                case 2:
                    VideoCopyActivity.this.Fw.setEnabled(false);
                    VideoCopyActivity.this.Fx.setText("");
                    VideoCopyActivity.this.Fx.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    };
    private Thread Fz = new Thread(new Runnable() { // from class: com.xiaopeng.devtools.view.copy.VideoCopyActivity.2
        @Override // java.lang.Runnable
        public void run() {
            VideoCopyActivity.this.Fv = Double.valueOf(0.0d);
            for (int i = 0; i < a.nz.length; i++) {
                VideoCopyActivity.this.Fv = Double.valueOf(VideoCopyActivity.this.Fv.doubleValue() + com.xiaopeng.lib.utils.b.u(a.nz[i], 3));
                c.f("VideoCopyActivity", "mGetFileSizeThread " + a.nz[i] + " " + VideoCopyActivity.this.Fv);
            }
            j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.copy.VideoCopyActivity.2.1
                @Override // java.lang.Runnable
                public void run() {
                    VideoCopyActivity.this.Fx.setText(VideoCopyActivity.this.getString(R.string.copying_camera_media_to_udisk_with_size, new Object[]{Long.valueOf((long) Math.floor(VideoCopyActivity.this.Fv.doubleValue()))}));
                }
            });
        }
    });

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        switch (num.intValue()) {
            case 1:
                this.Fw.setEnabled(true);
                this.Fx.setText(R.string.finished_copying_camera_media_to_udisk);
                return;
            case 2:
                this.Fw.setEnabled(true);
                this.Fx.setText(R.string.fail_copying_camera_media_to_udisk);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.Fy = (TextView) findViewById(R.id.tv_media_status);
        this.Fx = (TextView) findViewById(R.id.tv_copy_media_status);
        this.Fw = (Button) findViewById(R.id.copy_media_data_bt);
        this.AH = r.lo();
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
        this.CL = new b(this.mHandler);
        this.mStorageManager.registerListener(this.CL);
        if (TextUtils.isEmpty(this.AH)) {
            this.Fw.setEnabled(false);
        } else {
            this.Fw.setEnabled(true);
        }
        this.Fw.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_camera_video_copy);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.copy_media_data_bt) {
            this.Fx.setText(R.string.copying_camera_media_to_udisk);
            if (!this.Fz.isAlive()) {
                j.execute(this.Fz);
            }
            Intent intent = new Intent(this, ZipFileService.class);
            if (TextUtils.isEmpty(this.AH)) {
                this.AH = this.CL.kI();
            }
            c.f("VideoCopyActivity", "mStoragePath = " + this.AH);
            intent.putExtra("storagePath", this.AH);
            intent.putExtra("sourcePath", a.nz);
            startServiceAsUser(intent, UserHandle.CURRENT);
            this.Fw.setEnabled(false);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mStorageManager != null && this.CL != null) {
            this.mStorageManager.unregisterListener(this.CL);
        }
        EventBus.getDefault().unregister(this);
    }
}
