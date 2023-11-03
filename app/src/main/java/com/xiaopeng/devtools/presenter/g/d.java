package com.xiaopeng.devtools.presenter.g;

import android.car.hardware.CarPropertyValue;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.TboxApn;
import com.xiaopeng.devtools.utils.f;
import com.xiaopeng.devtools.utils.n;
import com.xiaopeng.lib.utils.j;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: SystemInfoPresenter.java */
/* loaded from: classes12.dex */
public class d implements c {
    private static final String[] zz = {"com.xiaopeng.xmart.cargallery", "com.xiaopeng.xmart.camera", "com.xiaopeng.usermanual", "com.xiaopeng.chargecontrol", "com.xiaopeng.montecarlo", "com.xiaopeng.message.center", "com.xiaopeng.caraccount", "com.xiaopeng.btphone", "com.xiaopeng.ota", "com.xiaopeng.musicradio", "com.xiaopeng.aiassistant", "com.xiaopeng.autopilot", "com.xiaopeng.aiavatarservice", "com.xiaopeng.appstore", "com.android.systemui", "com.xiaopeng.oobe"};
    private com.xiaopeng.devtools.model.h.b zx = new com.xiaopeng.devtools.model.h.c(this);
    private com.xiaopeng.devtools.view.systeminfo.b zy;

    public d(com.xiaopeng.devtools.view.systeminfo.b bVar) {
        this.zy = bVar;
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public void kd() {
        com.xiaopeng.devtools.model.a.d.fz();
        f.k(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue.getPropertyId() == 554700817) {
            this.zy.du(ck(((TboxApn) new Gson().fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxApn.class)).getImsi()));
        }
    }

    private String ck(String str) {
        if (str.startsWith("46000") || str.startsWith("46002") || str.startsWith("46004") || str.startsWith("46007") || str.startsWith("46008") || str.startsWith("46013")) {
            return MyApplication.getContext().getString(R.string.china_mobile);
        }
        if (str.startsWith("46001") || str.startsWith("46006") || str.startsWith("46009")) {
            return MyApplication.getContext().getString(R.string.china_unicom);
        }
        if (!str.startsWith("46003") && !str.startsWith("46005") && !str.startsWith("46011")) {
            return "UNKNOWN";
        }
        return MyApplication.getContext().getString(R.string.china_telecom);
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public String getMcuVersion() {
        return this.zx.getMcuVersion();
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public String hJ() {
        return this.zx.hJ();
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public String getSystemVersion() {
        return this.zx.getSystemVersion();
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public String fR() {
        return this.zx.fR();
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public void a(final String str, final ImageView imageView) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.g.d.1
            @Override // java.lang.Runnable
            public void run() {
                final Bitmap a = n.a(str, 250, 250, null);
                j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.g.d.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        imageView.setImageBitmap(a);
                    }
                });
            }
        });
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public String getVehicleId() {
        return this.zx.getVehicleId();
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public String getIccid() {
        return this.zx.getIccid();
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public String eH() {
        return this.zx.eH();
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public void hK() {
        this.zx.hK();
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public void ke() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.g.-$$Lambda$d$u78ZA_9MMlIj3rjtp-ScsPyPUY4
            @Override // java.lang.Runnable
            public final void run() {
                d.this.kf();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void kf() {
        String[] strArr;
        com.xiaopeng.lib.utils.c.f("SystemInfoPresenter", "collectWantedApps");
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = MyApplication.getContext().getPackageManager();
        for (String str : zz) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 8192);
                if (packageInfo != null) {
                    arrayList.add(packageInfo);
                }
            } catch (PackageManager.NameNotFoundException e) {
                com.xiaopeng.lib.utils.c.i("SystemInfoPresenter", "NameNotFoundException : " + str);
            }
        }
        u(arrayList);
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public void u(List<PackageInfo> list) {
        if (this.zy != null) {
            this.zy.u(list);
        }
    }

    @Override // com.xiaopeng.devtools.presenter.g.c
    public void onDestroy() {
        f.l(this);
        this.zx = null;
        this.zy = null;
    }
}
