package com.xiaopeng.devtools.presenter.factorytest.c;

import android.car.hardware.CarPropertyValue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.bean.aftersales.BleList;
import com.xiaopeng.devtools.bean.car.CarBase;
import com.xiaopeng.devtools.model.a.d;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.utils.u;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: UploadDevicesIdPresenter.java */
/* loaded from: classes12.dex */
public class b implements a {
    private static final String yk = a.f.ed("UPLOAD_BLEKEYS_MACADDR");
    private final String mVin;
    private final String wb;
    private String wf;
    private BleList wh;
    private final String[] wk;
    private com.xiaopeng.devtools.view.factorytest.vehicle.a yl;

    @Override // com.xiaopeng.devtools.presenter.factorytest.c.a
    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        BleList.Ble[] slaveList;
        if (carPropertyValue.getPropertyId() == 554700879) {
            c.f("UploadDevicesIdPresenter", "ID_TBOX_MUL_BLE_REPLACE : " + carPropertyValue.getValue());
            this.wh = (BleList) new Gson().fromJson((String) ((CarBase) new Gson().fromJson((String) carPropertyValue.getValue(), new TypeToken<CarBase<String>>() { // from class: com.xiaopeng.devtools.presenter.factorytest.c.b.1
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
                this.yl.cX(this.wf);
            }
        }
    }

    public b(com.xiaopeng.devtools.view.factorytest.vehicle.a aVar) {
        this.yl = aVar;
        if (r.lj()) {
            this.wb = com.xiaopeng.lib.utils.a.a.WY + yk;
        } else {
            this.wb = "https://digital-key-upload.xiaopeng.com" + yk;
        }
        this.wf = "";
        this.wh = null;
        this.mVin = r.eH();
        this.wk = a.C0041a.getString("BLE_POSITION_NAMES").split(",");
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.c.a
    public void ix() {
        d.fz().sendRenewalPartsRequest("replace");
        if (a.b.getBoolean("SUPPORT_MULTI_BLE_KEY")) {
            d.fz().sendMultiBleRenewalRequest(new Gson().toJson(new CarBase("ble_replace_req", "")));
        }
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.c.a
    public String iz() {
        return this.wf;
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.c.a
    public void jG() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.c.-$$Lambda$b$X7iLB9ElTsTPNvUmTmBjoTpmUaY
            @Override // java.lang.Runnable
            public final void run() {
                b.this.jH();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void jH() {
        String json;
        c.f("UploadDevicesIdPresenter", "uploadBleMac2Cloud");
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
            iHttp.cancelTag(this.wb);
            iHttp.bizHelper().post(this.wb, json).needAuthorizationInfo().enableSecurityEncoding().build().tag(this.wb).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.factorytest.c.b.2
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    com.xiaopeng.lib.http.a.a a = com.xiaopeng.devtools.utils.d.a(iResponse);
                    boolean z = a.getCode() == 200;
                    b.this.yl.aC(z);
                    if (z) {
                        c.g("UploadDevicesIdPresenter", "uploadBleMac2Cloud success");
                    } else {
                        u.bd(a.getMsg());
                    }
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    b.this.yl.aC(false);
                }
            });
            return;
        }
        c.i("UploadDevicesIdPresenter", "uploadBleMac2Cloud fail VIN = " + this.mVin);
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.c.a
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
