package com.xiaopeng.devtools.bean.event;

/* loaded from: classes12.dex */
public class ModemUpgradeEvent {
    private boolean isSuccess;

    public ModemUpgradeEvent(boolean z) {
        this.isSuccess = z;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }
}
