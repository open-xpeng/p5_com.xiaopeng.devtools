package com.xiaopeng.commonfunc.bean.oled;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class OledData {
    @SerializedName("btName")
    private String btName;
    @SerializedName("CDU_OELD_DynamicModeCfg")
    private int cduOledDynamicMode;

    public String getBtName() {
        return this.btName;
    }

    public void setBtName(String str) {
        this.btName = str;
    }

    public int getCduOledDynamicMode() {
        return this.cduOledDynamicMode;
    }

    public void setCduOledDynamicMode(int i) {
        this.cduOledDynamicMode = i;
    }
}
