package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class IPNPort {
    private String ip;
    private String port;

    public IPNPort() {
    }

    public IPNPort(String str, String str2) {
        this.ip = str;
        this.port = str2;
    }

    public String getIp() {
        return this.ip;
    }

    public String getPort() {
        return this.port;
    }

    public String toString() {
        return "IPNPort{ip='" + this.ip + "', port='" + this.port + "'}";
    }
}
