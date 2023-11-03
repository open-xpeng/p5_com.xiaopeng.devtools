package com.alibaba.sdk.android.man.crashreporter;

import java.util.Map;

/* loaded from: classes11.dex */
public interface IUTCrashCaughtListener {
    Map<String, Object> onCrashCaught(Thread thread, Throwable th);
}
