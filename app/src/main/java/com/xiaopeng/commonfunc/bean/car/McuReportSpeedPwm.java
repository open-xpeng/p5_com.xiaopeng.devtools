package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class McuReportSpeedPwm {
    public int PWM_FRQ_RESULT;
    public int SPEED_RESULT;

    public McuReportSpeedPwm(int[] iArr) {
        this.SPEED_RESULT = iArr[0];
        this.PWM_FRQ_RESULT = iArr[1];
    }

    public String toString() {
        return "SPEED_RESULT=" + this.SPEED_RESULT + "\n PWM_FRQ_RESULT=" + this.PWM_FRQ_RESULT;
    }
}
