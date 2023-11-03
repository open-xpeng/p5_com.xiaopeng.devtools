package com.xiaopeng.commonfunc.bean.car;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class TboxBean {
    @SerializedName("Request")
    private int mRequest;
    @SerializedName("Status")
    private int mStatus;
    @SerializedName("StringValue1")
    private String mStringValue1;
    @SerializedName("StringValue2")
    private String mStringValue2;

    public TboxBean(int i, int i2, String str, String str2) {
        this.mRequest = i;
        this.mStatus = i2;
        this.mStringValue1 = str;
        this.mStringValue2 = str2;
    }

    public int getRequest() {
        return this.mRequest;
    }

    public void setRequest(int i) {
        this.mRequest = i;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public String getStringValue1() {
        return this.mStringValue1;
    }

    public void setStringValue1(String str) {
        this.mStringValue1 = str;
    }

    public String getStringValue2() {
        return this.mStringValue2;
    }

    public void setStringValue2(String str) {
        this.mStringValue2 = str;
    }

    public String toString() {
        return "TboxBean{mRequest=" + this.mRequest + ", mStatus=" + this.mStatus + ", mStringValue1='" + this.mStringValue1 + "', mStringValue2='" + this.mStringValue2 + "'}";
    }
}
