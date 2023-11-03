package com.xiaopeng.commonfunc.bean.car;

import android.util.Log;
import java.util.Arrays;

/* loaded from: classes11.dex */
public class AndroidPsuOtaMsg {
    public int otaSt;
    public int psuResetReq;
    public int reserved0;
    public int reserved1;
    public int reserved2;
    public int wakeUpHour;
    public int wakeUpMin;
    public int wakeUpSecond;
    public int wakeUpSetReq;
    public int wakeUpSetType;

    public int[] packToIntArray() {
        int[] iArr = new int[4];
        iArr[0] = this.psuResetReq & 255;
        iArr[0] = iArr[0] | ((this.wakeUpSetReq & 255) << 8);
        iArr[0] = iArr[0] | ((this.wakeUpSetType & 255) << 16);
        iArr[0] = iArr[0] | ((this.wakeUpHour & 255) << 24);
        iArr[1] = this.wakeUpMin & 255;
        iArr[1] = iArr[1] | ((this.wakeUpSecond & 255) << 8);
        iArr[1] = iArr[1] | ((this.otaSt & 255) << 16);
        iArr[1] = iArr[1] | ((this.reserved0 & 255) << 24);
        iArr[2] = this.reserved1 & 255;
        iArr[2] = iArr[2] | ((this.reserved2 & 255) << 8);
        Log.d("AndroidPsuOtaMsg", "packToIntArray data = " + Arrays.toString(iArr));
        return iArr;
    }
}
