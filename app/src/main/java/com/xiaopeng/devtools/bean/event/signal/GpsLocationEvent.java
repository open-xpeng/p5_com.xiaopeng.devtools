package com.xiaopeng.devtools.bean.event.signal;

/* loaded from: classes12.dex */
public class GpsLocationEvent {
    private String locationTime;
    private String mLocation;

    public GpsLocationEvent(String str, String str2) {
        this.locationTime = str;
        this.mLocation = str2;
    }

    public String getLocationTime() {
        return this.locationTime;
    }

    public String getLocation() {
        return this.mLocation;
    }
}
