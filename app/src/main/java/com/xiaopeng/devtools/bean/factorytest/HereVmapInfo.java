package com.xiaopeng.devtools.bean.factorytest;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes12.dex */
public class HereVmapInfo {
    @SerializedName("Content license level")
    private String mContentLicense;
    @SerializedName("Jira")
    private String mJira;
    @SerializedName("MapSupplier")
    private String mMapSupplier;
    @SerializedName("Map Version")
    private String mMapVer;
    @SerializedName("Part Number")
    private String mPartNumber;
    @SerializedName("Region")
    private String mRegion;
    @SerializedName("Transaction tag")
    private String mTransTag;

    public HereVmapInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mRegion = str;
        this.mTransTag = str2;
        this.mMapSupplier = str3;
        this.mMapVer = str4;
        this.mContentLicense = str5;
        this.mPartNumber = str6;
        this.mJira = str7;
    }

    public String getRegion() {
        return this.mRegion;
    }

    public void setRegion(String str) {
        this.mRegion = str;
    }

    public String getTransTag() {
        return this.mTransTag;
    }

    public void setTransTag(String str) {
        this.mTransTag = str;
    }

    public String getMapSupplier() {
        return this.mMapSupplier;
    }

    public void setMapSupplier(String str) {
        this.mMapSupplier = str;
    }

    public String getMapVer() {
        return this.mMapVer;
    }

    public void setMapVer(String str) {
        this.mMapVer = str;
    }

    public String getContentLicense() {
        return this.mContentLicense;
    }

    public void setContentLicense(String str) {
        this.mContentLicense = str;
    }

    public String getPartNumber() {
        return this.mPartNumber;
    }

    public void setPartNumber(String str) {
        this.mPartNumber = str;
    }

    public String getJira() {
        return this.mJira;
    }

    public void setJira(String str) {
        this.mJira = str;
    }

    public String toString() {
        return "HereVmapInfo{mRegion='" + this.mRegion + "', mTransTag='" + this.mTransTag + "', mMapSupplier='" + this.mMapSupplier + "', mMapVer='" + this.mMapVer + "', mContentLicense='" + this.mContentLicense + "', mPartNumber='" + this.mPartNumber + "', mJira='" + this.mJira + "'}";
    }
}
