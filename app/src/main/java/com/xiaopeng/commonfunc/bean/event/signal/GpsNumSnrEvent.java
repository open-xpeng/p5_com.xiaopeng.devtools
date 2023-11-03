package com.xiaopeng.commonfunc.bean.event.signal;

/* loaded from: classes11.dex */
public class GpsNumSnrEvent {
    private final int count;
    private final String snrString;

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
