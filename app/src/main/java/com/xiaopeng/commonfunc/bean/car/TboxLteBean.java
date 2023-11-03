package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class TboxLteBean {
    public String Band;
    public String CID;
    public String EARFCN;
    public String PCI;
    public String RRCStatus;
    public String RSRP;
    public String RSRQ;
    public String SINR;
    public String TAC;
    public String Type = "LTE";

    public String getType() {
        return this.Type;
    }

    public String getRRCStatus() {
        return this.RRCStatus;
    }

    public String getBand() {
        return this.Band;
    }

    public String getEARFCN() {
        return this.EARFCN;
    }

    public String getPCI() {
        return this.PCI;
    }

    public String getTAC() {
        return this.TAC;
    }

    public String getCID() {
        return this.CID;
    }

    public String getRSRP() {
        return this.RSRP;
    }

    public String getRSRQ() {
        return this.RSRQ;
    }

    public String getSINR() {
        return this.SINR;
    }

    public String toString() {
        return "Type : " + this.Type + "\r\r\r\rRRCStatus : " + this.RRCStatus + "\r\r\r\rBand : " + this.Band + "\r\r\r\rEARFCN : " + this.EARFCN + "\r\r\r\rPCI : " + this.PCI + "\r\r\r\rTAC : " + this.TAC + "\r\r\r\rCID : " + this.CID + "\r\r\r\rRSRP : " + this.RSRP + "\r\r\r\rRSRQ : " + this.RSRQ + "\r\r\r\rSINR : " + this.SINR;
    }
}
