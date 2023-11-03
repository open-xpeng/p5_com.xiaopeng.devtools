package com.xiaopeng.commonfunc.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class DiagnosisError {
    @SerializedName("errorcode")
    private int mErrorCode;
    @SerializedName("errormsg")
    private String mErrorMsg;
    @SerializedName("module")
    private int mModule;

    public int getModule() {
        return this.mModule;
    }

    public void setModule(int i) {
        this.mModule = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorCode(int i) {
        this.mErrorCode = i;
    }

    public String getErrorMsg() {
        return this.mErrorMsg;
    }

    public void setErrorMsg(String str) {
        this.mErrorMsg = str;
    }

    public String toString() {
        return "DiagnosisError{mModule='" + this.mModule + "', mErrorCode='" + this.mErrorCode + "', mErrorMsg='" + this.mErrorMsg + "'}";
    }
}
