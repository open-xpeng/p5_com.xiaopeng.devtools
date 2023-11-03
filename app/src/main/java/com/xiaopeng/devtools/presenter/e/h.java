package com.xiaopeng.devtools.presenter.e;

import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;

/* compiled from: WifiBtPresenter.java */
/* loaded from: classes12.dex */
public class h implements f {
    @Override // com.xiaopeng.devtools.presenter.e.f
    public String jX() {
        return com.xiaopeng.devtools.model.f.d.hv().getWifiState() == 1 ? MyApplication.getContext().getString(R.string.wifi_bt_open) : MyApplication.getContext().getString(R.string.wifi_bt_close);
    }

    @Override // com.xiaopeng.devtools.presenter.e.f
    public String jY() {
        return com.xiaopeng.devtools.model.f.d.hv().hA() == 1 ? MyApplication.getContext().getString(R.string.wifi_bt_open) : MyApplication.getContext().getString(R.string.wifi_bt_close);
    }

    @Override // com.xiaopeng.devtools.presenter.e.f
    public String hw() {
        return com.xiaopeng.devtools.model.f.d.hv().hw();
    }

    @Override // com.xiaopeng.devtools.presenter.e.f
    public String getBtName() {
        return com.xiaopeng.devtools.model.f.d.hv().hB();
    }

    @Override // com.xiaopeng.devtools.presenter.e.f
    public String hz() {
        return com.xiaopeng.devtools.model.f.d.hv().hz();
    }

    @Override // com.xiaopeng.devtools.presenter.e.f
    public void hD() {
        com.xiaopeng.devtools.model.f.d.hv().hD();
    }

    @Override // com.xiaopeng.devtools.presenter.e.f
    public String hy() {
        return com.xiaopeng.devtools.model.f.d.hv().hy();
    }

    @Override // com.xiaopeng.devtools.presenter.e.f
    public String jZ() {
        return com.xiaopeng.devtools.model.f.d.hv().hx();
    }

    @Override // com.xiaopeng.devtools.presenter.e.f
    public String hC() {
        return com.xiaopeng.devtools.model.f.d.hv().hC();
    }
}
