package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.c;

import android.view.SurfaceHolder;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.lib.utils.j;

/* compiled from: CameraTestPresenter.java */
/* loaded from: classes12.dex */
public class a {
    private com.xiaopeng.devtools.model.c.a.c.a wN = com.xiaopeng.devtools.model.c.a.c.a.go();

    public a(SurfaceHolder surfaceHolder) {
        this.wN.a(surfaceHolder);
    }

    public void co(final int i) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.c.a.1
            @Override // java.lang.Runnable
            public void run() {
                g.F(a.c.ec("FPS_SELECTION_TOP_CAM"), String.valueOf(i));
            }
        });
    }

    public int getNumberOfCameras() {
        return com.xiaopeng.devtools.model.c.a.c.a.getNumberOfCameras();
    }

    public void cd(int i) {
        this.wN.cd(i);
    }

    public void gp() {
        this.wN.gp();
    }

    public void gq() {
        this.wN.gq();
    }

    public boolean gr() {
        return this.wN.gr();
    }

    public void stopRecording() {
        this.wN.stopRecording();
    }

    public boolean gs() {
        return this.wN.gs();
    }

    public void gu() {
        this.wN.gu();
    }

    public void gv() {
        this.wN.gv();
    }
}
