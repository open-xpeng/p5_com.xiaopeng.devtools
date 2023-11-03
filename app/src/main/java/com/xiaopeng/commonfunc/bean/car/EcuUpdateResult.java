package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class EcuUpdateResult {
    public static final String FAIL_REASON_SEND_FILE_FAIL = "fail send file to QNX";
    public static final String RESULT_FAIL = "fail";
    public static final String RESULT_MORE = "more";
    public static final String RESULT_SUCCESS = "success";
    private String Reason;
    private String Result;
    private String Target;

    public EcuUpdateResult(String str, String str2, String str3) {
        this.Target = str;
        this.Result = str2;
        this.Reason = str3;
    }

    public String getTarget() {
        return this.Target;
    }

    public void setTarget(String str) {
        this.Target = str;
    }

    public String getResult() {
        return this.Result;
    }

    public void setResult(String str) {
        this.Result = str;
    }

    public String getReason() {
        return this.Reason;
    }

    public void setReason(String str) {
        this.Reason = str;
    }

    public String toString() {
        return "EcuUpdateResult{Target='" + this.Target + "', Result='" + this.Result + "', Reason='" + this.Reason + "'}";
    }
}
