package com.xiaopeng.devtools.view.oled.adapter;

import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class MusicBookAdapter extends BaseAdapter {
    private ArrayMap<String, String> RZ = new ArrayMap<>();

    public void setMusicBookMap(ArrayMap arrayMap) {
        this.RZ = arrayMap;
    }

    public String dv(int i) {
        return this.RZ.get(this.RZ.keyAt(i));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.RZ.size();
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
            view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.custom_oled_item, (ViewGroup) null);
            a aVar = new a();
            aVar.mTextView = (TextView) view.findViewById(R.id.tv_oled_item);
            view.setTag(aVar);
        }
        ((a) view.getTag()).mTextView.setText(this.RZ.keyAt(i));
        return view;
    }

    /* loaded from: classes12.dex */
    static class a {
        private TextView mTextView;

        a() {
        }
    }
}
