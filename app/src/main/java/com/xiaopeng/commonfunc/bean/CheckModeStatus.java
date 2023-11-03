package com.xiaopeng.commonfunc.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class CheckModeStatus {
    @SerializedName("cduid")
    private String mCduId;
    @SerializedName("key")
    private String mKey;
    @SerializedName("maintainMode")
    private ParamRepairMode mMaintainMode;
    @SerializedName("speedLimitMode")
    private ParamRepairMode mSpeedLimitMode;
    @SerializedName("vin")
    private String mVin;

    public CheckModeStatus(String str, String str2, String str3, ParamRepairMode paramRepairMode, ParamRepairMode paramRepairMode2) {
        this.mVin = str;
        this.mCduId = str2;
        this.mKey = str3;
        this.mMaintainMode = paramRepairMode;
        this.mSpeedLimitMode = paramRepairMode2;
    }

    public String getVin() {
        return this.mVin;
    }

    public void setVin(String str) {
        this.mVin = str;
    }

    public String getCduId() {
        return this.mCduId;
    }

    public void setCduId(String str) {
        this.mCduId = str;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public ParamRepairMode getMaintainMode() {
        return this.mMaintainMode;
    }

    public void setMaintainMode(ParamRepairMode paramRepairMode) {
        this.mMaintainMode = paramRepairMode;
    }

    public ParamRepairMode getSpeedLimitMode() {
        return this.mSpeedLimitMode;
    }

    public void setSpeedLimitMode(ParamRepairMode paramRepairMode) {
        this.mSpeedLimitMode = paramRepairMode;
    }
}
