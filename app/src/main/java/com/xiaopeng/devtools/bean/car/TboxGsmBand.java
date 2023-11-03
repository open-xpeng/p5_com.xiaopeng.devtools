package com.xiaopeng.devtools.bean.car;

import com.xiaopeng.devtools.view.modemui.c;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class TboxGsmBand {
    private int GSM_1800;
    private int GSM_900;

    public int getGSM_900() {
        return this.GSM_900;
    }

    public int getGSM_1800() {
        return this.GSM_1800;
    }

    public String toString() {
        return "TboxGsmBand{GSM_900=" + this.GSM_900 + ", GSM_1800=" + this.GSM_1800 + '}';
    }

    public List<c> toBandList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c("GSM_900", this.GSM_900));
        arrayList.add(new c("GSM_1800", this.GSM_1800));
        return arrayList;
    }

    public void setBandValue(String str, int i) {
        if ("GSM_900".equals(str)) {
            this.GSM_900 = i;
        } else if ("GSM_1800".equals(str)) {
            this.GSM_1800 = i;
        }
    }
}
