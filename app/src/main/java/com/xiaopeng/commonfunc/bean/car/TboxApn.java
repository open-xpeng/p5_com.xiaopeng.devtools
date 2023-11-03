package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class TboxApn {
    public int apn1;
    public int apn2;
    public int apn3;
    public int rssi;
    public String imsi = "UNKNOWN";
    public String imei = "UNKNOWN";

    public String getImsi() {
        return this.imsi;
    }

    public int getRssi() {
        return this.rssi;
    }

    public int getApn1() {
        return this.apn1;
    }

    public int getApn2() {
        return this.apn2;
    }

    public int getApn3() {
        return this.apn3;
    }

    public String getImei() {
        return this.imei;
    }

    public String toString() {
        return "rssi : " + this.rssi + "\r\r\r\rapn1 : " + this.apn1 + "\r\r\r\rapn2 : " + this.apn2 + "\r\r\r\rapn3 : " + this.apn3;
    }
}
