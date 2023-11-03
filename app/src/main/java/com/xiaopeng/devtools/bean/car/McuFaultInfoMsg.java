package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class McuFaultInfoMsg {
    public int ApmStSynFailCount;
    public int BattRstCount;
    public int EepReadErrCount;
    public int EepWriteErrCount;
    public int HardErrRstCount;
    public int IapCount;
    public int IapFailCount;
    public int RtcErrCount;
    public int SoftRstCount;
    public int UartRxOverFlowCount;
    public int UartTxOverFlowCount;
    public int WdgRstCount;

    public McuFaultInfoMsg(int[] iArr) {
        this.SoftRstCount = iArr[0] & 255;
        this.BattRstCount = (iArr[0] >> 8) & 255;
        this.WdgRstCount = (iArr[0] >> 16) & 255;
        this.HardErrRstCount = (iArr[0] >> 24) & 255;
        this.RtcErrCount = iArr[1] & 255;
        this.EepReadErrCount = (iArr[1] >> 8) & 255;
        this.EepWriteErrCount = (iArr[1] >> 16) & 255;
        this.IapFailCount = (iArr[1] >> 24) & 255;
        this.IapCount = iArr[2] & 255;
        this.ApmStSynFailCount = (iArr[2] >> 8) & 255;
        this.UartRxOverFlowCount = (iArr[2] >> 16) & 255;
        this.UartTxOverFlowCount = (iArr[2] >> 24) & 255;
    }

    public String toString() {
        return "SoftRstCount=" + this.SoftRstCount;
    }
}
