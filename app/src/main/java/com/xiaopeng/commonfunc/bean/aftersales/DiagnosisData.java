package com.xiaopeng.commonfunc.bean.aftersales;

/* loaded from: classes11.dex */
public class DiagnosisData {
    private final int mErrorCode;
    private final String mErrorMsg;
    private final String mTriggerTime;
    private final String mVersion;

    public DiagnosisData(int i, String str, String str2, String str3) {
        this.mErrorCode = i;
        this.mTriggerTime = str;
        this.mErrorMsg = str2;
        this.mVersion = str3;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getTriggerTime() {
        return this.mTriggerTime;
    }

    public String getErrorMsg() {
        return this.mErrorMsg;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String toString() {
        return "DiagnosisData{mErrorCode=" + this.mErrorCode + ", mTriggerTime='" + this.mTriggerTime + "', mErrorMsg='" + this.mErrorMsg + "', mVersion='" + this.mVersion + "'}";
    }
}
