package com.xiaopeng.commonfunc.bean.car;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class DidResponse {
    @SerializedName("address")
    private int mAddress;
    @SerializedName("code")
    private int mCode;
    @SerializedName("did")
    private int mDid;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("value")
    private String mValue;

    public DidResponse(int i, int i2, String str, int i3, String str2) {
        this.mAddress = i;
        this.mDid = i2;
        this.mValue = str;
        this.mCode = i3;
        this.mMessage = str2;
    }

    public int getAddress() {
        return this.mAddress;
    }

    public void setAddress(int i) {
        this.mAddress = i;
    }

    public int getDid() {
        return this.mDid;
    }

    public void setDid(int i) {
        this.mDid = i;
    }

    public String getValue() {
        return this.mValue;
    }

    public void setValue(String str) {
        this.mValue = str;
    }

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int i) {
        this.mCode = i;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public String toString() {
        return "DidResponse{mAddress=" + this.mAddress + ", mDid=" + this.mDid + ", mValue=" + this.mValue + ", mCode=" + this.mCode + ", mMessage=" + this.mMessage + '}';
    }
}
