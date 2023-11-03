package com.alibaba.sdk.android.man.customperf;

import com.alibaba.sdk.android.man.util.MANLog;
import com.alibaba.sdk.android.man.util.ToolKit;
import java.util.regex.Pattern;

/* loaded from: classes11.dex */
public class MANCustomPerformanceHitBuilder {
    private static final String TAG = "MAN_MANCustomPerformanceHitBuilder";
    static Pattern pattern = Pattern.compile("[A-Za-z0-9_]*");
    long beginTime = -1;
    MANCustomPerformance performance = new MANCustomPerformance();

    public MANCustomPerformanceHitBuilder(String str) {
        if (pattern.matcher(str).matches()) {
            this.performance.setEventLabel(str);
            return;
        }
        MANLog.Loge(TAG, "eventLabel illegal ：" + str);
    }

    public MANCustomPerformanceHitBuilder hitStart() {
        this.beginTime = System.currentTimeMillis();
        return this;
    }

    public MANCustomPerformanceHitBuilder hitEnd() {
        if (this.beginTime != -1) {
            this.performance.setDuration(System.currentTimeMillis() - this.beginTime);
            MANLog.Logd(TAG, "performance.duration = " + this.performance.getDuration());
        } else {
            MANLog.Loge(TAG, "Without hitBegin");
        }
        return this;
    }

    public MANCustomPerformanceHitBuilder setDuration(long j) {
        this.performance.setDuration(j);
        return this;
    }

    public MANCustomPerformanceHitBuilder withExtraInfo(String str, String str2) {
        if (!ToolKit.isNullOrEmpty(str) && !ToolKit.isNullOrEmpty(str2)) {
            this.performance.getProperties().put(str, str2);
        }
        return this;
    }

    public MANCustomPerformance build() {
        return this.performance;
    }
}
