package com.xiaopeng.devtools.presenter.aftersales;

import android.car.hardware.CarPropertyValue;
import android.os.CountDownTimer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.a.a;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.aftersales.BleList;
import com.xiaopeng.devtools.bean.car.CarBase;
import com.xiaopeng.devtools.bean.car.TboxReplaceDevicesId;
import com.xiaopeng.devtools.bean.http.XpuActivateStatus;
import com.xiaopeng.devtools.utils.i;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.utils.u;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.utils.j;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: UpdateDevicesIdPresenter.java */
/* loaded from: classes12.dex */
public class g {
    private static final String vY = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("UPDATE_CDUID");
    private static final String vZ = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("UPDATE_ICCID");
    private static final String wa = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("UPDATE_NFC_SEID");
    private static final String wb = i.cC("UPDATE_BLEKEY_MACADDR") + a.f.ed("UPDATE_BLEKEY_MACADDR");
    private static final String wc = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("REPLACE_XPU");
    private static final String wd = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("CHECK_XPU_ACTIVATE_STATUS");
    private com.xiaopeng.devtools.view.aftersales.e wi;
    private boolean wj = false;
    private CountDownTimer wl = new CountDownTimer(300000, 300000) { // from class: com.xiaopeng.devtools.presenter.aftersales.g.1
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            g.this.iw();
            g.this.wi.S(null, MyApplication.getContext().getString(R.string.xpu_activate_check_timeout));
        }
    };
    private Runnable wm = new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.g.2
        @Override // java.lang.Runnable
        public void run() {
            g.this.wi.S(null, MyApplication.getContext().getString(R.string.xpu_activate_checking));
            g.this.iG();
        }
    };
    private String mCduId = r.getHardwareId();
    private String mIccid = "";
    private String we = "";
    private String wf = "";
    private String wg = "";
    private BleList wh = null;
    private String mVin = r.eH();
    private String[] wk = a.C0041a.getString("BLE_POSITION_NAMES").split(",");

    public g(com.xiaopeng.devtools.view.aftersales.e eVar) {
        this.wi = eVar;
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void iw() {
        com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "stopCheckXpuStatus");
        this.wj = false;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        BleList.Ble[] slaveList;
        int propertyId = carPropertyValue.getPropertyId();
        if (propertyId == 554700870) {
            TboxReplaceDevicesId tboxReplaceDevicesId = (TboxReplaceDevicesId) new Gson().fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxReplaceDevicesId.class);
            com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "ID_TBOX_REPLACE_REQ :" + tboxReplaceDevicesId.toString());
            this.mIccid = tboxReplaceDevicesId.getIccid();
            this.wi.cV(this.mIccid);
            if (a.b.getBoolean("SUPPORT_NFC_KEY")) {
                this.we = tboxReplaceDevicesId.getNfc_seid();
                this.wi.cW(this.we);
            }
            if (a.b.getBoolean("SUPPORT_BLE_KEY") && !a.b.getBoolean("SUPPORT_MULTI_BLE_KEY")) {
                this.wf = tboxReplaceDevicesId.getBle_mac();
                this.wi.cX(this.wf);
            }
        } else if (propertyId == 554700879) {
            com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "ID_TBOX_MUL_BLE_REPLACE : " + carPropertyValue.getValue());
            this.wh = (BleList) new Gson().fromJson((String) ((CarBase) new Gson().fromJson((String) carPropertyValue.getValue(), new TypeToken<CarBase<String>>() { // from class: com.xiaopeng.devtools.presenter.aftersales.g.3
            }.getType())).getContent(), (Class<Object>) BleList.class);
            if (this.wk.length > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.wk[0]);
                sb.append("@");
                sb.append(this.wh.getMainMac());
                for (BleList.Ble ble : this.wh.getSlaveList()) {
                    sb.append("\r\n");
                    sb.append(this.wk[ble.getPosition()]);
                    sb.append("@");
                    sb.append(ble.getSlaveMac());
                }
                this.wf = sb.toString();
                this.wi.cX(this.wf);
            }
        }
    }

    public void ix() {
        com.xiaopeng.devtools.model.a.d.fz().sendRenewalPartsRequest("replace");
        if (a.b.getBoolean("SUPPORT_MULTI_BLE_KEY")) {
            com.xiaopeng.devtools.model.a.d.fz().sendMultiBleRenewalRequest(new Gson().toJson(new CarBase("ble_replace_req", "")));
        }
    }

    public String getCduId() {
        return this.mCduId;
    }

    public String getVin() {
        return this.mVin;
    }

    public String getIccid() {
        return this.mIccid;
    }

    public String iy() {
        return this.we;
    }

    public String iz() {
        return this.wf;
    }

    public String getXpuSn() {
        return this.wg;
    }

    public void iA() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$g$9tixcu8-mDwUSVTcH1utiIb0tqo
            @Override // java.lang.Runnable
            public final void run() {
                g.this.iL();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void iL() {
        com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "updateCduid2Cloud");
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (this.mVin.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("vin", this.mVin);
            hashMap.put("cduId", this.mCduId);
            hashMap.put("time", String.valueOf(currentTimeMillis));
            IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            iHttp.cancelTag(vY);
            iHttp.bizHelper().post(vY, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().tag(vY).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.aftersales.g.4
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    boolean d = com.xiaopeng.devtools.utils.d.d(iResponse);
                    g.this.wi.ad(d);
                    if (d) {
                        com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace CDU:" + g.this.mCduId, EcuUpdateResult.RESULT_SUCCESS);
                        return;
                    }
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace CDU:" + g.this.mCduId, EcuUpdateResult.RESULT_FAIL);
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    g.this.wi.ad(false);
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace CDU:" + g.this.mCduId, EcuUpdateResult.RESULT_FAIL);
                }
            });
            return;
        }
        com.xiaopeng.lib.utils.c.i("UpdateDevicesIdPresenter", "updateCduid fail VIN = " + this.mVin);
    }

    public void iB() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$g$uj2PaFg1-lmucONCz3kSGdVWrHE
            @Override // java.lang.Runnable
            public final void run() {
                g.this.iK();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void iK() {
        com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "updateIccid2Cloud");
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (this.mVin.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("vin", this.mVin);
            hashMap.put("time", String.valueOf(currentTimeMillis));
            hashMap.put("iccid", this.mIccid);
            IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            iHttp.cancelTag(vZ);
            iHttp.bizHelper().post(vZ, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().tag(vZ).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.aftersales.g.5
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    boolean d = com.xiaopeng.devtools.utils.d.d(iResponse);
                    g.this.wi.ae(d);
                    if (d) {
                        com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace TBOX:" + g.this.mIccid, EcuUpdateResult.RESULT_SUCCESS);
                        return;
                    }
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace TBOX:" + g.this.mIccid, EcuUpdateResult.RESULT_FAIL);
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    g.this.wi.ae(false);
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace TBOX:" + g.this.mIccid, EcuUpdateResult.RESULT_FAIL);
                }
            });
            return;
        }
        com.xiaopeng.lib.utils.c.i("UpdateDevicesIdPresenter", "updateIccid2Cloud fail VIN = " + this.mVin);
    }

    public void iC() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$g$es0xAlFu5s184WlaBeVYqmQA56E
            @Override // java.lang.Runnable
            public final void run() {
                g.this.iJ();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void iJ() {
        String json;
        com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "updateBleMac2Cloud");
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (this.mVin.length() > 0) {
            if (a.b.getBoolean("SUPPORT_MULTI_BLE_KEY")) {
                this.wh.setTime(currentTimeMillis);
                this.wh.setVin(this.mVin);
                json = new Gson().toJson(this.wh);
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put("vin", this.mVin);
                hashMap.put("time", String.valueOf(currentTimeMillis));
                hashMap.put("mac", this.wf);
                json = new Gson().toJson(hashMap);
            }
            IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            iHttp.cancelTag(wb);
            iHttp.bizHelper().post(wb, json).needAuthorizationInfo().enableSecurityEncoding().build().tag(wb).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.aftersales.g.6
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    com.xiaopeng.lib.http.a.a a = com.xiaopeng.devtools.utils.d.a(iResponse);
                    boolean z = a.getCode() == 200;
                    g.this.wi.ag(z);
                    if (z) {
                        com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace BLE:" + g.this.wf, EcuUpdateResult.RESULT_SUCCESS);
                        return;
                    }
                    u.bd(a.getMsg());
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace BLE:" + g.this.wf, EcuUpdateResult.RESULT_FAIL);
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    g.this.wi.ag(false);
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace BLE:" + g.this.wf, EcuUpdateResult.RESULT_FAIL);
                }
            });
            return;
        }
        com.xiaopeng.lib.utils.c.i("UpdateDevicesIdPresenter", "updateBleMac2Cloud fail VIN = " + this.mVin);
    }

    public void iD() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$g$U-f-WvOYSiOVNjHPP_3CEmocBp8
            @Override // java.lang.Runnable
            public final void run() {
                g.this.iI();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void iI() {
        com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "updateNfcSeid2Cloud");
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (this.mVin.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("vin", this.mVin);
            hashMap.put("time", String.valueOf(currentTimeMillis));
            hashMap.put("seId", this.we);
            IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            iHttp.cancelTag(wa);
            iHttp.bizHelper().post(wa, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().tag(wa).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.aftersales.g.7
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    boolean d = com.xiaopeng.devtools.utils.d.d(iResponse);
                    g.this.wi.af(d);
                    if (d) {
                        com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace NFC:" + g.this.we, EcuUpdateResult.RESULT_SUCCESS);
                        return;
                    }
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace NFC:" + g.this.we, EcuUpdateResult.RESULT_FAIL);
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    g.this.wi.af(false);
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("replace NFC:" + g.this.we, EcuUpdateResult.RESULT_FAIL);
                }
            });
            return;
        }
        com.xiaopeng.lib.utils.c.i("UpdateDevicesIdPresenter", "updateNfcSeid2Cloud fail VIN = " + this.mVin);
    }

    public void iE() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$g$JMzTzx20xxJeE0M0l1gmgFERkT0
            @Override // java.lang.Runnable
            public final void run() {
                g.this.iH();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void iH() {
        com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "replaceXpu");
        if (this.mVin.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("vin", this.mVin);
            IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            iHttp.cancelTag(wc);
            iHttp.bizHelper().post(wc, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().tag(wc).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.aftersales.g.8
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    com.xiaopeng.lib.http.a.a a = com.xiaopeng.devtools.utils.d.a(iResponse);
                    if (a == null || a.getCode() != 200) {
                        g.this.wi.ah(true);
                        g.this.wi.S(null, MyApplication.getContext().getString(R.string.xpu_activate_fail));
                        com.xiaopeng.devtools.utils.b.recordRepairModeAction("activate XPU:" + g.this.wg, EcuUpdateResult.RESULT_FAIL);
                        return;
                    }
                    g.this.iF();
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("activate XPU:" + g.this.wg, "triggered");
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    g.this.wi.ah(true);
                    g.this.wi.S(null, MyApplication.getContext().getString(R.string.xpu_activate_fail));
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("activate XPU:" + g.this.wg, EcuUpdateResult.RESULT_FAIL);
                }
            });
            return;
        }
        com.xiaopeng.lib.utils.c.i("UpdateDevicesIdPresenter", "replaceXpu fail VIN = " + this.mVin);
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
        com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "checkXpuActivateStatus");
        if (this.mVin.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("vin", this.mVin);
            IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            iHttp.cancelTag(wd);
            iHttp.bizHelper().post(wd, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().tag(wd).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.aftersales.g.9
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    String stateMsg;
                    com.xiaopeng.lib.http.a.a a = com.xiaopeng.devtools.utils.d.a(iResponse);
                    if (a == null || a.getCode() != 200) {
                        g.this.wl.cancel();
                        g.this.wi.S(null, MyApplication.getContext().getString(R.string.xpu_activate_check_fail));
                        return;
                    }
                    XpuActivateStatus xpuActivateStatus = (XpuActivateStatus) new Gson().fromJson(a.getData(), (Class<Object>) XpuActivateStatus.class);
                    com.xiaopeng.lib.utils.c.f("UpdateDevicesIdPresenter", "status:" + xpuActivateStatus);
                    g.this.wg = xpuActivateStatus.getNewXpuSn() != null ? xpuActivateStatus.getNewXpuSn() : xpuActivateStatus.getXpuSn();
                    com.xiaopeng.devtools.view.aftersales.e eVar = g.this.wi;
                    String str = g.this.wg;
                    if (xpuActivateStatus.getState() == 1) {
                        stateMsg = xpuActivateStatus.getStateMsg() + ":" + xpuActivateStatus.getStateDesc();
                    } else {
                        stateMsg = xpuActivateStatus.getStateMsg();
                    }
                    eVar.S(str, stateMsg);
                    int state = xpuActivateStatus.getState();
                    if (state != 3) {
                        switch (state) {
                            case 1:
                                g.this.iG();
                                return;
                            case -1:
                            case 0:
                                g.this.wi.ah(true);
                                break;
                        }
                        g.this.wl.cancel();
                    }
                    g.this.wi.ah(true);
                    g.this.wl.cancel();
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    g.this.iG();
                }
            });
            return;
        }
        com.xiaopeng.lib.utils.c.i("UpdateDevicesIdPresenter", "checkXpuActivateStatus fail VIN = " + this.mVin);
        this.wl.cancel();
        this.wi.S(null, MyApplication.getContext().getString(R.string.xpu_activate_check_fail));
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        this.wl.cancel();
        iw();
    }
}
