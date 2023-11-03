package com.xiaopeng.devtools.bean.car;

import android.util.Log;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class McuAckTest {
    public float BAT_VOL_DET_RESULT;
    public int BAT_VOL_DET_VD;
    public int CAN_TEST_RESULT;
    public int EEPROM_TEST_RESULT;
    public int FMANT_TEST_RESULT;
    public int GNSSANT_TEST_RESULT;
    public int GNSS_COM_TEST_RESULT;
    public int MIC_TEST_RESULT;
    public int TEMP_DET_RESULT;
    public int TEMP_DET_VD;

    public McuAckTest(int[] iArr) {
        this.EEPROM_TEST_RESULT = iArr[0] & 255;
        this.CAN_TEST_RESULT = (iArr[0] >> 8) & 255;
        this.MIC_TEST_RESULT = (iArr[0] >> 16) & 255;
        this.GNSSANT_TEST_RESULT = (iArr[0] >> 24) & 255;
        this.FMANT_TEST_RESULT = iArr[1] & 255;
        this.TEMP_DET_VD = (iArr[1] >> 8) & 255;
        this.TEMP_DET_RESULT = (byte) ((iArr[1] >> 16) & 255);
        this.BAT_VOL_DET_VD = (iArr[1] >> 24) & 255;
        this.BAT_VOL_DET_RESULT = (float) ((iArr[2] & 65535) / 100.0d);
        this.GNSS_COM_TEST_RESULT = (iArr[2] >> 16) & 255;
        Log.d("McuAckTest", "McuAckTest data = " + Arrays.toString(iArr));
    }

    public String toString() {
        return "EEPROM_TEST_RESULT=" + this.EEPROM_TEST_RESULT + "\n CAN_TEST_RESULT=" + this.CAN_TEST_RESULT;
    }
}
