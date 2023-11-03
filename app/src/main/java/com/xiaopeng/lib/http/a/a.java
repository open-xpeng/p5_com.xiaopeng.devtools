package com.xiaopeng.lib.http.a;

import com.google.gson.annotations.SerializedName;

/* compiled from: ServerBean.java */
/* loaded from: classes12.dex */
public class a {
    @SerializedName("code")
    private int mCode;
    @SerializedName("data")
    private String mData;
    @SerializedName("msg")
    private String mMsg;

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int i) {
        this.mCode = i;
    }

    public String getData() {
        return this.mData;
    }

    public void setData(String str) {
        this.mData = str;
    }

    public String getMsg() {
        return this.mMsg;
    }

    public void dB(String str) {
        this.mMsg = str;
    }
}
