package com.xiaopeng.commonfunc.bean.aftersales;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class BleList {
    @SerializedName("mac")
    private String mMainMac;
    @SerializedName("slaveMacList")
    private Ble[] mSlaveList;
    @SerializedName("time")
    private long mTime;
    @SerializedName("vin")
    private String mVin;

    public BleList(String str, Ble[] bleArr) {
        this.mMainMac = str;
        this.mSlaveList = bleArr;
    }

    public String getMainMac() {
        return this.mMainMac;
    }

    public void setMainMac(String str) {
        this.mMainMac = str;
    }

    public Ble[] getSlaveList() {
        return this.mSlaveList;
    }

    public void setSlaveList(Ble[] bleArr) {
        this.mSlaveList = bleArr;
    }

    public String getVin() {
        return this.mVin;
    }

    public void setVin(String str) {
        this.mVin = str;
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    /* loaded from: classes11.dex */
    public class Ble {
        @SerializedName(RequestParameters.POSITION)
        private int mPosition;
        @SerializedName("mac")
        private String mSlaveMac;

        public Ble(String str, int i) {
            this.mSlaveMac = str;
            this.mPosition = i;
        }

        public String getSlaveMac() {
            return this.mSlaveMac;
        }

        public void setSlaveMac(String str) {
            this.mSlaveMac = str;
        }

        public int getPosition() {
            return this.mPosition;
        }

        public void setPosition(int i) {
            this.mPosition = i;
        }
    }
}
