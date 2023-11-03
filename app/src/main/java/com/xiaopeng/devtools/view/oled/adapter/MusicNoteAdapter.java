package com.xiaopeng.devtools.view.oled.adapter;

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
public class MusicNoteAdapter extends BaseAdapter {
    private List<String> Sa = new ArrayList();

    public void setNoteList(List<String> list) {
        this.Sa = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.Sa.size() + 1;
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
        a aVar2 = (a) view.getTag();
        if (i >= this.Sa.size()) {
            aVar2.mTextView.setText(R.string.text_custom);
        } else {
            aVar2.mTextView.setText(this.Sa.get(i));
        }
        return view;
    }

    /* loaded from: classes12.dex */
    static class a {
        private TextView mTextView;

        a() {
        }
    }
}
