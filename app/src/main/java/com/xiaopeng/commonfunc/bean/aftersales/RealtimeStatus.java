package com.xiaopeng.commonfunc.bean.aftersales;

/* loaded from: classes11.dex */
public class RealtimeStatus {
    private final String[] mStatus;
    private String mTitle;

    public RealtimeStatus(String str, String[] strArr) {
        this.mTitle = str;
        this.mStatus = strArr;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String[] getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i, String str) {
        this.mStatus[i] = str;
    }
}
