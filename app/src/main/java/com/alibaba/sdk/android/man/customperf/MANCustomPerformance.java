package com.alibaba.sdk.android.man.customperf;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class MANCustomPerformance {
    public static final String TAG = "MAN_MANCustomPerformance";
    private long duration = -1;
    private String eventLabel = null;
    private Map<String, String> properties = new HashMap();

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public String getEventLabel() {
        return this.eventLabel;
    }

    public void setEventLabel(String str) {
        this.eventLabel = str;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }
}
