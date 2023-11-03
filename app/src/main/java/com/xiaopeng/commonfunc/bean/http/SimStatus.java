package com.xiaopeng.commonfunc.bean.http;

/* loaded from: classes11.dex */
public class SimStatus {
    private String mActivate;
    private String mRestFlow;
    private String mTotalFlow;

    public SimStatus() {
        resetData();
    }

    public void resetData() {
        this.mActivate = "LOADING...";
        this.mTotalFlow = "LOADING...";
        this.mRestFlow = "LOADING...";
    }

    public String getActivate() {
        return this.mActivate;
    }

    public void setActivate(String str) {
        this.mActivate = str;
    }

    public String getTotalFlow() {
        return this.mTotalFlow;
    }

    public void setTotalFlow(String str) {
        this.mTotalFlow = str;
    }

    public String getRestFlow() {
        return this.mRestFlow;
    }

    public void setRestFlow(String str) {
        this.mRestFlow = str;
    }
}
