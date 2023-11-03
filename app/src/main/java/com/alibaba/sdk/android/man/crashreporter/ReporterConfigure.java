package com.alibaba.sdk.android.man.crashreporter;

/* loaded from: classes11.dex */
public class ReporterConfigure {
    public boolean enableCatchUncaughtException = true;
    public boolean enableCatchNativeException = true;
    public boolean enableCatchANRException = true;
    public boolean enableStartCount = true;
    public int sendOnLaunchDelay = 0;
    public boolean enableActivityMonitor = true;
    public boolean enableDumpSysLog = false;
    public boolean enableDumpEventsLog = false;
    public boolean enableDumpRadioLog = false;
    public boolean enableDumpAppLog = false;
    public boolean enableDumpAllThread = false;
    public boolean enableDebug = false;
    public int enabeANRTimeoutInterval = 5000;
    public boolean enableANRMainThreadOnly = false;
    public boolean enableDeduplication = false;
    public boolean enableAbortCount = false;
    public int enableMaxThreadNumber = 15;
    public int enableMaxThreadStackTraceNumber = 15;
    public int enableSysLogcatMaxCount = 100;
    public int enableSysLogcatLinkMaxCount = 100;

    public void setEnableCatchANRException(boolean z) {
        this.enableCatchANRException = z;
    }

    public void setEnableDumpSysLog(boolean z) {
        this.enableDumpSysLog = z;
    }

    public void setEnableDumpEventsLog(boolean z) {
        this.enableDumpEventsLog = z;
    }

    public void setEnableDumpRadioLog(boolean z) {
        this.enableDumpRadioLog = z;
    }

    public void setEnableDumpAppLog(boolean z) {
        this.enableDumpAppLog = z;
    }

    public void setEnableDumpAllThread(boolean z) {
        this.enableDumpAllThread = z;
    }

    public void setEnableDebug(boolean z) {
        this.enableDebug = z;
    }

    public void setEnableANRMainThreadOnly(boolean z) {
        this.enableANRMainThreadOnly = z;
    }
}
