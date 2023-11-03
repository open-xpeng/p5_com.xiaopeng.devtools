package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class McuGsensorOffsetAckTest {
    public int offset_x;
    public int offset_y;
    public int offset_z;

    public McuGsensorOffsetAckTest(int[] iArr) {
        this.offset_x = iArr[0];
        this.offset_y = iArr[1];
        this.offset_z = iArr[2];
    }

    public String toString() {
        return "McuGsensorOffsetAckTest{offset_x=" + this.offset_x + ", offset_y=" + this.offset_y + ", offset_z=" + this.offset_z + '}';
    }
}
