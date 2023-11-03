package com.xiaopeng.commonfunc.bean.factorytest;

/* loaded from: classes11.dex */
public class PingStatus {
    private int mFailTimes;
    private boolean mIsPingEthRunning;
    private int mSuccessTimes;

    public PingStatus(boolean z, int i, int i2) {
        this.mIsPingEthRunning = z;
        this.mSuccessTimes = i;
        this.mFailTimes = i2;
    }

    public boolean isPingEthRunning() {
        return this.mIsPingEthRunning;
    }

    public void setPingEthRunning(boolean z) {
        this.mIsPingEthRunning = z;
    }

    public int getSuccessTimes() {
        return this.mSuccessTimes;
    }

    public void increaseSuccessTimes() {
        this.mSuccessTimes++;
    }

    public int getFailTimes() {
        return this.mFailTimes;
    }

    public void increaseFailTimes() {
        this.mFailTimes++;
    }
}
