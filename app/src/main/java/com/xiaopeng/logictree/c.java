package com.xiaopeng.logictree;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

/* compiled from: LogicTree.java */
/* loaded from: classes12.dex */
public class c {
    @SerializedName("param")
    private String XP;
    @SerializedName("parseMethod")
    private int XQ;
    @SerializedName("results")
    private String[] XR;
    @SerializedName("nextActions")
    private c[] XS;
    @SerializedName("defaultAction")
    private c XT;
    @SerializedName("noResultAction")
    private c XU;
    @SerializedName("action")
    private String mAction;

    public String getAction() {
        return this.mAction;
    }

    public String pI() {
        return this.XP;
    }

    public String[] pJ() {
        return this.XR;
    }

    public c dU(int i) {
        return this.XS[i];
    }

    public int pK() {
        return this.XS.length;
    }

    public c pL() {
        return this.XT;
    }

    public int pM() {
        return this.XQ;
    }

    public c pN() {
        return this.XU;
    }

    public String toString() {
        return "LogicTree{mAction='" + this.mAction + "', mParam='" + this.XP + "', mParseMethod=" + this.XQ + ", mResult=" + Arrays.toString(this.XR) + ", mNextAction=" + Arrays.toString(this.XS) + ", mDefaultAction=" + this.XT + ", mNoResultAction=" + this.XU + '}';
    }
}
