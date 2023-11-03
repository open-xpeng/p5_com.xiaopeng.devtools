package com.xiaopeng.devtools.bean.event.signal;

import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.d;

/* loaded from: classes12.dex */
public class WifiStateMacEvent {
    private String mac;
    private int state;

    public WifiStateMacEvent() {
        this.state = 0;
        this.mac = d.getString(R.string.state_unknown);
    }

    public WifiStateMacEvent(int i, String str) {
        this.state = i;
        this.mac = str;
    }

    public void setState(int i) {
        this.state = i;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public int getState() {
        return this.state;
    }

    public String getMac() {
        return this.mac;
    }

    public String toString() {
        return "WifiStateMacEvent{state=" + this.state + ", mac='" + this.mac + "'}";
    }
}
