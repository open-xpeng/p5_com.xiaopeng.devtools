package com.xiaopeng.devtools.bean.event.signal;

/* loaded from: classes12.dex */
public class GpsNumSnrEvent {
    private int count;
    private String snrString;

    public GpsNumSnrEvent(int i, String str) {
        this.count = i;
        this.snrString = str;
    }

    public int getCount() {
        return this.count;
    }

    public String getSnrString() {
        return this.snrString;
    }
}
