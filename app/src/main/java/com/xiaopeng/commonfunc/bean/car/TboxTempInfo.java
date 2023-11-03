package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class TboxTempInfo {
    private final int bbat;
    private final int tbox;
    private final int tmcu;

    public TboxTempInfo(int i, int i2, int i3) {
        this.tbox = i;
        this.tmcu = i2;
        this.bbat = i3;
    }

    public TboxTempInfo() {
        this.tbox = -1;
        this.tmcu = -1;
        this.bbat = -1;
    }

    public int getTbox() {
        return this.tbox;
    }

    public int getTmcu() {
        return this.tmcu;
    }

    public int getBbat() {
        return this.bbat;
    }

    public float getFloatTbox() {
        return this.tbox / 10.0f;
    }

    public float getFloatTmcu() {
        return this.tmcu / 10.0f;
    }

    public float getFloatBbat() {
        return this.bbat / 10.0f;
    }

    public String toString() {
        return "TboxTempInfo{tbox=" + this.tbox + ", tmcu=" + this.tmcu + ", bbat=" + this.bbat + '}';
    }
}
