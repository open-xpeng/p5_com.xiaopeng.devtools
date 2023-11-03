package com.alibaba.sdk.android.man.crashreporter;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes11.dex */
public final class MotuCrashReporter {
    private static MotuCrashReporter instance = null;
    private ReporterConfigure configure;
    private c environment;
    private long startupTime = System.currentTimeMillis();
    private AtomicBoolean isEnable = new AtomicBoolean(true);
    private int crashReporterState = -1;
    private com.alibaba.sdk.android.man.crashreporter.handler.b crashReportManager = null;
    List myExtListenerList = new ArrayList();
    List mySenderListenerList = new ArrayList();
    private String strExtraInfo = null;

    private MotuCrashReporter() {
    }

    private static synchronized MotuCrashReporter initMotuCrashReporter() {
        MotuCrashReporter motuCrashReporter;
        synchronized (MotuCrashReporter.class) {
            if (instance == null) {
                instance = new MotuCrashReporter();
            }
            motuCrashReporter = instance;
        }
        return motuCrashReporter;
    }

    public static MotuCrashReporter getInstance() {
        if (instance == null) {
            initMotuCrashReporter();
        }
        return instance;
    }

    public boolean enable(Context context, String str, String str2, String str3, String str4, ReporterConfigure reporterConfigure) {
        if (this.isEnable == null || !this.isEnable.get()) {
            return false;
        }
        if (reporterConfigure != null) {
            this.configure = reporterConfigure;
        } else {
            this.configure = new ReporterConfigure();
        }
        try {
            String str5 = context.getApplicationInfo().packageName;
            if (str5 != null && str5.equals("com.taobao.taobao")) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("package name:" + str5);
                if (context == null) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("enable failure. because context equal to null!");
                    return false;
                }
            } else {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("package name:" + str5);
                if (context != null) {
                    if (str != null) {
                        if (str2 == null) {
                        }
                    }
                }
                com.alibaba.sdk.android.man.crashreporter.b.a.e("enable failure. because context or appKey or appVersion equal to null!");
                return false;
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.b("start enable. context:", context.toString());
            com.alibaba.sdk.android.man.crashreporter.b.a.b("start enable. appKey: ", str == null ? "use taobao detault" : str);
            com.alibaba.sdk.android.man.crashreporter.b.a.b("start enable. appVersion: ", str2 == null ? "use taobao default" : str2);
            if (this.environment == null) {
                this.environment = new c();
            }
            this.environment.appKey = str;
            this.environment.appVersion = str2;
            this.environment.channel = str3;
            this.environment.userNick = str4;
            this.environment.startupTime = this.startupTime;
            this.crashReportManager = new com.alibaba.sdk.android.man.crashreporter.handler.a();
            if (this.crashReportManager.a(context, this.configure, this.environment)) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("enable succ!");
                return true;
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.e("enable failure!");
            return true;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("enable err", e);
            return false;
        }
    }

    public boolean turnoffCrashReporter() {
        if (!this.isEnable.compareAndSet(true, false) || this.crashReportManager == null) {
            return false;
        }
        return this.crashReportManager.c();
    }

    public void setCrashCaughtListener(IUTCrashCaughtListener iUTCrashCaughtListener) {
        com.alibaba.sdk.android.man.crashreporter.b.a.b("setCrashCaughtListener", iUTCrashCaughtListener == null ? "ext listener is null" : iUTCrashCaughtListener.toString());
        if (this.myExtListenerList != null) {
            this.myExtListenerList.add(iUTCrashCaughtListener);
        }
    }

    public List getMyListenerList() {
        return this.myExtListenerList;
    }

    public void setSenderListener(a aVar) {
        com.alibaba.sdk.android.man.crashreporter.b.a.b("setSenderListener", aVar == null ? "sender listener is null" : aVar.toString());
        if (this.mySenderListenerList != null) {
            this.mySenderListenerList.add(aVar);
        }
    }

    public List getMySenderListenerList() {
        return this.mySenderListenerList;
    }

    public void setExtraInfo(String str) {
        this.strExtraInfo = str;
    }

    public String getStrExtraInfo() {
        return this.strExtraInfo;
    }

    public void setUserNick(String str) {
        if (this.environment == null) {
            this.environment = new c();
        }
        if (str != null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.b("set user nick succ!", str);
            this.environment.userNick = str;
        }
    }

    public void setAppVersion(String str) {
        if (this.environment == null) {
            this.environment = new c();
        }
        if (str != null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.b("set appVersion succ!", str);
            this.environment.appVersion = str;
        }
    }

    public void setTTid(String str) {
        if (this.environment == null) {
            this.environment = new c();
        }
        if (str != null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.b("set ttid succ!", str);
            this.environment.channel = str;
        }
    }

    public void setCrashReporterState(int i) {
        this.crashReporterState = i;
    }

    public int getCrashReporterState() {
        return this.crashReporterState;
    }

    public ReporterConfigure getConfigure() {
        return this.configure;
    }
}
