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
import com.xiaopeng.devtools.bean.event.signal.FmEvent;
import com.xiaopeng.devtools.presenter.e.a;
import com.xiaopeng.devtools.presenter.e.c;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class FmPerformanceFragment extends Fragment {
    TextView Se;
    TextView Sf;
    TextView Sg;
    private View Sh;
    private c Si;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FmEvent fmEvent) {
        this.Se.setText(MyApplication.getContext().getString(R.string.fm_status, this.Si.hh()));
        this.Sf.setText(MyApplication.getContext().getString(R.string.fm_frequency, this.Si.getFrequency()));
        this.Sg.setText(MyApplication.getContext().getString(R.string.fm_strength, this.Si.hi()));
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_fm_performance, viewGroup, false);
        this.Sh = inflate;
        this.Si = new a();
        nN();
        EventBus.getDefault().register(this);
        return inflate;
    }

    private void nN() {
        this.Se = (TextView) findViewById(R.id.tv_fm_status);
        this.Sf = (TextView) findViewById(R.id.tv_cur_frequency);
        this.Sg = (TextView) findViewById(R.id.tv_cur_strength);
        if (this.Se != null) {
            this.Se.setText(MyApplication.getContext().getString(R.string.fm_status, this.Si.hh()));
        }
        if (this.Sf != null) {
            this.Sf.setText(MyApplication.getContext().getString(R.string.fm_frequency, this.Si.getFrequency()));
        }
        if (this.Sg != null) {
            this.Sg.setText(MyApplication.getContext().getString(R.string.fm_strength, this.Si.hi()));
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
        this.Se = null;
        this.Sf = null;
        this.Sg = null;
        this.Sh = null;
        EventBus.getDefault().unregister(this);
    }
}
