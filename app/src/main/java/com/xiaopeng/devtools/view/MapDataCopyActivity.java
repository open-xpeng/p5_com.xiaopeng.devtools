package com.xiaopeng.devtools.view;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
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
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.a;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class MapDataCopyActivity extends ActionBarActivity implements View.OnClickListener {
    private Button CG;
    private Button CH;
    private Button CI;
    private Button CJ;
    private String CK;
    private com.xiaopeng.devtools.system.a.b CL;
    private TextView CM;
    private TextView CN;
    private TextView CO;
    private TextView CP;
    private int CQ;
    private int CS;
    private String CT;
    private StorageManager mStorageManager;
    private int mStep = 0;
    private boolean CU = false;
    private boolean CV = false;
    private boolean CW = false;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.MapDataCopyActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    MapDataCopyActivity.this.CK = MapDataCopyActivity.this.CL.kI();
                    MapDataCopyActivity.this.CG.setEnabled(true);
                    MapDataCopyActivity.this.CH.setEnabled(true);
                    MapDataCopyActivity.this.CI.setEnabled(true);
                    MapDataCopyActivity.this.CP.setVisibility(0);
                    return;
                case 2:
                    MapDataCopyActivity.this.CK = MapDataCopyActivity.this.CL.kI();
                    MapDataCopyActivity.this.CG.setEnabled(false);
                    MapDataCopyActivity.this.CH.setEnabled(false);
                    MapDataCopyActivity.this.CI.setEnabled(false);
                    MapDataCopyActivity.this.CP.setText("");
                    MapDataCopyActivity.this.CP.setVisibility(4);
                    MapDataCopyActivity.this.mStep = 0;
                    return;
                case 3:
                    MapDataCopyActivity.this.CT = "mounted";
                    MapDataCopyActivity.this.CP.setText("");
                    MapDataCopyActivity.this.CP.setVisibility(4);
                    MapDataCopyActivity.this.lR();
                    return;
                default:
                    return;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressLint({"MissingPermission"})
    public void onEvent(Integer num) {
        switch (num.intValue()) {
            case 1:
                this.CS++;
                c.f("MapDataCopyActivity", "POST_FILE_COPY_SUCCESS mFileSumFinish " + this.CS);
                if (this.CS == this.CQ) {
                    this.CG.setEnabled(true);
                    this.CH.setEnabled(true);
                    this.CI.setEnabled(true);
                    if (this.mStep == 1) {
                        this.CP.setText(R.string.finished_copying_map_data);
                        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
                        if (activityManager != null) {
                            activityManager.killBackgroundProcesses("com.xiaopeng.montecarlo");
                        }
                    } else if (this.mStep == 2) {
                        this.CP.setText(R.string.finished_copying_ai_media);
                    } else if (this.mStep == 3) {
                        this.CP.setText(R.string.finished_copying_app);
                    }
                    lR();
                    this.mStep = 0;
                    return;
                }
                return;
            case 2:
                this.CG.setEnabled(true);
                this.CH.setEnabled(true);
                this.CI.setEnabled(true);
                if (this.mStep == 1) {
                    this.CP.setText(R.string.fail_copying_map_data);
                } else if (this.mStep == 2) {
                    this.CP.setText(R.string.fail_copying_ai_media);
                } else if (this.mStep == 3) {
                    this.CP.setText(R.string.fail_copying_app);
                }
                lR();
                this.mStep = 0;
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.CM = (TextView) findViewById(R.id.tv_map_status);
        this.CN = (TextView) findViewById(R.id.tv_aimedia_status);
        this.CO = (TextView) findViewById(R.id.tv_app_status);
        this.CH = (Button) findViewById(R.id.copy_aimedia_data_bt);
        this.CG = (Button) findViewById(R.id.copy_map_data_bt);
        this.CI = (Button) findViewById(R.id.copy_app_bt);
        this.CJ = (Button) findViewById(R.id.sd_format_bt);
        this.CP = (TextView) findViewById(R.id.tv_copy_map_status);
        this.CQ = 0;
        this.CS = 0;
        this.CK = r.lo();
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
        this.CL = new com.xiaopeng.devtools.system.a.b(this.mHandler);
        this.mStorageManager.registerListener(this.CL);
        this.CT = g.ad(this);
        if (TextUtils.isEmpty(this.CK)) {
            this.CG.setEnabled(false);
            this.CH.setEnabled(false);
            this.CI.setEnabled(false);
        } else {
            this.CG.setEnabled(true);
            this.CH.setEnabled(true);
            this.CI.setEnabled(true);
        }
        if ("unknown".equalsIgnoreCase(this.CT) || "removed".equalsIgnoreCase(this.CT)) {
            this.CJ.setEnabled(false);
        } else {
            this.CJ.setEnabled(true);
        }
        lR();
        this.CG.setOnClickListener(this);
        this.CH.setOnClickListener(this);
        this.CI.setOnClickListener(this);
        this.CJ.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_map_data_copy);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.copy_aimedia_data_bt /* 2131296650 */:
                if (this.CV) {
                    cG(2);
                    return;
                } else {
                    c(".mp4", "/AIAssistant/media/", "/mnt/extsd/AIAssistant/media/", 2);
                    return;
                }
            case R.id.copy_app_bt /* 2131296651 */:
                if (this.CW) {
                    cG(3);
                    return;
                } else {
                    c(".apk", "/AppStore/apk/", "/mnt/extsd/AppStore/apk/", 3);
                    return;
                }
            case R.id.copy_map_data_bt /* 2131296653 */:
                if (this.CU) {
                    cG(1);
                    return;
                } else {
                    c(".ans", "/chn/", "/mnt/extsd/montecarlo/data/Navi/compile_v2/chn/", 1);
                    return;
                }
            case R.id.sd_format_bt /* 2131297052 */:
                StorageVolume i = g.i(this, "SD");
                if (i != null) {
                    this.CP.setText(R.string.formating_tfcard);
                    g.a(i, this);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void cG(final int i) {
        a aVar = new a(this);
        aVar.show();
        aVar.setCanceledOnTouchOutside(true);
        if (i == 1) {
            aVar.cR(getString(R.string.map_data_found_alert));
        } else if (i == 2) {
            aVar.cR(getString(R.string.ai_mdedia_found_alert));
        } else if (i == 3) {
            aVar.cR(getString(R.string.app_found_alert));
        }
        aVar.cP(getString(R.string.common_continue));
        aVar.cO(getString(R.string.common_cancel));
        aVar.a(new a.InterfaceC0071a() { // from class: com.xiaopeng.devtools.view.MapDataCopyActivity.2
            @Override // com.xiaopeng.devtools.view.a.InterfaceC0071a
            public void onClick(a aVar2) {
                aVar2.dismiss();
            }
        });
        aVar.b(new a.InterfaceC0071a() { // from class: com.xiaopeng.devtools.view.MapDataCopyActivity.3
            @Override // com.xiaopeng.devtools.view.a.InterfaceC0071a
            public void onClick(a aVar2) {
                aVar2.dismiss();
                if (i == 1) {
                    MapDataCopyActivity.this.c(".ans", "/chn/", "/mnt/extsd/montecarlo/data/Navi/compile_v2/chn/", i);
                } else if (i == 2) {
                    MapDataCopyActivity.this.c(".mp4", "/AIAssistant/media/", "/mnt/extsd/AIAssistant/media/", i);
                } else if (i == 3) {
                    MapDataCopyActivity.this.c(".apk", "/AppStore/apk/", "/mnt/extsd/AppStore/apk/", i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, final String str2, final String str3, int i) {
        if (!TextUtils.isEmpty(this.CK)) {
            c.f("MapDataCopyActivity", "mSourcePath = " + this.CK + str2);
            if (g.I(this.CK + str2, str)) {
                this.mStep = i;
                if (i == 1) {
                    this.CP.setText(R.string.copying_map_data);
                } else if (i == 2) {
                    this.CP.setText(R.string.copying_ai_media);
                } else if (i == 3) {
                    this.CP.setText(R.string.copying_app);
                }
                this.CG.setEnabled(false);
                this.CH.setEnabled(false);
                this.CI.setEnabled(false);
                j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.MapDataCopyActivity.4
                    @Override // java.lang.Runnable
                    public void run() {
                        g.aX(str3);
                        MapDataCopyActivity.this.CS = 0;
                        MapDataCopyActivity mapDataCopyActivity = MapDataCopyActivity.this;
                        mapDataCopyActivity.CQ = g.cx(MapDataCopyActivity.this.CK + str2);
                        g.M(MapDataCopyActivity.this.CK + str2, str3);
                    }
                });
            } else if (i == 1) {
                this.CP.setText(R.string.no_map_data_udisk);
            } else if (i == 2) {
                this.CP.setText(R.string.no_ai_media_udisk);
            } else if (i == 3) {
                this.CP.setText(R.string.no_app_udisk);
            }
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
    public void lR() {
        if ("unknown".equalsIgnoreCase(this.CT) || "removed".equalsIgnoreCase(this.CT)) {
            this.CU = false;
            this.CM.setText(R.string.no_sd);
            this.CM.setTextColor(getResources().getColor(17170457));
            this.CV = false;
            this.CN.setText(R.string.no_sd);
            this.CN.setTextColor(getResources().getColor(17170457));
            this.CW = false;
            this.CO.setText(R.string.no_sd);
            this.CO.setTextColor(getResources().getColor(17170457));
        } else if ("mounted".equalsIgnoreCase(this.CT)) {
            if (g.I("/mnt/extsd/montecarlo/data/Navi/compile_v2/chn/", ".ans")) {
                this.CU = true;
                this.CM.setText(R.string.map_data_found);
                this.CM.setTextColor(getResources().getColor(17170453));
            } else {
                this.CU = false;
                this.CM.setText(R.string.map_data_not_found);
                this.CM.setTextColor(getResources().getColor(17170455));
            }
            if (g.I("/mnt/extsd/AIAssistant/media/", ".mp4")) {
                this.CV = true;
                this.CN.setText(R.string.ai_media_data_found);
                this.CN.setTextColor(getResources().getColor(17170453));
            } else {
                this.CV = false;
                this.CN.setText(R.string.ai_media_data_not_found);
                this.CN.setTextColor(getResources().getColor(17170455));
            }
            if (g.I("/mnt/extsd/AppStore/apk/", ".apk")) {
                this.CW = true;
                this.CO.setText(R.string.app_found);
                this.CO.setTextColor(getResources().getColor(17170453));
                return;
            }
            this.CW = false;
            this.CO.setText(R.string.app_not_found);
            this.CO.setTextColor(getResources().getColor(17170455));
        } else {
            this.CU = false;
            this.CM.setText(R.string.tf_card_unmount);
            this.CM.setTextColor(getResources().getColor(17170457));
            this.CV = false;
            this.CN.setText(R.string.tf_card_unmount);
            this.CN.setTextColor(getResources().getColor(17170457));
            this.CW = false;
            this.CO.setText(R.string.tf_card_unmount);
            this.CO.setTextColor(getResources().getColor(17170457));
        }
    }
}
