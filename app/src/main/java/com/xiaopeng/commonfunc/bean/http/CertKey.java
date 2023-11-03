package com.xiaopeng.commonfunc.bean.http;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class CertKey {
    @SerializedName("cert")
    private String mCert;
    @SerializedName("index")
    private int mIndex;
    @SerializedName("key")
    private String mKey;

    public CertKey(String str, String str2, int i) {
        this.mCert = str;
        this.mKey = str2;
        this.mIndex = i;
    }

    public String getCert() {
        return this.mCert;
    }

    public void setCert(String str) {
        this.mCert = str;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int i) {
        this.mIndex = i;
    }
}
