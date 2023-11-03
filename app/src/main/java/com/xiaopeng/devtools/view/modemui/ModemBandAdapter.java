package com.xiaopeng.devtools.view.modemui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.car.TboxGsmBand;
import com.xiaopeng.devtools.bean.car.TboxLteBand;
import com.xiaopeng.devtools.bean.car.TboxUmtsBand;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class ModemBandAdapter extends BaseAdapter {
    private List<c> Qf = new ArrayList();
    private TboxGsmBand Qg;
    private TboxUmtsBand Qh;
    private TboxLteBand Qi;
    private int Qj;

    public void setGsmBand(TboxGsmBand tboxGsmBand) {
        this.Qg = tboxGsmBand;
        this.Qf = this.Qg.toBandList();
        this.Qj = 1;
    }

    public TboxGsmBand getGsmBands() {
        return this.Qg;
    }

    public void setUmtsBand(TboxUmtsBand tboxUmtsBand) {
        this.Qh = tboxUmtsBand;
        this.Qf = this.Qh.toBandList();
        this.Qj = 2;
    }

    public TboxUmtsBand getUmtsBands() {
        return this.Qh;
    }

    public void setLteBand(TboxLteBand tboxLteBand) {
        this.Qi = tboxLteBand;
        this.Qf = this.Qi.toBandList();
        this.Qj = 3;
    }

    public TboxLteBand getLteBands() {
        return this.Qi;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.Qf.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: du */
    public c getItem(int i) {
        return this.Qf.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_modem_band, viewGroup, false);
            aVar = new a();
            aVar.Ql = (CheckBox) view.findViewById(R.id.band_ischecked);
            aVar.Ql.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.modemui.ModemBandAdapter.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ((c) ModemBandAdapter.this.Qf.get(i)).setChecked(z);
                    if (ModemBandAdapter.this.Qj == 1) {
                        ModemBandAdapter.this.Qg.setBandValue(((c) ModemBandAdapter.this.Qf.get(i)).getBandName(), z ? 1 : 0);
                    } else if (ModemBandAdapter.this.Qj == 2) {
                        ModemBandAdapter.this.Qh.setBandValue(((c) ModemBandAdapter.this.Qf.get(i)).getBandName(), z ? 1 : 0);
                    } else if (ModemBandAdapter.this.Qj == 3) {
                        ModemBandAdapter.this.Qi.setBandValue(((c) ModemBandAdapter.this.Qf.get(i)).getBandName(), z ? 1 : 0);
                    }
                }
            });
            aVar.Qm = (TextView) view.findViewById(R.id.band_name);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        c cVar = this.Qf.get(i);
        aVar.Ql.setChecked(cVar.ischecked());
        aVar.Qm.setText(cVar.getBandName());
        return view;
    }

    /* loaded from: classes12.dex */
    private class a {
        CheckBox Ql;
        TextView Qm;

        private a() {
        }
    }
}
