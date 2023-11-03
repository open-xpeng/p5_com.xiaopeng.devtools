package com.xiaopeng.devtools.bean.event;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes12.dex */
public class BoardCastEvent {
    public static final String DATA_FAIL = "1";
    public static final String DATA_SUCCESS = "0";
    public static final int TYPE_OTA_CDU = 0;
    public static final int TYPE_OTA_ICM = 3;
    public static final int TYPE_OTA_MCU = 1;
    public static final int TYPE_OTA_PSU = 2;
    @SerializedName("data")
    private String data;
    @SerializedName("type")
    private int type;

    public BoardCastEvent(int i, String str) {
        this.type = i;
        this.data = str;
    }

    public int getType() {
        return this.type;
    }

    public String getData() {
        return this.data;
    }
}
