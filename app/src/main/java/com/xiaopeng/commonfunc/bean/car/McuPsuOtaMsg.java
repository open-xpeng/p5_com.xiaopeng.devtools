package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class McuPsuOtaMsg {
    public int mAndroidSleepTime;
    public int mRevert0;
    public int mRevert1;
    public int mRevert2;

    public McuPsuOtaMsg(int[] iArr) {
        this.mAndroidSleepTime = iArr[0];
        this.mRevert0 = iArr[1];
        this.mRevert1 = iArr[2];
        this.mRevert2 = iArr[3];
    }

    public String toString() {
        return "mAndroidSleepTime=" + this.mAndroidSleepTime;
    }
}
