package com.xiaopeng.logictree;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

/* compiled from: LogicTreeInfoList.java */
/* loaded from: classes12.dex */
public class f {
    @SerializedName("project")
    private String XX;
    @SerializedName("trees")
    private e[] XY;
    @SerializedName("version")
    private String mVersion;

    public String pS() {
        return this.XX;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public e[] pT() {
        return this.XY;
    }

    public String toString() {
        return "LogicTreeInfoList{mProject='" + this.XX + "', mVersion='" + this.mVersion + "', mLogicTreeList=" + Arrays.toString(this.XY) + '}';
    }
}
