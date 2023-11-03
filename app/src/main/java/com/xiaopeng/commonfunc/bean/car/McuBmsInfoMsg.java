package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class McuBmsInfoMsg {
    public int mChargeMode;
    public int mCurrent;
    public int mHwVersion;
    public int mJobMode;
    public int mSoc;
    public int mSoh;
    public int mState;
    public int mSwVersion;
    public int mTempature;
    public int mVolatage;

    public McuBmsInfoMsg(int[] iArr) {
        if (iArr != null) {
            this.mVolatage = iArr[0] & 65535;
            this.mCurrent = (iArr[0] & (-65536)) >> 16;
            this.mSoc = iArr[1] & 255;
            this.mSoh = (iArr[1] >> 8) & 255;
            this.mTempature = (byte) ((iArr[1] >> 16) & 255);
            this.mChargeMode = (iArr[1] >> 24) & 7;
            this.mHwVersion = (iArr[1] >> 27) & 31;
            this.mState = (iArr[2] >> 0) & 255;
            this.mJobMode = (iArr[2] >> 8) & 3;
            this.mSwVersion = (iArr[2] >> 10) & 63;
            return;
        }
        this.mVolatage = -1;
        this.mCurrent = -1;
        this.mSoc = -1;
        this.mSoh = -1;
        this.mTempature = -1;
        this.mChargeMode = -1;
        this.mHwVersion = -1;
        this.mState = -1;
        this.mJobMode = -1;
        this.mSwVersion = -1;
    }

    public String toString() {
        return "mVolatage=" + this.mVolatage + "\n mCurrent=" + this.mCurrent + "\n mSoc=" + this.mSoc + "\n mSoh=" + this.mSoh + "\n mTempature=" + this.mTempature + "\n mChargeMode=" + this.mChargeMode + "\n mHwVersion=" + this.mHwVersion + "\n mState=" + this.mState + "\n mJobMode=" + this.mJobMode + "\n mSwVersion=" + this.mSwVersion;
    }
}
