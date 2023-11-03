package com.xiaopeng.devtools.view.factorytest.hardwaretest.gps;

import android.content.Context;
import android.location.GpsSatellite;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class GPSSatelliteAdapter extends BaseAdapter {
    private Context mContext = MyApplication.getContext();
    private List<GpsSatellite> JD = new ArrayList();

    public void setGpsSatellites(List<GpsSatellite> list) {
        this.JD = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.JD.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            a aVar = new a();
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_gps_cnr_item, (ViewGroup) null);
            aVar.JE = (TextView) inflate.findViewById(R.id.gps_satellite_item);
            aVar.JF = (TextView) inflate.findViewById(R.id.gps_satellite_cnr);
            inflate.setTag(aVar);
            view = inflate;
        }
        a aVar2 = (a) view.getTag();
        aVar2.JE.setText(this.mContext.getString(R.string.gps_satellite_item, Integer.valueOf(this.JD.get(i).getPrn())));
        aVar2.JF.setText(this.mContext.getString(R.string.gps_satellite_cnr, Float.valueOf(this.JD.get(i).getSnr())));
        return view;
    }

    /* loaded from: classes12.dex */
    static class a {
        private TextView JE;
        private TextView JF;

        a() {
        }
    }
}
