package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class TboxReplaceDevicesId {
    public String ble_mac;
    public String iccid;
    public String nfc_seid;

    public TboxReplaceDevicesId(String str, String str2, String str3) {
        this.ble_mac = str;
        this.nfc_seid = str2;
        this.iccid = str3;
    }

    public String getBle_mac() {
        return this.ble_mac;
    }

    public String getNfc_seid() {
        return this.nfc_seid;
    }

    public String getIccid() {
        return this.iccid;
    }

    public String toString() {
        return "TboxReplaceDevicesId{ble_mac='" + this.ble_mac + "', nfc_seid='" + this.nfc_seid + "', iccid='" + this.iccid + "'}";
    }
}
