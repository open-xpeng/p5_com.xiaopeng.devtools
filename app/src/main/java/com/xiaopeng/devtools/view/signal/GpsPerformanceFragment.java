package com.xiaopeng.devtools.view.signal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.signal.GpsLocationEvent;
import com.xiaopeng.devtools.bean.event.signal.GpsNumSnrEvent;
import com.xiaopeng.devtools.bean.event.signal.GpsSpeedEvent;
import com.xiaopeng.devtools.presenter.e.b;
import com.xiaopeng.devtools.presenter.e.d;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class GpsPerformanceFragment extends Fragment {
    private View Sh;
    private TextView Sj;
    private TextView Sk;
    private TextView Sl;
    private TextView Sm;
    private TextView Sn;
    private d So;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GpsNumSnrEvent gpsNumSnrEvent) {
        this.Sj.setText(MyApplication.getContext().getString(R.string.gps_number, Integer.valueOf(gpsNumSnrEvent.getCount())));
        this.Sk.setText(MyApplication.getContext().getString(R.string.gps_snr, gpsNumSnrEvent.getSnrString()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GpsLocationEvent gpsLocationEvent) {
        this.Sl.setText(MyApplication.getContext().getString(R.string.gps_time, gpsLocationEvent.getLocationTime()));
        this.Sm.setText(MyApplication.getContext().getString(R.string.gps_location, gpsLocationEvent.getLocation()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GpsSpeedEvent gpsSpeedEvent) {
        this.Sn.setText(MyApplication.getContext().getString(R.string.gps_speed, gpsSpeedEvent.getGpsSpeed()));
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_gps_performance, viewGroup, false);
        this.Sh = inflate;
        nN();
        EventBus.getDefault().register(this);
        return inflate;
    }

    private void nN() {
        this.Sj = (TextView) findViewById(R.id.tv_gps_number);
        this.Sk = (TextView) findViewById(R.id.tv_gps_snr);
        this.Sl = (TextView) findViewById(R.id.tv_gps_time);
        this.Sm = (TextView) findViewById(R.id.tv_gps_location);
        this.Sn = (TextView) findViewById(R.id.tv_gps_speed_angle);
        this.So = new b();
        if (this.Sj != null) {
            this.Sj.setText(MyApplication.getContext().getString(R.string.gps_number, Integer.valueOf(this.So.jU())));
        }
        if (this.Sk != null) {
            this.Sk.setText(MyApplication.getContext().getString(R.string.gps_snr, this.So.hk()));
        }
        if (this.Sl != null) {
            this.Sl.setText(MyApplication.getContext().getString(R.string.gps_time, this.So.getLocationTime()));
        }
        if (this.Sm != null) {
            this.Sm.setText(MyApplication.getContext().getString(R.string.gps_location, this.So.hl()));
        }
        if (this.Sn != null) {
            this.Sn.setText(MyApplication.getContext().getString(R.string.gps_speed, this.So.getGpsSpeed()));
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
        this.Sj = null;
        this.Sk = null;
        this.Sl = null;
        this.Sm = null;
        this.Sn = null;
        this.Sh = null;
        EventBus.getDefault().unregister(this);
    }
}
