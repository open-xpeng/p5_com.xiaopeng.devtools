package com.xiaopeng.devtools.presenter.h;

import android.annotation.SuppressLint;
import android.car.hardware.CarPropertyValue;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.h;
import com.xiaopeng.lib.apirouter.ClientConstants;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

/* compiled from: XpuFirUpdatePresenter.java */
/* loaded from: classes12.dex */
public class f {
    private com.xiaopeng.devtools.view.update.e Ac;
    private String Ad;
    private String Ae;
    private String Af;
    private String Ag;
    private com.xiaopeng.devtools.model.a.e Ah = com.xiaopeng.devtools.model.a.e.fA();

    public f(com.xiaopeng.devtools.view.update.e eVar) {
        this.Ac = eVar;
        com.xiaopeng.lib.utils.c.f("XpuFirUpdatePresenter", "ScreenFirUpdatePresenter construct start.");
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressLint({"MissingPermission"})
    public void onEvent(CarPropertyValue carPropertyValue) {
        int propertyId = carPropertyValue.getPropertyId();
        if (propertyId != 554711042) {
            if (propertyId == 557856772) {
                int intValue = ((Integer) carPropertyValue.getValue()).intValue();
                com.xiaopeng.lib.utils.c.i("XpuFirUpdatePresenter", "ID_XPU_UPDATE_PROGRESS value:" + intValue);
                this.Ac.dE(intValue);
                return;
            } else if (propertyId == 557856774) {
                int intValue2 = ((Integer) carPropertyValue.getValue()).intValue();
                com.xiaopeng.lib.utils.c.i("XpuFirUpdatePresenter", "ID_XPU_UPDATE_RESULT value:" + intValue2);
                this.Ac.dD(intValue2);
                return;
            } else {
                return;
            }
        }
        try {
            String str = (String) carPropertyValue.getValue();
            com.xiaopeng.lib.utils.c.i("XpuFirUpdatePresenter", "ID_XPU_UPDATE_REQ resStr:" + str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("status");
            String string = jSONObject.getString("message");
            String string2 = jSONObject.getString("homepath");
            String string3 = jSONObject.getString("user");
            String string4 = jSONObject.getString("passwd");
            if (i == 0) {
                m(string2, string3, string4);
            } else {
                com.xiaopeng.lib.utils.c.i("XpuFirUpdatePresenter", "ID_XPU_UPDATE_REQ xpu update res reject,reason:" + string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean kE() {
        this.Ad = null;
        this.Ae = null;
        this.Af = null;
        this.Ag = null;
        String str = g.V(MyApplication.getContext()) + "/xpuFirm/";
        this.Ae = str + "update_xpu_by_cdu_cfg";
        if (g.aY(this.Ae)) {
            cm(this.Ae);
            com.xiaopeng.lib.utils.c.f("XpuFirUpdatePresenter", "get XPU FirmFile FromUsb md5value = " + this.Af + ",filename = " + this.Ag);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(this.Ag);
            this.Ad = sb.toString();
            if (g.aY(this.Ad)) {
                return true;
            }
        }
        return false;
    }

    public void kF() {
        try {
            if (this.Ah != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ClientConstants.ALIAS.P_NAME, this.Ag);
                jSONObject.put("cks", this.Af);
                String jSONObject2 = jSONObject.toString();
                com.xiaopeng.lib.utils.c.f("XpuFirUpdatePresenter", " sendIcmUpdateRequest str = " + jSONObject2);
                this.Ah.sendXpuUpdateRequest(jSONObject2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean m(final String str, final String str2, final String str3) {
        if (this.Ad != null) {
            com.xiaopeng.lib.utils.c.f("XpuFirUpdatePresenter", "startTrasnferXpuFirmToXpu,mPathXpuFirFile = " + this.Ad);
            j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.h.-$$Lambda$f$KgEwXi7VySD-2sw41hdxoSuh1SY
                @Override // java.lang.Runnable
                public final void run() {
                    f.this.n(str2, str3, str);
                }
            });
            return true;
        }
        this.Ac.dD(-2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(String str, String str2, String str3) {
        try {
            boolean a = h.a(this.Ad, "172.20.1.22", -1, str, str2, str3);
            com.xiaopeng.lib.utils.c.f("XpuFirUpdatePresenter", "FtpSender ret  = " + a);
            if (a) {
                this.Ah.bV(0);
                this.Ac.dD(2);
            } else {
                this.Ah.bV(1);
                this.Ac.dD(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cm(String str) {
        String G = g.G(str, "|");
        if (G != null) {
            String[] split = G.split("\\|");
            for (int i = 0; i < split.length; i++) {
                if (split[i].indexOf(ClientConstants.ALIAS.P_NAME) != -1) {
                    String[] split2 = split[i].split(":");
                    if (split2.length == 2) {
                        this.Ag = split2[1].trim();
                    }
                }
                if (split[i].indexOf("cks") != -1) {
                    String[] split3 = split[i].split(":");
                    if (split3.length == 2) {
                        this.Af = split3[1].trim();
                    }
                }
            }
        }
    }
}
