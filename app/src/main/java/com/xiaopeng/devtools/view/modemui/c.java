package com.xiaopeng.devtools.view.modemui;

/* compiled from: ModemBandEntity.java */
/* loaded from: classes12.dex */
public class c {
    private String mBandName;
    private int mIschecked;

    public c(String str, int i) {
        this.mBandName = str;
        this.mIschecked = i;
    }

    public String getBandName() {
        return this.mBandName;
    }

    public boolean ischecked() {
        return this.mIschecked == 1;
    }

    public void setChecked(boolean z) {
        this.mIschecked = z ? 1 : 0;
    }
}
