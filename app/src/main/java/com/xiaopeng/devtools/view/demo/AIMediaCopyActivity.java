package com.xiaopeng.devtools.view.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.system.a.b;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.a;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.io.File;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class AIMediaCopyActivity extends ActionBarActivity implements View.OnClickListener {
    private Button CH;
    private Button CJ;
    private String CK;
    private b CL;
    private TextView CN;
    private int CQ;
    private int CS;
    private String CT;
    private TextView FL;
    private StorageManager mStorageManager;
    private boolean CV = false;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.demo.AIMediaCopyActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    AIMediaCopyActivity.this.CK = AIMediaCopyActivity.this.CL.kI();
                    AIMediaCopyActivity.this.CH.setEnabled(true);
                    AIMediaCopyActivity.this.FL.setVisibility(0);
                    return;
                case 2:
                    AIMediaCopyActivity.this.CK = AIMediaCopyActivity.this.CL.kI();
                    AIMediaCopyActivity.this.CH.setEnabled(false);
                    AIMediaCopyActivity.this.FL.setText("");
                    AIMediaCopyActivity.this.FL.setVisibility(4);
                    return;
                case 3:
                    AIMediaCopyActivity.this.CT = "mounted";
                    AIMediaCopyActivity.this.FL.setText("");
                    AIMediaCopyActivity.this.FL.setVisibility(4);
                    AIMediaCopyActivity.this.mu();
                    return;
                default:
                    return;
            }
        }
    };
    Thread mThread = new Thread(new Runnable() { // from class: com.xiaopeng.devtools.view.demo.AIMediaCopyActivity.2
        @Override // java.lang.Runnable
        public void run() {
            g.aX("/mnt/extsd/AIAssistant/media/");
            AIMediaCopyActivity aIMediaCopyActivity = AIMediaCopyActivity.this;
            aIMediaCopyActivity.V(AIMediaCopyActivity.this.CK + "/AIAssistant/media/", "/mnt/extsd/AIAssistant/media/");
        }
    });

    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressLint({"MissingPermission"})
    public void onEvent(Integer num) {
        switch (num.intValue()) {
            case 1:
                this.CS++;
                if (this.CS == this.CQ) {
                    this.CH.setEnabled(true);
                    this.FL.setText(R.string.finished_copying_ai_media);
                    mu();
                    return;
                }
                return;
            case 2:
                this.CH.setEnabled(true);
                this.FL.setText(R.string.fail_copying_ai_media);
                mu();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.CN = (TextView) findViewById(R.id.tv_aimedia_status);
        this.FL = (TextView) findViewById(R.id.tv_copy_aimedia_status);
        this.CH = (Button) findViewById(R.id.copy_aimedia_data_bt);
        this.CJ = (Button) findViewById(R.id.sd_format_bt);
        this.CQ = 0;
        this.CS = 0;
        this.CK = r.lo();
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
        this.CL = new b(this.mHandler);
        this.mStorageManager.registerListener(this.CL);
        this.CT = g.ad(this);
        if (TextUtils.isEmpty(this.CK)) {
            this.CH.setEnabled(false);
        } else {
            this.CH.setEnabled(true);
        }
        if ("unknown".equalsIgnoreCase(this.CT) || "removed".equalsIgnoreCase(this.CT)) {
            this.CJ.setEnabled(false);
        } else {
            this.CJ.setEnabled(true);
        }
        mu();
        this.CH.setOnClickListener(this);
        this.CJ.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_ai_media_copy);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        StorageVolume i;
        int id = view.getId();
        if (id == R.id.copy_aimedia_data_bt) {
            if (this.CV) {
                showDialog();
            } else {
                mt();
            }
        } else if (id == R.id.sd_format_bt && (i = g.i(this, "SD")) != null) {
            this.FL.setText(R.string.formating_tfcard);
            g.a(i, this);
        }
    }

    private void showDialog() {
        a aVar = new a(this);
        aVar.show();
        aVar.setCanceledOnTouchOutside(true);
        aVar.cR(getString(R.string.map_data_found_alert));
        aVar.cP(getString(R.string.common_continue));
        aVar.cO(getString(R.string.common_cancel));
        aVar.a(new a.InterfaceC0071a() { // from class: com.xiaopeng.devtools.view.demo.AIMediaCopyActivity.3
            @Override // com.xiaopeng.devtools.view.a.InterfaceC0071a
            public void onClick(a aVar2) {
                aVar2.dismiss();
            }
        });
        aVar.b(new a.InterfaceC0071a() { // from class: com.xiaopeng.devtools.view.demo.AIMediaCopyActivity.4
            @Override // com.xiaopeng.devtools.view.a.InterfaceC0071a
            public void onClick(a aVar2) {
                aVar2.dismiss();
                AIMediaCopyActivity.this.mt();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mt() {
        if (!TextUtils.isEmpty(this.CK)) {
            c.f("AIMediaCopyActivity", "mSourcePath = " + this.CK + "/AIAssistant/media/");
            if (g.I(this.CK + "/AIAssistant/media/", ".mp4")) {
                this.FL.setText(R.string.copying_ai_media);
                this.CH.setEnabled(false);
                j.e(this.mThread);
                j.execute(this.mThread);
                return;
            }
            this.FL.setText(R.string.no_ai_media_udisk);
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

    /* JADX INFO: Access modifiers changed from: private */
    public boolean V(String str, String str2) {
        File file = new File(str);
        if (!g.aW(str2)) {
            c.h("AIMediaCopyActivity", "copyAiMedia mkDir fail ");
        }
        c.f("AIMediaCopyActivity", "copyAiMedia srcPath = " + str + " destPath = " + str2);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                c.f("AIMediaCopyActivity", "copyAiMedia filesList[i].getName() =  " + listFiles[i].getName());
                if (listFiles[i].isDirectory()) {
                    V(listFiles[i].getPath(), str2 + listFiles[i].getName() + "/");
                } else {
                    this.CQ++;
                    j.execute(new com.xiaopeng.devtools.system.b.a(listFiles[i].getPath(), str2 + listFiles[i].getName(), 0L, listFiles[i].length()));
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mu() {
        if ("unknown".equalsIgnoreCase(this.CT) || "removed".equalsIgnoreCase(this.CT)) {
            this.CV = false;
            this.CN.setText(R.string.no_sd);
            this.CN.setTextColor(getResources().getColor(17170457));
        } else if ("mounted".equalsIgnoreCase(this.CT)) {
            if (g.I("/mnt/extsd/AIAssistant/media/", ".mp4")) {
                this.CV = true;
                this.CN.setText(R.string.ai_media_data_found);
                this.CN.setTextColor(getResources().getColor(17170453));
                return;
            }
            this.CV = false;
            this.CN.setText(R.string.ai_media_data_not_found);
            this.CN.setTextColor(getResources().getColor(17170455));
        } else {
            this.CV = false;
            this.CN.setText(R.string.tf_card_unmount);
            this.CN.setTextColor(getResources().getColor(17170457));
        }
    }
}
