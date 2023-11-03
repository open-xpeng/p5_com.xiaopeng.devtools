package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.k;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.bean.car.McuTempInfo;
import com.xiaopeng.devtools.bean.car.TboxTempInfo;
import com.xiaopeng.devtools.bean.factorytest.AndTempInfo;
import com.xiaopeng.devtools.model.a.c;
import com.xiaopeng.devtools.model.a.d;
import com.xiaopeng.lib.utils.j;

/* compiled from: TemperaturePresenter.java */
/* loaded from: classes12.dex */
public class a {
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.a xp;
    private int vE = 1;
    private boolean xt = false;
    private Runnable xu = new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.k.a.1
        @Override // java.lang.Runnable
        public void run() {
            a.this.xq.setCpuTemp(a.this.vD.gP() / 10.0d);
            a.this.xq.setSocTemp(a.this.vD.gQ());
            a.this.xq.setUfsTemp(a.this.vD.gR());
            a.this.xq.setDdrTemp(a.this.vD.gS());
            a.this.xq.setAmpTemp(a.this.vD.gT());
            a.this.xq.setPmicTemp(a.this.vD.gU());
            try {
                if (a.b.getBoolean("SUPPORT_TMCU")) {
                    a.this.xr = (TboxTempInfo) new Gson().fromJson(d.fz().getDvTempMsg(), (Class<Object>) TboxTempInfo.class);
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
            a.this.xs.setMcuTemp(c.fx().getDvMcuTemp());
            a.this.xs.setBattTemp(c.fx().getDvBatTemp());
            a.this.xs.setPcbTemp(c.fx().getDvPcbTemp());
            if (a.this.xp != null) {
                a.this.xp.a(a.this.xq);
                if (a.b.getBoolean("SUPPORT_TMCU")) {
                    a.this.xp.a(a.this.xr);
                }
                a.this.xp.a(a.this.xs);
            }
            if (a.this.xt) {
                j.b(0, a.this.xu, a.this.vE * 1000);
            }
        }
    };
    private com.xiaopeng.devtools.model.c.a.i.a vD = new com.xiaopeng.devtools.model.c.a.i.a();
    private AndTempInfo xq = new AndTempInfo();
    private TboxTempInfo xr = new TboxTempInfo();
    private McuTempInfo xs = new McuTempInfo();

    public a(com.xiaopeng.devtools.view.factorytest.hardwaretest.temperature.a aVar) {
        this.xp = aVar;
    }

    public void cq(int i) {
        com.xiaopeng.lib.utils.c.f("TemperaturePresenter", "setInterval:" + i);
        this.vE = i;
    }

    public void jv() {
        com.xiaopeng.lib.utils.c.f("TemperaturePresenter", "readTemp");
        this.xt = true;
        j.e(this.xu);
        j.execute(this.xu);
    }

    public void destroy() {
        com.xiaopeng.lib.utils.c.f("TemperaturePresenter", "destroy");
        this.xt = false;
        j.e(this.xu);
    }
}
