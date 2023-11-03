package com.xiaopeng.commonfunc.bean.event;

import com.xiaopeng.commonfunc.utils.d;

/* loaded from: classes11.dex */
public class CameraResultEvent {
    private int mCameraId;
    private String mPath;
    private final boolean mResult;
    private final int mStep;

    public CameraResultEvent(int i, int i2, boolean z) {
        this.mStep = i;
        this.mCameraId = i2;
        this.mResult = z;
    }

    public CameraResultEvent(int i, boolean z) {
        this.mStep = i;
        this.mResult = z;
    }

    public CameraResultEvent(int i, boolean z, String str) {
        this.mStep = i;
        this.mResult = z;
        this.mPath = str;
    }

    public int getStep() {
        return this.mStep;
    }

    public int getCameraId() {
        return this.mCameraId;
    }

    public boolean getResult() {
        return this.mResult;
    }

    public String getPath() {
        return this.mPath;
    }

    public byte[] getDmOpenCamArgu() {
        return new byte[]{1, d.bH(this.mCameraId)};
    }
}
