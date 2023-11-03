package com.xiaopeng.logictree.handler;

import android.app.Application;
import android.text.TextUtils;
import com.xiaopeng.commonfunc.bean.event.CommonEvent;
import com.xiaopeng.commonfunc.utils.p;
import com.xiaopeng.logictree.R;
import com.xiaopeng.logictree.bean.LogicResponseData;
import com.xiaopeng.xui.app.d;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DisplayHandleResult.java */
/* loaded from: classes12.dex */
public class d extends h {
    private com.xiaopeng.commonfunc.utils.h Yi;
    private String Yj;
    private int Yk;
    private com.xiaopeng.commonfunc.a.a pf;

    public d(Application application) {
        super(application);
        this.Yk = -1;
        this.pf = new com.xiaopeng.commonfunc.a.a() { // from class: com.xiaopeng.logictree.handler.d.1
            @Override // com.xiaopeng.commonfunc.a.a
            public void onSuccess(String str, String str2) {
                d.this.dS(str);
            }

            @Override // com.xiaopeng.commonfunc.a.a
            public void onFailure() {
                d.this.dS(null);
            }
        };
        this.CLASS_NAME = "DisplayHandleResult";
        this.Yi = new com.xiaopeng.commonfunc.utils.h(this.context, this.pf);
    }

    @Override // com.xiaopeng.logictree.handler.h
    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        String str;
        super.a(aVar);
        if (a(this.YA, new String[]{"1"})) {
            this.Yj = this.YA[1];
            str = this.YA.length > 2 ? this.YA[2] : null;
        } else {
            if (a(this.YA, new String[]{"2"})) {
                try {
                    this.Yk = Integer.parseInt(this.YA[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                this.Yj = String.format(this.YA[1], Integer.valueOf(this.Yk));
                if (this.YA.length > 3) {
                    str = this.YA[3];
                }
            } else {
                this.Yj = "";
            }
        }
        b(aVar, this.Yj);
        String[] a = a(aVar, str);
        if (aVar.pG() == 1) {
            this.Yi.setDirection(1);
            this.Yi.a(this.mApplication, "/cache/aftersales", a, aVar.pF(), 2);
        } else {
            this.Yi.setDirection(2);
            this.Yi.a(a, aVar.pF(), 2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dS(String str) {
        String str2 = this.CLASS_NAME;
        com.xiaopeng.lib.utils.c.i(str2, this.Yb.pF() + " Handle Result : %s", this.Yj);
        final StringBuffer stringBuffer = new StringBuffer();
        if (this.Yb.pG() == 1) {
            stringBuffer.append(this.Yj);
            stringBuffer.append("\n");
            if (!TextUtils.isEmpty(str)) {
                stringBuffer.append(this.context.getString(R.string.data_download_path));
            } else {
                stringBuffer.append(this.context.getString(R.string.log_data_transfer_fail));
            }
            EventBus.getDefault().post(new LogicResponseData(stringBuffer.toString(), "", str, null));
            com.xiaopeng.logictree.d.pP();
        } else {
            stringBuffer.append(this.Yj);
            stringBuffer.append("\n");
            if (TextUtils.isEmpty(str)) {
                stringBuffer.append(this.context.getString(R.string.log_data_transfer_fail));
            } else {
                stringBuffer.append(this.context.getString(R.string.data_udisk_path, str));
            }
            com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$d$XCQriFKj3q9gPTyCtbb-6YsWoJU
                @Override // java.lang.Runnable
                public final void run() {
                    d.this.a(stringBuffer);
                }
            });
        }
        if (this.Yk > 0) {
            com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$d$CmywtCnagFmmWinMrrOM7a8voTk
                @Override // java.lang.Runnable
                public final void run() {
                    d.this.qe();
                }
            }, this.Yk * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(StringBuffer stringBuffer) {
        com.xiaopeng.xui.app.c a = new com.xiaopeng.xui.app.c(this.context).e(this.Yb.pF()).f(stringBuffer.toString()).a(this.context.getString(R.string.dialog_confirm), new d.a() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$d$8hqIgsjQ-JNxHf8lcT1gQlZ_JCI
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                cVar.dismiss();
            }
        });
        a.b(this.context.getString(R.string.run_catch_log_activity), new d.a() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$d$fvNgxHVJ95nW4xiyNIO5nmg-_Q4
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                d.this.y(cVar, i);
            }
        });
        com.xiaopeng.lib.utils.c.f(this.CLASS_NAME, "show dialog");
        a.dX(2008);
        a.show();
        EventBus.getDefault().post(new CommonEvent(CommonEvent.DIAGNOSIS_DISMISS_LOADING_DIALOG, null));
        com.xiaopeng.lib.utils.j.execute(new Runnable() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$d$neDP0qRc7JouK5-_wg1v_8EEpaA
            @Override // java.lang.Runnable
            public final void run() {
                d.this.qf();
            }
        });
        com.xiaopeng.logictree.d.pP();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(com.xiaopeng.xui.app.c cVar, int i) {
        com.xiaopeng.commonfunc.utils.a.e(this.context, "com.xiaopeng.devtools", "com.xiaopeng.devtools.view.log.GrabLogActivity");
        cVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void qf() {
        com.xiaopeng.commonfunc.utils.l.y("/data/Log/aftersales", this.Yb.pF() + "_" + p.eI());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void qe() {
        com.xiaopeng.commonfunc.utils.l.f(this.context, "ONE CLICK DIAGNOSIS REBOOT");
    }

    private String[] a(com.xiaopeng.logictree.a aVar, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("/data/Log/log0");
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(";");
            for (int i = 0; i < split.length; i++) {
                String str2 = split[i];
                char c = 65535;
                if (str2.hashCode() == -1511905523 && str2.equals("tboxlog")) {
                    c = 0;
                }
                if (c == 0) {
                    com.xiaopeng.commonfunc.utils.l.bc("/cache/aftersales/tboxlog");
                    arrayList.add("/cache/aftersales/tboxlog");
                } else {
                    arrayList.add(split[i]);
                }
            }
        }
        ArrayList<String> a = com.xiaopeng.commonfunc.utils.i.a("/data/Log", "main.txt", "log[1-7]", false, aVar.getStartTime(), aVar.pD());
        if (!a.isEmpty()) {
            arrayList.addAll(a);
            a.clear();
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void b(com.xiaopeng.logictree.a aVar, String str) {
        String str2 = "UNKNOWN";
        switch (aVar.pG()) {
            case 1:
                str2 = this.context.getString(R.string.entry_remote_diagnosis);
                break;
            case 2:
                str2 = this.context.getString(R.string.entry_check_mode);
                break;
            case 3:
                str2 = this.context.getString(R.string.entry_tester_ui);
                break;
        }
        com.xiaopeng.commonfunc.utils.b.eo().recordLogicAction(aVar.pF(), str.replaceAll("\n", "").replaceAll("\r", ""), p.a(aVar.getStartTime() / 1000, "yyyy-MM-dd HH:mm"), p.a(aVar.pD() / 1000, "yyyy-MM-dd HH:mm"), p.a(System.currentTimeMillis() / 1000, null), str2, aVar.pH());
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        super.destroy();
        this.Yi.destroy();
    }
}
