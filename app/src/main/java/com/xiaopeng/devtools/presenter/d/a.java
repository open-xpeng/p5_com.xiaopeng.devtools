package com.xiaopeng.devtools.presenter.d;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.xiaopeng.a.a;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;
import com.xiaopeng.devtools.bean.factorytest.HereVmapInfo;
import com.xiaopeng.devtools.bean.factorytest.VmapConfig;
import com.xiaopeng.devtools.bean.http.UuidBean;
import com.xiaopeng.devtools.utils.b;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.i;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.utils.c;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/* compiled from: NaviPresenter.java */
/* loaded from: classes12.dex */
public class a {
    private VmapConfig yC;
    private com.xiaopeng.devtools.view.navi.a yD;
    private static final String yz = a.c.ec("NAVI_UUID");
    public static final String yA = a.c.ec("NAVI_MAP_CONFIG");
    public static final String yB = a.c.ec("OLD_NAVI_MAP_VERSION");

    public a(com.xiaopeng.devtools.view.navi.a aVar) {
        this.yD = aVar;
        jI();
    }

    public a() {
        jI();
    }

    private void jI() {
        try {
            this.yC = (VmapConfig) new Gson().fromJson(g.cs(yA), (Class<Object>) VmapConfig.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        if (this.yC != null) {
            c.f("NaviPresenter", this.yC.toString());
        }
    }

    public void jJ() {
        c.f("NaviPresenter", "genUuid");
        b.recordRepairModeAction("generate navigation activation UUID", "triggered");
        i.a(new Callback() { // from class: com.xiaopeng.devtools.presenter.d.a.1
            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onSuccess(IResponse iResponse) {
                c.f("NaviPresenter", "genUuid onSuccess");
                if (iResponse.code() == 200) {
                    UuidBean uuidBean = (UuidBean) new Gson().fromJson(iResponse.body(), (Class<Object>) UuidBean.class);
                    c.g("NaviPresenter", "genUuid onSuccess: uuidBean = " + uuidBean);
                    if (uuidBean.getCode() == 200) {
                        String uuid = uuidBean.getData().getUuid();
                        if (a.this.ci(uuid) && g.u(a.yz, uuid)) {
                            c.g("NaviPresenter", "get uuid success ");
                            b.recordRepairModeAction("generate navigation activation UUID", EcuUpdateResult.RESULT_SUCCESS);
                        }
                    }
                }
                a.this.jP();
            }

            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onFailure(IResponse iResponse) {
                a.this.jP();
            }
        });
    }

    public boolean ci(String str) {
        return str != null && (str.matches("^[A-Z0-9]{6}UI[A-Z0-9]{24}$") || str.matches("^[A-Z0-9]{6}HW[A-Z0-9]{24}$"));
    }

    public String jK() {
        return g.cs(yz);
    }

    public String jL() {
        char c;
        String jN = jN();
        int hashCode = jN.hashCode();
        if (hashCode != 2245648) {
            if (hashCode == 1018351860 && jN.equals("Autonav")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (jN.equals("Here")) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                if (this.yC != null) {
                    return jN() + "_" + jO() + "_" + jM();
                }
                return "N/A";
            case 1:
                HereVmapInfo hereVmapInfo = null;
                try {
                    hereVmapInfo = (HereVmapInfo) new Gson().fromJson(new JsonReader(new InputStreamReader(new FileInputStream(a.c.ec("NAVI_MAP_VERSION")))), HereVmapInfo.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
                if (hereVmapInfo != null) {
                    c.f("NaviPresenter", hereVmapInfo.toString());
                    return hereVmapInfo.getMapSupplier() + "_" + hereVmapInfo.getRegion() + "_" + hereVmapInfo.getMapVer();
                }
                return "N/A";
            default:
                String cs = g.cs(yB);
                if (TextUtils.isEmpty(cs)) {
                    return "N/A";
                }
                return cs;
        }
    }

    public String jM() {
        int i;
        if (this.yC != null) {
            i = this.yC.getVersion();
        } else {
            i = 0;
        }
        return i != 0 ? String.valueOf(i) : "N/A";
    }

    public String jN() {
        String str;
        if (this.yC != null) {
            str = this.yC.getVendor();
        } else {
            str = null;
        }
        return str != null ? str : "N/A";
    }

    public String jO() {
        String str;
        if (this.yC != null) {
            str = this.yC.getRegion();
        } else {
            str = null;
        }
        return str != null ? str : "N/A";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jP() {
        if (this.yD != null) {
            this.yD.nK();
        }
    }
}
