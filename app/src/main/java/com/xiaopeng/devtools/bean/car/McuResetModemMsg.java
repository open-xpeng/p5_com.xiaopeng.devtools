package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class McuResetModemMsg {
    public int mMcuMsgType;
    public int mMcuRstAck;
    public int mMcuRstLvl;

    public McuResetModemMsg(int[] iArr) {
        this.mMcuMsgType = iArr[0];
        this.mMcuRstLvl = iArr[1];
        this.mMcuRstAck = iArr[2];
    }

    public String toString() {
        return "mMcuMsgType=" + this.mMcuMsgType;
    }
}
