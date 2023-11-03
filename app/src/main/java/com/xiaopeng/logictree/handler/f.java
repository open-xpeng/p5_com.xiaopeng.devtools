package com.xiaopeng.logictree.handler;

import android.app.Application;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.xiaopeng.commonfunc.bean.event.CommonEvent;
import com.xiaopeng.logictree.R;
import com.xiaopeng.logictree.bean.LogicResponseData;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.widget.XTextView;
import org.greenrobot.eventbus.EventBus;

/* compiled from: InterActive.java */
/* loaded from: classes12.dex */
public class f extends h {
    private com.xiaopeng.commonfunc.utils.h Yi;
    private com.xiaopeng.xui.app.c Yt;
    protected g Yu;
    private com.xiaopeng.commonfunc.a.a pf;

    public f(Application application) {
        super(application);
        this.Yu = new g() { // from class: com.xiaopeng.logictree.handler.f.1
            @Override // com.xiaopeng.logictree.handler.g
            public void onResult(String str) {
                com.xiaopeng.logictree.d.dR(str);
                if (f.this.Yt != null) {
                    f.this.Yt.dismiss();
                    f.this.Yt = null;
                }
            }
        };
        this.pf = new com.xiaopeng.commonfunc.a.a() { // from class: com.xiaopeng.logictree.handler.f.2
            @Override // com.xiaopeng.commonfunc.a.a
            public void onSuccess(String str, String str2) {
                f.this.ab(str, str2);
            }

            @Override // com.xiaopeng.commonfunc.a.a
            public void onFailure() {
                com.xiaopeng.logictree.d.pQ();
            }
        };
        this.CLASS_NAME = "InterActive";
        this.Yi = new com.xiaopeng.commonfunc.utils.h(this.context, this.pf);
    }

    @Override // com.xiaopeng.logictree.handler.h
    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        super.a(aVar);
        if (a(this.YA, new String[]{"1"})) {
            ab("", "");
        } else if (a(this.YA, new String[]{"2"})) {
            String[] split = this.YA[2].split(";");
            if (aVar.pG() == 1) {
                this.Yi.setDirection(1);
                this.Yi.a(this.mApplication, "/cache/aftersales", split, aVar.pF(), 1);
            } else {
                this.Yi.setDirection(2);
                this.Yi.a(split, aVar.pF(), 1);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab(final String str, final String str2) {
        final StringBuffer stringBuffer = new StringBuffer();
        if (this.Yb.pG() == 1) {
            if (this.YA.length > 1) {
                stringBuffer.append(this.YA[1]);
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                    stringBuffer.append("\n");
                    stringBuffer.append(this.context.getString(R.string.data_decrypt_password, str2));
                    stringBuffer.append("\n");
                    stringBuffer.append(this.context.getString(R.string.data_download_path));
                }
                com.xiaopeng.lib.utils.c.i(this.CLASS_NAME, "interactive : %s", stringBuffer.toString());
                EventBus.getDefault().post(new LogicResponseData(stringBuffer.toString(), com.xiaopeng.commonfunc.utils.d.bI(32), str, this.Yb.pE().pJ()));
                return;
            }
            return;
        }
        com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$f$XTzJ4cFVTIjfAeqmlr9MQAC3KlQ
            @Override // java.lang.Runnable
            public final void run() {
                f.this.a(stringBuffer, str2, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(StringBuffer stringBuffer, String str, String str2) {
        this.Yt = new com.xiaopeng.xui.app.c(this.context);
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.dialog_logictree_interactive, this.Yt.qu(), false);
        if (this.YA.length > 1) {
            stringBuffer.append(this.YA[1]);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                stringBuffer.append("\n");
                stringBuffer.append(this.context.getString(R.string.data_udisk_path, str2));
                stringBuffer.append("\n");
                stringBuffer.append(this.context.getString(R.string.udisk_data_decrypt_password));
            }
            stringBuffer.append(this.context.getString(R.string.tips_confirmation_for_diagnosis));
            com.xiaopeng.lib.utils.c.i(this.CLASS_NAME, "interactive : %s", stringBuffer.toString());
            ((XTextView) inflate.findViewById(R.id.interactive_purpose)).setText(stringBuffer.toString());
        }
        ((ListView) inflate.findViewById(R.id.interactive_selector)).setAdapter((ListAdapter) new InterActiveAdapter(this.Yb.pE().pJ(), this.Yu));
        this.Yt.dV(R.string.info_confirmation).d(inflate, false);
        this.Yt.aW(true);
        this.Yt.a(new d.b() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$f$aYsnTJrhkxIBbzVDCizdX8B49Q0
            @Override // com.xiaopeng.xui.app.d.b
            public final boolean onClose(com.xiaopeng.xui.app.c cVar) {
                boolean b;
                b = f.b(cVar);
                return b;
            }
        });
        this.Yt.aY(false);
        this.Yt.dX(2008);
        this.Yt.show();
        EventBus.getDefault().post(new CommonEvent(CommonEvent.DIAGNOSIS_DISMISS_LOADING_DIALOG, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean b(com.xiaopeng.xui.app.c cVar) {
        EventBus.getDefault().post(new CommonEvent(10002, null));
        return false;
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        super.destroy();
        this.Yi.destroy();
        if (this.Yt != null) {
            this.Yt.dismiss();
            this.Yt = null;
        }
    }
}
