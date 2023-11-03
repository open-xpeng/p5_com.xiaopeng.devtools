package com.xiaopeng.devtools.presenter.aftersales;

import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.aftersales.ShellCmdListener;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;

/* compiled from: CommonCmdPresenter.java */
/* loaded from: classes12.dex */
public class b {
    private com.xiaopeng.devtools.view.aftersales.b vo;
    private ShellCmdListener vp = new ShellCmdListener() { // from class: com.xiaopeng.devtools.presenter.aftersales.b.1
        public void onShellResponse(int i, String str, boolean z) {
            com.xiaopeng.lib.utils.c.f("CommonCmdPresenter", "errorcode :" + i + ", resultPath:" + str + ", isCloudCmd:" + z);
            if (!z && b.this.vo != null) {
                if (i != 0 || str == null || str.length() <= 0) {
                    if (!TextUtils.isEmpty(b.this.mAction)) {
                        com.xiaopeng.devtools.utils.b.recordRepairModeAction(b.this.mAction, EcuUpdateResult.RESULT_FAIL);
                        b.this.mAction = "";
                    }
                    b.this.vo.cS(MyApplication.getContext().getString(R.string.excute_cmd_fail));
                    return;
                }
                if (!TextUtils.isEmpty(b.this.mAction)) {
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction(b.this.mAction, EcuUpdateResult.RESULT_SUCCESS);
                    b.this.mAction = "";
                }
                if (com.xiaopeng.devtools.utils.g.cy(str) <= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) {
                    b.this.vo.cS(com.xiaopeng.devtools.utils.g.aV(str));
                } else {
                    b.this.vo.cS(MyApplication.getContext().getString(R.string.file_too_large));
                }
            }
        }
    };
    private String mAction = "";
    private AfterSalesManager vl = (AfterSalesManager) MyApplication.getContext().getSystemService("xiaopeng_aftersales");

    public b(com.xiaopeng.devtools.view.aftersales.b bVar) {
        this.vo = bVar;
        this.vl.registerShellCmdListener(this.vp);
    }

    public void bO(String str) {
        this.mAction = "getprop " + str;
        this.vl.executeShellCmd(1, str, false);
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    public void bP(String str) {
        this.mAction = "cat " + str;
        this.vl.executeShellCmd(2, str, false);
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    public void ia() {
        this.mAction = "df -h";
        this.vl.executeShellCmd(3, "", false);
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    public void ib() {
        this.mAction = "mount";
        this.vl.executeShellCmd(4, "", false);
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    public void bQ(String str) {
        this.mAction = "du -sh " + str;
        this.vl.executeShellCmd(5, str, false);
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    public void bR(String str) {
        this.mAction = "top " + str;
        this.vl.executeShellCmd(6, str, false);
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    public void ic() {
        this.mAction = "dumpsys meminfo";
        this.vl.executeShellCmd(7, "", false);
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    public void ie() {
        this.mAction = "ifconfig";
        this.vl.executeShellCmd(9, "", false);
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    public void bS(String str) {
        this.mAction = "ls -al " + str;
        this.vl.executeShellCmd(8, str, false);
        com.xiaopeng.devtools.utils.b.recordRepairModeAction(this.mAction, "triggered");
    }

    public void onDestroy() {
        this.vl.unregisterShellCmdListener(this.vp);
    }
}
