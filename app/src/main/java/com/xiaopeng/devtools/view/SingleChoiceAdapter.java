package com.xiaopeng.devtools.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import com.xiaopeng.devtools.R;
import com.xiaopeng.xui.widget.XRadioButton;

/* loaded from: classes12.dex */
public class SingleChoiceAdapter extends BaseAdapter {
    private String[] Dx;
    private int Dy = 0;
    private boolean Dz = true;

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.Dx != null) {
            return this.Dx.length;
        }
        return 0;
    }

    @Override // android.widget.Adapter
    /* renamed from: cI */
    public String getItem(int i) {
        return this.Dx[i];
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void setItems(String[] strArr) {
        this.Dx = strArr;
    }

    public void setSelectedItem(int i) {
        this.Dy = i;
    }

    public int getSelectedItem() {
        return this.Dy;
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_radiobutton_selector, viewGroup, false);
            aVar = new a();
            aVar.DA = (XRadioButton) view.findViewById(R.id.single_selector);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        this.Dz = false;
        aVar.DA.setText(getItem(i));
        if (i != this.Dy) {
            aVar.DA.setChecked(false);
        } else {
            aVar.DA.setChecked(true);
        }
        aVar.DA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.-$$Lambda$SingleChoiceAdapter$87gNQf6Z3WYhGYZxTxwZ6Kbh9b0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SingleChoiceAdapter.this.a(i, compoundButton, z);
            }
        });
        this.Dz = true;
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, CompoundButton compoundButton, boolean z) {
        if (this.Dz && z) {
            this.Dy = i;
            notifyDataSetChanged();
        }
    }

    /* loaded from: classes12.dex */
    private class a {
        XRadioButton DA;

        private a() {
        }
    }
}
