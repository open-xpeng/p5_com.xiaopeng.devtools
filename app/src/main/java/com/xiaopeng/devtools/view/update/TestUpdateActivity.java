package com.xiaopeng.devtools.view.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.BoardCastEvent;
import com.xiaopeng.devtools.bean.event.ModemUpgradeEvent;
import com.xiaopeng.devtools.presenter.h.f;
import com.xiaopeng.devtools.utils.m;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.a;
import com.xiaopeng.devtools.view.clean.ClearDataActivity;
import com.xiaopeng.lib.framework.moduleinterface.ipcmodule.IIpcService;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.g;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class TestUpdateActivity extends ActionBarActivity implements View.OnClickListener, a, b, c, d, e {
    private Button FC;
    private Button FD;
    private Button TV;
    private Button TW;
    private Button TX;
    private Button TY;
    private Button TZ;
    private Button Ua;
    private Button Ub;
    private Button Uc;
    private com.xiaopeng.devtools.presenter.h.c Ud;
    private com.xiaopeng.devtools.presenter.h.e Ue;
    private com.xiaopeng.devtools.presenter.h.a Ug;
    private com.xiaopeng.devtools.presenter.h.d Uh;
    private f Ui;
    private com.xiaopeng.devtools.presenter.h.b Uj;
    private com.xiaopeng.devtools.model.e.a st;
    private com.xiaopeng.devtools.devdebug.hwcheck.b ri = new com.xiaopeng.devtools.devdebug.hwcheck.b();
    private boolean Uf = false;
    private boolean Uk = false;
    private BroadcastReceiver AS = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.update.TestUpdateActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            com.xiaopeng.lib.utils.c.f("mUsbBroadcastReceiver", "action-->" + action);
            if (action.equals("android.intent.action.MEDIA_EJECT") || action.equals("android.intent.action.MEDIA_REMOVED")) {
                TestUpdateActivity.this.Ud.onDestroy();
                if (TestUpdateActivity.this.Ue != null) {
                    TestUpdateActivity.this.Ue.onDestroy();
                }
                if (TestUpdateActivity.this.Ug != null) {
                    TestUpdateActivity.this.Ug.onDestroy();
                }
                TestUpdateActivity.this.a(false, false);
            } else if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                TestUpdateActivity.this.a(false, true);
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ModemUpgradeEvent modemUpgradeEvent) {
        if (this.TY != null) {
            a(this.TY, true);
        }
        if (modemUpgradeEvent != null && this.TY != null) {
            bm("com.xiaopeng.broadcast.STOP_UPGRADE_MODEM");
            this.st.updateEcuComplete("CDU4G", modemUpgradeEvent.isSuccess());
            if (modemUpgradeEvent.isSuccess()) {
                this.TY.setText(R.string.text_modem_upgrade_success);
                m.f(MyApplication.getContext(), "TestUpdateActivity");
                return;
            }
            this.TY.setText(R.string.text_modem_upgrade_fail);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onReceiveData(IIpcService.IpcMessageEvent ipcMessageEvent) {
        com.xiaopeng.lib.utils.c.e("TestUpdateActivity", "onReceiveData event=" + ipcMessageEvent);
        if (ipcMessageEvent == null) {
            return;
        }
        String senderPackageName = ipcMessageEvent.getSenderPackageName();
        char c = 65535;
        if (senderPackageName.hashCode() == -773268792 && senderPackageName.equals("com.xiaopeng.ota")) {
            c = 0;
        }
        if (c == 0) {
            a(ipcMessageEvent.getPayloadData(), ipcMessageEvent.getMsgID());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_reset_system) {
            Intent intent = new Intent(this, ClearDataActivity.class);
            intent.addFlags(335544320);
            startActivity(intent);
        } else if (id == R.id.btn_state_usb) {
            if (!this.st.hf()) {
                Log.d("TestUpdateActivity", "btn_state_usb service not ready.");
            } else if (this.st.hasCduFileFromUsb()) {
                this.FC.setText(R.string.search_usb);
                aq(true);
            } else {
                this.FC.setText(R.string.has_no_update_file_in_usb);
            }
        } else {
            switch (id) {
                case R.id.btn_update_mcu /* 2131296573 */:
                    if (this.Ud.hasMcuFileFromUsb()) {
                        this.TV.setText(R.string.updating_mcu);
                        a(this.TV, false);
                        this.Ud.iv();
                        this.Ud.setOtaMcuReqStatus(1);
                        return;
                    }
                    this.TV.setText(R.string.no_mcu_file);
                    return;
                case R.id.btn_update_meter /* 2131296574 */:
                    if (!this.st.hf()) {
                        Log.d("TestUpdateActivity", "btn_update_meter service not ready.");
                        return;
                    } else if (this.st.hasMeterFileFromUsb()) {
                        this.st.updateMeterFromUsb();
                        this.TW.setText(R.string.updating_meter);
                        a(this.TW, false);
                        return;
                    } else {
                        this.TW.setText(R.string.no_meter_file);
                        return;
                    }
                case R.id.btn_update_psu /* 2131296575 */:
                    if (!this.st.hf()) {
                        Log.d("TestUpdateActivity", "btn_update_scu service not ready.");
                        return;
                    } else if (this.st.hasPsuFileFromUsb()) {
                        this.st.updatePsuFromUsb();
                        this.TX.setText(R.string.updating_psu);
                        return;
                    } else {
                        this.TX.setText(R.string.no_psu_file);
                        return;
                    }
                case R.id.btn_update_tbox /* 2131296576 */:
                    if (!this.Uf) {
                        if (this.Ue.kB()) {
                            this.TZ.setText(R.string.updating_tbox);
                            a(this.TZ, false);
                            this.Ue.iv();
                            this.Ue.kC();
                            return;
                        }
                        this.TZ.setText(R.string.no_tbox_file);
                        return;
                    } else if (this.Ug.kg()) {
                        this.Ug.kh();
                        return;
                    } else {
                        this.TZ.setText(R.string.no_tbox_file);
                        return;
                    }
                case R.id.btn_upgrade_icm /* 2131296577 */:
                    com.xiaopeng.lib.utils.c.f("TestUpdateActivity", "start to update ICM");
                    if (a.b.getBoolean("UPDATE_ICM_BY_OTA")) {
                        if (!this.st.hf()) {
                            Log.d("TestUpdateActivity", "btn_update_meter service not ready.");
                            return;
                        } else if (this.st.hasMeterFileFromUsb()) {
                            this.st.updateMeterFromUsb();
                            this.Uc.setText(R.string.updating_icm);
                            return;
                        } else {
                            this.Uc.setText(R.string.no_update_icm_file);
                            return;
                        }
                    } else if (this.Uj.kq()) {
                        this.Uj.iv();
                        this.Uj.kp();
                        this.Uj.kr();
                        this.Uc.setText(R.string.updating_icm);
                        return;
                    } else {
                        this.Uc.setText(R.string.no_update_icm_file);
                        return;
                    }
                case R.id.btn_upgrade_modem /* 2131296578 */:
                    if (getString(R.string.text_modem_upgrade_fail).equals(this.TY.getText().toString())) {
                        g.show(R.string.text_modem_upgrade_fail_reboot);
                    }
                    bm("com.xiaopeng.broadcast.START_UPGRADE_MODEM");
                    this.ri.fj();
                    a(this.TY, false);
                    this.TY.setText(R.string.text_modem_is_upgrading);
                    return;
                case R.id.btn_upgrade_screenFir /* 2131296579 */:
                    if (this.Uk) {
                        if (this.Uh.ks()) {
                            this.Uh.iv();
                            this.Uh.kt();
                            return;
                        }
                        this.Ua.setText(R.string.no_update_screenFir_file);
                        return;
                    }
                    return;
                case R.id.btn_upgrade_xpuFir /* 2131296580 */:
                    com.xiaopeng.lib.utils.c.f("TestUpdateActivity", "start to update xpu");
                    if (this.Ui.kE()) {
                        this.Ui.iv();
                        this.Ui.kF();
                        this.Ub.setText(R.string.updating_xpu);
                        return;
                    }
                    this.Ub.setText(R.string.no_update_xpuFirm_file);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        com.xiaopeng.lib.utils.c.f("TestUpdateActivity", "initBeforeView");
        setContentView(R.layout.activity_test_update);
        this.st = new com.xiaopeng.devtools.model.e.a(this);
        this.Ud = new com.xiaopeng.devtools.presenter.h.c(this);
        if (!nX()) {
            this.Ue = new com.xiaopeng.devtools.presenter.h.e(this);
        } else {
            HandlerThread handlerThread = new HandlerThread("FastbootUpdater");
            handlerThread.start();
            this.Ug = new com.xiaopeng.devtools.presenter.h.a(MyApplication.getContext(), handlerThread.getLooper(), this);
        }
        this.Uh = new com.xiaopeng.devtools.presenter.h.d(this);
        this.Ui = new f(this);
        this.Uj = new com.xiaopeng.devtools.presenter.h.b(this);
        this.Uk = this.Uh.kv();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.FC = (Button) findViewById(R.id.btn_state_usb);
        this.TV = (Button) findViewById(R.id.btn_update_mcu);
        this.TZ = (Button) findViewById(R.id.btn_update_tbox);
        this.TW = (Button) findViewById(R.id.btn_update_meter);
        this.TX = (Button) findViewById(R.id.btn_update_psu);
        this.TY = (Button) findViewById(R.id.btn_upgrade_modem);
        this.FD = (Button) findViewById(R.id.btn_reset_system);
        this.Ua = (Button) findViewById(R.id.btn_upgrade_screenFir);
        this.Ub = (Button) findViewById(R.id.btn_upgrade_xpuFir);
        this.Uc = (Button) findViewById(R.id.btn_upgrade_icm);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        a(true, false);
        if (!a.C0041a.dX("UPDATE_ANDROID_SOC")) {
            this.FC.setVisibility(8);
        }
        if (!a.C0041a.dX("UPDATE_IVI_SCREEN")) {
            this.Ua.setVisibility(8);
        }
        this.TX.setVisibility(8);
        this.TY.setVisibility(8);
        this.Ub.setVisibility(8);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_CHECKING");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addDataScheme("file");
        registerReceiver(this.AS, intentFilter);
        this.FD.setOnClickListener(this);
        this.FC.setOnClickListener(this);
        this.TV.setOnClickListener(this);
        this.TZ.setOnClickListener(this);
        this.TW.setOnClickListener(this);
        this.TX.setOnClickListener(this);
        this.TY.setOnClickListener(this);
        this.Ua.setOnClickListener(this);
        this.Ub.setOnClickListener(this);
        this.Uc.setOnClickListener(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        com.xiaopeng.lib.utils.c.f("TestUpdateActivity", "MainActivity onStart");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        com.xiaopeng.lib.utils.c.f("TestUpdateActivity", "MainActivity onResume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onStop() {
        com.xiaopeng.lib.utils.c.f("TestUpdateActivity", "MainActivity onStop");
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        com.xiaopeng.lib.utils.c.f("TestUpdateActivity", "MainActivity onDestroy");
        this.st.onDestroy();
        super.onDestroy();
        bm("com.xiaopeng.broadcast.STOP_UPGRADE_MODEM");
        unregisterReceiver(this.AS);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        this.Ud.onDestroy();
        if (this.Ue != null) {
            this.Ue.onDestroy();
        }
        if (this.Ug != null) {
            this.Ug.onDestroy();
        }
        this.Uh.onDestroy();
        this.Uj.onDestroy();
    }

    private boolean nX() {
        this.Uf = com.xiaopeng.devtools.utils.g.aY("/system/bin/fastboot_tbox");
        return this.Uf;
    }

    private void bm(String str) {
        sendBroadcast(new Intent(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, boolean z2) {
        if (z) {
            if (TextUtils.isEmpty(com.xiaopeng.devtools.utils.g.V(MyApplication.getContext()))) {
                a(this.FC, false);
                a(this.TV, false);
                a(this.TZ, false);
                a(this.TW, false);
                a(this.TX, false);
                a(this.TY, false);
                a(this.Ua, false);
                a(this.Ub, false);
                a(this.Uc, false);
                return;
            }
            a(this.FC, true);
            a(this.TV, true);
            a(this.TZ, true);
            a(this.TW, true);
            a(this.TX, true);
            a(this.TY, true);
            if (this.Uk) {
                a(this.Ua, true);
            }
            a(this.Ub, true);
            a(this.Uc, true);
        } else if (!z2) {
            a(this.FC, false);
            this.FC.setText(R.string.search_usb);
            a(this.TV, false);
            this.TV.setText(R.string.update_mcu);
            a(this.TZ, false);
            this.TZ.setText(R.string.update_tbox);
            a(this.TW, false);
            this.TW.setText(R.string.update_meter);
            a(this.TX, false);
            this.TX.setText(R.string.update_psu);
            a(this.TY, false);
            a(this.Ua, false);
            this.Ua.setText(R.string.update_screenFir);
            a(this.Ub, false);
            this.Ub.setText(R.string.update_xpu_firm);
            a(this.Uc, false);
            this.Uc.setText(R.string.update_icm);
        } else {
            a(this.FC, true);
            a(this.TV, true);
            a(this.TZ, true);
            a(this.TW, true);
            a(this.TX, true);
            if (this.Uk) {
                a(this.Ua, true);
            }
            if (!this.TY.getText().toString().equals(getString(R.string.text_modem_is_upgrading))) {
                a(this.TY, true);
            }
            a(this.Ub, true);
            a(this.Uc, true);
        }
    }

    private void a(Button button, boolean z) {
        if (z) {
            button.setTextColor(getResources().getColor(17170443));
            button.setEnabled(true);
            return;
        }
        button.setTextColor(getResources().getColor(R.color.color_6ce5f2));
        button.setEnabled(false);
    }

    private void aq(final boolean z) {
        com.xiaopeng.devtools.view.a aVar = new com.xiaopeng.devtools.view.a(this);
        aVar.show();
        aVar.cH(558);
        aVar.setCanceledOnTouchOutside(true);
        aVar.cQ(getString(R.string.dialog_title_upgrade_warn));
        aVar.cR(getString(R.string.upgrade_warn_tip));
        aVar.cO(getString(R.string.common_cancel));
        aVar.cP(getString(R.string.common_continue));
        aVar.b(new a.InterfaceC0071a() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$yydTpOB5JtW6f8gJ90dZgafoDuM
            @Override // com.xiaopeng.devtools.view.a.InterfaceC0071a
            public final void onClick(com.xiaopeng.devtools.view.a aVar2) {
                TestUpdateActivity.this.a(z, aVar2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(boolean z, com.xiaopeng.devtools.view.a aVar) {
        aVar.dismiss();
        if (z) {
            this.st.updateCduFromUsb();
        }
    }

    private void a(Bundle bundle, int i) {
        if (i != 1302 || bundle == null) {
            return;
        }
        BoardCastEvent boardCastEvent = (BoardCastEvent) new Gson().fromJson(bundle.getString("string_msg"), new TypeToken<BoardCastEvent>() { // from class: com.xiaopeng.devtools.view.update.TestUpdateActivity.2
        }.getType());
        if (boardCastEvent == null) {
            return;
        }
        com.xiaopeng.lib.utils.c.f("TestUpdateActivity", "handleOTAIpcMsg " + boardCastEvent.toString());
        int type = boardCastEvent.getType();
        if (type == 0) {
            a(boardCastEvent.getData(), this.FC, R.string.success_update_cdu, R.string.fail_update_cdu);
            return;
        }
        switch (type) {
            case 2:
                a(boardCastEvent.getData(), this.TX, R.string.success_update_psu_file, R.string.fail_update_psu_file);
                return;
            case 3:
                a(boardCastEvent.getData(), this.Uc, R.string.update_icm_success, R.string.update_icm_fail);
                return;
            default:
                return;
        }
    }

    private void a(final String str, final Button button, final int i, final int i2) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$Fizlq2ad5mTOEZgVYlpXsELMSCI
            @Override // java.lang.Runnable
            public final void run() {
                TestUpdateActivity.b(str, button, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(String str, Button button, int i, int i2) {
        if ("0".equals(str)) {
            button.setText(i);
        } else if ("1".equals(str)) {
            button.setText(i2);
        }
    }

    @Override // com.xiaopeng.devtools.view.update.b
    public void dz(final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$x39LfG2qqihTgPIPrK-s2brak0Q
            @Override // java.lang.Runnable
            public final void run() {
                TestUpdateActivity.this.dM(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dM(int i) {
        if (i != 0) {
            if (i == 5) {
                this.TV.setText(R.string.mcu_update_success);
                j.b(0, new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$t8be8OdSZO7Pbp1D9QbS8CHOSfg
                    @Override // java.lang.Runnable
                    public final void run() {
                        TestUpdateActivity.this.nY();
                    }
                }, 1000L);
                return;
            }
            switch (i) {
                case 9:
                case 10:
                    return;
                default:
                    com.xiaopeng.lib.utils.c.i("TestUpdateActivity", "onOTAMcuStatus status :" + i);
                    this.TV.setText(R.string.mcu_update_fail);
                    a(this.TV, true);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void nY() {
        this.Ud.setOtaMcuReqStatus(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dL(int i) {
        this.TV.setText(getString(R.string.updating_mcu_progress, new Object[]{Integer.valueOf(i)}));
    }

    @Override // com.xiaopeng.devtools.view.update.b
    public void dA(final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$s5PEYhAE0_eNYdD4Y7gxd3gS_0k
            @Override // java.lang.Runnable
            public final void run() {
                TestUpdateActivity.this.dL(i);
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.update.d
    public void dC(final int i) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$zNrSnD_LF5vNKNohM89E_j4Fj-w
            @Override // java.lang.Runnable
            public final void run() {
                TestUpdateActivity.this.dK(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dK(int i) {
        if (!this.Uf) {
            if (this.Ue.kz()) {
                a(this.TZ, !TextUtils.isEmpty(com.xiaopeng.devtools.utils.g.V(MyApplication.getContext())));
                this.TZ.setText(this.Ue.kA() ? getString(R.string.tbox_update_success) : getString(R.string.tbox_update_fail, new Object[]{Integer.valueOf(this.Ue.getUpdateResult())}));
                return;
            } else if (a.b.getBoolean("SUPPORT_TMCU")) {
                this.TZ.setText(getString(R.string.updating_tbox_progress, new Object[]{Integer.valueOf(this.Ue.kx()), Integer.valueOf(this.Ue.ky())}));
                return;
            } else {
                this.TZ.setText(getString(R.string.updating_tbox_progress_only4g, new Object[]{Integer.valueOf(this.Ue.kx())}));
                return;
            }
        }
        switch (i) {
            case 0:
                a(this.TZ, false);
                this.TZ.setText(R.string.updating_tbox_fastboot_progress);
                return;
            case 1:
                a(this.TZ, true);
                this.TZ.setText(R.string.tbox_update_success);
                return;
            case 2:
                a(this.TZ, true);
                this.TZ.setText(getString(R.string.tbox_update_fastboot_fail, new Object[]{this.Ug.ki()}));
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.update.c
    public void dB(final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$2paz9q16cobGGZnB-B_ULdah0dQ
            @Override // java.lang.Runnable
            public final void run() {
                TestUpdateActivity.this.dJ(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dJ(int i) {
        com.xiaopeng.lib.utils.c.i("TestUpdateActivity", "onScreenFirUpdateStatus :" + i);
        switch (i) {
            case -1:
                this.Ua.setText(R.string.update_screenFir_fail);
                return;
            case 0:
                this.Ua.setText(R.string.update_screenFir_success);
                return;
            case 1:
                this.Ua.setText(R.string.updating);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.update.e
    public void dD(final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$uBJeaW-cciGW-Z3JwW1Db9Zs-Z4
            @Override // java.lang.Runnable
            public final void run() {
                TestUpdateActivity.this.dI(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dI(int i) {
        com.xiaopeng.lib.utils.c.i("TestUpdateActivity", "onXpuFirUpdateStatus :" + i);
        switch (i) {
            case 0:
                this.Ub.setText(R.string.update_xpuFirm_success);
                return;
            case 1:
                this.Ub.setText(R.string.update_xpuFirm_fail);
                return;
            case 2:
                this.Ub.setText(getString(R.string.updating_xpuFirm_progress, new Object[]{0}));
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.update.e
    public void dE(final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$Dbf0S7MrJjlD8vd64UatT4--f10
            @Override // java.lang.Runnable
            public final void run() {
                TestUpdateActivity.this.dH(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dH(int i) {
        com.xiaopeng.lib.utils.c.i("TestUpdateActivity", "onXpuFirUpdateProcess :" + i);
        this.Ub.setText(getString(R.string.updating_xpuFirm_progress, new Object[]{Integer.valueOf(i)}));
    }

    @Override // com.xiaopeng.devtools.view.update.a
    public void dx(final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$LyOkbxpK29h_YTJrKoSdM9Y0o-4
            @Override // java.lang.Runnable
            public final void run() {
                TestUpdateActivity.this.dG(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dG(int i) {
        com.xiaopeng.lib.utils.c.i("TestUpdateActivity", "onIcmUpdateStatus :" + i);
        switch (i) {
            case 0:
                this.Uc.setText(R.string.update_icm_success);
                return;
            case 1:
                this.Uc.setText(R.string.update_icm_fail);
                return;
            case 2:
                this.Uc.setText(getString(R.string.updating_icm_progress, new Object[]{0}));
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.update.a
    public void dy(final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.update.-$$Lambda$TestUpdateActivity$FC_PFCtzSFBrylUPoqQ0pgectCE
            @Override // java.lang.Runnable
            public final void run() {
                TestUpdateActivity.this.dF(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dF(int i) {
        com.xiaopeng.lib.utils.c.i("TestUpdateActivity", "onIcmUpdateProcess :" + i);
        this.Uc.setText(getString(R.string.updating_icm_progress, new Object[]{Integer.valueOf(i)}));
    }
}
