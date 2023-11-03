package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class TboxUpdateReq {
    public String File;
    public int Key;

    public TboxUpdateReq(int i, String str) {
        this.Key = i;
        this.File = str;
    }

    public int getKey() {
        return this.Key;
    }

    public String getFile() {
        return this.File;
    }

    public String toString() {
        return "TboxUpdateReq{Key=" + this.Key + ", File='" + this.File + "'}";
    }
}
