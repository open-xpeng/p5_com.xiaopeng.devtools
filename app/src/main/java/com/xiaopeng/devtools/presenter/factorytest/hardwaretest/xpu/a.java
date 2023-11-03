package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu;

import android.os.CountDownTimer;
import com.google.gson.Gson;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.http.XpuActivateStatus;
import com.xiaopeng.devtools.utils.d;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.util.HashMap;

/* compiled from: XpuActivatePresenter.java */
/* loaded from: classes12.dex */
public class a {
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.a xB;
    private static final String xA = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("ACTIVATE_XPU");
    private static final String wd = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("CHECK_XPU_FACTORY_ACTIVATE_STATUS");
    private boolean wj = false;
    private CountDownTimer wl = new CountDownTimer(360000, 360000) { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.a.1
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.this.iw();
            a.this.xB.d(3, MyApplication.getContext().getString(R.string.xpu_activate_check_timeout));
        }
    };
    private Runnable wm = new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.a.2
        @Override // java.lang.Runnable
        public void run() {
            a.this.xB.d(-1, MyApplication.getContext().getString(R.string.xpu_activate_checking));
            a.this.iG();
        }
    };
    private String mVin = r.eH();

    public a(com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.a aVar) {
        this.xB = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void iw() {
        c.f("XpuActivatePresenter", "stopCheckXpuStatus");
        this.wj = false;
    }

    public void jw() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.-$$Lambda$a$js79eDX0Ub-TkaEnu3xPs-uGV2Q
            @Override // java.lang.Runnable
            public final void run() {
                a.this.jx();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void jx() {
        c.f("XpuActivatePresenter", "activateXpu");
        if (this.mVin.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("vin", this.mVin);
            IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            iHttp.cancelTag(xA);
            iHttp.bizHelper().post(xA, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().tag(xA).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.a.3
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    com.xiaopeng.lib.http.a.a a = d.a(iResponse);
                    if (a != null && a.getCode() == 200) {
                        a.this.iF();
                    } else {
                        a.this.xB.d(3, MyApplication.getContext().getString(R.string.xpu_activate_fail));
                    }
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    a.this.xB.d(3, MyApplication.getContext().getString(R.string.xpu_activate_fail));
                }
            });
            return;
        }
        c.i("XpuActivatePresenter", "activateXpu fail VIN = " + this.mVin);
        this.xB.d(3, MyApplication.getContext().getString(R.string.xpu_activate_fail));
    }

    public void iF() {
        this.wj = true;
        j.execute(this.wm);
        this.wl.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void iG() {
        if (!this.wj) {
            return;
        }
        com.xiaopeng.devtools.system.b.c.sleep(5000L);
        c.f("XpuActivatePresenter", "checkXpuActivateStatus");
        if (this.mVin.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("vin", this.mVin);
            IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            iHttp.cancelTag(wd);
            iHttp.bizHelper().post(wd, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().tag(wd).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.a.4
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    com.xiaopeng.lib.http.a.a a = d.a(iResponse);
                    if (a == null || a.getCode() != 200) {
                        a.this.wl.cancel();
                        a.this.xB.d(-1, MyApplication.getContext().getString(R.string.xpu_activate_check_fail));
                        return;
                    }
                    XpuActivateStatus xpuActivateStatus = (XpuActivateStatus) new Gson().fromJson(a.getData(), (Class<Object>) XpuActivateStatus.class);
                    c.f("XpuActivatePresenter", "status:" + xpuActivateStatus);
                    a.this.xB.d(xpuActivateStatus.getStateCode(), xpuActivateStatus.getStateDesc());
                    if (xpuActivateStatus.getStateCode() != 1) {
                        a.this.wl.cancel();
                    } else {
                        a.this.iG();
                    }
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    a.this.iG();
                }
            });
            return;
        }
        c.i("XpuActivatePresenter", "checkXpuActivateStatus fail VIN = " + this.mVin);
        this.wl.cancel();
        this.xB.d(-1, MyApplication.getContext().getString(R.string.xpu_activate_check_fail));
    }

    public void onDestroy() {
        this.wl.cancel();
        iw();
    }
}
