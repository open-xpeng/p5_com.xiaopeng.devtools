package com.xiaopeng.commonfunc.bean.car;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class TboxLteBand {
    private int Band1;
    private int Band3;
    private int Band34;
    private int Band38;
    private int Band39;
    private int Band40;
    private int Band41;
    private int Band5;
    private int Band8;

    public int getBand1() {
        return this.Band1;
    }

    public int getBand3() {
        return this.Band3;
    }

    public int getBand5() {
        return this.Band5;
    }

    public int getBand8() {
        return this.Band8;
    }

    public int getBand34() {
        return this.Band34;
    }

    public int getBand38() {
        return this.Band38;
    }

    public int getBand39() {
        return this.Band39;
    }

    public int getBand40() {
        return this.Band40;
    }

    public int getBand41() {
        return this.Band41;
    }

    public String toString() {
        return "TboxLteBand{Band1=" + this.Band1 + ", Band3=" + this.Band3 + ", Band5=" + this.Band5 + ", Band8=" + this.Band8 + ", Band34=" + this.Band34 + ", Band38=" + this.Band38 + ", Band39=" + this.Band39 + ", Band40=" + this.Band40 + ", Band41=" + this.Band41 + '}';
    }

    public List<ModemBandEntity> toBandList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ModemBandEntity("Band1", this.Band1));
        arrayList.add(new ModemBandEntity("Band3", this.Band3));
        arrayList.add(new ModemBandEntity("Band5", this.Band5));
        arrayList.add(new ModemBandEntity("Band8", this.Band8));
        arrayList.add(new ModemBandEntity("Band34", this.Band8));
        arrayList.add(new ModemBandEntity("Band38", this.Band8));
        arrayList.add(new ModemBandEntity("Band39", this.Band8));
        arrayList.add(new ModemBandEntity("Band40", this.Band8));
        arrayList.add(new ModemBandEntity("Band41", this.Band8));
        return arrayList;
    }

    public void setBandValue(String str, int i) {
        if ("Band1".equals(str)) {
            this.Band1 = i;
        } else if ("Band3".equals(str)) {
            this.Band3 = i;
        } else if ("Band5".equals(str)) {
            this.Band5 = i;
        } else if ("Band8".equals(str)) {
            this.Band8 = i;
        } else if ("Band34".equals(str)) {
            this.Band34 = i;
        } else if ("Band38".equals(str)) {
            this.Band38 = i;
        } else if ("Band39".equals(str)) {
            this.Band39 = i;
        } else if ("Band40".equals(str)) {
            this.Band40 = i;
        } else if ("Band41".equals(str)) {
            this.Band41 = i;
        }
    }
}
