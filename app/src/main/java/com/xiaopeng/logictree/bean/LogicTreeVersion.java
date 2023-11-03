package com.xiaopeng.logictree.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes12.dex */
public class LogicTreeVersion {
    @SerializedName("logicTreeUrl")
    private final String mUrl;
    @SerializedName("version")
    private final String mVersion;

    public LogicTreeVersion(String str, String str2) {
        this.mUrl = str;
        this.mVersion = str2;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getVersion() {
        return this.mVersion;
    }
}
