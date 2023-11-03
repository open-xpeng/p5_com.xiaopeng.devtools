package com.xiaopeng.devtools.bean.car;

import android.util.Log;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class AndroidChargeMsg {
    public int chargeMode;
    public int day1;
    public int day2;
    public int hour1;
    public int hour2;
    public int minute1;
    public int minute2;
    public int month1;
    public int month2;
    public int stopChargeSoc;
    public int timeMode;
    public int week1;
    public int week2;

    public int[] packToIntArray() {
        int[] iArr = new int[4];
        iArr[0] = this.chargeMode & 255;
        iArr[0] = iArr[0] | ((this.stopChargeSoc & 255) << 8);
        iArr[0] = iArr[0] | ((this.week1 & 255) << 16);
        iArr[0] = iArr[0] | ((this.month1 & 255) << 24);
        iArr[1] = this.day1 & 255;
        iArr[1] = iArr[1] | ((this.hour1 & 255) << 8);
        iArr[1] = iArr[1] | ((this.minute1 & 255) << 16);
        iArr[1] = iArr[1] | ((this.week2 & 255) << 24);
        iArr[2] = this.month2 & 255;
        iArr[2] = iArr[2] | ((this.day2 & 255) << 8);
        iArr[2] = iArr[2] | ((this.hour2 & 255) << 16);
        iArr[2] = iArr[2] | ((this.minute2 & 255) << 24);
        iArr[3] = this.timeMode & 255;
        Log.d("AndroidChargeMsg", "packToIntArray data = " + Arrays.toString(iArr));
        return iArr;
    }
}
