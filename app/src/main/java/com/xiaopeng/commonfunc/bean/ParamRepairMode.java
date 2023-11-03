package com.xiaopeng.commonfunc.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class ParamRepairMode {
    @SerializedName("entry")
    private String mEntry;
    @SerializedName("exit")
    private String mExit;
    @SerializedName("status")
    private boolean mStatus;

    public ParamRepairMode(boolean z, String str, String str2) {
        this.mStatus = z;
        this.mEntry = str;
        this.mExit = str2;
    }

    public boolean getStatus() {
        return this.mStatus;
    }

    public void setStatus(boolean z) {
        this.mStatus = z;
    }

    public String getEntry() {
        return this.mEntry;
    }

    public void setEntry(String str) {
        this.mEntry = str;
    }

    public String getExit() {
        return this.mExit;
    }

    public void setExit(String str) {
        this.mExit = str;
    }

    public String toString() {
        return "ParamRepairMode{mStatus=" + this.mStatus + ", mEntry='" + this.mEntry + "', mExit='" + this.mExit + "'}";
    }
}
