package com.xiaopeng.commonfunc.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class VcuModeResponse {
    @SerializedName("code")
    private Integer mCode;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("mode")
    private Integer mMode;

    public VcuModeResponse(Integer num, String str, Integer num2) {
        this.mCode = num;
        this.mMessage = str;
        this.mMode = num2;
    }

    public Integer getCode() {
        return this.mCode;
    }

    public void setCode(Integer num) {
        this.mCode = num;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public Integer getMode() {
        return this.mMode;
    }

    public void setMode(Integer num) {
        this.mMode = num;
    }

    public String toString() {
        return "VcuModeResponse{mCode=" + this.mCode + ", mMessage='" + this.mMessage + "', mMode=" + this.mMode + '}';
    }
}
