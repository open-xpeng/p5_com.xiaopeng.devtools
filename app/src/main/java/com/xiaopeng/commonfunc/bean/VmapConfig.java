package com.xiaopeng.commonfunc.bean;

/* loaded from: classes11.dex */
public class VmapConfig {
    private String region;
    private String vendor;
    private int version;

    public VmapConfig(int i, String str, String str2) {
        this.version = i;
        this.vendor = str;
        this.region = str2;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String str) {
        this.vendor = str;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public String toString() {
        return "VmapConfig{version=" + this.version + ", vendor='" + this.vendor + "', region='" + this.region + "'}";
    }
}
