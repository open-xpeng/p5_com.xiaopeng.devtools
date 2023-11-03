package com.xiaopeng.devtools.bean.http;

import com.google.gson.annotations.SerializedName;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;

/* loaded from: classes12.dex */
public class CellularBean {
    @SerializedName("flowUnit")
    private String mFlowUnit;
    @SerializedName("iccid")
    private String mIccid;
    @SerializedName("plateNo")
    private String mPlateNo;
    @SerializedName("respCode")
    private String mRespCode;
    @SerializedName("respDesc")
    private String mRespDesc;
    @SerializedName("restFlow")
    private String mRestFlow;
    @SerializedName(EcuUpdateResult.RESULT_SUCCESS)
    private boolean mSuccess;
    @SerializedName("totalFlow")
    private String mTotalFlow;
    @SerializedName("updateTime")
    private String mUpdateTime;
    @SerializedName("vehicleType")
    private String mVehicleType;
    @SerializedName("vin")
    private String mVin;

    public String getPlateNo() {
        return this.mPlateNo;
    }

    public void setPlateNo(String str) {
        this.mPlateNo = str;
    }

    public String getVin() {
        return this.mVin;
    }

    public void setVin(String str) {
        this.mVin = str;
    }

    public String getVehicleType() {
        return this.mVehicleType;
    }

    public void setVehicleType(String str) {
        this.mVehicleType = str;
    }

    public String getIccid() {
        return this.mIccid;
    }

    public void setIccid(String str) {
        this.mIccid = str;
    }

    public String getTotalFlow() {
        return this.mTotalFlow;
    }

    public void setTotalFlow(String str) {
        this.mTotalFlow = str;
    }

    public String getRestFlow() {
        return this.mRestFlow;
    }

    public void setRestFlow(String str) {
        this.mRestFlow = str;
    }

    public String getUpdateTime() {
        return this.mUpdateTime;
    }

    public void setUpdateTime(String str) {
        this.mUpdateTime = str;
    }

    public String getFlowUnit() {
        return this.mFlowUnit;
    }

    public void setFlowUnit(String str) {
        this.mFlowUnit = str;
    }

    public String getRespCode() {
        return this.mRespCode;
    }

    public int getIntCode() {
        try {
            return Integer.valueOf(this.mRespCode).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public void setRespCode(String str) {
        this.mRespCode = str;
    }

    public String getRespDesc() {
        return this.mRespDesc;
    }

    public void setRespDesc(String str) {
        this.mRespDesc = str;
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public void setSuccess(boolean z) {
        this.mSuccess = z;
    }

    public String toString() {
        return "CellularBean{mRespCode='" + this.mRespCode + "', mRespDesc='" + this.mRespDesc + "', mPlateNo='" + this.mPlateNo + "', mVin='" + this.mVin + "', mVehicleType='" + this.mVehicleType + "', mIccid='" + this.mIccid + "', mTotalFlow='" + this.mTotalFlow + "', mRestFlow='" + this.mRestFlow + "', mFlowUnit='" + this.mFlowUnit + "', mUpdateTime='" + this.mUpdateTime + "', mSuccess=" + this.mSuccess + '}';
    }
}
