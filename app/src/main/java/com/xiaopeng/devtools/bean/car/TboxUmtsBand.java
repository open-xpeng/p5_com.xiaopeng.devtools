package com.xiaopeng.devtools.bean.car;

import com.xiaopeng.devtools.view.modemui.c;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class TboxUmtsBand {
    private int TDSCDMA_BAND_A;
    private int TDSCDMA_BAND_F;
    private int WCDMA_2100;
    private int WCDMA_900;

    public int getTDSCDMA_BAND_A() {
        return this.TDSCDMA_BAND_A;
    }

    public int getTDSCDMA_BAND_F() {
        return this.TDSCDMA_BAND_F;
    }

    public int getWCDMA_2100() {
        return this.WCDMA_2100;
    }

    public int getWCDMA_900() {
        return this.WCDMA_900;
    }

    public String toString() {
        return "TboxUmtsBand{TDSCDMA_BAND_A=" + this.TDSCDMA_BAND_A + ", TDSCDMA_BAND_F=" + this.TDSCDMA_BAND_F + ", WCDMA_2100=" + this.WCDMA_2100 + ", WCDMA_900=" + this.WCDMA_900 + '}';
    }

    public List<c> toBandList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c("TDSCDMA_BAND_A", this.TDSCDMA_BAND_A));
        arrayList.add(new c("TDSCDMA_BAND_F", this.TDSCDMA_BAND_F));
        arrayList.add(new c("WCDMA_2100", this.WCDMA_2100));
        arrayList.add(new c("WCDMA_900", this.WCDMA_900));
        return arrayList;
    }

    public void setBandValue(String str, int i) {
        if ("TDSCDMA_BAND_A".equals(str)) {
            this.TDSCDMA_BAND_A = i;
        } else if ("TDSCDMA_BAND_F".equals(str)) {
            this.TDSCDMA_BAND_F = i;
        } else if ("WCDMA_2100".equals(str)) {
            this.WCDMA_2100 = i;
        } else if ("WCDMA_900".equals(str)) {
            this.WCDMA_900 = i;
        }
    }
}
