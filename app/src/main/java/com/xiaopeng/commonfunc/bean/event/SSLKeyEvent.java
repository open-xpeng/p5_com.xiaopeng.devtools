package com.xiaopeng.commonfunc.bean.event;

/* loaded from: classes11.dex */
public class SSLKeyEvent {
    public static final int CDU_KEY_SERVER_CONNECTION_ERROR = 2;
    public static final int PSU_ACTIVATE_FAIL = 8;
    public static final int PSU_ID_GET_FAIL = 7;
    public static final int PSU_ID_ILLEGAL = 9;
    public static final int PSU_KEY_RESET_FAIL = 4;
    public static final int PSU_REPAIR_FINISH = 10;
    public static final int PSU_REQUEST_SERVER_FAIL = 11;
    public static final int REQUEST_CDU_KEY_ERROR = 3;
    public static final int REQUEST_TBOX_KEY_ERROR = 13;
    public static final int REQUEST_WIFI_KEY_ERROR = 6;
    public static final int SAVE_TBOX_KEY_FAIL = 14;
    public static final int TBOX_KEY_SERVER_CONNECTION_ERROR = 12;
    public static final int WIFI_CONNECT_ERROR = 1;
    public static final int WIFI_KEY_SERVER_CONNECTION_ERROR = 5;
    private String mErrorMessage;
    private final int mState;

    public SSLKeyEvent(int i) {
        this.mState = i;
    }

    public SSLKeyEvent(int i, String str) {
        this.mState = i;
        this.mErrorMessage = str;
    }

    public int getState() {
        return this.mState;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }
}
