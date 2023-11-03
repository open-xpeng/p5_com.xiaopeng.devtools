package com.xiaopeng.commonfunc.bean.factorytest;

/* loaded from: classes11.dex */
public class ItemResult {
    private boolean mIsSucceed;
    private String mItem;
    private String mLog;

    public ItemResult(String str, boolean z, String str2) {
        this.mItem = str;
        this.mIsSucceed = z;
        this.mLog = str2;
    }

    public String getItem() {
        return this.mItem;
    }

    public void setItem(String str) {
        this.mItem = str;
    }

    public boolean isSucceed() {
        return this.mIsSucceed;
    }

    public void setSucceed(boolean z) {
        this.mIsSucceed = z;
    }

    public String getLog() {
        return this.mLog;
    }

    public void setLog(String str) {
        this.mLog = str;
    }
}
