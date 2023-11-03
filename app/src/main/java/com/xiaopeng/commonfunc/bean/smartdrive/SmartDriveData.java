package com.xiaopeng.commonfunc.bean.smartdrive;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class SmartDriveData {
    @SerializedName("btName")
    private String btName;
    @SerializedName("CDU_SCU_Test")
    private int cduScuTest;

    public String getBtName() {
        return this.btName;
    }

    public void setBtName(String str) {
        this.btName = str;
    }

    public int getCduScuTest() {
        return this.cduScuTest;
    }

    public void setCduScuTest(int i) {
        this.cduScuTest = i;
    }
}
