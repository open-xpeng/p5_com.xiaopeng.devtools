package com.xiaopeng.commonfunc.system.nativeImp;

/* loaded from: classes11.dex */
public class Security {
    public native boolean checkUnlockTimeValid(int i);

    public native boolean isUnlockKeyValid(String str);

    public native boolean isUnlockKeyValidV2(String str);

    static {
        System.loadLibrary("xpsecurity2");
    }
}
