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
import com.xiaopeng.devtools.bean.event.signal.NeighboringEvent;
import com.xiaopeng.devtools.bean.event.signal.NetServiceEvent;
import com.xiaopeng.devtools.bean.event.signal.NetStrengthEvent;
import com.xiaopeng.devtools.presenter.e.e;
import com.xiaopeng.devtools.presenter.e.g;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class NetPerformanceFragment extends Fragment {
    private View Sh;
    private TextView Sp;
    private TextView Sq;
    private TextView Sr;
    private TextView Ss;
    private TextView St;
    private TextView Su;
    private TextView Sv;
    private TextView Sw;
    private e Sx;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NetServiceEvent netServiceEvent) {
        if (this.Sv != null) {
            this.Sv.setText(MyApplication.getContext().getString(R.string.network_status, netServiceEvent.getNetService()));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NeighboringEvent neighboringEvent) {
        if (this.Sw != null) {
            this.Sw.setText(MyApplication.getContext().getString(R.string.network_data, neighboringEvent.getNeighboringCellInfo()));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NetStrengthEvent netStrengthEvent) {
        if (this.Sw != null && this.Su != null) {
            this.Su.setText(MyApplication.getContext().getString(R.string.network_strength, netStrengthEvent.getNetStrength()));
        }
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_net_performance, viewGroup, false);
        this.Sh = inflate;
        this.Sx = new g();
        nN();
        EventBus.getDefault().register(this);
        return inflate;
    }

    private void nN() {
        this.Sp = (TextView) findViewById(R.id.tv_iccid);
        this.Sq = (TextView) findViewById(R.id.tv_imei);
        this.Sr = (TextView) findViewById(R.id.tv_current_net);
        this.Ss = (TextView) findViewById(R.id.tv_current_frequency);
        this.St = (TextView) findViewById(R.id.tv_current_channel);
        this.Su = (TextView) findViewById(R.id.tv_current_strength);
        this.Sv = (TextView) findViewById(R.id.tv_network_search);
        this.Sw = (TextView) findViewById(R.id.tv_near_data);
        if (this.Sp != null) {
            this.Sp.setText(getString(R.string.network_iccid, this.Sx.getIccid()));
        }
        if (this.Sq != null) {
            this.Sq.setText(getString(R.string.network_imei, this.Sx.getImei()));
        }
        if (this.Sr != null) {
            this.Sr.setText(getString(R.string.network_current, this.Sx.hp()));
        }
        if (this.Ss != null) {
            this.Ss.setText(getString(R.string.network_frequency, this.Sx.hq()));
        }
        if (this.St != null) {
            this.St.setText(getString(R.string.network_channel, this.Sx.hr()));
        }
        if (this.Su != null) {
            this.Su.setText(MyApplication.getContext().getString(R.string.network_strength, this.Sx.jV()));
        }
        if (this.Sv != null) {
            this.Sv.setText(MyApplication.getContext().getString(R.string.network_status, this.Sx.jW()));
        }
        if (this.Sw != null) {
            this.Sw.setText(MyApplication.getContext().getString(R.string.network_data, this.Sx.hs()));
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
        this.Sp = null;
        this.Sq = null;
        this.Sr = null;
        this.Ss = null;
        this.St = null;
        this.Su = null;
        this.Sv = null;
        this.Sw = null;
        this.Sh = null;
        EventBus.getDefault().unregister(this);
    }
}
