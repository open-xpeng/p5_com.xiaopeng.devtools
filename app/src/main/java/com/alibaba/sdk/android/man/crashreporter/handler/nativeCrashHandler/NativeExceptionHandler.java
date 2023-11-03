package com.alibaba.sdk.android.man.crashreporter.handler.nativeCrashHandler;

/* loaded from: classes11.dex */
public interface NativeExceptionHandler {
    void onNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7);

    void onNativeExceptionStart(String str, String str2, String str3);
}
