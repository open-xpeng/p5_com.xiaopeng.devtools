package com.xiaopeng.lib.framework.moduleinterface.syncmodule;

import android.support.annotation.NonNull;

/* loaded from: classes12.dex */
public final class SyncOTPEvent {
    public IOTPCallback callback;
    public String deviceID;
    public String seq;

    public SyncOTPEvent(@NonNull String str, @NonNull String str2, @NonNull IOTPCallback iOTPCallback) {
        this.seq = str;
        this.deviceID = str2;
        this.callback = iOTPCallback;
    }

    public String toString() {
        return "SyncOTPEvent { seq:" + this.seq + "; deviceID:" + this.deviceID + "; }";
    }
}
