package com.xiaopeng.commonfunc.bean.event;

/* loaded from: classes11.dex */
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
