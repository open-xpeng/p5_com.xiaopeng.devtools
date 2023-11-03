package com.xiaopeng.commonfunc.utils;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.xiaopeng.commonfunc.R;
import com.xiaopeng.commonfunc.bean.event.CommonEvent;
import java.io.File;
import org.greenrobot.eventbus.EventBus;

/* compiled from: FileUploader.java */
/* loaded from: classes11.dex */
public class h {
    private Context mContext;
    private int mDirection;
    private com.xiaopeng.xui.app.e pe;
    private com.xiaopeng.commonfunc.a.a pf;
    private String pg;
    private int ph;
    private String[] pi;
    private int mStep = 0;
    private final BroadcastReceiver pj = new BroadcastReceiver() { // from class: com.xiaopeng.commonfunc.utils.h.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            com.xiaopeng.lib.utils.c.g("FileUploader", "onReceive action-->" + action);
            if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                com.xiaopeng.lib.utils.c.i("FileUploader", "onReceive mStep [%d]", Integer.valueOf(h.this.mStep));
                if (h.this.mStep == 1000) {
                    h.this.mStep = 0;
                    h.this.a(h.this.pi, h.this.pg, h.this.ph);
                }
            }
        }
    };

    public h(Context context, com.xiaopeng.commonfunc.a.a aVar) {
        this.mContext = context;
        this.pf = aVar;
        init();
    }

    public void setDirection(int i) {
        this.mDirection = i;
    }

    private void init() {
        com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$h$WcmU2jxiFuq33ZZRduSz3twaipQ
            @Override // java.lang.Runnable
            public final void run() {
                h.this.er();
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addDataScheme("file");
        this.mContext.registerReceiver(this.pj, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void er() {
        this.pe = new com.xiaopeng.xui.app.e(this.mContext, R.style.XAppTheme_XDialog_Loading);
        this.pe.create();
        this.pe.setCancelable(true);
        this.pe.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$h$mNpI28pydYDKriY2d6jzugThv74
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                h.this.a(dialogInterface);
            }
        });
        this.pe.a(null);
        this.pe.aZ(false);
        this.pe.dY(0);
        this.pe.getWindow().setType(2047);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface) {
        this.mStep = 0;
        if (this.pf != null) {
            this.pf.onFailure();
        }
    }

    public void a(final String[] strArr, final String str, final int i) {
        if (this.mDirection == 2) {
            com.xiaopeng.lib.utils.c.g("FileUploader", "copy diagnosis files to udisk");
            final String V = i.V(this.mContext);
            com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$h$B5Gth5CrnC1GoYBe90rE9INxobY
                @Override // java.lang.Runnable
                public final void run() {
                    h.this.a(V, str, strArr, i);
                }
            });
            return;
        }
        com.xiaopeng.lib.utils.c.a("FileUploader", "uploadFile2Cloud wrong direction [%d]", Integer.valueOf(this.mDirection));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(final String str, final String str2, final String[] strArr, final int i) {
        EventBus.getDefault().post(new CommonEvent(CommonEvent.DIAGNOSIS_DISMISS_LOADING_DIALOG, null));
        if (TextUtils.isEmpty(str)) {
            this.pe.setMessage(this.mContext.getString(R.string.tips_insert_udisk));
            this.pe.show();
            this.pg = str2;
            this.pi = strArr;
            this.ph = i;
            this.mStep = 1000;
            return;
        }
        this.pe.setMessage(this.mContext.getString(R.string.tips_copying_data_2_udisk));
        this.pe.show();
        com.xiaopeng.lib.utils.j.execute(new Runnable() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$h$G0F9dp8F_V5X7_LdJXPk496SNp8
            @Override // java.lang.Runnable
            public final void run() {
                h.this.b(str, strArr, str2, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, String[] strArr, String str2, int i) {
        a a2 = a(str, strArr, str2, i);
        eq();
        if (this.pf != null) {
            if (!TextUtils.isEmpty(a2.filePath)) {
                this.pf.onSuccess(i.aU(a2.filePath), a2.password);
            } else {
                this.pf.onFailure();
            }
        }
    }

    private a a(String str, String[] strArr, String str2, int i) {
        String bI;
        a aVar = new a();
        switch (i) {
            case 1:
                if (this.mDirection == 2) {
                    String eH = n.eH();
                    bI = eH.length() > 8 ? eH.substring(eH.length() - 8) : "XIAOPENG";
                } else {
                    bI = d.bI(8);
                }
                String str3 = str + File.separator + str2 + "_" + p.eI() + ".zip";
                if (r.a(strArr, str3, bI)) {
                    aVar.filePath = str3;
                    aVar.password = bI;
                    break;
                }
                break;
            case 2:
                String str4 = str2 + "_" + p.eI();
                String str5 = "/cache/temp" + File.separator + str4 + ".zip";
                String str6 = str + File.separator + str4 + ".xp";
                if (i.aW("/cache/temp" + File.separator) && r.a(strArr, str5, null) && com.xiaopeng.lib.utils.b.a.r("@)!%Xp0109Motors", str5, str6)) {
                    aVar.filePath = str6;
                    aVar.password = null;
                }
                i.deleteFile(str5);
                break;
        }
        return aVar;
    }

    public void a(final Application application, final String str, final String[] strArr, final String str2, final int i) {
        if (this.mDirection == 1) {
            com.xiaopeng.lib.utils.j.execute(new Runnable() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$h$sFm8Ci9IBintEiKVcY58Uj-MqN4
                @Override // java.lang.Runnable
                public final void run() {
                    h.this.a(str, strArr, str2, i, application);
                }
            });
        } else {
            com.xiaopeng.lib.utils.c.a("FileUploader", "uploadFile2Cloud wrong direction [%d]", Integer.valueOf(this.mDirection));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, String[] strArr, String str2, int i, Application application) {
        final a a2 = a(str, strArr, str2, i);
        g.a(application, a2.filePath, new com.xiaopeng.commonfunc.a.b() { // from class: com.xiaopeng.commonfunc.utils.h.2
            @Override // com.xiaopeng.commonfunc.a.b
            public void onStart(String str3, String str4) {
                com.xiaopeng.lib.utils.c.f("FileUploader", "uploadFile2Oss onStart s:" + str3 + ", s1:" + str4);
            }

            @Override // com.xiaopeng.commonfunc.a.b
            public void onSuccess(String str3, String str4) {
                com.xiaopeng.lib.utils.c.g("FileUploader", "uploadFile2Oss onSuccess s:" + str3 + ", s1:" + str4);
                if (h.this.pf != null) {
                    h.this.pf.onSuccess(str3.replaceFirst("https://xp-security.oss-cn-hangzhou.aliyuncs.com/", ""), a2.password);
                }
                i.deleteFile(a2.filePath);
            }

            @Override // com.xiaopeng.commonfunc.a.b
            public void a(String str3, String str4, Exception exc) {
                com.xiaopeng.lib.utils.c.i("FileUploader", "uploadFile2Oss onFailure s:" + str3 + ", s1:" + str4 + ", e:" + exc.toString());
                if (h.this.pf != null) {
                    h.this.pf.onFailure();
                }
                i.deleteFile(a2.filePath);
            }
        });
    }

    public void destroy() {
        this.mContext.unregisterReceiver(this.pj);
        eq();
        this.pe = null;
    }

    private void eq() {
        if (this.pe != null && this.pe.isShowing()) {
            this.pe.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileUploader.java */
    /* loaded from: classes11.dex */
    public class a {
        public String filePath;
        public String password;

        private a() {
        }
    }
}
