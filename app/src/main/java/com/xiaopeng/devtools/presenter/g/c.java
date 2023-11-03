package com.xiaopeng.devtools.presenter.g;

import android.content.pm.PackageInfo;
import android.widget.ImageView;
import java.util.List;

/* compiled from: ISystemInfoPresenter.java */
/* loaded from: classes12.dex */
public interface c {
    void a(String str, ImageView imageView);

    String eH();

    String fR();

    String getIccid();

    String getMcuVersion();

    String getSystemVersion();

    String getVehicleId();

    String hJ();

    void hK();

    void kd();

    void ke();

    void onDestroy();

    void u(List<PackageInfo> list);
}
