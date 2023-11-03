package com.xiaopeng.devtools.bean.event.signal;

import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.d;

/* loaded from: classes12.dex */
public class WifiNameSSEvent {
    private String ip;
    private String name;
    private String speed;
    private String strength;

    public WifiNameSSEvent() {
        this.name = d.getString(R.string.state_unknown);
        this.strength = d.getString(R.string.state_unknown);
        this.speed = d.getString(R.string.state_unknown);
        this.ip = d.getString(R.string.state_unknown);
    }

    public WifiNameSSEvent(String str, String str2, String str3, String str4) {
        this.name = str;
        this.strength = str2;
        this.speed = str3;
        this.ip = str4;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setStrength(String str) {
        this.strength = str;
    }

    public void setSpeed(String str) {
        this.speed = str;
    }

    public void setIp(String str) {
        this.ip = str;
    }

    public String getName() {
        return this.name;
    }

    public String getStrength() {
        return this.strength;
    }

    public String getSpeed() {
        return this.speed;
    }

    public String getIp() {
        return this.ip;
    }

    public String toString() {
        return "WifiNameSSEvent{name='" + this.name + "', strength='" + this.strength + "', speed='" + this.speed + "', ip='" + this.ip + "'}";
    }
}
