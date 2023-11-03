package com.xiaopeng.commonfunc.bean.event.signal;

/* loaded from: classes11.dex */
public class WifiNameSSEvent {
    private String ip;
    private String name;
    private String speed;
    private String strength;

    public WifiNameSSEvent() {
        this.name = "UNKNOWN";
        this.strength = "UNKNOWN";
        this.speed = "UNKNOWN";
        this.ip = "UNKNOWN";
    }

    public WifiNameSSEvent(String str, String str2, String str3, String str4) {
        this.name = str;
        this.strength = str2;
        this.speed = str3;
        this.ip = str4;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getStrength() {
        return this.strength;
    }

    public void setStrength(String str) {
        this.strength = str;
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String str) {
        this.speed = str;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String str) {
        this.ip = str;
    }

    public String toString() {
        return "WifiNameSSEvent{name='" + this.name + "', strength='" + this.strength + "', speed='" + this.speed + "', ip='" + this.ip + "'}";
    }
}
