package com.xiaopeng.logictree.handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.xiaopeng.logictree.R;
import com.xiaopeng.xui.widget.XButton;

/* loaded from: classes12.dex */
public class InterActiveAdapter extends BaseAdapter {
    private String[] Yw;
    private g Yx;

    public InterActiveAdapter(String[] strArr, g gVar) {
        this.Yw = strArr;
        this.Yx = gVar;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.Yw != null) {
            return this.Yw.length;
        }
        return 0;
    }

    @Override // android.widget.Adapter
    /* renamed from: cI */
    public String getItem(int i) {
        if (this.Yw != null) {
            return this.Yw[i];
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_button, viewGroup, false);
            aVar = new a();
            aVar.Yy = (XButton) view.findViewById(R.id.item_button);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.Yy.setText(getItem(i));
        aVar.Yy.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$InterActiveAdapter$HZglEFMdKoLX595mdY_VF-FvN48
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                InterActiveAdapter.this.b(i, view2);
            }
        });
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, View view) {
        if (this.Yx != null) {
            this.Yx.onResult(getItem(i));
        }
    }

    /* loaded from: classes12.dex */
    private class a {
        XButton Yy;

        private a() {
        }
    }
}
