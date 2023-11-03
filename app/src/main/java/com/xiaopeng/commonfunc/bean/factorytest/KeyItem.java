package com.xiaopeng.commonfunc.bean.factorytest;

/* loaded from: classes11.dex */
public class KeyItem {
    private final boolean mIsNeedResultButton;
    private String mKeyName;
    private boolean mKeyResult;

    public KeyItem(String str, boolean z, boolean z2) {
        this.mKeyName = str;
        this.mKeyResult = z;
        this.mIsNeedResultButton = z2;
    }

    public String getKeyName() {
        return this.mKeyName;
    }

    public void setKeyName(String str) {
        this.mKeyName = str;
    }

    public boolean isKeyPass() {
        return this.mKeyResult;
    }

    public void setKeyResult(boolean z) {
        this.mKeyResult = z;
    }

    public boolean isNeedResultButton() {
        return this.mIsNeedResultButton;
    }
}
