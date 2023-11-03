package com.xiaopeng.devtools.view.modemui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class NeighbourNetworkAdapter extends BaseAdapter {
    private Context mContext = MyApplication.getContext();
    private ArrayList<String> Qp = new ArrayList<>();

    public void setNeighbourNetwork(ArrayList<String> arrayList) {
        this.Qp = arrayList;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.Qp.size();
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
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.neighbour_network_item, (ViewGroup) null);
            aVar.Qq = (TextView) inflate.findViewById(R.id.tv_neighbour_network);
            inflate.setTag(aVar);
            view = inflate;
        }
        ((a) view.getTag()).Qq.setText(this.Qp.get(i));
        return view;
    }

    /* loaded from: classes12.dex */
    static class a {
        TextView Qq;

        a() {
        }
    }
}
