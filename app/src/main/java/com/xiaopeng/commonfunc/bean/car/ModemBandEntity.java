package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class ModemBandEntity {
    private String mBandName;
    private int mIschecked;

    public ModemBandEntity(String str, int i) {
        this.mBandName = str;
        this.mIschecked = i;
    }

    public String getBandName() {
        return this.mBandName;
    }

    public void setBandName(String str) {
        this.mBandName = str;
    }

    public boolean ischecked() {
        return this.mIschecked == 1;
    }

    public void setChecked(boolean z) {
        this.mIschecked = z ? 1 : 0;
    }
}
