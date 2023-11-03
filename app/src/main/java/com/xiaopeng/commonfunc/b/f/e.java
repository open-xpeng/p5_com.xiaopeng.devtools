package com.xiaopeng.commonfunc.b.f;

import android.content.Context;
import com.xiaopeng.a.a;
import com.xiaopeng.commonfunc.utils.n;
import com.xiaopeng.lib.utils.j;

/* compiled from: SecurityModel.java */
/* loaded from: classes11.dex */
public class e {
    private Context mContext;
    private com.xiaopeng.commonfunc.a.c nZ;
    private a oB;
    private f oC;
    private d oD;

    public e(Context context, com.xiaopeng.commonfunc.a.c cVar) {
        this.mContext = context;
        this.nZ = cVar;
        this.oB = new a(context);
        this.oC = new f(context);
        this.oD = new d(context);
    }

    public void onDestroy() {
        this.oB.onDestroy();
        this.oC.onDestroy();
        this.oD.onDestroy();
    }

    public void ef() {
        com.xiaopeng.lib.utils.c.g("TestSecurityPresenter", "verifyPsuKey");
        j.execute(new Runnable() { // from class: com.xiaopeng.commonfunc.b.f.-$$Lambda$e$YZCsCiD9-6YHORwf2-S1hxk5-o4
            @Override // java.lang.Runnable
            public final void run() {
                e.this.em();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void em() {
        m(2, this.oD.ee() ? 1 : 2);
    }

    public boolean eg() {
        boolean en = this.oC.en();
        com.xiaopeng.lib.utils.c.g("TestSecurityPresenter", "checkWifiKeyExist res:" + en);
        return en;
    }

    public void eh() {
        if (!eg()) {
            m(3, 2);
            return;
        }
        String str = "jCmX14gy4XLe" + n.getHardwareId().substring(0, 8);
        com.xiaopeng.lib.utils.c.g("TestSecurityPresenter", "verifyWifiKey password:" + str);
        new b(this.mContext, "/mnt/vendor/private/ck58l92i5/wf_c.png", "/mnt/vendor/private/ck58l92i5/wf_r_bks.png", a.f.ed("CHECK_WIFI_KEY"), str).a(new com.xiaopeng.lib.http.c<String, String>() { // from class: com.xiaopeng.commonfunc.b.f.e.1
            @Override // com.xiaopeng.lib.http.c
            public void onSuccess(String str2) {
                int i;
                try {
                    i = Integer.parseInt(str2);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    i = 0;
                }
                e.this.m(3, i == 200 ? 1 : 2);
            }

            @Override // com.xiaopeng.lib.http.c
            /* renamed from: onError */
            public void j(String str2) {
                e.this.m(3, 2);
            }
        });
    }

    public void ei() {
        j.execute(new Runnable() { // from class: com.xiaopeng.commonfunc.b.f.-$$Lambda$e$BaRMeSAyFPsH0F9WYg15f8BEU5w
            @Override // java.lang.Runnable
            public final void run() {
                e.this.el();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void el() {
        boolean z;
        if (!this.oB.ea() || !this.oB.eb()) {
            z = false;
        } else {
            z = true;
        }
        m(1, z ? 1 : 2);
    }

    public void ej() {
        j.execute(new Runnable() { // from class: com.xiaopeng.commonfunc.b.f.-$$Lambda$e$Y3Pxxy00C62HoVAYyyvRGgWyaMo
            @Override // java.lang.Runnable
            public final void run() {
                e.this.ek();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ek() {
        int i;
        if (this.oB.eb()) {
            i = 1;
        } else {
            i = 2;
        }
        m(1, i);
    }

    public boolean dY() {
        return this.oB.dY();
    }

    public boolean dZ() {
        return this.oB.dZ();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(int i, int i2) {
        if (this.nZ != null) {
            this.nZ.onReceiveResult(i, i2);
        }
    }
}
