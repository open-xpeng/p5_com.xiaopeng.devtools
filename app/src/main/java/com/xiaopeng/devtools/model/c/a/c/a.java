package com.xiaopeng.devtools.model.c.a.c;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.view.SurfaceHolder;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.t;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* compiled from: CameraModel.java */
/* loaded from: classes12.dex */
public class a implements Camera.PictureCallback {
    private static a sS;
    private MediaRecorder sU;
    private Camera sV;
    private SurfaceHolder sW;
    private Object sT = new Object();
    private int sX = -1;

    private a() {
    }

    public static a go() {
        if (sS == null) {
            c.f("CameraModel", "create CameraModel Instance");
            sS = new a();
        }
        return sS;
    }

    public void a(SurfaceHolder surfaceHolder) {
        this.sW = surfaceHolder;
    }

    public static int getNumberOfCameras() {
        return Camera.getNumberOfCameras();
    }

    public void cd(int i) {
        synchronized (this.sT) {
            c.f("CameraModel", "openCamera state = " + i);
            this.sX = i;
            if (this.sV == null) {
                this.sV = Camera.open(i);
            }
        }
    }

    public void gp() {
        synchronized (this.sT) {
            try {
                if (this.sV != null && this.sW.getSurface() != null) {
                    c.g("CameraModel", "setPreviewSurface before");
                    this.sV.setPreviewSurface(this.sW.getSurface());
                    c.g("CameraModel", "setPreviewSurface after");
                    this.sV.startPreview();
                    c.g("CameraModel", "startPreview after");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void gq() {
        synchronized (this.sT) {
            if (this.sV != null) {
                c.g("CameraModel", "stopPreview before");
                this.sV.stopPreview();
                c.g("CameraModel", "stopPreview after");
                this.sV.release();
                c.g("CameraModel", "release after");
                this.sV = null;
                this.sX = -1;
            }
        }
    }

    public boolean gr() {
        c.f("CameraModel", "startRecording");
        if (gt()) {
            this.sU.start();
            return true;
        }
        gu();
        return false;
    }

    public void stopRecording() {
        c.f("CameraModel", "stopRecording");
        if (this.sU != null) {
            this.sU.stop();
        }
        gu();
    }

    public boolean gs() {
        boolean z = this.sU != null;
        c.f("CameraModel", "isRecording = " + z);
        return z;
    }

    private boolean gt() {
        boolean z;
        synchronized (this.sT) {
            c.f("CameraModel", "prepareMediaRecorder");
            z = false;
            try {
                if (this.sV != null) {
                    this.sU = new MediaRecorder();
                    List<Camera.Size> supportedVideoSizes = getCameraParameters().getSupportedVideoSizes();
                    this.sV.unlock();
                    this.sU.setCamera(this.sV);
                    this.sU.setVideoSource(1);
                    c.f("CameraModel", "prepareMediaRecorder mCurrentState = " + this.sX);
                    this.sU.setProfile(CamcorderProfile.get(this.sX, 5));
                    c.f("CameraModel", "prepareMediaRecorder width:" + supportedVideoSizes.get(0).width + ", height:" + supportedVideoSizes.get(0).height);
                    this.sU.setVideoSize(supportedVideoSizes.get(0).width, supportedVideoSizes.get(0).height);
                    this.sU.setPreviewDisplay(this.sW.getSurface());
                    String kZ = g.kZ();
                    if (kZ != null) {
                        String str = kZ + File.separator + "VideoRecorderTest/";
                        g.aW(str);
                        String str2 = str + t.eI() + ".mp4";
                        c.f("CameraModel", "prepareMediaRecorder path = " + str2);
                        this.sU.setOutputFile(str2);
                        this.sU.prepare();
                        z = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        c.f("CameraModel", "prepareMediaRecorder ret:" + z);
        return z;
    }

    private Camera.Parameters getCameraParameters() {
        c.f("CameraModel", "getCameraParameters");
        try {
            return this.sV.getParameters();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void gu() {
        synchronized (this.sT) {
            c.f("CameraModel", "releaseMediaRecorder");
            if (this.sU != null) {
                this.sU.reset();
                this.sU.release();
                this.sU = null;
                this.sV.lock();
            }
        }
    }

    public void gv() {
        synchronized (this.sT) {
            c.f("CameraModel", "takePicture");
            if (this.sV != null) {
                this.sV.takePicture(null, null, this);
            }
        }
    }

    @Override // android.hardware.Camera.PictureCallback
    public void onPictureTaken(final byte[] bArr, Camera camera) {
        c.f("CameraModel", "onPictureTaken");
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.model.c.a.c.-$$Lambda$a$ku74awA3haLmrJRCSQsRwdo8_G8
            @Override // java.lang.Runnable
            public final void run() {
                a.this.v(bArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public void v(byte[] bArr) {
        String kZ = g.kZ();
        if (kZ != null) {
            String str = kZ + File.separator + "Pictures/" + t.cL("yyyy-MM-dd HH.mm.ss") + ".jpg";
            c.f("CameraModel", "savePicToSd path = " + str);
            g.c(str, bArr);
        }
    }
}
