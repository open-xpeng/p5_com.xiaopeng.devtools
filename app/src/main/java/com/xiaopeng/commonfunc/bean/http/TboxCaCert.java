package com.xiaopeng.commonfunc.bean.http;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class TboxCaCert {
    @SerializedName("ca1")
    private String mCa1;
    @SerializedName("ca2")
    private String mCa2;
    @SerializedName("euca")
    private String mEuCa;

    public TboxCaCert(String str, String str2, String str3) {
        this.mEuCa = str;
        this.mCa1 = str2;
        this.mCa2 = str3;
    }

    public String getEuCa() {
        return this.mEuCa;
    }

    public void setEuCa(String str) {
        this.mEuCa = str;
    }

    public String getCa1() {
        return this.mCa1;
    }

    public void setCa1(String str) {
        this.mCa1 = str;
    }

    public String getCa2() {
        return this.mCa2;
    }

    public void setCa2(String str) {
        this.mCa2 = str;
    }
}
