package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class McuAckPwrDebug {
    public int uP24G_BAT_ON_OFF_RESULT;
    public int uP24G_PWR_ON_RESULT;
    public int uP25V25_SW_EN_RESULT;
    public int uP2ANT_12V_EN_RESULT;
    public int uP2BL_PWR_EN_RESULT;
    public int uP2DVRUSB_PWR_EN_RESULT;
    public int uP2FAN_12V_EN_RESULT;
    public int uP2FRUSB_PWR_EN_RESULT;
    public int uP2GNSS_ANT_PWR_RESULT;
    public int uP2GNSS_PWR_EN_RESULT;
    public int uP2MIC8V_ON_OFF_RESULT;
    public int uP2MUSB_PWR_EN_RESULT;

    public McuAckPwrDebug(int[] iArr) {
        this.uP2BL_PWR_EN_RESULT = iArr[0] & 255;
        this.uP2GNSS_PWR_EN_RESULT = (iArr[0] >> 8) & 255;
        this.uP2GNSS_ANT_PWR_RESULT = (iArr[0] >> 16) & 255;
        this.uP24G_BAT_ON_OFF_RESULT = (iArr[0] >> 24) & 255;
        this.uP24G_PWR_ON_RESULT = iArr[1] & 255;
        this.uP25V25_SW_EN_RESULT = (iArr[1] >> 8) & 255;
        this.uP2DVRUSB_PWR_EN_RESULT = (iArr[1] >> 16) & 255;
        this.uP2MUSB_PWR_EN_RESULT = (iArr[1] >> 24) & 255;
        this.uP2FRUSB_PWR_EN_RESULT = iArr[2] & 255;
        this.uP2ANT_12V_EN_RESULT = (iArr[2] >> 8) & 255;
        this.uP2MIC8V_ON_OFF_RESULT = (iArr[2] >> 16) & 255;
        this.uP2FAN_12V_EN_RESULT = (iArr[2] >> 24) & 255;
    }

    public String toString() {
        return "uP2BL_PWR_EN_RESULT=" + this.uP2BL_PWR_EN_RESULT + "\n uP2GNSS_PWR_EN_RESULT=" + this.uP2GNSS_PWR_EN_RESULT;
    }
}
