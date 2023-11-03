package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class TboxUpdateResponse {
    public int Key;
    public int Value;

    public TboxUpdateResponse(int i, int i2) {
        this.Key = i;
        this.Value = i2;
    }

    public int getKey() {
        return this.Key;
    }

    public int getValue() {
        return this.Value;
    }

    public String toString() {
        return "TboxUpdateResponse{Key=" + this.Key + ", Value=" + this.Value + '}';
    }
}
