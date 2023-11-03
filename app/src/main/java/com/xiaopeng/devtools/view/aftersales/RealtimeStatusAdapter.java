package com.xiaopeng.devtools.view.aftersales;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.aftersales.RealtimeStatus;

/* loaded from: classes12.dex */
public class RealtimeStatusAdapter extends BaseAdapter {
    private RealtimeStatus[] Er;

    @Override // android.widget.Adapter
    public int getCount() {
        return this.Er.length;
    }

    @Override // android.widget.Adapter
    /* renamed from: cM */
    public RealtimeStatus getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void setAllStatus(RealtimeStatus[] realtimeStatusArr) {
        this.Er = realtimeStatusArr;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        View view2;
        String[] status;
        char c;
        if (view == null) {
            view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_realtime_status, viewGroup, false);
            aVar = new a();
            aVar.Es = (TextView) view2.findViewById(R.id.title);
            aVar.mContainer = (ViewGroup) view2.findViewById(R.id.container);
            view2.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
            view2 = view;
        }
        RealtimeStatus realtimeStatus = this.Er[i];
        aVar.Es.setText(realtimeStatus.getTitle());
        aVar.mContainer.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(view2.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        layoutParams.setMargins(0, 5, 0, 5);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        linearLayout.setWeightSum(3.0f);
        int i2 = 0;
        LinearLayout linearLayout2 = linearLayout;
        for (String str : realtimeStatus.getStatus()) {
            i2++;
            TextView textView = new TextView(view2.getContext());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            layoutParams2.setMargins(5, 5, 5, 5);
            textView.setLayoutParams(layoutParams2);
            textView.setTextColor(-1);
            textView.setTextSize(32.0f);
            textView.setText(str);
            linearLayout2.addView(textView);
            if (i2 % 3 == 0) {
                aVar.mContainer.addView(linearLayout2);
                LinearLayout linearLayout3 = new LinearLayout(view2.getContext());
                linearLayout3.setLayoutParams(layoutParams);
                linearLayout3.setOrientation(0);
                c = 0;
                linearLayout3.setWeightSum(3.0f);
                linearLayout2 = linearLayout3;
            } else {
                c = 0;
            }
        }
        if (i2 % 3 != 0) {
            aVar.mContainer.addView(linearLayout2);
        }
        return view2;
    }

    /* loaded from: classes12.dex */
    private class a {
        TextView Es;
        ViewGroup mContainer;

        private a() {
        }
    }
}
