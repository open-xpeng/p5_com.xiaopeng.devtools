package com.xiaopeng.devtools.bean.factorytest;

/* loaded from: classes12.dex */
public class ScanDevice {
    private String mAddress;
    private String mCod;
    private String mName;
    private int mRssi;

    public ScanDevice(String str, String str2, String str3, int i) {
        this.mName = str;
        this.mAddress = str2;
        this.mCod = str3;
        this.mRssi = i;
    }

    public int getRssi() {
        return this.mRssi;
    }

    public void setRssi(int i) {
        this.mRssi = i;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public void setAddress(String str) {
        this.mAddress = str;
    }

    public String getCod() {
        return this.mCod;
    }

    public void setCod(String str) {
        this.mCod = str;
    }
}
