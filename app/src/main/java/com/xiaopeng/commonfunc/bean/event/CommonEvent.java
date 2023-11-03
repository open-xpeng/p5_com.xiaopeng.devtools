package com.xiaopeng.commonfunc.bean.event;

/* loaded from: classes11.dex */
public class CommonEvent {
    public static final int DIAGNOSIS_ACTION_PAUSE = 10002;
    public static final int DIAGNOSIS_DISMISS_LOADING_DIALOG = 10003;
    public static final int DIAGNOSIS_LOGICTREE_INIT_FINISH = 10004;
    public static final int DISPLAY_LOGICTREE_TOAST = 10006;
    public static final int DISPLAY_ONE_CLICK_DIAGNOSIS_RUNNING = 10005;
    public static final int HTTP_PING_EVENT = 10001;
    public static final int REMOTE_ONE_CLICK_DIAGNOSIS_INIT_FINISH = 10009;
    public static final int REMOTE_ONE_CLICK_DIAGNOSIS_INTERACTIVE = 10011;
    public static final int REMOTE_ONE_CLICK_DIAGNOSIS_LOG_UPLOAD = 10012;
    public static final int REMOTE_ONE_CLICK_DIAGNOSIS_LOG_UPLOAD_PASSWORD = 10013;
    public static final int REMOTE_ONE_CLICK_DIAGNOSIS_RESULT = 10010;
    public static final int UPGRADE_LOGICTREE_FAIL = 10007;
    public static final int UPGRADE_LOGICTREE_SUCCESS = 10008;
    private final String mMsg;
    private final int mState;

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

    public String toString() {
        return "CommonEvent{mState=" + this.mState + ", mMsg='" + this.mMsg + "'}";
    }
}
