package com.xiaopeng.devtools.bean.http;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes12.dex */
public class CellularServerBean {
    @SerializedName("respCode")
    private String mRespCode;
    @SerializedName("respDesc")
    private String mRespDesc;
    @SerializedName("respInfo")
    private String mRespInfo;

    public String getRespCode() {
        return this.mRespCode;
    }

    public int getIntCode() {
        try {
            return Integer.valueOf(this.mRespCode).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public void setRespCode(String str) {
        this.mRespCode = str;
    }

    public String getRespDesc() {
        return this.mRespDesc;
    }

    public void setRespDesc(String str) {
        this.mRespDesc = str;
    }

    public String getRespInfo() {
        return this.mRespInfo;
    }

    public void setRespInfo(String str) {
        this.mRespInfo = str;
    }

    public String toString() {
        return "CellularServerBean{mRespCode='" + this.mRespCode + "', mRespDesc='" + this.mRespDesc + "', mRespInfo=" + this.mRespInfo + '}';
    }
}
