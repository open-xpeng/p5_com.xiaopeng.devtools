package com.xiaopeng.devtools.view.aftersales;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.xiaopeng.commonfunc.bean.event.CommonEvent;
import com.xiaopeng.commonfunc.utils.p;
import com.xiaopeng.commonfunc.utils.q;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBar;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.logictree.f;
import com.xiaopeng.logictree.g;
import com.xiaopeng.xui.app.b;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.app.f;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XTextView;
import com.xiaopeng.xui.widget.datepicker.XDatePicker;
import com.xiaopeng.xui.widget.timepicker.XTimePicker;
import java.io.File;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class OneClickDiagnosisActivity extends AfterSalesBaseActivity implements View.OnClickListener {
    private com.xiaopeng.xui.app.c Eb;
    private Button Ec;
    private Button Ed;
    private Button Ee;
    private XButton Ef;
    private XButton Eg;
    private XTextView Eh;
    private long Ei;
    private StringBuffer Ej;
    private g El;
    private String Em;
    private ViewGroup mContainer;
    private long mEndTimeStamp;
    private com.xiaopeng.xui.app.e pe;
    private f Ek = null;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.aftersales.OneClickDiagnosisActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            String action = intent.getAction();
            com.xiaopeng.lib.utils.c.f("OneClickDiagnosisActivity", "onReceive action-->" + action);
            int hashCode = action.hashCode();
            if (hashCode == -1665311200) {
                if (action.equals("android.intent.action.MEDIA_REMOVED")) {
                    c = 1;
                }
                c = 65535;
            } else if (hashCode == -1514214344) {
                if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                    c = 3;
                }
                c = 65535;
            } else if (hashCode != -963871873) {
                if (hashCode == -625887599 && action.equals("android.intent.action.MEDIA_EJECT")) {
                    c = 0;
                }
                c = 65535;
            } else {
                if (action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                    c = 2;
                }
                c = 65535;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                    OneClickDiagnosisActivity.this.Em = null;
                    OneClickDiagnosisActivity.this.Ef.setEnabled(!TextUtils.isEmpty(OneClickDiagnosisActivity.this.Em));
                    return;
                case 3:
                    OneClickDiagnosisActivity.this.Em = com.xiaopeng.devtools.utils.g.V(context);
                    OneClickDiagnosisActivity.this.Ef.setEnabled(!TextUtils.isEmpty(OneClickDiagnosisActivity.this.Em));
                    return;
                default:
                    return;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    @RequiresApi(api = 24)
    public void onEvent(CommonEvent commonEvent) {
        switch (commonEvent.getState()) {
            case 10002:
                com.xiaopeng.lib.utils.c.g("OneClickDiagnosisActivity", "receive DIAGNOSIS_ACTION_PAUSE");
                mb();
                return;
            case CommonEvent.DIAGNOSIS_DISMISS_LOADING_DIALOG /* 10003 */:
                com.xiaopeng.lib.utils.c.g("OneClickDiagnosisActivity", "receive DIAGNOSIS_DISMISS_LOADING_DIALOG");
                mc();
                return;
            case CommonEvent.DIAGNOSIS_LOGICTREE_INIT_FINISH /* 10004 */:
                com.xiaopeng.lib.utils.c.g("OneClickDiagnosisActivity", "receive DIAGNOSIS_LOGIC_TREE_INIT_FINISH");
                ma();
                return;
            case CommonEvent.DISPLAY_ONE_CLICK_DIAGNOSIS_RUNNING /* 10005 */:
                cJ(R.string.running_one_click_diagnosis);
                return;
            case CommonEvent.DISPLAY_LOGICTREE_TOAST /* 10006 */:
                q.bd(commonEvent.getMsg());
                return;
            case CommonEvent.UPGRADE_LOGICTREE_FAIL /* 10007 */:
                com.xiaopeng.lib.utils.c.g("OneClickDiagnosisActivity", "receive UPGRADE_LOGICTREE_FAIL");
                lX();
                q.b(this, R.string.upgrade_logictree_fail);
                return;
            case CommonEvent.UPGRADE_LOGICTREE_SUCCESS /* 10008 */:
                com.xiaopeng.lib.utils.c.g("OneClickDiagnosisActivity", "receive UPGRADE_LOGICTREE_SUCCESS");
                lX();
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_oneclick_diagnosis);
        this.Ej = new StringBuffer();
        this.El = new g();
        this.El.e(MyApplication.qx);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.mContainer = (ViewGroup) findViewById(R.id.container);
        this.Ec = (Button) findViewById(R.id.start_time);
        this.Ed = (Button) findViewById(R.id.end_time);
        this.Ee = (Button) findViewById(R.id.continue_last_diagnosis);
        af(this);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.mEndTimeStamp = System.currentTimeMillis();
        this.Ei = this.mEndTimeStamp - 600000;
        this.Ec.setText(p.a(this.Ei / 1000, "yyyy-MM-dd HH:mm"));
        this.Ed.setText(p.a(this.mEndTimeStamp / 1000, "yyyy-MM-dd HH:mm"));
        mb();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        lY();
        this.Ec.setOnClickListener(this);
        this.Ed.setOnClickListener(this);
        this.Ee.setOnClickListener(this);
        com.xiaopeng.commonfunc.utils.f.k(this);
        this.Cq.setOnCenterClickListener(new ActionBar.b() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$SMm1ut8LtAuvDbo9PYH-c67uEOk
            @Override // com.xiaopeng.devtools.view.ActionBar.b
            public final void onCenterClick(View view) {
                OneClickDiagnosisActivity.this.g(view);
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addDataScheme("file");
        registerReceiver(this.mReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(View view) {
        lZ();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.Em = com.xiaopeng.devtools.utils.g.V(this);
        cJ(R.string.loading_logictree_list);
        lX();
    }

    private void lX() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$GSHZi6s30jWT1YiImxMrv1TvzD8
            @Override // java.lang.Runnable
            public final void run() {
                OneClickDiagnosisActivity.this.me();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void me() {
        this.El.pU();
        this.El.pV();
    }

    private void lY() {
        this.Eb = new com.xiaopeng.xui.app.c(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_updater, this.Eb.qu(), false);
        this.Eh = (XTextView) inflate.findViewById(R.id.tips_updater);
        this.Ef = (XButton) inflate.findViewById(R.id.update_via_udisk);
        this.Eg = (XButton) inflate.findViewById(R.id.update_via_cloud);
        this.Ef.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$r6wY2P16crWWPDIOyGSL58liUcg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OneClickDiagnosisActivity.this.i(view);
            }
        });
        this.Eg.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$af1cRhXrGMSlW3EzEdV2OppfN_w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OneClickDiagnosisActivity.this.h(view);
            }
        });
        this.Eb.dV(R.string.tips_update).d(inflate, false);
        this.Eb.aW(true);
        this.Eb.a(new d.b() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$8xPJQWrpWq89iVnIBWlOanDJ4VI
            @Override // com.xiaopeng.xui.app.d.b
            public final boolean onClose(com.xiaopeng.xui.app.c cVar) {
                boolean a;
                a = OneClickDiagnosisActivity.this.a(cVar);
                return a;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(View view) {
        com.xiaopeng.lib.utils.c.g("OneClickDiagnosisActivity", "Update logic tree Via UDisk");
        g gVar = this.El;
        gVar.h(this.Em + File.separator + "logictree", true);
        cJ(R.string.upgrading_via_udisk);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(View view) {
        com.xiaopeng.lib.utils.c.g("OneClickDiagnosisActivity", "Update logic tree Via Cloud");
        this.El.d(MyApplication.qx);
        cJ(R.string.upgrading_via_cloud);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean a(com.xiaopeng.xui.app.c cVar) {
        mc();
        return false;
    }

    private void lZ() {
        this.Ef.setEnabled(!TextUtils.isEmpty(this.Em));
        this.Eg.setEnabled(this.El.qa());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getString(R.string.current_local_version, new Object[]{this.El.pY().getVersion()}));
        Object[] objArr = new Object[1];
        objArr[0] = this.El.pX() == null ? "UNKNOWN" : this.El.pX();
        stringBuffer.append(getString(R.string.latest_cloud_version, objArr));
        if (this.El.pX() == null) {
            stringBuffer.append(getString(R.string.tips_dont_confirm_need_update));
        } else if (this.El.qa()) {
            stringBuffer.append(getString(R.string.tips_update_latest_version));
        } else {
            stringBuffer.append(getString(R.string.tips_dont_need_update));
        }
        this.Eh.setText(stringBuffer.toString());
        if (this.Eb != null) {
            this.Eb.show();
            mc();
        }
    }

    private void af(Context context) {
        this.pe = new com.xiaopeng.xui.app.e(context, 2131821043);
        this.pe.create();
        this.pe.setCancelable(true);
        this.pe.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$9Nk5FKMDyy5goCZao-ml_iyLuCg
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                OneClickDiagnosisActivity.b(dialogInterface);
            }
        });
        this.pe.a(null);
        this.pe.aZ(false);
        this.pe.dY(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(DialogInterface dialogInterface) {
        EventBus.getDefault().post(new CommonEvent(10002, null));
    }

    private void ma() {
        this.Ek = this.El.pY();
        if (this.Ek == null) {
            finish();
            q.b(this, R.string.alert_logictree_list_empty);
        } else if (this.El.qa()) {
            q.b(this, R.string.logictree_not_lastest_version);
            lZ();
        } else {
            q.b(this, R.string.logictree_lastest_version);
            this.Cq.setCenterText(getString(R.string.one_click_diagnosis_with_version, new Object[]{this.Ek.getVersion()}));
            a(this.Ek.pT());
            mc();
        }
    }

    private void cJ(final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$nNB5Gh6hRwcX-A_OAKKj_oyqsdU
            @Override // java.lang.Runnable
            public final void run() {
                OneClickDiagnosisActivity.this.cL(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void cL(int i) {
        if (this.Eb != null && this.Eb.isShowing()) {
            this.Eb.dismiss();
        }
        if (this.pe != null) {
            this.pe.setMessage(getString(i));
            this.pe.show();
        }
    }

    private void mb() {
        if (!this.El.pZ()) {
            this.Ee.setVisibility(0);
        }
    }

    private void mc() {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$vzeUOA3Lk2aTTpRuzxp4tnnEE3k
            @Override // java.lang.Runnable
            public final void run() {
                OneClickDiagnosisActivity.this.md();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void md() {
        if (this.pe != null && this.pe.isShowing()) {
            this.pe.dismiss();
        }
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        int intValue;
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.continue_last_diagnosis) {
            this.Ee.setVisibility(8);
            this.El.qb();
            return;
        }
        if (id == R.id.end_time) {
            cK(2);
        } else if (id == R.id.start_time) {
            cK(1);
        } else if (view.getTag() != null && (view.getTag() instanceof Integer) && (intValue = ((Integer) view.getTag()).intValue()) > 0 && intValue <= this.Ek.pT().length) {
            this.El.a(this.Ek.pT()[intValue - 1], this.Ei, this.mEndTimeStamp, this.theme != 1 ? 3 : 2);
        }
    }

    private void cK(final int i) {
        final long j;
        if (i == 2) {
            j = this.mEndTimeStamp;
        } else {
            j = this.Ei;
        }
        b.a aVar = new b.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$H9Mz5apIZJkofgNAe_IHqLQhEX4
            @Override // com.xiaopeng.xui.app.b.a
            public final void onDateSet(XDatePicker xDatePicker, int i2, int i3, int i4) {
                OneClickDiagnosisActivity.this.a(i, j, xDatePicker, i2, i3, i4);
            }
        };
        long j2 = j / 1000;
        new com.xiaopeng.xui.app.b(this, aVar, Integer.parseInt(p.a(j2, "yyyy")), Integer.parseInt(p.a(j2, "MM")) - 1, Integer.parseInt(p.a(j2, "dd"))).dV(R.string.select_issue_happen_date).g(getString(R.string.dialog_confirm)).h(getString(R.string.common_cancel)).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(final int i, long j, XDatePicker xDatePicker, int i2, int i3, int i4) {
        this.Ej.setLength(0);
        this.Ej.append(i2);
        this.Ej.append("-");
        int i5 = i3 + 1;
        if (i5 < 10) {
            this.Ej.append(0);
        }
        this.Ej.append(i5);
        this.Ej.append("-");
        if (i4 < 10) {
            this.Ej.append(0);
        }
        this.Ej.append(i4);
        this.Ej.append(" ");
        long j2 = j / 1000;
        new com.xiaopeng.xui.app.f(this, new f.a() { // from class: com.xiaopeng.devtools.view.aftersales.-$$Lambda$OneClickDiagnosisActivity$MrOxwumu49d22zPibLTSc_L2KII
            @Override // com.xiaopeng.xui.app.f.a
            public final void onTimeSet(XTimePicker xTimePicker, int i6, int i7) {
                OneClickDiagnosisActivity.this.a(i, xTimePicker, i6, i7);
            }
        }, Integer.parseInt(p.a(j2, "HH")), Integer.parseInt(p.a(j2, "mm"))).dV(R.string.select_issue_happen_time).g(getString(R.string.dialog_confirm)).h(getString(R.string.common_cancel)).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, XTimePicker xTimePicker, int i2, int i3) {
        if (i2 < 10) {
            this.Ej.append(0);
        }
        this.Ej.append(i2);
        this.Ej.append(":");
        if (i3 < 10) {
            this.Ej.append(0);
        }
        this.Ej.append(i3);
        String stringBuffer = this.Ej.toString();
        switch (i) {
            case 1:
                this.Ei = p.z(stringBuffer, "yyyy-MM-dd HH:mm");
                this.Ec.setText(stringBuffer);
                return;
            case 2:
                this.mEndTimeStamp = p.z(stringBuffer, "yyyy-MM-dd HH:mm");
                this.Ed.setText(stringBuffer);
                return;
            default:
                return;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
        this.El.pW();
        if (this.pe != null) {
            this.pe.dismiss();
            this.pe = null;
        }
        if (this.Eb != null) {
            this.Eb.dismiss();
            this.Eb = null;
        }
        com.xiaopeng.commonfunc.utils.f.l(this);
    }

    private void a(com.xiaopeng.logictree.e[] eVarArr) {
        this.mContainer.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        layoutParams.setMargins(0, 5, 0, 5);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        linearLayout.setWeightSum(3.0f);
        LinearLayout linearLayout2 = linearLayout;
        int i = 0;
        for (com.xiaopeng.logictree.e eVar : eVarArr) {
            i++;
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, 100, 1.0f);
            layoutParams2.setMargins(5, 5, 5, 5);
            button.setLayoutParams(layoutParams2);
            button.setTextColor(-16777216);
            button.setTextSize(30.0f);
            button.setText(eVar.getName());
            button.setTag(Integer.valueOf(i));
            button.setBackgroundResource(R.drawable.btn_background);
            button.setOnClickListener(this);
            com.xiaopeng.lib.utils.c.d("OneClickDiagnosisActivity", "createUI", "Create Button. name[%s] tag[%s]", eVar.getName(), eVar.pR());
            linearLayout2.addView(button);
            if (i % 3 == 0) {
                this.mContainer.addView(linearLayout2);
                linearLayout2 = new LinearLayout(this);
                linearLayout2.setLayoutParams(layoutParams);
                linearLayout2.setOrientation(0);
                linearLayout2.setWeightSum(3.0f);
            }
        }
        if (i % 3 != 0) {
            this.mContainer.addView(linearLayout2);
        }
    }
}
