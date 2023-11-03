package com.xiaopeng.commonfunc.bean.event.signal;

/* loaded from: classes11.dex */
public class WifiStateMacEvent {
    private String mac;
    private int state;

    public WifiStateMacEvent() {
        this.state = 0;
        this.mac = "UNKNOWN";
    }

    public WifiStateMacEvent(int i, String str) {
        this.state = i;
        this.mac = str;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public String toString() {
        return "WifiStateMacEvent{state=" + this.state + ", mac='" + this.mac + "'}";
    }
}
