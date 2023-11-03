package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class McuOta1Msg {
    public int McuAckArdRstNotify;
    public int McuAckOtaTestAck;
    public int McuPacketSt;
    public int McuProCondStAck;

    public McuOta1Msg(int[] iArr) {
        this.McuProCondStAck = iArr[0];
        this.McuPacketSt = iArr[1];
        this.McuAckOtaTestAck = iArr[2];
        this.McuAckArdRstNotify = iArr[3];
    }

    public String toString() {
        return "McuProCondStAck=" + this.McuProCondStAck;
    }
}
