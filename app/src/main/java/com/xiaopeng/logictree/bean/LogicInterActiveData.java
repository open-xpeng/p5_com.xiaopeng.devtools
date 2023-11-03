package com.xiaopeng.logictree.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes12.dex */
public class LogicInterActiveData {
    @SerializedName("msgId")
    private final String mMsgId;
    @SerializedName("option")
    private final String mOption;

    public LogicInterActiveData(String str, String str2) {
        this.mMsgId = str;
        this.mOption = str2;
    }

    public String getMsgId() {
        return this.mMsgId;
    }

    public String getOption() {
        return this.mOption;
    }
}
