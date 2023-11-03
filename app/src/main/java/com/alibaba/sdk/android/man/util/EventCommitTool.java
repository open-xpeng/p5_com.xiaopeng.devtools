package com.alibaba.sdk.android.man.util;

import com.alibaba.sdk.android.man.MANTracker;
import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class EventCommitTool {
    private static final String TAG = "MAN_EventCommitTool";

    public static void commitEvent(int i, String str, Map<String, String> map) {
        if (str != null && map != null) {
            if (Aggregation.getInstance().addToNetPerfAggregation(map)) {
                MANLog.Logi(TAG, "ToAggregation : " + i + ", " + map.toString());
                return;
            }
            commitEventDirectly(i, str, map);
            return;
        }
        MANLog.Logw(TAG, "[commitEvent] - eventLabel: " + str + ", property : " + map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void commitEventDirectly(int i, String str, Map<String, String> map) {
        if (str != null && map != null) {
            commitEventToUT("UT", i, str, "", "", map);
            return;
        }
        MANLog.Logf(TAG, "[commitEvent] - eventLabel: " + str + ", property : " + map);
    }

    public static void commitCustomPerformanceEvent(MANCustomPerformance mANCustomPerformance) {
        if (Aggregation.getInstance().addCustomPerfToAggregation(mANCustomPerformance)) {
            if (MANLog.isPrintLog()) {
                MANLog.Logi(TAG, "ToAggregation : 66602, duration=" + mANCustomPerformance.getDuration() + ", label=" + mANCustomPerformance.getEventLabel());
                return;
            }
            return;
        }
        commitEventToUT("UT", MANConfig.CUSTOM_PERFORMANCE_EVENT_ID, mANCustomPerformance.getEventLabel(), "", String.valueOf(mANCustomPerformance.getDuration()), mANCustomPerformance.getProperties());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void commitEventToUT(String str, int i, String str2, String str3, String str4, Map<String, String> map) {
        synchronized (EventCommitTool.class) {
            if (map == null) {
                try {
                    map = new HashMap<>();
                } catch (Throwable th) {
                    throw th;
                }
            }
            Map<String, String> map2 = map;
            map2.put(MANConfig.SDK_VERSION_KEY, MANConfig.SDK_VERSION_VALUE);
            if (MANLog.isPrintLog()) {
                MANLog.Logd(TAG, "commitEventFinally : eventId=" + i + ", arg1=" + str2 + ", arg2=" + str3 + ", arg3=" + str4 + ", " + map2.toString());
            }
            MANTracker.getInstance().send(new UTOriginalCustomHitBuilder(str, i, str2, str3, str4, map2).build());
        }
    }
}
