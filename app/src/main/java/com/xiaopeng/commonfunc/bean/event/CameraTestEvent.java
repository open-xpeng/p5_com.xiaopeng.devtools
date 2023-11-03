package com.xiaopeng.commonfunc.bean.event;

/* loaded from: classes11.dex */
public class CameraTestEvent {
    public static final int CAMERA_CLOSE = 0;
    public static final int CAMERA_OPEN = 1;
    public static final int CAMERA_TAKEPIC = 2;
    private int mCameraId;
    private final int mStep;

    public CameraTestEvent(int i, int i2) {
        this.mStep = i;
        this.mCameraId = i2;
    }

    public CameraTestEvent(int i) {
        this.mStep = i;
    }

    public int getStep() {
        return this.mStep;
    }

    public int getCameraId() {
        return this.mCameraId;
    }
}
