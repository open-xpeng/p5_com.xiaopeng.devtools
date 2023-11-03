package com.xiaopeng.commonfunc.bean;

/* loaded from: classes11.dex */
public class MqttAfterSalesResult {
    public static final int OPERATION_FAIL = 2;
    public static final int OPERATION_FAIL_UPGRADE_LOGIC_TREE = 10;
    public static final int OPERATION_FAIL_UPLOAD_TO_OSS = 8;
    public static final int OPERATION_FAIL_VIN_MISMATCH = 9;
    public static final int OPERATION_FIND_CMD_TYPE_FAIL = 7;
    public static final int OPERATION_LAST_DIAGNOSIS_NO_FINISH = 13;
    public static final int OPERATION_LOGICTREE_INIT_FAIL = 14;
    public static final int OPERATION_LOGIC_MSGID_MISMATCH = 11;
    public static final int OPERATION_NEEDAUTHMODE = 4;
    public static final int OPERATION_NOTUNDERPLEVEL = 5;
    public static final int OPERATION_NO_SUCH_LOGIC_TREE = 12;
    public static final int OPERATION_SUCCESS = 1;
    public static final int OPERATION_TIMEOUT = 6;
    public static final int OPERATION_UNSUPPORTED = 3;
    private int cmd_result;
    private String cmd_result_file;
    private String cmd_result_info;
    private String cmd_result_string;

    public MqttAfterSalesResult(int i, String str, String str2, String str3) {
        this.cmd_result = i;
        this.cmd_result_file = str;
        this.cmd_result_string = str2;
        this.cmd_result_info = str3;
    }

    public int getCmd_result() {
        return this.cmd_result;
    }

    public void setCmd_result(int i) {
        this.cmd_result = i;
    }

    public String getCmd_result_file() {
        return this.cmd_result_file;
    }

    public void setCmd_result_file(String str) {
        this.cmd_result_file = str;
    }

    public String getCmd_result_string() {
        return this.cmd_result_string;
    }

    public void setCmd_result_string(String str) {
        this.cmd_result_string = str;
    }

    public String getCmd_result_info() {
        return this.cmd_result_info;
    }

    public void setCmd_result_info(String str) {
        this.cmd_result_info = str;
    }

    public String toString() {
        return "MqttAfterSalesResult{cmd_result=" + this.cmd_result + ", cmd_result_file='" + this.cmd_result_file + "', cmd_result_string='" + this.cmd_result_string + "', cmd_result_info='" + this.cmd_result_info + "'}";
    }
}
