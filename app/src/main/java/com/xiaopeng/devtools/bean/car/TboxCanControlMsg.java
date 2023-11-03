package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class TboxCanControlMsg {
    private int Key;
    private String Value;

    public TboxCanControlMsg() {
    }

    public TboxCanControlMsg(int i, String str) {
        this.Key = i;
        this.Value = str;
    }

    public int getKey() {
        return this.Key;
    }

    public String getValue() {
        return this.Value;
    }

    public String toString() {
        return "TboxCanControlMsg{Key=" + this.Key + ", Value='" + this.Value + "'}";
    }
}
