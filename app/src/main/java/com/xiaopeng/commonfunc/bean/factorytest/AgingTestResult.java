package com.xiaopeng.commonfunc.bean.factorytest;

import java.lang.reflect.Array;

/* loaded from: classes11.dex */
public class AgingTestResult {
    public int[][] testrecord = (int[][]) Array.newInstance(int.class, 3, 2);

    public AgingTestResult(String str) {
        byte[] bytes = str.getBytes();
        this.testrecord[0][0] = bytes[0] - 48;
        this.testrecord[0][1] = bytes[2] - 48;
        this.testrecord[1][0] = bytes[4] - 48;
        this.testrecord[1][1] = bytes[6] - 48;
        this.testrecord[2][0] = bytes[8] - 48;
        this.testrecord[2][1] = bytes[10] - 48;
    }

    public String toString() {
        return "iozone     测试次数 ：" + this.testrecord[0][0] + "\niozone     PASS次数 ：" + this.testrecord[0][1] + "\nmemtester  测试次数 ：" + this.testrecord[1][0] + "\nmemtester  PASS次数 ：" + this.testrecord[1][1] + "\ncpu_stress 测试次数 ：" + this.testrecord[2][0] + "\ncpu_stress PASS次数 ：" + this.testrecord[2][1];
    }

    public String toStringForAt() {
        return "" + this.testrecord[0][0] + "," + this.testrecord[0][1] + "," + this.testrecord[1][0] + "," + this.testrecord[1][1] + "," + this.testrecord[2][0] + "," + this.testrecord[2][1];
    }

    public byte[] toByteArrayForDm() {
        return new byte[]{(byte) (this.testrecord[0][0] & 255), (byte) (this.testrecord[0][1] & 255), (byte) (this.testrecord[1][0] & 255), (byte) (this.testrecord[1][1] & 255), (byte) (this.testrecord[2][0] & 255), (byte) (this.testrecord[2][1] & 255)};
    }
}
