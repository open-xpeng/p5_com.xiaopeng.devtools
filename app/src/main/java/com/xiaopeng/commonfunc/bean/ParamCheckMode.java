package com.xiaopeng.commonfunc.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class ParamCheckMode {
    @SerializedName("download_path")
    private String mDownloadPath;
    @SerializedName("jobNum")
    private String mJobNum;
    @SerializedName("vin")
    private String mVin;

    public ParamCheckMode(String str, String str2, String str3) {
        this.mDownloadPath = str;
        this.mJobNum = str2;
        this.mVin = str3;
    }

    public String getDownloadPath() {
        return this.mDownloadPath;
    }

    public void setDownloadPath(String str) {
        this.mDownloadPath = str;
    }

    public String getJobNum() {
        return this.mJobNum;
    }

    public void setJobNum(String str) {
        this.mJobNum = str;
    }

    public String getVin() {
        return this.mVin;
    }

    public void setVin(String str) {
        this.mVin = str;
    }

    public String toString() {
        return "ParamCheckMode{mDownloadPath='" + this.mDownloadPath + "', mJobNum='" + this.mJobNum + "', mVin='" + this.mVin + "'}";
    }
}
