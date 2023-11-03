package com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class ScreenSelfAdapter extends BaseAdapter {
    private ArrayList<ArrayList<String>> Km;

    public ScreenSelfAdapter(ArrayList<ArrayList<String>> arrayList) {
        this.Km = new ArrayList<>();
        if (arrayList != null) {
            this.Km = arrayList;
        }
    }

    public void e(ArrayList<ArrayList<String>> arrayList) {
        this.Km.clear();
        this.Km.addAll(arrayList);
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.Km.size();
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
            View inflate = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.list_screen_self_item, (ViewGroup) null);
            aVar.Kn = (TextView) inflate.findViewById(R.id.tv_signal_name);
            aVar.Ko = (TextView) inflate.findViewById(R.id.tv_fail_count);
            aVar.Kp = (TextView) inflate.findViewById(R.id.tv_fail_time);
            inflate.setTag(aVar);
            view = inflate;
        }
        a aVar2 = (a) view.getTag();
        ArrayList<String> arrayList = this.Km.get(i);
        aVar2.Kn.setText(arrayList.get(0));
        aVar2.Ko.setText(arrayList.get(1));
        aVar2.Kp.setText(arrayList.get(2));
        return view;
    }

    /* loaded from: classes12.dex */
    static class a {
        private TextView Kn;
        private TextView Ko;
        private TextView Kp;

        a() {
        }
    }
}
