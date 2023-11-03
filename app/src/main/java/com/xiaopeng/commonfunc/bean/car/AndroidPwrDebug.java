package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class AndroidPwrDebug {
    public int SWEn5v25;
    public int ant12vEn;
    public int batOn4G;
    public int blPwr;
    public int debugPwrSt;
    public int dvrUsbPwr;
    public int fan12vEn;
    public int frUsbPwr;
    public int gnssAntPwr;
    public int gnssPwr;
    public int mUsbPwr;
    public int mic8vEn;
    public int pwr4G;

    public int[] packToIntArray() {
        int[] iArr = new int[4];
        iArr[0] = this.blPwr & 255;
        iArr[0] = iArr[0] | ((this.gnssPwr & 255) << 8);
        iArr[0] = iArr[0] | ((this.gnssAntPwr & 255) << 16);
        iArr[0] = iArr[0] | ((this.batOn4G & 255) << 24);
        iArr[1] = this.pwr4G & 255;
        iArr[1] = iArr[1] | ((this.SWEn5v25 & 255) << 8);
        iArr[1] = iArr[1] | ((this.dvrUsbPwr & 255) << 16);
        iArr[1] = iArr[1] | ((this.mUsbPwr & 255) << 24);
        iArr[2] = this.frUsbPwr & 255;
        iArr[2] = iArr[2] | ((this.ant12vEn & 255) << 8);
        iArr[2] = iArr[2] | ((this.mic8vEn & 255) << 16);
        iArr[2] = iArr[2] | ((this.fan12vEn & 255) << 24);
        iArr[3] = this.debugPwrSt & 255;
        return iArr;
    }

    public String toString() {
        return "blPwr=" + this.blPwr + "\n gnssPwr=" + this.gnssPwr + "\n gnssAntPwr=" + this.gnssAntPwr + "\n batOn4G=" + this.batOn4G + "\n pwr4G=" + this.pwr4G + "\n SWEn5v25=" + this.SWEn5v25 + "\n dvrUsbPwr=" + this.dvrUsbPwr + "\n mUsbPwr=" + this.mUsbPwr + "\n frUsbPwr=" + this.frUsbPwr + "\n ant12vEn=" + this.ant12vEn + "\n mic8vEn=" + this.mic8vEn + "\n fan12vEn=" + this.fan12vEn + "\n debugPwrSt=" + this.debugPwrSt;
    }
}
