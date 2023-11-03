package com.xiaopeng.logictree;

import com.google.gson.annotations.SerializedName;
import com.xiaopeng.lib.apirouter.ClientConstants;

/* compiled from: LogicTreeInfo.java */
/* loaded from: classes12.dex */
public class e {
    @SerializedName("logicTreePath")
    private String XW;
    @SerializedName(ClientConstants.ALIAS.P_NAME)
    private String mName;

    public String getName() {
        return this.mName;
    }

    public String pR() {
        return this.XW;
    }

    public String toString() {
        return "LogicTreeInfo{mName='" + this.mName + "', mLogicTreePath='" + this.XW + "'}";
    }
}
