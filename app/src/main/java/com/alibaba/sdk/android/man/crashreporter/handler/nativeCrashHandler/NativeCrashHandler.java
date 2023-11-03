package com.alibaba.sdk.android.man.crashreporter.handler.nativeCrashHandler;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.c;
import com.alibaba.sdk.android.man.crashreporter.handler.a;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes11.dex */
public class NativeCrashHandler implements NativeExceptionHandler {
    private static boolean LOAD_SUCCESS;
    private static NativeCrashHandler nativeCrashHandler;
    private final String MOTU_PATH = "motu";
    private final String TOMBSTONE_PATH = "tombstone";
    private a crashReportManager = null;
    private AtomicBoolean crashing;
    private final String motuPath;

    public static native String regist(String str, boolean z, int i, long j, String str2);

    public static native String resetSigHandler();

    public static native String unregist();

    static {
        LOAD_SUCCESS = false;
        try {
            System.loadLibrary("Motu");
            LOAD_SUCCESS = true;
        } catch (Error e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("load motu library error.", e);
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler2;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler2 = nativeCrashHandler;
        }
        return nativeCrashHandler2;
    }

    public static synchronized NativeCrashHandler init(Context context) {
        NativeCrashHandler nativeCrashHandler2;
        synchronized (NativeCrashHandler.class) {
            if (nativeCrashHandler == null) {
                nativeCrashHandler = new NativeCrashHandler(context);
            }
            nativeCrashHandler2 = nativeCrashHandler;
        }
        return nativeCrashHandler2;
    }

    private NativeCrashHandler(Context context) {
        this.motuPath = String.format("%s/%s", context.getDir("tombstone", 0).getAbsolutePath(), "motu");
        File file = new File(this.motuPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public boolean removeNativeCrashHandler() {
        if (resetSigHandler() != null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Native crash handler is removed success");
            return true;
        }
        com.alibaba.sdk.android.man.crashreporter.b.a.e("Native crash handler is removed failed");
        return false;
    }

    public boolean regist(AtomicBoolean atomicBoolean, a aVar, boolean z, c cVar) {
        if (LOAD_SUCCESS) {
            this.crashing = atomicBoolean;
            this.crashReportManager = aVar;
            String str = cVar.appVersion;
            if (str == null) {
                str = "";
            }
            try {
                return regist(this.motuPath, false, 1, cVar.startupTime, str) != null;
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("regist native crash err", e);
                return false;
            } catch (UnsatisfiedLinkError e2) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("regist native crash err,UnsatisfiedLinkError:", e2);
                return false;
            }
        }
        return false;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.handler.nativeCrashHandler.NativeExceptionHandler
    public void onNativeExceptionStart(String str, String str2, String str3) {
        com.alibaba.sdk.android.man.crashreporter.b.a.e("onNativeExceptionStart call back.");
        try {
            if (str != null) {
                MotuCrashReporter.getInstance().setCrashReporterState(1);
                com.alibaba.sdk.android.man.crashreporter.b.a.e("stuck handler is closed");
                if (this.crashReportManager != null) {
                    this.crashReportManager.a(str2, str, str3);
                } else {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("native: crash manager is null!");
                }
                return;
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.e("native crash stack or path is null!");
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("onNativeException err", e);
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.handler.nativeCrashHandler.NativeExceptionHandler
    public void onNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        try {
            if (str4 != null) {
                if (this.crashReportManager != null) {
                    this.crashReportManager.a(str5, str4, str3);
                } else {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("native: crash manager is null!");
                }
                try {
                    try {
                        unregist();
                    } catch (Exception e) {
                        com.alibaba.sdk.android.man.crashreporter.b.a.d("unregist native crash err", e);
                    }
                } catch (UnsatisfiedLinkError e2) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("unregist native crash err,UnsatisfiedLinkError:", e2);
                }
                return;
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.e("native crash stack or path is null!");
        } catch (Exception e3) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("onNativeException err", e3);
        }
    }
}
