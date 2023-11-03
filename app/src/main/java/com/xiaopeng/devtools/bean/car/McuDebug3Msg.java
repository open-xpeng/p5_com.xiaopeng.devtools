package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class McuDebug3Msg {
    public int SWEn5v25;
    public int ant12VEn;
    public int awakeFailTime;
    public int batOn4G;
    public int blPwr;
    public int ctpPwr;
    public int dvrUsbPwr;
    public int fmSt;
    public int frUsbPwr;
    public int gnassSt;
    public int gnssAntPwr;
    public int gnssPwr;
    public int mUsbPwr;
    public int mainPwr;
    public int mcu2CoreBluEn;
    public int mic8vEn;
    public int micSt;
    public int pmOverVolTime;
    public int pmSynFailTime;
    public int pmUnderVolTime;
    public int ps1v8LREn;
    public int ps3v3SwEn;
    public int ps5vSwEn;
    public int psEn;
    public int sleepFailTime;

    public McuDebug3Msg(byte[] bArr) {
        this.psEn = bArr[0] & 255;
        this.batOn4G = bArr[1] & 255;
        this.blPwr = bArr[2] & 255;
        this.ant12VEn = bArr[3] & 255;
        this.mic8vEn = bArr[4] & 255;
        this.SWEn5v25 = bArr[5] & 255;
        this.ps3v3SwEn = bArr[6] & 255;
        this.ps1v8LREn = bArr[7] & 255;
        this.mUsbPwr = bArr[8] & 255;
        this.frUsbPwr = bArr[9] & 255;
        this.dvrUsbPwr = bArr[10] & 255;
        this.ctpPwr = bArr[11] & 255;
        this.gnssPwr = bArr[12] & 255;
        this.gnssAntPwr = bArr[13] & 255;
        this.mainPwr = bArr[14] & 255;
        this.mcu2CoreBluEn = bArr[15] & 255;
        this.ps5vSwEn = bArr[16] & 255;
        this.fmSt = bArr[17] & 255;
        this.gnassSt = bArr[18] & 255;
        this.micSt = bArr[19] & 255;
        this.sleepFailTime = bArr[20] & 255;
        this.awakeFailTime = bArr[21] & 255;
        this.pmSynFailTime = bArr[22] & 255;
        this.pmOverVolTime = bArr[23] & 255;
        this.pmUnderVolTime = bArr[24] & 255;
    }
}
