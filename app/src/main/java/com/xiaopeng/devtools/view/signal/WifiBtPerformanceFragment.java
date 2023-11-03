package com.xiaopeng.devtools.view.signal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.signal.BtNameEvent;
import com.xiaopeng.devtools.bean.event.signal.BtPowerEvent;
import com.xiaopeng.devtools.bean.event.signal.BtRssiEvent;
import com.xiaopeng.devtools.bean.event.signal.WifiNameSSEvent;
import com.xiaopeng.devtools.bean.event.signal.WifiStateMacEvent;
import com.xiaopeng.devtools.presenter.e.f;
import com.xiaopeng.devtools.presenter.e.h;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class WifiBtPerformanceFragment extends Fragment {
    private TextView SC;
    private TextView SD;
    private TextView SE;
    private TextView SF;
    private TextView SG;
    private TextView SH;
    private TextView SI;
    private TextView SJ;
    private TextView SK;
    private f SL;
    private View Sh;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WifiNameSSEvent wifiNameSSEvent) {
        this.SE.setText(MyApplication.getContext().getString(R.string.wlan_name, this.SL.hw()));
        this.SG.setText(MyApplication.getContext().getString(R.string.wlan_strength, this.SL.hz()));
        this.SI.setText(MyApplication.getContext().getString(R.string.wlan_speed, this.SL.hy()));
        this.SK.setText(MyApplication.getContext().getString(R.string.wlan_ip, this.SL.hC()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WifiStateMacEvent wifiStateMacEvent) {
        this.SC.setText(MyApplication.getContext().getString(R.string.wlan_status, this.SL.jX()));
        this.SJ.setText(MyApplication.getContext().getString(R.string.wlan_mac, this.SL.jZ()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BtNameEvent btNameEvent) {
        this.SF.setText(MyApplication.getContext().getString(R.string.bt_name, this.SL.getBtName()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BtPowerEvent btPowerEvent) {
        this.SD.setText(MyApplication.getContext().getString(R.string.bt_status, this.SL.jY()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BtRssiEvent btRssiEvent) {
        this.SH.setText(MyApplication.getContext().getString(R.string.bt_strength, btRssiEvent.getRssi()));
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_wifi_performance, viewGroup, false);
        this.Sh = inflate;
        this.SL = new h();
        nN();
        EventBus.getDefault().register(this);
        return inflate;
    }

    private void nN() {
        this.SC = (TextView) findViewById(R.id.tv_wifi_status);
        this.SD = (TextView) findViewById(R.id.tv_bt_status);
        this.SE = (TextView) findViewById(R.id.tv_wifi_name);
        this.SF = (TextView) findViewById(R.id.tv_bt_name);
        this.SG = (TextView) findViewById(R.id.tv_wifi_strength);
        this.SH = (TextView) findViewById(R.id.tv_bt_strength);
        this.SI = (TextView) findViewById(R.id.tv_wifi_speed);
        this.SJ = (TextView) findViewById(R.id.tv_wifi_mac);
        this.SK = (TextView) findViewById(R.id.tv_wifi_ip);
        if (this.SC != null) {
            this.SC.setText(MyApplication.getContext().getString(R.string.wlan_status, this.SL.jX()));
        }
        if (this.SD != null) {
            this.SD.setText(MyApplication.getContext().getString(R.string.bt_status, this.SL.jY()));
        }
        if (this.SE != null) {
            this.SE.setText(MyApplication.getContext().getString(R.string.wlan_name, this.SL.hw()));
        }
        if (this.SF != null) {
            this.SF.setText(MyApplication.getContext().getString(R.string.bt_name, this.SL.getBtName()));
        }
        if (this.SG != null) {
            this.SG.setText(MyApplication.getContext().getString(R.string.wlan_strength, this.SL.hz()));
        }
        this.SL.hD();
        if (this.SH != null) {
            this.SH.setText(MyApplication.getContext().getString(R.string.bt_strength, "N/A"));
        }
        if (this.SI != null) {
            this.SI.setText(MyApplication.getContext().getString(R.string.wlan_speed, this.SL.hy()));
        }
        if (this.SJ != null) {
            this.SJ.setText(MyApplication.getContext().getString(R.string.wlan_mac, this.SL.jZ()));
        }
        if (this.SK != null) {
            this.SK.setText(MyApplication.getContext().getString(R.string.wlan_ip, this.SL.hC()));
        }
    }

    private View findViewById(@IdRes int i) {
        if (this.Sh != null) {
            return this.Sh.findViewById(i);
        }
        return null;
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.SC = null;
        this.SD = null;
        this.SE = null;
        this.SF = null;
        this.SG = null;
        this.SH = null;
        this.SI = null;
        this.SJ = null;
        this.SK = null;
        this.Sh = null;
        EventBus.getDefault().unregister(this);
    }
}
