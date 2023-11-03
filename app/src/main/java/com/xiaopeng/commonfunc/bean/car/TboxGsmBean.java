package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class TboxGsmBean {
    public String ARFCN;
    public String CID;
    public String Frequency;
    public String LAC;
    public String RxLevel;
    public String RxQual;
    public String Type = "GSM";

    public String getType() {
        return this.Type;
    }

    public String getFrequency() {
        return this.Frequency;
    }

    public String getARFCN() {
        return this.ARFCN;
    }

    public String getLAC() {
        return this.LAC;
    }

    public String getCID() {
        return this.CID;
    }

    public String getRxLevel() {
        return this.RxLevel;
    }

    public String getRxQual() {
        return this.RxQual;
    }

    public String toString() {
        return "Type : " + this.Type + "\r\r\r\rFrequency : " + this.Frequency + "\r\r\r\rARFCN : " + this.ARFCN + "\r\r\r\rLAC : " + this.LAC + "\r\r\r\rCID : " + this.CID + "\r\r\r\rRxLevel : " + this.RxLevel + "\r\r\r\rRxQual : " + this.RxQual;
    }
}
