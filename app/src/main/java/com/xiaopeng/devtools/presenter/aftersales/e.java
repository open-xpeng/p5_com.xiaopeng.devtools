package com.xiaopeng.devtools.presenter.aftersales;

import android.text.TextUtils;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.aftersales.EncryptShListener;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.lib.utils.j;
import java.io.File;

/* compiled from: EncryptShPresenter.java */
/* loaded from: classes12.dex */
public class e {
    private com.xiaopeng.devtools.view.aftersales.c vx;
    private int mStep = 0;
    private String vy = null;
    private String vz = null;
    private EncryptShListener vA = new EncryptShListener() { // from class: com.xiaopeng.devtools.presenter.aftersales.e.1
        public void onEncryptShResponse(int i, String str, String str2, boolean z) {
            String string;
            if (z) {
                return;
            }
            String str3 = null;
            com.xiaopeng.lib.utils.c.f("EncryptShPresenter", "onEncryptShResponse errorcode: " + i + ", resultPath:" + str + ", outputPath:" + str2);
            if (i == 0 && com.xiaopeng.devtools.utils.g.cy(str2) > 0) {
                str3 = com.xiaopeng.devtools.utils.g.aU(str2) + ".xp";
                com.xiaopeng.lib.utils.b.a.r("@)!%Xp0109Motors", str2, e.this.vy + File.separatorChar + str3);
            }
            com.xiaopeng.devtools.utils.g.aX(str2);
            com.xiaopeng.devtools.utils.g.aX(e.this.vy + File.separatorChar + e.this.vz);
            if (e.this.vx != null) {
                com.xiaopeng.devtools.view.aftersales.c cVar = e.this.vx;
                if (i == 0) {
                    if (str3 != null) {
                        string = MyApplication.getContext().getString(R.string.execute_encrypt_sh_tool_success_withfile, str3);
                    } else {
                        string = MyApplication.getContext().getString(R.string.execute_encrypt_sh_tool_success);
                    }
                } else {
                    string = MyApplication.getContext().getString(R.string.execute_encrypt_sh_tool_fail);
                }
                cVar.cS(string);
                e.this.vx.ab(true);
            }
            if (!TextUtils.isEmpty(e.this.mAction)) {
                com.xiaopeng.devtools.utils.b.recordRepairModeAction(e.this.mAction, i == 0 ? EcuUpdateResult.RESULT_SUCCESS : EcuUpdateResult.RESULT_FAIL);
                e.this.mAction = "";
            }
        }
    };
    private String mAction = "";
    private AfterSalesManager vl = (AfterSalesManager) MyApplication.getContext().getSystemService("xiaopeng_aftersales");

    public e(com.xiaopeng.devtools.view.aftersales.c cVar) {
        this.vx = cVar;
        this.vl.registerEncryptShListener(this.vA);
    }

    public void ii() {
        this.mStep = 0;
    }

    public void bT(String str) {
        String str2;
        if (str != null) {
            str2 = str + File.separatorChar + "xptools";
        } else {
            str2 = null;
        }
        this.vy = str2;
    }

    public void ij() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$e$uXdwxrdYBx1eDwS5haalLRPAL30
            @Override // java.lang.Runnable
            public final void run() {
                e.this.ik();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ik() {
        String string;
        switch (this.mStep) {
            case 0:
                if (this.vx != null) {
                    this.vx.cS(MyApplication.getContext().getString(R.string.checking_encrypt_sh_tool));
                    this.vx.ab(false);
                }
                this.vz = com.xiaopeng.devtools.utils.g.H(this.vy, ".zip");
                this.mStep = this.vz != null ? 1 : 2;
                if (this.vx != null) {
                    com.xiaopeng.devtools.view.aftersales.c cVar = this.vx;
                    if (this.vz != null) {
                        string = MyApplication.getContext().getString(R.string.confirm_to_exec_udisk_tool, this.vz);
                    } else {
                        string = MyApplication.getContext().getString(R.string.udisk_no_encrypt_sh_tool);
                    }
                    cVar.cS(string);
                    this.vx.ab(true);
                    return;
                }
                return;
            case 1:
                if (this.vx != null) {
                    this.vx.cS(MyApplication.getContext().getString(R.string.executing_encrypt_sh_tool, this.vz));
                    this.vx.ab(false);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("execute xiaopeng tool ");
                sb.append(this.vz);
                sb.append(" ");
                sb.append("md5: ");
                sb.append(com.xiaopeng.devtools.utils.g.cB(this.vy + File.separatorChar + this.vz));
                this.mAction = sb.toString();
                com.xiaopeng.devtools.utils.g.x(this.vy + File.separatorChar + this.vz, "/cache/aftersales");
                this.vl.executeEncryptSh("/cache/aftersales", false);
                this.mStep = 2;
                com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
                return;
            case 2:
                if (this.vx != null) {
                    this.vx.dismissDialog();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onDestroy() {
        this.vl.unregisterEncryptShListener(this.vA);
    }
}
