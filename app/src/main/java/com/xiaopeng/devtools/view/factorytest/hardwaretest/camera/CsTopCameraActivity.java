package com.xiaopeng.devtools.view.factorytest.hardwaretest.camera;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.c.a;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.g;

/* loaded from: classes12.dex */
public class CsTopCameraActivity extends ActionBarActivity implements SurfaceHolder.Callback {
    private static final Integer[] Jy = {0, 30, 60};
    private TextView JA;
    private SurfaceView Jv;
    private a Jw;
    private int Jx = 3;
    private Spinner Jz;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.Jw.gq();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_cs_top_camera);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Jz = (Spinner) findViewById(R.id.spinner_selection_fps);
        this.JA = (TextView) findViewById(R.id.tv_current_fps);
        this.Jv = (SurfaceView) findViewById(R.id.camera_preview);
        this.Jw = new a(this.Jv.getHolder());
        this.Jv.getHolder().addCallback(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Jz.setAdapter((SpinnerAdapter) new ArrayAdapter(this, (int) R.layout.spinner_item, Jy));
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Jz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.CsTopCameraActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                CsTopCameraActivity.this.Jw.co(i);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void cW(final int i) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.-$$Lambda$CsTopCameraActivity$qxHizZNpxRkcA6gpOfWaYs-Dpbo
            @Override // java.lang.Runnable
            public final void run() {
                CsTopCameraActivity.this.cX(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void cX(int i) {
        boolean z;
        try {
            this.Jw.gq();
            this.Jw.cd(i);
            this.Jw.gp();
            z = true;
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        f(i, z);
    }

    private void f(int i, final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.-$$Lambda$CsTopCameraActivity$SwL1i3alPnMB-lhy8bYbfOCGBPs
            @Override // java.lang.Runnable
            public final void run() {
                CsTopCameraActivity.au(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void au(boolean z) {
        if (!z) {
            g.show(R.string.camera_preview_fail);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        c.f("CsTopCameraActivity", "surfaceCreated");
        int numberOfCameras = this.Jw.getNumberOfCameras();
        c.f("CsTopCameraActivity", "camera num:" + numberOfCameras);
        if (numberOfCameras > 0) {
            cW(this.Jx);
            return;
        }
        c.f("CsTopCameraActivity", "surfaceCreated but not found camera");
        g.show(R.string.no_camera);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        c.f("CsTopCameraActivity", "surfaceChanged format = " + i + " , width = " + i2 + " , height = " + i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        c.f("CsTopCameraActivity", "surfaceDestroyed");
        this.Jw.gq();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Jw.gq();
    }
}
