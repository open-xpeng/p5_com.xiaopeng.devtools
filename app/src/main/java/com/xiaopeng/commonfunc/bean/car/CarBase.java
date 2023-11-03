package com.xiaopeng.commonfunc.bean.car;

import com.google.gson.annotations.SerializedName;
import com.xiaopeng.lib.apirouter.ClientConstants;

/* loaded from: classes11.dex */
public class CarBase<T> {
    public static final String BLE_REPLACE_REQ = "ble_replace_req";
    public static final String BLE_REPLACE_RESP = "ble_replace_resp";
    @SerializedName(ClientConstants.BINDER.SCHEME)
    private T mContent;
    @SerializedName("id")
    private String mId;

    public CarBase(String str, T t) {
        this.mId = str;
        this.mContent = t;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public T getContent() {
        return this.mContent;
    }

    public void setContent(T t) {
        this.mContent = t;
    }
}
