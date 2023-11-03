package com.xiaopeng.devtools.view.log;

import android.car.hardware.CarPropertyValue;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.support.v4.media.MediaPlayer2;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.system.a.b;
import com.xiaopeng.devtools.system.service.CopyLogService;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.m;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.utils.u;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.SingleChoiceAdapter;
import com.xiaopeng.devtools.view.log.GrabLogActivity;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.StorageException;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.c;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.widget.XCompoundButton;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class GrabLogActivity extends ActionBarActivity implements View.OnClickListener {
    private static String[] PH;
    private static final int[] PI = {0, StorageException.REASON_EXCEED_TRAFFIC_QUOTA, 2561, 288, 4096, 8192};
    private static String[] PJ;
    private String AH;
    private b CL;
    private c DS;
    private Button FE;
    private TextView Fx;
    private CheckBox PK;
    private CheckBox PL;
    private CheckBox PM;
    private CheckBox PN;
    private CheckBox PO;
    private CheckBox PP;
    private CheckBox PQ;
    private CheckBox PR;
    private CheckBox PS;
    private Button PT;
    private Button PU;
    private Button PV;
    private ListView PW;
    private View PY;
    private SingleChoiceAdapter PZ;
    private com.xiaopeng.devtools.presenter.i.a Qa;
    private com.xiaopeng.devtools.presenter.b.c Qb;
    private AudioManager mAudioManager;
    private StorageManager mStorageManager;
    private final String vU = "clear";
    Double Fv = Double.valueOf(0.0d);
    private final Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.log.GrabLogActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    GrabLogActivity.this.FE.setEnabled(true);
                    GrabLogActivity.this.Fx.setVisibility(0);
                    return;
                case 2:
                    GrabLogActivity.this.FE.setEnabled(false);
                    GrabLogActivity.this.Fx.setText("");
                    GrabLogActivity.this.Fx.setVisibility(8);
                    GrabLogActivity.this.AH = null;
                    return;
                default:
                    return;
            }
        }
    };
    private final com.xiaopeng.commonfunc.b.a.a Bi = new com.xiaopeng.commonfunc.b.a.a() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$EAY5ROetBLqPksKdE8ByWo8rIro
        @Override // com.xiaopeng.commonfunc.b.a.a
        public final void onChangeEvent(CarPropertyValue carPropertyValue) {
            GrabLogActivity.this.b(carPropertyValue);
        }
    };
    private final BroadcastReceiver Qc = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.log.GrabLogActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NetworkInfo networkInfo;
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") && (networkInfo = (NetworkInfo) intent.getExtra("networkInfo")) != null && networkInfo.isConnected()) {
                boolean isChecked = GrabLogActivity.this.PR.isChecked();
                if (networkInfo.getType() == 1) {
                    if (isChecked) {
                        GrabLogActivity.this.dr("tcpdump_eth");
                        GrabLogActivity.this.dr("tcpdump_tbox");
                        GrabLogActivity.this.dq("tcpdump_wlan");
                        return;
                    }
                    GrabLogActivity.this.dr("tcpdump_wlan");
                } else if (networkInfo.getType() == 9 && networkInfo.getSubtype() == 3) {
                    if (isChecked) {
                        GrabLogActivity.this.dr("tcpdump_wlan");
                        GrabLogActivity.this.dq("tcpdump_tbox");
                        GrabLogActivity.this.dq("tcpdump_eth");
                        return;
                    }
                    GrabLogActivity.this.dr("tcpdump_tbox");
                    GrabLogActivity.this.dr("tcpdump_eth");
                }
            }
        }
    };
    private Thread Fz = new Thread(new AnonymousClass3());

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(CarPropertyValue carPropertyValue) {
        int propertyId = carPropertyValue.getPropertyId();
        Object value = carPropertyValue.getValue();
        com.xiaopeng.lib.utils.c.f("GrabLogActivity", carPropertyValue.toString());
        switch (propertyId) {
            case 557846613:
                if (((Integer) value).intValue() == 1) {
                    aJ(true);
                    return;
                } else {
                    aJ(false);
                    return;
                }
            case 557846614:
                if (((Integer) value).intValue() == 1) {
                    aF(true);
                    return;
                } else {
                    aF(false);
                    return;
                }
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        boolean z = true;
        switch (num.intValue()) {
            case 1:
                Button button = this.FE;
                if (TextUtils.isEmpty(this.CL.kI()) && TextUtils.isEmpty(this.AH)) {
                    z = false;
                }
                button.setEnabled(z);
                this.Fx.setText(R.string.finish_grab_log);
                return;
            case 2:
                Button button2 = this.FE;
                if (TextUtils.isEmpty(this.CL.kI()) && TextUtils.isEmpty(this.AH)) {
                    z = false;
                }
                button2.setEnabled(z);
                this.Fx.setText(R.string.fail_grab_log);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.DS = new c(this);
        this.AH = g.V(this);
        this.Qa = new com.xiaopeng.devtools.presenter.i.b();
        this.Qb = new com.xiaopeng.devtools.presenter.b.c();
        this.Qb.a(this.Bi);
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
        this.mAudioManager = (AudioManager) getSystemService("audio");
        this.PK = (CheckBox) findViewById(R.id.autoCatch);
        this.PK.setChecked(r.lt());
        this.PK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$c-za4hKBXO-YG0VO9oI3mJZEcK8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GrabLogActivity.this.p(compoundButton, z);
            }
        });
        this.PY = LayoutInflater.from(this).inflate(R.layout.layout_single_choice_listview, this.DS.qu(), false);
        this.PW = (ListView) this.PY.findViewById(R.id.list);
        this.PZ = new SingleChoiceAdapter();
        this.PW.setAdapter((ListAdapter) this.PZ);
        this.PL = (CheckBox) findViewById(R.id.catch_modem_log);
        this.PL.setEnabled(false);
        this.PL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$APGSejCc5zhZ0p42EEjo70e-NcA
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GrabLogActivity.this.o(compoundButton, z);
            }
        });
        this.Qb.setTboxModemCapture(2);
        this.PR = (CheckBox) findViewById(R.id.catch_tcpdump);
        this.PR.setChecked(r.lB());
        this.PR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$7AK-Em4eUp9gPtyM-8o16_Ufaho
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GrabLogActivity.this.n(compoundButton, z);
            }
        });
        if (r.ln()) {
            this.PS = (CheckBox) findViewById(R.id.catch_icm_tcpdump);
            this.PS.setVisibility(0);
            this.PS.setChecked(r.lC());
            this.PS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$if2UbPsLMEG4yOk8hha6W4YSj0o
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    GrabLogActivity.this.m(compoundButton, z);
                }
            });
        }
        this.PQ = (CheckBox) findViewById(R.id.enable_route_for_tbox);
        this.PQ.setEnabled(false);
        this.PQ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$tGaXhIvEfSGQLr0ULHfs9_BVBAw
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GrabLogActivity.this.l(compoundButton, z);
            }
        });
        this.Qb.setRoutingForTbox(2);
        MyApplication.getContext().registerReceiver(this.Qc, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"), "android.permission.CONNECTIVITY_INTERNAL", this.mHandler);
        this.PM = (CheckBox) findViewById(R.id.ramdump_onoff);
        this.PM.setChecked(r.lw());
        this.PM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$PVxIbDiuL-BTh8wECNEojX2XvaY
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GrabLogActivity.this.k(compoundButton, z);
            }
        });
        this.PN = (CheckBox) findViewById(R.id.navi_log_onoff);
        this.PN.setChecked(r.ly());
        this.PN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$hFW8JQ2Ql4hrnaXOcMLO01y0dbM
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GrabLogActivity.this.j(compoundButton, z);
            }
        });
        this.PO = (CheckBox) findViewById(R.id.catch_hci_log);
        this.PO.setChecked(r.ll());
        this.PO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$XnbXgW3qWxOU03wd2dED2-Uxa54
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GrabLogActivity.this.i(compoundButton, z);
            }
        });
        this.PP = (CheckBox) findViewById(R.id.catch_audio_log);
        int nF = nF();
        this.PP.setText(getString(R.string.catch_audio_log, new Object[]{PH[nF]}));
        this.PP.setChecked(nF != 0);
        this.PP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$bcvYLrx66T8mJTZl_kGM1cLEQDM
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GrabLogActivity.this.h(compoundButton, z);
            }
        });
        this.PT = (Button) findViewById(R.id.rebootBtn);
        this.PT.setOnClickListener(this);
        this.PU = (Button) findViewById(R.id.bt_clear_log);
        this.PU.setOnClickListener(this);
        this.PV = (Button) findViewById(R.id.bt_log_level);
        this.PV.setOnClickListener(this);
        nE();
        this.Fx = (TextView) findViewById(R.id.tv_copy_result);
        this.FE = (Button) findViewById(R.id.copy_log_bt);
        this.CL = new b(this.mHandler);
        this.mStorageManager.registerListener(this.CL);
        this.FE.setEnabled(!TextUtils.isEmpty(this.AH));
        this.FE.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(CompoundButton compoundButton, boolean z) {
        this.Qa.G(z);
        aE(z);
        recordRepairModeAction("set auto catch log " + z, EcuUpdateResult.RESULT_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isPressed()) {
            aI(z);
            recordRepairModeAction("set modem log " + z, EcuUpdateResult.RESULT_SUCCESS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(CompoundButton compoundButton, boolean z) {
        aH(z);
        recordRepairModeAction("set tcpdump " + z, EcuUpdateResult.RESULT_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(CompoundButton compoundButton, boolean z) {
        aG(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isPressed()) {
            aK(z);
            recordRepairModeAction("set tbox routing " + z, EcuUpdateResult.RESULT_SUCCESS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(CompoundButton compoundButton, boolean z) {
        recordRepairModeAction("set tcpdump " + z, EcuUpdateResult.RESULT_SUCCESS);
        reboot(z ? "ramdump-on" : "ramdump-off");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(CompoundButton compoundButton, boolean z) {
        r.X(z);
        recordRepairModeAction("set navi log " + z, EcuUpdateResult.RESULT_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(CompoundButton compoundButton, boolean z) {
        r.V(z);
        if (z) {
            r.cG("/data/Log/bluetooth/btsnoop_hci.log");
            dt(R.string.bt_hci_log_warning);
        }
        recordRepairModeAction("set bt hci log " + z, EcuUpdateResult.RESULT_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.PZ.setItems(PH);
            this.PZ.setSelectedItem(nF());
            this.DS.dV(R.string.set_audio_debug_param).d(this.PY, false).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$qU_22LwYyXmgyE0LtvMc-gi5YUU
                @Override // com.xiaopeng.xui.app.d.a
                public final void onClick(c cVar, int i) {
                    GrabLogActivity.this.x(cVar, i);
                }
            }).h("").show();
            return;
        }
        ds(0);
        this.PP.setText(getString(R.string.catch_audio_log, new Object[]{PH[0]}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(c cVar, int i) {
        if (this.PZ.getSelectedItem() != 0) {
            ds(PI[this.PZ.getSelectedItem()]);
            dt(R.string.audio_log_warning);
            this.PP.setText(getString(R.string.catch_audio_log, new Object[]{PH[this.PZ.getSelectedItem()]}));
            return;
        }
        this.PP.setChecked(false);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_grab_log);
        PH = getResources().getStringArray(R.array.audio_pcm_dump_param);
        PJ = getResources().getStringArray(R.array.log_levels);
    }

    private void aE(boolean z) {
        Intent intent = new Intent("com.xiaopeng.action.LOG_SWITCH_STATE");
        intent.setPackage("com.xiaopeng.data.collector");
        intent.putExtra("log_switch_state", z);
        sendBroadcast(intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_clear_log) {
            new a().execute(new Void[0]);
            recordRepairModeAction("clear log", "triggered");
        } else if (id == R.id.bt_log_level) {
            this.PZ.setItems(PJ);
            this.PZ.setSelectedItem(getLogLevel() - 2);
            this.DS.dV(R.string.log_level_config).d(this.PY, false).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$VdL43fn2F65YS3SKbdlJlEXSl-o
                @Override // com.xiaopeng.xui.app.d.a
                public final void onClick(c cVar, int i) {
                    GrabLogActivity.this.w(cVar, i);
                }
            }).h("").show();
        } else if (id == R.id.copy_log_bt) {
            nD();
        } else if (id == R.id.rebootBtn) {
            recordRepairModeAction("reboot system", "triggered");
            reboot("GrabLogActivity");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(c cVar, int i) {
        r.cD(this.PZ.getSelectedItem() + 2);
        nE();
        dt(R.string.tips_log_perform_after_reboot);
    }

    private void nD() {
        if (!u.lF()) {
            return;
        }
        if (g.la() < 5) {
            com.xiaopeng.xui.app.g.i(getString(R.string.tips_storage_not_enough_copy_log, new Object[]{5}));
            return;
        }
        c cVar = new c(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.copy_log_checkbox_list, cVar.qu(), false);
        final XCompoundButton xCompoundButton = (XCompoundButton) inflate.findViewById(R.id.checkbox_navi_log);
        final XCompoundButton xCompoundButton2 = (XCompoundButton) inflate.findViewById(R.id.checkbox_android_log);
        final XCompoundButton xCompoundButton3 = (XCompoundButton) inflate.findViewById(R.id.checkbox_tbox_log);
        final XCompoundButton xCompoundButton4 = (XCompoundButton) inflate.findViewById(R.id.checkbox_icm_log);
        final XCompoundButton xCompoundButton5 = (XCompoundButton) inflate.findViewById(R.id.checkbox_modem_log);
        if (r.lj()) {
            xCompoundButton.setVisibility(8);
        }
        cVar.dV(R.string.select_log_to_copy).d(inflate, false).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$sxUdqaV6YFYsf572QPpmQtK1vDo
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(c cVar2, int i) {
                GrabLogActivity.this.a(xCompoundButton2, xCompoundButton, xCompoundButton4, xCompoundButton3, xCompoundButton5, cVar2, i);
            }
        }).b(getString(R.string.cancel), (d.a) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(XCompoundButton xCompoundButton, XCompoundButton xCompoundButton2, XCompoundButton xCompoundButton3, XCompoundButton xCompoundButton4, XCompoundButton xCompoundButton5, c cVar, int i) {
        this.Fx.setText(R.string.grabing_log);
        Intent intent = new Intent(MyApplication.getContext(), CopyLogService.class);
        if (TextUtils.isEmpty(this.AH)) {
            this.AH = this.CL.kI();
        }
        com.xiaopeng.lib.utils.c.f("GrabLogActivity", "mStoragePath = " + this.AH);
        ArrayList arrayList = new ArrayList();
        if (xCompoundButton.isChecked()) {
            arrayList.add(1001);
        }
        if (xCompoundButton2.isChecked()) {
            arrayList.add(Integer.valueOf((int) MediaPlayer2.MEDIAPLAYER2_STATE_ERROR));
        }
        if (xCompoundButton3.isChecked()) {
            arrayList.add(1007);
        }
        if (xCompoundButton4.isChecked()) {
            arrayList.add(1003);
        }
        if (xCompoundButton5.isChecked()) {
            arrayList.add(1006);
        }
        intent.putExtra("storagePath", this.AH);
        intent.putExtra("copylogs", arrayList);
        startServiceAsUser(intent, UserHandle.CURRENT);
        this.FE.setEnabled(false);
    }

    private void nE() {
        this.PV.setText(getString(R.string.log_level, new Object[]{PJ[getLogLevel() - 2]}));
    }

    private void aF(final boolean z) {
        com.xiaopeng.lib.utils.c.g("GrabLogActivity", "checkModemLogStatus isModemLogOn = " + z);
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$gU0WUFrXWEDuMdLnv6Qoh43iQIU
            @Override // java.lang.Runnable
            public final void run() {
                GrabLogActivity.this.aO(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void aO(boolean z) {
        this.PL.setChecked(z);
        this.PL.setEnabled(true);
    }

    private int getLogLevel() {
        int logLevel = r.getLogLevel();
        com.xiaopeng.lib.utils.c.f("GrabLogActivity", "getLogLevel :" + logLevel);
        if (logLevel <= 0 || logLevel > 7) {
            return 8;
        }
        if (logLevel == 1) {
            return 2;
        }
        return logLevel;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int nF() {
        /*
            r5 = this;
            r0 = 0
            android.media.AudioManager r1 = r5.mAudioManager     // Catch: java.lang.NumberFormatException -> L28
            java.lang.String r2 = "pcm_dump"
            java.lang.String r1 = r1.getParameters(r2)     // Catch: java.lang.NumberFormatException -> L28
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L28
            java.lang.String r2 = "GrabLogActivity"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> L26
            r3.<init>()     // Catch: java.lang.NumberFormatException -> L26
            java.lang.String r4 = "current PCMDUMP PARAMETER : "
            r3.append(r4)     // Catch: java.lang.NumberFormatException -> L26
            r3.append(r1)     // Catch: java.lang.NumberFormatException -> L26
            java.lang.String r3 = r3.toString()     // Catch: java.lang.NumberFormatException -> L26
            com.xiaopeng.lib.utils.c.g(r2, r3)     // Catch: java.lang.NumberFormatException -> L26
            goto L2d
        L26:
            r2 = move-exception
            goto L2a
        L28:
            r2 = move-exception
            r1 = r0
        L2a:
            r2.printStackTrace()
        L2d:
            r2 = r0
        L2e:
            int[] r3 = com.xiaopeng.devtools.view.log.GrabLogActivity.PI
            int r3 = r3.length
            if (r2 >= r3) goto L40
            int[] r3 = com.xiaopeng.devtools.view.log.GrabLogActivity.PI
            r3 = r3[r2]
            if (r1 != r3) goto L3d
        L3b:
            r0 = r2
            goto L40
        L3d:
            int r2 = r2 + 1
            goto L2e
        L40:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.view.log.GrabLogActivity.nF():int");
    }

    private void reboot(final String str) {
        com.xiaopeng.xui.app.g.ee(R.string.text_reboot_policy_3sec);
        j.b(0, new Runnable() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$Ud2ioZThb4jYH87SnbwXC3K-z58
            @Override // java.lang.Runnable
            public final void run() {
                GrabLogActivity.this.dt(str);
            }
        }, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dt(String str) {
        m.f(this, str);
    }

    private void aG(final boolean z) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$cM6Rvf3jX3vWBiJC_vM6KU1EfpA
            @Override // java.lang.Runnable
            public final void run() {
                GrabLogActivity.this.aN(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void aN(boolean z) {
        g.aW("/data/Log/tcpdump/");
        dr("tcpdump_cdc");
        if (z) {
            dq("tcpdump_cdc");
        }
    }

    private void aH(final boolean z) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$CWLUTj_4jZ47hDJxE7hK8ywzg_8
            @Override // java.lang.Runnable
            public final void run() {
                GrabLogActivity.this.aM(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void aM(boolean z) {
        g.aW("/data/Log/tcpdump/");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) MyApplication.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == 1) {
                if (z) {
                    dq("tcpdump_wlan");
                } else {
                    dr("tcpdump_wlan");
                }
            } else if (activeNetworkInfo.getType() == 9 && activeNetworkInfo.getSubtype() == 3) {
                if (z) {
                    dq("tcpdump_tbox");
                    dq("tcpdump_eth");
                    return;
                }
                dr("tcpdump_tbox");
                dr("tcpdump_eth");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dq(String str) {
        SystemProperties.set("ctl.start", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dr(String str) {
        SystemProperties.set("ctl.stop", str);
    }

    private void aI(boolean z) {
        com.xiaopeng.lib.utils.c.f("GrabLogActivity", "doModemLogging isChecked = " + z);
        if (z) {
            this.Qb.setTboxModemCapture(1);
        } else {
            this.Qb.setTboxModemCapture(0);
        }
    }

    private void ds(String str) {
        Toast.makeText(MyApplication.getContext(), str, 1).show();
    }

    private void aJ(final boolean z) {
        com.xiaopeng.lib.utils.c.g("GrabLogActivity", "checkCduRoutingState isRouteEnable = " + z);
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$4KzBTvsniT-00OPnonSDNvXd9iE
            @Override // java.lang.Runnable
            public final void run() {
                GrabLogActivity.this.aL(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void aL(boolean z) {
        this.PQ.setChecked(z);
        this.PQ.setEnabled(true);
    }

    private void aK(boolean z) {
        com.xiaopeng.lib.utils.c.f("GrabLogActivity", "doRoutingForTbox isChecked = " + z);
        if (z && !nG()) {
            ds(MyApplication.getContext().getString(R.string.hint_fail_to_enable_routing));
            this.PQ.setChecked(false);
        } else if (!z) {
            this.Qb.setRoutingForTbox(0);
        } else {
            this.Qb.setRoutingForTbox(1);
            ds(MyApplication.getContext().getString(R.string.hint_data_usage));
        }
    }

    private boolean nG() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) MyApplication.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = false;
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.isConnected() && (activeNetworkInfo.getType() == 1 || (activeNetworkInfo.getType() == 9 && activeNetworkInfo.getSubtype() == 2))) {
                z = true;
            }
            com.xiaopeng.lib.utils.c.f("GrabLogActivity", "isNonMobileNetworkConnected(): " + activeNetworkInfo.getTypeName() + " is connected");
        }
        return z;
    }

    private void ds(int i) {
        AudioManager audioManager = this.mAudioManager;
        audioManager.setParameters("pcm_dump=" + i);
        recordRepairModeAction("pcm_dump=" + i, EcuUpdateResult.RESULT_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void is() {
        this.mAudioManager.setParameters("pcm_dump=clear");
    }

    private void dt(int i) {
        this.DS.dV(R.string.warning).f(getString(i)).a(getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$thAEYb9hKfk_zXZhTSl2wfCQdQA
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(c cVar, int i2) {
                cVar.dismiss();
            }
        }).h("").show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaopeng.devtools.view.log.GrabLogActivity$3  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass3 implements Runnable {
        AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GrabLogActivity.this.Fv = Double.valueOf(0.0d);
            for (int i = 0; i < com.xiaopeng.devtools.a.nx.length; i++) {
                GrabLogActivity.this.Fv = Double.valueOf(GrabLogActivity.this.Fv.doubleValue() + com.xiaopeng.lib.utils.b.u(com.xiaopeng.devtools.a.nx[i], 3));
                com.xiaopeng.lib.utils.c.f("GrabLogActivity", "mGetFileSizeThread " + com.xiaopeng.devtools.a.nx[i] + " " + GrabLogActivity.this.Fv);
            }
            j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$3$h1mIgrtDqs_MFHHVKl9WRiA7h_Y
                @Override // java.lang.Runnable
                public final void run() {
                    GrabLogActivity.AnonymousClass3.this.nH();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void nH() {
            GrabLogActivity.this.Fx.setText(GrabLogActivity.this.getString(R.string.log_copying, new Object[]{Long.valueOf((long) Math.floor(GrabLogActivity.this.Fv.doubleValue()))}));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class a extends AsyncTask<Void, Void, Void> {
        private a() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            GrabLogActivity.this.PU.setEnabled(false);
            GrabLogActivity.this.PU.setText(R.string.log_clearing);
            if (!r.lj()) {
                com.xiaopeng.devtools.utils.c.ac(MyApplication.getContext());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            GrabLogActivity.this.is();
            g.a(com.xiaopeng.devtools.a.nx, com.xiaopeng.devtools.a.nH, false);
            if (!r.lj()) {
                com.xiaopeng.devtools.system.b.c.sleep(3000L);
                g.a(com.xiaopeng.devtools.a.nC, com.xiaopeng.devtools.a.nH, false);
                return null;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            super.onPostExecute((a) r4);
            GrabLogActivity.this.mHandler.postDelayed(new Runnable() { // from class: com.xiaopeng.devtools.view.log.-$$Lambda$GrabLogActivity$a$KK2ippblWubEGIyg8JHIWguW9ns
                @Override // java.lang.Runnable
                public final void run() {
                    GrabLogActivity.a.this.nI();
                }
            }, 2000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void nI() {
            GrabLogActivity.this.PU.setEnabled(true);
            GrabLogActivity.this.PU.setText(R.string.clear_log);
            Toast.makeText(MyApplication.getContext(), (int) R.string.clear_log_success, 0).show();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mStorageManager != null && this.CL != null) {
            this.mStorageManager.unregisterListener(this.CL);
        }
        EventBus.getDefault().unregister(this);
        this.Qb.onDestroy();
    }
}
