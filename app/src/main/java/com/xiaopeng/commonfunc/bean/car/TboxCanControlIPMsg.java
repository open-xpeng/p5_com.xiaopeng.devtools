package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class TboxCanControlIPMsg {
    private int Key;
    private IPNPort Value;

    public TboxCanControlIPMsg() {
    }

    public TboxCanControlIPMsg(int i, IPNPort iPNPort) {
        this.Key = i;
        this.Value = iPNPort;
    }

    public int getKey() {
        return this.Key;
    }

    public IPNPort getValue() {
        return this.Value;
    }

    public String toString() {
        return "TboxCanControlIPMsg{Key=" + this.Key + ", Value=" + this.Value + '}';
    }
}
