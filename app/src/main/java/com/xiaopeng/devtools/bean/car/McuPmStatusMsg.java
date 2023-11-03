package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class McuPmStatusMsg {
    public int mPmAwake;
    public int mSysPmStatus;

    public McuPmStatusMsg(int[] iArr) {
        this.mPmAwake = iArr[0];
        this.mSysPmStatus = iArr[1];
    }

    public String toString() {
        return "mSysPmStatus=" + this.mSysPmStatus + "\n mPmAwake=" + this.mPmAwake;
    }
}
