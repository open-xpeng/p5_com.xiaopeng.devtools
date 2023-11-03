package com.alibaba.sdk.android.man.util;

import android.app.Application;
import android.content.Context;
import com.alibaba.sdk.android.beacon.Beacon;
import com.alibaba.sdk.android.man.a;
import com.alibaba.sdk.android.utils.AMSDevReporter;
import com.alibaba.sdk.android.utils.AlicloudTracker;
import com.alibaba.sdk.android.utils.AlicloudTrackerManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class UTWrapper {
    public static final String BIZ_CRASH_ACTIVE = "biz_crash_active";
    public static final String BIZ_CUSTOM_ACTIVE = "biz_custom_active";
    public static final String BIZ_PAGE_ACTIVE = "biz_page_active";
    public static final String BIZ_PERF_ACTIVE = "biz_perf_active";
    public static final String BIZ_USER_ACTIVE = "biz_user_active";
    public static final String BZ_ACTIVE = "biz_active";
    private static final String MODULE = "man";
    public static final String PAGE_ASSIST_TYPE = "1";
    public static final String PAGE_BASIC_TYPE = "2";
    public static final String PERF_ADVANCE_TYPE = "2";
    public static final String PERF_CUSTOM_TYPE = "3";
    public static final String PERF_STANDARD_TYPE = "1";
    private static final String TAG = "UTWrapper";
    public static final String USER_LOGIN_TYPE = "2";
    public static final String USER_REGIST_TYPE = "1";
    private static Beacon beacon;
    private static AlicloudTracker tracker;
    private static boolean enable = true;
    private static Map<String, Boolean> isAlreadySendUtMap = new HashMap();
    private static final Beacon.OnUpdateListener UPDATE_LISTENER = new Beacon.OnUpdateListener() { // from class: com.alibaba.sdk.android.man.util.UTWrapper.1
        @Override // com.alibaba.sdk.android.beacon.Beacon.OnUpdateListener
        public void onUpdate(List<Beacon.Config> list) {
            MANLog.Logi(UTWrapper.TAG, "beacon onUpdate");
            try {
                if (UTWrapper.beacon != null) {
                    UTWrapper.beacon.stop();
                }
                if (list != null && list.size() > 0) {
                    for (Beacon.Config config : list) {
                        if ("___man_service___".equals(config.key)) {
                            if (!"disabled".equals(new JSONObject(config.value).optString("ut"))) {
                                boolean unused = UTWrapper.enable = true;
                            } else {
                                MANLog.Logd(UTWrapper.TAG, "disable ut");
                                boolean unused2 = UTWrapper.enable = false;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                MANLog.Loge(UTWrapper.TAG, "onUpdate Exception " + e.getMessage());
            }
        }
    };
    private static final Beacon.OnServiceErrListener ERR_LISTENER = new Beacon.OnServiceErrListener() { // from class: com.alibaba.sdk.android.man.util.UTWrapper.2
        @Override // com.alibaba.sdk.android.beacon.Beacon.OnServiceErrListener
        public void onErr(Beacon.Error error) {
            if (error != null) {
                MANLog.Logi(UTWrapper.TAG, "beacon onErr:" + error.errMsg + ",errorcode:" + error.errCode);
            }
        }
    };

    public static void utInit(String str, String str2, Application application) {
        try {
            if (isApkDebugable(application)) {
                AMSDevReporter.setLogEnabled(true);
            }
            tracker = AlicloudTrackerManager.getInstance(application).getTracker(MODULE, a.d);
            tracker.setGlobalProperty("appKey", str);
            HashMap hashMap = new HashMap();
            hashMap.put("sdkId", MODULE);
            hashMap.put("sdkVer", a.d);
            MANLog.Logd(TAG, "call utInit");
            beacon = new Beacon.Builder().appKey(str).appSecret(str2).loopInterval(3600000L).extras(hashMap).build();
            beacon.stop();
            beacon.addUpdateListener(UPDATE_LISTENER);
            beacon.addServiceErrListener(ERR_LISTENER);
            beacon.start(application);
        } catch (Throwable th) {
            MANLog.Loge(TAG, "utInit Exception " + th.getMessage());
            th.printStackTrace();
        }
    }

    public static void commitDAUEvent(Context context) {
        if (enable && !isAlreadySendUI(BZ_ACTIVE)) {
            commitEvent(BZ_ACTIVE);
            HashMap hashMap = new HashMap();
            hashMap.put(AMSDevReporter.AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_VERSION.toString(), a.d);
            AMSDevReporter.asyncReport(context.getApplicationContext(), AMSDevReporter.AMSSdkTypeEnum.AMS_MAN, hashMap);
        }
    }

    private static void commitEvent(String str) {
        commitEvent(str, 0L, null);
    }

    public static void commitPageEvent(String str) {
        if (enable) {
            if (isAlreadySendUI(BIZ_PAGE_ACTIVE + str)) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("type", str);
            commitEvent(BIZ_PAGE_ACTIVE, 0L, hashMap);
        }
    }

    public static void commitUserEvent(String str) {
        if (enable) {
            if (isAlreadySendUI(BIZ_USER_ACTIVE + str)) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("type", str);
            commitEvent(BIZ_USER_ACTIVE, 0L, hashMap);
        }
    }

    public static void commitPerfEvent(String str) {
        if (enable) {
            if (isAlreadySendUI(BIZ_PERF_ACTIVE + str)) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("type", str);
            commitEvent(BIZ_PERF_ACTIVE, 0L, hashMap);
        }
    }

    public static void commitCustomEvent() {
        if (!enable || isAlreadySendUI(BIZ_CUSTOM_ACTIVE)) {
            return;
        }
        commitEvent(BIZ_CUSTOM_ACTIVE);
    }

    public static void commitCrashEvent() {
        if (!enable || isAlreadySendUI(BIZ_CRASH_ACTIVE)) {
            return;
        }
        commitEvent(BIZ_CRASH_ACTIVE);
    }

    private static void commitEvent(String str, long j, Map<String, String> map) {
        if (!enable) {
            return;
        }
        try {
            tracker.sendCustomHit(str, j, map);
        } catch (Throwable th) {
            MANLog.Loge(TAG, "commitEvent " + str + " Exception " + th.getMessage());
            th.printStackTrace();
        }
    }

    public static boolean isApkDebugable(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean isAlreadySendUI(String str) {
        if (isAlreadySendUtMap == null) {
            isAlreadySendUtMap = new HashMap();
        }
        if (isAlreadySendUtMap.get(str) == null) {
            isAlreadySendUtMap.put(str, new Boolean(true));
            return false;
        }
        return true;
    }
}
