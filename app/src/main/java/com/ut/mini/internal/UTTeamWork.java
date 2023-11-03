package com.ut.mini.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.mtl.appmonitor.AppMonitor;
import com.alibaba.mtl.log.a;
import com.alibaba.mtl.log.b;
import com.alibaba.mtl.log.c.c;
import com.alibaba.mtl.log.d.p;
import com.ut.device.UTDevice;
import com.ut.mini.base.UTMIVariables;
import java.util.Map;

/* loaded from: classes11.dex */
public class UTTeamWork {
    private static UTTeamWork a = null;

    public static synchronized UTTeamWork getInstance() {
        UTTeamWork uTTeamWork;
        synchronized (UTTeamWork.class) {
            if (a == null) {
                a = new UTTeamWork();
            }
            uTTeamWork = a;
        }
        return uTTeamWork;
    }

    public void initialized() {
    }

    public void turnOnRealTimeDebug(Map<String, String> map) {
        AppMonitor.turnOnRealTimeDebug(map);
    }

    public void turnOffRealTimeDebug() {
        AppMonitor.turnOffRealTimeDebug();
    }

    public void dispatchLocalHits() {
    }

    public void saveCacheDataToLocal() {
        c.aA().aB();
    }

    public void setToAliyunOsPlatform() {
        UTMIVariables.getInstance().setToAliyunOSPlatform();
    }

    public String getUtsid() {
        String str;
        try {
            if (a.ac() != null) {
                str = a.ac().getAppkey();
            } else {
                str = null;
            }
            String utdid = UTDevice.getUtdid(b.aj().getContext());
            long longValue = Long.valueOf(a.bJ).longValue();
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(utdid)) {
                return utdid + "_" + str + "_" + longValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeAuto1010Track() {
        com.alibaba.mtl.log.c.ax().ay();
    }

    public void disableNetworkStatusChecker() {
    }

    public void enableUpload(boolean z) {
        a.bN = z;
    }

    public void setHost4Https(Context context, String str) {
        if (context == null) {
            Log.w("UTTeamWork", "context is null");
        } else if (TextUtils.isEmpty(str)) {
            Log.w("UTTeamWork", "host or port is empty");
        } else {
            com.alibaba.mtl.log.a.a.f(str);
            p.b(context, "utanalytics_https_host", str);
        }
    }

    public void clearHost4Https(Context context) {
        if (context == null) {
            Log.w("UTTeamWork", "context is null");
            return;
        }
        com.alibaba.mtl.log.a.a.f("");
        p.b(context, "utanalytics_https_host", null);
    }
}
