package com.xiaopeng.devtools.bean.car;

import android.util.Log;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class AndroidTest {
    public int batVolDet;
    public int canTest;
    public int eepromTest;
    public int fmAntTest;
    public int gnssAntTest;
    public int gnssComDet;
    public int micTest;
    public int tempDet;

    public int[] packToIntArray() {
        int[] iArr = new int[4];
        iArr[0] = this.eepromTest & 255;
        iArr[0] = iArr[0] | ((this.canTest & 255) << 8);
        iArr[0] = iArr[0] | ((this.micTest & 255) << 16);
        iArr[0] = iArr[0] | ((this.gnssAntTest & 255) << 24);
        iArr[1] = this.fmAntTest & 255;
        iArr[1] = iArr[1] | ((this.tempDet & 255) << 8);
        iArr[1] = iArr[1] | ((this.batVolDet & 255) << 16);
        iArr[1] = iArr[1] | ((this.gnssComDet & 255) << 24);
        Log.d("AndroidTest", "packToIntArray data = " + Arrays.toString(iArr));
        return iArr;
    }
}
