package com.xiaopeng.devtools.view.factorytest.hardwaretest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.CopyFileTestResult;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.k;
import com.xiaopeng.devtools.system.a.b;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.u;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class USBTestActivity extends AsOrFacBaseActivity {
    private String AH;
    private b CL;
    private TextView HA;
    private TextView HB;
    private TextView HC;
    private TextView HD;
    private TextView HE;
    private TextView HF;
    private TextView HG;
    private TextView HH;
    private TextView HI;
    private TextView HJ;
    private TextView HK;
    private TextView HL;
    private TextView HM;
    private Button HN;
    private Button HO;
    private k Hv;
    private TextView Hw;
    private TextView Hx;
    private TextView Hy;
    private TextView Hz;
    private StorageManager mStorageManager;
    private d Ep = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b();
    private int HP = -3;
    private boolean HQ = false;
    private int HR = 0;
    private int HS = 0;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.USBTestActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    USBTestActivity.this.HO.setEnabled(true);
                    return;
                case 2:
                    USBTestActivity.this.HO.setEnabled(false);
                    USBTestActivity.this.AH = null;
                    return;
                default:
                    return;
            }
        }
    };
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.USBTestActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            c.f("USBTestActivity", "onReceive action = " + action);
            if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                USBTestActivity.this.mA();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void mA() {
        if (!this.HQ) {
            if (!this.Hv.ge()) {
                e(0, this.Hv.fY());
            }
            if (!this.Hv.gf()) {
                e(1, this.Hv.fZ());
            }
            if (!this.Hv.gg()) {
                e(2, this.Hv.ga());
            }
            c.f("USBTestActivity", "isMediaHasCheck()" + this.Hv.gh());
            if (!this.Hv.gh()) {
                e(3, this.Hv.gb());
                return;
            }
            return;
        }
        if (this.Hv.gd()) {
            this.HR++;
            this.HC.setText(getString(R.string.usb_plug_success, new Object[]{Integer.valueOf(this.HR)}));
        } else {
            this.HS++;
            this.HD.setText(getString(R.string.usb_plug_failure, new Object[]{Integer.valueOf(this.HS)}));
        }
        this.HE.setText(getString(R.string.usb_plug_total, new Object[]{Integer.valueOf(this.HS + this.HR)}));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.AH = g.V(this);
        c.f("USBTestActivity", "onCreate mStoragePath = " + this.AH);
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_test_usb);
        this.Hv = new k();
        setTarget(getString(R.string.test_usb));
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Hw = (TextView) findViewById(R.id.result_face_exist_tv);
        this.Hx = (TextView) findViewById(R.id.result_dvr_exist_tv);
        this.Hy = (TextView) findViewById(R.id.result_dcm_exist_tv);
        this.Hz = (TextView) findViewById(R.id.result_media_exist_tv);
        this.HN = (Button) findViewById(R.id.bt_start_usb_plug_test);
        this.HC = (TextView) findViewById(R.id.tv_usb_plug_success);
        this.HD = (TextView) findViewById(R.id.tv_usb_plug_failure);
        this.HE = (TextView) findViewById(R.id.tv_usb_plug_total);
        this.HA = (TextView) findViewById(R.id.face_exist_header);
        this.HB = (TextView) findViewById(R.id.dvr_exist_header);
        this.HO = (Button) findViewById(R.id.bt_start_usb_rw_test);
        this.HF = (TextView) findViewById(R.id.tv_usb_read_result);
        this.HG = (TextView) findViewById(R.id.tv_usb_read_time);
        this.HH = (TextView) findViewById(R.id.tv_usb_write_result);
        this.HI = (TextView) findViewById(R.id.tv_usb_write_time);
        this.HJ = (TextView) findViewById(R.id.tv_usb_read_title);
        this.HK = (TextView) findViewById(R.id.tv_usb_read_time_title);
        this.HL = (TextView) findViewById(R.id.tv_usb_write_title);
        this.HM = (TextView) findViewById(R.id.tv_usb_write_time_title);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (this.Ep.iN()) {
            this.HA.setText(R.string.dvr_usb_test);
            this.HB.setText(R.string.queue_usb_test);
        }
        this.HJ.setVisibility(8);
        this.HF.setVisibility(8);
        this.HK.setVisibility(8);
        this.HG.setVisibility(8);
        this.HL.setVisibility(8);
        this.HH.setVisibility(8);
        this.HM.setVisibility(8);
        this.HI.setVisibility(8);
        if (!a.b.getBoolean("SUPPORT_USB_DVR")) {
            findViewById(R.id.layout_dvr).setVisibility(8);
        }
        if (!a.b.getBoolean("SUPPORT_USB_ICM")) {
            findViewById(R.id.layout_dcm).setVisibility(8);
        }
        this.HO.setEnabled(!TextUtils.isEmpty(this.AH));
        mA();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        registerReceiver(this.mReceiver, intentFilter);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.HN.setOnClickListener(this);
        this.HO.setOnClickListener(this);
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
        this.CL = new b(this.mHandler);
        if (this.mStorageManager != null) {
            c.f("USBTestActivity", "setViewListener register StorageEventListener");
            this.mStorageManager.registerListener(this.CL);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.theme == 1) {
            recordRepairModeAction("usb diagnosis", "triggered");
        }
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.bt_start_usb_plug_test /* 2131296441 */:
                this.HQ = true;
                this.HC.setText(getString(R.string.usb_plug_success, new Object[]{Integer.valueOf(this.HR)}));
                this.HD.setText(getString(R.string.usb_plug_failure, new Object[]{Integer.valueOf(this.HS)}));
                this.HE.setText(getString(R.string.usb_plug_total, new Object[]{Integer.valueOf(this.HS + this.HR)}));
                return;
            case R.id.bt_start_usb_rw_test /* 2131296442 */:
                if (!u.lF()) {
                    return;
                }
                this.HJ.setVisibility(0);
                this.HF.setVisibility(0);
                this.HK.setVisibility(0);
                this.HG.setVisibility(0);
                this.HL.setVisibility(0);
                this.HH.setVisibility(0);
                this.HM.setVisibility(0);
                this.HI.setVisibility(0);
                this.HF.setText(R.string.test_unknown);
                this.HF.setTextColor(-12303292);
                this.HG.setText(R.string.test_unknown);
                this.HG.setTextColor(-12303292);
                this.HH.setText(R.string.test_unknown);
                this.HH.setTextColor(-12303292);
                this.HI.setText(R.string.test_unknown);
                this.HI.setTextColor(-12303292);
                this.HO.setEnabled(false);
                j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.USBTestActivity.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (TextUtils.isEmpty(USBTestActivity.this.AH)) {
                            USBTestActivity.this.AH = USBTestActivity.this.CL.kI();
                        }
                        c.f("USBTestActivity", "mStoragePath = " + USBTestActivity.this.AH);
                        g.aW("sdcard/usbtest/");
                        USBTestActivity uSBTestActivity = USBTestActivity.this;
                        TextView textView = USBTestActivity.this.HF;
                        TextView textView2 = USBTestActivity.this.HG;
                        uSBTestActivity.a(false, textView, textView2, USBTestActivity.this.AH + "/usbtest/test.zip", "sdcard/usbtest/test.zip");
                        USBTestActivity uSBTestActivity2 = USBTestActivity.this;
                        TextView textView3 = USBTestActivity.this.HH;
                        TextView textView4 = USBTestActivity.this.HI;
                        uSBTestActivity2.a(true, textView3, textView4, "sdcard/usbtest/test.zip", USBTestActivity.this.AH + "/usbtest/" + System.currentTimeMillis() + "test.zip");
                    }
                });
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_usb));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_usb));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final TextView textView, final TextView textView2, String str, String str2) {
        final CopyFileTestResult L = g.L(str, str2);
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.USBTestActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (L.mPass) {
                    textView.setText(R.string.test_succeed);
                    textView.setTextColor(-16776961);
                } else {
                    textView.setText(R.string.test_fail);
                    textView.setTextColor(-65536);
                }
                textView2.setText(String.valueOf(L.mUsedTime));
                textView2.setTextColor(-16711936);
                if (z) {
                    USBTestActivity.this.HO.setEnabled(USBTestActivity.this.Hv.gd());
                }
            }
        });
    }

    private void e(int i, boolean z) {
        switch (i) {
            case 0:
                a(z, getString(R.string.face_usb_test), this.Hw);
                return;
            case 1:
                a(z, getString(R.string.dvr_usb_test), this.Hx);
                return;
            case 2:
                a(z, getString(R.string.dcm_usb_test), this.Hy);
                return;
            case 3:
                a(z, getString(R.string.media_usb_test), this.Hz);
                return;
            default:
                return;
        }
    }

    private void a(boolean z, String str, TextView textView) {
        if (z) {
            textView.setTextColor(getResources().getColor(17170450));
            textView.setText(getString(R.string.test_succeed));
            a(str, true, "");
            return;
        }
        textView.setTextColor(getResources().getColor(17170454));
        textView.setText(getString(R.string.test_fail));
        a(str, false, "");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
        if (this.mStorageManager != null && this.CL != null) {
            this.mStorageManager.unregisterListener(this.CL);
        }
    }
}
