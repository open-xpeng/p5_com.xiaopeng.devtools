package com.xiaopeng.commonfunc.bean.event.signal;

/* loaded from: classes11.dex */
public class GpsLocationEvent {
    private final String locationTime;
    private final String mLocation;

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
