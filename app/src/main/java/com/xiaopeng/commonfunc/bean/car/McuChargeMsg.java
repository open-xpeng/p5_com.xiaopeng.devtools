package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class McuChargeMsg {
    public int ChargeMode;
    public int ChargeTime1Day;
    public int ChargeTime1Hour;
    public int ChargeTime1Min;
    public int ChargeTime1Mon;
    public int ChargeTime1Week;
    public int ChargeTime2Day;
    public int ChargeTime2Hour;
    public int ChargeTime2Min;
    public int ChargeTime2Mon;
    public int ChargeTime2Week;
    public int StopChargeSoc;
    public int TimeMode;

    public McuChargeMsg(int[] iArr) {
        this.ChargeMode = iArr[0] & 255;
        this.StopChargeSoc = (iArr[0] >> 8) & 255;
        this.ChargeTime1Week = (iArr[0] >> 16) & 255;
        this.ChargeTime1Mon = (iArr[0] >> 24) & 255;
        this.ChargeTime1Day = iArr[1] & 255;
        this.ChargeTime1Hour = (iArr[1] >> 8) & 255;
        this.ChargeTime1Min = (iArr[1] >> 16) & 255;
        this.ChargeTime2Week = (iArr[1] >> 24) & 255;
        this.ChargeTime2Mon = iArr[2] & 255;
        this.ChargeTime2Day = (iArr[2] >> 8) & 255;
        this.ChargeTime2Hour = (iArr[2] >> 16) & 255;
        this.ChargeTime2Min = (iArr[2] >> 24) & 255;
        this.TimeMode = iArr[3] & 255;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("McuChargeMsg{");
        stringBuffer.append("ChargeMode=");
        stringBuffer.append(this.ChargeMode);
        stringBuffer.append(", StopChargeSoc=");
        stringBuffer.append(this.StopChargeSoc);
        stringBuffer.append(", ChargeTime1Week=");
        stringBuffer.append(this.ChargeTime1Week);
        stringBuffer.append(", ChargeTime1Mon=");
        stringBuffer.append(this.ChargeTime1Mon);
        stringBuffer.append(", ChargeTime1Day=");
        stringBuffer.append(this.ChargeTime1Day);
        stringBuffer.append(", ChargeTime1Hour=");
        stringBuffer.append(this.ChargeTime1Hour);
        stringBuffer.append(", ChargeTime1Min=");
        stringBuffer.append(this.ChargeTime1Min);
        stringBuffer.append(", ChargeTime2Week=");
        stringBuffer.append(this.ChargeTime2Week);
        stringBuffer.append(", ChargeTime2Mon=");
        stringBuffer.append(this.ChargeTime2Mon);
        stringBuffer.append(", ChargeTime2Day=");
        stringBuffer.append(this.ChargeTime2Day);
        stringBuffer.append(", ChargeTime2Hour=");
        stringBuffer.append(this.ChargeTime2Hour);
        stringBuffer.append(", ChargeTime2Min=");
        stringBuffer.append(this.ChargeTime2Min);
        stringBuffer.append(", TimeMode=");
        stringBuffer.append(this.TimeMode);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
