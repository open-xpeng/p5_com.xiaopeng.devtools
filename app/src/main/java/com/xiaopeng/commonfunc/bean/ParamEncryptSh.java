package com.xiaopeng.commonfunc.bean;

/* loaded from: classes11.dex */
public class ParamEncryptSh {
    private String download_path;
    private String md5;

    public ParamEncryptSh(String str, String str2) {
        this.download_path = str;
        this.md5 = str2;
    }

    public String getDownload_path() {
        return this.download_path;
    }

    public void setDownload_path(String str) {
        this.download_path = str;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public String toString() {
        return "ParamEncryptSh{download_path='" + this.download_path + "', md5='" + this.md5 + "'}";
    }
}
