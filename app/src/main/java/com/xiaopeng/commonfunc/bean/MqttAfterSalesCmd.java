package com.xiaopeng.commonfunc.bean;

/* loaded from: classes11.dex */
public class MqttAfterSalesCmd {
    private String cmd_param;
    private String cmd_type;

    public String getCmd_type() {
        return this.cmd_type;
    }

    public void setCmd_type(String str) {
        this.cmd_type = str;
    }

    public String getCmd_param() {
        return this.cmd_param;
    }

    public void setCmd_param(String str) {
        this.cmd_param = str;
    }

    public String toString() {
        return "MqttAfterSalesCmd{cmd_type='" + this.cmd_type + "', cmd_param='" + this.cmd_param + "'}";
    }
}
