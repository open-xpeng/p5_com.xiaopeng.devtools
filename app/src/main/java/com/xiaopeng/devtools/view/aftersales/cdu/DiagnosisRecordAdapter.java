package com.xiaopeng.devtools.view.aftersales.cdu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaopeng.commonfunc.bean.aftersales.DiagnosisData;
import com.xiaopeng.commonfunc.bean.aftersales.DiagnosisErrorList;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class DiagnosisRecordAdapter extends BaseAdapter {
    private DiagnosisErrorList Fa;

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.Fa != null) {
            return this.Fa.getDiagnosisDataSize();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    /* renamed from: cP */
    public DiagnosisData getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void setDataList(DiagnosisErrorList diagnosisErrorList) {
        this.Fa = diagnosisErrorList;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_diagnosis_data, viewGroup, false);
            aVar = new a();
            aVar.Fb = (TextView) view.findViewById(R.id.error_code);
            aVar.Fc = (TextView) view.findViewById(R.id.error_code_cn);
            aVar.Fd = (TextView) view.findViewById(R.id.error_code_eng);
            aVar.Fe = (TextView) view.findViewById(R.id.trigger_time);
            aVar.Ff = (TextView) view.findViewById(R.id.trigger_version);
            aVar.Fg = (TextView) view.findViewById(R.id.error_msg);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        DiagnosisData diagnosisData = this.Fa.getDiagnosisData(i);
        aVar.Fb.setText(String.valueOf(diagnosisData.getErrorCode()));
        aVar.Fe.setText(diagnosisData.getTriggerTime());
        aVar.Ff.setText(diagnosisData.getVersion());
        aVar.Fg.setText(diagnosisData.getErrorMsg());
        aVar.Fc.setText(this.Fa.getDiagnosisName(diagnosisData.getErrorCode()).getErrorNameCn());
        aVar.Fd.setText(this.Fa.getDiagnosisName(diagnosisData.getErrorCode()).getErrorNameEng());
        return view;
    }

    /* loaded from: classes12.dex */
    private class a {
        TextView Fb;
        TextView Fc;
        TextView Fd;
        TextView Fe;
        TextView Ff;
        TextView Fg;

        private a() {
        }
    }
}
