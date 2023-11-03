package com.xiaopeng.logictree.bean;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class LogicResponseData {
    @SerializedName("link")
    private final String mLink;
    @SerializedName("msg")
    private final String mMsg;
    @SerializedName("msgId")
    private final String mMsgId;
    @SerializedName("option")
    private final String[] mOptions;

    public LogicResponseData(String str, String str2, String str3, String[] strArr) {
        this.mMsg = str;
        this.mMsgId = str2;
        this.mLink = str3;
        this.mOptions = strArr;
    }

    public String getMsg() {
        return this.mMsg;
    }

    public String getMsgId() {
        return this.mMsgId;
    }

    public String getLink() {
        return this.mLink;
    }

    public String[] getOptions() {
        return this.mOptions;
    }

    public String toString() {
        return "LogicResponseData{mMsg='" + this.mMsg + "', mMsgId='" + this.mMsgId + "', mLink='" + this.mLink + "', mOptions=" + Arrays.toString(this.mOptions) + '}';
    }
}
