package com.xiaopeng.commonfunc.bean.car;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class DidRequest {
    @SerializedName("address")
    private int mAddress;
    @SerializedName("did")
    private int mDid;

    public DidRequest(int i, int i2) {
        this.mAddress = i;
        this.mDid = i2;
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

    public String toString() {
        return "DidRequest{mAddress=" + this.mAddress + ", mDid=" + this.mDid + '}';
    }
}
