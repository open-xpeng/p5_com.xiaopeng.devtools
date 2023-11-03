package com.xiaopeng.devtools.bean.event;

/* loaded from: classes12.dex */
public class CommonEvent {
    public static final int DIAGNOSIS_ACTION_PAUSE = 10002;
    public static final int HTTP_PING_EVENT = 10001;
    private String mMsg;
    private int mState;

    public CommonEvent(int i, String str) {
        this.mState = i;
        this.mMsg = str;
    }

    public int getState() {
        return this.mState;
    }

    public String getMsg() {
        return this.mMsg;
    }
}
