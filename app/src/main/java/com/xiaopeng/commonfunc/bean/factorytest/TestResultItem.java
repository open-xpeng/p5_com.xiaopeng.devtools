package com.xiaopeng.commonfunc.bean.factorytest;

/* loaded from: classes11.dex */
public class TestResultItem {
    public static final int INDEX_4G_CONNECTION = 229;
    public static final int INDEX_AGINGTEST = 208;
    public static final int INDEX_BURNTEST = 200;
    public static final int INDEX_BURNTEST_4G = 201;
    public static final int INDEX_BURNTEST_BT = 203;
    public static final int INDEX_BURNTEST_CPUSTRESS = 207;
    public static final int INDEX_BURNTEST_GPS = 204;
    public static final int INDEX_BURNTEST_IOZONE = 205;
    public static final int INDEX_BURNTEST_MEMTESTER = 206;
    public static final int INDEX_BURNTEST_WIFI = 202;
    public static final int INDEX_CDU_CERT = 231;
    public static final int INDEX_KEYTEST = 230;
    public static final int INDEX_PSO_CERT = 234;
    public static final int INDEX_SEC_INDIV = 235;
    public static final int INDEX_TBOX_CERT = 232;
    public static final int INDEX_TOUCHTEST = 152;
    public static final int INDEX_V18_CDU_CERT = 236;
    public static final int INDEX_WIFI_CERT = 233;
    public static final byte RESULT_ENTER = 69;
    public static final byte RESULT_FAIL = 70;
    public static final int RESULT_INDEX_SIZE = 3;
    public static final byte RESULT_NOTEST = 78;
    public static final byte RESULT_PASS = 80;
    public static final int RESULT_SIZE = 4;
    private final int mIndex;
    private final byte mStatus;

    public TestResultItem(int i, byte b) {
        this.mIndex = i;
        this.mStatus = b;
    }

    public String toString() {
        return String.format("%03d%c", Integer.valueOf(this.mIndex), Byte.valueOf(this.mStatus));
    }
}
