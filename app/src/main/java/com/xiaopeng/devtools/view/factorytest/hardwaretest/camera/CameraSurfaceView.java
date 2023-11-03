package com.xiaopeng.devtools.view.factorytest.hardwaretest.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.xiaopeng.lib.utils.c;

/* loaded from: classes12.dex */
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static Camera Jl;
    private int Jm;
    private Handler mHandler;
    private boolean mIsPreview;
    private SurfaceHolder sW;

    /* JADX INFO: Access modifiers changed from: private */
    public void mK() {
        try {
            Jl = Camera.open(this.Jm);
            c.f("CameraSurfaceView", "Camera open success");
        } catch (Exception e) {
            c.f("CameraSurfaceView", "Camera open Exception-->" + e.getMessage());
        }
    }

    public void mL() {
        new Thread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.CameraSurfaceView.1
            @Override // java.lang.Runnable
            public void run() {
                if (CameraSurfaceView.Jl == null) {
                    CameraSurfaceView.this.mK();
                    CameraSurfaceView.this.mM();
                    return;
                }
                CameraSurfaceView.this.mM();
            }
        }).start();
    }

    public void mM() {
        this.mHandler.post(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.CameraSurfaceView.2
            @Override // java.lang.Runnable
            public void run() {
                c.f("CameraSurfaceView", "mIsPreview-->" + CameraSurfaceView.this.mIsPreview);
                c.f("CameraSurfaceView", "sCamera-->" + CameraSurfaceView.Jl);
                if (!CameraSurfaceView.this.mIsPreview) {
                    if (CameraSurfaceView.Jl != null) {
                        try {
                            CameraSurfaceView.Jl.setPreviewDisplay(CameraSurfaceView.this.sW);
                            CameraSurfaceView.Jl.startPreview();
                            c.f("CameraSurfaceView", "Camera startPreview success");
                            CameraSurfaceView.this.mIsPreview = true;
                            return;
                        } catch (Exception e) {
                            c.f("CameraSurfaceView", "Camera startPreview Exception");
                            return;
                        }
                    }
                    return;
                }
                if (CameraSurfaceView.Jl != null) {
                    CameraSurfaceView.Jl.stopPreview();
                }
                CameraSurfaceView.this.mIsPreview = false;
                CameraSurfaceView.this.mM();
            }
        });
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        c.f("CameraSurfaceView", "surfaceCreated");
        mL();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        c.f("CameraSurfaceView", "surfaceChanged:format=" + i + ",width=" + i2 + ",height=" + i3);
        if (this.sW.getSurface() == null) {
            return;
        }
        mL();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        c.f("CameraSurfaceView", "surfaceDestroyed");
        mN();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        c.f("CameraSurfaceView", "onMeasure");
        super.onMeasure(i, i2);
    }

    public static Camera getCamera() {
        return Jl;
    }

    public void mN() {
        if (Jl != null) {
            Jl.setPreviewCallback(null);
            Jl.stopPreview();
            this.mIsPreview = false;
            Jl.release();
            Jl = null;
        }
    }

    private Camera.Size getBestSupportedSize() {
        if (Jl == null) {
            return null;
        }
        for (Camera.Size size : Jl.getParameters().getSupportedPreviewSizes()) {
            c.f("CameraSurfaceView", "SupportedPreviewSizes-->" + size.width + ":" + size.height);
        }
        return null;
    }
}
